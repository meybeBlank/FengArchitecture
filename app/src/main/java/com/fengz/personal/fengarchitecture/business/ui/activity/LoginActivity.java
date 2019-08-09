package com.fengz.personal.fengarchitecture.business.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fengz.personal.fengarchitecture.R;
import com.fengz.personal.fengarchitecture.base.mvp.APresenter;
import com.fengz.personal.fengarchitecture.base.mvp.BaseActivity;
import com.fengz.personal.fengarchitecture.business.contract.LoginContract;
import com.fengz.personal.fengarchitecture.business.contract.UpdateVersionContract;
import com.fengz.personal.fengarchitecture.business.model.entity.CheckVersionBean;
import com.fengz.personal.fengarchitecture.business.ui.Navigator;
import com.fengz.personal.fengarchitecture.common.ProgressDialog;
import com.fengz.personal.fengarchitecture.common.DialogManager;
import com.fengz.personal.fengarchitecture.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends BaseActivity implements LoginContract.View, UpdateVersionContract.View {

    public static final String PARA_FORM_MAIN = "para_form_main";
    private static final int REQUEST_PERMISSION = 0x123;

    public static Intent getCallingIntent(@NonNull Context context, boolean formMain) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(PARA_FORM_MAIN, formMain);
        return intent;
    }

    @Inject
    @APresenter
    LoginContract.Presenter mPresenter;

    @Inject
    @APresenter
    UpdateVersionContract.Presenter mUpdatePresenter;

    @Inject
    Navigator mNavigator;

    @BindView(R.id.et_user_login_act)
    EditText mEtUser;
    @BindView(R.id.et_password_login_act)
    EditText mEtPassword;
    @BindView(R.id.btn_login_login_act)
    Button mBtnLogin;

    private ProgressDialog mProgressDialog;
    private AlertDialog mAlertDialog;
    private boolean canBack;
    private CheckVersionBean mVersionBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        canBack = getIntent().getBooleanExtra(PARA_FORM_MAIN, false);
        setTitle("");
        setHomeAsUp(R.drawable.ic_cancel_gray);
        reloadLastUser();
        mUpdatePresenter.checkUpdate();
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

    private void reloadLastUser() {
        String[] userInfo = mPresenter.getLastUserInfo();
        mEtUser.setText(userInfo[0]);
        mEtPassword.setText(userInfo[1]);
    }

    @Override
    public void clearPwd() {
        mEtPassword.setText("");
    }

    @Override
    public void showLoading() {
        showLoadingB(true);
    }

    @Override
    public void dismissLoading() {
        dismissLoadingB();
    }

    @Override
    public void loginSuccess() {
        if (!canBack) {
            mNavigator.navigator2MainAct(this);
        }
        onBackPressed();
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
                    mProgressDialog = DialogManager.showProgressDialog(LoginActivity.this, "下载中",
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

    private boolean check(String name, String pwd) {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd));
    }

    @OnClick({R.id.btn_login_login_act, R.id.tv_skip_login_act})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login_act:
                String name = mEtUser.getText().toString();
                String pwd = mEtPassword.getText().toString();
                if (check(name, pwd)) {
                    mPresenter.login(name, pwd);
                }
                break;
            case R.id.tv_skip_login_act:
                // 假设登录处理
                loginSuccess();
                break;
        }
    }

    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED,
            value = {R.id.et_user_login_act, R.id.et_password_login_act})
    public void onTextChanged() {
        String name = mEtUser.getText().toString();
        String pwd = mEtPassword.getText().toString();
        mBtnLogin.setEnabled(check(name, pwd));
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
