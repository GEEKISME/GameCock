package com.biotag.gamecock.javabean;

/**
 * Created by Lxh on 2017/8/2.
 * 斗鸡基本信息Bean
 */

public class ChanticleerBean {
    private String serialnumber;//鸡的编号
    private String chipnumber;//芯片编号
    private String catogry;//品种
    private String birthday;//出生日期
    private String location;//产地
    private String imgUrl;//鸡的照片
    private String currentstate;//该鸡当前的状态：报失，淘汰，正常

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getChipnumber() {
        return chipnumber;
    }

    public void setChipnumber(String chipnumber) {
        this.chipnumber = chipnumber;
    }

    public String getCatogry() {
        return catogry;
    }

    public void setCatogry(String catogry) {
        this.catogry = catogry;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCurrentstate() {
        return currentstate;
    }

    public void setCurrentstate(String currentstate) {
        this.currentstate = currentstate;
    }
}
