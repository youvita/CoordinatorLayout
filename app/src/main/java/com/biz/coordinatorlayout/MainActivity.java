package com.biz.coordinatorlayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.biz.coordinatorlayout.animation.SlideAnimation;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener {
    private CollapsingToolbarLayout mCollapsingToolbar;
    private Toolbar mToolBar;
    private TextView mCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollapsingToolbar = findViewById(R.id.collapsing_toolbar);
        mToolBar = findViewById(R.id.tool_bar);
        mCancel  = findViewById(R.id.et_cancel);

        EditText mSearch = findViewById(R.id.et_search);
        mSearch.setOnFocusChangeListener(this);
        mSearch.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_search:
                tranAnimationUp();
                disableScroll();
                mCancel.setVisibility(View.VISIBLE);

                break;
            case R.id.et_cancel:
                tranAnimationDown();
                enableScroll();
                mCancel.setVisibility(View.GONE);

                /*
                 * hided keyboard
                 */
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) tranAnimationUp();
        disableScroll();
        mCancel.setVisibility(View.VISIBLE);
    }

    /*
     * enable scroll for hide view
     */
    private void enableScroll() {
        final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mCollapsingToolbar.getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        mCollapsingToolbar.setLayoutParams(params);
    }

    /*
     * disable scroll for hide view
     */
    private void disableScroll() {
        final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mCollapsingToolbar.getLayoutParams();
        params.setScrollFlags(0);
        mCollapsingToolbar.setLayoutParams(params);
    }

    /*
     * animation up
     */
    private void tranAnimationUp() {
        Animation animation = new SlideAnimation(mToolBar, getActionBarHeight(), 0);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setDuration(300);
        mToolBar.setAnimation(animation);
        mToolBar.startAnimation(animation);
    }

    /*
     * animation down
     */
    private void tranAnimationDown() {
        Animation animation = new SlideAnimation(mToolBar, 0, getActionBarHeight());
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setDuration(300);
        mToolBar.setAnimation(animation);
        mToolBar.startAnimation(animation);
    }

    /*
     * get action bar height
     */
    @SuppressLint("ObsoleteSdkInt")
    private int getActionBarHeight() {
        int[] abSzAttr;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            abSzAttr = new int[] { android.R.attr.actionBarSize };
        } else {
            abSzAttr = new int[] { R.attr.actionBarSize };
        }
        @SuppressLint("Recycle") TypedArray a = obtainStyledAttributes(abSzAttr);
        return a.getDimensionPixelSize(0, -1);
    }
}
