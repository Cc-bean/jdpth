package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.HukouqianyiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HukouqianyiServiceImpl implements HukouqianyiService {

    @Autowired
    HukouqianyiMapper hukouqianyiMapper;
    @Autowired
    ZuMapper zuMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    RegionMapper regionMapper;

    String path0 = "E:\\HJResourse\\Hukouqianyi\\";

    @Override
    public Map<String, Object> QianYiSearch(Integer mType, Integer qid, Integer zid, Integer vid, Integer qyType, String time, Integer startPage, Integer pageSize) {
        PageHelper.startPage(startPage, pageSize);
        Map<String, Object> map = new HashMap<>();
        Zhen zhen = null;
        Village village = null;
        if (mType == 2) {
            System.out.println("qu");
            Page<Hukouqianyi> hukouqianyiPage = hukouqianyiMapper.Search(qid, zid, vid, qyType, time);
            for (int i = 0; i < hukouqianyiPage.size(); i++) {
                village = villageMapper.FindById_lfm(hukouqianyiPage.get(i).getHkqyVillage());
                zhen = zhenMapper.FindById_lfm(village.getvZhenXiang());
                hukouqianyiPage.get(i).setVillage(village);
                hukouqianyiPage.get(i).setZhen(zhen);
            }
            map.put("data", hukouqianyiPage);
            //总页数
            map.put("total", hukouqianyiPage.getPages());
            //记录总数
            map.put("count", hukouqianyiPage.getTotal());
            //当前第几页
            map.put("nowPage", hukouqianyiPage.getPageNum());
        }
        if (mType == 3) {
            System.out.println("zhineng");
            Page<Hukouqianyi> hukouqianyiPage = hukouqianyiMapper.Search(qid, zid, vid, qyType, time);
            for (int i = 0; i < hukouqianyiPage.size(); i++) {
                village = villageMapper.FindById_lfm(hukouqianyiPage.get(i).getHkqyVillage());
                zhen = zhenMapper.FindById_lfm(village.getvZhenXiang());
                hukouqianyiPage.get(i).setVillage(village);
                hukouqianyiPage.get(i).setZhen(zhen);
            }
            map.put("data", hukouqianyiPage);
            //总页数
            map.put("total", hukouqianyiPage.getPages());
            //记录总数
            map.put("count", hukouqianyiPage.getTotal());
            //当前第几页
            map.put("nowPage", hukouqianyiPage.getPageNum());
        }
        if (mType == 4) {
            System.out.println("zhen");
            Page<Hukouqianyi> hukouqianyiPage = hukouqianyiMapper.Search(qid, zid, vid, qyType, time);
            for (int i = 0; i < hukouqianyiPage.size(); i++) {
                village = villageMapper.FindById_lfm(hukouqianyiPage.get(i).getHkqyVillage());
                zhen = zhenMapper.FindById_lfm(village.getvZhenXiang());
                hukouqianyiPage.get(i).setVillage(village);
                hukouqianyiPage.get(i).setZhen(zhen);
            }
            map.put("data", hukouqianyiPage);
            //总页数
            map.put("total", hukouqianyiPage.getPages());
            //记录总数
            map.put("count", hukouqianyiPage.getTotal());
            //当前第几页
            map.put("nowPage", hukouqianyiPage.getPageNum());
        }
        if (mType == 5) {
            System.out.println("cun");
            Page<Hukouqianyi> hukouqianyiPage = hukouqianyiMapper.Search(qid, zid, vid, qyType, time);
            for (int i = 0; i < hukouqianyiPage.size(); i++) {
                village = villageMapper.FindById_lfm(hukouqianyiPage.get(i).getHkqyVillage());
                zhen = zhenMapper.FindById_lfm(village.getvZhenXiang());
                hukouqianyiPage.get(i).setVillage(village);
                hukouqianyiPage.get(i).setZhen(zhen);
            }
            map.put("data", hukouqianyiPage);
            //总页数
            map.put("total", hukouqianyiPage.getPages());
            //记录总数
            map.put("count", hukouqianyiPage.getTotal());
            //当前第几页
            map.put("nowPage", hukouqianyiPage.getPageNum());
        }
        return map;
    }

    @Override
    public Map<String, Object> QianYiXQ(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Zu zu = new Zu();
        Hukouqianyi hukouqianyi = hukouqianyiMapper.FindById(id);
        if (hukouqianyi.getHkqyZu() != null) {
            zu = zuMapper.FindById_lfm(hukouqianyi.getHkqyZu());
            map.put("Zuname", zu.getzName());
        } else {
            map.put("Zuname", "暂未录入");
        }
        Village village = villageMapper.FindById_lfm(hukouqianyi.getHkqyVillage());
        Zhen zhen = zhenMapper.FindById_lfm(village.getvZhenXiang());
        Region region = regionMapper.FindById(zhen.getzRegion());
        map.put("data", hukouqianyi);
        map.put("Rname", region.getrName());
        map.put("Zhenname", zhen.getzName());
        map.put("Vname", village.getvName());
        return map;
    }

    @Override
    public Map<String, Object> DeleteOne(Integer id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Hukouqianyi hukouqianyi = hukouqianyiMapper.FindById(id);
            if (hukouqianyi.getHkqyZhengming() != null) {
                String[] result = null;
                String str = hukouqianyi.getHkqyZhengming();
                if (str != null) {
                    result = str.split(",");
                    for (int i = 0; i < result.length; i++) {
                        File file = new File(path0 + result[i]);
                        file.delete();
                    }
                }
//                File file = new File(path0+hukouqianyi.getHkqyZhengming());
//                file.delete();
            }
            boolean flag = hukouqianyiMapper.DeleteHKQY(id);
            if (flag == true) {
                map.put("stste", "success");
                map.put("data", "删除成功");
            } else {
                map.put("stste", "success");
                map.put("data", "删除失败");
            }
        } catch (Exception e) {
            map.put("data", null);
            map.put("state", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> DeleteMany(String hkqyId) {
        String[] ids = hkqyId.split(",");
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        for (String id : ids) {
            Hukouqianyi hukouqianyi = hukouqianyiMapper.FindById(Integer.parseInt(id));
            if (hukouqianyi.getHkqyZhengming() != null) {
                String[] result = null;
                String str = hukouqianyi.getHkqyZhengming();
                if (str != null) {
                    result = str.split(",");
                    for (int i = 0; i < result.length; i++) {
                        File file = new File(path0 + result[i]);
                        file.delete();
                    }
                }
//                File file = new File(hukouqianyi.getHkqyZhengming());
//                file.delete();
            }
            boolean B = hukouqianyiMapper.DeleteHKQY(Integer.parseInt(id));
            if (B) {
                map1.put("success", id + "," +
                        "" + map1.get("success"));
            } else {
                map1.put("error", id + "," + map1.get("success"));
            }
        }
        return map1;
    }

    @Override
    public Map<String, Object> InsertHkqy(Hukouqianyi hukouqianyi, MultipartFile[] files, String hkqyTime) {
        Map<String, Object> map = new HashMap<>();
        long s = System.currentTimeMillis();
        String newName = null;
        String p = null;
        String p2 = null;
        String p3 = null;
        if (files.length != 0) {
            String path = path0;
            for (int i = 0; i < files.length; i++) {
                String realName = files[i].getOriginalFilename();
                newName = s + realName;
                p = newName + ",";
                File newFile = new File(path + newName);
                p2 += p;
                try {
                    files[i].transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            p3 = p2.substring(4);
        }
        try {
            hukouqianyi.setHkqyTime(Date.valueOf(hkqyTime));
            hukouqianyi.setHkqyZhengming(p3);
            Boolean flag = hukouqianyiMapper.HkqyInsert(hukouqianyi);
            if (flag == true) {
                map.put("statue", "success");
            } else {
                map.put("statue", "false");
            }
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @Override
    public Map<String, Object> UpdateHkqy(Hukouqianyi hukouqianyi, MultipartFile[] files, String hkqyTime) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (files != null) {
                String[] result = null;
                String str = hukouqianyi.getHkqyZhengming();
                if (str != null) {
                    result = str.split(",");
                    for (int i = 0; i < result.length; i++) {
                        File file = new File(path0 + result[i]);
                        file.delete();
                    }
                }
            }
            long s = System.currentTimeMillis();
            String newName = null;
            String p = null;
            String p2 = null;
            String p3 = null;
            if (files.length != 0) {
                String path = path0;
                for (int i = 0; i < files.length; i++) {
                    String realName = files[i].getOriginalFilename();
                    newName = s + realName;
                    p = newName + ",";
                    File newFile = new File(path + newName);
                    p2 += p;
                    try {
                        files[i].transferTo(newFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                p3 = p2.substring(4);
            }
            hukouqianyi.setHkqyTime(Date.valueOf(hkqyTime));
            hukouqianyi.setHkqyZhengming(p3);
            Boolean flag = hukouqianyiMapper.HkqyUpdate(hukouqianyi);
            if (flag == true) {
                map.put("statue", "success");
            } else {
                map.put("statue", "false");
            }
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @Override
    public boolean Xvhao(String xvhao) {
        if (hukouqianyiMapper.getxvhao(xvhao) != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Map<String, Object> Add(Hukouqianyi hukouqianyi) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean B = hukouqianyiMapper.HkqyInsert(hukouqianyi);
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

    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        String path;
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件的名称" + originalFileName);
        //获取文件的类型，以“.”作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        path = "E:\\HJResourse\\Hukouqianyi\\";
        File filePath = new File(path);
        System.out.println("文件保存的路径" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            //System.out.println("图片目录不存在，创建图片路径"+filePath);
            filePath.mkdirs();
        }
        //设置文件的新名字
        String fileName = System.currentTimeMillis() + "." + type;
        System.out.println("文件新名称" + fileName);
        //在指定的路径下面创建一个新的文件
        File targerFile = new File(path, fileName);
        //将文件保存到服务器的指定位置
        try {
            file.transferTo(targerFile);
            map.put("path", fileName);
            map.put("name", originalFileName);
            return map;
        } catch (Exception e) {
            System.out.println("文件保存错误");
            map.put("path", "");
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> InsertHkqy2(Hukouqianyi hukouqianyi) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (hukouqianyiMapper.HkqyInsert(hukouqianyi) == true) {
                result.put("status", "success");
                result.put("data", "新增成功");
            } else {
                result.put("status", "error");
                result.put("data", "新增失败");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "新增出错");
            e.printStackTrace();
        }
        return result;
    }
}
