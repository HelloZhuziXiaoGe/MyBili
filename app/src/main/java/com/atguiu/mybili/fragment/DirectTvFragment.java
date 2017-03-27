package com.atguiu.mybili.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.atguiu.mybili.R;
import com.atguiu.mybili.adapter.DirectTvAdapter;
import com.atguiu.mybili.base.BaseFragment;
import com.atguiu.mybili.bean.DirecTvInfo;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */


public class DirectTvFragment extends BaseFragment {


    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;

    private DirecTvInfo.DataBean datas;
    private DirectTvAdapter directTvAdapter;
    private RequestCall build;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_directvfragment, null);
        ButterKnife.inject(this, view);
        return view;
    }


    private void processData(String response) {
        swipeRefreshLayout.setRefreshing(false);

        DirecTvInfo direcTvInfo = new Gson().fromJson(response, DirecTvInfo.class);
        datas = direcTvInfo.getData();
        directTvAdapter = new DirectTvAdapter(context, datas);
        rvHome.setAdapter(directTvAdapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rvHome.setLayoutManager(layoutManager);

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
        build = OkHttpUtils
                .get()
                //联网地址
                .url("http://live.bilibili.com/AppNewIndex/common?_device=android&appkey=\n" +
                        "1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&scale=\n" +
                        "hdpi&ts=1490013188000&sign=92541a11ed62841120e786e637b9db3b")
                .id(100)//http,
                .build();

        build .execute(new StringCallback() {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        build.cancel();

    }


}
