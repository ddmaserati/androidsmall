package com.ddmaserati.dd.ui;

import android.app.Activity;
import android.content.Intent;

import com.ddmaserati.dd.R;
import com.ddmaserati.dd.base.BaseActivity;

public class MainActivity extends BaseActivity {


    public static void startMain(Activity activity)
    {
        Intent intent = new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


}
