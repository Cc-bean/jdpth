package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Hukouqianyi;
import com.hj.jdpth.service.HukouqianyiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class HukouqianyiController {

    @Autowired
    HukouqianyiService hukouqianyiService;

    @PostMapping(value = "/QianYiSearch")
    public Map<String, Object> QianYiSearch(@RequestParam(required = false) Integer mType,   //管理员类型
                                            @RequestParam(required = false) Integer rKey,     //区id
                                            @RequestParam(required = false) Integer zhenId,     //镇id
                                            @RequestParam(required = false) Integer villageId,     //村id
                                            @RequestParam(required = false) Integer hkqylxId,  //迁入迁出类型
                                            @RequestParam(required = false) String time,     //时间
                                            @RequestParam(required = false) Integer startPage,
                                            @RequestParam(required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (mType != null && startPage != null && pageSize != null && hkqylxId != null) {
            map = hukouqianyiService.QianYiSearch(mType, rKey, zhenId, villageId, hkqylxId, time, startPage, pageSize);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @GetMapping(value = "/HukouqianyiInfo/{HKQY_Id}")
    public Map<String, Object> HukouqianyiInfo(@PathVariable(value = "HKQY_Id") Integer HKQY_Id) {
        Map<String, Object> map = new HashMap<>();
        if (HKQY_Id != null) {
            map = hukouqianyiService.QianYiXQ(HKQY_Id);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.HUKOUQIANYI)
    @DeleteMapping(value = "/HkqyDeleteone/{hkqyId}/{managerId}")  //单删
    public Map<String, Object> HkqyDeleteone(@PathVariable(value = "hkqyId") Integer hkqyId, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = hukouqianyiService.DeleteOne(hkqyId);
        } catch (Exception e) {
            map.put("state", "error1");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.HUKOUQIANYI)
    @DeleteMapping(value = "/HkqyDeleteMany/{hkqyIds}/{managerId}")
    public Map<String, Object> HkqyDeleteMany(@PathVariable(value = "hkqyIds") String hkqyIds,
                                              @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = hukouqianyiService.DeleteMany(hkqyIds);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.HUKOUQIANYI)
    @PostMapping(value = "/InsertHkqy")
    public Map<String, Object> InsertHkqy(Hukouqianyi hukouqianyi, @RequestParam Integer managerId, MultipartFile[] file, @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = hukouqianyiService.InsertHkqy(hukouqianyi, file, time);
        } catch (Exception e) {
            map.put("state", "error1");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.HUKOUQIANYI)
    @PostMapping(value = "/UpdateHkqy")
    public Map<String, Object> UpdateHkqy(Hukouqianyi hukouqianyi, @RequestParam Integer managerId, MultipartFile[] file, @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = hukouqianyiService.UpdateHkqy(hukouqianyi, file, time);
        } catch (Exception e) {
            map.put("state", "error1");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.HUKOUQIANYI)
    @PostMapping(value = "/InsertHkqy2")
    public Map<String, Object> InsertHkqy2(Hukouqianyi hukouqianyi, @RequestParam Integer managerId, @RequestParam(required = false) MultipartFile[] file, @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (file != null) {
            hukouqianyi.setHkqyZhengming(insertFileS(file));
        }
        if (time != null) {
            Date date = null;
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hukouqianyi.setHkqyTime(date);
        }
        map = hukouqianyiService.InsertHkqy2(hukouqianyi);
        return map;
    }

    String insertFileS(MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        String name = "";
        for (MultipartFile file : files) {
            map = hukouqianyiService.uploadFile(file);
            name = name + map.get("path") + ",";
        }
        return name;
    }

}
