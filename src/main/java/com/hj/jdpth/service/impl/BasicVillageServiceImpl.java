package com.hj.jdpth.service.impl;

import com.hj.jdpth.domain.Villagesurvey;
import com.hj.jdpth.repository.BasicVillageMapper;
import com.hj.jdpth.service.BasicVillageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hj.jdpth.domain.Village;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasicVillageServiceImpl implements BasicVillageService {
    @Autowired
    BasicVillageMapper basicVillageMapper;

    @Override
    public Map<String, Object> DeleteOne(Integer id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Village village = basicVillageMapper.FindById(id);
            if (village.getvImagepath1() != null) {
                File file = new File(village.getvImagepath1());
                file.delete();
            }
            if (village.getvImagepath2() != null) {
                File file = new File(village.getvImagepath2());
                file.delete();
            }
            if (village.getvImagepath3() != null) {
                File file = new File(village.getvImagepath3());
                file.delete();
            }
            Villagesurvey villagesurvey = basicVillageMapper.FindByVid(id);
            if (villagesurvey != null) {
                boolean b = basicVillageMapper.DeleteVS(village.getVillageId());
                if (b == true) {
                    boolean flag = basicVillageMapper.DeleteBasic(id);
                    if (flag == true) {
                        map.put("state", "success");
                        map.put("data", "删除成功");
                    } else {
                        map.put("state", "success");
                        map.put("data", "删除失败");
                    }
                }
            } else {
                boolean flag = basicVillageMapper.DeleteBasic(id);
                if (flag == true) {
                    map.put("state", "success");
                    map.put("data", "删除成功");
                } else {
                    map.put("state", "success");
                    map.put("data", "删除失败");
                }
            }
        } catch (Exception e) {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> DeleteMany(String Id) {
        String[] ids = Id.split(",");

        Map<String, Object> map = new HashMap<>();
        for (String id : ids) {
            Village village = basicVillageMapper.FindById(Integer.parseInt(id));
            if (village.getvImagepath1() != null) {
                File file = new File(village.getvImagepath1());
                file.delete();
            }
            if (village.getvImagepath2() != null) {
                File file = new File(village.getvImagepath2());
                file.delete();
            }
            if (village.getvImagepath3() != null) {
                File file = new File(village.getvImagepath3());
                file.delete();
            }
            Villagesurvey villagesurvey = basicVillageMapper.FindByVid(Integer.parseInt(id));
            if (villagesurvey != null) {
                boolean b = basicVillageMapper.DeleteVS(village.getVillageId());
                if (b == true) {
                    boolean B = basicVillageMapper.DeleteBasic(Integer.parseInt(id));
                    if (B) {
                        map.put("success", id + "," +
                                "" + map.get("success"));
                    } else {
                        map.put("error", id + "," + map.get("success"));
                    }
                }
            } else {
                boolean B = basicVillageMapper.DeleteBasic(Integer.parseInt(id));
                if (B) {
                    map.put("success", id + "," +
                            "" + map.get("success"));
                } else {
                    map.put("error", id + "," + map.get("success"));
                }
            }
        }
        return map;
    }
}
