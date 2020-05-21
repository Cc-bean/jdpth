package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Capital;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Zhen;
import com.hj.jdpth.repository.CaiwuCapitalMapper;
import com.hj.jdpth.repository.LeaderCaitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class LeaderCapitalController {
    @Autowired
    CaiwuCapitalMapper caiwuCapitalMapper;
    @Autowired
    LeaderCaitalMapper leaderCaitalMapper;

    @PostMapping(value = "/leaderCapitalController/findCapitalYear")
    public Map<String, Object> findCapitalYear(
            @RequestParam String year
    ) {
        Map<String, Object> result = new HashMap<>();
        List<Zhen> zhens = caiwuCapitalMapper.findZhenByRegionId(1349);
        List list = new ArrayList();
        for (Zhen zhen : zhens) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("zhen", zhen);
            List<Map<String, Object>> cashCapital = leaderCaitalMapper.findCapitalYear(year, "现金收支", zhen.getZhenId());
            List<Map<String, Object>> bankCapital = leaderCaitalMapper.findCapitalYear(year, "银行收支", zhen.getZhenId());
            map1.put("cashCapital", cashCapital);
            map1.put("bankCapital", bankCapital);
            list.add(map1);
        }
        result.put("data", list);
        return result;
    }

    @PostMapping(value = "/leaderCapitalController/findAllCapital")
    public Map<String, Object> findAllCapital(
            @RequestParam Integer regionId
    ) {
        Map<String, Object> result = new HashMap<>();
        List<Zhen> zhens = caiwuCapitalMapper.findZhenByRegionId(regionId);
        List list = new ArrayList();
        for (Zhen zhen : zhens) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("zhen", zhen);
            List<Map<String, Object>> cashCapital = leaderCaitalMapper.findAllCapital(zhen.getZhenId(), "现金收支");
            List<Map<String, Object>> bankCapital = leaderCaitalMapper.findAllCapital(zhen.getZhenId(), "银行收支");
            map1.put("cashCapital", cashCapital);
            map1.put("bankCapital", bankCapital);
            list.add(map1);
        }
        result.put("data", list);
        return result;
    }

    @PostMapping(value = "/leaderCapitalController/findAllCapitalByZhenId")
    public Map<String, Object> findAllCapitalByZhenId(
            @RequestParam Integer zhenId
    ) {
        Map<String, Object> result = new HashMap<>();
        List<Village> villages = caiwuCapitalMapper.findVillageByZhenId(zhenId);
        List list = new ArrayList();
        for (Village village : villages) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("village", village);
            List<Capital> cashCapital = leaderCaitalMapper.findAllCapitalByZhenId(village.getVillageId(), "现金收支");
            List<Capital> bankCapital = leaderCaitalMapper.findAllCapitalByZhenId(village.getVillageId(), "银行收支");
            map1.put("cashCapital", cashCapital);
            map1.put("bankCapital", bankCapital);
            list.add(map1);
        }
        result.put("data", list);
        return result;
    }

    @PostMapping(value = "/leaderCapitalController/findAllCapitalByVillageId")
    public Map<String, Object> findAllCapitalByVillageId(
            @RequestParam Integer villageId,
            @RequestParam String date
    ) {
        Map<String, Object> result = new HashMap<>();
        String[] a = date.split("~");
        String startDate = a[0].replace("-", "");
        String endDate = a[1].replace("-", "");
        List<Map<String, Object>> Capital = leaderCaitalMapper.findCapitalByVillageId(villageId, startDate, endDate);
        result.put("data", Capital);
        return result;
    }
}


