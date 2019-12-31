package com.jinshu.homelibrary.adapter;

import android.content.Context;

import com.jinshu.homelibrary.R;
import com.jinshu.homelibrary.entity.ShopInfo;
import com.jinshu.homelibrary.recyclerview.baseadapter.FCommonRecycleViewAdapter;
import com.jinshu.homelibrary.recyclerview.baseadapter.SViewHolderHelper;

/**
 * Create on 2019/11/8 17:31 by bll
 */


public class ShopListAdapter extends FCommonRecycleViewAdapter<ShopInfo> {

    public ShopListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(SViewHolderHelper helper, ShopInfo shopInfo, int position) {
        helper.setImageUrl(R.id.iv_shop_img, shopInfo.getListImage());
        helper.setText(R.id.tv_shop_name, shopInfo.getName());
        helper.setText(R.id.tv_shop_phone, shopInfo.getTel());
        helper.setText(R.id.tv_shop_address, shopInfo.getAddress());
    }
}
