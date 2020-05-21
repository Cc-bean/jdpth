package com.hj.jdpth.service;

import java.util.Map;

public interface FeedService {

    public Map<String, Object> leaderUse(Integer zhenId, String time);

    public Map<String, Object> zhenzhexian(Integer villageId, String time);

    public Map<String, Object> quzhexian(Integer zhenId, String time);
}
