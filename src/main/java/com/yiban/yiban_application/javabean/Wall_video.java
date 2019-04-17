package com.yiban.yiban_application.javabean;

public class Wall_video {
    private String video_id;
    private String video_size;
    private String video_format;
    private String video_user;
    private String video_time;
    private int video_status;
    private String video_path;
    private String video_url;
    private String video_name;


    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_size() {
        return video_size;
    }

    public void setVideo_size(String video_size) {
        this.video_size = video_size;
    }

    public String getVideo_format() {
        return video_format;
    }

    public void setVideo_format(String video_format) {
        this.video_format = video_format;
    }

    public String getVideo_user() {
        return video_user;
    }

    public void setVideo_user(String video_user) {
        this.video_user = video_user;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getVideo_status() {
        return video_status;
    }

    public void setVideo_status(int video_status) {
        this.video_status = video_status;
    }

    public String getVideo_time() {
        return video_time;
    }

    public void setVideo_time(String video_time) {
        this.video_time = video_time;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    @Override
    public String toString() {
        return "Wall_video{" +
                "video_id='" + video_id + '\'' +
                ", video_size='" + video_size + '\'' +
                ", video_format='" + video_format + '\'' +
                ", video_user='" + video_user + '\'' +
                ", video_time='" + video_time + '\'' +
                ", video_status=" + video_status +
                ", video_path='" + video_path + '\'' +
                ", video_url='" + video_url + '\'' +
                ", video_name='" + video_name + '\'' +
                '}';
    }
}
