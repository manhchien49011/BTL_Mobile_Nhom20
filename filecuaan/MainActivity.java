package com.example.btl_nhom20;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        viewPager2 = findViewById(R.id.view_pager2);

        // set adapter cho viewpager2
        ViewPager2Adapter adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);

        //khi vuốt qua lại giữa các fragment thì các tab bottom navigation thay đổi tương ứng
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigation.getMenu().findItem(R.id.bottom_navigation_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigation.getMenu().findItem(R.id.bottom_navigation_project).setChecked(true);
                        break;
                    case 2:
                        bottomNavigation.getMenu().findItem(R.id.bottom_navigation_workplace).setChecked(true);
                        break;
                    case 3:
                        bottomNavigation.getMenu().findItem(R.id.bottom_navigation_account).setChecked(true);
                        break;
                }
            }
        });

        // khi click vào item tab bottom navigation thì call fragment tương ứng
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_navigation_home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.bottom_navigation_project:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.bottom_navigation_workplace:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.bottom_navigation_account:
                        viewPager2.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        //Runtime permissions
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
    }

}