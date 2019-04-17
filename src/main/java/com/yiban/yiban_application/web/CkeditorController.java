package com.yiban.yiban_application.web;

import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.javabean.Trends_type;
import com.yiban.yiban_application.javabean.Wall_trends;
import com.yiban.yiban_application.javabean.Wall_user;
import com.yiban.yiban_application.system.service.RendsService;
import com.yiban.yiban_application.util.AppUtil;
import com.yiban.yiban_application.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yiban.yiban_application.util.AppUtil.regEx_html;

@Controller
@RequestMapping(value = "/upload")
public class CkeditorController {

    private StringBuffer video = new StringBuffer();
    private StringBuffer img = new StringBuffer();
    private final RendsService rendsService;
    @Autowired
    public CkeditorController(RendsService rendsService) {
        this.rendsService = rendsService;
    }
    @Log("用户提交动态")
    @RequestMapping(value = "/CkeditorData",method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object> getCkeditorData(HttpServletRequest request){
        String username = request.getParameter("username");
        String res = request.getParameter("res");
        if (!res.equals("")){
            String[] res_list = res.split(",");
            for (String item:res_list) {
                String fileSufix = getFileSufix(item);
                String toLowerCase = fileSufix.toLowerCase();
                if (toLowerCase.equals("mov")
                        ||toLowerCase.equals("mp4")
                        ||toLowerCase.equals("rmvb")
                        ||toLowerCase.equals("flv")
                        ||toLowerCase.equals("3gp")){
                    video.append(item).append(",");
                    continue;
                }
                img.append(item).append(",");
            }
        }
        Map<String ,Object> map = new ConcurrentHashMap<>();
        String head = request.getParameter("head");
        String data = request.getParameter("data");
        String options = request.getParameter("options");
        Wall_trends trends = new Wall_trends();
        Wall_user user = new Wall_user();
        Trends_type type = new Trends_type();
        user.setId(username);
        type.setT_id(Integer.valueOf(options));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateNowStr = sdf.format(new Date());
        trends.setTrends_id(String.valueOf(new Date().getTime()));
        trends.setTrends_img(new String(img));
        trends.setTrends_video(new String(video));
        trends.setTrends_info(delHTMLTag(data));
        trends.setTrends_head(delHTMLTag(head));
        trends.setTrends_outtime(dateNowStr);
        trends.setWall_user(user);
        trends.setTrends_type(type);
        ResponseResult insertRends = rendsService.insertRends(trends);
        map.put("result",insertRends);
        return map;
    }

    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    private static String getFileSufix(String fileName) {
        if(fileName == null || "".equals(fileName)){
            return null;
        }
        return fileName.substring(fileName.lastIndexOf(".")+1);//从最后一个点之后截取字符串
    }

    /**
     * 正则表达式
     * @param htmlStr
     * @return
     */
    private static String delHTMLTag(String htmlStr) {
        Pattern p_w = Pattern.compile(AppUtil.regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll(""); // 过滤script标签

        Pattern p_script = Pattern.compile(AppUtil.regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签


        Pattern p_style = Pattern.compile(AppUtil.regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签


        Pattern p_space = Pattern.compile(AppUtil.regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签

        htmlStr = htmlStr.replaceAll(" ", ""); //过滤
        return htmlStr.trim(); // 返回文本字符串
    }
}
