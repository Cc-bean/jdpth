package com.hj.jdpth.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImportExcelService {
    //导入村干部信息
    public Map<String, Object> ImportVillagecadres(MultipartFile file);

    //导入村民信息
    public Map<String, Object> ImportYongHu(MultipartFile file);

    //导入行政村补助信息表
    //public Map<String,Object> ImportSubsidy(MultipartFile file);
    //导入补助个人表
    public Map<String, Object> ImportSubsidyProsonal(MultipartFile file);

    //导入补助家庭表
    public Map<String, Object> ImportSubsidyFamily(MultipartFile file);

    //校验excel
    public boolean isExcel(String name);
}
