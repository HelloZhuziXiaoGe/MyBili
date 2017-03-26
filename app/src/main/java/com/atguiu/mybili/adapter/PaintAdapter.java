package com.atguiu.mybili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguiu.mybili.R;
import com.atguiu.mybili.bean.DirecTvInfo;
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

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(datas.get(0).getLives().get(position).getCover().getSrc()).into(viewHolder.ibPicture);
        viewHolder.tvContent.setText(datas.get(0).getLives().get(position).getTitle());
        viewHolder.tvName.setText(datas.get(0).getLives().get(position).getOwner().getName());
        viewHolder.tvWatchingNumber.setText(datas.get(0).getLives().get(position).getOnline()+"");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "1111", Toast.LENGTH_SHORT).show();


            }
        });


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.ib_picture)
        ImageView ibPicture;
        @InjectView(R.id.tv_content)
        TextView tvContent;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_watching_number)
        TextView tvWatchingNumber;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);


        }
    }
}
