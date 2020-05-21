package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.repository.DepartmentMapper;
import com.hj.jdpth.repository.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class ManagerController {

    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @PostMapping("/h/managers")         //分级查询管理员
    public ResponseEntity<Map<String, Object>> queryAllMs(@RequestParam Integer startPage
            , @RequestParam(required = false) String mAccountnumber
            , @RequestParam Integer pagesize
            , @RequestParam(required = false) Integer zhenId
            , @RequestParam(required = false) Integer villageId) {
        Map<String, Object> map = new HashMap<>();
        try {
            PageHelper.startPage(startPage, pagesize);
            Page<HashMap> hashMaps = managerMapper.queryAllManager(zhenId, villageId, mAccountnumber);
            if (hashMaps.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                map.put("data", hashMaps);
                map.put("count", hashMaps.getTotal());
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        } catch (Exception e) {
            map.put("data", "error");
            return new ResponseEntity<>(map, HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @PostMapping(value = "/h/manager", consumes = "application/json")    //添加管理员
    @ResponseBody
    public Map<String, Object> insertManager(@RequestBody Manager manager) {
        Map<String, Object> map = new HashMap<>();
        try {
            managerMapper.insertManager(manager);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("state", "error");
        }
        return map;
    }

    @GetMapping(value = "/h/departments")   //调取所有职能部门
    @ResponseBody
    public Map<String, Object> queryAllDp() {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("state", "success");
            map.put("data", departmentMapper.queryAllDp());
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", "无");
        }
        return map;
    }


}
