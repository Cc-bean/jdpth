package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class LawController {

    @Autowired
    LawService lawService;
    @Autowired
    ShengMapper shengMapper;
    @Autowired
    ShiMapper shiMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    VillageMapper villageMapper;

    @GetMapping("/lawTypeList")
    public Map<String, Object> lawTypeList() {
        Map<String, Object> map = new HashMap<>();
        List<Lawtype> lawtypeList = lawService.queryAllType();
        map.put("lawtypeList", lawtypeList);
        return map;
    }

    @PostMapping("/queryLaw")
    public Map<String, Object> queryLaw(@RequestParam Integer startPage, @RequestParam Integer pageSize, @RequestParam Integer type_id, @RequestParam(required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Law> lawList = lawService.queryLaw(type_id, name);
        map.put("lawList", lawList);
        //总页数
        map.put("total", lawList.getPages());
        //记录总数
        map.put("count", lawList.getTotal());
        //当前第几页
        map.put("nowPage", lawList.getPageNum());
        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.LAW)
    @PostMapping("/insertLaw")////添加法律
    public Map<String, Object> insertLaw(@RequestParam Integer managerId,
                                         @RequestParam(required = false) Manager manager,
                                         @RequestParam(required = false) Integer lawtype,
                                         @RequestParam(required = false) Law law,
                                         @RequestParam MultipartFile law_wenjian) {
        String result = lawService.insertLaw(manager, lawtype, law, law_wenjian);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.LAW)
    @DeleteMapping("/deleteLaw/{law_id}/{managerId}")//////删除法律
    public Map<String, Object> deleteLaw(@PathVariable("managerId") Integer managerId, @PathVariable("law_id") Integer law_id) {
        String result = lawService.deleteLawAndLawType(law_id);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.LAW)
    @DeleteMapping("/deleteLawMany/{law_ids}/{managerId}")/////批量删除法律
    public Map<String, Object> deleteLawMany(@PathVariable("managerId") Integer managerId, @PathVariable("law_ids") Integer[] law_ids) {
        String result = lawService.deleteLawAndLawTypeMany(law_ids);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

    @Autowired
    LawMapper lawMapper;
    @Autowired
    LawtypeMapper lawtypeMapper;

    @GetMapping("/updateLawShow/{law_id}")
    public Map<String, Object> updateLawShow(@PathVariable("law_id") Integer law_id) {
        Map<String, Object> map = new HashMap<>();
        Law law = lawMapper.queryByLawId_yyq(law_id);
        Lawtype lawtype = lawtypeMapper.queryLawTypeById(law.getlType());
        if (lawtype.getlSheng() != null) {
            Sheng sheng = shengMapper.queryById_yyq(lawtype.getlSheng());
            map.put("shengNow", sheng);
        }
        if (lawtype.getlShi() != null) {
            Shi shi = shiMapper.queryShiById_yyq(lawtype.getlShi());
            map.put("shiNow", shi);
        }
        if (lawtype.getlRegion() != null) {
            Region qu = regionMapper.queryById_yyq(lawtype.getlRegion());
            map.put("quNow", qu);
        }
        if (lawtype.getlZhen() != null) {
            Zhen zhen = zhenMapper.queryByZhenId_yyq(lawtype.getlZhen());
            map.put("zhenNow", zhen);
        }
        map.put("law", law);
        map.put("lawtype", lawtype);
        return map;
    }


    ///////修改法律
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.LAW)
    @PutMapping("/updateLawSubmit")
    public Map<String, Object> updateLaw(@RequestParam Integer managerId, Lawtype lawtype, Law law, MultipartFile law_wenjian, @RequestParam Integer law_id) {
        String result = lawService.updateLaw(managerId, lawtype, law, law_wenjian, law_id);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }


    @GetMapping("/queryAllSheng")
    public Map<String, Object> queryAllSheng() {
        Map<String, Object> map = new HashMap<>();
        List<Sheng> shengList = shengMapper.queryAllSheng_yyq();
        map.put("shengList", shengList);
        return map;
    }

    @GetMapping("/queryAllShiByShengId/{shengId}")
    public Map<String, Object> queryAllShiByShengId(@PathVariable("shengId") Integer shengId) {
        Map<String, Object> map = new HashMap<>();
        List<Shi> shiList = shiMapper.queryShiByShengId_yyq(shengId);
        map.put("shiList", shiList);
        return map;
    }

    @GetMapping("/queryAllQuByShiId/{shiId}")
    public Map<String, Object> queryAllQuByShiId(@PathVariable("shiId") Integer shiId) {
        Map<String, Object> map = new HashMap<>();
        List<Region> quList = regionMapper.queryRegionByShiId_yyq(shiId);
        map.put("quList", quList);
        return map;
    }

    @GetMapping("/queryAllZhenByQuId/{quId}")
    public Map<String, Object> queryAllZhenByQuId(@PathVariable("quId") Integer quId) {
        Map<String, Object> map = new HashMap<>();
        List<Zhen> zhenList = zhenMapper.queryZhenByQuId_yyq(quId);
        map.put("zhenList", zhenList);
        return map;
    }

    @GetMapping("/queryAllCunByZhenId/{zhenId}")
    public Map<String, Object> queryAllCunByZhenId(@PathVariable("zhenId") Integer zhenId) {
        Map<String, Object> map = new HashMap<>();
        List<Village> cunList = villageMapper.queryVillageByZhenId_yyq(zhenId);
        map.put("cunList", cunList);
        return map;
    }


    @PostMapping("/queryLeaderLaw")
    public Map<String, Object> queryLeaderLaw(/*@RequestParam Integer law_type_id,*/@RequestParam(required = false) String zhen_name) {
        Map<String, Object> map = new HashMap<>();
        int zhen_id = 0;
        if (!zhen_name.equals("")) {
            zhen_id = lawMapper.Zhen_Id(zhen_name);
        }
        int lawNumber = lawMapper.lawNumber();
        List<Law> law1 = lawService.queryLeaderLaw(1, 0);
        List<Law> law2 = lawService.queryLeaderLaw(2, 0);
        List<Law> law3 = lawService.queryLeaderLaw(3, 0);
        List<Law> law4 = lawService.queryLeaderLaw(4, zhen_id);
        map.put("CountryLaw", law1);
        map.put("provinceLaw", law2);
        map.put("cityLaw", law3);
        map.put("townLaw", law4);
        map.put("lawNumber", lawNumber);
        return map;
    }

}
