package com.example.test.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.test.R;
import com.example.test.Utils.DimenUtils;
import com.example.test.Utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class GuideUI extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final String KEY_FIRST_ENTER = "first_enter";
    private ViewPager mViewPager;
    private LinearLayout mPointContainer;
    private Button mBtn;
    private List<ImageView> mImageDatas;
    private int[] icons = {R.drawable.splash_1, R.drawable.splash_2, R.drawable.splash_3, R.drawable.splash_4, R.drawable.splash_5};
    private View mSelectedPoint;
    private int mPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_guide);

        //初始化view
        initView();

        //加载数据
        initData();
    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        mPointContainer = (LinearLayout) findViewById(R.id.guide_point_container);
        mBtn = (Button) findViewById(R.id.guide_btn);
        mSelectedPoint = findViewById(R.id.guide_selected);

        //设置图片的监听
//        mViewPager.setOnPageChangeListener(this);
        mViewPager.addOnPageChangeListener(this);

        //设置布局完成的回调
        mPointContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mPoint = mPointContainer.getChildAt(1).getLeft() - mPointContainer.getChildAt(0).getLeft();
                //移除布局
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mSelectedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mSelectedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
        mBtn.setOnClickListener(this);

    }


    private void initData() {
        mImageDatas = new ArrayList<ImageView>();
        for (int i = 0; i < icons.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(icons[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //添加到集合
            mImageDatas.add(iv);
            //动态的添加点
            View point = new View(this);
            point.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DimenUtils.dp2px(this, 10), DimenUtils.dp2px(this, 10));
            if (i != 0) {
                params.leftMargin = DimenUtils.dp2px(this, 10);
            }
            mPointContainer.addView(point, params);
        }

        mViewPager.setAdapter(new GuidePagerAdapter());

    }

    public class GuidePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mImageDatas != null) {
                return mImageDatas.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = mImageDatas.get(position);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //页面滑动的时候回调
        int margenleft = (int) (mPoint * positionOffset + mPoint * position + 0.5f);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSelectedPoint.getLayoutParams();
        params.leftMargin = margenleft;
        mSelectedPoint.setLayoutParams(params);

    }

    @Override
    public void onPageSelected(int position) {
        if (position == mImageDatas.size() - 1) {
            mBtn.setVisibility(View.VISIBLE);
        } else {
            mBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //按钮点击事件
    @Override
    public void onClick(View v) {
        if (v == mBtn) {
            clickButton();
        }
    }

    private void clickButton() {
        //页面跳转
        Intent intent = new Intent(GuideUI.this, MainActivity.class);
        startActivity(intent);
        //存储数据
        PreferencesUtils.putBoolean(getApplicationContext(), KEY_FIRST_ENTER, false);
    }
}
