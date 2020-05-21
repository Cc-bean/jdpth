package com.hj.jdpth.service;

import com.hj.jdpth.domain.Jiaoyv;
import com.hj.jdpth.domain.Manager;

import java.util.Map;

public interface JiaoyvzixunListService {
    public Map<String, Object> GetInfoAllFirst(Integer quId, Integer zhenId, Integer cunId, Integer startpage, Integer pageSize, Integer adminId);

    public Map<String, Object> GetInfoSelect(Integer quId, Integer zhenId, Integer cunId, Integer startpage, Integer pageSize);

    public Map<String, Object> DeleteOne(Integer jiaoyvId);

    public Map<String, Object> DeleteMany(String jiaoyvIds);

    public Map<String, Object> UploadFile(Jiaoyv jiaoyv);

    public Map<String, Jiaoyv> GetInfoOne(Integer jiaoyvId);
}
