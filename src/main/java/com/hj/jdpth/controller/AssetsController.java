package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Assets;
import com.hj.jdpth.repository.AssetsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class AssetsController {
    @Autowired
    AssetsMapper assetsMapper;

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.ASSETS)
    @PostMapping("/a/insertAssets")
    public Map<String, Object> insertAssets(
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer villageId,
            @RequestParam Integer zuId,
            @RequestParam String atype
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            Assets assets = new Assets();
            assets.setaVillageid(villageId);
            assets.setaZu(zuId);
            assets.setaType(atype);
            assetsMapper.addAssets(assets);
            map.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "error");
        }
        return map;

    }

    @GetMapping("/a/quaryAllByVidZid/{villageId}/{zuId}")
    public Map<String, Object> quaryAllByVidZid(
            @PathVariable Integer villageId,
            @PathVariable Integer zuId
    ) {
        Map<String, Object> map = new HashMap<>();
        List<Assets> list = assetsMapper.quaryAllByVidZid(villageId, zuId);
        map.put("Type", list);
        return map;
    }

}
