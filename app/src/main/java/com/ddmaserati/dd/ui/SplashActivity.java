package com.ddmaserati.dd.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.ddmaserati.dd.R;
import com.ddmaserati.dd.base.BaseActivity;
import com.ddmaserati.dd.utlis.AppManager;
import com.ddmaserati.dd.utlis.SharePreUtil;
import com.ddmaserati.dd.utlis.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * dec:  启动图
 * Created by ddmaserati
 * on 2019/4/10.
 */
public class SplashActivity extends BaseActivity {


    @BindView(R.id.splash_image)
    ImageView splashImage;

    @Override
    public int getLayout() {
        return R.layout.activity_splash_layout;
    }

    @Override
    public void initView() {
        splashImage.setBackground(getResources().getDrawable(R.mipmap.splash));
    }

    @Override
    public void initData() {

        // 不是第一次使用app
        if (SharePreUtil.contains(SplashActivity.this, SharePreUtil.FIRST_TIME)) {
            MainActivity.startMain(SplashActivity.this);
            AppManager.getInstance().removeActivity(this);
        } else {
            SharePreUtil.put(SplashActivity.this, SharePreUtil.FIRST_TIME, SharePreUtil.FIRST_TIME);
            GuideActivity.startGuide(SplashActivity.this);
            AppManager.getInstance().removeActivity(this);
        }

    }


}
