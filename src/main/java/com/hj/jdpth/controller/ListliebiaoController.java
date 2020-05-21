package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.domain.Region;
import com.hj.jdpth.domain.Zhen;
import com.hj.jdpth.repository.ManagerMapper;
import com.hj.jdpth.repository.RegionMapper;
import com.hj.jdpth.repository.VillageMapper;
import com.hj.jdpth.repository.ZhenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ListliebiaoController {

    @Autowired
    VillageMapper villageMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    ManagerMapper managerMapper;

    @PostMapping(value = "/Quliebiao")
    public Map<String, Object> Quliebiao(@RequestParam Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        Manager manager = managerMapper.FindManager(managerId);
        Region region = regionMapper.FindById(manager.getmRegion());
        map.put("quid", manager.getmRegion());
        map.put("quname", region.getrName());
        return map;
    }

    @PostMapping(value = "/FindZhenByRKey")
    public Map<String, Object> FindZhenByRKey(@RequestParam Integer quid) {
        Map<String, Object> map = new HashMap<>();
        List<Zhen> zhenList = zhenMapper.queryZhenByQuId_yyq(quid);
        map.put("zhenList", zhenList);
        return map;
    }
}
