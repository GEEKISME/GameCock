package com.biotag.gamecock.utiles;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.biotag.gamecock.foundation.GameCockApplication;


/**
 * Created by Lxh on 2017/7/11.
 */

public class UiUtils {
    public static Context getContext(){
        return GameCockApplication.getApplication();
    }
    public static Handler getHandler(){
        return GameCockApplication.getMainThreadHandler();
    }
    public static boolean postDelayed(Runnable runnable, long delayMillis){
        return getHandler().postDelayed(runnable,delayMillis);
    }
    /** 在主线程执行runnable */
    public static boolean post(Runnable runnable){
        return getHandler().post(runnable);
    }
    /** 从主线程looper里面移除runnable */
    public static void removeCallback(Runnable runnable){
        getHandler().removeCallbacks(runnable);
    }
    public static View inflate(int resId){
        return LayoutInflater.from(getContext()).inflate(resId,null);
    }
    public static Resources getResources(){
        return getContext().getResources();
    }
    public static String getString(int resId){ return getResources().getString(resId);}
    public static String [] getStringArray(int resId){return getResources().getStringArray(resId);}
    public static int getDimens(int resId){return getResources().getDimensionPixelSize(resId);}
    public static Drawable getDrawable(int resId){return getResources().getDrawable(resId);}
    public static int getColor(int resId){return getResources().getColor(resId);}
    public static ColorStateList getColorStateList(int resId){return getResources().getColorStateList(resId);}

    //dp 与 px 互转
    /** px 转dp */
    public static int px2dp(int px){
        float density = getResources().getDisplayMetrics().density;
        return (int)(px/density+0.5);
    }

    public static int dp2px(int dp){
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(dp*scale+0.5f);
    }

    public static long getMainThreadId(){
        return GameCockApplication.getmMainThreadId();
    }
    //判断当前线程是不是主线程
    public static boolean isRunInMainThread(){
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable){
        if(isRunInMainThread()){
            runnable.run();
        }else {
            post(runnable);
        }
    }
}
