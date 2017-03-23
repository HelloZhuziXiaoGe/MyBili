package com.atguiu.mybili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguiu.mybili.R;
import com.atguiu.mybili.bean.GuiguInfo;
import com.atguiu.mybili.utils.Contans;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class RecommendAdapter extends BaseAdapter {
    private final Context context;
    private final List<GuiguInfo.ResultBean.RecommendInfoBean> datas;

    public RecommendAdapter(Context context, List<GuiguInfo.ResultBean.RecommendInfoBean> recommend_info) {
        this.context = context;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.item_recommend_grid_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(datas.get(position).getName());
        viewHolder.tvPrice.setText("￥"+datas.get(position).getCover_price());
        Glide.with(context).load(Contans.BASE_URL_IMAGE+datas.get(position).getFigure()).into(viewHolder.ivRecommend);


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_recommend)
        ImageView ivRecommend;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
