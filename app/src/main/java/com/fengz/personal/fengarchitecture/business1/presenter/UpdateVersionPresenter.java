package com.fengz.personal.fengarchitecture.business1.presenter;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.fengz.personal.fengarchitecture.base.mvp.BasePresenter;
import com.fengz.personal.fengarchitecture.business1.contract.UpdateVersionContract;
import com.fengz.personal.fengarchitecture.business1.model.api.B1Repository;
import com.fengz.personal.fengarchitecture.business1.model.entity.CheckVersionBean;
import com.fengz.personal.fengarchitecture.http.BaseObserver.BaseObserver;
import com.fengz.personal.fengarchitecture.utils.UpdateApkManager;

import javax.inject.Inject;

public class UpdateVersionPresenter extends BasePresenter<UpdateVersionContract.View> implements UpdateVersionContract.Presenter{

    B1Repository mB1Repository;
    private UpdateApkManager mUpdateApkManager;

    @Inject
    public UpdateVersionPresenter(B1Repository mB1Repository) {
        this.mB1Repository = mB1Repository;
    }

    @Override
    public void checkUpdate() {
        mB1Repository.checkUpload()
                .compose(getView().getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<CheckVersionBean>() {
                    @Override
                    public void onErrors(String e) {
                        // do nothing
                    }

                    @Override
                    public void onResponse(CheckVersionBean response) {
                        if (response.isCanUp()) {
                            getView().updateApk(response);
                        }
                    }
                });
    }

    @Override
    public void downloadAndInstall(CheckVersionBean bean) {
        mUpdateApkManager = new UpdateApkManager((Activity) getView());
        mUpdateApkManager.setUpdateListener((currentByte, totalByte) ->
                getView().refreshProgress(currentByte, totalByte));
        mUpdateApkManager.downloadApk(bean);
    }

    @Override
    public void cancelDownload() {
        if (mUpdateApkManager != null) {
            mUpdateApkManager.cancel();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void unregister() {
        if (mUpdateApkManager != null) {
            mUpdateApkManager.ondestroy();
        }
    }
}
