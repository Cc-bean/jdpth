package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Hu;
import com.hj.jdpth.domain.Zu;
import com.hj.jdpth.service.HuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class HuController {
    @Autowired
    HuService huService;

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.HU)
    @DeleteMapping(value = "/HuDeleteone/{HuId}/{managerId}")  //单删
    public Map<String, Object> HuDeleteone(@PathVariable(value = "HuId") Integer ZuId, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = huService.DeleteOne(ZuId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.HU)
    @DeleteMapping(value = "/HuDeleteMany/{HuId}/{managerId}")
    public Map<String, Object> HuDeleteMany(@PathVariable(value = "HuId") String HuId, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = huService.DeleteMany(HuId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @PostMapping(value = "/HuSearch")
    public Map<String, Object> HuSearch(@RequestParam Integer mRegion,
                                        @RequestParam(required = false) Integer mZhenid,
                                        @RequestParam(required = false) Integer mVillageid,
                                        @RequestParam(required = false) Integer zuid,
                                        @RequestParam Integer mType,
                                        @RequestParam Integer startPage,
                                        @RequestParam Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (mType != null && startPage != null && pageSize != null) {
            map = huService.huSearch(mRegion, mZhenid, mVillageid, zuid, mType, startPage, pageSize);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.HU)
    @PostMapping(value = "/HuInsert")
    public Map<String, Object> HuInsert(Hu hu, @RequestParam Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = huService.HuInsert(hu);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.HU)
    @PostMapping(value = "/HuUpdate")
    public Map<String, Object> HuUpdate(Hu hu, @RequestParam Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = huService.HuUpdate(hu);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }
}
