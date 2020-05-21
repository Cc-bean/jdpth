package com.hj.jdpth.controller;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.aspose.words.SaveOutputParameters;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Zuzifazhan;
import com.hj.jdpth.repository.JijifenziMapper;
import com.hj.jdpth.repository.ZuzifazhanMapper;
import com.hj.jdpth.service.ZuzifazhanService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ICell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
public class ZuzifazhanController {

    @Autowired
    private ZuzifazhanService zuzifazhanService;
    @Autowired
    private ZuzifazhanMapper zuzifazhanMapper;


    @PostMapping("/Zuzifazhan")//初始进入组织发展界面
    public Map<String, Object> zuzifazhanFirst(
            @RequestParam(required = false) Integer quId,//区
            @RequestParam(required = false) Integer zId,//镇
            @RequestParam(required = false) Integer vId,//村
            @RequestParam(required = false) String name,//时间
            @RequestParam(required = false) String entity,//身份
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize,
            @RequestParam Integer adminId//管理员类型
    ) {
        Map<String, Object> map = new HashMap<>();
        if (adminId != null && startPage != null && pageSize != null && (quId != null || zId != null || vId != null || name != null || entity != null)) {
            if (quId == null) {
                quId = 0;
            }
            if (zId == null) {
                zId = 0;
            }
            if (vId == null) {
                vId = 0;
            }
            if (StringUtils.isEmpty(entity)) {
                entity = null;
            }
            map = zuzifazhanService.GetInfo(quId, zId, vId, name, entity, startPage, pageSize, adminId);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }


    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.ZUZHIFAZHAN)
    @PostMapping("/Insert")
    public Map<String, Object> InsertZuzifazhan(
            @RequestParam Integer managerId,
            @RequestParam String name,
            @RequestParam String sex,
            @RequestParam Integer mingzu,
            @RequestParam(required = false) String shengfengzhenghao,
            @RequestParam(required = false) String danwei,
            @RequestParam String phone,
            @RequestParam(required = false) String[] zhiwu,
            @RequestParam(required = false) String wenhua,
            @RequestParam String entity,
            @RequestParam Integer villageId,
            @RequestParam(required = false) Integer zuId,
            @RequestParam(required = false) String jjfzTime,
            @RequestParam(required = false) String fzdxTime,
            @RequestParam(required = false) String ybdyTime,
            @RequestParam(required = false) String zsdyTime,
            @RequestParam(required = false) String zzfzBeiYong1,
            @RequestParam(required = false) String zzfzBeiYong2,
            @RequestParam(required = false) String zzfzBeiYong3,
            @RequestParam String address
    ) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date date1 = null;
//            Date date2 = null;
//            Date date3 = null;
//            Date date4 = null;
//            try {
//                if (jjfzTime == null) {
//                } else {
//                    date1 = sdf.parse(jjfzTime);
//                }
//            } catch (Exception e) {
//                date1 = null;
//            }
//            try {
//                if (fzdxTime == null) {
//                } else {
//                    date2 = sdf.parse(fzdxTime);
//                }
//            } catch (Exception e) {
//                date2 = null;
//            }
//            try {
//                if (ybdyTime == null) {
//                } else {
//                    date3 = sdf.parse(ybdyTime);
//                }
//            } catch (Exception e) {
//                date3 = null;
//            }
//            try {
//                if (zsdyTime == null) {
//                } else {
//                    date4 = sdf.parse(zsdyTime);
//                }
//            } catch (Exception e) {
//                date4 = null;
//            }
        Map<String, Object> map = new HashMap<>();
        Zuzifazhan j = new Zuzifazhan();
        j.setZzfzName(name);
        j.setZzfzSex(sex);
        j.setZzfzNative(mingzu);
        j.setZzfzShenfenzheng(shengfengzhenghao);
        j.setZzfzDanWei(danwei);
        j.setZzfzPhone(phone);
        if (zhiwu != null) {
            String a = new String();
            for (int i = 0; i < zhiwu.length; i++) {
                if (i == zhiwu.length - 1) {
                    a = a + zhiwu[i];
                } else {
                    a = a + zhiwu[i] + ",";
                }
            }
            j.setZzfzZhiWu(a);
        } else {
            j.setZzfzZhiWu(null);
        }
        j.setZzfzWenHua(wenhua);
        j.setZzfzEntity(entity);
        j.setZzfzVillage(villageId);
        j.setZzfzZu(zuId);
        j.setJjfzTime(jjfzTime);
        j.setFzdxTime(fzdxTime);
        j.setYbdyTime(ybdyTime);
        j.setZsdyTime(zsdyTime);
        j.setBeiYong1(zzfzBeiYong1);
        j.setBeiYong2(zzfzBeiYong2);
        j.setBeiYong3(zzfzBeiYong3);
        j.setZzfzAddress(address);
        Integer count = zuzifazhanMapper.InsertZuzifazhan(j);
        if (count == 1) {
            map.put("status", "success");
        } else {
            map.put("status", "error");
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.ZUZHIFAZHAN)
    @PutMapping("/Update")
    public Map<String, Object> UpdateZuzifazhan(
            @RequestParam Integer managerId,
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String sex,
            @RequestParam Integer mingzu,
            @RequestParam(required = false) String shengfengzhenghao,
            @RequestParam(required = false) String danwei,
            @RequestParam String phone,
            @RequestParam(required = false) String[] zhiwu,
            @RequestParam(required = false) String wenhua,
            @RequestParam String entity,
            @RequestParam Integer villageId,
            @RequestParam(required = false) Integer zuId,
            @RequestParam(required = false) String jjfzTime,
            @RequestParam(required = false) String fzdxTime,
            @RequestParam(required = false) String ybdyTime,
            @RequestParam(required = false) String zsdyTime,
            @RequestParam(required = false) String zzfzBeiYong1,
            @RequestParam(required = false) String zzfzBeiYong2,
            @RequestParam(required = false) String zzfzBeiYong3,
            @RequestParam String address

    ) {
//        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//        Date date1=null;
//        Date date2=null;
//        Date date3=null;
//        Date date4=null;
//        try {
//            if(jjfzTime==null){
//            }else{
//                date1 =sdf.parse(jjfzTime);
//            }
//        } catch (Exception e) {
//            date1 = null;
//        }
//        try{
//            if(fzdxTime==null) {
//            }else{
//                date2 =sdf.parse(fzdxTime);
//            }
//        }catch (Exception e){
//            date2=null;
//        }
//        try{
//            if(ybdyTime==null) {
//            }else{
//                date3 =sdf.parse(ybdyTime);
//            }
//        }catch (Exception e){
//            date3=null;
//        }
//        try{
//            if(zsdyTime==null) {
//            }else{
//                date4 =sdf.parse(zsdyTime);
//            }
//        }catch (Exception e){
//            date4=null;
//        }
        Zuzifazhan j = zuzifazhanMapper.GetById(id);
        Map<String, Object> map = new HashMap<>();
        if (j == null) {
            map.put("data", "此人不存在");
        } else {
            j.setZzfzName(name);
            j.setZzfzSex(sex);
            j.setZzfzNative(mingzu);
            j.setZzfzShenfenzheng(shengfengzhenghao);
            j.setZzfzDanWei(danwei);
            j.setZzfzPhone(phone);
            String a = new String();
            if (zhiwu != null) {
                for (int i = 0; i < zhiwu.length; i++) {
                    if (i == zhiwu.length - 1) {
                        a = a + zhiwu[i];
                    } else {
                        a = a + zhiwu[i] + ",";
                    }
                }
                j.setZzfzZhiWu(a);
            } else {
                j.setZzfzZhiWu(null);
            }
            j.setZzfzWenHua(wenhua);
            j.setZzfzEntity(entity);
            j.setZzfzVillage(villageId);
            j.setZzfzZu(zuId);
            j.setJjfzTime(jjfzTime);
            j.setFzdxTime(fzdxTime);
            j.setYbdyTime(ybdyTime);
            j.setZsdyTime(zsdyTime);
            j.setBeiYong1(zzfzBeiYong1);
            j.setBeiYong2(zzfzBeiYong2);
            j.setBeiYong3(zzfzBeiYong3);
            j.setZzfzAddress(address);
            Integer count = zuzifazhanMapper.UpdateZuzifazhan(j);
            if (count == 1) {
                map.put("status", "success");
            } else {
                map.put("status", "error");
            }
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.ZUZHIFAZHAN)
    @DeleteMapping("/Delete")//单个删除
    public Map<String, Object> DeleteZuzifazhan(
            @RequestParam Integer managerId,
            @RequestParam Integer id
    ) {
        Map<String, Object> map = new HashMap<>();
        Integer count = zuzifazhanMapper.Delete(id);
        if (count == 1) {
            map.put("status", "success");
        } else {
            map.put("status", "error");
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.ZUZHIFAZHAN)
    @DeleteMapping("/DeleteDuoTiao")//批量删除
    public Map<String, Object> DeleteDuoTiaoZuzifazhan(
            @RequestParam Integer managerId,
            @RequestParam Integer[] id
    ) {
        Map<String, Object> map = new HashMap<>();
        int result = 0;
        for (int a = 0; a < id.length; a++) {
            result = zuzifazhanMapper.Delete(id[a]);
            if (result == 1) {
                map.put("status", "success");
            } else {
                map.put("status", "error");
            }
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.SELECT, operationUnit = OperationUnit.ZUZHIFAZHAN)
    @PostMapping("/Select")
    public Map<String, Object> SelectZuzifazhan(
            @RequestParam Integer managerId,
            @RequestParam Integer id
    ) {
        Map<String, Object> map = new HashMap<>();
        Zuzifazhan zuzifazhan = zuzifazhanMapper.GetById(id);
        String villageName = zuzifazhanMapper.GetVillageById(zuzifazhan.getZzfzVillage());
        zuzifazhan.setVillageName(villageName);
        String zuName = zuzifazhanMapper.GetZuById(zuzifazhan.getZzfzZu());
        zuzifazhan.setZuName(zuName);
        if (zuzifazhan == null) {
            map.put("data", "此人不存在");
        } else {
            map.put("data", zuzifazhan);
        }
        return map;
    }

    @Transactional
    @PostMapping("/ImportExcel")//导入excel
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.ZUZHIFAZHAN)
    public Map<String, Object> ZuzifazhanExcelUpload(
            @RequestParam Integer managerId,
            @RequestParam("ExcelFile") MultipartFile ExcelFile
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
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
                for (int j = 2; j < physicalNumberOfRows; j++) {
                    Row row = sheet.getRow(j);//获得当前行数据
                    if (row == null) {
                        continue;
                    }
                    Zuzifazhan zuzifazhan = new Zuzifazhan();
                    if (zuzifazhanMapper.GetByXuHao(row.getCell(16).toString()) == null) {
                        zuzifazhan.setZzfzVillage((int) row.getCell(0).getNumericCellValue());
                        if ((int) row.getCell(1).getNumericCellValue() != 0) {
                            zuzifazhan.setZzfzZu((int) (row.getCell(1).getNumericCellValue()));
                        } else {
                            zuzifazhan.setZzfzZu(null);
                        }
                        zuzifazhan.setZzfzName(row.getCell(2).getStringCellValue());
                        zuzifazhan.setZzfzSex(row.getCell(3).toString());
                        zuzifazhan.setZzfzNative(zuzifazhanMapper.nation(row.getCell(4).toString()));
                        zuzifazhan.setZzfzEntity(row.getCell(5).toString());
                        if (row.getCell(6).toString() != null) {
                            zuzifazhan.setZzfzShenfenzheng(row.getCell(6).toString());
                        } else {
                            zuzifazhan.setZzfzShenfenzheng(null);
                        }
                        zuzifazhan.setZzfzPhone((String) row.getCell(7).toString());
                        zuzifazhan.setZzfzAddress(row.getCell(8).toString());
                        if (row.getCell(9).toString() != null) {
                            zuzifazhan.setZzfzWenHua(row.getCell(9).toString());
                        } else {
                            zuzifazhan.setZzfzWenHua(null);
                        }
                        if (row.getCell(10).getDateCellValue() != null) {
                            String jjfzTime = sdf.format(row.getCell(10).getDateCellValue());//将Date类型转换成String类型
                            zuzifazhan.setJjfzTime(jjfzTime);
                        } else {
                            zuzifazhan.setJjfzTime(null);
                        }
                        if (row.getCell(11).getDateCellValue() != null) {
                            String fzdxTime = sdf.format(row.getCell(11).getDateCellValue());//将Date类型转换成String类型
                            zuzifazhan.setFzdxTime(fzdxTime);
                        } else {
                            zuzifazhan.setFzdxTime(null);
                        }
                        if (row.getCell(12).getDateCellValue() != null) {
                            String ybdyTime = sdf.format(row.getCell(12).getDateCellValue());//将Date类型转换成String类型
                            zuzifazhan.setYbdyTime(ybdyTime);
                        } else {
                            zuzifazhan.setYbdyTime(null);
                        }
                        if (row.getCell(13).getDateCellValue() != null) {
                            String zsdyTime = sdf.format(row.getCell(13).getDateCellValue());//将Date类型转换成String类型
                            zuzifazhan.setZsdyTime(zsdyTime);
                        } else {
                            zuzifazhan.setZsdyTime(null);
                        }
                        if (row.getCell(14).toString() != null) {
                            zuzifazhan.setZzfzDanWei(row.getCell(14).toString());
                        } else {
                            zuzifazhan.setZzfzDanWei(null);
                        }
                        if (row.getCell(15).toString() != null) {
                            zuzifazhan.setZzfzZhiWu(row.getCell(15).toString());
                        } else {
                            zuzifazhan.setZzfzZhiWu(null);
                        }
                        zuzifazhan.setZzfzXuHao(row.getCell(16).toString());
                        zuzifazhanMapper.InsertZuzifazhan(zuzifazhan);
                    }
                }
            }
            map.put("status", "success");
        } catch (Exception e) {
            map.put("status", "error");
            throw new RuntimeException();
        }
        return map;
    }

    @PostMapping("/CountByqu")
    public Map<String, Object> countByqu(
    ) {
        Map<String, Object> map = new HashMap<>();
        Integer zId;
        List list = new ArrayList();
        try {
            for (zId = 1; zId <= 9; zId++) {
                if (zId == 5) {
                    continue;
                }
                List<String> zName = zuzifazhanMapper.GetzName(zId);
                Integer jjfz = zuzifazhanMapper.CountByEntityAndzId("积极分子", zId);
                Integer fzdx = zuzifazhanMapper.CountByEntityAndzId("发展对象", zId);
                Integer ybdy = zuzifazhanMapper.CountByEntityAndzId("预备党员", zId);
                Integer zsdy = zuzifazhanMapper.CountByEntityAndzId("正式党员", zId);

                list.add(zName);
                list.add(jjfz);
                list.add(fzdx);
                list.add(ybdy);
                list.add(zsdy);
            }
            map.put("data", list);
        } catch (Exception e) {
            map.put("data", "error");
        }
        return map;
    }

    @PostMapping("/findDangyaunByQu")
    public Map<String, Object> findDangyaunByQu(
    ) {
        Map<String, Object> map = new HashMap<>();
        Integer count;
        try {
            String time = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                Date data = new Date();
                time = sdf.format(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Integer a = Integer.parseInt(time);
            List b = new ArrayList();
            List<String> zName = null;
            for (int i = 2012; i <= a; i++) {
                String time1 = 2012 + "-" + 0 + 1 + "-" + 0 + 1;
                String time2 = (i + 1) + "-" + 0 + 1 + "-" + 0 + 1;
                for (int zId = 1; zId <= 9; zId++) {
                    if (zId == 5) {
                        continue;
                    }
                    count = zuzifazhanMapper.FindByZhen4(zId, time1, time2);
                    zName = zuzifazhanMapper.findzName(zId);
                    b.add(zName);
                    zName.add(count + "");
                }
            }
            map.put("data", b);
        } catch (Exception e) {
            map.put("data", "error");
        }
        return map;
    }

    @PostMapping("/FindByZhen")
    public Map<String, Object> FindByZhen(
            @RequestParam Integer zId
    ) {
        Map<String, Object> map = new HashMap<>();
        String time = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date data = new Date();
            time = sdf.format(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer a = Integer.parseInt(time);
        try {
            List b = new ArrayList();
            for (int i = 2012; i <= a; i++) {
                String time1 = 2012 + "-" + 0 + 1 + "-" + 0 + 1;
                String time2 = (i + 1) + "-" + 0 + 1 + "-" + 0 + 1;
                List<Integer> list = new ArrayList<>();
                Integer jjfz = zuzifazhanMapper.FindByZhen1(zId, time1, time2);
                Integer fzdx = zuzifazhanMapper.FindByZhen2(zId, time1, time2);
                Integer ybdy = zuzifazhanMapper.FindByZhen3(zId, time1, time2);
                Integer zsdy = zuzifazhanMapper.FindByZhen4(zId, time1, time2);
                list.add(jjfz);
                list.add(fzdx);
                list.add(ybdy);
                list.add(zsdy);
                b.add(list);
                map.put("data", b);
            }
        } catch (Exception e) {
            map.put("data", "error");
        }
        return map;
    }

    @PostMapping("/cun_List")//根据镇找村
    public Map<String, Object> cun_List(@RequestParam Integer zhenId
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Village> cuns = zuzifazhanMapper.cun_List(zhenId);
            map.put("data", cuns);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }


    @PostMapping("/findByCun")
    public Map<String, Object> findByCun(
            @RequestParam Integer vId,
            @RequestParam Integer entity,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize


    ) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Zuzifazhan> count = null;
        try {
            if (entity == 1) {
                count = zuzifazhanMapper.CountByCun(vId, "积极分子");
            } else if (entity == 2) {
                count = zuzifazhanMapper.CountByCun(vId, "发展对象");
            } else if (entity == 3) {
                count = zuzifazhanMapper.CountByCun(vId, "预备党员");
            } else if (entity == 4) {
                count = zuzifazhanMapper.CountByCun(vId, "正式党员");
            } else {
            }
            map.put("data", count);
            map.put("status", "success");
            //总页数
            map.put("total", count.getPages());
            //记录总数
            map.put("count", count.getTotal());
            //页面大小
            map.put("pageSize", count.getPageSize());
            //当前第几页
            map.put("nowPage", count.getPageNum());
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @PostMapping("/selectzuzifazhan")//（详情）
    public Map<String, Object> selectzuzifazhan(
            @RequestParam Integer entity,
            @RequestParam Integer Id
    ) {
        Map<String, Object> map = new HashMap<>();
        Zuzifazhan zuzifazhan = null;
        if (entity == 1) {
            zuzifazhan = zuzifazhanMapper.CountByIdAndEntity(Id, "积极分子");
        } else if (entity == 2) {
            zuzifazhan = zuzifazhanMapper.CountByIdAndEntity(Id, "发展对象");
        } else if (entity == 3) {
            zuzifazhan = zuzifazhanMapper.CountByIdAndEntity(Id, "预备党员");
        } else if (entity == 4) {
            zuzifazhan = zuzifazhanMapper.CountByIdAndEntity(Id, "正式党员");
        } else {

        }
        if (zuzifazhan == null) {
            map.put("record", "此人不存在");
        } else {
            map.put("record", zuzifazhan);
        }
        return map;
    }

    @PostMapping("/QSelect")
    public Map<String, Object> Select(
            @RequestParam(required = false) String name,//姓名
            @RequestParam(required = false) String entity,//身份
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> map = zuzifazhanService.Select(name, entity, startPage, pageSize);
        return map;
    }

    @PostMapping("/FindInfoByZhen")
    public Map<String, Object> FindInfoByZhen(
            @RequestParam Integer zId,
            @RequestParam Integer entity,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(entity)) {
            entity = null;
        }
        PageHelper.startPage(startPage, pageSize);
        Page<Zuzifazhan> zuzifazhan = null;
        if (zId == 0) {
            if (entity == 1) {
                zuzifazhan = zuzifazhanMapper.GetInfoByEntity("积极分子");
            }
            if (entity == 2) {
                zuzifazhan = zuzifazhanMapper.GetInfoByEntity("发展对象");
            }
            if (entity == 3) {
                zuzifazhan = zuzifazhanMapper.GetInfoByEntity("预备党员");
            }
            if (entity == 4) {
                zuzifazhan = zuzifazhanMapper.GetInfoByEntity("正式党员");
            }
        }
        if (zId != 0) {
            if (entity == 1) {
                zuzifazhan = zuzifazhanMapper.GetByEntityAndzId(zId, "积极分子");
            }
            if (entity == 2) {
                zuzifazhan = zuzifazhanMapper.GetByEntityAndzId(zId, "发展对象");
            }
            if (entity == 3) {
                zuzifazhan = zuzifazhanMapper.GetByEntityAndzId(zId, "预备党员");
            }
            if (entity == 4) {
                zuzifazhan = zuzifazhanMapper.GetByEntityAndzId(zId, "正式党员");
            }
        }
        if (zuzifazhan == null) {
            map.put("status", "null");
            map.put("data", "没有数据");
        } else {
            map.put("data", zuzifazhan);
            //总页数
            map.put("status", "success");
            //总页数
            map.put("total", zuzifazhan.getPages());
            //记录总数
            map.put("count", zuzifazhan.getTotal());
            //页面大小
            map.put("pageSize", zuzifazhan.getPageSize());
            //当前第几页
            map.put("nowPage", zuzifazhan.getPageNum());
        }
        return map;
    }


}
