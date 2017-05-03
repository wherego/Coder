package com.peng_hongru.coder.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.peng_hongru.coder.R;
import com.peng_hongru.coder.dagger.component.activity.DaggerImageComponent;
import com.peng_hongru.coder.dagger.module.activity.ImageModule;
import com.peng_hongru.coder.module.dao.DbHelper;
import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.presenter.activity.ImagePresenter;
import com.peng_hongru.coder.utils.ImageLoader;
import com.peng_hongru.coder.utils.QiNiuUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ImageActivity extends BaseActivity implements View.OnClickListener {


    private final Handler mHideHandler = new Handler();
    @InjectView(R.id.fullscreen_content)
    ImageView mContentView;
    @InjectView(R.id.image_position)
    TextView imagePosition;
    @InjectView(R.id.fullscreen_content_controls)
    LinearLayout mControlsView;
    @InjectView(R.id.collection_button)
    Button collectionButton;
    @InjectView(R.id.save_button)
    Button saveButton;
    @Inject
    public ImagePresenter presenter;

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;
    private static final int FLING_MIN_DISTANCE = 120;//移动最小距离
    private static final int FLING_MIN_VELOCITY = 200;//移动最大速度
    private static final String DATA_KEY = "informations";
    private static final String DATA_POSITION = "position";
    @InjectView(R.id.loading_progress)
    ProgressBar loadingProgress;
    private ArrayList<Information> datas;
    private int position;
    private boolean mVisible;


    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    public static Intent getStartIntent(Context context, ArrayList<Information> informations, int position) {
        Intent intent = new Intent(context, ImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DATA_KEY, informations);
        bundle.putInt(DATA_POSITION, position);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.inject(this);
        DaggerImageComponent component = (DaggerImageComponent) DaggerImageComponent.builder()
                .imageModule(new ImageModule(this))
                .build();
        component.in(this);

        Intent intent = getIntent();
        datas = intent.getParcelableArrayListExtra(DATA_KEY);
        position = intent.getIntExtra(DATA_POSITION, 0);
        mVisible = true;

        collectionButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

        refreshImage(position);

        refreshCollectionButton(datas.get(position));
    }

    public void refreshImage(List<Information> informations) {
        datas.clear();
        datas.addAll(informations);
        setImagePosition(0, datas.size());
    }

    public void loadMoreImage(List<Information> informations) {
        Logger.d(informations.size());
        datas.addAll(informations);
        Logger.d(datas.size());
        setImagePosition(position, datas.size());
    }

    public int getDataSize() {
        return datas.size();
    }

    public void showLoadingProgressBar() {
        loadingProgress.setVisibility(View.VISIBLE);
    }

    public void hideLoadingProgressBar() {
        loadingProgress.setVisibility(View.GONE);
    }

    private void refreshImage(int position) {
        ImageLoader.loadImage(
                this,
                QiNiuUtils.setUrl(datas.get(position).getUrl())
                        .slim()
                        .size(this, true)
                        .interlace(true)
                        .commit(),
                mContentView
        );
        setImagePosition(position, datas.size());
        refreshCollectionButton(datas.get(position));
    }

    private void refreshCollectionButton(Information information) {
        collectionButton.setText(
                DbHelper.contain(information)
                        ? "已收藏"
                        : "收藏"
        );
    }

    private void setImagePosition(int current, int max) {
        imagePosition.setText(++current + "/" + max);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };


    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };


    private GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                position++;
                if (position < datas.size()) {
                    refreshImage(position); // 右滑
                } else {
                    position--;
                    // TODO: 2017/5/2 加载更多
                    presenter.loadMore();
                }
                return true;
            }
            if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                position--;
                if (position >= 0) {
                    refreshImage(position);//左滑
                } else {
                    position = 0;
                    // TODO: 2017/5/2 刷新
                    presenter.loadData();
                }
                return true;
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            toggle();
        }
    };

    private GestureDetector gestureDetector = new GestureDetector(onGestureListener); // 手势探测器


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collection_button:
                if (!DbHelper.contain(datas.get(position))) {
                    try {
                        DbHelper.getReadSession().getInformationDao().insert(datas.get(position));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                refreshCollectionButton(datas.get(position));
                break;
            case R.id.save_button:
                //// TODO: 2017/5/3 将图片保存至本地
                break;
        }
    }
}
