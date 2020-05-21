package com.hj.jdpth.service;

import com.hj.jdpth.domain.Zu;

import java.util.Map;

public interface ZuService {

    public Map<String, Object> deleteOne(Integer id);

    public Map<String, Object> deleteMany(String ZuId);

    Map<String, Object> zuSearch(int regionId, int zhenId, int villageId, int mType, Integer startPage, Integer pageSize);

    public Map<String, Object> zuInsert(Zu zu);

    public Map<String, Object> ZuUpdate(Zu zu);

    public boolean Check(String xvhao);

    public Map<String, Object> Add(Zu zu);
}
