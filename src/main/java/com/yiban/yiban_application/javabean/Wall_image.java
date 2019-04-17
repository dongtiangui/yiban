package com.yiban.yiban_application.javabean;

import java.util.List;

public class Wall_image {
    private String image_id;
    private String image_size;
    private String image_user;
    private String image_path;
    private String image_url;
    private String image_type;
    private int image_status;
    private String image_format;
    private String image_name;

    private List<String> list;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getImage_size() {
        return image_size;
    }

    public void setImage_size(String image_size) {
        this.image_size = image_size;
    }

    public String getImage_user() {
        return image_user;
    }

    public void setImage_user(String image_user) {
        this.image_user = image_user;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }


    public int getImage_status() {
        return image_status;
    }

    public void setImage_status(int image_status) {
        this.image_status = image_status;
    }

    public String getImage_format() {
        return image_format;
    }

    public void setImage_format(String image_format) {
        this.image_format = image_format;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    @Override
    public String toString() {
        return "Wall_image{" +
                "image_id='" + image_id + '\'' +
                ", image_size='" + image_size + '\'' +
                ", image_user='" + image_user + '\'' +
                ", image_path='" + image_path + '\'' +
                ", image_url='" + image_url + '\'' +
                ", image_type='" + image_type + '\'' +
                ", image_status=" + image_status +
                ", image_format='" + image_format + '\'' +
                ", image_name='" + image_name + '\'' +
                ", list=" + list +
                '}';
    }
}
