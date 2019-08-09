package com.fengz.personal.fengarchitecture.business.presenter;

import com.fengz.personal.fengarchitecture.base.mvp.BasePresenter;
import com.fengz.personal.fengarchitecture.business.contract.FunnyVideoContract;
import com.fengz.personal.fengarchitecture.business.contract.MineContract;
import com.fengz.personal.fengarchitecture.business.model.api.B1Repository;

import javax.inject.Inject;

public class FunnyVideoPresenter extends BasePresenter<FunnyVideoContract.View> implements FunnyVideoContract.Presenter {

    B1Repository mRepository;

    @Inject
    public FunnyVideoPresenter(B1Repository mRepository) {
        this.mRepository = mRepository;
    }
}
