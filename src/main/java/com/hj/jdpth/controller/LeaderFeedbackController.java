package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Zhen;
import com.hj.jdpth.repository.FeedbackMapper;
import com.hj.jdpth.repository.ZhenMapper;
import com.hj.jdpth.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class LeaderFeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    FeedbackMapper feedbackMapper;
    @Autowired
    ZhenMapper zhenMapper;

    @PostMapping(value = "/qulxbintu")
    public Map<String, Object> qulxbintu(@RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = feedbackService.qulxbintu(time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/ququanfankui")
    public Map<String, Object> aaa(@RequestParam(required = false) String time, @RequestParam(required = false) Integer zhuangtai, @RequestParam(required = false) Integer startPage, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer zhenId) {
        PageHelper.startPage(startPage, pageSize);
        Page<HashMap> leaderxq = new Page<>();
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        Map<String, Object> map = new HashMap<>();
        if (zhuangtai == 1) {
            leaderxq = feedbackMapper.leaderxqycl(time1, time2, zhenId);
            map.put("data", leaderxq);
        }
        if (zhuangtai == 0) {
            leaderxq = feedbackMapper.leaderxqwcl(time1, time2, zhenId);
            map.put("data", leaderxq);
        }
        if (zhuangtai == 2) {
            leaderxq = feedbackMapper.leaderxqall(time1, time2, zhenId);
            map.put("data", leaderxq);
        }
        //总页数
        map.put("total", leaderxq.getPages());
        //记录总数
        map.put("count", leaderxq.getTotal());
        //当前第几页
        map.put("nowPage", leaderxq.getPageNum());
        return map;
    }

    //2019-11-27
    @PostMapping(value = "/zhenfankuixq")
    public Map<String, Object> zhenfankuixq(@RequestParam(required = false) String zhenname, @RequestParam(required = false) String time, @RequestParam(required = false) Integer zhuangtai, @RequestParam(required = false) Integer startPage, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer villageId) {
        Zhen zhen = zhenMapper.quaryByname(zhenname);
        PageHelper.startPage(startPage, pageSize);
        Page<HashMap> leaderxq = new Page<>();
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        Map<String, Object> map = new HashMap<>();
        if (zhuangtai == 1) {
            leaderxq = feedbackMapper.zhenleaderxqycl(time1, time2, zhen.getZhenId(), villageId);
            map.put("data", leaderxq);
        }
        if (zhuangtai == 0) {
            leaderxq = feedbackMapper.zhenleaderxqwcl(time1, time2, zhen.getZhenId(), villageId);
            map.put("data", leaderxq);
        }
        if (zhuangtai == 2) {
            leaderxq = feedbackMapper.zhenleaderxqall(time1, time2, zhen.getZhenId(), villageId);
            map.put("data", leaderxq);
        }
        //总页数
        map.put("total", leaderxq.getPages());
        //记录总数
        map.put("count", leaderxq.getTotal());
        //当前第几页
        map.put("nowPage", leaderxq.getPageNum());
        return map;
    }

    //2019-12-13
    @PostMapping(value = "/findbylx")
    public Map<String, Object> findbylx(@RequestParam(required = false) String time, @RequestParam(required = false) Integer zhuangtai, @RequestParam(required = false) Integer startPage, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer zhenId, @RequestParam(required = false) Integer bkey) {
        PageHelper.startPage(startPage, pageSize);
        Page<HashMap> leaderxq = new Page<>();
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        Map<String, Object> map = new HashMap<>();
        if (zhuangtai == 1) {
            leaderxq = feedbackMapper.findbylxycl(time1, time2, zhenId, bkey);
            map.put("data", leaderxq);
        }
        if (zhuangtai == 0) {
            leaderxq = feedbackMapper.findbylxwcl(time1, time2, zhenId, bkey);
            map.put("data", leaderxq);
        }
        if (zhuangtai == 2) {
            leaderxq = feedbackMapper.findbylxall(time1, time2, zhenId, bkey);
            map.put("data", leaderxq);
        }
        //总页数
        map.put("total", leaderxq.getPages());
        //记录总数
        map.put("count", leaderxq.getTotal());
        //当前第几页
        map.put("nowPage", leaderxq.getPageNum());
        return map;
    }
}
