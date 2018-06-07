package com.bnk.myedx;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);


        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);

        SimplePagerAdapter pagerAdapter =new SimplePagerAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager);

    }
}
