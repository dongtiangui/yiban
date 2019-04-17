package com.yiban.yiban_application.util;

public class AppUtil {

    /**
     * 高德地图
     */
    public static final String APP_IP_ID = "3688b1f698e3e080d6cdd275845b8c5e";

    public static final String APP_IP_LOACAL = "https://restapi.amap.com/v3/ip?key=3688b1f698e3e080d6cdd275845b8c5e";
    /**
     * 匹配img正则表达式
     */
    
    public static final String IMAGESITEURL = "http://localhost:8083/static/upload";
    
    
    public static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式

    public static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式

    public static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    public static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符

}
