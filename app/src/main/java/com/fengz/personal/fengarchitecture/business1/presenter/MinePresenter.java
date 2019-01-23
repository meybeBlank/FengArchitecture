package com.fengz.personal.fengarchitecture.business1.presenter;

import com.fengz.personal.fengarchitecture.base.mvp.BasePresenter;
import com.fengz.personal.fengarchitecture.business1.contract.HomeContract;
import com.fengz.personal.fengarchitecture.business1.contract.MineContract;

import javax.inject.Inject;

public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter {

    @Inject
    public MinePresenter() {
    }
}
