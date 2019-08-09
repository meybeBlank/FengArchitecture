package com.fengz.personal.fengarchitecture.business.contract;

import com.fengz.personal.fengarchitecture.base.mvp.IPresenter;
import com.fengz.personal.fengarchitecture.base.mvp.IView;

public class WelcomeContract {

    public interface View extends IView {
        void launchLoginAct();

        void launchMainAct();
    }

    public interface Presenter extends IPresenter<View> {

        void login(String userName, String password);

        String[] getLastUserInfo();
    }
}
