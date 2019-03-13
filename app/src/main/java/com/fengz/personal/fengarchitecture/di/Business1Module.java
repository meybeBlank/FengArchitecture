package com.fengz.personal.fengarchitecture.di;

import com.fengz.personal.fengarchitecture.business1.contract.FunnyStoryContract;
import com.fengz.personal.fengarchitecture.business1.contract.FunnyVideoContract;
import com.fengz.personal.fengarchitecture.business1.contract.HomeContract;
import com.fengz.personal.fengarchitecture.business1.contract.LoginContract;
import com.fengz.personal.fengarchitecture.business1.contract.MainContract;
import com.fengz.personal.fengarchitecture.business1.contract.MineContract;
import com.fengz.personal.fengarchitecture.business1.contract.UpdateVersionContract;
import com.fengz.personal.fengarchitecture.business1.contract.WelcomeContract;
import com.fengz.personal.fengarchitecture.business1.presenter.FunnyStoryPresenter;
import com.fengz.personal.fengarchitecture.business1.presenter.FunnyVideoPresenter;
import com.fengz.personal.fengarchitecture.business1.presenter.HomePresenter;
import com.fengz.personal.fengarchitecture.business1.presenter.LoginPresenter;
import com.fengz.personal.fengarchitecture.business1.presenter.MinePresenter;
import com.fengz.personal.fengarchitecture.business1.presenter.UpdateVersionPresenter;
import com.fengz.personal.fengarchitecture.business1.presenter.WelcomePresenter;
import com.fengz.personal.fengarchitecture.business1.ui.activity.LoginActivity;
import com.fengz.personal.fengarchitecture.business1.ui.activity.MainActivity;
import com.fengz.personal.fengarchitecture.business1.ui.activity.WelcomeActivity;
import com.fengz.personal.fengarchitecture.business1.ui.fragment.FunnyStoryFragment;
import com.fengz.personal.fengarchitecture.business1.ui.fragment.FunnyVideoFragment;
import com.fengz.personal.fengarchitecture.business1.ui.fragment.HomeFragment;
import com.fengz.personal.fengarchitecture.business1.ui.fragment.MineFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class Business1Module {

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