package com.jayantxie.service;

import com.jayantxie.model.DistractionTime;

import java.util.List;

/**
 * Created by 天亮就出发 on 2017/5/4.
 */
public interface DistractionTimeService {
    public boolean addList(List<DistractionTime> distractionTimeList);

    public List<DistractionTime> queryList(Integer id);
}
