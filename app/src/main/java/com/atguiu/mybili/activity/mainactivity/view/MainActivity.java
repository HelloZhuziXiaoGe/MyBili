package com.atguiu.mybili.activity.mainactivity.view;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguiu.mybili.DownloadListActivity;
import com.atguiu.mybili.LoginActivity;
import com.atguiu.mybili.PlaySearchActivity;
import com.atguiu.mybili.R;
import com.atguiu.mybili.activity.mainactivity.presenter.MainPresenter;
import com.atguiu.mybili.adapter.MainViewPagerAdapter;
import com.atguiu.mybili.base.BaseFragment;
import com.atguiu.mybili.fragment.view.DirectTvFragment;
import com.atguiu.mybili.fragment.view.FindFragment;
import com.atguiu.mybili.fragment.PartitionFragment;
import com.atguiu.mybili.fragment.view.RecommendFragment;
import com.atguiu.mybili.fragment.RunPlayFragment;
import com.atguiu.mybili.theme.ThemeHelper;
import com.atguiu.mybili.view.CircleImageView;
import com.bilibili.magicasakura.utils.ThemeUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements IMainView{

    private static final int REQUEST_CODE = 7;
    @InjectView(R.id.chuaxianLeft)
    ImageView chuaxianLeft;
    @InjectView(R.id.iv_circleview)
    CircleImageView ivCircleview;
    @InjectView(R.id.iv_red)
    ImageView ivRed;
    @InjectView(R.id.ll_some_image)
    LinearLayout llSomeImage;
    @InjectView(R.id.iv_title_game)
    ImageView ivTitleGame;
    @InjectView(R.id.iv_title_download)
    ImageView ivTitleDownload;
    @InjectView(R.id.iv_title_search)
    ImageView ivTitleSearch;
    @InjectView(R.id.navigation_layout)
    LinearLayout navigationLayout;
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
    @InjectView(R.id.iv_maintitle_back)
    ImageView ivMaintitleBack;
    @InjectView(R.id.iv_maintitle_scan)
    ImageView ivMaintitleScan;
    @InjectView(R.id.edt_maintitle_text)
    EditText edtMaintitleText;
    @InjectView(R.id.iv_maintitle_search)
    ImageView ivMaintitleSearch;
    @InjectView(R.id.item_live_layout)
    CardView itemLiveLayout;
    @InjectView(R.id.btn_maintitle_back)
    Button btnMaintitleBack;
    @InjectView(R.id.ll_maintitle_search)
    LinearLayout llMaintitleSearch;
    @InjectView(R.id.tv_headviewname)
    TextView tvHeadviewname;


    private ArrayList<BaseFragment> fragments;
    private MainViewPagerAdapter mainViewPager;

    //广播接收器
    LocalBroadcastManager broadcastManager;
    IntentFilter intentFilter;
    BroadcastReceiver mReceiver;


    private boolean isswitchhead = true;
    private ImageView headView;
    private TextView switchusername;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mainPresenter = new MainPresenter(this);
        mainPresenter.registerBroadcast();


        mainPresenter.initFragment();
        mainPresenter.initAdapter();
        initListener();
        mainPresenter.switchDayandNightAndtoLoginActivity();

    }



    private void initListener() {
        llSomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mainPresenter.openDrawer();
            }
        });

        ivTitleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             mainPresenter.showllMaintitleSearch();


            }
        });
        ivMaintitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.backllMaintitleSearchAndSoftInput();

            }
        });
        ivMaintitleScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "扫描二维码", Toast.LENGTH_SHORT).show();
            mainPresenter.toCaptureActivity();


            }
        });
        ivMaintitleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             mainPresenter.toPlaySearcheActivity();

            }
        });
        btnMaintitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这个和返回的键调用的方法是一样的
                mainPresenter.backllMaintitleSearchAndSoftInput();
            }
        });
        ivTitleDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.toDownloadListActivity();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            //得到返回结果
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(mReceiver);
        ButterKnife.reset(this);
    }

    @Override
    public void registerBroadcast() {
        broadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
        intentFilter = new IntentFilter();
        intentFilter.addAction("canswitchhead");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ivCircleview.setImageResource(R.drawable.headview);
                tvHeadviewname.setText("蜡笔小新");

                headView.setImageResource(R.drawable.headview);
                switchusername.setText("蜡笔小新");
                isswitchhead = false;

            }
        };
        broadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    @Override
    public void switchDayandNight() {
        View headerView = navigationView.getHeaderView(0);
        ImageView mSwitchMode = (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        mSwitchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] curr = {ThemeHelper.CARD_SAKURA, ThemeHelper.CARD_HOPE, ThemeHelper.CARD_STORM, ThemeHelper.CARD_WOOD, ThemeHelper.CARD_LIGHT,
                        ThemeHelper.CARD_THUNDER, ThemeHelper.CARD_SAND, ThemeHelper.CARD_FIREY};

                Random random = new Random();
                int number = random.nextInt(7);

                switchNightMode(curr[number]);
                /// switchNightMode();
            }
        });

        //给头布局头像设置点击事件
        headView = (ImageView) headerView.findViewById(R.id.user_avatar_view);
        switchusername = (TextView) headerView.findViewById(R.id.user_name);


        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isswitchhead) {
                    //将跳转Intent的写到了这里
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
    private void switchNightMode(int currentTheme) {
        ///设置切换主题
        // recreate();
        if (ThemeHelper.getTheme(MainActivity.this) != currentTheme) {
            ThemeHelper.setTheme(MainActivity.this, currentTheme);
            ThemeUtils.refreshUI(MainActivity.this, new ThemeUtils.ExtraRefreshable() {
                        @Override
                        public void refreshGlobal(Activity activity) {
                            //for global setting, just do once
                            if (Build.VERSION.SDK_INT >= 21) {
                                final MainActivity context = MainActivity.this;
                                ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null, null, ThemeUtils.getThemeAttrColor(context, android.R.attr.colorPrimary));
                                setTaskDescription(taskDescription);
                                getWindow().setStatusBarColor(ThemeUtils.getColorById(context, R.color.theme_color_primary));
                            }
                        }

                        @Override
                        public void refreshSpecificView(View view) {
                            //TODO: will do this for each traversal
                        }
                    }
            );
        }
    }

    @Override
    public void toLoginActivity() {

    }

    @Override
    public void openDrawer() {
        idDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void showMaintitleSearch() {
        llMaintitleSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMaintitleSearch() {
        llMaintitleSearch.setVisibility(View.GONE);

    }

    @Override
    public void hideSoftInputFromWindow() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        imm.hideSoftInputFromWindow(edtMaintitleText.getWindowToken(), 0);
    }

    @Override
    public void toCaptureActivity() {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }



    @Override
    public void toPlaySearcheActivity() {
        String content = edtMaintitleText.getText().toString().trim();

        Intent intent = new Intent(MainActivity.this, PlaySearchActivity.class);
        intent.putExtra("content", content);
        startActivity(intent);
    }

    @Override
    public void toDownloadListActivity() {
        Intent intent = new Intent(MainActivity.this, DownloadListActivity.class);
        startActivity(intent);
    }

    @Override
    public void initAdapter() {
        mainViewPager = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mainViewPager);
        //关联ViewPager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void initFragemnt() {
        fragments = new ArrayList<>();
        fragments.add(new DirectTvFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new RunPlayFragment());
        fragments.add(new PartitionFragment());
        fragments.add(new FindFragment());
    }
}
