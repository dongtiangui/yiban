package com.yiban.yiban_application.javabean;

public class Wall_assess {

    private int assess_id;
    private String assess_info;
    private String assess_user;
    private int assess_number;
    private String assess_status;
    private Wall_trends wall_trends;

    private String assess_time;

    public int getAssess_id() {
        return assess_id;
    }

    public void setAssess_id(int assess_id) {
        this.assess_id = assess_id;
    }

    public String getAssess_info() {
        return assess_info;
    }

    public void setAssess_info(String assess_info) {
        this.assess_info = assess_info;
    }

    public String getAssess_user() {
        return assess_user;
    }

    public void setAssess_user(String assess_user) {
        this.assess_user = assess_user;
    }

    public int getAssess_number() {
        return assess_number;
    }

    public void setAssess_number(int assess_number) {
        this.assess_number = assess_number;
    }

    public String getAssess_status() {
        return assess_status;
    }

    public void setAssess_status(String assess_status) {
        this.assess_status = assess_status;
    }

    public Wall_trends getWall_trends() {
        return wall_trends;
    }

    public void setWall_trends(Wall_trends wall_trends) {
        this.wall_trends = wall_trends;
    }

    public String getAssess_time() {
        return assess_time;
    }

    public void setAssess_time(String assess_time) {
        this.assess_time = assess_time;
    }

}
