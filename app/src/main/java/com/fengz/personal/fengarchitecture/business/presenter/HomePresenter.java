package com.fengz.personal.fengarchitecture.business.presenter;

import com.fengz.personal.fengarchitecture.base.mvp.BasePresenter;
import com.fengz.personal.fengarchitecture.business.contract.HomeContract;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter() {
    }
}
