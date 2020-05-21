package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Operationmanagement;
import com.hj.jdpth.repository.CaiwuOperationManagementMapper;
import com.hj.jdpth.service.CaiwuOperationManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class CaiwuOperationManagementController {
    @Autowired
    CaiwuOperationManagementService caiwuOperationManagementService;
    @Autowired
    CaiwuOperationManagementMapper caiwuOperationManagementMapper;

    @PostMapping("/caiwu/findOperationManagement")
    public Map<String, Object> findOperationManagement(
            @RequestParam Integer mRegion,
            @RequestParam(required = false) Integer mVillageid,
            @RequestParam(required = false) Integer mZhenid,
            @RequestParam(required = false) Integer mType,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> result = new HashMap<>();
        if (mZhenid == null) {
            mZhenid = 0;
        }
        if (mVillageid == null) {
            mVillageid = 0;
        }
        if (mType == null) {
            mType = 0;
        }
        result = caiwuOperationManagementService.findOperationManagement(mRegion, mZhenid, mVillageid, mType, startPage, pageSize);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.OPERATIONMANAGEMENT)
    @DeleteMapping("/caiwu/deleteOperationManagement")
    public Map<String, Object> deleteOperationManagement(
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer operationmanagementId
    ) {
        Map<String, Object> result = new HashMap<>();
        result = caiwuOperationManagementService.deleteOperationManagement(operationmanagementId);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.OPERATIONMANAGEMENT)
    @DeleteMapping("/caiwu/deleteManyOperationManagement/{operationmanagementId}/{managerId}")
    public Map<String, Object> deleteManyOperationManagement(
            @PathVariable(value = "managerId", required = false) Integer managerId,
            @PathVariable(value = "operationmanagementId") String operationmanagementId
    ) {
        Map<String, Object> result = new HashMap<>();
        String[] deleteId = operationmanagementId.split(",");
        result = caiwuOperationManagementService.deleteManyOperationManagement(deleteId);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.OPERATIONMANAGEMENT)
    @PostMapping("/caiwu/updateOperationManagement")
    public Map<String, Object> updateOperationManagement(
            @RequestParam Integer OperationManagement_Id,//项目建设id
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer Village_Id,//村名idx
            @RequestParam String OM_Name,//项目名称x
            @RequestParam(required = false) Boolean OM_Implementation,//是否实施
            @RequestParam(required = false) String OM_TenderingMaterial,//项目编号x
            @RequestParam(required = false) String OM_ImplementingParty,//实施方x
            @RequestParam(required = false) String OM_SourceOfFunds,//是否立项x
            @RequestParam(required = false) String OM_ImplementThePlan,//是否招标X
            @RequestParam(required = false) String OM_Acceptanceparty,//验收方x
            @RequestParam(required = false) String OM_FollowUpManagement,//监理方x
            @RequestParam(required = false) String OM_DivisionOfResponsibility,//工程支付情况x
            @RequestParam(required = false) String OM_Type,//备注x
            @RequestParam(required = false) String OM_TM_beiyong1,//会议介绍
            @RequestParam(required = false) MultipartFile[] OM_TM_beiyong2//合同照片
//            @RequestParam(required = false) MultipartFile[] OM_TM_ZhaoBiaoWenDang,//招标文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_ShiShiWenDang,//实施方文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_SourceOfFundsFile,//资金来源文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_AcceptancePartyFile,//验收方文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_FollowUpManagementFile,//后续管理文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_DivisionOfResponsibilityFile//责任划分文档
    ) {
        Map<String, Object> result = new HashMap<>();
        Operationmanagement operationmanagement = caiwuOperationManagementMapper.findOperationManagementById(OperationManagement_Id);
        operationmanagement.setOmVillageid(Village_Id);
        operationmanagement.setOmName(OM_Name);//项目名称x
        operationmanagement.setOmImplementation(OM_Implementation);//是否实施
        operationmanagement.setOmTenderingmaterial(OM_TenderingMaterial);//项目编号x
        operationmanagement.setOmImplementingparty(OM_ImplementingParty);//实施方x
        operationmanagement.setOmSourceoffunds(OM_SourceOfFunds);//是否立项x
        operationmanagement.setOmImplementtheplan(OM_ImplementThePlan);//是否招标X
        operationmanagement.setOmAcceptanceparty(OM_Acceptanceparty);//验收方x
        operationmanagement.setOmFollowupmanagement(OM_FollowUpManagement);//监理方x
        if (OM_TM_beiyong1 != null) {
            operationmanagement.setOmTmBeiyong1(OM_TM_beiyong1);//会议介绍
        }
        if (OM_DivisionOfResponsibility != null) {
            operationmanagement.setOmDivisionofresponsibility(OM_DivisionOfResponsibility);//工程支付情况x
        }
        if (OM_Type != null) {
            operationmanagement.setOmType(OM_Type);//备注x
        }
        if (OM_TM_beiyong2 != null) {
            operationmanagement.setOmTmBeiyong2(updateFileS(OM_TM_beiyong2, operationmanagement.getOmTmBeiyong2()));
        }
//        if (OM_TM_ZhaoBiaoWenDang != null) {
//            operationmanagement.setOmTmZhaobiaowendang(updateFileS(OM_TM_ZhaoBiaoWenDang, operationmanagement.getOmTmZhaobiaowendang()));
//        }
//        if (OM_TM_ShiShiWenDang != null) {
//            operationmanagement.setOmTmShishiwendang(updateFileS(OM_TM_ShiShiWenDang, operationmanagement.getOmTmShishiwendang()));
//        }
//        if (OM_TM_SourceOfFundsFile != null) {
//            operationmanagement.setOmTmSourceoffundsfile(updateFileS(OM_TM_SourceOfFundsFile, operationmanagement.getOmTmSourceoffundsfile()));
//        }
//        if (OM_TM_AcceptancePartyFile != null) {
//            operationmanagement.setOmTmAcceptancepartyfile(updateFileS(OM_TM_AcceptancePartyFile, operationmanagement.getOmTmAcceptancepartyfile()));
//        }
//        if (OM_TM_FollowUpManagementFile != null) {
//            operationmanagement.setOmTmFollowupmanagementfile(updateFileS(OM_TM_FollowUpManagementFile, operationmanagement.getOmTmFollowupmanagementfile()));
//        }
//        if (OM_TM_DivisionOfResponsibilityFile != null) {
//            operationmanagement.setOmTmDivisionofresponsibilityfile(updateFileS(OM_TM_DivisionOfResponsibilityFile, operationmanagement.getOmTmDivisionofresponsibilityfile()));
//        }
        result = caiwuOperationManagementService.updateOperationManagement(operationmanagement);
        return result;
    }

    String updateFileS(MultipartFile[] files, String lujings) {
        Map<String, Object> map = new HashMap<>();
        String name = "";
        String[] lujing = lujings.split(",");
        for (String lujing1 : lujing) {
            File file = new File("E:\\HJResourse\\OperationManagement\\" + lujing1);
            file.delete();
        }
        for (MultipartFile file : files) {
            map = caiwuOperationManagementService.uploadFile(file);
            name = name + map.get("path") + ",";
        }
        return name;
    }

    String insertFileS(MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        String name = "";
        for (MultipartFile file : files) {
            map = caiwuOperationManagementService.uploadFile(file);
            name = name + map.get("path") + ",";
        }
        System.out.println(name);
        return name;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.OPERATIONMANAGEMENT)
    @PostMapping("/caiwu/insertOperationManagement")
    public Map<String, Object> insertOperationManagement(
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer Village_Id,//村名idx
            @RequestParam String OM_Name,//项目名称x
            @RequestParam(required = false) Boolean OM_Implementation,//是否实施
            @RequestParam(required = false) String OM_TenderingMaterial,//项目编号x
            @RequestParam(required = false) String OM_ImplementingParty,//实施方x
            @RequestParam(required = false) String OM_SourceOfFunds,//是否立项x
            @RequestParam(required = false) String OM_ImplementThePlan,//是否招标X
            @RequestParam(required = false) String OM_Acceptanceparty,//验收方x
            @RequestParam(required = false) String OM_FollowUpManagement,//监理方x
            @RequestParam(required = false) String OM_DivisionOfResponsibility,//工程支付情况x
            @RequestParam(required = false, defaultValue = "") String OM_Type,//备注x
            @RequestParam(required = false) String OM_TM_beiyong1,//会议介绍
            @RequestParam(required = false) MultipartFile[] OM_TM_beiyong2//合同照片
//            @RequestParam(required = false) MultipartFile[] OM_TM_ZhaoBiaoWenDang,//招标文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_ShiShiWenDang,//实施方文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_SourceOfFundsFile,//资金来源文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_AcceptancePartyFile,//验收方文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_FollowUpManagementFile,//后续管理文档
//            @RequestParam(required = false) MultipartFile[] OM_TM_DivisionOfResponsibilityFile//责任划分文档
    ) {
        Map<String, Object> result = new HashMap<>();
        Operationmanagement operationmanagement = new Operationmanagement();
        operationmanagement.setOmVillageid(Village_Id);
        operationmanagement.setOmName(OM_Name);//项目名称x
        operationmanagement.setOmImplementation(OM_Implementation);//是否实施
        operationmanagement.setOmTenderingmaterial(OM_TenderingMaterial);//项目编号x
        operationmanagement.setOmImplementingparty(OM_ImplementingParty);//实施方x
        operationmanagement.setOmSourceoffunds(OM_SourceOfFunds);//是否立项x
        operationmanagement.setOmImplementtheplan(OM_ImplementThePlan);//是否招标X
        operationmanagement.setOmAcceptanceparty(OM_Acceptanceparty);//验收方x
        operationmanagement.setOmFollowupmanagement(OM_FollowUpManagement);//监理方x
        if (OM_TM_beiyong1 != null) {
            operationmanagement.setOmTmBeiyong1(OM_TM_beiyong1);//会议介绍
        }
        if (OM_DivisionOfResponsibility != null) {
            operationmanagement.setOmDivisionofresponsibility(OM_DivisionOfResponsibility);//工程支付情况x
        }
        if (OM_Type != null) {
            operationmanagement.setOmType(OM_Type);//备注x
        }
        if (OM_TM_beiyong2 != null) {
            operationmanagement.setOmTmBeiyong2(insertFileS(OM_TM_beiyong2));
        }
//        if (OM_TM_ZhaoBiaoWenDang != null) {
//            operationmanagement.setOmTmZhaobiaowendang(insertFileS(OM_TM_ZhaoBiaoWenDang));
//        }
//        if (OM_TM_ShiShiWenDang != null) {
//            operationmanagement.setOmTmShishiwendang(insertFileS(OM_TM_ShiShiWenDang));
//        }
//        if (OM_TM_SourceOfFundsFile != null) {
//            operationmanagement.setOmTmSourceoffundsfile(insertFileS(OM_TM_SourceOfFundsFile));
//        }
//        if (OM_TM_AcceptancePartyFile != null) {
//            operationmanagement.setOmTmAcceptancepartyfile(insertFileS(OM_TM_AcceptancePartyFile));
//        }
//        if (OM_TM_FollowUpManagementFile != null) {
//            operationmanagement.setOmTmFollowupmanagementfile(insertFileS(OM_TM_FollowUpManagementFile));
//        }
//        if (OM_TM_DivisionOfResponsibilityFile != null) {
//            operationmanagement.setOmTmDivisionofresponsibilityfile(insertFileS(OM_TM_DivisionOfResponsibilityFile));
//        }
        result = caiwuOperationManagementService.insertOperationManagement(operationmanagement);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.OPERATIONMANAGEMENT)
    @PostMapping("/caiwu/importOperationManagementExcel")
    public Map<String, Object> importOperationManagementExcel(
            @RequestParam("file") MultipartFile file,
            @RequestParam(required = false) Integer managerId) {
        Map<String, Object> result = new HashMap<>();
        //用来检测程序运行时间
        long startTime = System.currentTimeMillis();
        try {
            String path = "";
            result = caiwuOperationManagementService.uploadFile(file);
            try {
                path = "E:\\HJResourse\\OperationManagement\\" + result.get("path");
                result = caiwuOperationManagementService.importExcel(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            File file1 = new File(path);
            file1.delete();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法一的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        result.put("runtime", String.valueOf(endTime - startTime));
        return result;
    }
}
