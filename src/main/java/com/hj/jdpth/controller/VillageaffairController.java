package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Villageaffair;
import com.hj.jdpth.repository.VillageaffairMapper;
import com.hj.jdpth.service.impl.VillageaffairServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
public class VillageaffairController {
    @Autowired
    VillageaffairServiceImpl villageaffairService;
    @Autowired
    VillageaffairMapper villageaffairMapper;

    //搜索村事务
    @PostMapping("/affair/findAffair")
    public Map<String, Object> findAffair(
            @RequestParam Integer style,
            @RequestParam(required = false) Integer qu_id,
            @RequestParam(required = false) Integer zhen_id,
            @RequestParam(required = false) Integer cun_id,
            @RequestParam(required = false) String affairName,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> result = new HashMap<>();
        if (style != null && startPage != null && pageSize != null && (qu_id != null || zhen_id != null || cun_id != null || affairName != null)) {
            if (qu_id == null) {
                qu_id = 0;
            }
            if (zhen_id == null) {
                zhen_id = 0;
            }
            if (cun_id == null) {
                cun_id = 0;
            }
            result = villageaffairService.findAffair(style, qu_id, zhen_id, cun_id, affairName, startPage, pageSize);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    @GetMapping("/affair/findAffairXingQing_yp/{id}")//查询村事务详情
    public Map<String, Object> findAffairXingQing_yp(@PathVariable(value = "id") Integer id) {
        Map<String, Object> result = villageaffairService.findAffairXingQing_yp(id);
        return result;
    }

    //删除一条记录
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.VILLAGEAFFAIR)
    @DeleteMapping("/deleteaffair/deleteAffairById_yp/{id}/{managerId}")
    public Map<String, Object> deleteAffairById_yp(@PathVariable(value = "id") Integer id, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> result = villageaffairService.deleteAffairById_yp(id);
        return result;
    }

    //删除多条记录
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.VILLAGEAFFAIR)
    @DeleteMapping("/deleteManyAffair/deleteManyAffairById_yp/{Ids}/{managerId}")
    public Map<String, Object> deleteManyAffairById_yp(@PathVariable(value = "Ids") String Ids, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result = villageaffairService.deleteManyAffairById_yp(Ids);
        } catch (Exception e) {
            result.put("state", "error");
            result.put("data", null);
        }
        return result;
    }

//    //添加一条数据
//    @OperationLogDetail(operationType = OperationType.INSERT,operationUnit = OperationUnit.VILLAGEAFFAIR)
//    @PostMapping("/insert/insertAffair_yp")
//    public Map<String,Object> insertAffair_yp(Villageaffair villageaffair, @RequestParam Integer managerId){
//        Map<String,Object> result=villageaffairService.insertAffair_yp(villageaffair);
//        return result;
//    }
//
//    //编辑 更新一条数据
//    @OperationLogDetail(operationType = OperationType.UPDATE,operationUnit = OperationUnit.VILLAGEAFFAIR)
//    @PutMapping("/updateAffair/updateAffair_yp")
//    public Map<String,Object> updateAffair_yp(Villageaffair villageaffair, @RequestParam Integer managerId){
//        Map<String,Object> result=villageaffairService.updateAffair_yp(villageaffair);
//        return result;
//    }

    //导入Excel表
    @PostMapping("/daoru/daoruAffair_yp")
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.VILLAGEAFFAIR)
    public Map<String, Object> daoruAffair_yp(
            @RequestParam Integer managerId,
            @RequestParam("file") MultipartFile file
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Villageaffair> villageaffairs = new ArrayList<>();
            Workbook book = null;
            //判断是xls还是xlsx
            try {
                book = new XSSFWorkbook(file.getInputStream());
            } catch (Exception ex) {
                try {
                    book = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //获取一共有多少sheet,遍历
            int numberOfSheets = book.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = book.getSheetAt(i);
                //获取sheet中有多少行，遍历每一行
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                for (int j = 1; j < physicalNumberOfRows; j++) {
                    if (j == 1) {
                        continue;//标题行
                    }
                    Villageaffair villageaffair = new Villageaffair();
                    Row row = sheet.getRow(j);//获得当前行数据
                    Integer k = 0;
                    if (villageaffairService.findAffairByxuhao_yp(row.getCell(5).toString())) {
                        villageaffair.setVaTitle(row.getCell(3).toString());//会议名称
                        villageaffair.setVaTime(row.getCell(1).toString());
                        villageaffair.setVaVillageid((int) row.getCell(0).getNumericCellValue());
                        villageaffair.setVaContent(row.getCell(4).toString());
                        villageaffair.setVaXuhao(row.getCell(5).toString());
                        villageaffair.setVaPlace(row.getCell(2).toString());
                        k = (int) villageaffairService.insertAffair_yp(villageaffair).get("result");
                    }
                    if (k != 0) {
                        if (result.get("success") != null) {
                            result.put("success", result.get("success") + "," + String.valueOf(j));
                        } else {
                            result.put("success", String.valueOf(j));
                        }
                    } else {
                        if (result.get("error") != null) {
                            result.put("error", result.get("error") + "," + String.valueOf(j));
                        } else {
                            result.put("error", String.valueOf(j));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //2019-11-27

    //添加一条数据
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.VILLAGEAFFAIR)
    @PostMapping("/insert/insertAffair_yp")
    public Map<String, Object> insertAffair_yp(Villageaffair villageaffair, @RequestParam Integer managerId, @RequestParam(required = false) MultipartFile[] file) {
        if (file != null) {
            villageaffair.setVaPhoto(insertFileS(file));
        }
        Map<String, Object> result = villageaffairService.insertAffair_yp(villageaffair);
        return result;
    }

    //编辑 更新一条数据
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.VILLAGEAFFAIR)
    @PutMapping("/updateAffair/updateAffair_yp")
    public Map<String, Object> updateAffair_yp(Villageaffair villageaffair, @RequestParam Integer managerId, @RequestParam(required = false) MultipartFile[] file) {
        Villageaffair villageaffair1 = villageaffairMapper.findbyid_lfm(villageaffair.getVillageaffairId());
        if (file != null) {
            if (villageaffair1.getVaPhoto() != null) {
                villageaffair.setVaPhoto(updateFileS(file, villageaffair1.getVaPhoto()));
            } else {
                villageaffair.setVaPhoto(insertFileS(file));
            }
        }
        Map<String, Object> result = villageaffairService.updateAffair_yp(villageaffair);
        return result;
    }

    String insertFileS(MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        String name = "";
        for (MultipartFile file : files) {
            map = villageaffairService.uploadFile(file);
            name = name + map.get("path") + ",";
        }
        return name;
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
            map = villageaffairService.uploadFile(file);
            name = name + map.get("path") + ",";
        }
        return name;
    }
}
