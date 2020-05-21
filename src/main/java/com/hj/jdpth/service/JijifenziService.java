package com.hj.jdpth.service;

import java.util.Date;
import java.util.Map;

public interface JijifenziService {
    // Map<String, Object> findJijifenzi(int regionId, int zhenId, int villageId,int style, Integer startPage, Integer pageSize);
    public Map<String, Object> GetInfoAllFirst(Integer quId, Integer zId, Integer vId, Date time, Integer entity, Integer startPage, Integer pageSize, Integer adminId);

    public Map<String, Object> cun_List(Integer zhenId);


}
