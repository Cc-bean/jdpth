package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Jiaoyv;
import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.repository.JiaoyvzixunListMapper;
import com.hj.jdpth.service.FileUploadService;
import com.hj.jdpth.service.JiaoyvzixunListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class JiaoyvzixunListController {

    @Autowired
    JiaoyvzixunListService jiaoyvzixunListService;
    @Autowired(required = false)
    FileUploadService fileUploadService;
    @Autowired
    JiaoyvzixunListMapper jiaoyvzixunListMapper;

    @PostMapping("h/JiaoyvzixunListFirst")//初始进入
    public Map<String, Object> zuzhihuodongListFirst(@RequestParam Integer mRegion,//区
                                                     @RequestParam Integer mZhenid,//镇
                                                     @RequestParam Integer mVillageid,//村
                                                     @RequestParam Integer startPage,
                                                     @RequestParam Integer pageSize,
                                                     @RequestParam Integer mType//管理员类型
    ) {
        Map<String, Object> map = new HashMap<>();
        if (mType == null || mRegion == null || mZhenid == null || mVillageid == null) {
            map.put("data", "管理员null");
            map.put("state", "false");
        } else {
            map = jiaoyvzixunListService.GetInfoAllFirst(mRegion, mZhenid, mVillageid, startPage, pageSize, mType);
        }
        return map;
    }

    @PostMapping("h/JiaoyvzixunSelect")//查询、上下页、跳转
    public Map<String, Object> zuzhihuodongListSelect(@RequestParam Integer mRegion,//区
                                                      @RequestParam(required = false) Integer mZhenid,//镇
                                                      @RequestParam(required = false) Integer mVillageid,//村
                                                      @RequestParam Integer startPage,//当前页
                                                      @RequestParam Integer pageSize//每页显示条数
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = jiaoyvzixunListService.GetInfoSelect(mRegion, mZhenid, mVillageid, startPage, pageSize);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.JIAOYV)
    @DeleteMapping("h/JiaoyvzixunDeleteOne/{jiaoyvzixunId}/{managerId}")//单个删除
    public Map<String, Object> jiaoyvzixunDeleteOne(@PathVariable(value = "jiaoyvzixunId") Integer jiaoyvzixunId, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = jiaoyvzixunListService.DeleteOne(jiaoyvzixunId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.JIAOYV)
    @DeleteMapping("h/JiaoyvzixunDeleteMany/{jiaoyvzixunIds}/{managerId}")//多个删除
    public Map<String, Object> jiaoyvzixunDeleteMany(@PathVariable(value = "jiaoyvzixunIds") String jiaoyvzixunIds, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = jiaoyvzixunListService.DeleteMany(jiaoyvzixunIds);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.UPLOAD, operationUnit = OperationUnit.JIAOYV)
    @PostMapping(value = "h/JiaoyvFileUploadDoc")
    public Map<String, Object> FileUploadDoc(
            @RequestParam Integer managerId,
            @RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = fileUploadService.fileUpload(image, request);
            Manager manager = jiaoyvzixunListMapper.GetInfoManager(managerId);
            Jiaoyv jiaoyv = new Jiaoyv();
            jiaoyv.setJyLujing(String.valueOf(map.get("path")));
            jiaoyv.setJyZhenshilujing(String.valueOf(map.get("path")));
            jiaoyv.setJyNmaeoffile(String.valueOf(map.get("name")));
            jiaoyv.setJyZhen(manager.getmZhenid());
            jiaoyv.setJyPeople(manager.getmName());
            jiaoyv.setJyQu(1349);
            jiaoyv.setJySheng(16);
            jiaoyv.setJyShi(151);
            jiaoyv.setJyTime(new Date());
            jiaoyv.setJyVillage(manager.getmVillageid());
            System.out.println(jiaoyv);
            jiaoyvzixunListService.UploadFile(jiaoyv);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("state", "error");
        }
        return map;
    }
}
