package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Resources;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ResourcesController {
    @Autowired
    ResourcesService resourcesService;
    @Autowired
    ResourcesMapper resourcesMapper;

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.RESOURCES)
    @PostMapping("/resources/addResources")
    public Map<String, Object> addResources(
            @RequestParam(required = false) Integer managerId,
            @RequestParam String R_type,
            @RequestParam Integer R_Villageid,
            @RequestParam Integer R_zu) {
        Map<String, Object> result = new HashMap<>();
        if (R_type != null && R_Villageid != null && R_zu != null) {
            Map<String, Object> map = resourcesService.addResources(R_type, R_Villageid, R_zu);
            result.put("data", map);
        } else {
            result.put("data", "数据不完整");
            result.put("status", "null");
        }
        return result;
    }

    //根据类型查询资源子分类
    @GetMapping("/resources/quaryAllRbeiyong1/{R_type}")
    public Map<String, Object> quaryAllR_beiyong1(
            @PathVariable Integer R_type
    ) {
        Map<String, Object> result = new HashMap<>();
        if (R_type != null) {
            try {
                List<Resources> list = resourcesMapper.quaryResourcesR_beiyong1(R_type);
                if (list.size() != 0) {
                    result.put("date", list);
                } else {
                    List<Resources> a = null;
                    result.put("date", a);
                }
            } catch (Exception e) {
                result.put("date", "error");
            }
        } else {
            result.put("date", "数据不完整");
        }
        return result;
    }

    //编辑资源类型
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.RESOURCES)
    @PostMapping("/resources/updateResources")
    public Map<String, Object> updateResources(
            @RequestParam(required = false) Integer managerId,
            @RequestParam String R_type,
            @RequestParam Integer Resources_Id
    ) {
        Map<String, Object> result = new HashMap<>();
        Resources resources = resourcesMapper.quaryResources(Resources_Id);
        if (R_type != null) {
            resources.setrType(R_type);
            Integer flag = resourcesMapper.updateResources(resources);
            if (flag > 0) {
                result.put("status", "修改成功");
            }
        } else {
            result.put("data", "数据为空");
            result.put("status", "修改失败");
        }

        return result;
    }
}
