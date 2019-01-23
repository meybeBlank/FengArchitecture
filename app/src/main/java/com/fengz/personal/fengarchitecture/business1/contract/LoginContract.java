package com.fengz.personal.fengarchitecture.business1.contract;

import com.fengz.personal.fengarchitecture.base.mvp.IPresenter;
import com.fengz.personal.fengarchitecture.base.mvp.IView;

public class LoginContract {

    public interface View extends IView {
        void clearPwd();

        void showLoading();

        void dismissLoading();

        void loginSuccess();
    }

    public interface Presenter extends IPresenter<View> {

        void login(String userName, String password);

        String[] getLastUserInfo();
    }
}
