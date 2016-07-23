package com.example.test.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.R;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends Fragment {
    private String mTitle;
    private String[] strs = {"哈哈", "哈韩", "哈日"};

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_simple_card, null);
        TextView card_title_tv = (TextView) v.findViewById(R.id.card_title_tv);
        ListView listView = (ListView) v.findViewById(R.id.list_item);
        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, strs));
        card_title_tv.setText(mTitle);
        return v;
    }


}