package com.ddmaserati.dd.ui;

import android.app.Activity;
import android.content.Intent;

import com.ddmaserati.dd.R;
import com.ddmaserati.dd.base.BaseActivity;


/**
 * dec:  引导图
 * Created by ddmaserati
 * on 2019/4/10.
 */
public class GuideActivity extends BaseActivity {





    public static void startGuide(Activity activity) {
        Intent intent = new Intent(activity, GuideActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_guide_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }




}
