package com.example.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.test.R;
import com.example.test.Utils.ViewFindUtils;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/23.
 */
public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"前端", "后端", "设计", "工具资源"};
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (String title : mTitles) {
            if ("前端" == title) {
                mFragments.add(SimpleCardFragment.getInstance(title));
            } else {
                mFragments.add(SimpleFragment.getInstance(title));
            }
        }

        View decorView = getWindow().getDecorView();
        ViewPager viewPager = ViewFindUtils.find(decorView, R.id.vp);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);

        SlidingTabLayout tabLayout_9 = ViewFindUtils.find(decorView, R.id.tl_10);
        tabLayout_9.setViewPager(viewPager);

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
