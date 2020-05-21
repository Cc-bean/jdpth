package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Villagesurvey;
import com.hj.jdpth.repository.VillageMapper;
import com.hj.jdpth.repository.VillagesurveyMapper;
import com.hj.jdpth.service.VillageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class VillageInfoServiceImpl implements VillageInfoService {
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    VillagesurveyMapper villagesurveyMapper;

    String path0 = "E:\\HJResourse\\Village\\";

    @Override
    public Map<String, Object> VillageInfoSearch(Integer mType, Integer qid, Integer zid, Integer vid, Integer startPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Village> villagePage = new Page<>();
        if (mType == 2) {
            System.out.println("qu");
            try {
                villagePage = villageMapper.SearchVillage_lfm(qid, zid, vid);
                for (int i = 0; i < villagePage.size(); i++) {
                    Villagesurvey villagesurvey = villagesurveyMapper.FindById_lfm(villagePage.get(i).getVillageId());
                    villagePage.get(i).setVillagesurvey(villagesurvey);
                }
            } catch (Exception e) {
                map.put("status", "error");
                map.put("data", "查询出错");
            }
        }
        if (mType == 3) {
            System.out.println("zhineng");
            try {
                villagePage = villageMapper.SearchVillage_lfm(qid, zid, vid);
                for (int i = 0; i < villagePage.size(); i++) {
                    Villagesurvey villagesurvey = villagesurveyMapper.FindById_lfm(villagePage.get(i).getVillageId());
                    villagePage.get(i).setVillagesurvey(villagesurvey);
                }
            } catch (Exception e) {
                map.put("status", "error");
                map.put("data", "查询出错");
            }
        }
        if (mType == 4) {
            System.out.println("zhen");
            try {
                villagePage = villageMapper.SearchVillage_lfm(qid, zid, vid);
                for (int i = 0; i < villagePage.size(); i++) {
                    Villagesurvey villagesurvey = villagesurveyMapper.FindById_lfm(villagePage.get(i).getVillageId());
                    villagePage.get(i).setVillagesurvey(villagesurvey);
                }
            } catch (Exception e) {
                map.put("status", "error");
                map.put("data", "查询出错");
            }
        }
        if (mType == 5) {
            System.out.println("cun");
            try {
                villagePage = villageMapper.SearchVillage_lfm(qid, zid, vid);
                for (int i = 0; i < villagePage.size(); i++) {
                    Villagesurvey villagesurvey = villagesurveyMapper.FindById_lfm(villagePage.get(i).getVillageId());
                    villagePage.get(i).setVillagesurvey(villagesurvey);
                }
            } catch (Exception e) {
                map.put("status", "error");
                map.put("data", "查询出错");
            }
        }
        map.put("data", villagePage);
        //总页数
        map.put("total", villagePage.getPages());
        //记录总数
        map.put("count", villagePage.getTotal());
        //当前第几页
        map.put("nowPage", villagePage.getPageNum());
        return map;
    }

    @Override
    public Map<String, Object> VillageXQ(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Village village = villageMapper.FindById_lfm(id);
        Villagesurvey villagesurvey = villagesurveyMapper.FindById_lfm(village.getVillageId());
        map.put("data", villagesurvey);
        map.put("diqu", village.getvAdress());
        return map;
    }

    @Override
    public Map<String, Object> InsertV(Village village, MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        long s = System.currentTimeMillis();
        String newName = null;
        String p = null;
        String p2 = null;
        String p3 = null;
        try {
            if (files.length != 0) {
                String path = path0;
                for (int i = 0; i < files.length; i++) {
                    String realName = files[i].getOriginalFilename();
                    newName = s + realName;
                    p = newName + ",";
                    File newFile = new File(path + newName);
                    p2 += p;
                    files[i].transferTo(newFile);
                }
                p3 = p2.substring(4);
                village.setvImagepath1(p3);
            }
            boolean b = villageMapper.Insert(village);
            if (b == true) {
                Villagesurvey villagesurvey = new Villagesurvey();
                villagesurvey.setVsVillageid(village.getVillageId());
                villagesurvey.setVsSurvey(village.getvBeiyong3());
                villagesurvey.setVsVillagehead(village.getvBeiyong1());
                villagesurvey.setVsShuji(village.getvBeiyong2());
                villagesurvey.setVsPopulation(Integer.parseInt(village.getvPopulation()));
                villagesurvey.setVsPlace(village.getvAdress());
                villagesurveyMapper.VInsert(villagesurvey);
                map.put("statue", "success");
            } else {
                map.put("statue", "false");
            }
            return map;
        } catch (Exception e) {
            map.put("error", e.getMessage());
            return map;
        }
    }

    @Override
    public Map<String, Object> UpdateV(Village village, MultipartFile[] files) {
        System.out.println(village.getvImagepath1());
        Map<String, Object> map = new HashMap<>();
        long s = System.currentTimeMillis();
        String newName = null;
        String p = null;
        String p2 = null;
        String p3 = null;
        try {
            if (files.length != 0) {
                System.out.println("aa");
                if (files != null) {
                    String[] result = null;
                    String str = village.getvImagepath1();
                    if (str != null) {
                        result = str.split(",");
                        for (int i = 0; i < result.length; i++) {
                            File file = new File(path0 + result[i]);
                            file.delete();
                        }
                    }
                }
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
                village.setvImagepath1(p3);
            }
            Boolean flag = villageMapper.Update(village);
            if (flag == true) {
                Villagesurvey villagesurvey = villageMapper.FindByVid(village.getVillageId());
                villagesurvey.setVsSurvey(village.getvBeiyong3());
                villagesurvey.setVsVillagehead(village.getvBeiyong1());
                villagesurvey.setVsShuji(village.getvBeiyong2());
                villagesurvey.setVsPopulation(Integer.parseInt(village.getvPopulation()));
                villagesurvey.setVsPlace(village.getvAdress());
                villagesurveyMapper.VUpdate(villagesurvey);
                map.put("statue", "success");
            } else {
                map.put("statue", "false");
            }
            map.put("statue", "success");
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    public Map<String, Object> uploadFile(MultipartFile file) {
        String path;
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件的名称" + originalFileName);
        //获取文件的类型，以“.”作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        path = "E:\\HJResourse\\Village\\";
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
    public Map<String, Object> InsertV2(Village village) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (villageMapper.Insert(village) == true) {
                Villagesurvey villagesurvey = new Villagesurvey();
                villagesurvey.setVsVillageid(village.getVillageId());
                villagesurvey.setVsSurvey(village.getvBeiyong3());
                villagesurvey.setVsVillagehead(village.getvBeiyong1());
                villagesurvey.setVsShuji(village.getvBeiyong2());
                villagesurvey.setVsPopulation(Integer.parseInt(village.getvPopulation()));
                villagesurvey.setVsPlace(village.getvAdress());
                villagesurveyMapper.VInsert(villagesurvey);
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

    @Override
    public Map<String, Object> UpdateV2(Village village) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (villageMapper.Update(village) == true) {
                Villagesurvey villagesurvey = villageMapper.FindByVid(village.getVillageId());
                if (villagesurvey != null) {
                    villagesurvey.setVsSurvey(village.getvBeiyong3());
                    villagesurvey.setVsVillagehead(village.getvBeiyong1());
                    villagesurvey.setVsShuji(village.getvBeiyong2());
                    villagesurvey.setVsPopulation(Integer.parseInt(village.getvPopulation()));
                    villagesurvey.setVsPlace(village.getvAdress());
                    villagesurveyMapper.VUpdate(villagesurvey);
                }
                result.put("statue", "success");
            } else {
                result.put("statue", "error");
            }
        } catch (Exception e) {
            result.put("statue", "error");
        }
        return result;
    }
}
