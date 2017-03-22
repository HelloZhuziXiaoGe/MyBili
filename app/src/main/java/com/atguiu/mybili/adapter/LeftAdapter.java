package com.atguiu.mybili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguiu.mybili.R;
import com.atguiu.mybili.bean.ContentModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 进击的程序猿 on 2017/3/20.
 */

public class LeftAdapter extends BaseAdapter {
    private final Context context;
    private final List<ContentModel> datas;

    public LeftAdapter(Context context, List<ContentModel> list) {
        this.context = context;
        this.datas = list;
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.left_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.leftItemImage.setImageResource(datas.get(position).getImage());
        viewHolder.leftItemText.setText(datas.get(position).getContent());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Position=="+position, Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.left_item_image)
        ImageView leftItemImage;
        @InjectView(R.id.left_item_text)
        TextView leftItemText;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);

        }
    }
}
