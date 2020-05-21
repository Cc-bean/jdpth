package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Dangyuanzuzhihuodong;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Zhen;
import com.hj.jdpth.repository.ZuzhihuodongListMapper;
import com.hj.jdpth.repository.ZuzhihuodongSelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ZuzhihuodongAddControl {

    @Autowired
    ZuzhihuodongSelectMapper zuzhihuodongSelectMapper;
    @Autowired
    ZuzhihuodongListMapper zuzhihuodongListMapper;

    @PostMapping("h/ZuzhihuodongGetByTime")//新添加的东西，得到党员组织活动在该村或者该镇下镇名字和活动数目
    public Map<String, Object> zuzhihuodongListFirst(@RequestParam Integer mRegion,//区
                                                     @RequestParam(required = false) Integer mZhenid,//镇
                                                     @RequestParam(required = false) Integer mVillageid,//村
                                                     @RequestParam String startTime,//开始时间
                                                     @RequestParam String stopTime //结束时间

    ) {
        Map<String, Object> map = new HashMap<>();
        //Map<String, Object> map2 = null;
        //List<Object> list = null;
        List<Object> list = new ArrayList<>();
        if (mVillageid != null) {

            List<Village> list_cun = zuzhihuodongSelectMapper.GetVillage(mVillageid);
            List<HashMap> lists = zuzhihuodongSelectMapper.GetInfoSelectByTime(mRegion, mZhenid, mVillageid, startTime, stopTime);
            list.add(list_cun.get(0).getvName());
            list.add(lists.size());
            list.add(lists);
        } else if (mZhenid != null) {
            List<Village> list_village = zuzhihuodongListMapper.list_village(mZhenid);
            for (int i = 0; i < list_village.size(); i++) {
                List<HashMap> lists = zuzhihuodongSelectMapper.GetInfoSelectByTime(mRegion, mZhenid, list_village.get(i).getVillageId(), startTime, stopTime);
                list.add(list_village.get(i).getvName());
                list.add(lists.size());
                list.add(lists);
            }
        } else if (mRegion != null) {
            List<Zhen> list_zhen = zuzhihuodongListMapper.list_zhen(mRegion);
            for (int i = 0; i < list_zhen.size(); i++) {
                List<HashMap> lists = zuzhihuodongSelectMapper.GetInfoSelectByTime(mRegion, list_zhen.get(i).getZhenId(), mVillageid, startTime, stopTime);
                list.add(list_zhen.get(i).getzName());
                list.add(lists.size());
                list.add(lists);
            }
        }
        map.put("data", list);
        return map;
    }

    @PostMapping("h/ZuzhihuodongGetAAll")//新添加的东西,得到所有党员组织活动，在一个村中;必须有村id
    public Map<String, Object> zuzhihuodongVillageList(@RequestParam Integer mRegion,//区
                                                       @RequestParam Integer mZhenid,//镇
                                                       @RequestParam Integer mVillageid,//村
                                                       @RequestParam String startTime,//开始时间
                                                       @RequestParam String stopTime //结束时间

    ) {
        Map<String, Object> map = new HashMap<>();
        if (mVillageid != null) {
            List<HashMap> lists = zuzhihuodongSelectMapper.GetInfoSelectByTime(mRegion, mZhenid, mVillageid, startTime, stopTime);
            map.put("data", lists);
        } else {
            map.put("data", "村id不能为空");
        }
        return map;
    }
}
