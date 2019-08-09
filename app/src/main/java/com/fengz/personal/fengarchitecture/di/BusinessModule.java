package com.fengz.personal.fengarchitecture.di;

import com.fengz.personal.fengarchitecture.business.contract.FunnyStoryContract;
import com.fengz.personal.fengarchitecture.business.contract.FunnyVideoContract;
import com.fengz.personal.fengarchitecture.business.contract.HomeContract;
import com.fengz.personal.fengarchitecture.business.contract.LoginContract;
import com.fengz.personal.fengarchitecture.business.contract.MainContract;
import com.fengz.personal.fengarchitecture.business.contract.MineContract;
import com.fengz.personal.fengarchitecture.business.contract.UpdateVersionContract;
import com.fengz.personal.fengarchitecture.business.contract.WelcomeContract;
import com.fengz.personal.fengarchitecture.business.presenter.FunnyStoryPresenter;
import com.fengz.personal.fengarchitecture.business.presenter.FunnyVideoPresenter;
import com.fengz.personal.fengarchitecture.business.presenter.HomePresenter;
import com.fengz.personal.fengarchitecture.business.presenter.LoginPresenter;
import com.fengz.personal.fengarchitecture.business.presenter.MinePresenter;
import com.fengz.personal.fengarchitecture.business.presenter.UpdateVersionPresenter;
import com.fengz.personal.fengarchitecture.business.presenter.WelcomePresenter;
import com.fengz.personal.fengarchitecture.business.ui.activity.LoginActivity;
import com.fengz.personal.fengarchitecture.business.ui.activity.MainActivity;
import com.fengz.personal.fengarchitecture.business.ui.activity.WelcomeActivity;
import com.fengz.personal.fengarchitecture.business.ui.fragment.FunnyStoryFragment;
import com.fengz.personal.fengarchitecture.business.ui.fragment.FunnyVideoFragment;
import com.fengz.personal.fengarchitecture.business.ui.fragment.HomeFragment;
import com.fengz.personal.fengarchitecture.business.ui.fragment.MineFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 修改时间：2019/8/9
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：业务module 可分模块创建module
 */
@Module
public abstract class BusinessModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract LoginActivity loginActivityInject();

    @Binds
    abstract LoginContract.Presenter loginPresenterInject(LoginPresenter presenter);

    @Binds
    abstract UpdateVersionContract.Presenter UpdateVersionPresenterInject(UpdateVersionPresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract WelcomeActivity welcomeActivityInject();

    @Binds
    abstract WelcomeContract.Presenter welcomePresenterInject(WelcomePresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivityInject();

    @ActivityScope
    @ContributesAndroidInjector
    abstract HomeFragment homeFragmentInject();

    @Binds
    abstract HomeContract.Presenter HomePresenterInject(HomePresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract MineFragment mineFragmentInject();

    @Binds
    abstract MineContract.Presenter minePresenterInject(MinePresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract FunnyVideoFragment funnyVideoFragmentInject();

    @Binds
    abstract FunnyVideoContract.Presenter funnyVideoPresenterInject(FunnyVideoPresenter presenter);

    @ActivityScope
    @ContributesAndroidInjector
    abstract FunnyStoryFragment funnyStoryFragmentInject();

    @Binds
    abstract FunnyStoryContract.Presenter funnyStoryPresenterInject(FunnyStoryPresenter presenter);
}