package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Fazhanduixiang;
import com.hj.jdpth.repository.FazhanduixiangMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class FazhanduixiangController {
    @Autowired
    private FazhanduixiangMapper fazhanduixiangMapper;

    @PostMapping("/selectfazhanduixiang")//根据id查询发展对象（详情）
    public Map<String, Object> selectFazhanduixiang(
            @RequestParam Integer fzdxId
    ) {
        Map<String, Object> map = new HashMap<>();
        Fazhanduixiang fazhanduixiang = fazhanduixiangMapper.queryAll(fzdxId);
        if (fazhanduixiang == null) {
            map.put("record", "此人不存在");
        }
        map.put("record", fazhanduixiang);
        return map;
    }
}
//    @PostMapping( "/pageFindFazhanduixiangByZId")//根据镇id查询发展对象
//    public Map<String, Object> pageFindFazhanduixiangByZId(
//            @RequestParam Integer zId,
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//
//        PageHelper.startPage(startPage, pageSize);
//        Page<Fazhanduixiang>  fazhanduixiangPage = fazhanduixiangMapper.pageFindFazhanduixiangByZId(zId);
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", fazhanduixiangPage);
//        //总页数
//        result.put("total", fazhanduixiangPage.getPages());
//        //记录总数
//        result.put("count", fazhanduixiangPage.getTotal());
//        //当前第几页
//        result.put("nowPage", fazhanduixiangPage.getPageNum());
//        return result;
//    }
//    @PostMapping( "/pageFindFazhanduixiangByVIdandZId")//根据镇id和村id查询发展对象
//    public Map<String, Object> pageFindFazhanduixiangByVIdandZId(
//            @RequestParam Integer zId,
//            @RequestParam Integer vId,
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//        PageHelper.startPage(startPage, pageSize);
//        Page<Fazhanduixiang>  fazhanduixiangPage = fazhanduixiangMapper.pageFindFazhanduixiangByVIdandZId(zId,vId);
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", fazhanduixiangPage);
//        //总页数
//        result.put("total", fazhanduixiangPage.getPages());
//        //记录总数
//        result.put("count", fazhanduixiangPage.getTotal());
//        //当前第几页
//        result.put("nowPage", fazhanduixiangPage.getPageNum());
//        return result;
//    }
//
//    @PostMapping( "/pageFindFazhanduixiangByVIdandZIdandFzdxTime")//根据镇id村id和时间查询发展对象
//    public Map<String, Object> pageFindFazhanduixiangByVIdandZIdandFzdxTime(
//            @RequestParam Integer zId,
//            @RequestParam Integer vId,
//            @RequestParam String time,
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//        PageHelper.startPage(startPage, pageSize);
//        Page<Fazhanduixiang>  fazhanduixiangPage = fazhanduixiangMapper.pageFindFazhanduixiangByVIdandZIdandFzdxTime(zId,vId,time);
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", fazhanduixiangPage);
//        //总页数
//        result.put("total", fazhanduixiangPage.getPages());
//        //记录总数
//        result.put("count", fazhanduixiangPage.getTotal());
//        //当前第几页
//        result.put("nowPage", fazhanduixiangPage.getPageNum());
//        return result;
//    }
//
//    @PostMapping( "/selectAllFazhanduixiang")//查询所有发展对象
//    public Map<String, Object> selectAllFazhanduixiang(
//            @RequestParam Integer startPage,
//            @RequestParam Integer pageSize
//    ) {
//        PageHelper.startPage(startPage, pageSize);
//        Page<Fazhanduixiang>  fazhanduixiangPage = fazhanduixiangMapper.selectAllFazhanduixiang();
//        Map<String, Object> result = new HashMap<>();
//        result.put("record", fazhanduixiangPage);
//        //总页数
//        result.put("total", fazhanduixiangPage.getPages());
//        //记录总数
//        result.put("count", fazhanduixiangPage.getTotal());
//        //当前第几页
//        result.put("nowPage", fazhanduixiangPage.getPageNum());
//        return result;
//    }
//
//    @OperationLogDetail(operationType = OperationType.DELETE,operationUnit = OperationUnit.FAZHANDUIXIANG)
//    @DeleteMapping("/DeleteFazhanduixiang")//单个删除发展对象
//    public Map<String, Object> DeleteFazhanduixiang(
//            @RequestParam Integer managerId,
//            @RequestParam Integer fzdxId
//    ) {
//
//        Map<String, Object> map = new HashMap<>();
//        Fazhanduixiang fazhanduixiang=fazhanduixiangMapper.queryAll(fzdxId);
//        if(fazhanduixiang==null){
//            map.put("result","null");
//        }else{
//            int result= fazhanduixiangMapper.DeleteFazhanduixiang(fzdxId);
//            if(result==1){
//                map.put("result","success");
//            }else{
//                map.put("result","error");
//            }
//        }
//        return map;
//    }
//    @OperationLogDetail(operationType = OperationType.DELETE,operationUnit = OperationUnit.FAZHANDUIXIANG)
//    @DeleteMapping("/DeleteDuoTiaoFazhanduixiang")//批量删除发展对象
//    public Map<String, Object> DeleteDuoTiaoFazhanduixiang(
//            @RequestParam Integer managerId,
//            @RequestParam Integer[] fzdxId
//    ) {
//
//        Map<String, Object> map = new HashMap<>();
//        int result=0;
//        for(int a=0;a<fzdxId.length;a++){
//            result=fazhanduixiangMapper.DeleteFazhanduixiang(fzdxId[a]);
//        }
//        map.put("result",result);
//        return map;
//    }
//    @OperationLogDetail(operationType = OperationType.UPDATE,operationUnit = OperationUnit.FAZHANDUIXIANG)
//    @PutMapping("/UpdateFazhanduixiang")//修改发展对象
//    public Map<String, Object> UpdateFazhanduixiang(
//            @RequestParam Integer managerId,
//            @RequestParam Integer fzdxId,
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
//            @RequestParam Date biangengshijian,
//            // @RequestParam Integer xiangzheng,
//            @RequestParam Integer cun,
//            @RequestParam Integer zuhao
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        Fazhanduixiang j=fazhanduixiangMapper.queryAll(fzdxId);
//        if(j==null){
//            map.put("result","此人不存在");
//        }else {
//            j.setFzdxName(name);
//            j.setFzdxSex(sex);
//            j.setFzdxNative(mingzu);
//            j.setFzdxShenfenzheng(shengfengzheng);
//            j.setFzdxDanwei(danwei);
//            j.setFzdxPhone(phone);
//            j.setFzdxZhiwu(zhiwu);
//            j.setFzdxWenhua(wenhua);
//            j.setFzdxEntity(shengfeng);
//            j.setFzdxYear(year);
//            j.setFzdxTime(biangengshijian);
//            //j.setFzdxId(xiangzheng);
//            j.setFzdxVillage(cun);
//            j.setFzdxZu(zuhao);
//
//            int result = fazhanduixiangMapper.UpdateFazhanduixiang(j);
//            if (result == 1) {
//                map.put("result", "success");
//            } else {
//                map.put("result", "error");
//            }
//        }
//        return  map;
//
//    }
//    @OperationLogDetail(operationType = OperationType.INSERT,operationUnit = OperationUnit.FAZHANDUIXIANG)
//    @PostMapping("/InsertFazhanduixiang")//添加发展对象
//    public Map<String, Object> InsertFazhanduixiang(
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
//            @RequestParam Date biangengshijian,
//            //@RequestParam Integer xiangzheng,
//            @RequestParam Integer cun,
//            @RequestParam Integer zuhao
//    ) {
//
//        Map<String, Object> map = new HashMap<>();
//        Fazhanduixiang j=new Fazhanduixiang();
//        j.setFzdxName(name);
//        j.setFzdxSex(sex);
//        j.setFzdxNative(mingzu);
//        j.setFzdxShenfenzheng(shengfengzhenghao);
//        j.setFzdxDanwei(danwei);
//        j.setFzdxPhone(phone);
//        j.setFzdxZhiwu(zhiwu);
//        j.setFzdxWenhua(wenhua);
//        j.setFzdxEntity(shengfen);
//        j.setFzdxYear(year);
//        j.setFzdxTime(biangengshijian);
//        //j.setFzdxId(xiangzheng);
//        j.setFzdxVillage(cun);
//        j.setFzdxZu(zuhao);
//
//        int result=fazhanduixiangMapper.InsertFazhanduixiang(j);
//        if (result == 1) {
//            map.put("result", "success");
//        } else {
//            map.put("result", "error");
//        }
//        return map;
//    }
//    @PostMapping("FazhanduixiangExcelUpload")//发展对象导入excel
//    @OperationLogDetail(operationType = OperationType.IMPORT,operationUnit = OperationUnit.FAZHANDUIXIANG)
//    public Map<String ,Object> zuzhifazhanExcelUpload(
//            @RequestParam Integer managerId,
//            //@RequestParam Integer jjfzEntity,
//            @RequestParam("ExcelFile") MultipartFile ExcelFile
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            List<Fazhanduixiang> demoList = new ArrayList<>();
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
//                    Fazhanduixiang fazhanduixiang = new Fazhanduixiang();
//                    Row row = sheet.getRow(j);//获得当前行数据
//                    Integer k = 0;
//                    if (fazhanduixiangMapper.findFazhanduixiangByxuhao(row.getCell(13).toString()) == null) {
//                        fazhanduixiang.setFzdxName(row.getCell(3).toString());
//                        if (row.getCell(10) != null) {
//                            fazhanduixiang.setFzdxTime(sdf.parse(row.getCell(10).getStringCellValue()));
//                        }
//                        fazhanduixiang.setFzdxSex(row.getCell(4).getStringCellValue());
//                        fazhanduixiang.setFzdxNative((int) row.getCell(5).getNumericCellValue());
//                        fazhanduixiang.setFzdxShenfenzheng(row.getCell(6).getStringCellValue());
//                        fazhanduixiang.setFzdxDanwei(row.getCell(11).getStringCellValue());
//                        fazhanduixiang.setFzdxPhone(row.getCell(7).toString());
//                        fazhanduixiang.setFzdxZhiwu(row.getCell(12).toString());
//                        fazhanduixiang.setFzdxWenhua(row.getCell(9).toString());
//                        fazhanduixiang.setFzdxVillage((int) row.getCell(1).getNumericCellValue());
//                        fazhanduixiang.setFzdxZu((int) row.getCell(2).getNumericCellValue());
//                        fazhanduixiang.setFzdxXuhao(row.getCell(13).toString());
//                        k = fazhanduixiangMapper.InsertFazhanduixiang(fazhanduixiang);
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
//
//
//
//}
