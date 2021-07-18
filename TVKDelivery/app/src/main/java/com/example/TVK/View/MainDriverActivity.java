package com.example.TVK.View;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.TVK.R;
import com.example.TVK.View.Fragment.ViewPagerAdapterDriver;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainDriverActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    Button btndangxuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);

        viewPager = findViewById(R.id.ViewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        ViewPagerAdapterDriver adapter= new ViewPagerAdapterDriver(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_thongbao).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_danhmuc).setChecked(true);
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
                    case R.id.menu_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_thongbao:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_danhmuc:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }
    private void anhxa()
    {
        btndangxuat = findViewById(R.id.btndangxuat);
    }
}
