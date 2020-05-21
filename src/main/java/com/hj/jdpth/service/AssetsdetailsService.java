package com.hj.jdpth.service;


import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.Map;

public interface AssetsdetailsService {

    public Map<String, Object> quaryAssetsdetails(int mType, int mRegion, int mZhenid, int mVillageid, String aType, int startPage, int pageSize);

    public Map<String, Object> quaryZichanmingziByType(String aType, int startPage, int pageSize);

    public Map<String, Object> deleteOne(Integer id);

    public Map<String, Object> deleteMany(String ids);

    public Map<String, Object> quaryA_Type(Integer A_Villageid, Integer A_Zu);

    public Map<String, Object> addZCMZ_Name(Integer A_Zu, Integer A_Villageid, String ZCMZ_Name, Integer A_Type);

    public Map<String, Object> addZCTZ(Integer zjtzName, String zjtzJilangdanwei, Integer zjtzShuliang, Float zjtzDanjian, String zjtzYuanzhi,
                                       String zjtzBiandongqingkuang, String zjtzXianzhi, String zjtzPinpai,
                                       String zjtzXinghao, Date zjtzGoujianshijian, String zjtzLaiyuan,
                                       String zjtzYvjinianxian, String zjtzDepartment, String zjtzPeople, String zjtzBeizhu, String zjtzZhuangtai);

    public Map<String, Object> addZCJY(Integer zcjyName, String zcjyPhoto, String zcjyZhenshilujing, String zcjyZhuangtai,
                                       String zcjyChengzhuren, Boolean zcjyHetong, String zcjyHetongqixian,
                                       Float zcjyHetongjine, String zcjyZhifuqinkuang, String zcjyBeizhu, String zcjyXvhao);


    public Map<String, Object> insertZCTZ(Integer zjtzId, String zjtzJilangdanwei, Integer zjtzShuliang, Float zjtzDanjian,
                                          String zjtzBiandongqingkuang, String zjtzXianzhi, String zjtzPinpai,
                                          String zjtzXinghao, Date zjtzGoujianshijian, String zjtzLaiyuan,
                                          String zjtzYvjinianxian, String zjtzDepartment, String zjtzPeople, String zjtzBeizhu, String zjtzZhuangtai);

    public Map<String, Object> insertZCJY(Integer zcjyId, String zcjyPhoto, String zcjyZhenshilujing, String zcjyZhuangtai,
                                          String zcjyChengzhuren, Boolean zcjyHetong, String zcjyHetongqixian,
                                          Float zcjyHetongjine, String zcjyZhifuqinkuang, String zcjyBeizhu, String zcjyXvhao);

    public Map<String, Object> uploadFile_ZiChanJingYing(MultipartFile file);

    public Map<String, Object> uploadFile_ZiChanTaiZhang(MultipartFile file);

    public Map<String, Object> uploadExcel_ZiChanJingYing(String path);

    public Map<String, Object> uploadExcel_ZiChanTaiZhang(String path);
}
