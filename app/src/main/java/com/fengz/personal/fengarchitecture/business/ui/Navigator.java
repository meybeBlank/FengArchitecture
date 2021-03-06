package com.fengz.personal.fengarchitecture.business.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.fengz.personal.fengarchitecture.business.ui.activity.LoginActivity;
import com.fengz.personal.fengarchitecture.business.ui.activity.MainActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 创建时间：2019/1/21
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：页面跳转导航
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigator2LoginAct(@NonNull Context context) {
        Intent intent = LoginActivity.getCallingIntent(context,false);
        context.startActivity(intent);
    }

    public void navigator2LoginActBack(@NonNull Context context) {
        Intent intent = LoginActivity.getCallingIntent(context,true);
        context.startActivity(intent);
    }

    public void navigator2MainAct(@NonNull Context context) {
        Intent intent = MainActivity.getCallingIntent(context);
        context.startActivity(intent);
    }

}
