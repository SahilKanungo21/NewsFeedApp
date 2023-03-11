package com.application.newsfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.application.newsfeed.Fragments.PagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem mhome , msports , mhealth, mscience , mtechnology, mentertainment;
    PagerAdapter pagerAdapter;
    Toolbar mtoolBar;

    private boolean filterConditions(int tabPos) {
        return (tabPos==0) || (tabPos==1) || (tabPos==2) || (tabPos==3) || (tabPos==4) || (tabPos ==5);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolBar);

        mhome = findViewById(R.id.Home);
        mscience = findViewById(R.id.Science);
        mentertainment = findViewById(R.id.Entertainment);
        mhealth = findViewById(R.id.Health);
        msports = findViewById(R.id.Sport);
        mtechnology = findViewById(R.id.Technology);

        ViewPager viewPager = findViewById(R.id.fragmentcontainer);
        tabLayout = findViewById(R.id.include);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                int tabPos = tab.getPosition();
                if(filterConditions(tabPos)) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}