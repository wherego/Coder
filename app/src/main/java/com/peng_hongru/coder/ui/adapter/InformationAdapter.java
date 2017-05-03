package com.peng_hongru.coder.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peng_hongru.coder.R;
import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.utils.ImageLoader;
import com.peng_hongru.coder.utils.QiNiuUtils;

import java.util.List;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */
public class InformationAdapter extends BaseAdapter<InformationAdapter.ViewHolder> {
    private List<Information> datas = null;
    private Context context;

    public InformationAdapter(List<Information> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_information,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    protected void onBindView(ViewHolder viewHolder, int position) {
        Information information = datas.get(position);
        viewHolder.desc.setText(information.getDesc());
        viewHolder.who.setText(information.getWho());
        viewHolder.createAt.setText(information.getCreatedAt().substring(0,10));
        viewHolder.source.setText(information.getSource());
        viewHolder.type.setText(information.getType());
        List<String> images = information.getImages();
        if (images != null && images.size() > 0) {
            viewHolder.face.setVisibility(View.VISIBLE);
            ImageLoader.loadImage(
                    context,
                    QiNiuUtils.setUrl(images.get(0))
                            .size(100,100)
                            .slim()
                            .commit(),
                    viewHolder.face
            );
        } else {
            viewHolder.face.setVisibility(View.GONE);
        }
    }


    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }



    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends BaseAdapter.BaseViewHolder {

        public final ImageView face;
        public final TextView who;
        public final TextView createAt;
        public final TextView source;
        public final TextView desc;
        public final TextView type;

        public ViewHolder(View view){
            super(view);
            face = (ImageView) view.findViewById(R.id.face);
            desc = (TextView) view.findViewById(R.id.desc);
            who = (TextView) view.findViewById(R.id.who);
            createAt = (TextView) view.findViewById(R.id.createdAt);
            type = (TextView) view.findViewById(R.id.type);
            source = (TextView) view.findViewById(R.id.source);
        }
    }
}
