package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.repository.VillageMapper;
import com.hj.jdpth.service.VillageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class VillageInfoController {

    @Autowired
    VillageInfoService villageInfoService;
    @Autowired
    VillageMapper villageMapper;

    @PostMapping(value = "/VillageInfoSearch")
    public Map<String, Object> VillageInfoSearch(@RequestParam(required = false) Integer mType,   //管理员类型
                                                 @RequestParam(required = false) Integer rKey,     //区id
                                                 @RequestParam(required = false) Integer zhenId,     //镇id
                                                 @RequestParam(required = false) Integer villageId,   //村id
                                                 @RequestParam(required = false) Integer startPage,
                                                 @RequestParam(required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (mType != null && startPage != null && pageSize != null) {
            map = villageInfoService.VillageInfoSearch(mType, rKey, zhenId, villageId, startPage, pageSize);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @GetMapping(value = "/VillageXQ/{id}")
    public Map<String, Object> VillageXQ(@PathVariable(value = "id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        if (id != null) {
//            map = villageInfoService.VillageXQ(id);
            Village village = villageMapper.FindById_lfm(id);
            map.put("data", village);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @PostMapping(value = "/VInsert")
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.VILLAGE)
    public Map<String, Object> VInsert(Village village, @RequestParam Integer managerId,
                                       @RequestParam(required = false) MultipartFile[] file
            , HttpServletResponse response) {
        Map<String, Object> map = villageInfoService.InsertV(village, file);
        map.put("length", file.length);
        if (map.get("statue").equals("success")) {
            response.setStatus(200);
        } else {
            //false
            response.setStatus(202);
        }
        if (map.get("error").equals("error")) {
            response.setStatus(501);
        }
        return map;
    }

    /*@OperationLogDetail(operationType = OperationType.UPDATE,operationUnit = OperationUnit.VILLAGE)
    @PostMapping(value = "/VUpdate")
    public  Map<String,Object> VUpdate(Village village, @RequestParam Integer managerId, MultipartFile[] file){
        Map<String,Object> map = new HashMap<>();
        try {
            map =villageInfoService.UpdateV(village,file);
        }catch (Exception e){
            map.put("state","error1");
            map.put("data",null);
        }
        return map;
    }*/

}
