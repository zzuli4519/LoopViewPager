package com.foang.library.transform;

import android.support.v4.view.ViewPager;
import android.view.View;

public class BannerTransform implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;

    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        if (position > 0 && position <= 1) {
            onRightScroll(page, position);
        } else if (position < 0 && position >= -1) {
            onLeftScroll(page, position);
        } else if (position == 0) {
            onCenterIdle(page);
        }
        onTransform(page, position);
    }

    public void onRightScroll(View view, float position) {

    }

    public void onLeftScroll(View view, float position) {

    }

    public void onCenterIdle(View view) {

    }

    public void onTransform(View view, float position) {
        if (position >= -1 && position <= 1) {

            final float height = view.getHeight();

            final float width = view.getWidth();

            final float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));

            final float verticalMargin = height * (1 - scaleFactor) / 2;

            final float horizontalMargin = width * (1 - scaleFactor) / 2;

            view.setPivotY(0.5f * height);

            view.setPivotX(0.5f * width);

            if (position < 0) {
                view.setTranslationX(horizontalMargin - verticalMargin / 2);
            } else {
                view.setTranslationX(-horizontalMargin + verticalMargin / 2);
            }

            view.setScaleX(scaleFactor);

            view.setScaleY(scaleFactor);

            view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else {
            view.setScaleX(1);
            view.setScaleY(1);
            view.setAlpha(1);
            view.setTranslationX(0);
        }
    }

}