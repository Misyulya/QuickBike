package com.example.dmitry.quickbike.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitry.quickbike.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TemporaryFragment extends BaseFragment {

    private Unbinder mUnbinder;
    private String title;
    private int page;

    @BindView(R.id.tv_label) TextView tvLabel;

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
        mUnbinder = ButterKnife.bind(this, view);
        tvLabel.setText(page + " -- " + title);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
