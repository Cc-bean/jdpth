package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Cunfazhan;
import com.hj.jdpth.service.impl.CunfazhanServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
public class CunfazhanController {
    @Autowired
    CunfazhanServiceImpl cunfazhanService;

    //搜索村发展规划
    @PostMapping(value = "/cunFaZhan/findCFZ")
    public Map<String, Object> findCunFaZhan(
            @RequestParam Integer style,
            @RequestParam(required = false) Integer qu_id,
            @RequestParam(required = false) Integer zhen_id,
            @RequestParam(required = false) Integer cun_id,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> result = new HashMap<>();
        if (style != null && startPage != null && pageSize != null && (qu_id != null || zhen_id != null || cun_id != null)) {
            if (qu_id == null) {
                qu_id = 0;
            }
            if (zhen_id == null) {
                zhen_id = 0;
            }
            if (cun_id == null) {
                cun_id = 0;
            }
            result = cunfazhanService.findCunFaZhan(style, qu_id, zhen_id, cun_id, startPage, pageSize);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    @GetMapping("/fazhanxiangqing/findFazhanXingQing_yp/{id}")//查询村发展规划详情
    public Map<String, Object> findFazhanXingQing_yp(@PathVariable(value = "id") Integer id) {
        Map<String, Object> result = cunfazhanService.findFazhanXingQing_yp(id);
        return result;
    }

    //删除一条记录
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.CUNFAZHAN)
    @DeleteMapping("/deletefazhan/deleteFazhanById_yp/{id}/{managerId}")
    public Map<String, Object> deleteFazhanById_yp(@PathVariable(value = "id") Integer id, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> result = cunfazhanService.deleteFazhanById_yp(id);
        return result;
    }

    //删除多条记录
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.CUNFAZHAN)
    @DeleteMapping("/deleteManyfazhan/deleteManyFazhanById_yp/{Ids}/{managerId}")
    public Map<String, Object> deleteManyFazhanById_yp(@PathVariable(value = "Ids") String Ids, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result = cunfazhanService.deleteManyFazhanById_yp(Ids);
        } catch (Exception e) {
            result.put("state", "error");
            result.put("data", null);
        }
        return result;
    }

    //添加一条数据
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.CUNFAZHAN)
    @PostMapping("/insert/insertFazhan_yp")
    public Map<String, Object> insertFazhan_yp(Cunfazhan cunfazhan, @RequestParam Integer managerId) {
        Map<String, Object> result = cunfazhanService.insertFazhan_yp(cunfazhan);
        return result;
    }

    //编辑 更新一条数据
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.CUNFAZHAN)
    @PutMapping("/updatefazhan/updateFazhan_yp")
    public Map<String, Object> updateFazhan_yp(Cunfazhan cunfazhan, @RequestParam Integer managerId) {
        Map<String, Object> result = cunfazhanService.updateFazhan_yp(cunfazhan);
        return result;
    }

    //导入Excel表
    @PostMapping("/daoru/daoruFazhan_yp")
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.CUNFAZHAN)
    public Map<String, Object> daoruFazhan_yp(
            @RequestParam Integer managerId,
            @RequestParam("file") MultipartFile file
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Cunfazhan> cunfazhans = new ArrayList<>();
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
                    Cunfazhan cunfazhan = new Cunfazhan();
                    Row row = sheet.getRow(j);//获得当前行数据
                    Integer k = 0;
                    if (cunfazhanService.findFazhanByxuhao_yp(row.getCell(2).toString())) {
                        cunfazhan.setCfzVillage((int) row.getCell(0).getNumericCellValue());
                        cunfazhan.setCfzContent(row.getCell(1).toString());
                        cunfazhan.setCfzXuhao(row.getCell(2).toString());
                        k = (int) cunfazhanService.insertFazhan_yp(cunfazhan).get("result");
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

}
