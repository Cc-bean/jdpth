package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Hu;
import com.hj.jdpth.domain.Hukouqianyi;
import com.hj.jdpth.domain.Zu;
import com.hj.jdpth.repository.HukouqianyiMapper;
import com.hj.jdpth.repository.NationMapper;
import com.hj.jdpth.service.HuService;
import com.hj.jdpth.service.HukouqianyiService;
import com.hj.jdpth.service.ZuService;
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
public class lfmExcelController {

    @Autowired
    HukouqianyiService hukouqianyiService;
    @Autowired
    HukouqianyiMapper hukouqianyiMapper;
    @Autowired
    ZuService zuService;
    @Autowired
    HuService huService;
    @Autowired
    NationMapper nationMapper;

    @PostMapping(value = "/ZuExcel")
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.ZU)
    public Map<String, Object> ZuExcel(@RequestParam Integer managerId,
                                       @RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Zu> demoList = new ArrayList<>();
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
                for (int j = 1; j < physicalNumberOfRows; j++) {
                    if (j == 1) {
                        continue;//标题行
                    }
                    Zu zu = new Zu();
                    Row row = sheet.getRow(j);//获得当前行数据
                    boolean k = false;
                    if (zuService.Check(row.getCell(2).toString())) {
                        zu.setzVillage((int) row.getCell(0).getNumericCellValue());
                        zu.setzName(row.getCell(1).toString());
                        zu.setzBeiyong1(row.getCell(2).toString());
                        k = (boolean) zuService.Add(zu).get("data");
                    }
                    if (k != false) {
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

    @PostMapping(value = "/HuExcel")
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.HU)
    public Map<String, Object> HuExcel(@RequestParam Integer managerId,
                                       @RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Hu> demoList = new ArrayList<>();
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
                for (int j = 1; j < physicalNumberOfRows; j++) {
                    if (j == 1) {
                        continue;//标题行
                    }
                    Hu hu = new Hu();
                    Row row = sheet.getRow(j);//获得当前行数据
                    boolean k = false;
                    if (huService.Check(row.getCell(3).toString())) {
                        hu.sethZu((int) row.getCell(1).getNumericCellValue());
                        hu.sethName(row.getCell(2).toString());
                        hu.sethBeiyong1(row.getCell(3).toString());
                        k = (boolean) huService.Add(hu).get("data");
                    }
                    if (k != false) {
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
