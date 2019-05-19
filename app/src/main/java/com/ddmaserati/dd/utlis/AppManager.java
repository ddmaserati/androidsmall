package com.ddmaserati.dd.utlis;

import android.app.Activity;

import java.util.Stack;

/**
 * dec:  app管理
 * Created by ddmaserati
 * on 2019/4/9.
 */
public class AppManager {

    //存储ActivityStack
    private static Stack<Activity> activityStack = new Stack<>();

    private static AppManager instance;

    public static AppManager getInstance() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }


    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);

    }


    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 移除所有
     */
    public void removeAll() {
        if (null != activityStack && activityStack.size() > 0) {
            //创建临时集合对象
            Stack<Activity> activityTemp = new Stack<Activity>();
            for (Activity activity : activityStack) {
                if (null != activity) {
                    //添加到临时集合中
                    activityTemp.add(activity);
                    //结束Activity
                    activity.finish();
                }
            }
            activityStack.removeAll(activityTemp);
        }
        System.gc();
        System.exit(0);

    }

    /**
     * 获取当前Act对象
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (!activityStack.empty()){
            activity = activityStack.lastElement();
        }
        return activity;
    }


}
