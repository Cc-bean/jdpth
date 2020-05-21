package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.service.SubsidyService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class SubsidyController {
    @Autowired
    SubsidyService subsidyService;

    //根据id查看详情
    @GetMapping("/susbidyInformation/queryDetailById/{id}")
    public Map<String, Object> querySusbidyDetailById(@PathVariable Integer id) {
        Map map = new HashMap<String, Object>();
        map.put("subsidy", subsidyService.selectDetailByID(id));
        return map;
    }

    //查看补助对象//id=susbidyInformation.Id
    @GetMapping("/susbidyInformation/queryObjectById/{startPage}/{pageSize}/{id}")
    public Map<String, Object> queryDetailObjectById(@PathVariable Integer startPage, @PathVariable Integer pageSize, @PathVariable Integer id) {
        Map map = new HashMap<String, Object>();
        Map params = new HashMap();
        params.put("startPage", startPage);
        params.put("pageSize", pageSize);
        params.put("id", id);
        map.put("subsidy", subsidyService.selectOBjectsByID(params));
        return map;
    }

    //动态查找
    @GetMapping("/susbidyInformation/querySubsidyDynamic/{startPage}/{pageSize}/{regionId}")
    public Map querySubsidyDynamic(@PathVariable(value = "startPage") int startPage, @PathVariable(value = "pageSize") int pageSize, @PathVariable(value = "regionId") Integer regionId, @RequestParam(value = "zhenId", required = false) Integer zhenId, @RequestParam(value = "cunId", required = false) Integer cunId, @RequestParam(value = "type", required = false) Integer type) {
        Map map = new HashMap<String, Object>();
        Map params = new HashMap<String, Object>();
        params.put("startPage", startPage);
        params.put("pageSize", pageSize);
        params.put("regionId", regionId);
        params.put("zhenId", zhenId);
        params.put("cunId", cunId);
        params.put("type", type);
        Map<String, Object> list = subsidyService.selectSubsidyInformationDynamic(params);
        map.put("list", list);
        return map;
    }

    //动态更新OK
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.SUBSIDYINFORMATION)
    @PutMapping("/susbidyInformation/updateSubsidyInformation/{managerId}/{mType}")
    public Map updateSubsidyInformation(Subsidyinformation subsidyinformation, @PathVariable Integer managerId, @PathVariable Integer mType, @RequestParam String subsidyname) {
        Map map = new HashMap();
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        try {
            System.out.println(subsidyinformation);
            subsidyService.updateSubsidyName(subsidyname, subsidyinformation);
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE");
            return map;
        }
    }

    //删除
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.SUBSIDYINFORMATION)
    @PostMapping("/susbidyInformation/deleteInformation/{managerId}/{mType}")
    public Map deleteInformation(@RequestParam(value = "ids") List<Integer> ids, @PathVariable Integer managerId, @PathVariable Integer mType) {
        Map map = new HashMap();
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        try {
            subsidyService.deleteInformation(ids);
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE");
            return map;
        }
    }

    //新增补助项类型OK
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.SUBSIDYTYPE)
    @PostMapping("/susbidyInformation/insertSubsidyType/{managerId}/{mType}")
    public Map insertSubsidyType(Subsidytype subsidytype, @PathVariable Integer managerId, @PathVariable Integer mType) {
        Map map = new HashMap();
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        try {
            System.out.println(subsidytype.toString());
            subsidyService.insertSubsidyType(subsidytype);
            System.out.println(subsidytype.toString());
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE ss");
            return map;
        }
    }

    //新增补助项+=custokmOK
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.SUBSIDTNAME)
    @PostMapping("/susbidyInformation/insertSubsidyName/{managerId}/{mType}")
    public Map insertSubsidyName(SubsidynameCustom subsidynameCustom, @PathVariable Integer managerId, @PathVariable Integer mType) {
        Map map = new HashMap();
        String name = subsidyService.distinctName(subsidynameCustom.getsName(), subsidynameCustom.getsType());
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        if (name != null && !name.equals("")) {
            map.put("STSTUS", "该补助项已存在");
            return map;
        }
        try {
            subsidyService.insertSubsidyName(subsidynameCustom);
            System.out.println(subsidynameCustom);
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE");
            return map;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //根据村选择补助类型
    @GetMapping("/susbidyInformation/querySubsidyTypeBYID/{id}")
    public Map querySubsidyTypeBYID(@PathVariable Integer id) {
        Map map = new HashMap();
        map.put("susbidyTypes", subsidyService.selectPolicyTypeBIId(id));
        return map;
    }

    //根据村(类型ID)选择补助项名称
    @GetMapping("/susbidyInformation/querySubsidyNameBYType/{type}")
    public Map querySubsidyNameBYType(@PathVariable Integer type) {
        Map map = new HashMap();
        map.put("susbidyNames", subsidyService.selectPolicyNameBYId(type));
        return map;
    }

    //国家政策
    @GetMapping("/susbidyInformation/queryPolicyNation")
    public Map queryPolicyNation() {
        Map map = new HashMap<>();
        map.put("policies", subsidyService.selectPolicyNationNameAdID());
        return map;
    }

    //国家政策ID
    @GetMapping("/susbidyInformation/queryPolicyNationById/{id}")
    public Map queryPolicyNationById(@PathVariable Integer id) {
        Map map = new HashMap<>();
        map.put("policiesNation", subsidyService.selectPolicyNationNameAdID(id));
        return map;
    }

    //实施政策
    @GetMapping("/susbidyInformation/queryPolicyShisHi")
    public Map queryPolicyShisHi() {
        Map map = new HashMap<>();
        map.put("policiesShisHi", subsidyService.selectPolicyShisHiNameAdID());
        return map;
    }

    //实施政策ID
    @GetMapping("/susbidyInformation/queryPolicyShisHiBYID/{id}")
    public Map queryPolicyShisHiBYID(@PathVariable Integer id) {
        Map map = new HashMap<>();
        map.put("policiesShisHi", subsidyService.selectPolicyShisHiNameAdID(id));
        return map;
    }
}
