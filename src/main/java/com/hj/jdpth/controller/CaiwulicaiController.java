package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Capital;
import com.hj.jdpth.domain.Capitaldetails;
import com.hj.jdpth.domain.Licaiqingkuang;
import com.hj.jdpth.repository.CaiwulicaiMapper;
import com.hj.jdpth.service.CaiwulicaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class CaiwulicaiController {
    @Autowired
    CaiwulicaiService caiwulicaiService;
    @Autowired
    CaiwulicaiMapper caiwulicaiMapper;

    @PostMapping("/caiwu/findlicai")
    public Map<String, Object> findlicai(
            @RequestParam Integer mRegion,
            @RequestParam(required = false) Integer mVillageid,
            @RequestParam(required = false) Integer mZhenid,
            @RequestParam(required = false) Integer zuId,
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
        if (zuId == null) {
            zuId = 0;
        }
        if (mType == null) {
            mType = 0;
        }
        result = caiwulicaiService.findlicai(mRegion, mZhenid, mVillageid, zuId, mType, startPage, pageSize);
        return result;
    }

    @GetMapping("/caiwu/findZu/{villageid}")
    public Map<String, Object> findZu(
            @PathVariable(value = "villageid") Integer villageid
    ) {
        Map<String, Object> result = new HashMap<>();
        result = caiwulicaiService.findZu(villageid);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.LICAIQINGKUANG)
    @DeleteMapping("/caiwu/deleteLicai")
    public Map<String, Object> deleteLicai(
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer LC_Id
    ) {
        Map<String, Object> result = new HashMap<>();
        result = caiwulicaiService.deleteLicai(LC_Id);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.LICAIQINGKUANG)
    @DeleteMapping("/caiwu/deleteManyLicai/{LC_Id}/{managerId}")
    public Map<String, Object> deleteManyLicai(
            @PathVariable(value = "managerId", required = false) Integer managerId,
            @PathVariable(value = "LC_Id") String LC_Id
    ) {
        Map<String, Object> result = new HashMap<>();
        String[] deleteId = LC_Id.split(",");
        result = caiwulicaiService.deleteManyLicai(deleteId);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.LICAIQINGKUANG)
    @RequestMapping("/caiwu/insertLicai")
    public Map<String, Object> insertLicai(
            @RequestParam(required = false) Integer managerId,
            @RequestParam(value = "file", required = false) MultipartFile[] file,
            @RequestParam Integer LC_zu,
            @RequestParam String LC_name,
            @RequestParam String LC_year,
            @RequestParam Integer LC_month
    ) {
        Map<String, Object> result = new HashMap<>();
        Licaiqingkuang licaiqingkuang = new Licaiqingkuang();
        String path = "";
        long startTime = System.currentTimeMillis();
        //用来检测程序运行时间
        for (MultipartFile file1 : file) {
            if (file1.getOriginalFilename() != "") {
                result = caiwulicaiService.uploadFile(file1);
                path = path + result.get("path") + ",";
            }
        }
        licaiqingkuang.setLcLujing(path);
        licaiqingkuang.setLcZu(LC_zu);
        licaiqingkuang.setLcName(LC_name);
        licaiqingkuang.setLcYear(Short.valueOf(LC_year));
        licaiqingkuang.setLcMonth(LC_month);
        if (caiwulicaiMapper.insertLicai(licaiqingkuang) > 0) {
            result.put("status", "success");
            result.put("data", "新增成功");
        } else {
            result.put("status", "null");
            result.put("data", "新增失败");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法一的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        result.put("runtime", String.valueOf(endTime - startTime));
        return result;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.LICAIQINGKUANG)
    @PutMapping("/caiwu/updataLicai")
    public Map<String, Object> updataLicai(
            @RequestParam Integer licaiId,
            @RequestParam(required = false) Integer managerId,
            @RequestParam(value = "file", required = false) MultipartFile[] file,
            @RequestParam Integer LC_zu,
            @RequestParam String LC_name,
            @RequestParam String LC_year,
            @RequestParam Integer LC_month
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            Licaiqingkuang licaiqingkuang = caiwulicaiMapper.findLicaiById(licaiId);
            if (LC_zu != null) {
                licaiqingkuang.setLcZu(LC_zu);
            }
            if (LC_name != null) {
                licaiqingkuang.setLcName(LC_name);
            }
            if (LC_year != null) {
                licaiqingkuang.setLcYear(Short.valueOf(LC_year));
            }
            if (LC_month != null) {
                licaiqingkuang.setLcMonth(LC_month);
            }
            if (file != null) {
                String name = "";
                for (MultipartFile files : file) {
                    Map<String, Object> map = new HashMap<>();
                    map = caiwulicaiService.uploadFile(files);
                    name = name + map.get("path") + ",";
                }
                String[] lujing = licaiqingkuang.getLcLujing().split(",");
                for (String lujing1 : lujing) {
                    File file1 = new File(lujing1);
                    file1.delete();
                }
                licaiqingkuang.setLcLujing(name);
            }
            if (caiwulicaiMapper.updataLicai(licaiqingkuang) > 0) {
                result.put("data", "修改成功");
            } else {
                result.put("data", "修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("data", "出现错误!");
            result.put("state", "false");
        }
        return result;
    }
}
