package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Region;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component(value = "regionMapper")
public interface RegionMapper {
    ////
    //级联搜索用
    //查所有区ID
    @Select("select R_Key,R_name from region where R_Key=#{reionid}")
    List<HashMap> selectRegionIDAdName(int reionid);

    //添加级联用
    @Select(("select R_Key,R_Name from region where R_shi=#{shiId}"))
    List<HashMap> selectRegionNameAdId(int shiId);

    //根据Id查区名
    @Select("select R_Name from region where R_Key=#{id}")
    public String selectNameByID(int id);

    ///
    @Select("select * from region where R_Key=(select M_Region from manager where Manager_Id=#{id})")
    //根据管理员id获得区
    Region FindBymanager(Integer id);

    @Select("select * from region where R_Key=#{id}")
    Region FindById(Integer id);

    @Select("select R_Key,R_name from region where R_Key = #{qvid}")
    Region queryById_gsh(int qvid);

    @Select("select * from region where R_Key=#{id}")
    Region queryById_yyq(Integer id);

    @Select("select * from region where R_shi=#{id}")
    List<Region> queryRegionByShiId_yyq(Integer id);
}
