package com.yiban.yiban_application.javabean;

import java.util.List;

public class Wall_trends {
    private String trends_id;
    private String trends_info;
    private String trends_outtime;
    private int trends_status;
    private int trends_praise_number;
    private String trends_grand_user;
    private String trends_head;
    private String trends_img;
    private String trends_video;
    private Wall_user wall_user;
    private Trends_type trends_type;
    private List<String> image_info;
    private List<String> video_info;

    public String getTrends_id() {
        return trends_id;
    }

    public void setTrends_id(String trends_id) {
        this.trends_id = trends_id;
    }

    public String getTrends_info() {
        return trends_info;
    }

    public void setTrends_info(String trends_info) {
        this.trends_info = trends_info;
    }

    public String getTrends_outtime() {
        return trends_outtime;
    }

    public void setTrends_outtime(String trends_outtime) {
        this.trends_outtime = trends_outtime;
    }

    public int getTrends_status() {
        return trends_status;
    }

    public void setTrends_status(int trends_status) {
        this.trends_status = trends_status;
    }

    public int getTrends_praise_number() {
        return trends_praise_number;
    }

    public void setTrends_praise_number(int trends_praise_number) {
        this.trends_praise_number = trends_praise_number;
    }

    public String getTrends_grand_user() {
        return trends_grand_user;
    }

    public void setTrends_grand_user(String trends_grand_user) {
        this.trends_grand_user = trends_grand_user;
    }

    public Trends_type getTrends_type() {
        return trends_type;
    }

    public void setTrends_type(Trends_type trends_type) {
        this.trends_type = trends_type;
    }

    public String getTrends_head() {
        return trends_head;
    }

    public void setTrends_head(String trends_head) {
        this.trends_head = trends_head;
    }

    public String getTrends_img() {
        return trends_img;
    }

    public void setTrends_img(String trends_img) {
        this.trends_img = trends_img;
    }

    public String getTrends_video() {
        return trends_video;
    }

    public void setTrends_video(String trends_video) {
        this.trends_video = trends_video;
    }

    public Wall_user getWall_user() {
        return wall_user;
    }

    public void setWall_user(Wall_user wall_user) {
        this.wall_user = wall_user;
    }

    public List<String> getImage_info() {
        return image_info;
    }

    public void setImage_info(List<String> image_info) {
        this.image_info = image_info;
    }

    public List<String> getVideo_info() {
        return video_info;
    }

    public void setVideo_info(List<String> video_info) {
        this.video_info = video_info;
    }

    @Override
    public String toString() {
        return "Wall_trends{" +
                ", trends_id='" + trends_id + '\'' +
                ", trends_info='" + trends_info + '\'' +
                ", trends_outtime='" + trends_outtime + '\'' +
                ", trends_status=" + trends_status +
                ", trends_praise_number=" + trends_praise_number +
                ", trends_grand_user='" + trends_grand_user + '\'' +
                ", trends_head='" + trends_head + '\'' +
                ", trends_img='" + trends_img + '\'' +
                ", trends_video='" + trends_video + '\'' +
                ", wall_user=" + wall_user +
                ", trends_type=" + trends_type +
                ", image_info=" + image_info +
                ", video_info=" + video_info +
                '}';
    }
}
