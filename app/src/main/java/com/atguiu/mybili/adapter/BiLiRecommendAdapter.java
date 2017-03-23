package com.atguiu.mybili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguiu.mybili.R;
import com.atguiu.mybili.bean.RecommendBean;
import com.atguiu.mybili.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 进击的程序猿 on 2017/3/22.
 */

public class BiLiRecommendAdapter extends RecyclerView.Adapter {
    private final Context context;
    private Utils utils = new Utils();
    private final List<RecommendBean.DataBean> datas;
    private RecommedViewHolder viewHolder;


    public BiLiRecommendAdapter(Context context, List<RecommendBean.DataBean> data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommedViewHolder(context, View.inflate(context, R.layout.item_bilirecommend, null));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder = (RecommedViewHolder) holder;
        Glide.with(context).load(datas.get(position).getCover()).into(viewHolder.ivBiliRcommendImage);
        viewHolder.tvPlaynumber.setText(datas.get(position).getPlay()+"");
        viewHolder.tvDanmuku.setText(datas.get(position).getDanmaku()+"");
        viewHolder.tvVideoTime.setText(utils.stringForTime(datas.get(position).getCtime()*1000));
        viewHolder.tvTitle.setText(datas.get(position).getTitle()+"");
        viewHolder.tvTags.setText(datas.get(position).getTname()+"");

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class RecommedViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_biliRcommend_image)
        ImageView ivBiliRcommendImage;
        @InjectView(R.id.tv_playnumber)
        TextView tvPlaynumber;
        @InjectView(R.id.tv_danmuku)
        TextView tvDanmuku;
        @InjectView(R.id.tv_video_time)
        TextView tvVideoTime;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_tags)
        TextView tvTags;
        @InjectView(R.id.image_more)
        ImageView imageMore;

        public RecommedViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }

   /* private Utils utils;
    private final Context context;
    private final List<RecommendBean.DataBean> datas;

    public BiLiRecommendAdapter(Context context, List<RecommendBean.DataBean> data) {
        this.context = context;
        this.datas = data;
        utils = new Utils();
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
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_bilirecommend, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(datas.get(position).getCover()).into(viewHolder.ivBiliRcommendImage);

        viewHolder.tvPlaynumber.setText(datas.get(position).getPlay()+"");
        viewHolder.tvDanmuku.setText(datas.get(position).getDanmaku()+"");
        viewHolder.tvVideoTime.setText(utils.stringForTime(datas.get(position).getCtime()*1000));
        viewHolder.tvTitle.setText(datas.get(position).getTitle()+"");
        viewHolder.tvTags.setText(datas.get(position).getTname()+"");

        return convertView;
    }

     class ViewHolder {
        @InjectView(R.id.iv_biliRcommend_image)
        ImageView ivBiliRcommendImage;
        @InjectView(R.id.tv_playnumber)
        TextView tvPlaynumber;
        @InjectView(R.id.tv_danmuku)
        TextView tvDanmuku;
        @InjectView(R.id.tv_video_time)
        TextView tvVideoTime;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_tags)
        TextView tvTags;
        @InjectView(R.id.image_more)
        ImageView imageMore;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }*/
}
