package com.fengz.personal.fengarchitecture.business1.contract;

import com.fengz.personal.fengarchitecture.base.mvp.IPresenter;
import com.fengz.personal.fengarchitecture.base.mvp.IView;
import com.fengz.personal.fengarchitecture.business1.model.entity.JokeModule;

import java.util.List;

public class FunnyVideoContract {
    public interface View extends IView {
    }

    public interface Presenter extends IPresenter<View> {
    }
}
