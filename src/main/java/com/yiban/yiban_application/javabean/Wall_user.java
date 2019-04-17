package com.yiban.yiban_application.javabean;

public class Wall_user {
    private String id;
    private String yb_usernick;
    private String yb_schoolname;
    private String yb_money;
    private String yb_regtime;
    private String yb_userhead;
    private int yb_usertrends;
    private int yb_userassess;
    private Role role;
    private String session;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYb_usernick() {
        return yb_usernick;
    }

    public void setYb_usernick(String yb_usernick) {
        this.yb_usernick = yb_usernick;
    }

    public String getYb_schoolname() {
        return yb_schoolname;
    }

    public void setYb_schoolname(String yb_schoolname) {
        this.yb_schoolname = yb_schoolname;
    }

    public String getYb_money() {
        return yb_money;
    }

    public void setYb_money(String yb_money) {
        this.yb_money = yb_money;
    }

    public String getYb_regtime() {
        return yb_regtime;
    }

    public void setYb_regtime(String yb_regtime) {
        this.yb_regtime = yb_regtime;
    }

    public String getYb_userhead() {
        return yb_userhead;
    }

    public void setYb_userhead(String yb_userhead) {
        this.yb_userhead = yb_userhead;
    }

    public int getYb_usertrends() {
        return yb_usertrends;
    }

    public void setYb_usertrends(int yb_usertrends) {
        this.yb_usertrends = yb_usertrends;
    }

    public int getYb_userassess() {
        return yb_userassess;
    }

    public void setYb_userassess(int yb_userassess) {
        this.yb_userassess = yb_userassess;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Wall_user{" +
                "id='" + id + '\'' +
                ", yb_usernick='" + yb_usernick + '\'' +
                ", yb_schoolname='" + yb_schoolname + '\'' +
                ", yb_money='" + yb_money + '\'' +
                ", yb_regtime='" + yb_regtime + '\'' +
                ", yb_userhead='" + yb_userhead + '\'' +
                ", yb_usertrends=" + yb_usertrends +
                ", yb_userassess=" + yb_userassess +
                ", role=" + role +
                ", session='" + session + '\'' +
                '}';
    }
}
