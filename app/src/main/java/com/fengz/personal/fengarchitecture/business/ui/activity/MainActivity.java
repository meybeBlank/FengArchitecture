package com.fengz.personal.fengarchitecture.business.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.fengz.personal.fengarchitecture.R;
import com.fengz.personal.fengarchitecture.base.mvp.APresenter;
import com.fengz.personal.fengarchitecture.base.mvp.BaseActivity;
import com.fengz.personal.fengarchitecture.business.contract.MainContract;
import com.fengz.personal.fengarchitecture.business.contract.UpdateVersionContract;
import com.fengz.personal.fengarchitecture.business.model.entity.CheckVersionBean;
import com.fengz.personal.fengarchitecture.business.ui.adapter.MainPageAdapter;
import com.fengz.personal.fengarchitecture.common.ProgressDialog;
import com.fengz.personal.fengarchitecture.common.DialogManager;
import com.fengz.personal.fengarchitecture.utils.ScreenUtils;
import com.fengz.personal.fengarchitecture.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：主界面 包含四个子界面
 */
public class MainActivity extends BaseActivity implements MainContract.View, UpdateVersionContract.View {

    private static final int REQUEST_PERMISSION = 0x123;

    @BindView(R.id.viewpager_main_act)
    AHBottomNavigationViewPager mViewpagerMainAct;
    @BindView(R.id.navigator_main_act)
    AHBottomNavigation mNavigatorMainAct;
    @BindView(R.id.tv_title_main_act)
    TextView mTvTitle;

    private MainPageAdapter mAdapter;
    private long lastClick = 0;

    private AlertDialog mAlertDialog;
    private CheckVersionBean mVersionBean;
    private ProgressDialog mProgressDialog;

    @Inject
    @APresenter
    UpdateVersionContract.Presenter mUpdatePresenter;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

//    private static String json = "{\"ret\":1000,\"content\":{\"scalar\":false},\"msg\":\"msg是无知的\",\"timestamp\":1553828024,\"zone_id\":\"6000\",\"list\":[{\"a\":\"listinfo1\"},{\"a\":\"listinfo2\"},{\"a\":\"listinfo3\"}]}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        mUpdatePresenter.checkUpdate();
    }

    public void setFull(boolean full) {
        ScreenUtils.setFullScreen(this, full);
    }

    private void initUI() {

        mAdapter = new MainPageAdapter(getSupportFragmentManager());
        mViewpagerMainAct.setAdapter(mAdapter);
        mViewpagerMainAct.setOffscreenPageLimit(4);
        mViewpagerMainAct.setPagingEnabled(false);
        mViewpagerMainAct.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                if (i == 0 || (i == 2 && v > 0) || (i == 3 && v == 0)) {
//                    mTvTitle.setVisibility(View.INVISIBLE);
//                    setFull(false);
//                } else {
//                    setFull(true);
//                    mTvTitle.setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public void onPageSelected(int i) {
                mNavigatorMainAct.setCurrentItem(i);
                switch (i) {
                    case 0:
                        mTvTitle.setVisibility(View.GONE);
                        setFull(false);
                        break;
                    case 1:
                        mTvTitle.setText("段子");
                        mTvTitle.setVisibility(View.VISIBLE);
                        setFull(true);
                        break;
                    case 2:
                        mTvTitle.setText("视频");
                        mTvTitle.setVisibility(View.VISIBLE);
                        setFull(true);
                        break;
                    case 3:
                        mTvTitle.setText("我的");
                        mTvTitle.setVisibility(View.VISIBLE);
                        setFull(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_4);
        navigationAdapter.setupWithBottomNavigation(mNavigatorMainAct, tabColors);
        mNavigatorMainAct.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        mNavigatorMainAct.setCurrentItem(1);
        // 按钮颜色 和 背景同化 true tabColors才生效
//        mNavigatorMainAct.setColored(true);
        mNavigatorMainAct.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mNavigatorMainAct.setInactiveColor(ContextCompat.getColor(this, R.color.c_333333_text));
        // 背景色
        mNavigatorMainAct.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.white_darker));
        // 圆角图标
//        AHNotification notification = new AHNotification.Builder()
//                .setText("233")
//                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.c_2d2d2d_text_gray))
//                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey_dark))
//                .build();
//        mNavigatorMainAct.setNotification(notification, 1);
        mNavigatorMainAct.setOnTabSelectedListener((position, wasSelected) -> {
            mViewpagerMainAct.setCurrentItem(position, false);
            return true;
        });

//        // Display color under navigation bar (API 21+)
//        // Don't forget these lines in your style-v21
//        // <item name="android:windowTranslucentNavigation">true</item>
//        // <item name="android:fitsSystemWindows">true</item>
//        mNavigatorMainAct.setTranslucentNavigationEnabled(true);
//        // Force to tint the drawable (useful for font with icon for example)
//        mNavigatorMainAct.setForceTint(true);
//        // Use colored navigation with circle reveal effect
//        mNavigatorMainAct.setColored(true);
//
//        // Customize notification (title, background, typeface)
////        mNavigatorMainAct.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
////        // Add or remove notification for each item
////        mNavigatorMainAct.setNotification("1", 3);
////        // OR
//
//        // Enable / disable item & set disable color
////        mNavigatorMainAct.enableItemAtPosition(2);
////        mNavigatorMainAct.disableItemAtPosition(2);
////        mNavigatorMainAct.setItemDisableColor(Color.parseColor("#3A000000"));
//
//        mNavigatorMainAct.setOnNavigationPositionListener(y -> {
//            // Manage the new y position
//        });
    }

    @Override
    public void onBackPressed() {
        if (mViewpagerMainAct.getCurrentItem() == 0) {
            long second = System.currentTimeMillis() / 1000;
            if (second - lastClick <= 2) {
                super.onBackPressed();
            } else {
                lastClick = second;
                ToastUtils.show("再次点击退出程序");
            }
        } else {
            switchFragment(0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void switchFragment(int position) {
        mNavigatorMainAct.setCurrentItem(position);
    }

    @Override
    public void updateApk(CheckVersionBean bean) {
        mVersionBean = bean;
        checkPermission(bean);
    }

    public void showUpdateDialog(CheckVersionBean bean) {
        mAlertDialog = DialogManager.createAlertDialog(this, "新版本升级",
                bean.getUpContent(), "升级", "取消",
                (dialogInterface, i) -> {
                    mUpdatePresenter.downloadAndInstall(bean);
                    mProgressDialog = DialogManager.showProgressDialog(MainActivity.this, "下载中",
                            (dialogInterface1, i1) -> {
                                mUpdatePresenter.cancelDownload();
                            });
                    mProgressDialog.show();
                },
                (dialogInterface, i) ->
                        mAlertDialog.dismiss());
        mAlertDialog.show();
    }

    @Override
    public void refreshProgress(int current, int total) {
        if (mProgressDialog != null) {
            if (current == total) {
                mProgressDialog.dismiss();
            } else {
                mProgressDialog.setProgress(current, total);
            }
        }
    }

    private void checkPermission(CheckVersionBean bean) {
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            showUpdateDialog(bean);
        } else if (permission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showUpdateDialog(mVersionBean);
                } else {
                    ToastUtils.show("没有权限，无法升级，请开启权限重试");
                }
                break;
        }
    }
}
