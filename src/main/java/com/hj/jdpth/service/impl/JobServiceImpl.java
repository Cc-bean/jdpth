package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Job;
import com.hj.jdpth.repository.JobMapper;
import com.hj.jdpth.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobMapper jobMapper;

    @Override
    public Map<String, Object> SearchJob(Integer id, Integer startPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Job> jobPage = jobMapper.SearchJob(id);
        map.put("data", jobPage);
        map.put("total", jobPage.getPages());
        //记录总数
        map.put("count", jobPage.getTotal());
        //当前第几页
        map.put("nowPage", jobPage.getPageNum());
        return map;
    }
}
