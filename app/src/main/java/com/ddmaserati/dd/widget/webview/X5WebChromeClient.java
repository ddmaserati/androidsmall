package com.ddmaserati.dd.widget.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.tencent.smtt.export.external.interfaces.DownloadListener;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import static android.app.Activity.RESULT_OK;

/**
 * 描述：
 * 时间： 2019-10-09 16:02
 * 作者：ddmaserati
 */
public class X5WebChromeClient extends WebChromeClient {

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageForAndroid5;
    /**
     * 注意h5中调用上传图片，resultCode保持一致性
     */
    public static int FILE_CHOOSER_RESULT_CODE = 1;
    public static int FILE_CHOOSER_RESULT_CODE_5 = 2;

    private Activity mContext;

    private InterWebListenter webListenter;
    private VideoWebListenter videoWebListenter;
    private View customView;
    private IX5WebChromeClient.CustomViewCallback customViewCallback;
    private FullscreenHolder videoFullView;

    public X5WebChromeClient(Context context) {
        this.mContext = (Activity) context;
    }

    public void setWebListenter(InterWebListenter listenter) {
        this.webListenter = listenter;
    }

    public void setVideoWebListenter(VideoWebListenter videoWebListenter) {
        this.videoWebListenter = videoWebListenter;
    }

    @Override
    public void onProgressChanged(WebView webView, int progress) {
        super.onProgressChanged(webView, progress);
        if (webListenter != null) {
            webListenter.startProgress(progress);
            if (progress >= 100) {
                webListenter.hideProgressBar();
            }
        }
    }

    // 获取h5 标题
    @Override
    public void onReceivedTitle(WebView webView, String title) {
        super.onReceivedTitle(webView, title);
        if (TextUtils.isEmpty(title) || webListenter == null) {
            return;
        }

        webListenter.showTitle(title);
    }

    /**
     * 播放网络视频时全屏会被调用的方法，播放视频切换为横屏
     */
    @Override
    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback callback) {
       mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        videoWebListenter.hideWebView();
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }
        fullViewAddView(view);
        customView = view;
        customViewCallback = callback;
        videoWebListenter.showVideoFullView();
    }


    /**
     * 视频播放退出全屏会被调用的
     */
    @Override
    public void onHideCustomView() {
        if (customView == null) {
            // 不是全屏播放状态
            return;
        }
        if (mContext != null) {
            mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        customView.setVisibility(View.GONE);
        if (getVideoFullView() != null) {
            getVideoFullView().removeView(customView);
        }
        customView = null;
        if (videoWebListenter != null) {
            videoWebListenter.hideVideoFullView();
        }
        customViewCallback.onCustomViewHidden();
        if (videoWebListenter != null) {
            videoWebListenter.showWebView();
        }
    }


    /**
     * 添加view到decorView容齐中view
     */
    private void fullViewAddView(View view) {
        //增强逻辑判断，尤其是getWindow()
        if (mContext != null && mContext.getWindow() != null) {
            FrameLayout decor = (FrameLayout) mContext.getWindow().getDecorView();
            videoFullView = new FullscreenHolder(mContext);
            videoFullView.addView(view);
            decor.addView(videoFullView);
        }
    }

    /**
     * 获取视频控件view
     *
     * @return view
     */
    private FrameLayout getVideoFullView() {
        return videoFullView;
    }

    /**
     * 销毁的时候需要移除一下视频view
     */
    public void removeVideoView() {
        if (videoFullView != null) {
            videoFullView.removeAllViews();
        }
    }

    /**
     * 判断是否是全屏
     */
    public boolean inCustomView() {
        return (customView != null);
    }

    /**
     * 逻辑是：先判断是否全频播放，如果是，则退出全频播放
     * 全屏时按返加键执行退出全屏方法
     */
    public void hideCustomView() {
        this.onHideCustomView();
        if (mContext != null) {
            mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    //************************文件**********************/


    /**
     * 打开文件夹，扩展浏览器上传文件，3.0++版本
     */
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        openFileChooserImpl(uploadMsg);
    }

    /**
     * 3.0--版本
     */
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooserImpl(uploadMsg);
    }

    /**
     * 打开文件夹
     */
    @Override
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooserImpl(uploadMsg);
    }
    /**
     * For Android > 5.0
     */
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg,
                                     FileChooserParams fileChooserParams) {
        openFileChooserImplForAndroid5(uploadMsg);
        return true;
    }

    /**
     * 打开文件夹
     */
    private void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        if (mContext!=null){
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            mContext.startActivityForResult(
                    Intent.createChooser(i, "文件选择"), FILE_CHOOSER_RESULT_CODE);
        }
    }

    /**
     * 打开文件夹，Android5.0以上
     */
    private void openFileChooserImplForAndroid5(ValueCallback<Uri[]> uploadMsg) {
        if (mContext!=null){
            mUploadMessageForAndroid5 = uploadMsg;
            Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
            contentSelectionIntent.setType("image/*");
            Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
            chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
            chooserIntent.putExtra(Intent.EXTRA_TITLE, "图片选择");
            mContext.startActivityForResult(chooserIntent, FILE_CHOOSER_RESULT_CODE_5);
        }
    }

    //*********上传图片成功回调*******8

    /**
     * 5.0以下 上传图片成功后的回调
     */
    public void uploadMessage(Intent intent, int resultCode) {
        if (null == mUploadMessage) {
            return;
        }
        Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
        mUploadMessage.onReceiveValue(result);
        mUploadMessage = null;
    }

    /**
     * 5.0以上 上传图片成功后的回调
     */
    public void uploadMessageForAndroid5(Intent intent, int resultCode) {
        if (null == mUploadMessageForAndroid5) {
            return;
        }
        Uri result = (intent == null || resultCode != RESULT_OK) ? null : intent.getData();
        if (result != null) {
            mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
        } else {
            mUploadMessageForAndroid5.onReceiveValue(new Uri[]{});
        }
        mUploadMessageForAndroid5 = null;
    }
    DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onDownloadStart(String s, String s1, String s2, String s3, long l) {

        }

        @Override
        public void onDownloadStart(String s, String s1, byte[] bytes, String s2, String s3, String s4, long l, String s5, String s6) {

        }

        @Override
        public void onDownloadVideo(String s, long l, int i) {

        }
    };

}
