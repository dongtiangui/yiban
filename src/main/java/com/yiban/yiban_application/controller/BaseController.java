package com.yiban.yiban_application.controller;

import com.github.pagehelper.PageInfo;
import java.util.HashMap;
import java.util.Map;
public class BaseController {

	protected Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
		Map<String, Object> rspData = new HashMap<String, Object>();
		rspData.put("rows", pageInfo.getList());
		rspData.put("total", pageInfo.getTotal());
		return rspData;
	}
}
