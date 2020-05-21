package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Cunhuodongleixing;
import com.hj.jdpth.domain.Cunzuzhihuodong;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.repository.CunzuzhihuodongMapper;
import com.hj.jdpth.repository.VillageMapper;
import com.hj.jdpth.service.impl.CunzuzhihuodongServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
public class CunzuzhihuodongController {
    @Autowired
    CunzuzhihuodongServiceImpl cunzuzhihuodongService;

    @Autowired
    CunzuzhihuodongMapper cunzuzhihuodongMapper;
    @Autowired
    VillageMapper villageMapper;


    @PostMapping("/villagebulletin/queryHuodong_yp")//搜索村级活动
    public Map<String, Object> queryHuodong_yp(
            @RequestParam Integer style,
            @RequestParam(required = false) Integer qu_id,
            @RequestParam(required = false) Integer zhen_id,
            @RequestParam(required = false) Integer cun_id,
            @RequestParam(required = false) Integer chdlx_id,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> result = cunzuzhihuodongService.queryHuodong_yp(style, qu_id, zhen_id, cun_id, chdlx_id, startPage, pageSize);
        return result;
    }

    //查询活动详情
    @GetMapping("/huodongxiangqing/findXingqing_yp/{id}")
    public Map<String, Object> findXingqing_yp(@PathVariable(value = "id") Integer id) {
        Map<String, Object> result = cunzuzhihuodongService.findXingqing_yp(id);
        return result;
    }

    //删除一条记录
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.CUNZUZHIHUODONG)
    @DeleteMapping("/deletehuodong/deleteOneById_yp/{id}/{managerId}")
    public Map<String, Object> deleteOneById_yp(@PathVariable(value = "id") Integer id, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> result = cunzuzhihuodongService.deleteOneById_yp(id);
        return result;
    }

    //删除多条记录
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.CUNZUZHIHUODONG)
    @DeleteMapping("/deleteMany/deleteManyById_yp/{Ids}/{managerId}")
    public Map<String, Object> deleteManyById_yp(@PathVariable(value = "Ids") String Ids, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result = cunzuzhihuodongService.deleteManyById_yp(Ids);
        } catch (Exception e) {
            result.put("state", "error");
            result.put("data", null);
        }
        return result;
    }

//    //添加一条数据
//    @OperationLogDetail(operationType = OperationType.INSERT,operationUnit = OperationUnit.CUNZUZHIHUODONG)
//    @PostMapping("/insert/insertOne_yp")
//    public Map<String,Object> insertOne_yp(Cunzuzhihuodong cunzuzhihuodong, @RequestParam Integer managerId,@RequestParam String czzhdTime){
//        Map<String,Object> result=cunzuzhihuodongService.insertOne_yp(cunzuzhihuodong,czzhdTime);
//        return result;
//    }
//
//    //编辑 更新一条数据
//    @OperationLogDetail(operationType = OperationType.UPDATE,operationUnit = OperationUnit.CUNZUZHIHUODONG)
//    @PutMapping("/update/updateOne_yp")
//    public Map<String,Object> updateOne_yp(Cunzuzhihuodong cunzuzhihuodong,@RequestParam Integer managerId){
//        Map<String,Object> result=cunzuzhihuodongService.updateOne_yp(cunzuzhihuodong);
//        return result;
//    }

    //根据村ID搜索村活动类型
    @GetMapping("/findChdlxById_yp/{cun_id}")
    public Map<String, Object> findChdlxById_yp(@PathVariable(value = "cun_id") Integer cun_id) {
        Map<String, Object> result = cunzuzhihuodongService.findChdlxById_yp(cun_id);
        return result;
    }

    //添加一条村活动类型
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.CUNHUODONGLEIXING)
    @PostMapping("/insert/insertLeixing_yp")
    public Map<String, Object> insertLeixing_yp(Cunhuodongleixing cunhuodongleixing, @RequestParam Integer managerId) {
        Map<String, Object> result = cunzuzhihuodongService.insertLeixing_yp(cunhuodongleixing);
        return result;
    }

    //导入Excel表
    @PostMapping("/daoru/daoruHuodong_yp")
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.CUNZUZHIHUODONG)
    public Map<String, Object> daoruHuodong_yp(
            @RequestParam Integer managerId,
            @RequestParam("file") MultipartFile file
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Cunzuzhihuodong> cunzuzhihuodongs = new ArrayList<>();
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
                    Cunzuzhihuodong cunzuzhihuodong = new Cunzuzhihuodong();
                    Row row = sheet.getRow(j);//获得当前行数据
                    Integer k = 0;
                    if (cunzuzhihuodongService.findHuodongByxuhao_yp(row.getCell(6).toString())) {
                        cunzuzhihuodong.setCdyzzhdName(row.getCell(2).toString());//活动名称
                        if (row.getCell(5) != null) {
                            cunzuzhihuodong.setCdyzzhdTime((Date) sdf.parse(row.getCell(5).getStringCellValue()));
                        }
                        if (row.getCell(1).toString() != null) {
//                            cunzuzhihuodong.setCdyzzhdLeixing((int) row.getCell(1).getNumericCellValue());
                            if (row.getCell(0) != null) {
                                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                            }
                            Integer id = cunzuzhihuodongMapper.selectIDBYName(row.getCell(1).toString(), Integer.valueOf(row.getCell(0).getStringCellValue()));
                            cunzuzhihuodong.setCdyzzhdLeixing(id);
                        }
                        cunzuzhihuodong.setCdyzzhdContent(row.getCell(3).toString());
                        cunzuzhihuodong.setCdyzzhdXuhao(row.getCell(6).toString());
                        cunzuzhihuodong.setCdyzzhdPlace(row.getCell(4).toString());
                        k = (int) cunzuzhihuodongService.daoruOne_yp(cunzuzhihuodong).get("result");
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


    //领导镇级页面查询
    @PostMapping("/chdzhen")
    public Map<String, Object> chdzhen(
            @RequestParam(required = false) Integer cunId,
            @RequestParam(required = false) String time
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = cunzuzhihuodongService.chdzhen(cunId, time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    //领导全区页面查询
    @PostMapping("/chdqu")
    public Map<String, Object> chdqu(
            @RequestParam(required = false) Integer zhenId,
            @RequestParam(required = false) String time
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = cunzuzhihuodongService.chdqu(zhenId, time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }


    @PostMapping("/qzxian")
    public Map<String, Object> qzxian() {
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            map = cunzuzhihuodongService.qzxian(sdf.format(date));
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping("/zzxian")
    public Map<String, Object> zzxian(
            @RequestParam(required = false) Integer zhenId,
            @RequestParam(required = false) String time
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = cunzuzhihuodongService.zzxian(zhenId, time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping("/h/cunhuodong/")
    public Map<String, Object> queryLeiXingA(@RequestParam(value = "zhenId", required = false) Integer zhenId,
                                             @RequestParam(value = "villageId", required = false) Integer villageId,
                                             HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {

            List<Cunhuodongleixing> list = cunzuzhihuodongMapper.queryAllLeixing(zhenId, villageId);
            if (list.isEmpty()) {
                response.setStatus(204);
            } else {
                map.put("data", list);
                response.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            map.put("error", e.getMessage());
        }
        return map;
    }

    @PostMapping(value = "/Vzhexian")
    public Map<String, Object> Vzhexian(@RequestParam Integer zhenId, @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        //Map<String,Object> map3=new HashMap<>();
        List<Village> list = villageMapper.queryVillageByZhenId_yyq(zhenId);
        List a = new ArrayList();
        try {
            for (Village v :
                    list) {
                a = new ArrayList();
                for (Village villages :
                        list) {
                    map2 = new HashMap<>();
                    map2 = cunzuzhihuodongService.Vzhexian(villages.getVillageId(), time);
                    a.add(map2);
                }
            }
        } catch (Exception e) {
            map.put("statue", "error");
        }
        map.put("data", a);
        return map;
    }

    //2019-11-27

    //添加一条数据
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.CUNZUZHIHUODONG)
    @PostMapping("/insert/insertOne_yp")
    public Map<String, Object> insertOne_yp(Cunzuzhihuodong cunzuzhihuodong, @RequestParam Integer managerId, @RequestParam String czzhdTime, @RequestParam(required = false) MultipartFile[] file) {
        if (file != null) {
            cunzuzhihuodong.setCdyzzhdZhenshilujing(insertFileS(file));
        }
        Map<String, Object> result = cunzuzhihuodongService.insertOne_yp(cunzuzhihuodong, czzhdTime);
        return result;
    }

    //编辑 更新一条数据
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.CUNZUZHIHUODONG)
    @PutMapping("/update/updateOne_yp")
    public Map<String, Object> updateOne_yp(Cunzuzhihuodong cunzuzhihuodong, @RequestParam Integer managerId, @RequestParam(required = false) MultipartFile[] file) {
        Cunzuzhihuodong czzhd = cunzuzhihuodongMapper.findbyid_lfm(cunzuzhihuodong.getCdyzzhdId());
        if (file != null) {
            if (czzhd.getCdyzzhdZhenshilujing() != null) {
                cunzuzhihuodong.setCdyzzhdZhenshilujing(updateFileS(file, czzhd.getCdyzzhdZhenshilujing()));
            } else {
                cunzuzhihuodong.setCdyzzhdZhenshilujing(insertFileS(file));
            }
        }
        Map<String, Object> result = cunzuzhihuodongService.updateOne_yp(cunzuzhihuodong);
        return result;
    }

    String insertFileS(MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        String name = "";
        for (MultipartFile file : files) {
            map = cunzuzhihuodongService.uploadFile(file);
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
            map = cunzuzhihuodongService.uploadFile(file);
            name = name + map.get("path") + ",";
        }
        return name;
    }
}
