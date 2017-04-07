package com.atguiu.mybili.fragment.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.atguiu.mybili.R;
import com.atguiu.mybili.adapter.DirectTvAdapter;
import com.atguiu.mybili.base.BaseFragment;
import com.atguiu.mybili.bean.DirecTvInfo;
import com.atguiu.mybili.fragment.presenter.DirectTvPresenter;
import com.atguiu.mybili.utils.Contans;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */


public class DirectTvFragment extends BaseFragment implements IDirectTvFragmentView{


    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;

    private DirecTvInfo.DataBean datas;
    private DirectTvAdapter directTvAdapter;


    private DirectTvPresenter directTvPresenter;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_directvfragment, null);
        ButterKnife.inject(this, view);
        directTvPresenter = new DirectTvPresenter(this);
        return view;
    }




    @Override
    public void initData() {
        super.initData();
      //  getDataFromNet();
        directTvPresenter.getDataFromNet();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                directTvPresenter.getDataFromNet();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
       // build.cancel();

    }
    @Override
    public String  getUrl() {

        return Contans.DIRECTTV_URL;

    }

    @Override
    public void finishSwipeRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void initAdapter(DirecTvInfo direcTvInfo) {
        directTvAdapter = new DirectTvAdapter(context, direcTvInfo.getData());
        rvHome.setAdapter(directTvAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rvHome.setLayoutManager(layoutManager);
    }

    @Override
    public void showErrorMessage(Exception ex) {
        Log.e("TAG", "Exception"+ex.getMessage());
    }
}
