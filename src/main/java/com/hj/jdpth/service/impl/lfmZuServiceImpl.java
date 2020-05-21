package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Zu;
import com.hj.jdpth.repository.ZuMapper;
import com.hj.jdpth.service.ZuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class lfmZuServiceImpl implements ZuService {
    @Autowired
    ZuMapper zuMapper;

    @Override
    public Map<String, Object> zuSearch(int regionId, int zhenId, int villageId, int mType, Integer startPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Map<String, Object>> zu = new Page<>();
        if (mType == 2) {
            System.out.println("qu");
            try {
                zu = zuMapper.zuSearch_lfm(regionId, zhenId, villageId);
                map.put("status", "success");
                map.put("data", zu);
            } catch (Exception e) {
                map.put("status", "error");
                map.put("data", null);
            }
        }
        if (mType == 3) {
            System.out.println("zhineng");
            try {
                zu = zuMapper.zuSearch_lfm(regionId, zhenId, villageId);
                map.put("status", "success");
                map.put("data", zu);
            } catch (Exception e) {
                map.put("status", "error");
                map.put("data", null);
            }
        }
        if (mType == 4) {
            System.out.println("zhen");
            try {
                zu = zuMapper.zuSearch_lfm(regionId, zhenId, villageId);
                map.put("status", "success");
                map.put("data", zu);
            } catch (Exception e) {
                map.put("status", "error");
                map.put("data", null);
            }
        }
        if (mType == 5) {
            System.out.println("cun");
            try {
                zu = zuMapper.zuSearch_lfm(regionId, zhenId, villageId);
                map.put("status", "success");
                map.put("data", zu);
            } catch (Exception e) {
                map.put("status", "error");
                map.put("data", null);
            }
        }
        map.put("data", zu);
        //总页数
        map.put("total", zu.getPages());
        //记录总数
        map.put("count", zu.getTotal());
        //当前第几页
        map.put("nowPage", zu.getPageNum());
        return map;
    }

    @Override
    public Map<String, Object> deleteOne(Integer id) {
        Map<String, Object> map = new HashMap<>();
        boolean b = (boolean) zuMapper.ZuDelete(id);
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
    public Map<String, Object> deleteMany(String ZuId) {
        String[] ids = ZuId.split(",");
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        for (String id : ids) {

            boolean B = (boolean) zuMapper.ZuDelete(Integer.parseInt(id));
            if (B) {
                map1.put("success", id + "," + map1.get("success"));
            } else {
                map1.put("error", id + "," + map1.get("success"));
            }
        }
        return map1;
    }

    @Override
    public Map<String, Object> zuInsert(Zu zu) {
        Map<String, Object> map = new HashMap<>();
        Boolean b = zuMapper.ZuInsert(zu);
        if (b) {
            map.put("data", b);
            map.put("stste", "success");
        } else {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> ZuUpdate(Zu zu) {
        Map<String, Object> map = new HashMap<>();
        Boolean b = zuMapper.ZuUpdate(zu);
        if (b) {
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
        if (zuMapper.Check(xvhao) != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Map<String, Object> Add(Zu zu) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean B = zuMapper.ZuInsert(zu);
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
