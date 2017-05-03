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
public class GirlsAdapter extends BaseAdapter<GirlsAdapter.ViewHolder> {
    private List<Information> datas = null;
    private Context context;

    public GirlsAdapter(List<Information> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_girl,viewGroup,false);
        return new ViewHolder(view);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //将数据与界面进行绑定的操作
    @Override
    protected void onBindView(ViewHolder viewHolder, int position) {
        Information information = datas.get(position);
        viewHolder.createAt.setText(information.getCreatedAt().substring(0,10));
        String url = information.getUrl();
        if (url != null && url.length() > 0) {
            ImageLoader.loadImage(
                    context,
                    QiNiuUtils.setUrl(url)
                            .slim()
                            .interlace(true)
                            .size(160,240)
                            .commit(),
                    viewHolder.face
            );
        }
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends BaseAdapter.BaseViewHolder {

        public ImageView face;
        public TextView createAt;

        public ViewHolder(View view){
            super(view);
            face = (ImageView) view.findViewById(R.id.face);
            createAt = (TextView) view.findViewById(R.id.createdAt);
        }
    }
}
