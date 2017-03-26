package com.atguiu.mybili.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguiu.mybili.R;
import com.atguiu.mybili.WebViewActivity;
import com.atguiu.mybili.adapter.RunPlayAdapter;
import com.atguiu.mybili.adapter.RunTenPlayAdapter;
import com.atguiu.mybili.base.BaseFragment;
import com.atguiu.mybili.bean.RunPlayBean;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class RunPlayFragment extends BaseFragment {


    @InjectView(R.id.iv_playitem)
    ImageView ivPlayitem;
    @InjectView(R.id.iv_chainaplay)
    ImageView ivChainaplay;
    @InjectView(R.id.tv_time_blank)
    TextView tvTimeBlank;
    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.iv_logining)
    ImageView ivLogining;
    @InjectView(R.id.ll_more_play)
    LinearLayout llMorePlay;
    @InjectView(R.id.gv_play)
    GridView gvPlay;
    @InjectView(R.id.iv_onebanner)
    ImageView ivOnebanner;
    @InjectView(R.id.tv_onebanner_title)
    TextView tvOnebannerTitle;
    @InjectView(R.id.tv_onebanner_desc)
    TextView tvOnebannerDesc;
    @InjectView(R.id.ll_onebanner)
    LinearLayout llOnebanner;
    @InjectView(R.id.ll_more_play2)
    LinearLayout llMorePlay2;
    @InjectView(R.id.gv_play2)
    GridView gvPlay2;
    @InjectView(R.id.iv_twobanner)
    ImageView ivTwobanner;
    @InjectView(R.id.tv_twobanner_title)
    TextView tvTwobannerTitle;
    @InjectView(R.id.tv_twobanner_desc)
    TextView tvTwobannerDesc;
    @InjectView(R.id.ll_twobanner)
    LinearLayout llTwobanner;
    private RunPlayAdapter runPlayAdapter;
    private List<RunPlayBean.ResultBean.SerializingBean> serializing;

    private RunTenPlayAdapter runTenPlayAdapter;
    private List<RunPlayBean.ResultBean.PreviousBean.ListBean> list;


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_runplay, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initDataFromNet();

        initOneBanner();
    }

    private void initOneBanner() {
        Glide.with(context).load("http://i0.hdslb.com/bfs/bangumi/a093348aea6f7def3ef45a68f6272270e5f0afb1.jpg").into(ivOnebanner);
        ivOnebanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "梦之祭！研究室 21", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("webviewbean","http://www.bilibili.com/blackboard/activity-S1aPZanjx.html");
                intent.putExtra("webviewtitle","2017年4月新番第1波公布！");
                context.startActivity(intent);


            }
        });
        Glide.with(context).load("http://i0.hdslb.com/bfs/bangumi/669ede525abf7e52c2ec0fefdee5eb9fe1a6cffc.jpg").into(ivTwobanner);
        ivTwobanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "听说那一家子笨蛋又出来了！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("webviewbean","http://www.bilibili.com/blackboard/activity-rk0PH-M2e.html");
                intent.putExtra("webviewtitle","【资讯档】2017年第12周");
                context.startActivity(intent);
            }
        });
    }

    private void initDataFromNet() {
        OkHttpUtils
                .get()
                //联网地址
                .url("http://bangumi.bilibili.com/api/app_index_page_v4?build=3940&device=phone&mobi_app=iphone&platform=ios")
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
        RunPlayBean runPlayBean = new Gson().fromJson(response, RunPlayBean.class);
        serializing = runPlayBean.getResult().getSerializing();
        list = runPlayBean.getResult().getPrevious().getList();

        runPlayAdapter = new RunPlayAdapter(context, serializing);
        gvPlay.setAdapter(runPlayAdapter);

        runTenPlayAdapter = new RunTenPlayAdapter(context, list);
        gvPlay2.setAdapter(runTenPlayAdapter);

    }


}
