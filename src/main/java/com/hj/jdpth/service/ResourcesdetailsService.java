package com.hj.jdpth.service;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.domain.Resources;
import com.hj.jdpth.domain.Resourcesdetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ResourcesdetailsService {
    //用了
    public Map<String, Object> queryResourcesdetails(Integer mType, Integer mRegion, Integer mZhenid, Integer mVillageid, String resourceStyle, Integer startPage, Integer pageSize);

    public Map<String, Object> deleteOne(Integer id);

    public Map<String, Object> deleteMany(String[] arrayId);

    public Map<String, Object> deleteStringMany(String resourcesId);

    public Map<String, Object> addResourcesDetails(String RD_Adress, Float RD_AreaCovered, String RD_Details, String RD_Name, Integer RD_ResourcesId);

    public Map<String, Object> uploadExcel(String path);

    public Map<String, Object> uploadFile(MultipartFile file);
}
