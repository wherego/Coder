package com.peng_hongru.coder.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 彭鸿儒 on 2017/5/2
 * 邮箱：peng_hongru@163.com
 */

public abstract class BaseAdapter<T extends BaseAdapter.BaseViewHolder> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

    private OnItemClickListener listener;


    @Override
    public final void onBindViewHolder(BaseViewHolder holder, int position) {
        final int index = position;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v,index);
                }
            }
        });

        onBindView((T)holder, position);
    }

    protected abstract void onBindView(T viewHolder, int position);

    // 设置条目点击监听
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //条目点击监听器接口
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }



    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        public final View view;

        public BaseViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }

}

