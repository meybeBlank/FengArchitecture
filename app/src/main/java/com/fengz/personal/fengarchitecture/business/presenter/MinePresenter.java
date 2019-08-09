package com.fengz.personal.fengarchitecture.business.presenter;

import com.fengz.personal.fengarchitecture.base.Constants;
import com.fengz.personal.fengarchitecture.base.mvp.BasePresenter;
import com.fengz.personal.fengarchitecture.business.contract.HomeContract;
import com.fengz.personal.fengarchitecture.business.contract.MineContract;
import com.fengz.personal.fengarchitecture.business.model.PreferencesRepository;

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
