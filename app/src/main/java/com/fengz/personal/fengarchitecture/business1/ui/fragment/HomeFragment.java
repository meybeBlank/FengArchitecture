package com.fengz.personal.fengarchitecture.business1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengz.personal.fengarchitecture.R;
import com.fengz.personal.fengarchitecture.base.mvp.APresenter;
import com.fengz.personal.fengarchitecture.base.mvp.BaseFragment;
import com.fengz.personal.fengarchitecture.business1.contract.HomeContract;

import javax.inject.Inject;

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：首页Fragment
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    @Inject
    @APresenter
    HomeContract.Presenter mPresenter;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
