package com.jinshu.homelibrary.recyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinshu.homelibrary.R;
import com.jinshu.homelibrary.recyclerview.irc.RefreshTrigger;
import com.jinshu.homelibrary.utils.TimeUtil;


/**
 * Created by aspsine on 16/3/14.
 */
public class ClassicRefreshHeaderView extends RelativeLayout implements RefreshTrigger {
    private ImageView ivArrow;

    private ImageView ivSuccess;

    private TextView tvRefresh, tvRefreshDate;

    private ProgressBar progressBar;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;

    private int mHeight;

    public ClassicRefreshHeaderView(Context context) {
        this(context, null);
    }

    public ClassicRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.library_layout_irecyclerview_classic_refresh_header_view, this);

        tvRefresh = (TextView) findViewById(R.id.tvRefresh);

        tvRefreshDate = (TextView) findViewById(R.id.tvRefreshDate);

        ivArrow = (ImageView) findViewById(R.id.ivArrow);

        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        rotateUp = AnimationUtils.loadAnimation(context, R.anim.library_rotate_up);

        rotateDown = AnimationUtils.loadAnimation(context, R.anim.library_rotate_down);

    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
        this.mHeight = headerHeight;
        progressBar.setIndeterminate(false);
    }

    @Override
    public void onMove(boolean isComplete, boolean automatic, int moved) {
        if (!isComplete) {
            ivArrow.setVisibility(VISIBLE);
            progressBar.setVisibility(GONE);
            ivSuccess.setVisibility(GONE);
            if (moved <= mHeight) {
                if (rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }

                tvRefresh.setText("下拉刷新");
                tvRefreshDate.setVisibility(GONE);
            } else {
                tvRefresh.setText("释放开始刷新");
                tvRefreshDate.setVisibility(View.GONE);
                if (!rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("正在刷新");
        tvRefreshDate.setVisibility(VISIBLE);
        tvRefreshDate.setText("更新于" + TimeUtil.getCurrentDay2());
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("刷新完成");
        tvRefreshDate.setText("更新于" + TimeUtil.getCurrentDay2());
    }

    @Override
    public void onReset() {
        rotated = false;
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }
}
