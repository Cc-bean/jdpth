package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.repository.VillageMapper;
import com.hj.jdpth.service.BasicVillageService;
import com.hj.jdpth.service.VillageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class BasicVillageController {
    @Autowired
    BasicVillageService basicVillageService;
    @Autowired
    VillageInfoService villageInfoService;
    @Autowired
    VillageMapper villageMapper;

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.VILLAGE)
    @DeleteMapping(value = "/VillageDeleteone/{Id}/{managerId}")  //单删
    public Map<String, Object> VillageDeleteone(@PathVariable(value = "Id") Integer Id, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = basicVillageService.DeleteOne(Id);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.VILLAGE)
    @DeleteMapping(value = "/VillageDeleteMany/{Id}/{managerId}")
    public Map<String, Object> VillageDeleteMany(@PathVariable(value = "Id") String Id, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = basicVillageService.DeleteMany(Id);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping(value = "/VInsert2")
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.VILLAGE)
    public Map<String, Object> VInsert2(Village village, @RequestParam Integer managerId,
                                        @RequestParam(required = false) MultipartFile[] file) {
        Map<String, Object> map = new HashMap<>();
        if (file != null) {
            village.setvImagepath1(insertFileS(file));
        }
        map = villageInfoService.InsertV2(village);
        return map;
    }

    String insertFileS(MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        String name = "";
        for (MultipartFile file : files) {
            map = villageInfoService.uploadFile(file);
            name = name + map.get("path") + ",";
        }
        return name;
    }

    @PostMapping(value = "/VUpdate")
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.VILLAGE)
    public Map<String, Object> VUpdate(Village village, @RequestParam Integer managerId,
                                       @RequestParam(required = false) MultipartFile[] file) {
        Map<String, Object> map = new HashMap<>();
        Village v = villageMapper.FindById_lfm(village.getVillageId());
        if (file != null) {
            if (v.getvImagepath1() != null) {
                village.setvImagepath1(updateFileS(file, v.getvImagepath1()));
            } else {
                village.setvImagepath1(insertFileS(file));
            }
        }
        map = villageInfoService.UpdateV2(village);
        return map;
    }

    String updateFileS(MultipartFile[] files, String lujings) {
        Map<String, Object> map = new HashMap<>();
        String name = "";
        String[] lujing = lujings.split(",");
        for (String lujing1 : lujing) {
            File file = new File(lujing1);
            file.delete();
        }
        for (MultipartFile file : files) {
            map = villageInfoService.uploadFile(file);
            name = name + map.get("path") + ",";
        }
        return name;
    }


}
