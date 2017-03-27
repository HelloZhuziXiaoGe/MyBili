package com.atguiu.mybili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguiu.mybili.R;
import com.atguiu.mybili.bean.DirecTvInfo;
import com.atguiu.mybili.player.DanmkuVideoActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class PaintAdapter extends BaseAdapter {

    private final Context context;
    private final List<DirecTvInfo.DataBean.PartitionsBean> datas;


    public PaintAdapter(Context context, List<DirecTvInfo.DataBean.PartitionsBean> partitions) {
        this.context = context;
        this.datas = partitions;
    }


    @Override
    public int getCount() {
        return 4;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_paint_grid_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(datas.get(0).getLives().get(position).getCover().getSrc()).into(viewHolder.ibPicture);
        viewHolder.tvContent.setText(datas.get(0).getLives().get(position).getTitle());
        viewHolder.tvName.setText(datas.get(0).getLives().get(position).getOwner().getName());
        viewHolder.tvWatchingNumber.setText(datas.get(0).getLives().get(position).getOnline() + "");

       viewHolder.itemLiveLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent  = new Intent(context, DanmkuVideoActivity.class);
                intent.putExtra("playerUrl",datas.get(0).getLives().get(position).getPlayurl());
               intent.putExtra("coverimage",datas.get(0).getLives().get(position).getCover().getSrc());
               context.startActivity(intent);



           }
       });

        return convertView;
    }


    class ViewHolder {
        @InjectView(R.id.ib_picture)
        ImageView ibPicture;
        @InjectView(R.id.tv_content)
        TextView tvContent;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_watching_number)
        TextView tvWatchingNumber;
        @InjectView(R.id.item_live_layout)
        CardView itemLiveLayout;


        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
