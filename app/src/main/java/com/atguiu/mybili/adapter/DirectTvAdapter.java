package com.atguiu.mybili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguiu.mybili.R;
import com.atguiu.mybili.bean.DirecTvInfo;
import com.atguiu.mybili.bean.GuiguInfo;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.ForegroundToBackgroundTransformer;
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

public class DirectTvAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final DirecTvInfo.DataBean datas;


    public static final int BANNER = 0;

    public static final int SECOND = 1;

    public static final int AREA = 2;
    public static final int LIFE = 3;
    public static final int SING = 4;


    public int currentType = BANNER;


    private List<GuiguInfo.ResultBean.RecommendInfoBean> recommend_info;
    private SecondViewHolder secondViewHolder;


    public DirectTvAdapter(Context context, DirecTvInfo.DataBean datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, View.inflate(context, R.layout.banner_viewpager, null));
        } else if (viewType == SECOND) {
            return new SecondViewHolder(context, View.inflate(context, R.layout.second_item, null));
        } else if (viewType == AREA) {
            return new AreaViewHolder(context, View.inflate(context, R.layout.area_item, null));
        } else if (viewType == LIFE) {
            return new LifeViewHolder(context, View.inflate(context, R.layout.lift_item, null));

        } else if (viewType == SING) {
            return new SingViewHolder(context, View.inflate(context, R.layout.sing_item, null));

        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;

            bannerViewHolder.setData(datas.getBanner());

        } else if (getItemViewType(position) == SECOND) {
            secondViewHolder = (SecondViewHolder) holder;
            getDataFromGuigu();
        } else if (getItemViewType(position) == AREA) {
            AreaViewHolder areaViewHolder = (AreaViewHolder) holder;
            areaViewHolder.setData(datas.getPartitions());
        } else if (getItemViewType(position) == LIFE) {
            LifeViewHolder lifeViewHolder = (LifeViewHolder) holder;
            lifeViewHolder.setData(datas.getPartitions());

        } else if (getItemViewType(position) == SING) {
            SingViewHolder singViewHolder = (SingViewHolder) holder;
            singViewHolder.setData(datas.getPartitions());
        }
    }

    private void getDataFromGuigu() {
        OkHttpUtils
                .get()
                //联网地址
                .url("http://47.93.118.241:8081//android/resources/json/HOME_URL.json")
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
        GuiguInfo guiguInfo = new Gson().fromJson(response, GuiguInfo.class);
        recommend_info = guiguInfo.getResult().getRecommend_info();
        //Log.e("TAG","recommend_info"+recommend_info.toString());
        secondViewHolder.setData(recommend_info);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == SECOND) {
            currentType = SECOND;
        } else if (position == AREA) {
            currentType = AREA;
        } else if (position == LIFE) {
            currentType = LIFE;
        } else if (position == SING) {
            currentType = SING;
        }

        return currentType;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class SingViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.iv_title_image)
        ImageView ivTitleImage;
        @InjectView(R.id.tv_title_text)
        TextView tvTitleText;
        @InjectView(R.id.tv_number_zhubo)
        TextView tvNumberZhubo;
        @InjectView(R.id.ll_recommend_zhubo)
        LinearLayout llRecommendZhubo;
        @InjectView(R.id.gv_video)
        GridView gvVideo;
        @InjectView(R.id.btn_watch_more)
        Button btnWatchMore;
        @InjectView(R.id.ima_refresh)
        ImageView imaRefresh;
        private SingAdapter singAdapter;

        public SingViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<DirecTvInfo.DataBean.PartitionsBean> partitions) {
            Glide.with(context).load(partitions.get(2).getPartition().getSub_icon().getSrc()).into(ivTitleImage);
            tvTitleText.setText(partitions.get(2).getPartition().getName());
            tvNumberZhubo.setText("当前" + partitions.get(2).getPartition().getCount() + "个直播");

            singAdapter = new SingAdapter(context, partitions);
            gvVideo.setAdapter(singAdapter);

        }
    }


    class LifeViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @InjectView(R.id.iv_title_image)
        ImageView ivTitleImage;
        @InjectView(R.id.tv_title_text)
        TextView tvTitleText;
        @InjectView(R.id.tv_number_zhubo)
        TextView tvNumberZhubo;
        @InjectView(R.id.ll_recommend_zhubo)
        LinearLayout llRecommendZhubo;
        @InjectView(R.id.gv_video)
        GridView gvVideo;
        @InjectView(R.id.btn_watch_more)
        Button btnWatchMore;
        @InjectView(R.id.ima_refresh)
        ImageView imaRefresh;
        private LifeAdapter lifeAdapter;

        public LifeViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);

        }

        public void setData(List<DirecTvInfo.DataBean.PartitionsBean> partitions) {
            Glide.with(context).load(partitions.get(1).getPartition().getSub_icon().getSrc()).into(ivTitleImage);
            tvTitleText.setText(partitions.get(1).getPartition().getName());
            tvNumberZhubo.setText("当前" + partitions.get(1).getPartition().getCount() + "个直播");

            lifeAdapter = new LifeAdapter(context, partitions);
            gvVideo.setAdapter(lifeAdapter);

        }
    }

    class AreaViewHolder extends RecyclerView.ViewHolder {


        private final Context context;
        @InjectView(R.id.iv_title_image)
        ImageView ivTitleImage;
        @InjectView(R.id.tv_title_text)
        TextView tvTitleText;
        @InjectView(R.id.tv_number_zhubo)
        TextView tvNumberZhubo;
        @InjectView(R.id.ll_recommend_zhubo)
        LinearLayout llRecommendZhubo;
        @InjectView(R.id.gv_video)
        GridView gvVideo;
        @InjectView(R.id.btn_watch_more)
        Button btnWatchMore;
        @InjectView(R.id.ima_refresh)
        ImageView imaRefresh;
        private PaintAdapter paintAdapter;


        public AreaViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<DirecTvInfo.DataBean.PartitionsBean> partitions) {
            Glide.with(context).load(partitions.get(0).getPartition().getSub_icon().getSrc()).into(ivTitleImage);
            tvTitleText.setText(partitions.get(0).getPartition().getName());
            tvNumberZhubo.setText("当前" + partitions.get(0).getPartition().getCount() + "个直播");
            paintAdapter = new PaintAdapter(context, partitions);
            gvVideo.setAdapter(paintAdapter);
        }

/*
        public void setData(DirecTvInfo.DataBean.PartitionsBean partitionsBean) {
            paintAdapter  = new PaintAdapter(context,partitionsBean);

            gvVideo.setAdapter(paintAdapter);


        }*/
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @InjectView(R.id.ll_recommend_zhubo)
        LinearLayout llRecommendZhubo;
        @InjectView(R.id.gv_recommend)
        GridView gvRecommend;

        private RecommendAdapter recommendAdapter;

        public SecondViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
           ButterKnife.inject(this,itemView);
        }

        public void setData(List<GuiguInfo.ResultBean.RecommendInfoBean> recommend_info) {
            recommendAdapter = new RecommendAdapter(context, recommend_info);

            gvRecommend.setAdapter(recommendAdapter);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @InjectView(R.id.banner)
        Banner banner;
        @InjectView(R.id.ll_center)
        LinearLayout llCenter;
        @InjectView(R.id.ll_game)
        LinearLayout llGame;
        @InjectView(R.id.ll_search)
        LinearLayout llSearch;
        @InjectView(R.id.ll_type)
        LinearLayout llType;
        @InjectView(R.id.ll_watch)
        LinearLayout llWatch;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<DirecTvInfo.DataBean.BannerBean> banner_info) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                images.add(banner_info.get(0).getImg());
            }
            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    })
                    .start();
            banner.setBannerAnimation(ForegroundToBackgroundTransformer.class);


        }
    }

}
