package com.fengz.personal.fengarchitecture.business.contract;

import com.fengz.personal.fengarchitecture.base.mvp.IPresenter;
import com.fengz.personal.fengarchitecture.base.mvp.IView;
import com.fengz.personal.fengarchitecture.business.model.entity.JokeModule;

import java.util.List;

public class FunnyStoryContract {
    public interface View extends IView {
        void showErr(String msg);
        void setData(List<JokeModule> data);
        void showEmpty();
        void showLoading();

        void showContext();

        void refreshFinish();

        void addData(List<JokeModule> data,int page);
        void showNomore(boolean nomore);
        void loadFinish();
    }

    public interface Presenter extends IPresenter<View> {
        void getData();

        void loadmore(int page);

        void refreshData();
    }
}
