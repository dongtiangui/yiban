package com.yiban.yiban_application.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.yiban_application.controller.BaseController;
import com.yiban.yiban_application.javabean.SysLog;
import com.yiban.yiban_application.system.dao.LogInterface;
import com.yiban.yiban_application.util.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value = "/log")
public class LogController extends BaseController {

    @Autowired
    private LogInterface logInterface;

    @RequestMapping(value = "/logPage")
    public String logPage(){
        return "admin_pages/tables/logdata";
    }
    @RequestMapping(value = "/ajaxLog")
    @ResponseBody
    public Map<String, Object> selectPrint(QueryRequest request) {
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<SysLog> all = logInterface.getAllLog();
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(all);
        return getDataTable(pageInfo);
    }
}
