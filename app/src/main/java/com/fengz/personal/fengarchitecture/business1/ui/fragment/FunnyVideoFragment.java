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
import com.fengz.personal.fengarchitecture.business1.contract.FunnyVideoContract;
import com.fengz.personal.fengarchitecture.business1.contract.MineContract;
import com.fengz.personal.fengarchitecture.common.MultipleRelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import yuan.kuo.yu.view.YRecyclerView;

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：搞笑视频 Fragment
 */
public class FunnyVideoFragment extends BaseFragment implements FunnyVideoContract.View {

    @Inject
    @APresenter
    FunnyVideoContract.Presenter mPresenter;

    @BindView(R.id.recycler_funny_video_frg)
    YRecyclerView mRecycler;
    @BindView(R.id.multiple_funny_video_frg)
    MultipleRelativeLayout mMultiple;

    public static FunnyVideoFragment newInstance() {
        Bundle args = new Bundle();
        FunnyVideoFragment fragment = new FunnyVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_funny_video, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
