package com.jinshu.homelibrary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinshu.homelibrary.R;
import com.jinshu.homelibrary.api.FApi;
import com.jinshu.homelibrary.api.FHostType;
import com.jinshu.homelibrary.base.FBaseActivity;
import com.jinshu.homelibrary.baseapp.FAppConstant;
import com.jinshu.homelibrary.baserx.FRxHelper;
import com.jinshu.homelibrary.baserx.FRxSchedulers;
import com.jinshu.homelibrary.baserx.FRxSubscriber;
import com.jinshu.homelibrary.entity.ShopDetailEntity;
import com.jinshu.homelibrary.utils.BaseUtils;
import com.jinshu.homelibrary.utils.ImageLoaderUtils;
import com.jinshu.homelibrary.utils.ToastUtil;

/**
 * Create on 2019/11/8 17:21 by bll
 */


public class ShopDetailActivity extends FBaseActivity {

    private TextView tvTitle;
    private ImageView ivBack;

    private String shopID;
    private ImageView ivShop;
    private TextView tvShopName, tvShopPhone, tvShopAddress, tvShopDescribe;

    @Override
    public int getLayoutId() {
        return R.layout.library_act_shop_detail;
    }

    @Override
    public void initView(Intent intent, @Nullable Bundle savedInstanceState) {

        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_left_image);

        ivShop = findViewById(R.id.iv_shop_img);
        tvShopName = findViewById(R.id.tv_shop_name);
        tvShopPhone = findViewById(R.id.tv_shop_phone);
        tvShopAddress = findViewById(R.id.tv_shop_address);
        tvShopDescribe = findViewById(R.id.tv_shop_describe);

        tvTitle.setText("店铺详情");

        setListener();
    }

    @Override
    protected void onInitData(Intent intent, @Nullable Bundle savedInstanceState) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            shopID = bundle.getString(FAppConstant.SHOP_ID);
        }
        getShopDetail();
    }

    private void getShopDetail() {
        FApi.getDefault(FHostType.BASE_URL)
                .getShopDetail(BaseUtils.getSessionID(), shopID)
                .compose(FRxHelper.<ShopDetailEntity>handleResult())
                .compose(FRxSchedulers.<ShopDetailEntity>io_main())
                .subscribe(new FRxSubscriber<ShopDetailEntity>(mContext) {

                    @Override
                    protected void _onNext(ShopDetailEntity shopDetailEntity) {
                        if (shopDetailEntity.getData() == null) {
                            return;
                        }
                        ShopDetailEntity.DataInfo info = shopDetailEntity.getData();
                        tvShopName.setText(info.getName());
                        tvShopPhone.setText(info.getTel());
                        tvShopAddress.setText(info.getAddress());
                        tvShopDescribe.setText(info.getDescription());
                        if (TextUtils.isEmpty(info.getLogoURL())) {
                            ivShop.setVisibility(View.GONE);
                        } else {
                            ivShop.setVisibility(View.VISIBLE);
                            ImageLoaderUtils.display(mContext, ivShop, info.getLogoURL());
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showShort(message);
                    }
                });
    }

    private void setListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
