package com.example.dmitry.quickbike.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitry.quickbike.R;


public class TemporaryFragment extends Fragment {

    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static TemporaryFragment newInstance(int page, String title) {
        TemporaryFragment fragmentFirst = new TemporaryFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temporary_fragment, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.tv_label);
        tvLabel.setText(page + " -- " + title);
        return view;
    }
}
