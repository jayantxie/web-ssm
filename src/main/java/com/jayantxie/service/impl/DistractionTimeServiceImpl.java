package com.jayantxie.service.impl;

import com.jayantxie.dao.DistractionTimeDao;
import com.jayantxie.model.DistractionTime;
import com.jayantxie.service.DistractionTimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 天亮就出发 on 2017/5/4.
 */

@Service("distractionTimeService")
@Transactional(rollbackFor = Exception.class)
public class DistractionTimeServiceImpl implements DistractionTimeService {
    @Resource
    private DistractionTimeDao distractionTimeDao;

    public boolean addList(List<DistractionTime> distractionTimeList){
        for(DistractionTime distractionTime:distractionTimeList){
            if(distractionTimeDao.insert(distractionTime) == 0)
                return false;
        }
        return true;
    }

    public List<DistractionTime> queryList(Integer id){
        return distractionTimeDao.selectListByEvaId(id);
    }
}
