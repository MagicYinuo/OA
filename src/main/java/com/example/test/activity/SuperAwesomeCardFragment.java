package com.example.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.test.R;

/**
 * Created by Administrator on 2016/7/22.
 */
public class SuperAwesomeCardFragment extends android.support.v4.app.Fragment {
    private static final String ARG_POSITION = "position";
    private int position;

    public static SuperAwesomeCardFragment newInstance(int position) {
        SuperAwesomeCardFragment sf = new SuperAwesomeCardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        sf.setArguments(bundle);
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        FrameLayout layout = new FrameLayout(getActivity());
        layout.setLayoutParams(params);
        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        params.setMargins(margin, margin, margin, margin);
        TextView tv = new TextView(getActivity());
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        layout.setBackgroundResource(R.drawable.ddd);
        tv.setText("界面" + (position + 1));
        return layout;
    }
}
