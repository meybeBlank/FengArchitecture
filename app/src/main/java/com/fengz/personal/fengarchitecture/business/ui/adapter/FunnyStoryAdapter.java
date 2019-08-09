package com.fengz.personal.fengarchitecture.business.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fengz.personal.fengarchitecture.R;
import com.fengz.personal.fengarchitecture.business.model.entity.JokeModule;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：搞下段子 adapter
 */
public class FunnyStoryAdapter extends RecyclerView.Adapter {

    private List<JokeModule> mData;

    public FunnyStoryAdapter(List<JokeModule> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_funny_story, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setContent(mData.get(i).getText());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_content_story_item)
        TextView mTvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setContent(String text) {
            mTvContent.setText(text);
        }
    }
}
