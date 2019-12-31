package com.jinshu.homelibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jinshu.homelibrary.R;
import com.jinshu.homelibrary.adapter.ShopAdapter;
import com.jinshu.homelibrary.api.FApi;
import com.jinshu.homelibrary.api.FHostType;
import com.jinshu.homelibrary.baseapp.FAppConstant;
import com.jinshu.homelibrary.baserx.FRxHelper;
import com.jinshu.homelibrary.baserx.FRxSchedulers;
import com.jinshu.homelibrary.baserx.FRxSubscriber;
import com.jinshu.homelibrary.entity.ShopEntity;
import com.jinshu.homelibrary.entity.ShopInfo;
import com.jinshu.homelibrary.recyclerview.irc.OnItemClickListener;
import com.jinshu.homelibrary.ui.activity.ShopDetailActivity;
import com.jinshu.homelibrary.ui.activity.ShopListActivity;
import com.jinshu.homelibrary.utils.BaseUtils;
import com.jinshu.homelibrary.utils.JumpUtils;
import com.jinshu.homelibrary.utils.ToastUtil;

/**
 * Create on 2019/11/8 15:47 by bll
 */


public class ShopView extends LinearLayout {

    private int currentPage = 1;
    private int pageNumber = 3;

    private LinearLayout llMore;
    private ShopAdapter mShopAdapter;
    private Activity mActivity;

    public ShopView(Context context) {
        this(context, null);
    }

    public ShopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.library_shop_view, this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        llMore = view.findViewById(R.id.ll_more_shop);
        mShopAdapter = new ShopAdapter(context, R.layout.library_fragment_adapter_item_shop);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(mShopAdapter);

        initData();
        setListener();
    }

    private void initData() {
        getShopList();
    }

    private void setListener() {
        llMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.jumpActivity(mActivity, ShopListActivity.class);
            }
        });

        mShopAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object o, int position) {
                ShopInfo info = (ShopInfo) o;
                Bundle bundle = new Bundle();
                bundle.putString(FAppConstant.SHOP_ID, info.getShopID());
                JumpUtils.jumpActivity(mActivity, ShopDetailActivity.class, bundle);
            }
        });
    }

    /**
     * 动态设置店铺数量
     *
     * @param num
     */
    public void setShowNumber(int num) {
        pageNumber = num;
        getShopList();
    }

    private void getShopList() {
        FApi.getDefault(FHostType.BASE_URL)
                .getShopList(BaseUtils.getSessionID(), BaseUtils.getApplicationID(), currentPage, pageNumber, "1")
                .compose(FRxHelper.<ShopEntity>handleResult())
                .compose(FRxSchedulers.<ShopEntity>io_main())
                .subscribe(new FRxSubscriber<ShopEntity>(getContext(), false) {
                    @Override
                    protected void _onNext(ShopEntity shopEntity) {
                        if (shopEntity.getData() == null || shopEntity.getData().getRows() == null) {
                            return;
                        }
                        if (shopEntity.getData().getRows().size() == 0) {
                            llMore.setVisibility(GONE);
                            return;
                        }
                        mShopAdapter.replaceAll(shopEntity.getData().getRows());
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showShort(message);
                    }
                });
    }
}
