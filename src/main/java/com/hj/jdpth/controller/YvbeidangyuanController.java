package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Yvbeidangyuan;
import com.hj.jdpth.repository.YvbeidangyuanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class YvbeidangyuanController {
    @Autowired
    private YvbeidangyuanMapper yvbeidangyuanMapper;

    //
    @PostMapping("/selextyvbeidangyuan")//根据id查询预备党员（详情）
    public Map<String, Object> selextYvbeidangyuan(
            @RequestParam Integer ybdyId
    ) {
        Map<String, Object> map = new HashMap<>();
        Yvbeidangyuan yvbeidangyuan = yvbeidangyuanMapper.queryAll(ybdyId);
        if (yvbeidangyuan == null) {
            map.put("record", "此人不存在");
        }
        map.put("record", yvbeidangyuan);
        return map;
    }
}
//    @PostMapping( "/pageFindYvbeidangyuanByZId")//根据镇id查询预备党员
//    public Map<String, Object> pageFindYvbeidangyuanByZId(
//            @RequestParam Integer zId,
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//
//        PageHelper.startPage(startPage, pageSize);
//        Page< Yvbeidangyuan>  YvbeidangyuanPage = yvbeidangyuanMapper.pageFindYvbeidangyuanByZId(zId);
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", YvbeidangyuanPage);
//        //总页数
//        result.put("total", YvbeidangyuanPage.getPages());
//        //记录总数
//        result.put("count", YvbeidangyuanPage.getTotal());
//        //当前第几页
//        result.put("nowPage", YvbeidangyuanPage.getPageNum());
//        return result;
//    }
//    @PostMapping( "/pageYvbeidangyuanByVIdandZId")//根据镇id和村id查询预备党员
//    public Map<String, Object> pageYvbeidangyuanByVIdandZId(
//            @RequestParam Integer zId,
//            @RequestParam Integer vId,
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//
//        PageHelper.startPage(startPage, pageSize);
//        Page< Yvbeidangyuan>  YvbeidangyuanPage = yvbeidangyuanMapper.pageYvbeidangyuanByVIdandZId(zId,vId);
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", YvbeidangyuanPage);
//        //总页数
//        result.put("total", YvbeidangyuanPage.getPages());
//        //记录总数
//        result.put("count", YvbeidangyuanPage.getTotal());
//        //当前第几页
//        result.put("nowPage", YvbeidangyuanPage.getPageNum());
//        return result;
//    }
//    @PostMapping( "/pageYvbeidangyuanByVIdandZIdandYbdyTime")//根据镇id和村id和时间查询预备党员
//    public Map<String, Object> pageYvbeidangyuanByVIdandZIdandYbdyTime(
//            @RequestParam Integer zId,
//            @RequestParam Integer vId,
//            @RequestParam String time,
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//
//        PageHelper.startPage(startPage, pageSize);
//        Page< Yvbeidangyuan>  YvbeidangyuanPage = yvbeidangyuanMapper.pageYvbeidangyuanByVIdandZIdandYbdyTime(zId,vId,time);
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", YvbeidangyuanPage);
//        //总页数
//        result.put("total", YvbeidangyuanPage.getPages());
//        //记录总数
//        result.put("count", YvbeidangyuanPage.getTotal());
//        //当前第几页
//        result.put("nowPage", YvbeidangyuanPage.getPageNum());
//        return result;
//    }
//
//    @PostMapping( "/selectAllYvbeidangyuan")//查询所有预备党员
//    public Map<String, Object> selectAllYvbeidangyuan(
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//
//        PageHelper.startPage(startPage, pageSize);
//        Page< Yvbeidangyuan>  YvbeidangyuanPage = yvbeidangyuanMapper.selectAllYvbeidangyuan();
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", YvbeidangyuanPage);
//        //总页数
//        result.put("total", YvbeidangyuanPage.getPages());
//        //记录总数
//        result.put("count", YvbeidangyuanPage.getTotal());
//        //当前第几页
//        result.put("nowPage", YvbeidangyuanPage.getPageNum());
//        return result;
//    }
//    @OperationLogDetail(operationType = OperationType.DELETE,operationUnit = OperationUnit.YVBEIDANGYUAN)
//    @DeleteMapping("/DeleteYvbeidangyuan")//单个删除预备党员
//    public Map<String ,Object> DeleteYvbeidangyuan(
//            @RequestParam Integer managerId,
//            @RequestParam Integer ybdyId
//    ){
//        Map<String ,Object> map = new HashMap<>();
//        Yvbeidangyuan yvbeidangyuan =yvbeidangyuanMapper.queryAll(ybdyId);
//        if(yvbeidangyuan==null){
//            map.put("result","null");
//        }else{
//            int result= yvbeidangyuanMapper.DeleteYvbeidangyuan(ybdyId);
//            if(result==1){
//                map.put("result","success");
//            }
//            else {
//                map.put("result","error");
//            }
//        }
//        return map;
//    }
//    @OperationLogDetail(operationType = OperationType.DELETE,operationUnit = OperationUnit.YVBEIDANGYUAN)
//    @DeleteMapping("/DeleteDuoTiaoYvbeidangyuan")//批量删除预备党员
//    public Map<String, Object> DeleteDuoTiaoYvbeidangyuan(
//            @RequestParam Integer managerId,
//            @RequestParam Integer[] ybdyId
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        int result=0;
//        for(int a=0;a<ybdyId.length;a++){
//            result=yvbeidangyuanMapper.DeleteYvbeidangyuan(ybdyId[a]);
//        }
//        map.put("result",result);
//        return map;
//    }
//    @OperationLogDetail(operationType = OperationType.INSERT,operationUnit = OperationUnit.YVBEIDANGYUAN)
//    @PostMapping("/InsertYvbeidangyuan")//添加预备党员
//    public Map<String, Object> InsertYvbeidangyuan(
//            @RequestParam Integer managerId,
//            @RequestParam String name,
//            @RequestParam String sex,
//            @RequestParam Integer mingzu,
//            @RequestParam String shengfengzhenghao,
//            @RequestParam String danwei,
//            @RequestParam String phone,
//            @RequestParam String zhiwu,
//            @RequestParam String wenhua,
//            @RequestParam String shengfen,
//            @RequestParam Integer year,
//             @RequestParam Date biangengshijian,
//           // @RequestParam Integer xiangzheng,
//            @RequestParam Integer cun,
//            @RequestParam Integer zuhao
//    ) {
//
//        Map<String, Object> map = new HashMap<>();
//        Yvbeidangyuan j=new Yvbeidangyuan();
//        j.setYbdyName(name);
//        j.setYbdySex(sex);
//        j.setYbdyNative(mingzu);
//        j.setYbdyShengfenzheng(shengfengzhenghao);
//        j.setYbdyDanwei(danwei);
//        j.setYbdyPhone(phone);
//        j.setYbdyZhiwu(zhiwu);
//        j.setYbdyWenhua(wenhua);
//        j.setYbdyEntity(shengfen);
//        j.setYbdyYear(year);
//        j.setYbdyTime(biangengshijian);
//        //j.setYbdyId(xiangzheng);
//        j.setYbdyVillage(cun);
//        j.setYbdyZu(zuhao);
//
//        int result=yvbeidangyuanMapper.InsertYvbeidangyuan(j);
//        if (result == 1) {
//            map.put("result", "success");
//        } else {
//            map.put("result", "error");
//        }
//        return map;
//    }
//    @OperationLogDetail(operationType = OperationType.UPDATE,operationUnit = OperationUnit.YVBEIDANGYUAN)
//    @PutMapping("/UpdateYvbeidangyuan")//修改预备党员
//    public Map<String, Object> UpdateJijifenzi(
//            @RequestParam Integer managerId,
//            @RequestParam Integer jjfzId,
//            @RequestParam String name,
//            @RequestParam String sex,
//            @RequestParam Integer mingzu,
//            @RequestParam String shengfengzheng,
//            @RequestParam String danwei,
//            @RequestParam String phone,
//            @RequestParam String zhiwu,
//            @RequestParam String wenhua,
//            @RequestParam String shengfeng,
//            @RequestParam Integer year,
//             @RequestParam Date biangengshijian,
//           // @RequestParam Integer xiangzheng,
//            @RequestParam Integer cun,
//            @RequestParam Integer zuhao
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        Yvbeidangyuan j=yvbeidangyuanMapper.queryAll(jjfzId);
//        if(j==null){
//            map.put("result","此人不存在");
//        }else {
//            j.setYbdyName(name);
//            j.setYbdySex(sex);
//            j.setYbdyNative(mingzu);
//            j.setYbdyShengfenzheng(shengfengzheng);
//            j.setYbdyDanwei(danwei);
//            j.setYbdyPhone(phone);
//            j.setYbdyZhiwu(zhiwu);
//            j.setYbdyWenhua(wenhua);
//            j.setYbdyEntity(shengfeng);
//            j.setYbdyYear(year);
//            j.setYbdyTime(biangengshijian);
//           // j.setYbdyId(xiangzheng);
//            j.setYbdyVillage(cun);
//            j.setYbdyZu(zuhao);
//
//            int result = yvbeidangyuanMapper.UpdateYvbeidangyuan(j);
//            if (result == 1) {
//                map.put("result", "success");
//            } else {
//                map.put("result", "error");
//            }
//        }
//        return  map;
//    }
//    @PostMapping("YvbeidangyuanExcelUpload")//预备党员导入excel
//    @OperationLogDetail(operationType = OperationType.IMPORT,operationUnit = OperationUnit.YVBEIDANGYUAN)
//    public Map<String ,Object> zuzhifazhanExcelUpload(
//            @RequestParam Integer managerId,
//            //@RequestParam Integer jjfzEntity,
//            @RequestParam("ExcelFile") MultipartFile ExcelFile
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            List<Yvbeidangyuan> demoList = new ArrayList<>();
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
//                    Yvbeidangyuan yvbeidangyuan = new Yvbeidangyuan();
//                    Row row = sheet.getRow(j);//获得当前行数据
//                    Integer k = 0;
//                    if (yvbeidangyuanMapper.findYvbeidangyuanByxuhao(row.getCell(13).toString()) == null) {
//                        yvbeidangyuan.setYbdyName(row.getCell(3).toString());
//                        if (row.getCell(10) != null) {
//                            yvbeidangyuan.setYbdyTime(sdf.parse(row.getCell(10).getStringCellValue()));
//                        }
//                        yvbeidangyuan.setYbdySex(row.getCell(4).getStringCellValue());
//                        yvbeidangyuan.setYbdyNative((int) row.getCell(5).getNumericCellValue());
//                        yvbeidangyuan.setYbdyShengfenzheng(row.getCell(6).getStringCellValue());
//                        yvbeidangyuan.setYbdyDanwei(row.getCell(11).getStringCellValue());
//                        yvbeidangyuan.setYbdyPhone(row.getCell(7).toString());
//                        yvbeidangyuan.setYbdyZhiwu(row.getCell(12).toString());
//                        yvbeidangyuan.setYbdyWenhua(row.getCell(9).toString());
//                        yvbeidangyuan.setYbdyVillage((int) row.getCell(1).getNumericCellValue());
//                        yvbeidangyuan.setYbdyZu((int) row.getCell(2).getNumericCellValue());
//                        yvbeidangyuan.setYbdyXuhao(row.getCell(13).toString());
//                        k = yvbeidangyuanMapper.InsertYvbeidangyuan(yvbeidangyuan);
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
