package com.example.bao.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

/**
 * 首页分类ViewPager适配器
 */
public class CagegoryViewPagerAdapter extends PagerAdapter {
    private ViewPager viewPager;
    private List<View> mViewList;

    public  CagegoryViewPagerAdapter(List<View> mViewList,ViewPager viewPager){
        this.mViewList=mViewList;
        this.viewPager=viewPager;
    }
    @Override
    public int getCount() {
        if (mViewList==null){
            return 0;
        }else return  mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        viewPager.addView(mViewList.get(position));
        return mViewList.get(position);
    }
}




