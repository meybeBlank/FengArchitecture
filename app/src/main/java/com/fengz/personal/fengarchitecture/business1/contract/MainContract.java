package com.fengz.personal.fengarchitecture.business1.contract;

import com.fengz.personal.fengarchitecture.base.mvp.IPresenter;
import com.fengz.personal.fengarchitecture.base.mvp.IView;

public class MainContract {

    public interface View extends IView {
        void switchFragment(int position);
    }

    public interface Presenter extends IPresenter<View> {
    }
}
