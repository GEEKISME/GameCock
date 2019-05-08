package com.biotag.gamecock.foundation;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;
import android.os.Handler;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lxh on 2017/8/2.
 */

public class GameCockApplication extends Application {

    protected static GameCockApplication mInstance;
    private static Handler mMainThreadHandler;
    private static int mMainThreadId;
    private List<Activity> mActivityList = new LinkedList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        mInstance = this;
        mMainThreadHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
        //创建一个GameCock文件夹，用来存储缓存数据
        File dir  = new File(Environment.getExternalStorageDirectory()+"/GameCock/");
        if(!dir.exists()){
            dir.mkdirs();
        }
    }

    public static GameCockApplication getApplication(){
        return mInstance;
    }
    public static Handler getMainThreadHandler(){
        return mMainThreadHandler;
    }
    public static int getmMainThreadId(){
        return mMainThreadId;
    }
}
