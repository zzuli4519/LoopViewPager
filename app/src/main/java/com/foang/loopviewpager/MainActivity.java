package com.foang.loopviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.foang.library.LoopAdapter;
import com.foang.library.LoopPageListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private LoopAdapter<ImageView> mLoopAdapter;


    private int[] images = new int[]{R.mipmap.icon_guide_01, R.mipmap.icon_guide_02,
            R.mipmap.icon_guide_03, R.mipmap.icon_guide_04, R.mipmap.icon_guide_05};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        mLoopAdapter = new LoopAdapter<>(mViewPager, getImageViewList(images), new LoopPageListener(mViewPager, true));
        mViewPager.setAdapter(mLoopAdapter);
        mViewPager.setCurrentItem(1, true);
    }

    private ArrayList<ImageView> getImageViewList(int[] data){
        ArrayList<ImageView> listViews = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            listViews.add(getImageView(data[i]));
        }
        listViews.add(0, getImageView(data[data.length - 1]));
        listViews.add(getImageView(data[0]));
        return listViews;
    }

    private ImageView getImageView(int resId) {
        ImageView mImageView = new ImageView(this);
        mImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mImageView.setImageResource(resId);
        return mImageView;
    }

}
