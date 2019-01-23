package com.fengz.personal.fengarchitecture.business1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.fengz.personal.fengarchitecture.R;
import com.fengz.personal.fengarchitecture.base.mvp.APresenter;
import com.fengz.personal.fengarchitecture.base.mvp.BaseActivity;
import com.fengz.personal.fengarchitecture.business1.contract.LoginContract;
import com.fengz.personal.fengarchitecture.business1.presenter.LoginPresenter;
import com.fengz.personal.fengarchitecture.business1.ui.Navigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    public static Intent getCallingIntent(@NonNull Context context){
        return new Intent(context,LoginActivity.class);
    }

    @Inject
    @APresenter
    LoginContract.Presenter mPresenter;

    @Inject
    Navigator mNavigator;

    @BindView(R.id.et_user_login_act)
    EditText mEtUser;
    @BindView(R.id.et_password_login_act)
    EditText mEtPassword;
    @BindView(R.id.btn_login_login_act)
    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("");
        setHomeAsUp(R.drawable.ic_cancel_gray);
        reloadLastUser();
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
        mNavigator.navigator2MainAct(this);
        onBackPressed();
    }

    private boolean check(String name, String pwd) {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd));
    }

    @OnClick(R.id.btn_login_login_act)
    public void onViewClicked() {
        String name = mEtUser.getText().toString();
        String pwd = mEtPassword.getText().toString();
        if (check(name, pwd)) {
            mPresenter.login(name, pwd);
        }
    }

    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED,
            value = {R.id.et_user_login_act, R.id.et_password_login_act})
    public void onTextChanged() {
        String name = mEtUser.getText().toString();
        String pwd = mEtPassword.getText().toString();
        mBtnLogin.setEnabled(check(name, pwd));
    }
}
