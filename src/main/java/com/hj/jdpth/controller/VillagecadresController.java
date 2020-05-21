package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Villagecadres;
import com.hj.jdpth.domain.Villagecadres_Custom;
import com.hj.jdpth.service.VillagecadresService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class VillagecadresController {

    @Autowired
    VillagecadresService villagecadresService;

    //动态查询干部信息
    @PostMapping("/villagecadres/queryVillagecadres/{startPage}/{pageSize}/{regionId}")
    public Map queryVillagecadres(@PathVariable(value = "startPage") int startPage, @PathVariable(value = "pageSize") int pageSize, @PathVariable(value = "regionId") Integer regionId, @RequestParam(value = "zhenId", required = false) Integer zhenId, @RequestParam(value = "cunId", required = false) Integer cunId, @RequestParam(value = "year", required = false) String year) {
        Map map = new HashMap<String, Object>();
        Map params = new HashMap<String, Object>();
        params.put("startPage", startPage);
        params.put("pageSize", pageSize);
        params.put("regionId", regionId);
        params.put("zhenId", zhenId);
        params.put("cunId", cunId);
        params.put("year", year);

        Map<String, Object> list = villagecadresService.queryVillagecadres(params);

        map.put("list", list);
        return map;
    }

    //干部信息详情
    @GetMapping("/villagecadres/queryVillagecadreDetail/{id}")
    public Map queryVillagecadreDetail(@PathVariable Integer id) {
        Map map = new HashMap();
        map.put("villagecadre", villagecadresService.getVillagecadreDetail(id));
        return map;
    }

    //添加干部
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.VILLAGECADRES)
    @PostMapping("/villagecadres/insertVillagecadre/{managerId}/{mType}")
    public Map insertVillagecadre(Villagecadres villagecadres,
                                  @PathVariable int managerId, @PathVariable int mType,
                                  MultipartFile image) {
        Map map = new HashMap();
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        if (image != null) {
            try {
                String imageName = villagecadresService.uploadImage(image);
                villagecadres.setVcNewimagename(imageName);
                villagecadres.setVcOldimagename(imageName);
            } catch (IOException e) {
                e.printStackTrace();
                map.put("STSTUS", "FALSE:图片上传失败");
            }
        }

        try {
            villagecadresService.insertDynameic(villagecadres);
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE");
            return map;
        }
    }

    //更新干部
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.VILLAGECADRES)
    @PostMapping("/villagecadres/updateVillagecadres/{managerId}/{mType}")
    public Map updateVillagecadre(Villagecadres villagecadres, @PathVariable int managerId, @PathVariable int mType, MultipartFile image) {
        System.out.println(villagecadres.toString());
        Map map = new HashMap();
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        if (image != null) {
            if (villagecadresService.deleteImage(villagecadres.getVcOldimagename())) {
                try {
                    String imageName = villagecadresService.uploadImage(image);
                    villagecadres.setVcNewimagename(imageName);
                    villagecadres.setVcOldimagename(imageName);
                } catch (IOException e) {
                    e.printStackTrace();
                    map.put("STSTUS", "FALSE:图片上传失败");
                }
            } else {
                map.put("STSTUS", "FALSE:旧图片删除失败");
            }
        }

        try {
            villagecadresService.updateDynamic(villagecadres);
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE");
            return map;
        }
    }

    //删除干部
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.VILLAGECADRES)
    @PostMapping("/villagecadres/deleteVillageres/{managerId}/{mType}")
    public Map deleteVillagecadres(@RequestParam("ids") List<Integer> ids, @PathVariable int managerId, @PathVariable int mType) {
        Map map = new HashMap();
        if (mType == 3) {
            map.put("STSTUS", "FALSE");
            return map;
        }
        try {
            villagecadresService.deleteVillagecadres(ids);
            map.put("STSTUS", "SUCCESS");
            return map;
        } catch (Exception e) {
            map.put("STSTUS", "FALSE");
            return map;
        }
    }

    //////////////搜索模块//////////
    //级联区ID
    //登录自动选定
    @GetMapping("/villagecadres/getRegions")
    public Map getRegions(Manager manager) {
        Map map = new HashMap<String, Object>();
        map.put("list", villagecadresService.selectRegionID(manager.getmRegion()));
        return map;
    }

    //级联镇ID
    //镇/村固定
    @GetMapping("/villagecadres/getZhens")
    public Map getZhens(Manager manager) {
        Map map = new HashMap<String, Object>();
        map.put("list", villagecadresService.selectZhenID(manager.getmRegion(), manager.getmType(), manager.getmZhenid()));
        return map;
    }

    //级联村ID
    //村固定
    @GetMapping("/villagecadres/getVillages/{zhenId}")
    public Map getVillages(@PathVariable int zhenId, Manager manager) {
        Map map = new HashMap<String, Object>();
        map.put("list", villagecadresService.selectCunID(zhenId, manager.getmType(), manager.getmVillageid()));
        return map;
    }

    ///////添加模块///////////////////////////
    //下拉列表Job name And Id
    @GetMapping("/villagecadres/queryJobName")
    public Map queryJobName() {
        Map map = new HashMap();
        map.put("list", villagecadresService.selectNameAdID());
        return map;
    }

    //某村下所有职务
    @GetMapping("/villagecadres/queryJobNameBuVillageId/{cunId}")
    public Map queryJobNameById(@PathVariable int cunId) {
        Map map = new HashMap();
        map.put("list", villagecadresService.queryJobNameById(cunId));
        return map;
    }

    //省，下拉列表
    @GetMapping("/villagecadres/queryShengAdinsert")
    public Map queryShenName() {
        Map map = new HashMap();
        map.put("Sheng", villagecadresService.selectShengAdId());
        return map;
    }

    //市，下拉列表
    @GetMapping("/villagecadres/queryShiAdinsert/{id}")
    public Map queryShiName(@PathVariable int id) {
        Map map = new HashMap();
        map.put("Shi", villagecadresService.selectShigAdId(id));
        return map;
    }

    //区，下拉列表
    @GetMapping("/villagecadres/queryRegionAdinsert/{id}")
    public Map queryRegionName(@PathVariable int id) {
        Map map = new HashMap();
        map.put("Region", villagecadresService.selectRegionAdId(id));
        return map;
    }

    //镇，下拉列表
    @GetMapping("/villagecadres/queryZhenAdinsert/{id}")
    public Map queryZhenName(@PathVariable int id) {
        Map map = new HashMap();
        map.put("Zhen", villagecadresService.selectZhenAdId(id));
        return map;
    }

    //村，下拉列表
    @GetMapping("/villagecadres/queryVillageAdinsert/{id}")
    public Map queryVillageName(@PathVariable int id) {
        Map map = new HashMap();
        map.put("Cun", villagecadresService.selectVillageAdId(id));
        return map;
    }
}
