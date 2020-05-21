package com.hj.jdpth.service;

import java.util.Map;

public interface JobService {

    public Map<String, Object> SearchJob(Integer id, Integer startPage, Integer pageSize);
}
