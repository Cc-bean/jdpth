package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Yonghu;
import com.hj.jdpth.service.YonghuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class YonghuController {
    @Autowired
    YonghuService yonghuService;

    //查询用户详情
    @GetMapping("/yonghus/queryYongHuDetail/{id}")
    public Map queryYongHuDetail(@PathVariable Integer id) {
        Map map = new HashMap();
        map.put("yonghu", yonghuService.selectYonghuDetail(id));
        return map;
    }

    //动态查询用户
    @GetMapping("/yonghus/queryYonghuDynamic/{startPage}/{pageSize}/{regionId}")
    public Map queryYonghuDynamic(@PathVariable(value = "startPage") int startPage, @PathVariable(value = "pageSize") int pageSize, @PathVariable(value = "regionId") Integer regionId, @RequestParam(value = "zhenId", required = false) Integer zhenId, @RequestParam(value = "cunId", required = false) Integer cunId, @RequestParam(value = "name", required = false) String name) {
        Map map = new HashMap<String, Object>();
        Map params = new HashMap<String, Object>();
        params.put("startPage", startPage);
        params.put("pageSize", pageSize);
        params.put("regionId", regionId);
        params.put("zhenId", zhenId);
        params.put("cunId", cunId);
        params.put("userName", name);

        map.put("yonghuList", yonghuService.seletYonghuDynamic(params));
        return map;
    }

    //删除用户
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.YONGHU)
    @PostMapping("/yonghus/deleteYonghuDynamic/{managerId}/{mType}")
    public Map deleteYonghu(@RequestParam(value = "ids") List<Integer> ids, @PathVariable int managerId, @PathVariable int mType) {
        Map map = new HashMap();
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        try {
            yonghuService.deleteYongHu(ids);
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE");
            return map;
        }
    }

    //更新用户
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.YONGHU)
    @PutMapping("/yonghus/updateYonghu/{managerId}/{mType}")
    public Map updateYonghu(Yonghu yonghu, @PathVariable int managerId, @PathVariable int mType) {
        Map map = new HashMap();
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        try {
            yonghuService.updateYongHu(yonghu);
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE");
            return map;
        }
    }

    //添加用户
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.YONGHU)
    @PostMapping("/yonghus/insertYonghu/{managerId}/{mType}")
    public Map insertYongHu(@PathVariable int managerId, Yonghu yonghu, @PathVariable int mType) {
        Map map = new HashMap();
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        try {
            yonghuService.insertYongHu(yonghu);
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE");
            return map;
        }
    }

    //政治面貌列表
    @GetMapping("/yonghus/queryPolicyStatus")
    public Map queryPolicyStatus() {
        Map map = new HashMap();
        map.put("policyStatus", yonghuService.selectPolicyStatuNameAdId());
        return map;
    }

    //某村，组下拉列表
    @GetMapping("/yonghus/queryZuNames/{cunId}")
    public Map queryZuNames(@PathVariable Integer cunId) {
        Map map = new HashMap<>();
        map.put("Zus", yonghuService.selecctZuNameAdId(cunId));
        return map;
    }

    //根据组ID查找户
    @GetMapping("/yonghus/queryHuNames/{zuId}")
    public Map queryHuNames(@PathVariable Integer zuId) {
        Map map = new HashMap();
        map.put("Hus", yonghuService.selectHuNameAdId(zuId));
        return map;
    }
}
