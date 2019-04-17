package com.yiban.yiban_application.javabean;

public class Sys_info {

    private int info_id;
//    总访问数
    private String info_access;
//    动态总数
    private String info_trends;
//    点击率
    private String info_ctr;
//    近日访问数
    private String info_today_access;
//    未授权数
    private String info_no_gank;
//    已授权数
    private String info_yes_gank;

    private String info_into_time;

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public String getInfo_access() {
        return info_access;
    }

    public void setInfo_access(String info_access) {
        this.info_access = info_access;
    }

    public String getInfo_trends() {
        return info_trends;
    }

    public void setInfo_trends(String info_trends) {
        this.info_trends = info_trends;
    }

    public String getInfo_ctr() {
        return info_ctr;
    }

    public void setInfo_ctr(String info_ctr) {
        this.info_ctr = info_ctr;
    }

    public String getInfo_today_access() {
        return info_today_access;
    }

    public void setInfo_today_access(String info_today_access) {
        this.info_today_access = info_today_access;
    }

    public String getInfo_no_gank() {
        return info_no_gank;
    }

    public void setInfo_no_gank(String info_no_gank) {
        this.info_no_gank = info_no_gank;
    }

    public String getInfo_yes_gank() {
        return info_yes_gank;
    }

    public void setInfo_yes_gank(String info_yes_gank) {
        this.info_yes_gank = info_yes_gank;
    }

    public String getInfo_into_time() {
        return info_into_time;
    }

    public void setInfo_into_time(String info_into_time) {
        this.info_into_time = info_into_time;
    }
}
