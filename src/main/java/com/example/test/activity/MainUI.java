package com.example.test.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import com.astuetz.PagerSlidingTabStrip;
import com.example.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainUI extends AppCompatActivity {


    private static final String TAG = "MainUI";
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.pager)
    ViewPager pager;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
        ButterKnife.bind(this);
        //加载数据
        initData();
    }

    private void initData() {

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(myPagerAdapter);
        final int pagerMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pagerMargin);
        tabs.setViewPager(pager);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private final String TITLES[] = {"消息", "工作", "联系人", "我的", "设置"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            return SuperAwesomeCardFragment.newInstance(position);
        }

        // TODO: 2016/7/22 此处出现bug 返回了空
        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
