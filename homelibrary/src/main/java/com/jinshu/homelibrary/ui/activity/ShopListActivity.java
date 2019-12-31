package com.jinshu.homelibrary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinshu.homelibrary.R;
import com.jinshu.homelibrary.adapter.ShopListAdapter;
import com.jinshu.homelibrary.api.FApi;
import com.jinshu.homelibrary.api.FHostType;
import com.jinshu.homelibrary.base.FBaseActivity;
import com.jinshu.homelibrary.baseapp.FAppConstant;
import com.jinshu.homelibrary.baserx.FRxHelper;
import com.jinshu.homelibrary.baserx.FRxSchedulers;
import com.jinshu.homelibrary.baserx.FRxSubscriber;
import com.jinshu.homelibrary.entity.ShopEntity;
import com.jinshu.homelibrary.entity.ShopInfo;
import com.jinshu.homelibrary.recyclerview.animation.ScaleInAnimation;
import com.jinshu.homelibrary.recyclerview.irc.IRecyclerView;
import com.jinshu.homelibrary.recyclerview.irc.OnItemClickListener;
import com.jinshu.homelibrary.recyclerview.irc.OnLoadMoreListener;
import com.jinshu.homelibrary.recyclerview.irc.OnRefreshListener;
import com.jinshu.homelibrary.recyclerview.widget.LoadMoreFooterView;
import com.jinshu.homelibrary.utils.BaseUtils;
import com.jinshu.homelibrary.utils.JumpUtils;
import com.jinshu.homelibrary.utils.ToastUtil;
import com.jinshu.homelibrary.widget.FLoadingTip;

import io.reactivex.disposables.Disposable;

/**
 * Create on 2019/11/8 17:11 by bll
 */


public class ShopListActivity extends FBaseActivity implements OnRefreshListener, OnLoadMoreListener {

    private TextView tvTitle;
    private ImageView ivBack;

    private int currentPage = 1;
    private int pageNumber = 4;

    private IRecyclerView mIrc;
    private FLoadingTip mLoadingTip;
    private ShopListAdapter mAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.library_act_shop_list;
    }

    @Override
    public void initView(Intent intent, @Nullable Bundle savedInstanceState) {

        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_left_image);

        mIrc = findViewById(R.id.irc);
        mLoadingTip = findViewById(R.id.loadedTip);

        tvTitle.setText("店铺列表");
        mIrc.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShopListAdapter(this, R.layout.library_adapter_item_shop_list);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mIrc.setAdapter(mAdapter);
        mIrc.setOnRefreshListener(this);
        mIrc.setOnLoadMoreListener(this);

        setListener();
    }

    @Override
    protected void onInitData(Intent intent, @Nullable Bundle savedInstanceState) {
        getShopList(true);
    }

    private void getShopList(final boolean isRefresh) {
        FApi.getDefault(FHostType.BASE_URL)
                .getShopList(BaseUtils.getSessionID(), BaseUtils.getApplicationID(), currentPage, pageNumber, "1")
                .compose(FRxHelper.<ShopEntity>handleResult())
                .compose(FRxSchedulers.<ShopEntity>io_main())
                .subscribe(new FRxSubscriber<ShopEntity>(mContext, false) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        if (isRefresh) {
                            mLoadingTip.setLoadingTip(FLoadingTip.LoadStatus.loading);
                        }
                    }

                    @Override
                    protected void _onNext(ShopEntity shopEntity) {
                        if (shopEntity.getData() == null || shopEntity.getData().getRows() == null) {
                            return;
                        }
                        if (isRefresh) {
                            if (shopEntity.getData().getRows().size() == 0) {
                                mLoadingTip.setLoadingTip(FLoadingTip.LoadStatus.empty);
                                return;
                            }
                        }
                        currentPage++;
                        mLoadingTip.setLoadingTip(FLoadingTip.LoadStatus.finish);
                        if (mAdapter.getPageBean().isRefresh()) {
                            mIrc.setRefreshing(false);
                            mIrc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                        } else {
                            if (shopEntity.getData().getRows().size() > 0) {
                                mIrc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                            } else {
                                mIrc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                            }
                        }
                        if (isRefresh) {
                            mAdapter.replaceAll(shopEntity.getData().getRows());
                        } else {
                            mAdapter.addAll(shopEntity.getData().getRows());
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showShort(message);
                        mLoadingTip.setLoadingTip(FLoadingTip.LoadStatus.error);
                    }
                });
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        mAdapter.getPageBean().setRefresh(true);
        mIrc.setRefreshing(true);
        getShopList(true);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mAdapter.getPageBean().setRefresh(false);
        mIrc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        getShopList(false);
    }

    private void setListener() {
        mAdapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View view, Object o, int position) {
                ShopInfo info = (ShopInfo) o;
                Bundle bundle = new Bundle();
                bundle.putString(FAppConstant.SHOP_ID, info.getShopID());
                JumpUtils.jumpActivity(mActivity, ShopDetailActivity.class, bundle);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
