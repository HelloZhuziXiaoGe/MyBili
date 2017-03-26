package com.atguiu.mybili.fragment.searchfragment;

import android.view.View;
import android.widget.TextView;

import com.atguiu.mybili.base.BaseFragment;

/**
 * Created by 进击的程序猿 on 2017/3/24.
 */

public class UpmasterFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setText("Up主Fragment");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
