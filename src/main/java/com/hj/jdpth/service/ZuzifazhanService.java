package com.hj.jdpth.service;


import com.hj.jdpth.domain.Zuzifazhan;

import java.util.Map;

public interface ZuzifazhanService {
    //初始页面
    public Map<String, Object> GetInfo(Integer quId, Integer zId, Integer vId, String name, String entity, Integer startPage, Integer pageSize, Integer adminId);

    //新增
    public Map<String, Object> Insert(Zuzifazhan zuzifazhan);

    //修改
    public Map<String, Object> Update(Integer zzfzId);

    //删除
    public Map<String, Object> Delete(Integer zzfzId);

    //查询
    public Map<String, Object> Select(String name, String entity, Integer startPage, Integer pageSize);
}
