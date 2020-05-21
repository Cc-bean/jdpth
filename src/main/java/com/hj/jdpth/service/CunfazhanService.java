package com.hj.jdpth.service;


import com.hj.jdpth.domain.Cunfazhan;

import java.util.Map;

public interface CunfazhanService {
    public Map<String, Object> findCunFaZhan(Integer style, Integer qu_id, Integer zhen_id, Integer cun_id, Integer startPage, Integer pageSize);

    public Map<String, Object> findFazhanXingQing_yp(Integer id);

    public Map<String, Object> deleteFazhanById_yp(Integer id);

    public Map<String, Object> deleteManyFazhanById_yp(String Ids);

    public Map<String, Object> insertFazhan_yp(Cunfazhan cunfazhan);

    public Map<String, Object> updateFazhan_yp(Cunfazhan cunfazhan);

    public Boolean findFazhanByxuhao_yp(String cfzXuhao);
}
