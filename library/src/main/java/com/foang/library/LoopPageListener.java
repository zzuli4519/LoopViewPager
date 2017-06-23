package com.foang.library;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by FoAng on 17/6/23 上午10:21;
 */

public class LoopPageListener implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;

    private int pageIndex;

    private boolean isAutoLoop = true;

    private LoopListener listener;


    public LoopPageListener(ViewPager mViewPager, boolean isAutoLoop) {
        this.mViewPager = mViewPager;
        this.isAutoLoop = isAutoLoop;
        if (mViewPager == null) throw new IllegalArgumentException("viewPager is null, please check");
        this.mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                removeAutoLoopRunnable();
                return false;
            }
        });
    }

    public void setLoopListener(LoopListener listener) {
        this.listener = listener;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private Runnable loopCallBack = new Runnable() {
        @Override
        public void run() {
            mViewPager.setCurrentItem((pageIndex + 1) % mViewPager.getAdapter().getCount(), true);
        }
    };

    private void postAutoLoopRunnable() {
        removeAutoLoopRunnable();
        mHandler.postDelayed(loopCallBack, 2000);
    }

    private void removeAutoLoopRunnable() {
        mHandler.removeCallbacks(loopCallBack);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        PagerAdapter mLoopAdapter = mViewPager.getAdapter();
        pageIndex = position;
        if (mLoopAdapter != null && mLoopAdapter instanceof LoopAdapter) {
            final int dataSize = mLoopAdapter.getCount() - LoopAdapter.LOOP_COUNT;
            if (position == 0) {
                pageIndex = dataSize;
            } else if (position == dataSize + 1) {
                pageIndex = 1;
            } else if (position >= 1 && position <= dataSize) {
                // the normal data source
                if (listener != null) listener.onPageSelected(position - 1);
                if (isAutoLoop) postAutoLoopRunnable();
            }

            if (pageIndex != position) {
                removeAutoLoopRunnable();
                postSelectPosition(pageIndex, false);
                if (listener != null) listener.onPageSelected(pageIndex - 1);
            }

        }
    }

    private void postSelectPosition(final int position, final boolean isAnimate) {

        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(position, isAnimate);
            }
        }, 300);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
