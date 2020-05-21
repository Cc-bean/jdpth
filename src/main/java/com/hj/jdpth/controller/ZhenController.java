package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Zhen;
import com.hj.jdpth.repository.ZhenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class ZhenController {
    @Autowired
    ZhenMapper zhenMapper;

    //区查镇名和镇id
    @ResponseBody
    @GetMapping(value = "/quaryZhenName/{Z_region}")
    public Map<String, Object> quaryZhenName_gsh(
            @PathVariable(value = "Z_region") Integer Z_region) {
        Map<String, Object> result = new HashMap<>();
        List<Zhen> zhens = zhenMapper.quaryByRegion_gsh(Z_region);
        result.put("zhen", zhens);
        return result;
    }

    //村组反查镇区
    @PostMapping("/h/inSelect")
    public ResponseEntity<Map<String, Object>> queryIn(@RequestParam(value = "villageId", required = false) Integer villageId,
                                                       @RequestParam(value = "zuId", required = false) Integer zuId
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<HashMap> list = zhenMapper.queryIn(villageId, zuId);
            if (list.size() == 0) {
                return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
            }
            result.put("data", list);
        } catch (Exception e) {
            result.put("data", "请确认参数是否正确");
            return new ResponseEntity<>(result, HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
