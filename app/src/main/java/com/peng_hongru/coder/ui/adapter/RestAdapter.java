package com.peng_hongru.coder.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peng_hongru.coder.R;
import com.peng_hongru.coder.module.net.bean.Information;

import java.util.List;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */
public class RestAdapter extends BaseAdapter<RestAdapter.ViewHolder> {
    private List<Information> datas = null;
    private Context context;

    public RestAdapter(List<Information> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_rest,viewGroup,false);
        return new ViewHolder(view);
    }


    //转换来源内容
    private String formatSource(String url) {
        if (url.contains("bilibili")) {
            url = "哔哩哔哩";
        }
        else if (url.contains("weibo")) {
            url = "微博";
        }
        else if (url.contains("miaopai")) {
            url = "秒拍";
        }
        else if (url.contains("douban")) {
            url = "豆瓣";
        }
        else if (url.contains("youku")) {
            url = "优酷";
        }
        else if (url.contains("qq")) {
            url = "QQ";
        } else if (url.contains("vmovier")) {
            url = "V电影";
        } else {
            String[] split = url.split("/");
            url = split[2]; // 获得主域名
        }
        return url;
    }

    //将数据与界面进行绑定的操作
    @Override
    protected void onBindView(ViewHolder viewHolder, int position) {
        Information information = datas.get(position);
        viewHolder.desc.setText(information.getDesc());
        viewHolder.who.setText(information.getWho());
        viewHolder.createAt.setText(information.getCreatedAt().substring(0,10));
        String url = information.getUrl();
        viewHolder.source.setText(formatSource(url));
        viewHolder.type.setText(information.getType());
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends BaseAdapter.BaseViewHolder {

        public final TextView who;
        public final TextView createAt;
        public final TextView source;
        public final TextView desc;
        public final TextView type;

        public ViewHolder(View view){
            super(view);
            desc = (TextView) view.findViewById(R.id.desc);
            who = (TextView) view.findViewById(R.id.who);
            createAt = (TextView) view.findViewById(R.id.createdAt);
            type = (TextView) view.findViewById(R.id.type);
            source = (TextView) view.findViewById(R.id.source);
        }
    }
}
