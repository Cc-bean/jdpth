package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Jiaoyv;
import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.repository.JiaoyvzixunListMapper;
import com.hj.jdpth.repository.JisoyvzixunSelectMapper;
import com.hj.jdpth.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class FileUploadController {
    @Autowired(required = false)
    FileUploadService fileUploadService;

    @Autowired
    JisoyvzixunSelectMapper jiaoyvzixunSelectMapper;

    @Autowired
    JiaoyvzixunListMapper jiaoyvzixunListMapper;

    @PostMapping(value = "h/FileUploadDoc")
    public Map<String, Object> FileUploadDoc(
            @RequestParam Integer managerId,
            @RequestParam(value = "image", required = false) MultipartFile image,
            HttpServletRequest request
    ) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        map = fileUploadService.fileUpload(image, request);
        Jiaoyv jiaoyv = new Jiaoyv();
        jiaoyv.setJyLujing(map.get("path").toString());
        jiaoyv.setJyNmaeoffile(map.get("name").toString());
        Manager manager = jiaoyvzixunListMapper.GetInfoManager(managerId);

        jiaoyv.setJyPeople(manager.getmName());
        jiaoyv.setJyQu(manager.getmRegion());
        jiaoyv.setJyVillage(manager.getmVillageid());
        jiaoyv.setJyZhen(manager.getmZhenid());
        jiaoyv.setJyZhenshilujing(map.get("path").toString());
        jiaoyv.setJyTime(new Date());
        try {
            jiaoyvzixunSelectMapper.AddInfo(jiaoyv);
        } catch (Exception e) {
            map.put("eStack", e.getMessage());
        }
        return map;
    }
}
