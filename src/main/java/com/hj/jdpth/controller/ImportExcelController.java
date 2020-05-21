package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.service.ImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class ImportExcelController {
    @Autowired
    ImportExcelService importExcelService;

    //导入村干部信息
    @PostMapping("/ImportVillagecadres/{managerId}")
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.VILLAGECADRES)
    public Map<String, Object> ImportVillagecadres(MultipartFile file, @PathVariable Integer managerId) {
        Map map = new HashMap();
        if (importExcelService.isExcel(file.getOriginalFilename())) {
            map = importExcelService.ImportVillagecadres(file);
        } else {
            map.put("Error", "非Excel文件");
        }
        return map;
    }

    //导入村民信息
    @PostMapping("/ImportYongHu/{managerId}")
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.YONGHU)
    public Map<String, Object> ImportYongHu(MultipartFile file, @PathVariable Integer managerId) {
        Map map = new HashMap();
        if (importExcelService.isExcel(file.getOriginalFilename())) {
            map = importExcelService.ImportYongHu(file);
        } else {
            map.put("Error", "非Excel文件");
        }
        return map;
    }

    //导入行政村补助信息暂无
    //导入补助个人表
    @PostMapping("/ImportSubsidyProsonal/{managerId}")
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.SUBSIDYOBJECT)
    public Map<String, Object> ImportSubsidyProsonal(MultipartFile file, @PathVariable Integer managerId) {
        Map map = new HashMap();
        if (importExcelService.isExcel(file.getOriginalFilename())) {
            map = importExcelService.ImportSubsidyProsonal(file);
        } else {
            map.put("Error", "非Excel文件");
        }
        return map;
    }

    //导入补助家庭表
    @PostMapping("/ImportSubsidyFamily/{managerId}")
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.SUBSIDIZEDHOUSEHOLDS)
    public Map<String, Object> ImportSubsidyFamily(MultipartFile file, @PathVariable Integer managerId) {
        Map map = new HashMap();
        if (importExcelService.isExcel(file.getOriginalFilename())) {
            map = importExcelService.ImportSubsidyFamily(file);
        } else {
            map.put("Error", "非Excel文件");
        }
        return map;
    }
}
