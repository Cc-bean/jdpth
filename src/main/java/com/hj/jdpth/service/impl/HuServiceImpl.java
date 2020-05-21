package com.hj.jdpth.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Hu;
import com.hj.jdpth.repository.HuMapper;
import com.hj.jdpth.service.HuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HuServiceImpl implements HuService {
    @Autowired
    HuMapper huMapper;

    @Override
    public Map<String, Object> DeleteOne(Integer HuId) {
        Map<String, Object> map = new HashMap<>();
        boolean b = (boolean) huMapper.HuDelete(HuId);
        if (b == true) {
            map.put("data", b);
            map.put("state", "success");
        } else {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> DeleteMany(String HuId) {
        String[] ids = HuId.split(",");
        Map<String, Object> map = new HashMap<>();
        for (String id : ids) {

            boolean B = (boolean) huMapper.HuDelete(Integer.parseInt(id));
            if (B) {
                map.put("success", id + "," + map.get("success"));
            } else {
                map.put("error", id + "," + map.get("success"));
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> huSearch(int regionId, int zhenId, int villageId, int zuId, int mType, Integer startPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Map<String, Object>> hu = new Page<>();
        try {
            hu = huMapper.HuSearch(regionId, zhenId, villageId, zuId);
            map.put("status", "success");
            map.put("data", hu);
        } catch (Exception e) {
            map.put("status", "error");
            map.put("data", null);
        }
        //总页数
        map.put("total", hu.getPages());
        //记录总数
        map.put("count", hu.getTotal());
        //当前第几页
        map.put("nowPage", hu.getPageNum());
        return map;
    }

    @Override
    public Map<String, Object> HuInsert(Hu hu) {
        Map<String, Object> map = new HashMap<>();
        Boolean b = huMapper.HuInsert(hu);
        if (b == true) {
            map.put("data", b);
            map.put("stste", "success");
        } else {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> HuUpdate(Hu hu) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("a");
        Boolean b = huMapper.HuUpdate(hu);
        System.out.println(b);
        if (b == true) {
            map.put("data", b);
            map.put("stste", "success");
        } else {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public boolean Check(String xvhao) {
        if (huMapper.Check(xvhao) != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Map<String, Object> Add(Hu hu) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean B = huMapper.HuInsert(hu);
            if (B != false) {
                map.put("state", "success");
                map.put("data", B);
            }
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }
}

