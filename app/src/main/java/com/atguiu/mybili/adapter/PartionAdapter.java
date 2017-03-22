package com.atguiu.mybili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguiu.mybili.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 进击的程序猿 on 2017/3/22.
 */

public class PartionAdapter extends BaseAdapter {
    private final Context context;
    private int[] images = {R.drawable.ic_head_live, R.drawable.ic_category_t13, R.drawable.ic_category_t1, R.drawable.ic_category_t3, R.drawable.ic_category_t129
            , R.drawable.ic_category_t4, R.drawable.ic_category_t36, R.drawable.ic_category_t160, R.drawable.ic_category_t119, R.drawable.ic_category_t155, R.drawable.ic_category_t165,
            R.drawable.ic_category_t5, R.drawable.ic_category_t23, R.drawable.ic_category_t11, R.drawable.ic_category_game_center};

    private String[] texts = {"直播", "番剧", "动画", "音乐", "舞蹈", "游戏", "科技", "生活", "鬼畜", "时尚", "广告", "娱乐", "电影", "电视剧", "游戏中心"};

    public PartionAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return images.length;
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
    public View getView(final int positon, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder  = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_grid_partion, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivItemPartion.setImageResource(images[positon]);
        viewHolder.tvItemPartion.setText(texts[positon]);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,texts[positon]+"", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_item_partion)
        ImageView ivItemPartion;
        @InjectView(R.id.tv_item_partion)
        TextView tvItemPartion;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
