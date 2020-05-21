
package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Dangyuanzuzhihuodong;
import com.hj.jdpth.service.ZuzhihuodongListService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
public class ZuzhihuodongExcelController {

    @Autowired
    ZuzhihuodongListService zuzhihuodongListService;


    @PostMapping("/h/ZuzhihuodongExcelUpload")//党员组织活动导入excel
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.DANGYUANZUZHIHUODONG)
    public Map<String, Object> zuzhihuodongExcelUpload(
            @RequestParam Integer managerId,
            @RequestParam("ExcelFile") MultipartFile ExcelFile
    ) {
        Map<String, Object> map = new HashMap<>();
        if (ExcelFile != null) {
            System.out.println("EXCEL文件不为空");
        } else {
            System.out.println("文件为空");
        }

        try {
            List<Dangyuanzuzhihuodong> demoList = new ArrayList<>();
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
                    Dangyuanzuzhihuodong dangyuanzuzhihuodong = new Dangyuanzuzhihuodong();
                    Row row = sheet.getRow(j);//获得当前行数据
                    Integer k = 0;
                    if (zuzhihuodongListService.GetInfoByXvhao(row.getCell(6).toString())) {
                        dangyuanzuzhihuodong.setDyzzhdName(row.getCell(2).toString());//活动姓名
                        if (row.getCell(5) != null) {
                            dangyuanzuzhihuodong.setDyzzhdTime((Date) sdf.parse(row.getCell(5).getStringCellValue()));
                        }
                        dangyuanzuzhihuodong.setDyzzhdVillageid((int) row.getCell(0).getNumericCellValue());
                        dangyuanzuzhihuodong.setDyzzhdPlace(row.getCell(4).toString());
                        dangyuanzuzhihuodong.setDyzzhdContent(row.getCell(3).toString());
                        int i1 = zuzhihuodongListService.GetTypeId(row.getCell(1).getStringCellValue(), (int) row.getCell(0).getNumericCellValue());
                        dangyuanzuzhihuodong.setDyzzhdLeixing(i1);
                        k = (int) zuzhihuodongListService.GetInfoAddInfo(dangyuanzuzhihuodong).get("data");
                    }
                    if (k != 0) {
                        if (map.get("success") != null) {
                            map.put("success", map.get("success") + "," + String.valueOf(j + 1));
                        } else {
                            map.put("success", String.valueOf(j + 1));
                        }
                    } else {
                        if (map.get("error") != null) {
                            map.put("error", map.get("error") + "," + String.valueOf(j + 1));
                        } else {
                            map.put("error", String.valueOf(j + 1));
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


