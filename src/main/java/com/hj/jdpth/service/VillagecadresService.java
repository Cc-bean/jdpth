package com.hj.jdpth.service;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Villagecadres;
import com.hj.jdpth.domain.Villagecadres_Custom;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VillagecadresService {
    //上传新图片
    public String uploadImage(MultipartFile image) throws IOException;

    //删除老图片
    public Boolean deleteImage(String path);

    //多条件动态查找干部
    public Map<String, Object> queryVillagecadres(Map<String, Object> map);

    //删除干部
    public void deleteVillagecadres(List<Integer> ids);

    //查看详情
    public Villagecadres getVillagecadreDetail(int id);

    //动态插入
    public void insertDynameic(Villagecadres obj);

    //动态更新
    public void updateDynamic(Villagecadres obj);

    //级联区Id
    public List<HashMap> selectRegionID(int id);

    //级联镇ID
    public List<HashMap> selectZhenID(int id, int type, int zhenId);

    //级联村ID
    public List<HashMap> selectCunID(int id, int type, int cunId);

    //下拉列表职务
    public List<HashMap> selectNameAdID();

    //
    public List<HashMap> queryJobNameById(int id);

    //添加省。下拉列表
    public List<HashMap> selectShengAdId();

    //添加市。下拉列表
    public List<HashMap> selectShigAdId(int id);

    //添加区。下拉列表
    public List<HashMap> selectRegionAdId(int id);

    //添加镇。下拉列表
    public List<HashMap> selectZhenAdId(int id);

    //添加村。下拉列表
    public List<HashMap> selectVillageAdId(int id);

}
