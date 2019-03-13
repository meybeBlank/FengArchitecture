package com.fengz.personal.fengarchitecture.business1.contract;

import com.fengz.personal.fengarchitecture.base.mvp.IPresenter;
import com.fengz.personal.fengarchitecture.base.mvp.IView;
import com.fengz.personal.fengarchitecture.business1.model.entity.CheckVersionBean;

/**
 * 应用内升级
 */
public class UpdateVersionContract {
    public interface View extends IView {

        void updateApk(CheckVersionBean bean);

        void refreshProgress(int current, int total);

    }

    public interface Presenter extends IPresenter<View> {

        void checkUpdate();

        void downloadAndInstall(CheckVersionBean bean);

        void cancelDownload();
    }
}
