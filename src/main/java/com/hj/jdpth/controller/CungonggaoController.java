package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Villagebulletin;
import com.hj.jdpth.service.impl.CungonggaoServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class CungonggaoController {
    @Autowired
    CungonggaoServiceImpl cungonggaoService;

    @PostMapping("/cunGongGao/findCunGongGao")
    public Map<String, Object> findCunGongGao(
            @RequestParam Integer style,
            @RequestParam(required = false) Integer qu_id,
            @RequestParam(required = false) Integer zhen_id,
            @RequestParam(required = false) Integer cun_id,
            @RequestParam(required = false) String gongGaoName,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> result = new HashMap<>();
        if (style != null && startPage != null && pageSize != null && (qu_id != null || zhen_id != null || cun_id != null || gongGaoName != null)) {
            //System.out.println("village"+mVillageid+"zhenId"+mZhenid);
            if (qu_id == null) {
                qu_id = 0;
            }
            if (zhen_id == null) {
                zhen_id = 0;
            }
            if (cun_id == null) {
                cun_id = 0;
            }
            result = cungonggaoService.findCunGongGao(style, qu_id, zhen_id, cun_id, gongGaoName, startPage, pageSize);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    @GetMapping("/gonggaoxiangqing/findgongGaoXingQing_yp/{id}")//查询村公告详情
    public Map<String, Object> findgongGaoXingQing_yp(@PathVariable(value = "id") Integer id) {
        Map<String, Object> result = cungonggaoService.findgongGaoXingQing_yp(id);
        return result;
    }

    //删除一条记录
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.VILLAGEBULLETIN)
    @DeleteMapping("/deletegonggao/deleteGonggaoById_yp/{id}/{managerId}")
    public Map<String, Object> deleteGonggaoById_yp(@PathVariable(value = "id") Integer id, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> result = cungonggaoService.deleteGonggaoById_yp(id);
        return result;
    }

    //删除多条记录
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.VILLAGEBULLETIN)
    @DeleteMapping("/deleteManyGonggao/deleteManyGonggaoById_yp/{Ids}/{managerId}")
    public Map<String, Object> deleteManyGonggaoById_yp(@PathVariable(value = "Ids") String Ids, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result = cungonggaoService.deleteManyGonggaoById_yp(Ids);
        } catch (Exception e) {
            result.put("state", "error");
            result.put("data", null);
        }
        return result;
    }

    //添加一条数据
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.VILLAGEBULLETIN)
    @PostMapping("/insert/insertGonggao_yp")
    public Map<String, Object> insertGonggao_yp(Villagebulletin villagebulletin, @RequestParam Integer managerId, @RequestParam String ggTime) {
        Map<String, Object> result = cungonggaoService.insertGonggao_yp(villagebulletin, ggTime);
        return result;
    }

    //编辑 更新一条数据
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.VILLAGEBULLETIN)
    @PutMapping("/updategonggao/updateGonggao_yp")
    public Map<String, Object> updateGonggao_yp(Villagebulletin villagebulletin, @RequestParam Integer managerId, @RequestParam String ggTime) {
        Map<String, Object> result = cungonggaoService.updateGonggao_yp(villagebulletin, ggTime);
        return result;
    }

    //导入Excel表
    @PostMapping(value = "/daoru/daoruGonggao_yp")
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.VILLAGEBULLETIN)
    public Map<String, Object> daoruGonggao_yp(
            @RequestParam Integer managerId,
            @RequestParam("file") MultipartFile file
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Villagebulletin> villagebulletins = new ArrayList<>();
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
                    Villagebulletin villagebulletin = new Villagebulletin();
                    Row row = sheet.getRow(j);//获得当前行数据
                    Integer k = 0;
                    if (cungonggaoService.findgonggaoByxuhao_yp(row.getCell(4).toString())) {
                        villagebulletin.setVbTitle(row.getCell(1).toString());//公告名称
                        if (row.getCell(3) != null) {
                            villagebulletin.setVbLanchtime((Date) sdf.parse(row.getCell(3).getStringCellValue()));
                        }
                        villagebulletin.setVbVillageid((int) row.getCell(0).getNumericCellValue());
                        villagebulletin.setVbContent(row.getCell(2).toString());
                        villagebulletin.setVbXuhao(row.getCell(4).toString());
                        villagebulletin.setVbLanchpersonid(managerId);
                        k = (int) cungonggaoService.daoruGonggao_yp(villagebulletin).get("result");
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
