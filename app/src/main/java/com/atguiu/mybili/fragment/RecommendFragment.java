package com.atguiu.mybili.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.atguiu.mybili.R;
import com.atguiu.mybili.adapter.BiLiRecommendAdapter;
import com.atguiu.mybili.base.BaseFragment;
import com.atguiu.mybili.bean.RecommendBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class RecommendFragment extends BaseFragment {


    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.gv_recommend)
    RecyclerView gvRecommend;
    private BiLiRecommendAdapter biLiRecommendAdapter;
    private List<RecommendBean.DataBean> data;
    private RequestCall build;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_recommend, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromNet();
            }
        });

    }

    private void getDataFromNet() {
        //联网地址
//http,
        build = OkHttpUtils
                .get()
                //联网地址
                .url("http://app.bilibili.com/x/feed/index?appkey=1d8b6e7d45233436&build=501000&idx=1490013261&mobi_app=android&network=wifi&platform=android&pull=true&style=2&ts=1490015599000&sign=af4edc66aef7e443c98c28de2b660aa4")
                .id(100)//http,
                .build();
        build.execute(new StringCallback() {
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

        swipeRefreshLayout.setRefreshing(false);
        RecommendBean recommendBean = new Gson().fromJson(response, RecommendBean.class);
        Log.e("TAG", recommendBean + "");
        data = recommendBean.getData();


        biLiRecommendAdapter = new BiLiRecommendAdapter(context, data);
        gvRecommend.setAdapter(biLiRecommendAdapter);

        gvRecommend.setLayoutManager(new GridLayoutManager(context,2));
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        build.cancel();
        ButterKnife.reset(this);
    }
}
