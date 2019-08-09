package com.fengz.personal.fengarchitecture.business.presenter;


import com.fengz.personal.fengarchitecture.base.mvp.IPresenter;
import com.fengz.personal.fengarchitecture.base.mvp.IView;

import java.util.List;

/**
 * 创建时间：2019/4/25
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：常见的仅支持数据刷新 页面基础Contract
 */
public class BaseRefreshContract {
    public interface View<T> extends IView {
        void showErr(boolean isMul, String msg);

        void setData(List<T> data);
    }

    public interface Presenter<T extends IView> extends IPresenter<T> {
        void getData();
    }
}
