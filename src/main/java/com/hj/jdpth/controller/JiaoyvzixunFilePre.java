package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Jiaoyv;
import com.hj.jdpth.service.JiaoyvzixunListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class JiaoyvzixunFilePre {

    @Autowired
    JiaoyvzixunListService jiaoyvzixunListService;

    @GetMapping(value = "h/JiaoyvzixunFilePre/{jiaoyvId}")
    public Map<String, Jiaoyv> jiaoyvzixunFilePre(@PathVariable(value = "jiaoyvId") Integer jiaoyvId) {
        Map<String, Jiaoyv> map = new HashMap<>();
        map = jiaoyvzixunListService.GetInfoOne(jiaoyvId);
        if (map.get("data") != null) {
            if (map.get("data").getJyFangwen() == null) {
                map.get("data").setJyFangwen(1);
            } else {
                map.get("data").setJyFangwen(map.get("data").getJyFangwen() + 1);
            }
        }
        return map;

    }
}
