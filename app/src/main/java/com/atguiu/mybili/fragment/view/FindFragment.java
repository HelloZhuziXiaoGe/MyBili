package com.atguiu.mybili.fragment.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguiu.mybili.OriginalRanklistActivity;
import com.atguiu.mybili.PlaySearchActivity;
import com.atguiu.mybili.R;
import com.atguiu.mybili.ShoppingActivity;
import com.atguiu.mybili.TopicCenterActivity;
import com.atguiu.mybili.activity.mainactivity.view.MainActivity;
import com.atguiu.mybili.base.BaseFragment;
import com.atguiu.mybili.bean.TagBean;
import com.atguiu.mybili.fragment.presenter.FindPresenter;
import com.atguiu.mybili.utils.Contans;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class FindFragment extends BaseFragment implements IFindFragmentView {

    private static final int REQUEST_CODE2 = 8;
    @InjectView(R.id.search_scan)
    ImageView searchScan;
    @InjectView(R.id.search_edit)
    TextView searchEdit;
    @InjectView(R.id.search_img)
    ImageView searchImg;
    @InjectView(R.id.card_view)
    CardView cardView;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.tags_layout)
    TagFlowLayout tagsLayout;
    @InjectView(R.id.hide_tags_layout)
    TagFlowLayout hideTagsLayout;
    @InjectView(R.id.hide_scroll_view)
    NestedScrollView hideScrollView;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.more_layout)
    LinearLayout moreLayout;
    @InjectView(R.id.ic_quanzi)
    ImageView icQuanzi;
    @InjectView(R.id.ic_quanzi_layout)
    RelativeLayout icQuanziLayout;
    @InjectView(R.id.ic_topic)
    ImageView icTopic;
    @InjectView(R.id.topic_center_layout)
    RelativeLayout topicCenterLayout;
    @InjectView(R.id.ic_activity)
    ImageView icActivity;
    @InjectView(R.id.activity_center_layout)
    RelativeLayout activityCenterLayout;
    @InjectView(R.id.ic_original)
    ImageView icOriginal;
    @InjectView(R.id.layout_original)
    RelativeLayout layoutOriginal;
    @InjectView(R.id.ic_all_rank)
    ImageView icAllRank;
    @InjectView(R.id.layout_all_rank)
    RelativeLayout layoutAllRank;
    @InjectView(R.id.ic_game)
    ImageView icGame;
    @InjectView(R.id.layout_game_center)
    RelativeLayout layoutGameCenter;
    @InjectView(R.id.ic_shop)
    ImageView icShop;
    @InjectView(R.id.layout_shop)
    RelativeLayout layoutShop;

    private boolean isShowMore = true;
    private MainActivity mainactivity;


    private FindPresenter findPresenter;

    private LinearLayout llMaintitleSearch;
    private ImageView ivMaintitleBack;
    private EditText edtMaintitleText;
    private ImageView ivMaintitleSearch;
    private Button btnMaintitleBack;
    private ImageView ivMaintitleScan;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_find, null);
        ButterKnife.inject(this, view);
        findPresenter = new FindPresenter(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        // getTags();
        findPresenter.getTagFromNet();

        initListener();

    }

    private void initListener() {
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowMore) {
                    isShowMore = false;
                    findPresenter.tvMoreHideClick();
                } else {
                    isShowMore = true;
                    findPresenter.tvMoreShowClick();
                }
            }
        });
        //得到Activity的实例
        findPresenter.getMainActivity();

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPresenter.showllMaintitleSearch();
            }
        });

        ivMaintitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPresenter.hidellMaintitleSearch();
                findPresenter.hideSoftInputFromWindow();
            }
        });
        ivMaintitleScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPresenter.toCaptureActivity();
            }
        });
        ivMaintitleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = edtMaintitleText.getText().toString().trim();
                findPresenter.toPlaySearchActivity(content);
            }
        });
        btnMaintitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPresenter.hidellMaintitleSearch();
                findPresenter.hideSoftInputFromWindow();
            }
        });
        topicCenterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPresenter.toTopicCenterActivity();
            }
        });
        activityCenterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPresenter.toTopicCenterActivity();
            }
        });
        layoutOriginal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findPresenter.toOriginalRanklistActivity();

            }
        });
        layoutAllRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "和原创排行榜差不多，有时间再做", Toast.LENGTH_SHORT).show();
            }
        });
        layoutShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPresenter.toShoppingActivity();

            }
        });

    }


    @Override
    public String getUrl() {
        return Contans.FIND_URL;
    }

    @Override
    public void TagslayoutsetAdapter(List<TagBean.DataBean.ListBean> list) {

        List<TagBean.DataBean.ListBean> hotTags = list.subList(0, 8);
        tagsLayout.setAdapter(new TagAdapter<TagBean.DataBean.ListBean>(hotTags) {
            @Override
            public View getView(FlowLayout parent, int position, final TagBean.DataBean.ListBean hotTags) {

                final TextView mTags;
                mTags = (TextView) LayoutInflater.from(getActivity())
                        .inflate(R.layout.layout_tags_item, parent, false);
                mTags.setText(hotTags.getKeyword());

                mTags.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        findPresenter.toPlaySearchActivity(mTags.getText().toString());
                    }
                });

                return mTags;
            }
        });
        hideTagsLayout.setAdapter(new TagAdapter<TagBean.DataBean.ListBean>(list) {
            @Override
            public View getView(FlowLayout parent, int position, final TagBean.DataBean.ListBean list) {

                final TextView mTags = (TextView) LayoutInflater.from(getActivity())
                        .inflate(R.layout.layout_tags_item, parent, false);
                mTags.setText(list.getKeyword());

                mTags.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        findPresenter.toPlaySearchActivity(mTags.getText().toString());
                    }
                });

                return mTags;
            }
        });
    }

    @Override
    public void hideHideScrollView() {
        hideScrollView.setVisibility(View.GONE);
    }

    @Override
    public void showhideScrollView() {
        hideScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setStopText() {
        tvMore.setText("收起");
    }

    @Override
    public void setMoreText() {
        tvMore.setText("查看更多");
    }

    @Override
    public void hideTagslayout() {
        tagsLayout.setVisibility(View.GONE);
    }

    @Override
    public void showTagslayout() {
        tagsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUpDrawable() {
        Drawable upDrawable = getResources().getDrawable(R.drawable.ic_arrow_up_gray_round);
        upDrawable.setBounds(0, 0, upDrawable.getMinimumWidth(), upDrawable.getMinimumHeight());
        tvMore.setCompoundDrawables(upDrawable, null, null, null);
    }


    @Override
    public void setDownDrawable() {
        Drawable downDrawable = getResources().getDrawable(R.drawable.ic_arrow_down_gray_round);
        downDrawable.setBounds(0, 0, downDrawable.getMinimumWidth(), downDrawable.getMinimumHeight());
        tvMore.setCompoundDrawables(downDrawable, null, null, null);
    }

    @Override
    public void getMainActivity() {
        mainactivity = (MainActivity) getActivity();
        llMaintitleSearch = (LinearLayout) mainactivity.findViewById(R.id.ll_maintitle_search);
        ivMaintitleBack = (ImageView) mainactivity.findViewById(R.id.iv_maintitle_back);
        edtMaintitleText = (EditText) mainactivity.findViewById(R.id.edt_maintitle_text);
        ivMaintitleSearch = (ImageView) mainactivity.findViewById(R.id.iv_maintitle_search);
        btnMaintitleBack = (Button) mainactivity.findViewById(R.id.btn_maintitle_back);
        ivMaintitleScan = (ImageView) mainactivity.findViewById(R.id.iv_maintitle_scan);
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
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        imm.hideSoftInputFromWindow(edtMaintitleText.getWindowToken(), 0);
    }

    @Override
    public void toCaptureActivity() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE2);

    }

    @Override
    public void toPlaySearcheActivity(String content) {

        Intent intent = new Intent(getActivity(), PlaySearchActivity.class);
        intent.putExtra("content", content);
        startActivity(intent);
    }

    @Override
    public void toTopicCenterActivity() {
        Intent intent = new Intent(context, TopicCenterActivity.class);
        startActivity(intent);
    }

    @Override
    public void toOriginalRanklistActivity() {
        Intent intent = new Intent(context, OriginalRanklistActivity.class);
        startActivity(intent);
    }

    @Override
    public void toShoppingActivity() {
        Intent intent = new Intent(context, ShoppingActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(Exception ex) {
        Log.e("TAG", "Exception" + ex.getMessage());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            //得到返回结果
        }
    }
}
