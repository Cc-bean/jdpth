package com.hj.jdpth.service.impl;

import com.hj.jdpth.domain.Feedback;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Zhen;
import com.hj.jdpth.repository.FeedbackMapper;
import com.hj.jdpth.repository.VillageMapper;
import com.hj.jdpth.repository.ZhenMapper;
import com.hj.jdpth.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedServiceImpl implements FeedService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    ZhenMapper zhenMapper;

    @Override
    public Map<String, Object> leaderUse(Integer zhenId, String time) {
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        Map<String, Object> a1 = new HashMap<>();
        List<HashMap> list = feedbackMapper.queryLeader(zhenId, time1, time2);
        List<HashMap> list2 = feedbackMapper.ycl(zhenId, time1, time2);
        List<HashMap> list3 = feedbackMapper.wcl(zhenId, time1, time2);
        Zhen zhen = zhenMapper.FindById_lfm(zhenId);
        if (list.size() != 0) {
            a1.put("total", list.size());
        } else {
            a1.put("total", 0);
        }
        if (list2.size() != 0) {
            a1.put("ycl", list2.size());
        } else {
            a1.put("ycl", 0);
        }
        if (list3.size() != 0) {
            a1.put("wcl", list3.size());
        } else {
            a1.put("wcl", 0);
        }
        a1.put("name", zhen.getzName());
        return a1;
    }

    @Override
    public Map<String, Object> zhenzhexian(Integer villageId, String time) {
        Village village = villageMapper.FindById_lfm(villageId);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> a = new HashMap<>();
        List b = new ArrayList();
        List<HashMap> list3 = feedbackMapper.vtotal(villageId);
        String shijian;
        String tt;
        Integer t = Integer.parseInt(time);
        if (list3 != null) {
            for (int i = 1; i <= 12; i++) {
                b = new ArrayList();
                for (int j = 1; j <= 12; j++) {
                    a = new HashMap<>();
                    if (j < 10) {
                        tt = "0" + String.valueOf(j);
                    } else {
                        tt = String.valueOf(j);
                    }
                    shijian = time + "-" + tt;
                    List<Feedback> fTime = feedbackMapper.findbydatetotalV(villageId, shijian);
                    a.put("time", j + "月");
                    a.put("total", fTime.size());
                    b.add(a);
                }
                result.put("zongfankui", b);
            }
        } else {
            for (int i = 1; i <= 12; i++) {
                b = new ArrayList();
                for (int j = 1; j <= 12; j++) {
                    a = new HashMap<>();
                    if (j < 10) {
                        tt = "0" + String.valueOf(j);
                    } else {
                        tt = String.valueOf(j);
                    }
                    a.put("time", j + "月");
                    a.put("total", 0);
                    b.add(a);
                }
                result.put("zongfankui", b);
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> quzhexian(Integer zhenId, String time) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> a = new HashMap<>();
        List b = new ArrayList();
        List<HashMap> list = feedbackMapper.queryLeader2(zhenId);
        String shijian;
        String tt;
        if (list.size() != 0) {
            b = new ArrayList();
            for (int j = 1; j <= 12; j++) {
                a = new HashMap<>();
                if (j < 10) {
                    tt = "0" + String.valueOf(j);
                } else {
                    tt = String.valueOf(j);
                }
                shijian = time + "-" + tt;
                List<Feedback> fTime = feedbackMapper.findbydate(zhenId, shijian);
                a.put("time", j + "月");
                a.put("total", fTime.size());
                b.add(a);
            }
            result.put("data", b);
        } else {
            for (int i = 1; i <= 12; i++) {
                b = new ArrayList();
                for (int j = 1; j <= 12; j++) {
                    a = new HashMap<>();
                    if (j < 10) {
                        tt = "0" + String.valueOf(j);
                    } else {
                        tt = String.valueOf(j);
                    }
                    a.put("time", j + "月");
                    a.put("total", 0);
                    b.add(a);
                }
                result.put("data", b);
            }
        }
        return result;
    }
}
