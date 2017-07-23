package com.jayantxie.service;

import com.jayantxie.model.EvaluationData;

import java.util.List;

/**
 * Created by 天亮就出发 on 2017/3/13.
 */
public interface EvaluationDataService {

    public boolean addEvaluationData(EvaluationData evaluationData);

    public boolean deleteEvaluationData(Integer id);

    public EvaluationData queryOne(Integer id);

    public List<EvaluationData> queryTen(String name, Integer number);

    public Integer queryId(String name);
}
