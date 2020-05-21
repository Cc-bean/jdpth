package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.ZuzhihuodongListMapper;
import com.hj.jdpth.repository.ZuzhihuodongSelectMapper;
import com.hj.jdpth.service.ZuzhihuodongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZuzhihuodongListServiceImpl implements ZuzhihuodongListService {

    @Autowired
    ZuzhihuodongListMapper zuzhihuodongListMapper;

    @Autowired
    ZuzhihuodongSelectMapper zuzhihuodongSelectMapper;

    @Override
    public Map<String, Object> GetinfoFirstAll(Integer quId, Integer zhenId, Integer cunId, Integer startpage, Integer pageSize, Integer adminId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Region region = zuzhihuodongListMapper.GetRegion(quId);
            Zhen zhen = zuzhihuodongListMapper.GetZhen(zhenId);
            Village village = zuzhihuodongListMapper.GetVillage(cunId);
            PageHelper.startPage(startpage, pageSize);
            Page<Dangyuanzuzhihuodong> GetInfo = zuzhihuodongListMapper.GentInfo(quId);
            switch (adminId) {
                case 1:
                    map.put("region", region);
                    map.put("zhen", "");
                    map.put("village", "");
                    break;
                case 2:
                    map.put("region", region);
                    map.put("zhen", "");
                    map.put("village", "");
                    break;
                case 3:
                    map.put("region", region);
                    map.put("zhen", "");
                    map.put("village", "");
                    break;
                case 4:
                    PageHelper.startPage(startpage, pageSize);
                    GetInfo = zuzhihuodongListMapper.GetInfoByZhen(zhenId, quId);
                    map.put("region", region);
                    map.put("zhen", zhen);
                    map.put("village", "");
                    break;
                case 5:
                    PageHelper.startPage(startpage, pageSize);
                    GetInfo = zuzhihuodongListMapper.GetInfoByCun(cunId);
                    map.put("region", region);
                    map.put("zhen", zhen);
                    map.put("village", village);
                    break;
                default:
                    map.put("state", "error");
                    return map;

            }
            if (GetInfo != null) {
                map.put("nowPage", GetInfo.getPageNum());//当前页
                map.put("tatol", GetInfo.getPages());//页数
                map.put("count", GetInfo.getTotal());//条数
                map.put("data", GetInfo);//数据
                map.put("state", "success");//状态
            } else {
                map.put("state", "no Content");
            }
            return map;
        } catch (Exception e) {
            map.put("data", null);
            map.put("state", "error");
            e.printStackTrace();
            return map;
        }
    }

    @Override
    public Map<String, Object> GetinfoSelectAll(Integer quId, Integer zhenId, Integer cunId, Integer typeId, Integer startpage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startpage, pageSize);
        Page<Dangyuanzuzhihuodong> GetInfo = zuzhihuodongSelectMapper.GetInfoSelectAll(quId, zhenId, cunId, typeId);
        map.put("nowPage", GetInfo.getPageNum());//当前页
        map.put("total", GetInfo.getPages());//页数
        map.put("count", GetInfo.getTotal());//条数
        map.put("data", GetInfo);//数据
        map.put("state", "success");//状态

        return map;
    }

    @Override
    public Map<String, Dangyuanzuzhihuodong> GetinfoOne(Integer zuzhihuodongId) {
        Map<String, Dangyuanzuzhihuodong> map = new HashMap<>();
        try {
            Dangyuanzuzhihuodong dangyuanzuzhihuodong = zuzhihuodongListMapper.GetInfoById(zuzhihuodongId);
            map.put("data", dangyuanzuzhihuodong);
        } catch (Exception e) {
            map.put("data", null);
        }
        return map;
    }

    @Override
    public Map<String, Object> DeleteOne(Integer zuzhihuodongId) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean B = zuzhihuodongListMapper.DeleteInfoOne(zuzhihuodongId);
            map.put("data", B);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("data", "false");
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> DeleteMany(String zuzhihuodongIds) {
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println("impl   " + zuzhihuodongIds);
            boolean B = zuzhihuodongListMapper.DeleteInfoMany(zuzhihuodongIds);
            map.put("data", B);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> GetInfoAddTypeFirst(Integer quId, Integer zhenId, Integer cunId, Integer adminTypeId) {
        Map<String, Object> map = new HashMap<>();
        Region region = zuzhihuodongListMapper.GetRegion(quId);
        List<Zhen> list_zhen = zuzhihuodongListMapper.list_zhen(quId);
        Zhen zhen = zuzhihuodongListMapper.GetZhen(zhenId);
        List<Village> list_village = zuzhihuodongListMapper.list_village(zhenId);
        Village village = zuzhihuodongListMapper.GetVillage(cunId);
        if (adminTypeId == 1 || adminTypeId == 2 || adminTypeId == 3) {
            map.put("region", region);
            map.put("list_zhen", list_zhen);
            map.put("zhen", "");
            map.put("list_village", "");
            map.put("village", "");
        } else if (adminTypeId == 4) {
            map.put("region", region);
            map.put("list_zhen", "");
            map.put("zhen", zhen);
            map.put("list_village", list_village);
            map.put("village", "");
        } else if (adminTypeId == 5) {
            map.put("region", region);
            map.put("list_zhen", "");
            map.put("zhen", zhen);
            map.put("list_village", list_village);
            map.put("village", village);
        } else {
            map.put("region", "");
            map.put("list_zhen", "");
            map.put("zhen", "");
            map.put("list_village", "");
            map.put("village", "");
        }
        return map;
    }

    @Override
    public Map<String, Object> GetInfoAddType(Integer hdlxZhen, Integer hdlxVillage, String hdlxName) {
        Map<String, Object> map = new HashMap<>();
        Huodongleixing huodongleixing = new Huodongleixing();
        huodongleixing.setHdlxVillage(hdlxVillage);
        huodongleixing.setHdlxZhen(hdlxZhen);
        huodongleixing.setHdlxQu(1349);
        huodongleixing.setHdlxName(hdlxName);
        try {
            int B = zuzhihuodongListMapper.AddInfoType(huodongleixing);
            if (B != 0) {
                map.put("state", "success");
                map.put("data", B);
            }
        } catch (Exception e) {
            map.put("state", "error");
            map.put("data", null);
        }
        return map;
    }

    @Override
    public Map<String, Object> GetInfoType(Integer quId, Integer zhenId, Integer cunId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("qu", zuzhihuodongListMapper.GetInfoTypeQu(quId));
            map.put("zhen", zuzhihuodongListMapper.GetInfoTypeZhen(zhenId));
            map.put("cun", zuzhihuodongListMapper.GetInfoTypeVillage(cunId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> GetInfoAddInfo(Dangyuanzuzhihuodong dangyuanzuzhihuodong) {
        Map<String, Object> map = new HashMap<>();
        Integer B = zuzhihuodongListMapper.AddInfo(dangyuanzuzhihuodong);
        map.put("data", B);
        map.put("state", "success");
        return map;
    }

    @Override
    public Map<String, Object> GetInfoType(Integer villageId) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Huodongleixing> list = zuzhihuodongListMapper.GetInfoTypeVillage(villageId);
            map.put("data", list);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> UpdateInfo(Dangyuanzuzhihuodong dangyuanzuzhihuodong) {
        Map<String, Object> map = new HashMap<>();
        try {
            Integer B = zuzhihuodongListMapper.UpdateInfo(dangyuanzuzhihuodong);
            map.put("data", B);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public boolean GetInfoByXvhao(String dyzzhdXvhao) {
        if (zuzhihuodongListMapper.GetInfoByXvhao(dyzzhdXvhao) != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int GetTypeId(String TypeName, Integer VillageId) {
        Integer integer = zuzhihuodongListMapper.GetTypeId(VillageId, TypeName);
        if (integer != null) {
            return integer;
        } else {
            return 0;
        }
    }


}
