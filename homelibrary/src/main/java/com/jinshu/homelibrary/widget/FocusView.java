package com.jinshu.homelibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jinshu.homelibrary.R;
import com.jinshu.homelibrary.api.FApi;
import com.jinshu.homelibrary.api.FHostType;
import com.jinshu.homelibrary.baserx.FRxHelper;
import com.jinshu.homelibrary.baserx.FRxSchedulers;
import com.jinshu.homelibrary.baserx.FRxSubscriber;
import com.jinshu.homelibrary.entity.FocusEntity;
import com.jinshu.homelibrary.entity.FocusInfo;
import com.jinshu.homelibrary.utils.BaseUtils;
import com.jinshu.homelibrary.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 2019/11/8 18:20 by bll
 */


public class FocusView extends LinearLayout {

    private String focusID;
    private List<String> images;
    private List<String> titles;
    private List<FocusInfo> mInfo;
    private Banner banner;

    public FocusView(Context context) {
        this(context, null);
    }

    public FocusView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FocusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.library_focus_view, this);
        banner = view.findViewById(R.id.banner);
        images = new ArrayList<>();
        titles = new ArrayList<>();

        setListener();
    }

    public void setFocusID(String focusId) {
        this.focusID = focusId;
        //轮播图
        getFocusData();
    }

    /**
     * 获取轮播图数据
     */
    private void getFocusData() {
        FApi.getDefault(FHostType.BASE_URL)
                .getFocusPictureList(BaseUtils.getSessionID(), focusID)
                .compose(FRxHelper.<FocusEntity>handleResult())
                .compose(FRxSchedulers.<FocusEntity>io_main())
                .subscribe(new FRxSubscriber<FocusEntity>(getContext(), false) {
                    @Override
                    protected void _onNext(FocusEntity response) {
                        if (response.getData() == null || response.getData().getRows() == null) {
                            return;
                        }
                        mInfo = response.getData().getRows();
                        initBanner(response.getData().getRows());
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showShort(message);
                    }
                });
    }

    private void initBanner(List<FocusInfo> infoList) {
        for (FocusInfo info : infoList) {
            images.add(info.getPicturePath());
            titles.add(info.getName());
        }

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public void startAutoPlay() {
        banner.startAutoPlay();
    }

    public void stopAutoPlay() {
        banner.stopAutoPlay();
    }

    private void setListener() {
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (mListener != null) {
                    mListener.onBannerClick(mInfo.get(position));
                }
            }
        });
    }

    private OnBannerClickListener mListener;

    public interface OnBannerClickListener {
        void onBannerClick(FocusInfo info);
    }

    public void setOnBannerClickListener(OnBannerClickListener listener) {
        mListener = listener;
    }

}
