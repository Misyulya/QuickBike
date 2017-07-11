package com.example.dmitry.quickbike.activity;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitry.quickbike.R;
import com.example.dmitry.quickbike.adapter.MyPagerAdapter;
import com.example.dmitry.quickbike.db.BikeDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends AppCompatActivity {

    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.sliding_tabs) TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        setUpViewPager();
    }

    private void setUpViewPager() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
