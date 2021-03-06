package com.fengz.personal.fengarchitecture.business.presenter;

import com.fengz.personal.fengarchitecture.base.Constants;
import com.fengz.personal.fengarchitecture.base.mvp.BasePresenter;
import com.fengz.personal.fengarchitecture.business.contract.WelcomeContract;
import com.fengz.personal.fengarchitecture.business.model.PreferencesRepository;
import com.fengz.personal.fengarchitecture.business.model.api.B1Repository;
import com.fengz.personal.fengarchitecture.business.model.entity.UserModel;
import com.fengz.personal.fengarchitecture.di.ActivityScope;
import com.fengz.personal.fengarchitecture.http.BaseObserver.BaseObserver;

import javax.inject.Inject;

//@ActivityScope
public class WelcomePresenter extends BasePresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    B1Repository mB1Repository;
    PreferencesRepository mPreRepository;

    @Inject
    public WelcomePresenter(B1Repository mB1Repository, PreferencesRepository preRepository) {
        this.mB1Repository = mB1Repository;
        this.mPreRepository = preRepository;
    }

    @Override
    public void login(String userName, String password) {
        userLogin(userName, password);
    }

    @Override
    public String[] getLastUserInfo() {
        return mPreRepository.getLoginInfo();
    }

    private void userLogin(String userName, String password) {
        mB1Repository.loginUser(userName, password)
                .compose(getView().getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<UserModel>() {
                    @Override
                    public void onErrors(String e) {
                        getView().launchLoginAct();
                    }

                    @Override
                    public void onResponse(UserModel response) {
                        saveUserInfo(userName, password, response);
                        getView().launchMainAct();
                    }
                });
    }

    private void saveUserInfo(String userName, String password, UserModel userModel) {
        mPreRepository.setLoginInfo(userName, password);
        Constants.setUser(userModel);
    }
}
