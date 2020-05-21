package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Jijifenzi;
import com.hj.jdpth.domain.Qingkuangbiangeng;
import com.hj.jdpth.repository.QingkuangbiangengMapper;
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
public class QingkuangbiangengController {
    @Autowired
    private QingkuangbiangengMapper qingkuangbiangengMapper;

    @PostMapping("/pageFindQingkuangbiangengByZId")//根据镇id查询情况变更
    public Map<String, Object> pageFindQingkuangbiangengByZId(
            @RequestParam Integer zId,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {

        PageHelper.startPage(startPage, pageSize);
        Page<Qingkuangbiangeng> QingkuangbiangengPage = qingkuangbiangengMapper.pageFindQingkuangbiangengByZId(zId);
        Map<String, Object> result = new HashMap<>();
        result.put("record", QingkuangbiangengPage);
        //总页数
        result.put("total", QingkuangbiangengPage.getPages());
        //记录总数
        result.put("count", QingkuangbiangengPage.getTotal());
        //当前第几页
        result.put("nowPage", QingkuangbiangengPage.getPageNum());
        return result;
    }

    @PostMapping("/pageFindQingkuangbiangengByZIdandVId")//根据镇id和村id查询情况变更
    public Map<String, Object> pageFindQingkuangbiangengByZIdandVId(
            @RequestParam Integer zId,
            @RequestParam Integer vId,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {

        PageHelper.startPage(startPage, pageSize);
        Page<Qingkuangbiangeng> QingkuangbiangengPage = qingkuangbiangengMapper.pageFindQingkuangbiangengByZIdandVId(zId, vId);
        Map<String, Object> result = new HashMap<>();
        result.put("record", QingkuangbiangengPage);
        //总页数
        result.put("total", QingkuangbiangengPage.getPages());
        //记录总数
        result.put("count", QingkuangbiangengPage.getTotal());
        //当前第几页
        result.put("nowPage", QingkuangbiangengPage.getPageNum());
        return result;
    }

    @PostMapping("/pageFindQingkuangbiangengByZIdandVIdandTime")//根据镇id和村id和时间查询情况变更
    public Map<String, Object> pageFindQingkuangbiangengByZIdandVIdandTime(
            @RequestParam Integer zId,
            @RequestParam Integer vId,
            @RequestParam String time,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {

        PageHelper.startPage(startPage, pageSize);
        Page<Qingkuangbiangeng> QingkuangbiangengPage = qingkuangbiangengMapper.pageFindQingkuangbiangengByZIdandVIdandTime(zId, vId, time);
        Map<String, Object> result = new HashMap<>();
        result.put("record", QingkuangbiangengPage);
        //总页数
        result.put("total", QingkuangbiangengPage.getPages());
        //记录总数
        result.put("count", QingkuangbiangengPage.getTotal());
        //当前第几页
        result.put("nowPage", QingkuangbiangengPage.getPageNum());
        return result;
    }

    @PostMapping("/selectAllqingKuangBianGeng")//查询所有情况变更
    public Map<String, Object> selectAllqingKuangBianGeng(
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        PageHelper.startPage(startPage, pageSize);
        Page<Qingkuangbiangeng> QingkuangbiangengPage = qingkuangbiangengMapper.selectAllqingKuangBianGeng();
        Map<String, Object> result = new HashMap<>();
        result.put("record", QingkuangbiangengPage);
        //总页数
        result.put("total", QingkuangbiangengPage.getPages());
        //记录总数
        result.put("count", QingkuangbiangengPage.getTotal());
        //当前第几页
        result.put("nowPage", QingkuangbiangengPage.getPageNum());
        return result;
    }

    @PostMapping("/selectQingkuangbiangeng")//详情
    public Map<String, Object> selectQingkuangbiangeng(
            @RequestParam Integer qkbgId
    ) {
        Map<String, Object> map = new HashMap<>();
        Qingkuangbiangeng qingkuangbiangeng = qingkuangbiangengMapper.queryAll(qkbgId);
        if (qingkuangbiangeng == null) {
            map.put("record", "此人不存在");
        }
        map.put("record", qingkuangbiangeng);
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.QINGKUANGBIANGENG)
    @DeleteMapping("/DeleteqingKuangBianGeng")//单个删除情况变更
    public Map<String, Object> DeleteqingKuangBianGeng(
            @RequestParam Integer managerId,
            @RequestParam Integer qkbgId
    ) {

        Map<String, Object> map = new HashMap<>();
        Qingkuangbiangeng qingkuangbiangeng = qingkuangbiangengMapper.queryAll(qkbgId);
        if (qingkuangbiangeng == null) {
            map.put("result", "null");
        } else {
            int result = qingkuangbiangengMapper.DeleteqingKuangBianGeng(qkbgId);
            if (result == 1) {
                map.put("result", "success");
            } else {
                map.put("result", "error");
            }
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.QINGKUANGBIANGENG)
    @DeleteMapping("/DeleteDuoTiaoqingKuangBianGeng")//批量删除情况变更
    public Map<String, Object> DeleteDuoTiaoqingKuangBianGeng(
            @RequestParam Integer managerId,
            @RequestParam Integer[] qkbgId
    ) {
        Map<String, Object> map = new HashMap<>();
        int result = 0;
        for (int a = 0; a < qkbgId.length; a++) {
            result = qingkuangbiangengMapper.DeleteqingKuangBianGeng(qkbgId[a]);
        }
        map.put("result", result);
        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.QINGKUANGBIANGENG)
    @PostMapping("/InsertQingKuangBianGeng")//添加情况变更
    public Map<String, Object> InsertQingKuangBianGeng(
            @RequestParam Integer managerId,
            @RequestParam Integer qkbgZu,
            @RequestParam Integer qkbgLeixing,
            @RequestParam String qkbgPhone,
            @RequestParam String qkbgShengfenzheng,
            @RequestParam String qkbgName,
            @RequestParam Integer qkbgMinzu,
            @RequestParam Date qkbgTime,
            @RequestParam String qkbgYuanyin,
            @RequestParam String qkbgDizhi,
            @RequestParam Integer qkbgYear,
            @RequestParam String qkbgXuhao
    ) {

        Map<String, Object> map = new HashMap<>();
        Qingkuangbiangeng j = new Qingkuangbiangeng();
        j.setQkbgZu(qkbgZu);
        j.setQkbgLeixing(qkbgLeixing);
        j.setQkbgPhone(qkbgPhone);
        j.setQkbgShengfenzheng(qkbgShengfenzheng);
        j.setQkbgName(qkbgName);
        j.setQkbgMinzu(qkbgMinzu);
        j.setQkbgTime(qkbgTime);
        j.setQkbgYuanyin(qkbgYuanyin);
        j.setQkbgDizhi(qkbgDizhi);
        j.setQkbgYear(qkbgYear);
        j.setQkbgXuhao(qkbgXuhao);

        int result = qingkuangbiangengMapper.InsertQingKuangBianGeng(j);
        if (result == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "error");
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.QINGKUANGBIANGENG)
    @PutMapping("/UpdateQingKuangBianGeng")//修改情况变更
    public Map<String, Object> UpdateQingKuangBianGeng(
            @RequestParam Integer managerId,
            @RequestParam Integer qkbgId,
            @RequestParam Integer qkbgZu,
            @RequestParam Integer qkbgLeixing,
            @RequestParam String qkbgPhone,
            @RequestParam String qkbgShengfenzheng,
            @RequestParam String qkbgName,
            @RequestParam Integer qkbgMinzu,
            @RequestParam Date qkbgTime,
            @RequestParam String qkbgYuanyin,
            @RequestParam String qkbgDizhi,
            @RequestParam Integer qkbgYear,
            @RequestParam String qkbgXuhao
    ) {

        Map<String, Object> map = new HashMap<>();
        Qingkuangbiangeng j = qingkuangbiangengMapper.queryAll(qkbgId);
        if (j == null) {
            map.put("result", "此人不存在");
        } else {
            j.setQkbgZu(qkbgZu);
            j.setQkbgLeixing(qkbgLeixing);
            j.setQkbgPhone(qkbgPhone);
            j.setQkbgShengfenzheng(qkbgShengfenzheng);
            j.setQkbgName(qkbgName);
            j.setQkbgMinzu(qkbgMinzu);
            j.setQkbgTime(qkbgTime);
            j.setQkbgYuanyin(qkbgYuanyin);
            j.setQkbgDizhi(qkbgDizhi);
            j.setQkbgYear(qkbgYear);
            j.setQkbgXuhao(qkbgXuhao);

            int result = qingkuangbiangengMapper.UpdateQingKuangBianGeng(j);
            if (result == 1) {
                map.put("result", "success");
            } else {
                map.put("result", "error");
            }
        }
        return map;
    }

    @PostMapping("QingkuangbiangengExcelUpload")//积极分子导入excel
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.QINGKUANGBIANGENG)
    public Map<String, Object> QingkuangbiangengExcelUpload(
            @RequestParam Integer managerId,
            //@RequestParam Integer jjfzEntity,
            @RequestParam("ExcelFile") MultipartFile ExcelFile
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Qingkuangbiangeng> demoList = new ArrayList<>();
            Workbook book = null;
            //判断是xls还是xlsx
            try {
                book = new XSSFWorkbook(ExcelFile.getInputStream());
            } catch (Exception ex) {
                try {
                    book = new HSSFWorkbook(new POIFSFileSystem(ExcelFile.getInputStream()));
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
                    Qingkuangbiangeng qingkuangbiangeng = new Qingkuangbiangeng();
                    Row row = sheet.getRow(j);//获得当前行数据
                    Integer k = 0;
                    if (qingkuangbiangengMapper.findQingKuangBianGengByxuhao(row.getCell(12).toString()) == null) {
                        qingkuangbiangeng.setQkbgName(row.getCell(4).toString());
                        if (row.getCell(9) != null) {
                            qingkuangbiangeng.setQkbgTime(sdf.parse(row.getCell(9).getStringCellValue()));
                        }
                        qingkuangbiangeng.setQkbgMinzu((int) row.getCell(6).getNumericCellValue());
                        qingkuangbiangeng.setQkbgShengfenzheng(row.getCell(7).getStringCellValue());
                        qingkuangbiangeng.setQkbgLeixing((int) row.getCell(3).getNumericCellValue());
                        qingkuangbiangeng.setQkbgDizhi(row.getCell(8).toString());
                        qingkuangbiangeng.setQkbgYuanyin(row.getCell(10).getStringCellValue());
                        qingkuangbiangeng.setQkbgDizhi(row.getCell(11).getStringCellValue());
                        qingkuangbiangeng.setQkbgZu((int) row.getCell(2).getNumericCellValue());
                        qingkuangbiangeng.setQkbgXuhao(row.getCell(12).toString());
                        k = qingkuangbiangengMapper.InsertQingKuangBianGeng(qingkuangbiangeng);
                    }
                    if (k != 0) {
                        if (map.get("success") != null) {
                            map.put("success", map.get("success") + "," + String.valueOf(j));
                        } else {
                            map.put("success", String.valueOf(j));
                        }
                    } else {
                        if (map.get("error") != null) {
                            map.put("error", map.get("error") + "," + String.valueOf(j));
                        } else {
                            map.put("error", String.valueOf(j));
                        }
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return map;
    }


}
