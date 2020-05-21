package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Village;
import com.hj.jdpth.repository.VillageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class VillageController {
    @Autowired
    VillageMapper villageMapper;

    //镇查村名和村id
    @GetMapping("/quaryByZhen/{V_Zhen_xiang}")
    public Map<String, Object> quaryZhenName_gsh(
            @PathVariable(value = "V_Zhen_xiang") Integer V_Zhen_xiang) {
        Map<String, Object> result = new HashMap<>();
        List<Village> list = villageMapper.queryByZhen_gsh(V_Zhen_xiang);
        result.put("Cun", list);
        return result;
    }

    @GetMapping("/h/cacheVillage")
    public Map<String, Object> query() {
        Map<String, Object> result = new HashMap<>();
        List<HashMap> list = villageMapper.cacheUse();
        result.put("data", list);
        return result;
    }


}
