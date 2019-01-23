package com.fengz.personal.fengarchitecture.business1.presenter;

import com.fengz.personal.fengarchitecture.base.Constants;
import com.fengz.personal.fengarchitecture.base.mvp.BasePresenter;
import com.fengz.personal.fengarchitecture.business1.contract.HomeContract;
import com.fengz.personal.fengarchitecture.business1.contract.MineContract;
import com.fengz.personal.fengarchitecture.business1.model.PreferencesRepository;

import javax.inject.Inject;

public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter {

    PreferencesRepository mPreRepository;

    @Inject
    public MinePresenter(PreferencesRepository preRepository) {
        this.mPreRepository = preRepository;
    }

    @Override
    public void logout() {
        Constants.setUser(null);
        mPreRepository.setLoginInfo(null, null);
        getView().logout();
    }
}
