package com.ddmaserati.dd.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ddmaserati.dd.R;
import com.ddmaserati.dd.base.BaseActivity;
import com.ddmaserati.dd.ui.fragment.AFragment;
import com.ddmaserati.dd.ui.fragment.BFragment;
import com.ddmaserati.dd.ui.fragment.HomeFragment;
import com.ddmaserati.dd.ui.fragment.ProfileFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private HomeFragment homeFragment;
    private AFragment aFragment;
    private BFragment bFragment;
    private ProfileFragment profileFragment;

    public static void startMain(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void initData() {
        navigation.setSelectedItemId(R.id.navigation_home);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (item.getItemId()) {
            case R.id.navigation_home:
               onTabSelect(0);
                break;
            case R.id.navigation_dashboard:
                onTabSelect(1);
                break;
            case R.id.navigation_notifications:
               onTabSelect(2);
                break;
            default:
                break;
        }
        return true;
    }


    private void onTabSelect(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.frame_layout, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (aFragment == null) {
                    aFragment = new AFragment();
                    transaction.add(R.id.frame_layout, aFragment);
                } else {
                    transaction.show(aFragment);
                }
                break;
            case 2:
                if (bFragment == null) {
                    bFragment = new BFragment();
                    transaction.add(R.id.frame_layout, bFragment);
                } else {
                    transaction.show(bFragment);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }


    private void hideAllFragment(android.support.v4.app.FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (aFragment != null) {
            transaction.hide(aFragment);
        }
        if (bFragment != null) {
            transaction.hide(bFragment);
        }
        if (profileFragment != null) {
            transaction.hide(profileFragment);
        }
    }
}
