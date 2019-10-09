package com.ddmaserati.dd.widget.webview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ddmaserati.dd.widget.ProgressView;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.Map;

import static android.os.Build.VERSION_CODES.KITKAT;

/**
 * 描述： 封装webview，基于腾讯x5内核
 * 时间： 2019-10-09 11:22
 * 作者：ddmaserati
 */
public class X5Webview extends WebView {

    private static final String TAG = "x5webview";
    int progressColor = 0xFFFF4081;
    private ProgressView mProgressview; //自定义WebView加载进度条
    private TextView titleView; // h5标题
    private Context context;

    public X5Webview(Context context) {
        super(context);
        this.context = context;
        initWebViewSettings();//初始化setting配置
    }

    public X5Webview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        initWebViewSettings();
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        this.getView().setClickable(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        WebSettings webSetting = this.getSettings(); //设置可以点击

        webSetting.setJavaScriptEnabled(true);//允许js调用
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        webSetting.setAllowFileAccess(true);//在File域下，能够执行任意的JavaScript代码，同源策略跨域访问能够对私有目录文件进行访问等
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//控制页面的布局(使所有列的宽度不超过屏幕宽度)
        webSetting.setSupportZoom(true);//支持页面缩放
        webSetting.setBuiltInZoomControls(true);//进行控制缩放
        webSetting.setAllowContentAccess(true);//是否允许在WebView中访问内容URL（Content Url），默认允许
        webSetting.setUseWideViewPort(true);//设置缩放密度
        webSetting.setSupportMultipleWindows(false);//设置WebView是否支持多窗口,如果为true需要实现onCreateWindow(WebView, boolean, boolean, Message)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //两者都可以
            webSetting.setMixedContentMode(webSetting.getMixedContentMode());//设置安全的来源
        }
        webSetting.setAppCacheEnabled(true);//设置应用缓存
        webSetting.setDomStorageEnabled(true);//DOM存储API是否可用
        webSetting.setGeolocationEnabled(true);//定位是否可用
        webSetting.setLoadWithOverviewMode(true);//是否允许WebView度超出以概览的方式载入页面，
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);//设置应用缓存内容的最大值
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);//设置是否支持插件
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);//重写使用缓存的方式
        webSetting.setAllowUniversalAccessFromFileURLs(true);//是否允许运行在一个file schema URL环境下的JavaScript访问来自其他任何来源的内容
        webSetting.setAllowFileAccessFromFileURLs(true);//是否允许运行在一个URL环境
        if(Build.VERSION.SDK_INT >= KITKAT) {
            //设置网页在加载的时候暂时不加载图片
            webSetting.setLoadsImagesAutomatically(true);
        } else {
            webSetting.setLoadsImagesAutomatically(false);
        }
    }

    //    进度条
    private void initProgressBar() {
        mProgressview = new ProgressView(context);
        mProgressview.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 6));
        mProgressview.setDefaultColor(progressColor);
        addView(mProgressview);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && this.canGoBack())
        {
            this.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 客户端配置
    private WebViewClient client = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
        }

        @Override
        public void onPageFinished(WebView webView, String url) {

            if(mProgressview != null)
            {
                mProgressview.setVisibility(GONE);
            }
            //处理客户端与WebView同步
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            String endCookie = cookieManager.getCookie(url);
            Log.i(TAG, "onPageFinished: endCookie : " + endCookie);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                CookieSyncManager.getInstance().sync();//同步cookie
            } else {
                CookieManager.getInstance().flush();
            }
            super.onPageFinished(webView, url);
        }


        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            super.onReceivedError(webView, i, s, s1);
            //网页问题报错的时候执行
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            if (sslError.getPrimaryError() == android.net.http.SslError.SSL_INVALID) {// 校验过程遇到了bug
                //这里直接忽略ssl证书的检测出错问题，选择继续执行页面
                sslErrorHandler.proceed();
            } else {
                //不是证书问题时候则停止执行加载页面
                sslErrorHandler.cancel();
            }
        }
    };

    ////当前涉及调用拍照、摄像功能，需要重新设置WebChromeClient
    private WebChromeClient picClient = new WebChromeClient()
    {
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        }

        public void openFileChooser(ValueCallback<Uri> uploadMsgs) {
        }

        // For Android  > 4.1.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        }

        // For Android  >= 5.0 该项目需求是在5.0之上开发、所以5.0以下不作处理
        public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView,
                                         ValueCallback<Uri[]> filePathCallback,
                                         final WebChromeClient.FileChooserParams fileChooserParams) {
//            uploadFiles = filePathCallback;
//            new ActionSheetDialog(BrowserActivity.this)
//                    .builder(uploadFile,uploadFiles)//这里是重点!!!,需要传入uploadFile,uploadFiles进行判断处理
//                    .setCancelable(true) //取消键
//                    .setCanceledOnTouchOutside(true)//空白地方取消dialog
//                    .addSheetItem("上传照片",
//                            ActionSheetDialog.SheetItemColor.Blue,
//                            new ActionSheetDialog.OnSheetItemClickListener() {
//                                @Override
//                                public void onClick(int which) {
//                                    take();
//                                }
//                            })
//                    .addSheetItem("上传视频",
//                            ActionSheetDialog.SheetItemColor.Blue,
//                            new ActionSheetDialog.OnSheetItemClickListener() {
//                                @Override
//                                public void onClick(int which) {
//                                    Toast.makeText(BrowserActivity.this, "调用视频", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//                                    //限制时长
//                                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
//                                    //开启摄像机
//                                    startActivityForResult(intent, 101);
//                                }
//                            })
//                    //                    .addSheetItem("调用相册",
//                            ActionSheetDialog.SheetItemColor.Blue,
//                            new ActionSheetDialog.OnSheetItemClickListener() {
//                                @Override
//                                public void onClick(int which) {
//                                    Toast.makeText(BrowserActivity.this, "调用相册", Toast.LENGTH_SHORT).show();
//                                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                                    i.addCategory(Intent.CATEGORY_OPENABLE);
//                                    i.setType("image/*");
//                                    startActivityForResult(Intent.createChooser(i, "选择相册"), 102);
//                                }
//                    })
//                    .show();
            return true;
        }

    };


    // x5浏览器配置视频播放、文件下载等
    private WebChromeClient videoClient = new WebChromeClient() {


        /**
         *   获取h5标题
         */
        @Override
        public void onReceivedTitle(WebView webView, String title) {
           if(titleView != null && !TextUtils.isEmpty(title))
           {
               titleView.setText(title);
           }
        }

        // 监听进度条
        @Override
        public void onProgressChanged(WebView webView, int progress) {

            if(mProgressview != null)
            {
                mProgressview.setProgress(progress);
                if(progress != 100)
                {
                    mProgressview.setVisibility(VISIBLE);
                }
                else
                {
                    mProgressview.setVisibility(GONE);
                }
            }
            super.onProgressChanged(webView, progress);
        }
    };


    // 下载监听器
    DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onDownloadStart(final String url, String userAgent, String contentDisposition, String minetype, long contentLength) {

            new AlertDialog.Builder(context)
                    .setTitle("allow to download？")
                    .setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Toast.makeText(
                                            context,
                                            "fake message: i'll download...",
                                            Toast.LENGTH_LONG).show();
// 下载url内容
//                                    Uri uri = Uri.parse(url);
//                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                                    context.startActivity(intent);
                                }
                            })
                    .setNegativeButton("no",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Toast.makeText(
                                            context,
                                            "fake message: refuse download...",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                    .setOnCancelListener(
                            new DialogInterface.OnCancelListener() {

                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    Toast.makeText(
                                            context,
                                            "fake message: refuse download...",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }).show();
        }
    };


}
