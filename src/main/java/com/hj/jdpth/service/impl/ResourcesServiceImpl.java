package com.hj.jdpth.service.impl;

import com.hj.jdpth.domain.Resources;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.ResourcesService;
import com.hj.jdpth.service.ResourcesdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    ResourcesMapper resourcesMapper;
    @Autowired
    ResourcesdetailsService resourcesdetailsService;
    @Autowired
    ZuMapper zuMapper;

    @Override
    public Map<String, Object> addResources(String R_type, Integer R_Villageid, Integer R_zu) {
        Map<String, Object> result = new HashMap<>();
        try {
            Resources resources = resourcesMapper.quaryResourcesStyle(R_Villageid, R_zu, R_type);
            if (resources == null) {
                resources = new Resources();
                resources.setrType(R_type);
                resources.setrVillageid(R_Villageid);
                resources.setrZu(R_zu);
                resourcesMapper.AddResources(resources);
                result.put("status", "添加成功");
            } else {
                result.put("status", "重复添加");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("data", "error");
            result.put("status", "error");
        }
        return result;
    }


}
