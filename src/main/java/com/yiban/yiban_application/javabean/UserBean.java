package com.yiban.yiban_application.javabean;

public class UserBean {
    private String id;
    private String yb_username;
    private String yb_usernick;
    private String yb_sex;
    private String yb_money;
    private String yb_exp;
    private String yb_userhead;
    private String yb_schoolid;
    private String yb_schoolname;
    private String yb_regtime;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYb_username() {
        return yb_username;
    }

    public void setYb_username(String yb_username) {
        this.yb_username = yb_username;
    }

    public String getYb_usernick() {
        return yb_usernick;
    }

    public void setYb_usernick(String yb_usernick) {
        this.yb_usernick = yb_usernick;
    }

    public String getYb_sex() {
        return yb_sex;
    }

    public void setYb_sex(String yb_sex) {
        this.yb_sex = yb_sex;
    }

    public String getYb_money() {
        return yb_money;
    }

    public void setYb_money(String yb_money) {
        this.yb_money = yb_money;
    }

    public String getYb_exp() {
        return yb_exp;
    }

    public void setYb_exp(String yb_exp) {
        this.yb_exp = yb_exp;
    }

    public String getYb_userhead() {
        return yb_userhead;
    }

    public void setYb_userhead(String yb_userhead) {
        this.yb_userhead = yb_userhead;
    }

    public String getYb_schoolid() {
        return yb_schoolid;
    }

    public void setYb_schoolid(String yb_schoolid) {
        this.yb_schoolid = yb_schoolid;
    }

    public String getYb_schoolname() {
        return yb_schoolname;
    }

    public void setYb_schoolname(String yb_schoolname) {
        this.yb_schoolname = yb_schoolname;
    }

    public String getYb_regtime() {
        return yb_regtime;
    }

    public void setYb_regtime(String yb_regtime) {
        this.yb_regtime = yb_regtime;
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", yb_username='" + yb_username + '\'' +
                ", yb_usernick='" + yb_usernick + '\'' +
                ", yb_sex='" + yb_sex + '\'' +
                ", yb_money='" + yb_money + '\'' +
                ", yb_exp='" + yb_exp + '\'' +
                ", yb_userhead='" + yb_userhead + '\'' +
                ", yb_schoolid='" + yb_schoolid + '\'' +
                ", yb_schoolname='" + yb_schoolname + '\'' +
                ", yb_regtime='" + yb_regtime + '\'' +
                '}';
    }
}
