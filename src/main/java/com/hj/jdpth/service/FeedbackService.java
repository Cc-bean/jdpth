package com.hj.jdpth.service;

import com.hj.jdpth.domain.Feedbackreply;

import java.util.Map;

public interface FeedbackService {

    Map<String, Object> FeedbakcReply(Feedbackreply feedbackreply, Integer managerId, Integer frFeedbackid);   //回复反馈

    public Map<String, Object> YichuliSearch(int regionId, int zhenId, int villageId, String title, int mType, Integer startPage, Integer pageSize);

    public Map<String, Object> WeichuliSearch(int regionId, int zhenId, int villageId, String title, int mType, Integer startPage, Integer pageSize);

    public Map<String, Object> Xiangshang(int regionId, int zhenId, int villageId, String title, int mType, Integer startPage, Integer pageSize);


    public Map<String, Object> chulilv(int zhenId, String time);

    public Map<String, Object> bingtu(String time);


    /**
     * 处理领导页面反馈量统计
     */
    public Map<String, Object> leaderUse(Integer zhenId, String time);

    public Map<String, Object> qulx(Integer zhenId, String time);

    public Map<String, Object> ycllb(Integer zhenId, String time);

    public Map<String, Object> wcllb(Integer zhenId, String time);

    public Map<String, Object> upregion(String time);

    public Map<String, Object> zhenlx(Integer zhenId, String time);

    public Map<String, Object> quzhexian(Integer zhenId, String time);

    public Map<String, Object> zhenzhexian(Integer zhenId, String time);

    //2019-11-20
    public Map<String, Object> qulxbintu(String time);
}
