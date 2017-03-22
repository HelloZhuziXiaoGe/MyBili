package com.atguiu.mybili.fragment;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguiu.mybili.R;
import com.atguiu.mybili.base.BaseFragment;
import com.atguiu.mybili.bean.TagBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class FindFragment extends BaseFragment {


    @InjectView(R.id.search_scan)
    ImageView searchScan;
    @InjectView(R.id.search_edit)
    TextView searchEdit;
    @InjectView(R.id.search_img)
    ImageView searchImg;
    @InjectView(R.id.card_view)
    CardView cardView;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.tags_layout)
    TagFlowLayout tagsLayout;
    @InjectView(R.id.hide_tags_layout)
    TagFlowLayout hideTagsLayout;
    @InjectView(R.id.hide_scroll_view)
    NestedScrollView hideScrollView;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.more_layout)
    LinearLayout moreLayout;
    @InjectView(R.id.ic_quanzi)
    ImageView icQuanzi;
    @InjectView(R.id.ic_quanzi_layout)
    RelativeLayout icQuanziLayout;
    @InjectView(R.id.ic_topic)
    ImageView icTopic;
    @InjectView(R.id.topic_center_layout)
    RelativeLayout topicCenterLayout;
    @InjectView(R.id.ic_activity)
    ImageView icActivity;
    @InjectView(R.id.activity_center_layout)
    RelativeLayout activityCenterLayout;
    @InjectView(R.id.ic_original)
    ImageView icOriginal;
    @InjectView(R.id.layout_original)
    RelativeLayout layoutOriginal;
    @InjectView(R.id.ic_all_rank)
    ImageView icAllRank;
    @InjectView(R.id.layout_all_rank)
    RelativeLayout layoutAllRank;
    @InjectView(R.id.ic_game)
    ImageView icGame;
    @InjectView(R.id.layout_game_center)
    RelativeLayout layoutGameCenter;
    @InjectView(R.id.ic_shop)
    ImageView icShop;
    @InjectView(R.id.layout_shop)
    RelativeLayout layoutShop;
    private List<TagBean.DataBean.ListBean> list;
    private boolean isShowMore = true;

    @Override
    public View initView() {


        View view = View.inflate(context, R.layout.fragment_find, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getTags();
        initListener();

    }

    private void initListener() {
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowMore) {
                    isShowMore = false;
                    hideScrollView.setVisibility(View.VISIBLE);
                    tvMore.setText("收起");
                    tagsLayout.setVisibility(View.GONE);
                    Drawable upDrawable = getResources().getDrawable(R.drawable.ic_arrow_up_gray_round);
                    upDrawable.setBounds(0, 0, upDrawable.getMinimumWidth(), upDrawable.getMinimumHeight());
                    tvMore.setCompoundDrawables(upDrawable, null, null, null);
                } else {
                    isShowMore = true;
                    hideScrollView.setVisibility(View.GONE);
                    tvMore.setText("查看更多");
                    tagsLayout.setVisibility(View.VISIBLE);
                    Drawable downDrawable = getResources().getDrawable(R.drawable.ic_arrow_down_gray_round);
                    downDrawable.setBounds(0, 0, downDrawable.getMinimumWidth(), downDrawable.getMinimumHeight());
                    tvMore.setCompoundDrawables(downDrawable, null, null, null);
                }
            }
        });
    }

    private void getTags() {
        OkHttpUtils
                .get()
                //联网地址
                .url("http://app.bilibili.com/x/v2/search/hot?appkey=1d8b6e7d45233436&build=501000&lim" +
                        "it=50&mobi_app=android&platform=android&ts=1490014710000&sign=e5ddf94fa9a0d6876cb85756c37c4adc")
                .id(100)//http,
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "联网成功==");
                        processData(response);

                    }
                });
    }

    private void processData(String response) {
        TagBean tagBean = new Gson().fromJson(response, TagBean.class);
        list = tagBean.getData().getList();

        List<TagBean.DataBean.ListBean> hotTags = list.subList(0, 8);
        tagsLayout.setAdapter(new TagAdapter<TagBean.DataBean.ListBean>(hotTags) {
            @Override
            public View getView(FlowLayout parent, int position, final TagBean.DataBean.ListBean hotTags) {

                TextView mTags = (TextView) LayoutInflater.from(getActivity())
                        .inflate(R.layout.layout_tags_item, parent, false);
                mTags.setText(hotTags.getKeyword());

                mTags.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, hotTags.getKeyword() + "", Toast.LENGTH_SHORT).show();
                    }
                });

                return mTags;
            }
        });
        hideTagsLayout.setAdapter(new TagAdapter<TagBean.DataBean.ListBean>(list) {
            @Override
            public View getView(FlowLayout parent, int position, final TagBean.DataBean.ListBean list) {

                TextView mTags = (TextView) LayoutInflater.from(getActivity())
                        .inflate(R.layout.layout_tags_item, parent, false);
                mTags.setText(list.getKeyword());

                mTags.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, list.getKeyword() + "", Toast.LENGTH_SHORT).show();
                    }
                });

                return mTags;
            }
        });




    }



}
