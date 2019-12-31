package com.jinshu.homelibrary.adapter;

import android.content.Context;

import com.jinshu.homelibrary.entity.HistoryInfo;
import com.jinshu.homelibrary.recyclerview.baseadapter.FCommonRecycleViewAdapter;
import com.jinshu.homelibrary.recyclerview.baseadapter.SViewHolderHelper;

/**
 * Create on 2019/10/8 16:22 by bll
 */


public class HistoryAdapter extends FCommonRecycleViewAdapter<HistoryInfo> {

    public HistoryAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(SViewHolderHelper helper, HistoryInfo dataInfo, int position) {

    }
}
