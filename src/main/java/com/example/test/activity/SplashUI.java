package com.example.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.example.test.R;
import com.example.test.Utils.PreferencesUtils;

/**
 * Created by Administrator on 2016/7/21.
 */
public class SplashUI extends AppCompatActivity {
    private static final long ANIMATION_DURATION = 2000;//动画持续时间
    public static final String KEY_FIRST_ENTER = "first_enter";//第一次进入


    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_splash);

        initView();

    }

    private void initView() {
        mRootView = findViewById(R.id.splash_root);
        //动画
        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        //透明动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1f);
        //把动画添加到动画合集
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.setDuration(ANIMATION_DURATION);
        set.setAnimationListener(new SplashAnimationListener());
        mRootView.startAnimation(set);

    }

    /**
     * 动画的监听
     */
    public class SplashAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {


            new Thread() {


                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //查看是否是第一次进入
                    boolean flag = PreferencesUtils.getBoolean(getApplicationContext(), KEY_FIRST_ENTER, true);
                    //1.如果第一次进入做动画,接着进入引导界面
                    if (flag) {
                        Intent intent = new Intent(SplashUI.this, GuideUI.class);
                        startActivity(intent);
                    } else {
                        //2.否则进入主界面
                        Intent intent = new Intent(SplashUI.this, MainActivity.class);
                        startActivity(intent);
                    }
                    finish();

                }
            }.start();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
