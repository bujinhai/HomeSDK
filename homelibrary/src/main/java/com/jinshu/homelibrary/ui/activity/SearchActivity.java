package com.jinshu.homelibrary.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinshu.homelibrary.R;
import com.jinshu.homelibrary.adapter.FBaseFragmentAdapter;
import com.jinshu.homelibrary.api.FApi;
import com.jinshu.homelibrary.api.FHostType;
import com.jinshu.homelibrary.baseapp.FAppConstant;
import com.jinshu.homelibrary.baserx.FRxHelper;
import com.jinshu.homelibrary.baserx.FRxSchedulers;
import com.jinshu.homelibrary.baserx.FRxSubscriber;
import com.jinshu.homelibrary.entity.SearchDefineData;
import com.jinshu.homelibrary.entity.SearchDefineInfo;
import com.jinshu.homelibrary.ui.fragment.SearchFragment;
import com.jinshu.homelibrary.utils.BaseUtils;
import com.jinshu.homelibrary.utils.StrUtils;
import com.jinshu.homelibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 2019/10/8 15:05 by bll
 */


public class SearchActivity extends AppCompatActivity {

    private ImageView ivBack;
    private EditText etSearch;
    private TextView tvSearch;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private SearchFragment fragment;
    private int position;


//    private String sessionId, siteId, objectDefineId, searchDefineId;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString(FApiConstants.OBJECT_DEFINE_ID, objectDefineId);
//        bundle.putString(FApiConstants.SEARCH_DEFINE_ID, searchDefineId);
//        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_act_search);

//        Bundle bundle = getIntent().getExtras();
//        objectDefineId = bundle.getString(FApiConstants.OBJECT_DEFINE_ID);
//        searchDefineId = bundle.getString(FApiConstants.SEARCH_DEFINE_ID);

        ivBack = findViewById(R.id.iv_left_image);
        etSearch = findViewById(R.id.et_search);
        tvSearch = findViewById(R.id.tv_search);
        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);

        getSearchDefine();
        setListener();
    }

    private void getSearchDefine() {
        FApi.getDefault(FHostType.BASE_URL)
                .getSearchDefineList(BaseUtils.getSessionID())
                .compose(FRxHelper.<SearchDefineData>handleResult())
                .compose(FRxSchedulers.<SearchDefineData>io_main())
                .subscribe(new FRxSubscriber<SearchDefineData>(this, false) {
                    @Override
                    protected void _onNext(SearchDefineData searchDefineData) {
                        if (searchDefineData.getData() == null || searchDefineData.getData().getRows() == null) {
                            return;
                        }
                        initFragment(searchDefineData.getData().getRows());
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showShort(message);
                    }
                });
    }

    private void initFragment(List<SearchDefineInfo> infoList) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            if (StrUtils.isEmpty(infoList.get(i).getName())){
                continue;
            }
            titles.add(infoList.get(i).getName());
            fragment = new SearchFragment();
            Bundle args = new Bundle();
            args.putString(FAppConstant.SEARCH_OBJECT_DEFINE_ID, infoList.get(i).getObjectDefineID());
            args.putString(FAppConstant.SEARCH_DEFINE_ID, infoList.get(i).getSearchDefineID());
            fragment.setArguments(args);
            fragments.add(fragment);
        }

        viewPager.setAdapter(new FBaseFragmentAdapter(getSupportFragmentManager(), fragments, titles));
        viewPager.setOffscreenPageLimit(titles.size() - 1);
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabTextColors(ContextCompat.getColor(this, R.color.library_main_text_color), ContextCompat.getColor(this, R.color.library_orange));
    }


    private void setListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String search = etSearch.getText().toString();
                if (TextUtils.isEmpty(search)) {
                    ToastUtil.showShort("请输入搜索内容");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putInt("", position);
                fragment.setArguments(bundle);

            }
        });

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
