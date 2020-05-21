package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.AssetsdetailsService;
import com.hj.jdpth.service.CaiwulicaiService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
public class AssetsdetailsController {
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    AssetsdetailsService assetsdetailsService;
    @Autowired
    AssetsMapper assetsMapper;
    @Autowired
    CaiwulicaiService caiwulicaiService;
    @Autowired
    AssetsdetailsMapper assetsdetailsMapper;

    @PostMapping("/assetsdetails/importZichantaizhangExcel")
    public Map<String, Object> importZichantaizhangExcel(
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = assetsdetailsService.uploadFile_ZiChanTaiZhang(file);
            String path = map.get("path").toString();
            map.clear();
            map = assetsdetailsService.uploadExcel_ZiChanTaiZhang(path);
            File file1 = new File(path);
            file1.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/assetsdetails/importZiChanJingYingExcel")
    public Map<String, Object> importZiChanJingYingExcel(
            @RequestParam(required = false) Integer managerId,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = assetsdetailsService.uploadFile_ZiChanJingYing(file);
            String path = map.get("path").toString();
            map.clear();
            map = assetsdetailsService.uploadExcel_ZiChanJingYing(path);
            File file1 = new File(path);
            file1.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    //插入资产经营表(zcjyName 必须有值)
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.ZICHANJINGYING)
    @PostMapping("/assetsdetails/addZCJY")
    public Map<String, Object> addZCJY(
            @RequestParam(required = false) Integer managerId,
            @RequestParam(value = "file", required = false) MultipartFile[] file,
            @RequestParam Integer zcjyName, @RequestParam(required = false) String zcjyPhoto, @RequestParam(required = false) String zcjyZhenshilujing,
            @RequestParam(required = false) String zcjyZhuangtai, @RequestParam(required = false) String zcjyChengzhuren, @RequestParam(required = false) Boolean zcjyHetong,
            @RequestParam(required = false) String zcjyHetongqixian, @RequestParam(required = false) Float zcjyHetongjine, @RequestParam(required = false) String zcjyZhifuqinkuang,
            @RequestParam(required = false) String zcjyBeizhu, @RequestParam(required = false) String zcjyXvhao
    ) {
        Map<String, Object> map = new HashMap<>();
        String path = null;
        String path2 = "";
        Map<String, Object> date = null;
        if (zcjyHetongjine == null) {
            zcjyHetongjine = 0f;
        }
        if (file != null) {
            Map<String, Object> result = new HashMap<>();
            for (int i = 0; i < file.length; i++) {
                result = assetsdetailsService.uploadFile_ZiChanJingYing(file[i]);
                path = result.get("path").toString();
                path2 = path2 + "," + path;
            }
        }
        if (!path2.isEmpty()) {
            date = assetsdetailsService.addZCJY(zcjyName, path2.substring(1), zcjyZhenshilujing, zcjyZhuangtai, zcjyChengzhuren, zcjyHetong, zcjyHetongqixian, zcjyHetongjine, zcjyZhifuqinkuang, zcjyBeizhu, zcjyXvhao);
        } else {
            date = assetsdetailsService.addZCJY(zcjyName, path, zcjyZhenshilujing, zcjyZhuangtai, zcjyChengzhuren, zcjyHetong, zcjyHetongqixian, zcjyHetongjine, zcjyZhifuqinkuang, zcjyBeizhu, zcjyXvhao);
        }
        map.put("date", date);
        return map;
    }

    //插入资产台账表(zjtzName 必须有值)
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.ZICHANTAIZHANG)
    @PostMapping("/assetsdetails/addZCTZ")
    public Map<String, Object> addZCTZ(
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer zjtzName, @RequestParam(required = false) String zjtzJilangdanwei, @RequestParam(required = false) Integer zjtzShuliang,
            @RequestParam(required = false) Float zjtzDanjian, @RequestParam(required = false) String zjtzYuanzhi, @RequestParam(required = false) String zjtzBiandongqingkuang, @RequestParam(required = false) String zjtzXianzhi,
            @RequestParam(required = false) String zjtzPinpai, @RequestParam(required = false) String zjtzXinghao, @RequestParam(required = false) Date zjtzGoujianshijian,
            @RequestParam(required = false) String zjtzLaiyuan, @RequestParam(required = false) String zjtzYvjinianxian, @RequestParam(required = false) String zjtzDepartment, @RequestParam(required = false) String zjtzPeople,
            @RequestParam(required = false) String zjtzBeizhu, @RequestParam(required = false) String zjtzZhuangtai
    ) {
        Map<String, Object> result = new HashMap<>();
        if (zjtzDanjian == null) {
            zjtzDanjian = 0f;
        }
        try {
            Map<String, Object> date = assetsdetailsService.addZCTZ(zjtzName, zjtzJilangdanwei, zjtzShuliang, zjtzDanjian, zjtzYuanzhi, zjtzBiandongqingkuang, zjtzXianzhi, zjtzPinpai, zjtzXinghao, zjtzGoujianshijian, zjtzLaiyuan, zjtzYvjinianxian, zjtzDepartment, zjtzPeople, zjtzBeizhu, zjtzZhuangtai);
            result.put("date", date);
        } catch (Exception e) {
            result.put("error", "error");
        }
        return result;
    }

    //更新资产经营表（zcjyId 必须有值）
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.ZICHANJINGYING)
    @PutMapping("/assetsdetails/updateZiChanJingYing")
    public Map<String, Object> updateZiChanJingYing(
            @RequestParam(required = false) Integer managerId,
            @RequestParam(value = "file", required = false) MultipartFile[] file,
            @RequestParam Integer zcjyId, @RequestParam(required = false) String zcjyPhoto, @RequestParam(required = false) String zcjyZhenshilujing,
            @RequestParam(required = false) String zcjyZhuangtai, @RequestParam(required = false) String zcjyChengzhuren, @RequestParam(required = false) Boolean zcjyHetong,
            @RequestParam(required = false) String zcjyHetongqixian, @RequestParam(required = false) Float zcjyHetongjine, @RequestParam(required = false) String zcjyZhifuqinkuang,
            @RequestParam(required = false) String zcjyBeizhu, @RequestParam(required = false) String zcjyXvhao
    ) {
        Map<String, Object> map = new HashMap<>();
        String path = null;
        String path2 = "";
        if (zcjyHetongjine == null) {
            zcjyHetongjine = assetsdetailsMapper.quaryZCJY(zcjyId).getZcjyHetongjine();
        }
        if (file != null) {
            Map<String, Object> result = new HashMap<>();
            for (int i = 0; i < file.length; i++) {
                result = assetsdetailsService.uploadFile_ZiChanJingYing(file[i]);
                path = result.get("path").toString();
                path2 = path2 + "," + path;
            }
            //map.put("path",path2.substring(1));
        }
        if (!path2.isEmpty()) {
            Map<String, Object> date = assetsdetailsService.insertZCJY(zcjyId, path2.substring(1), zcjyZhenshilujing, zcjyZhuangtai, zcjyChengzhuren, zcjyHetong, zcjyHetongqixian, zcjyHetongjine, zcjyZhifuqinkuang, zcjyBeizhu, zcjyXvhao);
            map.put("date", date);
        }
        return map;
    }

    //更新资产台账表（zjtzId 必须有值）
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.ZICHANTAIZHANG)
    @PutMapping("/assetsdetails/updateZichantaizhang")
    public Map<String, Object> updateZichantaizhang(
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer zjtzId, @RequestParam(required = false) String zjtzJilangdanwei, @RequestParam(required = false) Integer zjtzShuliang,
            @RequestParam(required = false) Float zjtzDanjian, @RequestParam(required = false) String zjtzBiandongqingkuang, @RequestParam(required = false) String zjtzXianzhi,
            @RequestParam(required = false) String zjtzPinpai, @RequestParam(required = false) String zjtzXinghao, @RequestParam(required = false) Date zjtzGoujianshijian,
            @RequestParam(required = false) String zjtzLaiyuan, @RequestParam(required = false) String zjtzYvjinianxian, @RequestParam(required = false) String zjtzDepartment, @RequestParam(required = false) String zjtzPeople,
            @RequestParam(required = false) String zjtzBeizhu, @RequestParam(required = false) String zjtzZhuangtai
    ) {
        Map<String, Object> result = new HashMap<>();
        if (zjtzDanjian == null) {
            zjtzDanjian = assetsdetailsMapper.quaryZCTZ(zjtzId).getZjtzDanjian();
        }
        System.out.println(zjtzBeizhu + "/t" + zjtzZhuangtai);
        Map<String, Object> date = assetsdetailsService.insertZCTZ(zjtzId, zjtzJilangdanwei, zjtzShuliang, zjtzDanjian, zjtzBiandongqingkuang, zjtzXianzhi, zjtzPinpai, zjtzXinghao, zjtzGoujianshijian, zjtzLaiyuan, zjtzYvjinianxian, zjtzDepartment, zjtzPeople, zjtzBeizhu, zjtzZhuangtai);
        result.put("date", date);
        return result;
    }

    //查看详情
    @PostMapping("/assetsdetails/quaryZJTZAndZCJY")
    public Map<String, Object> quaryZJTZAndZCJY(
            @RequestParam Integer ZCMZ_Id,
            @RequestParam Integer aVillagedid,
            @RequestParam Integer aZu) {
        Map<String, Object> result = new HashMap<>();
        String zhenName = assetsdetailsMapper.quaryZhen(aVillagedid);
        String cunName = villageMapper.queryByid_gsh(aVillagedid).getvName();
        String zuName = assetsdetailsMapper.quaryZu(aZu);
        String qvName = assetsdetailsMapper.quaryQv(aVillagedid);
        result.put("qv", qvName);
        result.put("zhen", zhenName);
        result.put("cun", cunName);
        result.put("zu", zuName);
        if (ZCMZ_Id != null) {
            Zichantaizhang zichantaizhang = assetsdetailsMapper.quaryZichantaizhang(ZCMZ_Id);
            Zichanjingying zichanjingying = assetsdetailsMapper.quaryZiChanJingYing(ZCMZ_Id);
            if (zichantaizhang != null) {
                result.put("zichantaizhang", zichantaizhang);
            } else {
                result.put("zichantaizhang", "没有数据");
            }
            if (zichanjingying != null) {
                result.put("zichanjingying", zichanjingying);
            } else {
                result.put("zichanjingying", "没有数据");
            }
            String ZCMZ_Name = null;
            if (zichantaizhang != null) {
                ZCMZ_Name = assetsdetailsMapper.quaryZCMZ_Name(zichantaizhang.getZjtzName());
            }
            if (zichanjingying != null) {
                ZCMZ_Name = assetsdetailsMapper.quaryZCMZ_Name(zichanjingying.getZcjyName());
            }
            result.put("ZCTZ_TypeName", ZCMZ_Name);
            result.put("ZCJY_TypeName", ZCMZ_Name);
            result.put("state", "success");
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    //添加资产名字表和资产表
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.ZICHANMINGZIASSETS)
    @PostMapping("/assetsdetails/addZichanmingzi")
    public Map<String, Object> addZichanmingzi(
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer A_Zu,
            @RequestParam Integer A_Villageid,
            @RequestParam String ZCMZ_Name,
            @RequestParam Integer A_Type) {
        Map<String, Object> map = null;
        if (A_Zu != null && A_Villageid != null && ZCMZ_Name != null && A_Type != null) {
            map = assetsdetailsService.addZCMZ_Name(A_Zu, A_Villageid, ZCMZ_Name, A_Type);
        }
        return map;
    }

    //四级查询
    @PostMapping("/assetsdetails/Zichanmingzi")
    public Map<String, Object> quaryZichanmingzi(
            @RequestParam(required = false) Integer mType,
            @RequestParam Integer mRegion,
            @RequestParam(required = false) Integer mZhenid,
            @RequestParam(required = false) Integer mVillageid,
            @RequestParam(required = false) String aType,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize) {
        Map<String, Object> result = new HashMap<>();

        if (mRegion != null && startPage != null && pageSize != null) {
            if (mType == null) {
                mType = 0;
            }
            if (mZhenid == null) {
                mZhenid = 0;
            }
            if (mVillageid == null) {
                mVillageid = 0;
            }
            result = assetsdetailsService.quaryAssetsdetails(mType, mRegion, mZhenid, mVillageid, aType, startPage, pageSize);

        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    //资产类型单独查询
    @PostMapping("/assetsdetails/ZichanmingziByType")
    public Map<String, Object> quaryZichanmingziByType(
            @RequestParam String aType,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        if (aType != null && startPage != null && pageSize != null) {
            List<Assets> assets = assetsMapper.quaryAllType();
            Map<String, Object> map = assetsdetailsService.quaryZichanmingziByType(aType, startPage, pageSize);
            result.put("TTTTType", assets);
            result.put("AssetsNameType", map);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    //单删
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.ZICHANMINGZI)
    @DeleteMapping("/assetsdetails/ZichanmingziDeleteOne/{ZCMZ_Id}/{managerId}")
    public Map<String, Object> deleteOne(
            @PathVariable(value = "ZCMZ_Id") Integer ZCMZ_Id,
            @PathVariable(required = false) Integer managerId) {
        System.out.println(ZCMZ_Id);
        Map<String, Object> result = new HashMap<>();
        if (ZCMZ_Id != null) {
            Map<String, Object> map = assetsdetailsService.deleteOne(ZCMZ_Id);
            result.put("data", map);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    //多删
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.ZICHANMINGZI)
    @DeleteMapping("/assetsdetails/ZichanmingziDeleteMany/{ZCMZ_Id}/{managerId}")
    public Map<String, Object> deleteMany(
            @PathVariable(value = "ZCMZ_Id") String ZCMZ_Id,
            @PathVariable(required = false) Integer managerId) {
        Map<String, Object> result = new HashMap<>();
        if (!ZCMZ_Id.equals("null")) {
            Map<String, Object> map = assetsdetailsService.deleteMany(ZCMZ_Id);
            result.put("data", map);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    //根据村id和组id查询资产类型
    @PostMapping("/assetsdetails/quaryAType")
    public Map<String, Object> quaryA_Type(
            @RequestParam(value = "A_Villageid") Integer A_Villageid,
            @RequestParam(value = "A_Zu") Integer A_Zu) {
        Map<String, Object> result = new HashMap<>();
        if (A_Villageid != null && A_Zu != null) {
            try {
                Map<String, Object> map = assetsdetailsService.quaryA_Type(A_Villageid, A_Zu);
                result.put("data", map);
                result.put("status", "success");
            } catch (Exception e) {
                result.put("data", "error");
                result.put("status", "error");
            }
        } else {
            result.put("data", "null");
            result.put("status", "数据输入不完整");
        }
        return result;
    }

    @GetMapping("/assetsdetails/quaryAllType")
    public Map<String, Object> quaryAllType() {
        Map<String, Object> map = new HashMap<>();
        List<Assets> list = assetsdetailsMapper.quaryAllType();
        map.put("Type", list);
        return map;
    }


}
