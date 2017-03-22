package com.atguiu.mybili.fragment;

import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.atguiu.mybili.R;
import com.atguiu.mybili.adapter.BiLiRecommendAdapter;
import com.atguiu.mybili.base.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class RecommendFragment extends BaseFragment {

    @InjectView(R.id.gv_recommend)
    GridView gvRecommend;
    private BiLiRecommendAdapter biLiRecommendAdapter;
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
        initAdapter();

    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                //联网地址
                .url("http://app.bilibili.com/x/feed/index?appkey=1d8b6e7d45233436&build=501000&idx=1490013261&mobi_app=android&network=wifi&platform=android&pull=true&style=2&ts=1490015599000&sign=af4edc66aef7e443c98c28de2b660aa4")
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
        
    }

    private void initAdapter() {
        biLiRecommendAdapter = new BiLiRecommendAdapter();

    }
}
