package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.JiaoyvzixunListMapper;
import com.hj.jdpth.repository.JisoyvzixunSelectMapper;
import com.hj.jdpth.repository.ZuzhihuodongListMapper;
import com.hj.jdpth.service.JiaoyvzixunListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class JiaoyvzixunListServiceImpl implements JiaoyvzixunListService {

    @Autowired
    JiaoyvzixunListMapper jiaoyvzixunListMapper;
    @Autowired
    ZuzhihuodongListMapper zuzhihuodongListMapper;
    @Autowired
    JisoyvzixunSelectMapper jisoyvzixunSelectMapper;

    @Override
    public Map<String, Object> GetInfoAllFirst(Integer quId, Integer zhenId, Integer cunId, Integer startpage, Integer pageSize, Integer adminId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Region region = zuzhihuodongListMapper.GetRegion(quId);
            Zhen zhen = zuzhihuodongListMapper.GetZhen(zhenId);
            Village village = zuzhihuodongListMapper.GetVillage(cunId);
            PageHelper.startPage(startpage, pageSize);
            Page<Jiaoyv> GetInfo = jiaoyvzixunListMapper.GetInfoAllFirst(quId, zhenId, cunId, adminId);
            if (adminId == 1 || adminId == 2 || adminId == 3) {
                map.put("region", region);
                map.put("zhen", "");
                map.put("village", "");
            } else if (adminId == 4) {
                map.put("region", region);
                map.put("zhen", zhen);
                map.put("village", "");
            } else if (adminId == 5) {
                map.put("region", region);
                map.put("zhen", zhen);
                map.put("village", village);
            }
            map.put("nowPage", GetInfo.getPageNum());//当前页
            map.put("tatol", GetInfo.getPages());//页数
            map.put("count", GetInfo.getTotal());//条数
            map.put("data", GetInfo);//数据
            map.put("state", "success");//状态
            return map;
        } catch (Exception e) {
            map.put("data", null);
            map.put("state", "error");
            return map;
        }
    }


    @Override
    public Map<String, Object> GetInfoSelect(Integer quId, Integer zhenId, Integer cunId, Integer startpage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startpage, pageSize);
        Page<Jiaoyv> GetInfo = jisoyvzixunSelectMapper.GetInfoSelect(quId, zhenId, cunId);
        map.put("nowPage", GetInfo.getPageNum());//当前页
        map.put("tatol", GetInfo.getPages());//页数
        map.put("count", GetInfo.getTotal());//条数
        map.put("data", GetInfo);//数据
        map.put("state", "success");//状态
        return map;
    }

    @Override
    public Map<String, Object> DeleteOne(Integer jiaoyvId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Jiaoyv jiaoyv = jiaoyvzixunListMapper.GetInfoOne(jiaoyvId);
            File file = new File(jiaoyv.getJyZhenshilujing());
            file.delete();
            boolean B = jiaoyvzixunListMapper.DeleteInfoOne(jiaoyvId);
            map.put("data", B);
            map.put("stste", "success");
        } catch (Exception e) {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> DeleteMany(String jiaoyvIds) {
        String[] ids = jiaoyvIds.split(",");
        Map<String, Object> map = new HashMap<>();
        for (String id : ids) {
            Jiaoyv jiaoyv = jiaoyvzixunListMapper.GetInfoOne(Integer.parseInt(id));
            File file = new File(jiaoyv.getJyZhenshilujing());
            if (file.exists()) {
                file.delete();
            } else {
                file = null;
            }
            boolean B = jiaoyvzixunListMapper.DeleteInfoOne(Integer.parseInt(id));
            if (B) {
                if (map.get("success") != null) {
                    map.put("success", id + "," + map.get("success"));
                } else {
                    map.put("success", id);
                }
            } else {
                if (map.get("error") != null) {
                    map.put("error", id + "," + map.get("error"));
                } else {
                    map.put("error", id);
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> UploadFile(Jiaoyv jiaoyv) {
        Map<String, Object> map = new HashMap<>();
        Integer B = jisoyvzixunSelectMapper.AddInfo(jiaoyv);
        if (B != 0) {
            map.put("data", B);
            map.put("state", "success");
        } else {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Jiaoyv> GetInfoOne(Integer jiaoyvId) {
        Map<String, Jiaoyv> map = new HashMap<>();
        Jiaoyv jiaoyv = jiaoyvzixunListMapper.GetInfoOne(jiaoyvId);
        if (jiaoyv != null) {
            map.put("data", jiaoyv);
        } else {
            map.put("data", null);
        }
        return map;
    }
}
