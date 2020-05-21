package com.hj.jdpth.service;

import java.util.Map;

public interface BasicVillageService {
    public Map<String, Object> DeleteOne(Integer id);

    public Map<String, Object> DeleteMany(String Id);
}
