package com.fengz.personal.fengarchitecture.business1.contract;

import com.fengz.personal.fengarchitecture.base.mvp.IPresenter;
import com.fengz.personal.fengarchitecture.base.mvp.IView;

public class MineContract {
    public interface View extends IView {
    }

    public interface Presenter extends IPresenter<View> {
    }
}
