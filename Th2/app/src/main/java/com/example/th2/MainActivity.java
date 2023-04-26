package com.example.th2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.th2.adapter.FragmentAdapter;
import com.example.th2.model.Cat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        viewPager = findViewById(R.id.viewPage);
        adapter = new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_search);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_view_list);

        bottomNavigationView = findViewById(R.id.navigation);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.bt_home).setCheckable(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.bt_search).setCheckable(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.bt_list).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bt_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bt_search:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.bt_list:
                        viewPager.setCurrentItem(2);
                        break;

                }
                return true;
            }
        });

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityAdd.class);
            startActivity(intent);
        });
    }
}