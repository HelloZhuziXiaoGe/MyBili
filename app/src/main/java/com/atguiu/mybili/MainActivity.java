package com.atguiu.mybili;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguiu.mybili.adapter.MainViewPagerAdapter;
import com.atguiu.mybili.base.BaseFragment;
import com.atguiu.mybili.fragment.DirectTvFragment;
import com.atguiu.mybili.fragment.FindFragment;
import com.atguiu.mybili.fragment.PartitionFragment;
import com.atguiu.mybili.fragment.RecommendFragment;
import com.atguiu.mybili.fragment.RunPlayFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.toobar)
    Toolbar toobar;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.appbar)
    AppBarLayout appbar;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.cl_main)
    CoordinatorLayout clMain;
    @InjectView(R.id.navigation_view)
    NavigationView navigationView;
    @InjectView(R.id.id_drawer_layout)
    DrawerLayout idDrawerLayout;
    @InjectView(R.id.navigation_layout)
    LinearLayout navigationLayout;
    @InjectView(R.id.chuaxianLeft)
    ImageView chuaxianLeft;

    private ArrayList<BaseFragment> fragments;
    private MainViewPagerAdapter mainViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initFragment();
        initAdapter();
        initListener();

    }

    private void initListener() {
        chuaxianLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void initAdapter() {
        mainViewPager = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mainViewPager);
        //关联ViewPager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new DirectTvFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new RunPlayFragment());
        fragments.add(new PartitionFragment());
        fragments.add(new FindFragment());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
