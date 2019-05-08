package com.biotag.gamecock.common;

/**
 * Created by Lxh on 2017/8/3.
 */

public class Constants {
    public static final boolean SHOW_TITLEBAR = true;

    public static final String LOGIN_URL = "";

    public static final String COCK_INFO_URL = "";

    public static final String HOST = "http://211.152.45.196:8123/";

    /**
     * 用户登录
     **/
    public static final String USR_LOGIN_URL = HOST + "api/Owners/Login?username={username}&password={password}";
    /**
     * 由ID 得到斗鸡的信息
     */
    public static final String GET_COCK_INFO_URL = HOST + "api/Cocks/Get?id={id}";

}

