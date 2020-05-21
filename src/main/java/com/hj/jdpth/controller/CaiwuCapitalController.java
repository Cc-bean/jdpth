package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Capital;
import com.hj.jdpth.domain.Capitaldetails;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Zhen;
import com.hj.jdpth.repository.CaiwuCapitalMapper;
import com.hj.jdpth.service.CaiwuCapitalService;
import com.hj.jdpth.service.CaiwulicaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class CaiwuCapitalController {
    @Autowired
    CaiwuCapitalService caiwuCapitalService;
    @Autowired
    CaiwuCapitalMapper caiwuCapitalMapper;
    @Autowired
    CaiwulicaiService caiwulicaiService;

    @PostMapping("/caiwu/findCapitaldetails")
    public Map<String, Object> findCapitaldetails(
            @RequestParam Integer mRegion,
            @RequestParam(required = false) Integer mVillageid,
            @RequestParam(required = false) Integer mZhenid,
            @RequestParam(required = false) Integer mType,
            @RequestParam(required = false) String C_Type,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize

    ) {
        Map<String, Object> result = new HashMap<>();
        System.out.println("village" + mVillageid + "zhenId" + mZhenid);
        if (mZhenid == null) {
            mZhenid = 0;
        }
        if (mVillageid == null) {
            mVillageid = 0;
        }
        if (mType == null) {
            mType = 0;
        }
        result = caiwuCapitalService.findCapitaldetails(mRegion, mZhenid, mVillageid, C_Type, mType, startPage, pageSize);
        return result;
    }

    @GetMapping("/caiwu/findzhenByRegionId/{regionId}")
    public Map<String, Object> findzhenByRegionId(
            @PathVariable(value = "regionId") Integer regionId
    ) {
        Map<String, Object> result = new HashMap<>();
        result = caiwuCapitalService.findzhenByRegionId(regionId);
        return result;

    }

    @GetMapping("/caiwu/findzhenByZhenId/{zhenId}")
    public Map<String, Object> findzhenByZhenId(
            @PathVariable(value = "zhenId") Integer zhenId
    ) {
        Map<String, Object> result = new HashMap<>();
        result = caiwuCapitalService.findVillageByZhenId(zhenId);
        return result;
    }

    @PostMapping("/caiwu/findCapitaldetailsByZhenId")
    public Map<String, Object> findCapitaldetailsByZhenId(
            @RequestParam Integer zhenId,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> result = new HashMap<>();
        result = caiwuCapitalService.findCapitaldetailsByZhenId(zhenId, startPage, pageSize);
        return result;
    }

    @PostMapping("/caiwu/findCapitaldetailsByVillageId")
    public Map<String, Object> findCapitaldetailsByVillageId(
            @RequestParam Integer villageId,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> result = new HashMap<>();
        result = caiwuCapitalService.findCapitaldetailsByVillageId(villageId, startPage, pageSize);
        return result;
    }

    @PostMapping("/caiwu/findCapitaldetailsByStyle")
    public Map<String, Object> findCapitaldetailsByStyle(
            @RequestParam Integer regionId,
            @RequestParam(required = false) Integer zhenId,
            @RequestParam(required = false) Integer villageId,
            @RequestParam String cType,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize
    ) {
        Map<String, Object> result = new HashMap<>();
        if (zhenId == null) {
            zhenId = 0;
        }
        if (villageId == null) {
            villageId = 0;
        }
        result = caiwuCapitalService.findCapitaldetailsByStyle(regionId, zhenId, villageId, cType, startPage, pageSize);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.CAPITALDETAILS)
    @DeleteMapping("/caiwu/deleteCapitaldetails")
    public Map<String, Object> deleteCapitaldetails(
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer capitalDetailsId
    ) {
        Map<String, Object> result = new HashMap<>();
        Capitaldetails capitaldetails = caiwuCapitalMapper.findCapitaldetailsById(capitalDetailsId);
        if (capitaldetails.getCdBeiyong1() != null) {
            String[] lujing = capitaldetails.getCdBeiyong1().split(",");
            for (String lujing1 : lujing) {
                File file = new File("E:\\HJResourse\\Capitaldetails\\" + lujing1);
                file.delete();
            }
        }
        result = caiwuCapitalService.deleteCapitaldetails(capitalDetailsId);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.CAPITALDETAILS)
    @DeleteMapping("/caiwu/deleteManyCapitaldetails/{capitalDetailsId}/{managerId}")
    public Map<String, Object> deleteManyCapitaldetails(
            @PathVariable(value = "managerId", required = false) Integer managerId,
            @PathVariable(value = "capitalDetailsId") String capitalDetailsId
    ) {
        Map<String, Object> result = new HashMap<>();
        String[] deleteId = capitalDetailsId.split(",");

        result = caiwuCapitalService.deleteManyCapitaldetails(deleteId);
        return result;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.CAPITAL)
    @PostMapping("/caiwu/insertCapital")
    public Map<String, Object> insertCapital(
            @RequestParam(required = false) Integer managerId,
            @RequestParam String cType,
            @RequestParam Integer cVillageid,
            @RequestParam String cdIncome,
            @RequestParam String cdExpenditure,
            @RequestParam(required = false) String cdBeiyong1,
            @RequestParam(required = false) String cdBeiyong2,
            @RequestParam Integer cdZu
    ) {
        Map<String, Object> result = new HashMap<>();
        Capital capital = new Capital();
        capital.setCdIncome(cdIncome);
        capital.setCdExpenditure(cdExpenditure);
        capital.setCdBeiyong1(cdBeiyong1);
        capital.setCdBeiyong2(cdBeiyong2);
        capital.setCdZu(cdZu);
        capital.setcVillageid(cVillageid);
        Capital capital1 = null;
        try {
            capital1 = caiwuCapitalMapper.findCapitalmohu(cVillageid, Integer.parseInt(cdIncome), Integer.parseInt(cdExpenditure), cType);
        } catch (Exception e) {
        }
        if (capital1 == null) {
            Village village = caiwuCapitalMapper.findVillageById(cVillageid);
            Zhen zhen = caiwuCapitalMapper.findZhenById(village.getvZhenXiang());
            String a = zhen.getzName() + village.getvName() + cdIncome + "年" + cdExpenditure + "月" + cType + "明细";
            capital.setcType(a);
            result = caiwuCapitalService.insertCapital(capital);
        } else {
            result.put("data", "资金类型已存在");
            result.put("state", "panduan");
        }
        return result;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.CAPITALDETAILS)
    @PostMapping("/caiwu/insertCapitaldetails")
    public Map<String, Object> insertCapitaldetails(
            @RequestParam Integer managerId,
            @RequestParam(required = false) String cdIncome,
            @RequestParam(required = false) String cdExpenditure,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date cdTime,
            @RequestParam(required = false) String cdXiangqing,
            @RequestParam Integer cdZu,
            @RequestParam String style,
            @RequestParam Integer villageId,
            @RequestParam(required = false) MultipartFile[] files
    ) {
        Map<String, Object> result = new HashMap<>();
        Capitaldetails capitaldetails = new Capitaldetails();
        if (cdIncome != null) {
            capitaldetails.setCdIncome(new BigDecimal(cdIncome));
        }
        if (cdExpenditure != null) {
            capitaldetails.setCdExpenditure(new BigDecimal(cdExpenditure));
        }
        capitaldetails.setCdTime(cdTime);
        capitaldetails.setCdXiangqing(cdXiangqing);
        capitaldetails.setCdZu(cdZu);
        if (files != null) {
            Map<String, Object> map = new HashMap<>();
            String name = "";
            for (MultipartFile file : files) {
                map = caiwuCapitalService.uploadFile(file, "E:\\HJResourse\\Capitaldetails\\");
                name = name + map.get("path") + ",";
            }
            capitaldetails.setCdBeiyong1(name);
        }
        Capital capital = caiwuCapitalMapper.findCapitalmohu(villageId, Integer.parseInt(cdTime.toString().substring(0, 4)), Integer.parseInt(cdTime.toString().substring(5, 7)), style);
        if (capital != null) {
            capitaldetails.setCdZijinid(capital.getCapitalId());
            result = caiwuCapitalService.insertCapitaldetails(capitaldetails);
        } else {
            result.put("data", "资金类型不存在");
        }
        return result;
    }

    /*
     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @OperationLogDetail(operationType = OperationType.IMPORT, operationUnit = OperationUnit.CAPITALANDCAPITALDETAILS)
    @PostMapping("/caiwu/importCapitalExcel")
    public Map<String, Object> importCapitalExcel(
            @RequestParam Integer managerId,
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        Map<String, Object> result = new HashMap<>();
        //用来检测程序运行时间
        try {
            String path = "";
            result = caiwuCapitalService.uploadFile(file, "E:\\HJResourse\\Capitals\\");
            path = "E:\\HJResourse\\Capitals\\" + result.get("path");
            result.putAll(caiwuCapitalService.uploadExcel(path));
            File file1 = new File(path);
            file1.delete();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.put("status", "出现错误");
        }
        return result;
    }

    @PostMapping("/caiwu/importYearCapitalExcel")
    public Map<String, Object> importYearCapitalExcel(
            @RequestParam("file") MultipartFile file
    ) {
        Map<String, Object> result = new HashMap<>();
        //用来检测程序运行时间
        String path = "";
        result = caiwuCapitalService.uploadFile(file, "E:\\HJResourse\\Capitals\\");
        try {
            path = "E:\\HJResourse\\Capitals\\" + result.get("path");
            result.putAll(caiwuCapitalService.yearUploadExcel(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file1 = new File(path);
        file1.delete();
        return result;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.CAPITALDETAILS)
    @PutMapping("/caiwu/updateCapitaldetails")
    public Map<String, Object> updateCapitaldetails(
            @RequestParam Integer managerId,
            @RequestParam Integer CapitalDetails_Id,
            @RequestParam(required = false) String CD_Income,
            @RequestParam(required = false) String CD_Expenditure,
            @RequestParam String CD_Time,
            @RequestParam(required = false) String CD_XiangQing,
            @RequestParam Integer CD_zu,
            @RequestParam String C_Type,
            @RequestParam Integer C_Villageid,
            @RequestParam(required = false) MultipartFile[] files
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            Capitaldetails capitaldetails = caiwuCapitalMapper.findCapitaldetailsById(CapitalDetails_Id);
            if (CD_Income != null) {
                capitaldetails.setCdIncome(new BigDecimal(CD_Income));
            }
            if (CD_Expenditure != null) {
                capitaldetails.setCdExpenditure(new BigDecimal(CD_Expenditure));
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            capitaldetails.setCdTime(df.parse(CD_Time));
            if (capitaldetails.getCdXiangqing() != CD_XiangQing) {
                capitaldetails.setCdXiangqing(CD_XiangQing);
            }
            if (files != null) {
                Map<String, Object> map = new HashMap<>();
                String name = "";
                String[] lujing = capitaldetails.getCdBeiyong1().split(",");
                for (String lujing1 : lujing) {
                    File file = new File("E:\\HJResourse\\Capitaldetails\\" + lujing1);
                    file.delete();
                }
                for (MultipartFile file : files) {
                    map = caiwuCapitalService.uploadFile(file, "E:\\HJResourse\\Capitaldetails\\");
                    name = name + map.get("path") + ",";
                }
                capitaldetails.setCdBeiyong1(name);
            }
            capitaldetails.setCdZu(CD_zu);
            Capital capital = caiwuCapitalMapper.findCapitalmohu(C_Villageid, Integer.parseInt(CD_Time.toString().substring(0, 4)), Integer.parseInt(CD_Time.toString().substring(5, 7)), C_Type);
            capitaldetails.setCdZijinid(capital.getCapitalId());
            result = caiwuCapitalService.updateCapitaldetails(capitaldetails);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("data", "出现错误!");
            result.put("state", "false");
        }
        return result;
    }

    @PostMapping("/caiwu/findCapitalaDetails")
    public Map<String, Object> findCapitalaDetails(
            @RequestParam Integer capitaldetailsId
    ) {
        Map<String, Object> result = new HashMap<>();
        result = caiwuCapitalMapper.findCapitalaDetails(capitaldetailsId);
        return result;
    }
}
