package com.foang.library;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.foang.library.transform.BannerTransform;

import java.util.ArrayList;

/**
 * Created by FoAng on 17/6/23 上午10:19;
 */

public class LoopAdapter<T extends View> extends PagerAdapter{

    public static final int LOOP_COUNT = 2;

    private ArrayList<T> mViewList;

    private ViewPager mViewPager;

    public LoopAdapter(ViewPager mViewPager, ArrayList<T> mViewList, LoopPageListener listener) {
        this.mViewPager = mViewPager;
        this.mViewList = mViewList;
        this.mViewPager.setOnPageChangeListener(listener);
        this.mViewPager.setPageTransformer(true, new BannerTransform());
    }

    @Override
    public int getCount() {
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mViewList.get(position) != null) {
            View mView = mViewList.get(position);
            container.addView(mView);
            return mView;
        } else {
            return super.instantiateItem(container, position);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View mView = mViewList.get(position);
        try {
            if (mView != null) {
                container.removeView(mView);
            } else {
                super.destroyItem(container, position, object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
