package com.hj.jdpth.service;

import com.hj.jdpth.domain.Villagebulletin;

import java.util.Map;

public interface CungonggaoService {
    public Map<String, Object> findCunGongGao(Integer style, Integer qu_id, Integer zhen_id, Integer cun_id, String gongGaoName, Integer startPage, Integer pageSize);

    public Map<String, Object> findgongGaoXingQing_yp(Integer id);

    public Map<String, Object> deleteGonggaoById_yp(Integer id);

    public Map<String, Object> deleteManyGonggaoById_yp(String Ids);

    public Map<String, Object> insertGonggao_yp(Villagebulletin villagebulletin, String ggTime);

    public Map<String, Object> daoruGonggao_yp(Villagebulletin villagebulletin);

    public Map<String, Object> updateGonggao_yp(Villagebulletin villagebulletin, String ggTime);

    public Boolean findgonggaoByxuhao_yp(String vbXuhao);
}
