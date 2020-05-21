package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Zu;
import com.hj.jdpth.repository.ZuMapper;
import com.hj.jdpth.service.ZuService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ZuController {

    @Autowired
    ZuService zuService;
    //加
    @Autowired
    ZuMapper zuMapper;

    @PostMapping(value = "/zuSearch")
    public Map<String, Object> zuSearch(@RequestParam Integer mRegion,
                                        @RequestParam(required = false) Integer mZhenid,
                                        @RequestParam(required = false) Integer mVillageid,
                                        @RequestParam Integer mType,
                                        @RequestParam Integer startPage,
                                        @RequestParam Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (mType != null && startPage != null && pageSize != null) {
            map = zuService.zuSearch(mRegion, mZhenid, mVillageid, mType, startPage, pageSize);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.ZU)
    @DeleteMapping(value = "/ZuDeleteone/{ZuId}/{managerId}")  //单删
    public Map<String, Object> ZuDeleteone(@PathVariable(value = "ZuId") Integer ZuId, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = zuService.deleteOne(ZuId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.ZU)
    @DeleteMapping(value = "/ZuDeleteMany/{ZuId}/{managerId}")
    public Map<String, Object> ZuDeleteMany(@PathVariable(value = "ZuId") String ZuId, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = zuService.deleteMany(ZuId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.ZU)
    @PostMapping(value = "/zuAdd")
    public Map<String, Object> zuAdd(Zu zu, @RequestParam Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = zuService.zuInsert(zu);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    //村id搜组
    @PostMapping("/quaryZuByVillageId")
    public Map<String, Object> quaryZuByVillage_gsh(
            @RequestParam Integer villageId) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Zu> zu = zuMapper.quaryByVillage_gsh(villageId);
            map.put("state", "success");
            map.put("data", zu);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.ZU)
    @PostMapping(value = "/zuUpdate")
    public Map<String, Object> zuUpdate(Zu zu, @RequestParam Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = zuService.ZuUpdate(zu);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }
}
