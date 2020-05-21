package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Villagecadres;
import com.hj.jdpth.domain.Villagecadres_Custom;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.VillagecadresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class VillagecadresServiceImpl implements VillagecadresService {
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    VillagecadresMapper villagecadresMapper;
    @Autowired
    JobMapper jobMapper;
    @Autowired
    ShengMapper shengMapper;
    @Autowired
    ShiMapper shiMapper;

    //上传图片
    @Override
    public String uploadImage(MultipartFile image) throws IOException {
        //上传路径
        String Basepath = "E:\\HJResourse\\VillagecadresImages";
        String imagename = null;
        if (!image.isEmpty()) {
            //重置上传文件原始名称
            String[] type = image.getContentType().split("/");
            imagename = UUID.randomUUID().toString().replace("-", "").toLowerCase() + "." + type[1];
            //设置保存目录
            File filepath = new File(Basepath);
            //判断路径是否存在
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            //保存到目标文件
            image.transferTo(new File(filepath + File.separator + imagename));
            System.out.println("上传路径：" + filepath + File.separator + imagename);
        }
        return imagename;
    }

    //删除老图片
    @Override
    public Boolean deleteImage(String path) {
        String Basepath = "E:\\HJResourse\\VillagecadresImages";
        Boolean bool = false;
        if (path != null && !path.equals("")) {
            File image = new File((Basepath + path));
            try {
                image.delete();
                bool = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            bool = true;
        }
        return bool;
    }

    //多条件动态查找干部
    @Override
    public Map<String, Object> queryVillagecadres(Map<String, Object> map) {
        List list = new ArrayList();
        if (map.get("cunId") != null && !map.get("cunId").equals("")) {
            list.add(map.get("cunId"));
        } else {
            list = villageMapper.selectIDDynamic(map);
            if (list.size() == 0)
                list.add(null);
        }
        map.put("list", list);

        PageHelper.startPage((int) map.get("startPage"), (int) map.get("pageSize"));
        Page<Villagecadres_Custom> page = villagecadresMapper.selectDynamic(map);

        Map<String, Object> result = new HashMap<>();
        result.put("Villagecadres", page);
        //总页数
        result.put("total", page.getPages());
        //记录总数
        result.put("count", page.getTotal());
        //当前页数
        result.put("nowPage", page.getPageNum());
        return result;
    }

    //删除干部
    @Override
    public void deleteVillagecadres(List<Integer> ids) {
        int size = ids.size();
        for (int i = 0; i < size; i++) {
            villagecadresMapper.deleteById(ids.get(i));
        }
    }

    //查看详情
    @Override
    public Villagecadres getVillagecadreDetail(int id) {
        return villagecadresMapper.selectById(id);
    }

    //动态插入
    @Override
    public void insertDynameic(Villagecadres obj) {
        try {
            villagecadresMapper.insertDynamic(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //动态更新
    @Override
    public void updateDynamic(Villagecadres obj) {
        villagecadresMapper.updateDynamic(obj);
    }

    //级联区Id
    @Override
    public List<HashMap> selectRegionID(int id) {
        return regionMapper.selectRegionIDAdName(id);
    }

    //级联镇ID
    @Override
    public List<HashMap> selectZhenID(int id, int type, int zhenId) {
        List<HashMap> list = new ArrayList<HashMap>();
        if (type == 2 || type == 3) {
            list = zhenMapper.selectIdByRegin_Id(id);
        } else if (type == 4 || type == 5) {
            list = zhenMapper.selectIdByZhen_Id(zhenId);
        }
        return list;
    }

    //级联村ID
    @Override
    public List<HashMap> selectCunID(int id, int type, int cunId) {
        List<HashMap> list = new ArrayList<HashMap>();
        if (type == 2 || type == 3 || type == 4) {
            list = villageMapper.selectIDByZhen_Id(id);
        } else if (type == 5) {
            list = villageMapper.selectIDAdName(cunId);
        }
        return list;
    }

    //下拉列表职务
    @Override
    public List<HashMap> selectNameAdID() {
        return jobMapper.selectNameAdID();
    }

    @Override
    public List<HashMap> queryJobNameById(int id) {
        return jobMapper.selectNameAdIDX(id);
    }

    //添加省。下拉列表
    @Override
    public List<HashMap> selectShengAdId() {
        return shengMapper.selectShengAdId();
    }

    //添加市。下拉列表
    @Override
    public List<HashMap> selectShigAdId(int id) {
        return shiMapper.selectShiNameAdId(id);
    }

    //添加区。下拉列表
    @Override
    public List<HashMap> selectRegionAdId(int id) {
        return regionMapper.selectRegionNameAdId(id);
    }

    //添加镇。下拉列表
    @Override
    public List<HashMap> selectZhenAdId(int id) {
        return zhenMapper.selectIdByRegin_Id(id);
    }

    //添加村。下拉列表
    @Override
    public List<HashMap> selectVillageAdId(int id) {
        return villageMapper.selectIDByZhen_Id(id);
    }
}
