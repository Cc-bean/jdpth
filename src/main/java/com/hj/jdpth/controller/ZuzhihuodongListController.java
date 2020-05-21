package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Dangyuanzuzhihuodong;
import com.hj.jdpth.repository.ZuzhihuodongSelectMapper;
import com.hj.jdpth.service.FileUploadService;
import com.hj.jdpth.service.ZuzhihuodongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ZuzhihuodongListController {
    @Autowired
    ZuzhihuodongListService zuzhihuodongListService;

    @Autowired
    ZuzhihuodongSelectMapper zuzhihuodongSelectMapper;

    @Autowired(required = false)
    FileUploadService fileUploadService;


    @PostMapping("h/ZuzhihuodongListFirst")//初始进入
    public Map<String, Object> zuzhihuodongListFirst(@RequestParam Integer mRegion,//区
                                                     @RequestParam Integer mZhenid,//镇
                                                     @RequestParam Integer mVillageid,//村
                                                     @RequestParam Integer startPage,
                                                     @RequestParam Integer pageSize,
                                                     @RequestParam Integer mType//管理员类型
    ) {
        Map<String, Object> map = new HashMap<>();
        if (mType == null || mRegion == null || mZhenid == null || mVillageid == null) {
            map.put("data", "管理员null");
            map.put("state", "false");
        } else {
            map = zuzhihuodongListService.GetinfoFirstAll(mRegion, mZhenid, mVillageid, startPage, pageSize, mType);
        }
        return map;
    }

    @PostMapping("h/ZuzhihuodongListSelect")//查询、上下页、跳转
    public Map<String, Object> zuzhihuodongListSelect(@RequestParam Integer mRegion,//区
                                                      @RequestParam(required = false) Integer mZhenid,//镇
                                                      @RequestParam(required = false) Integer mVillageid,//村
                                                      @RequestParam(required = false) Integer typeId,//党员组织活动类
                                                      @RequestParam Integer startPage,//当前页
                                                      @RequestParam Integer pageSize//每页显示条数
    ) {//管理员类型
        Map<String, Object> map = new HashMap<>();
        try {
            map = zuzhihuodongListService.GetinfoSelectAll(mRegion, mZhenid, mVillageid, typeId, startPage, pageSize);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @GetMapping("h/ZuzhihuodongListSelectOne/{dangyuanzuzhihuodongId}")//查看详情
    public Map<String, Dangyuanzuzhihuodong> zuzhihuodongListSelect(@PathVariable(value = "dangyuanzuzhihuodongId") Integer dangyuanzuzhihuodongId) {
        Map<String, Dangyuanzuzhihuodong> map = new HashMap<>();
        try {
            map = zuzhihuodongListService.GetinfoOne(dangyuanzuzhihuodongId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.DANGYUANZUZHIHUODONG)
    @DeleteMapping("h/ZuzhihuodongListDeleteOne/{dangyuanzuzhihuodongId}/{managerId}")//单个删除
    public Map<String, Object> zuzhihuodongListDeleteOne(@PathVariable(value = "dangyuanzuzhihuodongId") Integer dangyuanzuzhihuodongId, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Dangyuanzuzhihuodong> stringDangyuanzuzhihuodongMap = zuzhihuodongListService.GetinfoOne(dangyuanzuzhihuodongId);
            if (stringDangyuanzuzhihuodongMap.get("data").getDyzzhdZhenshilujing() != null) {
                String[] urls = stringDangyuanzuzhihuodongMap.get("data").getDyzzhdZhenshilujing().split(",");
                for (int i = 0; i < urls.length; i++) {
                    File file = new File("E:\\HJResourse\\Zuzhihuodong\\" + urls[i]);
                    file.delete();
                }
            }
            map = zuzhihuodongListService.DeleteOne(dangyuanzuzhihuodongId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.DANGYUANZUZHIHUODONG)
    @DeleteMapping("h/ZuzhihuodongListDeleteMany/{dangyuanzuzhihuodongIds}/{managerId}")//多个删除
    public Map<String, Object> zuzhihuodongListDeleteOne(@PathVariable(value = "dangyuanzuzhihuodongIds") String dangyuanzuzhihuodongIds, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        try {
            String[] split = dangyuanzuzhihuodongIds.split(",");
            for (int j = 0; j < split.length; j++) {
                Map<String, Dangyuanzuzhihuodong> stringDangyuanzuzhihuodongMap = zuzhihuodongListService.GetinfoOne(Integer.parseInt(split[j]));
                if (stringDangyuanzuzhihuodongMap.get("data").getDyzzhdZhenshilujing() != null) {
                    String[] urls = stringDangyuanzuzhihuodongMap.get("data").getDyzzhdZhenshilujing().split(",");
                    for (int i = 0; i < urls.length; i++) {
                        File file = new File("E:\\HJResourse\\Zuzhihuodong\\" + urls[i]);
                        file.delete();
                    }
                }
                zuzhihuodongListService.DeleteOne(Integer.parseInt(split[j]));
            }
            //System.out.println(dangyuanzuzhihuodongIds);
            //map = zuzhihuodongListService.DeleteMany(dangyuanzuzhihuodongIds);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @PostMapping("h/ZuzhihuodongAddTypeFirst")//初次进入
    public Map<String, Object> zuzhihuodongAddTypeFirst(
            @RequestParam Integer mRegion,//区
            @RequestParam Integer mZhenid,//镇
            @RequestParam Integer mVillageid,//村
            @RequestParam Integer mType
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = zuzhihuodongListService.GetInfoAddTypeFirst(mRegion, mZhenid, mVillageid, mType);
            map.put("state", "success");
            map.put("data", "不为空");
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.HUODONGLEIXING)
    @PostMapping("h/ZuzhihuodongAddType")//添加一个党员活动类型
    public Map<String, Object> zuzhihuodongAddType(
            @RequestParam Integer hdlxZhen,
            @RequestParam Integer hdlxVillage,
            @RequestParam String hdlxName,
            @RequestParam Integer managerId//管理员id
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = zuzhihuodongListService.GetInfoAddType(hdlxZhen, hdlxVillage, hdlxName);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @PostMapping("h/ZuzhihuodongAddFirst")//初次进入党员组织活动添加页面
    public Map<String, Object> zuzhihuodongAddFirst(
            @RequestParam Integer mRegion,//区
            @RequestParam Integer mZhenid,//镇
            @RequestParam Integer mVillageid,//村
            @RequestParam Integer mType
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (mType == 1 || mType == 2 || mType == 3) {
                map.put("data", zuzhihuodongListService.GetInfoType(mRegion, mZhenid, mVillageid).get("qu"));
                map.put("state", "success");
            } else if (mType == 4) {
                map.put("data", zuzhihuodongListService.GetInfoType(mRegion, mZhenid, mVillageid).get("zhen"));
                map.put("state", "success");
            } else {
                map.put("data", zuzhihuodongListService.GetInfoType(mRegion, mZhenid, mVillageid).get("cun"));
                map.put("state", "success");
            }
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }

        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.DANGYUANZUZHIHUODONG)
    @PostMapping("h/ZuzhihuodongAddInfo")//党员组织活动添加
    public Map<String, Object> zuzhihuodongAddInfo(
            @RequestParam Integer Villageid,//村id，与活动类型相对应
            @RequestParam Integer managerId,
            @RequestParam Integer huodongTypeId,
            @RequestParam String huodongName,//名称
            @RequestParam String huodongPlace,//活动地点
            @RequestParam String huodongtime,//活动时间
            @RequestParam String huodongContent, //活动内容
            @RequestParam(value = "file", required = false) MultipartFile[] file, HttpServletRequest request
    ) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String paths = "";
        for (MultipartFile file1 : file) {
            String path = (String) fileUploadService.fileUpload(file1, request).get("path");
            paths = paths + path + ",";
        }
        try {
            Dangyuanzuzhihuodong dangyuanzuzhihuodong = new Dangyuanzuzhihuodong();
            dangyuanzuzhihuodong.setDyzzhdLeixing(huodongTypeId);
            dangyuanzuzhihuodong.setDyzzhdContent(huodongContent);
            dangyuanzuzhihuodong.setDyzzhdName(huodongName);
            dangyuanzuzhihuodong.setDyzzhdPhoto(paths);
            dangyuanzuzhihuodong.setDyzzhdPlace(huodongPlace);
            dangyuanzuzhihuodong.setDyzzhdTime(sdf.parse(huodongtime));
            dangyuanzuzhihuodong.setDyzzhdZhenshilujing(paths);
            dangyuanzuzhihuodong.setDyzzhdVillageid(Villageid);
            map = zuzhihuodongListService.GetInfoAddInfo(dangyuanzuzhihuodong);
        } catch (Exception e) {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @GetMapping("h/zuzhihuodongTypeListSelect/{villageId}")//查看当前村的组织活动类型
    public Map<String, Object> zuzhihuodongTypeListSelect(@PathVariable(value = "villageId") Integer villageId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = zuzhihuodongListService.GetInfoType(villageId);
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.DANGYUANZUZHIHUODONG)
    @PostMapping("h/zuzhihuodongUpdateInfo")//党员组织活动修改
    public Map<String, Object> zuzhihuodongUpdateInfo(
            @RequestParam Integer huodongId,//该活动的id
            @RequestParam Integer Villageid,//村id，与活动类型相对应
            @RequestParam Integer managerId,
            @RequestParam Integer huodongTypeId,
            @RequestParam String huodongName,//名称
            @RequestParam String huodongPlace,//活动地点
            @RequestParam String huodongtime,//活动时间
            @RequestParam String huodongContent, //活动内容
            @RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request
    ) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Dangyuanzuzhihuodong dangyuanzuzhihuodong = zuzhihuodongListService.GetinfoOne(huodongId).get("data");
        if (image != null) {
            File file = new File(dangyuanzuzhihuodong.getDyzzhdZhenshilujing());
            file.delete();
            String path = (String) fileUploadService.fileUpload(image, request).get("path");
            dangyuanzuzhihuodong.setDyzzhdPhoto(path);
            dangyuanzuzhihuodong.setDyzzhdZhenshilujing(path);
        }
        dangyuanzuzhihuodong.setDyzzhdLeixing(huodongTypeId);
        dangyuanzuzhihuodong.setDyzzhdContent(huodongContent);
        dangyuanzuzhihuodong.setDyzzhdName(huodongName);
        dangyuanzuzhihuodong.setDyzzhdPlace(huodongPlace);
        try {
            dangyuanzuzhihuodong.setDyzzhdTime(sdf.parse(huodongtime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        map = zuzhihuodongListService.UpdateInfo(dangyuanzuzhihuodong);
        return map;
    }

    @PostMapping("h/huodongFindByZhenAndTime")//初次进入党员组织活动添加页面
    public Map<String, Object> huodongFindByZhenAndTime(
            @RequestParam Integer mZhenid,//镇
            @RequestParam String year,//开始时间
            @RequestParam Integer startPage,//当前页
            @RequestParam Integer pageSize//每页显示条数
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            Integer size = zuzhihuodongSelectMapper.FindByZhenAndTime(mZhenid, year).size();
            Integer pages = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
            PageHelper.startPage(startPage, pageSize);
            List<HashMap> list = zuzhihuodongSelectMapper.FindByZhenAndTime(mZhenid, year);
            map.put("total", pages);
            map.put("size", size);
            map.put("data", list);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

}
