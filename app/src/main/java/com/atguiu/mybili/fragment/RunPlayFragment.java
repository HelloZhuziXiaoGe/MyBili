package com.atguiu.mybili.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguiu.mybili.base.BaseFragment;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class RunPlayFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {

        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("追番Fragment");
    }
}
