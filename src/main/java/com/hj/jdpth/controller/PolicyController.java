
package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Policy;
import com.hj.jdpth.domain.Policytype;
import com.hj.jdpth.repository.PolicyMapper;
import com.hj.jdpth.service.PolicyService;
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
public class PolicyController {
    @Autowired
    private PolicyMapper policyMapper;
    @Autowired
    private PolicyService policyService;


    //    @PostMapping("/findPolicyByQuId")
//    public Map<String ,Object> findPolicyByQuId(
//
//                                                @RequestParam Integer quId,
//                                                @RequestParam Integer startPage,
//                                                 @RequestParam Integer pageSize
//
//    ){
//        Map<String , Object> map = new HashMap<>();
//        try {
//            map = policyService.findPolicyByQuId(quId,startPage, pageSize);
//        }catch (Exception e){
//            map.put("state","error");
//            map.put("data",null);
//        }
//        return map;
//    }
//    @PostMapping("/findPolicyByZhenId")
//    public Map<String ,Object> findPolicyByZhenId(
//
//                                                  @RequestParam Integer zhenId,
//                                                  @RequestParam Integer quId,
//                                                @RequestParam Integer startPage,
//                                                @RequestParam Integer pageSize
//
//    ){
//        Map<String , Object> map = new HashMap<>();
//        try {
//            map = policyService.findPolicyByZhenId(quId,zhenId,startPage, pageSize);
//        }catch (Exception e){
//            map.put("state","error");
//            map.put("data",null);
//        }
//        return map;
//    }
//
//    @PostMapping("/findPolicyByCunId")
//    public Map<String ,Object> findPolicyByCunId(
//
//                                                  @RequestParam Integer zhenId,
//                                                  @RequestParam Integer quId,
//                                                 @RequestParam Integer cunId,
//                                                 @RequestParam Integer startPage,
//                                                  @RequestParam Integer pageSize
//
//    ){
//        Map<String , Object> map = new HashMap<>();
//        try {
//            map = policyService.findPolicyByCunId(quId,zhenId,cunId,startPage, pageSize);
//        }catch (Exception e){
//            map.put("state","error");
//            map.put("data",null);
//        }
//        return map;
//    }
    @PostMapping("/findPolicy")
    public Map<String, Object> findPolicy(@RequestParam Integer policyId
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = policyService.findPolicy(policyId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    //    @PostMapping("/queryAll")
//    public Map<String ,Object> queryAll(
//            @RequestParam String time
//    ){
//        String[] strs=time.split("~");
//        String time1=strs[0];
//        String time2=strs[1];
//        Map<String , Object> map = new HashMap<>();
//        try {
//            Page<Policy> policy = policyMapper.queryAll(time1,time2);
//            map.put("data",policy);
//        }catch (Exception e){
//            map.put("state","error");
//            map.put("data",null);
//        }
//        return map;
//    }
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.POLICY)
    @DeleteMapping("/deletePolicy")
    public Map<String, Object> deletePolicy(@RequestParam Integer policyId,
                                            @RequestParam Integer managerId

    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = policyService.deletePolicy(policyId, managerId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.POLICY)
    @DeleteMapping("/deleteManyPolicy")
    public Map<String, Object> deleteManyPolicy(@RequestParam Integer[] policyId,
                                                @RequestParam Integer managerId

    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = policyService.deleteManyPolicy(policyId, managerId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @PostMapping("/zhen_List")
    public Map<String, Object> zhen_List(@RequestParam Integer quId
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = policyService.zhen_List(quId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @PostMapping("/cun_list")
    public Map<String, Object> cun_List(@RequestParam Integer zhenId
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = policyService.cun_list(zhenId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @PostMapping("/shi_List")
    public Map<String, Object> shi_List(@RequestParam Integer sId
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = policyService.shi_List(sId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @PostMapping("/qu_List")
    public Map<String, Object> qu_List(@RequestParam Integer shiId
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = policyService.qu_List(shiId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @PostMapping("/GetInfoFirst")//初始进入
    public Map<String, Object> GetInfoFirst(
            @RequestParam(required = false) Integer shengId,
            @RequestParam(required = false) Integer shiId,
            @RequestParam(required = false) Integer quId,
            @RequestParam(required = false) Integer zId,
            @RequestParam(required = false) Integer vId,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize,
            @RequestParam Integer managerId//管理员类型
    ) {
        Map<String, Object> result = new HashMap<>();
        if (managerId != null && startPage != null && pageSize != null && (quId != null || zId != null || vId != null || shengId != null || shiId != null)) {
            if (quId == null) {
                quId = 0;
            }
            if (zId == null) {
                zId = 0;
            }
            if (vId == null) {
                vId = 0;
            }
            result = policyService.GetInfoFirst(shengId, shiId, quId, zId, vId, managerId, startPage, pageSize);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.POLICY)
    @PostMapping("/InsertPolicy")//添加政策
    public Map<String, Object> InsertPolicy(
            @RequestParam Integer managerId,
            @RequestParam String pName,
            @RequestParam String pDocumentpath,
            @RequestParam Integer pType,
            @RequestParam String pDatetime,
            @RequestParam Integer pVillageid,
            @RequestParam Integer pZhenid,
            @RequestParam Integer pRegion,
            @RequestParam Integer pShi,
            @RequestParam Integer pSheng
    ) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(pDatetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        Policy j = new Policy();
        j.setpName(pName);
        j.setpDocumentpath(pDocumentpath);
        j.setpType(pType);
        j.setpDatetime(date);
        j.setpVillageid(pVillageid);
        j.setpZhenid(pZhenid);
        j.setpRegion(pRegion);
        j.setpShi(pShi);
        j.setpSheng(pSheng);

        int result = policyMapper.insertPolicy(j);
        if (result == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "error");
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.POLICY)
    @PostMapping("/FileUpload")////上传政策
    public Map<String, Object> insertPolicy(@RequestParam Integer managerId, Policytype policytype, Policy policy, MultipartFile policy_wenjian) {
        String result = policyService.fileUpload(policytype, policy, policy_wenjian);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

    @PostMapping("/queryPolicy")
    public Map<String, Object> queryPolicy(@RequestParam(required = false) Integer zId) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (zId == null) {
                Page<Policy> policy1 = policyMapper.queryAll(1);
                Page<Policy> policy2 = policyMapper.queryAll(2);
                Page<Policy> policy3 = policyMapper.queryAll(3);
                Page<Policy> policy4 = policyMapper.queryAll(4);
                Integer count = policyMapper.policyNumber();
                map.put("国家级政策", policy1);
                map.put("省厅级政策", policy2);
                map.put("市区级政策", policy3);
                map.put("镇村级政策", policy4);
                map.put("数量", count);
            } else {
                Page<Policy> policy1 = policyMapper.query(1, zId);
                Page<Policy> policy2 = policyMapper.query(2, zId);
                Page<Policy> policy3 = policyMapper.query(3, zId);
                Page<Policy> policy4 = policyMapper.query(4, zId);
                Integer count = policyMapper.policyNumberZhen(zId);
                map.put("国家级政策", policy1);
                map.put("省厅级政策", policy2);
                map.put("市区级政策", policy3);
                map.put("镇村级政策", policy4);
                map.put("数量", count);
            }

        } catch (Exception e) {
            map.put("data", "error");
        }
        return map;
    }

}














