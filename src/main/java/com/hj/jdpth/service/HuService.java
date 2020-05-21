package com.hj.jdpth.service;

import com.hj.jdpth.domain.Hu;

import java.util.Map;

public interface HuService {
    public Map<String, Object> DeleteOne(Integer HuId);

    public Map<String, Object> DeleteMany(String HuId);

    public Map<String, Object> huSearch(int regionId, int zhenId, int villageId, int zuId, int mType, Integer startPage, Integer pageSize);

    public Map<String, Object> HuInsert(Hu hu);

    public Map<String, Object> HuUpdate(Hu hu);

    public boolean Check(String xvhao);

    public Map<String, Object> Add(Hu hu);
}
