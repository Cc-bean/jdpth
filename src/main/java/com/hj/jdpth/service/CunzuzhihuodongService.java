package com.hj.jdpth.service;

import com.hj.jdpth.domain.Cunhuodongleixing;
import com.hj.jdpth.domain.Cunzuzhihuodong;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CunzuzhihuodongService {
    public Map<String, Object> queryHuodong_yp(Integer style, Integer qu_id, Integer zhen_id, Integer cun_id, Integer chdlx_id, Integer startPage, Integer pageSize);

    public Map<String, Object> findXingqing_yp(Integer id);

    public Map<String, Object> deleteOneById_yp(Integer id);

    public Map<String, Object> deleteManyById_yp(String Ids);

    public Map<String, Object> insertOne_yp(Cunzuzhihuodong cunzuzhihuodong, String czzhdTime);

    public Map<String, Object> daoruOne_yp(Cunzuzhihuodong cunzuzhihuodong);

    public Map<String, Object> updateOne_yp(Cunzuzhihuodong cunzuzhihuodong);

    public Boolean findHuodongByxuhao_yp(String cdyzzhdXuhao);

    public Map<String, Object> findChdlxById_yp(Integer cun_id);

    public Map<String, Object> insertLeixing_yp(Cunhuodongleixing cunhuodongleixing);


    public Map<String, Object> chdzhen(Integer cunId, String time);

    public Map<String, Object> chdqu(Integer zhenId, String time);

    public Map<String, Object> qzxian(String time);

    public Map<String, Object> zzxian(Integer zhenId, String time);

    public Map<String, Object> Vzhexian(Integer villageId, String time);

    //2019-11-27
    public Map<String, Object> uploadFile(MultipartFile file);
}
