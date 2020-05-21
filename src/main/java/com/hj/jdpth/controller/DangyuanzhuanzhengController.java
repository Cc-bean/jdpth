//package com.hj.jdpth.controller;
//
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.hj.jdpth.annotation.OperationLogDetail;
//import com.hj.jdpth.aop.OperationType;
//import com.hj.jdpth.aop.OperationUnit;
//import com.hj.jdpth.domain.Dangyuanzhuanzheng;
//import com.hj.jdpth.domain.Jijifenzi;
//import com.hj.jdpth.repository.DangyuanzhuanzhengMapper;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@CrossOrigin
//@RestController
//public class DangyuanzhuanzhengController {
//    @Autowired
//    private DangyuanzhuanzhengMapper dangyuanzhuanzhengMapper;
//
//    @PostMapping( "/selectDangyuanzhuanzheng")//根据id查询党员转正（详情）
//    public Map<String, Object> selectDangyuanzhuanzheng(
//            @RequestParam Integer dyzzId
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        Dangyuanzhuanzheng dangyuanzhuanzheng = dangyuanzhuanzhengMapper.queryAll(dyzzId);
//        if(dangyuanzhuanzheng==null){
//            map.put("record","此人不存在");
//        }
//        map.put("record", dangyuanzhuanzheng);
//        return map;
//    }
//    @PostMapping( "/pageFindDangyuanzhuanzhengByZId")//根据镇id查询党员转正
//    public Map<String, Object> pageFindDangyuanzhuanzhengByZId(
//            @RequestParam Integer zId,
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//
//        PageHelper.startPage(startPage, pageSize);
//        Page< Dangyuanzhuanzheng>  DangyuanzhuanzhengPage = dangyuanzhuanzhengMapper.pageFindDangyuanzhuanzhengByZId(zId);
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", DangyuanzhuanzhengPage);
//        //总页数
//        result.put("total", DangyuanzhuanzhengPage.getPages());
//        //记录总数
//        result.put("count", DangyuanzhuanzhengPage.getTotal());
//        //当前第几页
//        result.put("nowPage", DangyuanzhuanzhengPage.getPageNum());
//        return result;
//    }
//
//    @PostMapping( "/pageFindDangyuanzhuanzhengByVIdandZId")//根据镇id和村id查询党员转正
//    public Map<String, Object> pageFindDangyuanzhuanzhengByVIdandZId(
//            @RequestParam Integer zId,
//            @RequestParam Integer vId,
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//
//        PageHelper.startPage(startPage, pageSize);
//        Page< Dangyuanzhuanzheng>  DangyuanzhuanzhengPage = dangyuanzhuanzhengMapper.pageFindDangyuanzhuanzhengByVIdandZId(zId,vId);
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", DangyuanzhuanzhengPage);
//        //总页数
//        result.put("total", DangyuanzhuanzhengPage.getPages());
//        //记录总数
//        result.put("count", DangyuanzhuanzhengPage.getTotal());
//        //当前第几页
//        result.put("nowPage", DangyuanzhuanzhengPage.getPageNum());
//        return result;
//    }
//    @PostMapping( "/pageFindDangyuanzhuanzhengByVIdandZIdandDyzzTime")//根据镇id和村id和时间查询党员转正
//    public Map<String, Object> pageFindDangyuanzhuanzhengByVIdandZIdandDyzzTime(
//            @RequestParam Integer zId,
//            @RequestParam Integer vId,
//            @RequestParam String time,
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//        PageHelper.startPage(startPage, pageSize);
//        Page< Dangyuanzhuanzheng>  DangyuanzhuanzhengPage = dangyuanzhuanzhengMapper.pageFindDangyuanzhuanzhengByVIdandZIdandDyzzTime(zId,vId,time);
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", DangyuanzhuanzhengPage);
//        //总页数
//        result.put("total", DangyuanzhuanzhengPage.getPages());
//        //记录总数
//        result.put("count", DangyuanzhuanzhengPage.getTotal());
//        //当前第几页
//        result.put("nowPage", DangyuanzhuanzhengPage.getPageNum());
//        return result;
//    }
//    @PostMapping( "/selectAlldangyuanzhuanzheng")//查询所有党员转正
//    public Map<String, Object> selectAlldangyuanzhuanzheng(
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//        PageHelper.startPage(startPage, pageSize);
//        Page<Dangyuanzhuanzheng>DangyuanzhuanzhengPage = dangyuanzhuanzhengMapper.selectAlldangyuanzhuanzheng();
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", DangyuanzhuanzhengPage);
//        //总页数
//        result.put("total", DangyuanzhuanzhengPage.getPages());
//        //记录总数
//        result.put("count", DangyuanzhuanzhengPage.getTotal());
//        //当前第几页
//        result.put("nowPage", DangyuanzhuanzhengPage.getPageNum());
//        return result;
//    }
//    @OperationLogDetail(operationType = OperationType.DELETE,operationUnit = OperationUnit.JIJIFENZI)
//    @DeleteMapping("/DeleteDangyuanzhuanzheng")//单个删除党员转正
//    public Map<String, Object> DeleteDangyuanzhuanzheng(
//            @RequestParam Integer managerId,
//            @RequestParam Integer dyzzId
//    ) {
//
//        Map<String, Object> map = new HashMap<>();
//        Dangyuanzhuanzheng dangyuanzhuanzheng=dangyuanzhuanzhengMapper.queryAll(dyzzId);
//        if(dangyuanzhuanzheng==null){
//            map.put("result","null");
//        }else{
//            int result= dangyuanzhuanzhengMapper.DeleteDangyuanzhuanzheng(dyzzId);
//            if(result==1){
//                map.put("result","success");
//            }else{
//                map.put("result","error");
//            }
//        }
//        return map;
//    }
//    @OperationLogDetail(operationType = OperationType.DELETE,operationUnit = OperationUnit.JIJIFENZI)
//    @DeleteMapping("/DeleteDuoTiaoDangyuanzhuanzheng")//批量删除党员转正
//    public Map<String, Object> DeleteDuoTiaoDangyuanzhuanzheng(
//            @RequestParam Integer managerId,
//            @RequestParam Integer[] dyzzId
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        int result=0;
//        for(int a=0;a<dyzzId.length;a++){
//            result=dangyuanzhuanzhengMapper.DeleteDangyuanzhuanzheng(dyzzId[a]);
//        }
//        map.put("result",result);
//        return map;
//    }
//    @OperationLogDetail(operationType = OperationType.INSERT,operationUnit = OperationUnit.DANGYUANZHUANZHENG)
//    @PostMapping("/Insertdangyuanzhuanzheng")//添加党员转正
//    public Map<String, Object> Insertdangyuanzhuanzheng(
//            @RequestParam Integer managerId,
//            @RequestParam String name,
//            @RequestParam String sex,
//            @RequestParam Integer mingzu,
//           @RequestParam String shengfengzhenghao,
//            @RequestParam String danwei,
//            @RequestParam String phone,
//            @RequestParam String zhiwu,
//            @RequestParam String wenhua,
//            @RequestParam String shengfen,
//            @RequestParam Integer year,
//             @RequestParam Date biangengshijian,
//            //@RequestParam Integer xiangzheng,
//            @RequestParam Integer cun,
//            @RequestParam Integer zuhao
//    ) {
//
//        Map<String, Object> map = new HashMap<>();
//        Dangyuanzhuanzheng j=new Dangyuanzhuanzheng();
//        j.setDyzzName(name);
//        j.setDyzzSex(sex);
//        j.setDyzzNative(mingzu);
//        j.setDyzzShengfengzheng(shengfengzhenghao);
//        j.setDyzzDanwei(danwei);
//        j.setDyzzPhone(phone);
//        j.setDyzzZhiwu(zhiwu);
//        j.setDyzzWenhua(wenhua);
//        j.setDyzzEntity(shengfen);
//        j.setDyzzYear(year);
//        j.setDyzzTime(biangengshijian);
//       // j.setDyzzId(xiangzheng);
//        j.setDyzzVillage(cun);
//        j.setDyzzZu(zuhao);
//
//        int result=dangyuanzhuanzhengMapper.Insertdangyuanzhuanzheng(j);
//        if (result == 1) {
//            map.put("result", "success");
//        } else {
//            map.put("result", "error");
//        }
//        return map;
//    }
//    @OperationLogDetail(operationType = OperationType.UPDATE,operationUnit = OperationUnit.DANGYUANZHUANZHENG)
//    @PutMapping("/UpdateDangyuanzhuanzheng")//修改党员转正
//    public Map<String, Object> UpdateJijifenzi(
//            @RequestParam Integer managerId,
//            @RequestParam Integer dyzzId,
//            @RequestParam String name,
//            @RequestParam String sex,
//            @RequestParam Integer mingzu,
//            @RequestParam String shengfengzhenghao,
//            @RequestParam String danwei,
//            @RequestParam String phone,
//            @RequestParam String zhiwu,
//            @RequestParam String wenhua,
//            @RequestParam String shengfeng,
//            @RequestParam Integer year,
//             @RequestParam Date biangengshijian,
//            //@RequestParam Integer xiangzheng,
//            @RequestParam Integer cun,
//            @RequestParam Integer zuhao
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        Dangyuanzhuanzheng j=dangyuanzhuanzhengMapper.queryAll(dyzzId);
//        if(j==null){
//            map.put("result","此人不存在");
//        }else {
//            j.setDyzzName(name);
//            j.setDyzzSex(sex);
//            j.setDyzzNative(mingzu);
//            j.setDyzzShengfengzheng(shengfengzhenghao);
//            j.setDyzzDanwei(danwei);
//            j.setDyzzPhone(phone);
//            j.setDyzzZhiwu(zhiwu);
//            j.setDyzzWenhua(wenhua);
//            j.setDyzzEntity(shengfeng);
//            j.setDyzzYear(year);
//            j.setDyzzTime(biangengshijian);
//            //j.setDyzzId(xiangzheng);
//            j.setDyzzVillage(cun);
//            j.setDyzzZu(zuhao);
//            int result = dangyuanzhuanzhengMapper.UpdateDangyuanzhuanzheng(j);
//            if (result == 1) {
//                map.put("result", "success");
//            } else {
//                map.put("result", "error");
//            }
//        }
//        return  map;
//    }
//    @PostMapping("DangyuanzhuanzhengExcelUpload")//党员转正导入excel
//    @OperationLogDetail(operationType = OperationType.IMPORT,operationUnit = OperationUnit.DANGYUANZHUANZHENG)
//    public Map<String ,Object> zuzhifazhanExcelUpload(
//            @RequestParam Integer managerId,
//            //@RequestParam Integer jjfzEntity,
//            @RequestParam("ExcelFile") MultipartFile ExcelFile
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            List<Dangyuanzhuanzheng> demoList = new ArrayList<>();
//            Workbook book = null;
//            //判断是xls还是xlsx
//            try {
//                book = new XSSFWorkbook(ExcelFile.getInputStream());
//            } catch (Exception ex) {
//                try {
//                    book = new HSSFWorkbook(new POIFSFileSystem(ExcelFile.getInputStream()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            //获取一共有多少sheet,遍历
//            int numberOfSheets = book.getNumberOfSheets();
//            for (int i = 0; i < numberOfSheets; i++) {
//                Sheet sheet = book.getSheetAt(i);
//                //获取sheet中有多少行，遍历每一行
//                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                for (int j = 1; j < physicalNumberOfRows; j++) {
//                    if (j == 1) {
//                        continue;//标题行
//                    }
//                    Dangyuanzhuanzheng dangyuanzhuanzheng = new Dangyuanzhuanzheng();
//                    Row row = sheet.getRow(j);//获得当前行数据
//                    Integer k = 0;
//                    if (dangyuanzhuanzhengMapper.findDangyuanzhuanzhengByxuhao(row.getCell(13).toString()) == null) {
//                        dangyuanzhuanzheng.setDyzzName(row.getCell(3).toString());
//                        if (row.getCell(10) != null) {
//                            dangyuanzhuanzheng.setDyzzTime(sdf.parse(row.getCell(10).getStringCellValue()));
//                        }
//                        dangyuanzhuanzheng.setDyzzSex(row.getCell(4).getStringCellValue());
//                        dangyuanzhuanzheng.setDyzzNative((int) row.getCell(5).getNumericCellValue());
//                        dangyuanzhuanzheng.setDyzzShengfengzheng(row.getCell(6).getStringCellValue());
//                        dangyuanzhuanzheng.setDyzzDanwei(row.getCell(11).getStringCellValue());
//                        dangyuanzhuanzheng.setDyzzPhone(row.getCell(7).toString());
//                        dangyuanzhuanzheng.setDyzzZhiwu(row.getCell(12).toString());
//                        dangyuanzhuanzheng.setDyzzWenhua(row.getCell(9).toString());
//                        dangyuanzhuanzheng.setDyzzVillage((int) row.getCell(1).getNumericCellValue());
//                        dangyuanzhuanzheng.setDyzzZu((int) row.getCell(2).getNumericCellValue());
//                        dangyuanzhuanzheng.setDyzzXuhao(row.getCell(13).toString());
//                        k = dangyuanzhuanzhengMapper.Insertdangyuanzhuanzheng(dangyuanzhuanzheng);
//                    }
//                    if (k != 0) {
//                        if (map.get("success") != null) {
//                            map.put("success", map.get("success") + "," + String.valueOf(j));
//                        } else {
//                            map.put("success", String.valueOf(j));
//                        }
//                    } else {
//                        if (map.get("error") != null) {
//                            map.put("error", map.get("error") + "," + String.valueOf(j));
//                        } else {
//                            map.put("error", String.valueOf(j));
//                        }
//                    }
//                }
//            }
//        }catch (Exception e){
//
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//}
