package com.jinshu.homemodule;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.jinshu.homelibrary.ui.activity.SearchActivity;
import com.jinshu.homelibrary.utils.BaseUtils;
import com.jinshu.homelibrary.utils.ToastUtil;
import com.jinshu.homelibrary.utils.UpdateUtils;
import com.jinshu.homelibrary.utils.facp.Acp;
import com.jinshu.homelibrary.utils.facp.AcpListener;
import com.jinshu.homelibrary.utils.facp.AcpOptions;
import com.jinshu.homelibrary.widget.FocusView;
import com.jinshu.homelibrary.widget.ShopView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UpdateUtils.OnUpDateClickListener{

    private final String FOCUS_ID = "82a7d2cbd6c44b7690142e987d18b808";
    private final String SITE_ID = "8a2f462a6aa17470016aa47bac8f0f32";
    private String VERSION_ID = "8a2f462a6eb0a0f8016eb55e46ef117b";

    private Button btnUpdate, btn1, btn2;
    private FocusView mFocusView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnUpdate = findViewById(R.id.btn_update);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        //店铺view
        ShopView view = findViewById(R.id.shop_view);
        view.setActivity(this);
        //轮播图view
        mFocusView = findViewById(R.id.focus_view);
        mFocusView.setFocusID(FOCUS_ID);

        mFocusView.setOnBannerClickListener(info -> ToastUtil.showShort(info.getName()));

        btnUpdate.setOnClickListener(v -> requestPermission());

        btn2.setOnClickListener(v -> {
            SearchActivity.newInstance(MainActivity.this);
        });
    }

    private void requestPermission() {
        Acp.getInstance(this).request(new AcpOptions.Builder().setPermissions(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        //检查版本更新
                        UpdateUtils.getVersionData(MainActivity.this, BaseUtils.getSiteID());
                        UpdateUtils.setOnUpDateCancelListener(MainActivity.this);
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(MainActivity.this, "获取权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 轮播图，以下方法必须要写
     */
    @Override
    public void onStart() {
        super.onStart();
        mFocusView.startAutoPlay();
    }

    /**
     * 轮播图，以下方法必须要写
     */
    @Override
    public void onStop() {
        super.onStop();
        mFocusView.stopAutoPlay();
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onNew(String versionCode, String versionID) {

    }

}
