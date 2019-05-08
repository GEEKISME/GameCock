package com.biotag.gamecock.utiles;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ScaleXSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String TwitterCode(String strContent) { //
        String re;
        Pattern pattern = null;
        Matcher matcher = null;

        re = "[@](.[^\\[@|:]*)[:]";
        pattern = Pattern.compile(re);
        matcher = pattern.matcher(strContent);
        strContent = matcher
                .replaceAll("<a href=\"http://t.sina.cn/dpool/ttt/domain.php?n=$1\">@$1</a>:");

        re = "[#](.[^\\[#]*)[#]";
        pattern = Pattern.compile(re);
        matcher = pattern.matcher(strContent);
        strContent = matcher
                .replaceAll("<a href=\"http://t.sina.cn/dpool/ttt/hotword.php?keyword=$1\">#$1#</a>");
        return strContent;
    }

    public static String formatDigit(long digit){
        DecimalFormat df1 = new DecimalFormat("###");
        df1.setGroupingUsed(true);//或者不写
        return df1.format(digit) ;
    }

    /**
     * 等同new DecimalFormat("###")，部分android机无效
     * 将每三个数字加上逗号处理
     *
     * @param s 无逗号的数字
     * @return 加上逗号的数字
     */
    public static String addComma(String s) {

        // 将传进数字反转
        String reverseStr = new StringBuilder(s.substring(0, s.length())).reverse().toString();
        String strTemp = "";
        for (int i = 0; i < reverseStr.length(); i++) {
            if (i * 3 + 3 > reverseStr.length()) {
                strTemp += reverseStr.substring(i * 3, reverseStr.length());
                break;
            }
            strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
        }
        // 最后一个【,】去除
        if (strTemp.endsWith(",")) {
            strTemp = strTemp.substring(0, strTemp.length() - 1);
        }

        // 将数字重新反转
        String resultStr = new StringBuilder(strTemp).reverse().toString();
        return resultStr;

    }


    public static String HtmlCode(String strContent) {
        String re;
        Pattern pattern = null;
        Matcher matcher = null;
        re = "^((http|https|ftp|rtsp|mms):(\\/\\/|\\\\)[A-Za-z0-9\\./=\\?%\\-&_~`@':+!]+)";
        pattern = Pattern.compile(re);
        matcher = pattern.matcher(strContent);
        strContent = matcher.replaceAll("<a href=$1>$1</a>");
        re = "((http|https|ftp|rtsp|mms):(\\/\\/|\\\\)[A-Za-z0-9\\./=\\?%\\-&_~`@':+!]+)$";
        pattern = Pattern.compile(re);
        matcher = pattern.matcher(strContent);
        strContent = matcher.replaceAll("<a href=$1>$1</a>");
        re = "([^>=\"])((http|https|ftp|rtsp|mms):(\\/\\/|\\\\)[A-Za-z0-9\\./=\\?%\\-&_~`@':+!]+)";
        pattern = Pattern.compile(re);
        matcher = pattern.matcher(strContent);
        strContent = matcher.replaceAll("<a href=$2>$2</a>");

        // 自动识别www等开头的网址
        re = "([^(http://|http:\\\\)])((www|cn)[.](\\w)+[.]{1,}(net|com|cn|org|cc)(((\\/[\\~]*|\\[\\~]*)(\\w)+)|[.](\\w)+)*(((([?](\\w)+){1}[=]*))*((\\w)+){1}([\\&](\\w)+[\\=](\\w)+)*)*)";
        pattern = Pattern.compile(re);
        matcher = pattern.matcher(strContent);
        strContent = matcher.replaceAll("<a href=http://$2>$2</a>");

        // 自动识别Email地址，如打开本功能在浏览内容很多的帖子会引起服务器停顿
        re = "([^(=)])((\\w)+[@]{1}((\\w)+[.]){1,3}(\\w)+)";
        pattern = Pattern.compile(re);
        matcher = pattern.matcher(strContent);
        strContent = matcher.replaceAll("<a href=\"mailto:$2\">$2</a>");
        return strContent;
    }

    public static String replace(String source, String regex, String replacement) {
        int index = -1;
        StringBuffer buffer = new StringBuffer();
        while ((index = source.indexOf(regex)) >= 0) {
            buffer.append(source.substring(0, index));
            buffer.append(replacement);
            source = source.substring(index + regex.length());
        }
        buffer.append(source);
        return buffer.toString();
    }


    public static String urlEncode(String obj) {
        return urlEncode(obj, "GBK");
    }

    public static String urlEncode(String obj, String charset) {
        String result = null;
        if (obj != null) {
            try {
                result = URLEncoder.encode(obj, charset);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                return result;
            }
        }
        return result;
    }

    public static String urlDecode(String obj) {
        return urlDecode(obj, "GBK");
    }

    public static String urlDecode(String obj, String charset) {
        String result = null;
        if (obj != null) {
            try {
                result = URLDecoder.decode(obj, charset);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                return result;
            }
        }
        return result;
    }


    public static int numberOfStr(String str, String con) {
        str = " " + str;
        if (str.endsWith(con)) {
            return str.split(con).length;
        } else {
            return str.split(con).length - 1;
        }
    }


    public static String md5(String str) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buf.toString();
    }
    /*
       百度的对md5的签名
     */
    public static String creatBaiduSignInt(String md5) {
        if (md5 == null || md5.length() < 32) return "-1";
        String sign = md5.substring(8, 8 + 16);
        long id1 = 0;
        long id2 = 0;
        String s = "";
        for (int i = 0; i < 8; i++) {
            id2 *= 16;
            s = sign.substring(i, i + 1);
            id2 += Integer.parseInt(s, 16);
        }
        for (int i = 8; i < sign.length(); i++) {
            id1 *= 16;
            s = sign.substring(i, i + 1);
            id1 += Integer.parseInt(s, 16);
        }
        long id = (id1 + id2) & 0xFFFFFFFFL;
        return String.valueOf(id);
    }

    public static String getDomain(String url) {
        try {
            URI uri = new URI(url);
            return uri.getHost();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static String getRequestPath(String url) {
        try {
            URI uri = new URI(url);
            return uri.getHost() + uri.getPath();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static synchronized String getCMDExecuteResult(String[] cmd, String workdirectory) throws IOException {
        String result = "";
        InputStream in;
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);//设置一个路径
            if (workdirectory != null)
                builder.directory(new File(workdirectory));
            builder.redirectErrorStream(true);
            Process process = builder.start();
            in = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(isr);
            String line = "";
            //byte[] re = new byte[1024];
            while ((line = reader.readLine()) != null) {
                result = result + line + "\r\n";
            }
            in.close();
        } catch (Exception ex) {
//                ex.printStackTrace();
            return result;
        }
        return result;
    }

    public static String bSubstring(String s, int length) {
        try {
            byte[] bytes = s.getBytes("Unicode");
            int n = 0;
            int i = 2;
            for (; i < bytes.length && n < length; i++) {
                if (i % 2 == 1) {
                    n++;
                } else {
                    if (bytes[i] != 0) {
                        n++;
                    }
                }
            }

            if (i % 2 == 1) {
                if (bytes[i - 1] != 0)
                    i = i - 1;
                else
                    i = i + 1;

            }

            return new String(bytes, 0, i, "Unicode");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String genMachineCode(String token) {
        String result = "";
        try {
            String t = Long.toString(Long.valueOf(token.substring(0, 5)), 32);
            result = t + String.valueOf(token).substring(5, String.valueOf(token).length());
        } catch (Exception e) {
        }
        return result;
    }

    public static long genToken(String machineCode) {
        long result = 0;
        try {
            machineCode = machineCode.toLowerCase();
            String t = String.valueOf(Long.parseLong(machineCode.substring(0, 3), 32));
            result = Long.valueOf(t + machineCode.substring(3, machineCode.length()));
        } catch (Exception e) {
        }
        return result;
    }

    public static String convertStreamToString(InputStream is) {
        /*
        * To convert the InputStream to Personal we use the BufferedReader.readLine()
        * method. We iterate until the BufferedReader return null which means
        * there's no more data to read. Each line will appended to a StringBuilder
        * and returned as Personal.
        */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    public static String genKey(Context context, String content) {
        String s = "";
        PackageInfo pi = null;
        try {
            pi = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            s = StringUtil.md5(pi.signatures[0].toCharsString());
        } catch (Exception e) {
            s = "";
        }
         // s = "b1c7c4bfd3da4f33a9bdd1347ec1e8e9";//正式版时注释的
        if (content != null) {
            content = content.replaceAll("http", "");
            content = s + content;
            return md5(content);
        }
        return null;
    }


    /*
   * 中国移动134.135.136.137.138.139.150.151.152.157.158.159.187.188 ,147(数据卡不验证)
           中国联通130.131.132.155.156.185.186
           中国电信133.153.180.189
      CDMA   133,153
      网络号码 177
           手机号码验证 适合目前所有的手机
    */
    public static boolean checkMobile(String mobile) {
        String regex = "^1(3[0-9]|5[0-9]|8[0-9]|7[0-9])\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobile);
        return m.find();
    }

    /**
     * 验证验证码
     * @param captcha
     * @return
     */
    public static boolean checkCaptcha(String captcha) {
        String regex = "^\\d{6}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(captcha);
        return m.find();
    }

    public static String getCaptcha(String messageBody) {
        String captcha = null;
        String regex = "\\d{6}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(messageBody);
        while (m.find()) {
            captcha = m.group(0);
            break;
        }
        return captcha;
    }

    public static String urlDecoder(String obj) {
        return urlDecoder(obj, "gbk");
    }

    public static String urlDecoder(String obj, String charset) {
        String result = null;
        if (obj != null) {
            try {
                result = URLDecoder.decode(obj, charset);
            } catch (UnsupportedEncodingException e) {
                return result;
            }
        }
        return result;
    }

    public static String getConstellation(String birthday) {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(birthday);
            int month = date.getMonth() + 1;
            int day = date.getDate();
            switch (month) {
                case 1:
                    if (day <= 19) {
                        str = "魔蝎座";
                    } else {
                        str = "水瓶座";
                    }
                    break;
                case 2:
                    if (day <= 18) {
                        str = "水瓶座";
                    } else {
                        str = "双鱼座";
                    }
                    break;
                case 3:
                    if (day <= 20) {
                        str = "双鱼座";
                    } else {
                        str = "白羊座";
                    }
                    break;
                case 4:
                    if (day <= 19) {
                        str = "白羊座";
                    } else {
                        str = "金牛座";
                    }
                    break;
                case 5:
                    if (day <= 20) {
                        str = "金牛座";
                    } else {
                        str = "双子座";
                    }
                    break;
                case 6:
                    if (day <= 21) {
                        str = "双子座";
                    } else {
                        str = "巨蟹座";
                    }
                    break;
                case 7:
                    if (day <= 22) {
                        str = "巨蟹座";
                    } else {
                        str = "狮子座";
                    }
                    break;
                case 8:
                    if (day <= 22) {
                        str = "狮子座";
                    } else {
                        str = "处女座";
                    }
                    break;
                case 9:
                    if (day <= 22) {
                        str = "处女座";
                    } else {
                        str = "天秤座";
                    }
                    break;
                case 10:
                    if (day <= 23) {
                        str = "天秤座";
                    } else {
                        str = "天蝎座";
                    }
                    break;
                case 11:
                    if (day <= 22) {
                        str = "天蝎座";
                    } else {
                        str = "射手座";
                    }
                    break;
                case 12:
                    if (day <= 21) {
                        str = "射手座";
                    } else {
                        str = "魔蝎座";
                    }
                    break;
                default:
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;

    }

    public static String getDownAppPartner(String partner, int status) {
        String str = "";
        switch (status) {
            case 1:
                if (partner.equals("gamelist")) {
                    str = "gamelist_download_appname";
                } else if (partner.equals("relativeapp_partner")) {
                    str = "applist_download_appname";
                } else {
                    str = "gamelist_download_appname";
                }
                break;
            case 2:
                if (partner.equals("gamelist")) {
                    str = "gamelist_download_succ_appname";
                } else if (partner.equals("relativeapp_partner")) {
                    str = "applist_download_succ_appname";
                } else {
                    str = "gamelist_download_succ_appname";
                }
                break;
            case 3:
                if (partner.equals("gamelist")) {
                    str = "gamelist_click_appname";
                } else if (partner.equals("relativeapp_partner")) {
                    str = "applist_click_appname";
                } else {
                    str = "gamelist_click_appname";
                }
                break;
            default:
                break;
        }
        return str;
    }

//    public static String getDeviceId(Context context) {
//        try {
//            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//            WifiInfo info = wifi.getConnectionInfo();
//            String mac = info.getMacAddress();
//            if (mac == null) {
//                mac = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//            }
//            if (mac == null) {
//                Class<?> c = Class.forName("android.os.SystemProperties");
//                Method get = c.getMethod("get", String.class, String.class);
//                mac = (String) (get.invoke(c, "ro.serialno", "unknown"));
//            }
//            return mac;
//        } catch (Exception e) {
//            return "";
//        }
//    }

//    public static String getMac(Context context) {
//        String str = "";
//        try {
//            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//            WifiInfo info = wifi.getConnectionInfo();
//            str = info.getMacAddress();
//        } catch (Exception e) {
//            return "";
//        }
//        return str;
//    }

    /**
     * 优酷ID
     *
     * @param youkuurl
     * @return
     */
    public static String getYoukuId(String youkuurl) {
        if (youkuurl != null && youkuurl.indexOf("id_") != -1 && youkuurl.indexOf(".html") != -1) {
            return youkuurl.substring(youkuurl.lastIndexOf("id_") + 3, youkuurl.lastIndexOf(".html"));
        }
        return null;
    }


    public static String formatPlayCount(long playcount) {

        double y = playcount / 100000000f;
        if (playcount / 100000000 > 0) {
            return round(y, 2) + "亿";
        }
        double w = playcount / 10000f;
        if (playcount / 10000 > 0) {
            return round(w, 2) + "万";
        }

        DecimalFormat df1 = new DecimalFormat("###");
        df1.setGroupingUsed(true);//或者不写
        return df1.format(playcount) ;

//        double q = playcount/1000f;
//        if(playcount/1000>0){
//            return round(q,2)+"千";
//        }
       // return Personal.valueOf(playcount);
    }

    public static double round(double v, int scale) {
        String temp = "####.";
        for (int i = 0; i < scale; i++) {
            temp += "0";
        }
        return Double.valueOf(new java.text.DecimalFormat(temp).format(v));
    }

    public static byte[] decode(final byte[] bytes) {
        return Base64.decode(bytes, 2);
    }

    public static String encode(final byte[] bytes) {
        return new String(Base64.encode(bytes, 2));
    }

    public static String formatDate(int hour) {
        String src = "";
        if (hour >= 23 || hour < 1) {
            src = "子时";
        } else if (hour >= 1 && hour < 3) {
            src = "丑时";
        } else if (hour >= 3 && hour < 5) {
            src = "寅时";
        } else if (hour >= 5 && hour < 7) {
            src = "卯时";
        } else if (hour >= 7 && hour < 9) {
            src = "辰时";
        } else if (hour >= 9 && hour < 11) {
            src = "巳时";
        } else if (hour >= 11 && hour < 13) {
            src = "午时";
        } else if (hour >= 13 && hour < 15) {
            src = "未时";
        } else if (hour >= 15 && hour < 17) {
            src = "申时";
        } else if (hour >= 17 && hour < 19) {
            src = "酉时";
        } else if (hour >= 19 && hour < 21) {
            src = "戌时";
        } else if (hour >= 21 && hour < 23) {
            src = "亥时";
        }
        return src;
    }

    public static String formatDate1(int position) {
        String src = "";
        switch (position) {
            case 0:
                src = "子时";
                break;
            case 1:
                src = "丑时";
                break;
            case 2:
                src = "寅时";
                break;
            case 3:
                src = "卯时";
                break;
            case 4:
                src = "辰时";
                break;
            case 5:
                src = "巳时";
                break;
            case 6:
                src = "午时";
                break;
            case 7:
                src = "未时";
                break;
            case 8:
                src = "申时";
                break;
            case 9:
                src = "酉时";
                break;
            case 10:
                src = "戌时";
                break;
            case 11:
                src = "亥时";
                break;
        }
        return src;
    }

    public static int formatDateIndex(int hour) {
        int position = -1;
        if (hour >= 23 && hour < 1) {
            position = 0;
        } else if (hour >= 1 && hour < 3) {
            position = 1;
        } else if (hour >= 3 && hour < 5) {
            position = 2;
        } else if (hour >= 5 && hour < 7) {
            position = 3;
        } else if (hour >= 7 && hour < 9) {
            position = 4;
        } else if (hour >= 9 && hour < 11) {
            position = 5;
        } else if (hour >= 11 && hour < 13) {
            position = 6;
        } else if (hour >= 13 && hour < 15) {
            position = 7;
        } else if (hour >= 15 && hour < 17) {
            position = 8;
        } else if (hour >= 17 && hour < 19) {
            position = 9;
        } else if (hour >= 19 && hour < 21) {
            position = 10;
        } else if (hour >= 21 && hour < 23) {
            position = 11;
        }
        return position;
    }

    public static int getScreenHeight(Activity paramActivity) {
        Display display = paramActivity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getStatusBarHeight(Activity paramActivity) {
        Rect localRect = new Rect();
        paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        return localRect.top;
    }

    public static int getAppHeight(Activity paramActivity) {
        Rect localRect = new Rect();
        paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        return localRect.height();
    }

//    public static int getKeyboardHeight(Activity paramActivity) {
//
//        int height = getScreenHeight(paramActivity)
//                - getStatusBarHeight(paramActivity)
//                - getAppHeight(paramActivity);
//        //如果是魅族手机 并且smartbar显示时
//        if (hasSmartBar() && paramActivity.getActionBar() != null && paramActivity.getActionBar().isShowing()) {
//            height -= paramActivity.getResources().getDimension(R.dimen.mx_keyboard_margin);
//        }
//
//        return height;
//    }

    public static String getBindPhone(String phone){
        return phone.substring(0,3) + "****" + phone.substring(7, phone.length());
    }

//    public static int getAppContentHeight(Activity paramActivity) {
//        return StringUtil.getScreenHeight(paramActivity)
//                - StringUtil.getStatusBarHeight(paramActivity)
//                - StringUtil.getActionBarHeight(paramActivity)
//                - StringUtil.getKeyboardHeight(paramActivity);
//    }

    public static int getActionBarHeight(Activity paramActivity) {
        //test on samsung 9300 android 4.1.2, this value is 96px
        //but on galaxy nexus android 4.2, this value is 146px
        //statusbar height is 50px
        //I guess 4.1 Window.ID_ANDROID_CONTENT contain statusbar
        int contentViewTop =
                paramActivity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();

//        return contentViewTop - getStatusBarHeight(paramActivity);

        return getDimensionPixelSize(paramActivity, android.R.attr.actionBarSize,
                dip2px(paramActivity, 48));
    }

    public static int getDimensionPixelSize(Activity activity, int attr, int defaultValue) {
        int[] attrs = new int[]{attr};
        TypedArray ta = activity.obtainStyledAttributes(attrs);
        int value = ta.getDimensionPixelSize(0, defaultValue);
        ta.recycle();
        return value;
    }

    public static int dip2px(Context activity, int dipValue) {
        float reSize = activity.getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }

    public static boolean hasSmartBar() {
        try {
            Method method = Class.forName("android.os.Build").getMethod("hasSmartBar");
            return ((Boolean) method.invoke(null)).booleanValue();
        } catch (Exception e) {
        }

        if (Build.DEVICE.equals("mx2")) {
            return true;
        } else if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) {
            return false;
        }
        return false;
    }

    public static boolean isEmulator() {
        int rating = -1;
        if (rating < 0) { // rating is not calculated yet
            int newRating = 0;
            if (Build.PRODUCT.equals("sdk") ||
                    Build.PRODUCT.equals("google_sdk") ||
                    Build.PRODUCT.equals("sdk_x86") ||
                    Build.PRODUCT.equals("vbox86p")) {
                newRating++;
            }

            if (Build.MANUFACTURER.equals("unknown") ||
                    Build.MANUFACTURER.equals("Genymotion")) {
                newRating++;
            }

            if (Build.BRAND.equals("generic") ||
                    Build.BRAND.equals("generic_x86")) {
                newRating++;
            }

            if (Build.DEVICE.equals("generic") ||
                    Build.DEVICE.equals("generic_x86") ||
                    Build.DEVICE.equals("vbox86p")) {
                newRating++;
            }

            if (Build.MODEL.equals("sdk") ||
                    Build.MODEL.equals("google_sdk") ||
                    Build.MODEL.equals("Android SDK built for x86")) {
                newRating++;
            }

            if (Build.HARDWARE.equals("goldfish") ||
                    Build.HARDWARE.equals("vbox86")) {
                newRating++;
            }

            if (Build.FINGERPRINT.contains("generic/sdk/generic") ||
                    Build.FINGERPRINT.contains("generic_x86/sdk_x86/generic_x86") ||
                    Build.FINGERPRINT.contains("generic/google_sdk/generic") ||
                    Build.FINGERPRINT.contains("generic/vbox86p/vbox86p")) {
                newRating++;
            }

            rating = newRating;
        }
        return rating > 4;
    }

    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static String generateTime(long time) {    //格式化时间 只显示 mm:ss
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        minutes =hours*60+minutes;
        return hours > 1 ? String.format("%03d:%02d",minutes, seconds) : String.format("%02d:%02d",minutes, seconds);
    }

    /**
     * 汉字半角转为全角
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i< c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }if (c[i]> 65280&& c[i]< 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static String formatSum(int sum) {

        double y = sum / 100000000f;
        if (sum / 100000000 > 0) {
            return round(y, 2) + "亿";
        }
        double w = sum / 10000f;
        if (sum / 10000 > 0) {
            return round(w, 2) + "万";
        }

        DecimalFormat df1 = new DecimalFormat("###");
        df1.setGroupingUsed(true);//或者不写
        return df1.format(sum) ;
    }

    public static String liveTime(long time){
        double hours = (double) (time / 60.0) /60;
        return hours>=10?new DecimalFormat("##").format(hours):new DecimalFormat("0.0").format(hours);
    };

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String formatSumInt(int sum) {

        int w = sum / 10000;
        if (sum / 10000 > 0) {
            return w + "W";
        }

        DecimalFormat df1 = new DecimalFormat("###");
        df1.setGroupingUsed(true);//或者不写
        return df1.format(sum) ;
    }


    /**
     * 将给定的字符串给定的长度两端对齐
     *
     * @param str 待对齐字符串
     * @param size 汉字个数，eg:size=5，则将str在5个汉字的长度里两端对齐
     * @Return
     */
    public static SpannableStringBuilder justifyString(String str, int size) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (TextUtils.isEmpty(str)) {
            return spannableStringBuilder;
        }
        char[] chars = str.toCharArray();
        if (chars.length >= size || chars.length == 1) {
            return spannableStringBuilder.append(str);
        }
        int l = chars.length;
        float scale = (float) (size - l) / (l - 1);
        for (int i = 0; i < l; i++) {
            spannableStringBuilder.append(chars[i]);
            if (i != l - 1) {
                SpannableString s = new SpannableString("　");//全角空格
                s.setSpan(new ScaleXSpan(scale), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(s);
            }
        }
        return spannableStringBuilder;
    }


    /**
     * 两端对齐格式文字
     * 仅全中文
     * @param str
     * @return
     */
    public static String formatAlignString(String str){
        switch (str.length()){
            case 2:
                return str.charAt(0)+"\u3000\u3000"+str.charAt(1);
            case 3:
                return str.charAt(0)+"\u0020\u0020"+str.charAt(1)+"\u0020\u0020"+str.charAt(2);
            default:
                return str;
        }
    }

    /**
     * 去除字符串两端空白字符,(\n,\s,\t,..)
     * @param rawStr
     * @return
     */
    public static String removeBothEndWhitespace(String rawStr){

        if (null == rawStr || TextUtils.isEmpty(rawStr)) return null;

        int start = 0;
        int end = 0;
        char[] chars = rawStr.toCharArray();

        for(int i = 0;i<chars.length;i++){
            if(!Character.isWhitespace(chars[i])){
                start = i;
                break;
            }
        }

        for(int i= chars.length-1;i>=0;i--){
            if(!Character.isWhitespace(chars[i])){
                end = i;
                break;
            }
        }

        if(0==end && Character.isWhitespace(chars[end])){
            return null;
        }

        return rawStr.substring(start, end + 1);

    }

    /**
     * 保留小数点后两位
     * @param value
     * @return
     */
    public static String formatDecimal2digit(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }


    /**
     * 检测字符串是否有emoji字符
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (TextUtils.isEmpty(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (!isEmojiCharacter(codePoint)) {
                // do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 格式化单价显示
     * 当价格有小数位时，显示两位小数
     * 当价格为整数，千分位处理
     * @return
     */
    public static String formatPrice(int price){
        if(0 !=price % 100){
            return String.valueOf(price/100.0f);
        }

        return addComma(String.valueOf(price/100));
    }



}