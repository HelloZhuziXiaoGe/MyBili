package com.atguiu.mybili.fragment.view;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.atguiu.mybili.R;
import com.atguiu.mybili.adapter.BiLiRecommendAdapter;
import com.atguiu.mybili.base.BaseFragment;
import com.atguiu.mybili.bean.RecommendBean;
import com.atguiu.mybili.fragment.presenter.RecommendPresenter;
import com.atguiu.mybili.utils.Contans;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class RecommendFragment extends BaseFragment implements IRecommendFragmentView{


    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.gv_recommend)
    RecyclerView gvRecommend;
    private BiLiRecommendAdapter biLiRecommendAdapter;
    private RecommendPresenter recommendPresenter;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_recommend, null);
        ButterKnife.inject(this, view);
        recommendPresenter =  new RecommendPresenter(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
       recommendPresenter.getDataFromNet();
        initListener();


    }

    private void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recommendPresenter.getDataFromNet();
            }
        });

        gvRecommend.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == biLiRecommendAdapter.getItemCount()) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
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
                                            RecommendBean recommendBean = new Gson().fromJson(response, RecommendBean.class);
                                            List<RecommendBean.DataBean> data2 = recommendBean.getData();

                                            List<RecommendBean.DataBean> footerDatas = new ArrayList<RecommendBean.DataBean>();
                                            for (int i =0;i< data2.size();i++){
                                                footerDatas.add(data2.get(i));
                                            }

                                            biLiRecommendAdapter.AddFooterItem(footerDatas);
                                            Toast.makeText(context, "上拉加载更多", Toast.LENGTH_SHORT).show();


                                        }
                                    });

                        }
                    });
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem=layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public String getUrl() {
        return Contans.RECOMMEND_URL;
    }

    @Override
    public void finishSwipeRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void initAdapter(RecommendBean recommendBean) {


        biLiRecommendAdapter = new BiLiRecommendAdapter(context, recommendBean.getData());
        gvRecommend.setAdapter(biLiRecommendAdapter);

        gvRecommend.setLayoutManager(new GridLayoutManager(context,2));
    }

    @Override
    public void showErrorMessage(Exception ex) {
        Log.e("TAG", "Exception+" + ex.getMessage());
    }
}
