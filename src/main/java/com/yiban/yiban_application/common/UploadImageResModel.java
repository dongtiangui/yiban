package com.yiban.yiban_application.common;

public class UploadImageResModel {
    /**
     * 1成功，0失败
     */
    private Integer uploaded;

    private String fileName;

    private String url;

    private String message;

    public Integer getUploaded() {
        return uploaded;
    }

    public void setUploaded(Integer uploaded) {
        this.uploaded = uploaded;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UploadImageResModel{" +
                "uploaded=" + uploaded +
                ", fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
