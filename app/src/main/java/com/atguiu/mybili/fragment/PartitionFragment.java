package com.atguiu.mybili.fragment;

import android.view.View;
import android.widget.GridView;

import com.atguiu.mybili.R;
import com.atguiu.mybili.adapter.PartionAdapter;
import com.atguiu.mybili.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class PartitionFragment extends BaseFragment {

    @InjectView(R.id.gv_partion)
    GridView gvPartion;
    private PartionAdapter partionAdapter;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_partion, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        initGridView();
    }

    private void initGridView() {
        partionAdapter = new PartionAdapter(context);

        gvPartion.setAdapter(partionAdapter);

    }


}
