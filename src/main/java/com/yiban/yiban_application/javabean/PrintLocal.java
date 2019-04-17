package com.yiban.yiban_application.javabean;

public class PrintLocal {

    private int t_local_id;
    private String t_local_name;
    private String t_local_person;
    private String t_local_number;
    private String t_local_host;

    public int getT_local_id() {
        return t_local_id;
    }

    public void setT_local_id(int t_local_id) {
        this.t_local_id = t_local_id;
    }

    public String getT_local_name() {
        return t_local_name;
    }

    public void setT_local_name(String t_local_name) {
        this.t_local_name = t_local_name;
    }

    public String getT_local_person() {
        return t_local_person;
    }

    public void setT_local_person(String t_local_person) {
        this.t_local_person = t_local_person;
    }

    public String getT_local_number() {
        return t_local_number;
    }

    public void setT_local_number(String t_local_number) {
        this.t_local_number = t_local_number;
    }

    public String getT_local_host() {
        return t_local_host;
    }

    public void setT_local_host(String t_local_host) {
        this.t_local_host = t_local_host;
    }

    @Override
    public String toString() {
        return "PrintLocal{" +
                "t_local_id=" + t_local_id +
                ", t_local_name='" + t_local_name + '\'' +
                ", t_local_person='" + t_local_person + '\'' +
                ", t_local_number='" + t_local_number + '\'' +
                ", t_local_host='" + t_local_host + '\'' +
                '}';
    }
}
