package com.biotag.gamecock.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lxh on 2017/8/8.
 */

public class UserService {
    private static final String TAG = "UserService";
    public static final String PROKEY_HISTORY = "GameCockHistory";
    private volatile static UserService utils;
    private UserService(){}

    public static UserService getInstance() {
        if ( utils== null) {
            synchronized (UserService.class) {
                if (utils == null) {
                    utils = new UserService();
                }
            }
        }
        return utils;
    }

    public void saveUserHistoryLoginInfo(Context context, String username, String password){
        SharedPreferences.Editor logineditor = context.getSharedPreferences(PROKEY_HISTORY,0).edit();
        if(username!=null&&password!=null){
            logineditor.putString("username",username);
            logineditor.putString("password",password);
        }
        logineditor.commit();
    }
}
