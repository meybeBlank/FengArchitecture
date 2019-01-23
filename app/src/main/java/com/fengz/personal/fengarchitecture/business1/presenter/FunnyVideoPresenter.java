package com.fengz.personal.fengarchitecture.business1.presenter;

import com.fengz.personal.fengarchitecture.base.mvp.BasePresenter;
import com.fengz.personal.fengarchitecture.business1.contract.FunnyVideoContract;
import com.fengz.personal.fengarchitecture.business1.contract.MineContract;
import com.fengz.personal.fengarchitecture.business1.model.api.B1Repository;

import javax.inject.Inject;

public class FunnyVideoPresenter extends BasePresenter<FunnyVideoContract.View> implements FunnyVideoContract.Presenter {

    B1Repository mRepository;

    @Inject
    public FunnyVideoPresenter(B1Repository mRepository) {
        this.mRepository = mRepository;
    }
}
