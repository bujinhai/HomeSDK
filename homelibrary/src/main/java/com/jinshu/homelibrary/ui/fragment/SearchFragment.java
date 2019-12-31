package com.jinshu.homelibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jinshu.homelibrary.R;
import com.jinshu.homelibrary.adapter.HistoryAdapter;
import com.jinshu.homelibrary.adapter.RecommendAdapter;
import com.jinshu.homelibrary.adapter.SearchAdapter;
import com.jinshu.homelibrary.api.FApi;
import com.jinshu.homelibrary.api.FHostType;
import com.jinshu.homelibrary.base.FBaseFragment;
import com.jinshu.homelibrary.baseapp.FAppConstant;
import com.jinshu.homelibrary.basebean.FBaseResponse;
import com.jinshu.homelibrary.baserx.FRxHelper;
import com.jinshu.homelibrary.baserx.FRxSchedulers;
import com.jinshu.homelibrary.baserx.FRxSubscriber;
import com.jinshu.homelibrary.entity.HistoryData;
import com.jinshu.homelibrary.entity.HistoryInfo;
import com.jinshu.homelibrary.entity.HotWordData;
import com.jinshu.homelibrary.entity.HotWordInfo;
import com.jinshu.homelibrary.recyclerview.irc.OnItemClickListener;
import com.jinshu.homelibrary.utils.BaseUtils;
import com.jinshu.homelibrary.utils.ToastUtil;
import com.jinshu.homelibrary.widget.FNoScrollRecyclerView;

/**
 * Create on 2019/11/26 15:30 by bll
 */


public class SearchFragment extends FBaseFragment {

    private FNoScrollRecyclerView mRvHistory, mRvRecommend;
    private ImageView ivDelete;
    private HistoryAdapter mHisAdapter;
    private RecommendAdapter mRecAdapter;
    private RecyclerView mRvResult;
    private SearchAdapter mSearchAdapter;

    private String objectDefineID;
    private String searchDefineID;

    @Override
    protected Object getLayoutResource() {
        return R.layout.library_fragment_search;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mRvResult = bindView(R.id.search_result);
        mRvHistory = bindView(R.id.rv_history);
        mRvRecommend = bindView(R.id.rv_recommend);
        ivDelete = bindView(R.id.iv_delete);
        //历史搜索
        mRvHistory.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mHisAdapter = new HistoryAdapter(getContext(), R.layout.library_activity_adapter_item_search_history);
        mRvHistory.setAdapter(mHisAdapter);
        //热词推荐
        mRvRecommend.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecAdapter = new RecommendAdapter(getContext(), R.layout.library_activity_adapter_item_search_recommend);
        mRvRecommend.setAdapter(mRecAdapter);
        //搜索商品列表
        mRvResult.setLayoutManager(new LinearLayoutManager(getContext()));
        mSearchAdapter = new SearchAdapter();
        mRvResult.setAdapter(mSearchAdapter);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        objectDefineID = bundle.getString(FAppConstant.SEARCH_OBJECT_DEFINE_ID);
        searchDefineID = bundle.getString(FAppConstant.SEARCH_DEFINE_ID);

        getHisData();
        getHotData();
    }


    /**
     * 获取历史数据
     */
    private void getHisData() {
        FApi.getDefault(FHostType.BASE_URL)
                .getMemberSearchList(BaseUtils.getSessionID(), objectDefineID, "1", BaseUtils.getSiteID())
                .compose(FRxHelper.<HistoryData>handleResult())
                .compose(FRxSchedulers.<HistoryData>io_main())
                .subscribe(new FRxSubscriber<HistoryData>(getContext(), false) {

                    @Override
                    protected void _onNext(HistoryData response) {
                        if (response.getData() == null || response.getData().getRows() == null) {
                            return;
                        }
                        mHisAdapter.replaceAll(response.getData().getRows());
                    }

                    @Override
                    protected void _onError(String message) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 获取热门数据
     */
    private void getHotData() {
        FApi.getDefault(FHostType.BASE_URL)
                .getHotWordList(BaseUtils.getSessionID(), searchDefineID, "1")
                .compose(FRxHelper.<HotWordData>handleResult())
                .compose(FRxSchedulers.<HotWordData>io_main())
                .subscribe(new FRxSubscriber<HotWordData>(getContext(), false) {
                    @Override
                    protected void _onNext(HotWordData response) {
                        if (response.getData() == null || response.getData().getRows() == null) {
                            return;
                        }
                        mRecAdapter.replaceAll(response.getData().getRows());
                    }

                    @Override
                    protected void _onError(String message) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setListener() {
        mHisAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object o, int position) {
                HistoryInfo info = (HistoryInfo) o;

            }
        });

        mRecAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object o, int position) {
                HotWordInfo info = (HotWordInfo) o;
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FApi.getDefault(FHostType.BASE_URL)
                        .deleteMyMemberSearch(BaseUtils.getSessionID())
                        .compose(FRxSchedulers.<FBaseResponse>io_main())
                        .subscribe(new FRxSubscriber<FBaseResponse>(mContext, false) {
                            @Override
                            protected void _onNext(FBaseResponse fBaseResponse) {
                                if (!fBaseResponse.success()) {
                                    ToastUtil.showShort(fBaseResponse.header.msg);
                                }
                                getHisData();
                            }

                            @Override
                            protected void _onError(String message) {
                                ToastUtil.showShort(message);
                            }
                        });
            }
        });
    }

    private void getList(String objectDefineId) {

    }
}
