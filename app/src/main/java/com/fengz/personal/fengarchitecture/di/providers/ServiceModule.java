package com.fengz.personal.fengarchitecture.di.providers;

import com.fengz.personal.fengarchitecture.business.model.api.UserService;
import com.fengz.personal.fengarchitecture.http.ServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 创建时间：2019/1/3
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：retrofit Service Module
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    public UserService provideUserService() {
        return ServiceFactory.getInstance().getService(UserService.class);
    }
}
