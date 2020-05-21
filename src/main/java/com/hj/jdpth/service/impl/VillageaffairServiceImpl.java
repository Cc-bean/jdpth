package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Villageaffair;
import com.hj.jdpth.repository.VillageaffairMapper;
import com.hj.jdpth.service.VillageaffairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class VillageaffairServiceImpl implements VillageaffairService {
    @Autowired
    VillageaffairMapper villageaffairMapper;


    @Override
    public Map<String, Object> findAffair(Integer style, Integer qu_id, Integer zhen_id, Integer cun_id, String affairName, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        if (affairName != null) {
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cungonggao = villageaffairMapper.findaffairByaffairName(affairName);
                if (cungonggao == null) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cungonggao);
                    //总页数
                    result.put("total", cungonggao.getPages());
                    //记录总数
                    result.put("count", cungonggao.getTotal());
                    //当前第几页
                    result.put("nowPage", cungonggao.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 2 || style == 3) {
            System.out.println("进入区级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cungonggao;

                if (cun_id != 0) {
                    cungonggao = villageaffairMapper.findaffairByCunId(cun_id);
                } else if (zhen_id != 0) {
                    cungonggao = villageaffairMapper.findaffairByZhenId(zhen_id);
                } else {
                    cungonggao = villageaffairMapper.findaffairByRegionId(qu_id);
                }

                if (cungonggao.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cungonggao);
                    //总页数
                    result.put("total", cungonggao.getPages());
                    //记录总数
                    result.put("count", cungonggao.getTotal());
                    //当前第几页
                    result.put("nowPage", cungonggao.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 4) {
            System.out.println("进入镇级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cungonggao;
                if (cun_id != 0) {
                    cungonggao = villageaffairMapper.findaffairByCunId(cun_id);
                } else {
                    cungonggao = villageaffairMapper.findaffairByZhenId(zhen_id);
                }
                if (cungonggao.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cungonggao);
                    //总页数
                    result.put("total", cungonggao.getPages());
                    //记录总数
                    result.put("count", cungonggao.getTotal());
                    //当前第几页
                    result.put("nowPage", cungonggao.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 5) {
            System.out.println("进入村级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cungonggao = villageaffairMapper.findaffairByCunId(cun_id);
                if (cungonggao.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cungonggao);
                    //总页数
                    result.put("total", cungonggao.getPages());
                    //记录总数
                    result.put("count", cungonggao.getTotal());
                    //当前第几页
                    result.put("nowPage", cungonggao.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> findAffairXingQing_yp(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> affair = villageaffairMapper.findAffairXingQing_yp(id);
        result.put("affair", affair);
        return result;
    }

    @Override
    public Map<String, Object> deleteAffairById_yp(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Boolean flag = villageaffairMapper.deleteAffairById_yp(id);
        result.put("flag", flag);
        return result;
    }

    @Override
    public Map<String, Object> deleteManyAffairById_yp(String Ids) {
        String[] ids = Ids.split(",");
        Map<String, Object> result = new HashMap<>();
        for (String id : ids) {
            Boolean flag = villageaffairMapper.deleteAffairById_yp(Integer.parseInt(id));
            if (flag == true) {
                if (result.get("success") != null) {
                    result.put("success", id + "," + result.get("success"));
                } else {
                    result.put("success", id);
                }
            } else {
                if (result.get("error") != null) {
                    result.put("error", id + "," + result.get("error"));
                } else {
                    result.put("error", id);
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> insertAffair_yp(Villageaffair villageaffair) {
        Map<String, Object> result = new HashMap<>();
        int i = villageaffairMapper.insertAffair_yp(villageaffair);
        if (i > 0) {
            result.put("result", i);
        } else {
            result.put("result", i);
        }
        return result;
    }

    @Override
    public Map<String, Object> updateAffair_yp(Villageaffair villageaffair) {
        Map<String, Object> result = new HashMap<>();
        Boolean flag = villageaffairMapper.updateAffair_yp(villageaffair);
        if (flag == true) {
            result.put("result", "更新成功");
        } else {
            result.put("result", "更新失败");
        }
        return result;
    }

    @Override
    public Boolean findAffairByxuhao_yp(String vaXuhao) {
        if (villageaffairMapper.findAffairByxuhao_yp(vaXuhao) != null) {
            return false;
        } else {
            return true;
        }
    }

    //2019-11-27
    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        String path;
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件的名称" + originalFileName);
        //获取文件的类型，以“.”作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        path = "E:\\HJResourse\\CunHuiyi\\";
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
}
