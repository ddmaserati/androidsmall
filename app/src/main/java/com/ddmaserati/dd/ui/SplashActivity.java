package com.ddmaserati.dd.ui;

import android.widget.ImageView;

import com.ddmaserati.dd.R;
import com.ddmaserati.dd.base.BaseActivity;
import com.ddmaserati.dd.http.Api;
import com.ddmaserati.dd.http.ApiClient;
import com.ddmaserati.dd.utlis.AppManager;
import com.ddmaserati.dd.utlis.SharePreUtil;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
//        ApiClient.retrofit().create(Api.class).getNews("1","2").subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
////                .compose()内存泄漏
//        .subscribe(new Observer<Object>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//        Call<Object> call = ApiClient.retrofit().create(Api.class).getNews("1", "3");
//        call.enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//
//            }
//        });

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
