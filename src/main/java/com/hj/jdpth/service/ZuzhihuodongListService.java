package com.hj.jdpth.service;

import com.hj.jdpth.domain.Dangyuanzuzhihuodong;
import com.hj.jdpth.domain.Huodongleixing;

import java.util.Map;

public interface ZuzhihuodongListService {
    public Map<String, Object> GetinfoFirstAll(Integer quId, Integer zhenId, Integer cunId, Integer startpage, Integer pageSize, Integer adminId);

    public Map<String, Object> GetinfoSelectAll(Integer quId, Integer zhenId, Integer cunId, Integer typeId, Integer startpage, Integer pageSize);

    public Map<String, Dangyuanzuzhihuodong> GetinfoOne(Integer zuzhihuodongId);

    public Map<String, Object> DeleteOne(Integer zuzhihuodongId);

    public Map<String, Object> DeleteMany(String zuzhihuodongIds);

    public Map<String, Object> GetInfoAddTypeFirst(Integer quId, Integer zhenId, Integer cunId, Integer adminTypeId);

    public Map<String, Object> GetInfoAddType(Integer hdlxZhen, Integer hdlxVillage, String hdlxName);

    public Map<String, Object> GetInfoType(Integer quId, Integer zhenId, Integer cunId);

    public Map<String, Object> GetInfoAddInfo(Dangyuanzuzhihuodong dangyuanzuzhihuodong);

    public Map<String, Object> GetInfoType(Integer villageId);

    public Map<String, Object> UpdateInfo(Dangyuanzuzhihuodong dangyuanzuzhihuodong);

    public boolean GetInfoByXvhao(String dyzzhdXvhao);

    public int GetTypeId(String TypeName, Integer VillageId);
}
