package com.jayantxie.service.impl;

import com.jayantxie.dao.EvaluationDataDao;
import com.jayantxie.model.EvaluationData;
import com.jayantxie.service.EvaluationDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 天亮就出发 on 2017/3/13.
 */

@Service("evaluationDataService")
@Transactional(rollbackFor = Exception.class)
public class EvaluationDataServiceImpl implements EvaluationDataService {
    @Resource
    private EvaluationDataDao evaluationDataDao;

    public boolean addEvaluationData(EvaluationData evaluationData){
        if(this.evaluationDataDao.insert(evaluationData) == 1)
            return true;
        else
            return false;
    }

    public boolean deleteEvaluationData(Integer id){
        if(this.evaluationDataDao.deleteByPrimaryKey(id) == 1)
            return true;
        else
            return false;
    }

    public EvaluationData queryOne(Integer id){
        return this.evaluationDataDao.selectByPrimaryKey(id);
    }

    public List<EvaluationData> queryTen(String name, Integer number){
        return this.evaluationDataDao.selectTenByPrimaryKey(name,number);
    }

    public Integer queryId(String name){
        return this.evaluationDataDao.selectLatestOne(name);
    }
}
