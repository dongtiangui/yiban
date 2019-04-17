package com.yiban.yiban_application.web.service.empl;

import com.yiban.yiban_application.javabean.NiceDetail;
import com.yiban.yiban_application.system.dao.NiceDetailMapper;
import com.yiban.yiban_application.web.service.NiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NiceDetailServiceImpl implements NiceDetailService {
    @Autowired
    private NiceDetailMapper nicedetailMapper;

    @Override
    public void insertNiceDetail(NiceDetail niceDetail) {
        nicedetailMapper.insertSelective(niceDetail);
    }

    @Override
    public void deleteNiceDetail(Integer id) {
        nicedetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public NiceDetail findNiceDetail(NiceDetail niceDetail) {
    return nicedetailMapper.findNiceDetail(niceDetail);
     }

}
