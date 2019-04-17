package com.yiban.yiban_application.web.service.empl;


import com.yiban.yiban_application.javabean.Wall_trends;
import com.yiban.yiban_application.web.service.IndexServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IndexService implements IndexServiceInterface {

    @Override
    public List<Wall_trends> getWall_trendsList(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<Wall_trends> getWall_trendsListBySearch(String search_info) {
        return null;
    }
}
