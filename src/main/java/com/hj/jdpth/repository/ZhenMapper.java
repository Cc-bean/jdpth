package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Zhen;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;

@Component(value = "zhenMapper")
public interface ZhenMapper {
    ////////////
    //级联用
    //根据区Id查镇Id
    @Select("select Zhen_Id,Z_Name from zhen where Z_region =#{regionId}")
    List<HashMap> selectIdByRegin_Id(int regionId);

    //2/3级管理选用
    @Select("select Zhen_Id,Z_Name from zhen where Zhen_Id =#{zhenId}")
    List<HashMap> selectIdByZhen_Id(int zhenId);

    //根据Id查镇名
    @Select("select Z_Name from zhen zhen where Zhen_Id =#{zhenId}")
    String selectNameByID(int id);

    ///////////
    @Select("select * from zhen where Zhen_Id=(select M_ZhenId from manager where Manager_Id=#{id})")
    Zhen FindBymanager(Integer id);

    @Select("select * from zhen where Zhen_Id=#{id}")
    Zhen FindById_lfm(Integer id);

    //区查镇
    @Select("select Zhen_Id,Z_Name from zhen where Z_region = #{Z_region}")
    List<Zhen> quaryByRegion_gsh(Integer Z_region);

    //id查镇
    @Select("select Zhen_Id,Z_Name from zhen where Zhen_Id = #{id}")
    Zhen quaryById_gsh(Integer id);


    @Select("select * from zhen where Z_region=#{qu_id}")
    List<Zhen> queryZhenByQuId_yyq(Integer qu_id);

    @Select("select * from zhen where Zhen_Id=#{zhen_id}")
    Zhen queryByZhenId_yyq(Integer zhen_id);

    //反查
    @Select("<script>" +
            "SELECT region.R_Key,R_name,Zhen_Id,zhen.Z_Name,Village_Id,V_Name,Z_Key,zu.Z_name as z\n" +
            "FROM region left join zhen on Z_region=R_Key left join village on V_Zhen_xiang = zhen.Zhen_Id left join zu on zu.Z_village=Village_Id \n" +
            "where " +
            "<if test='villageId != null'>" +
            "Village_Id=#{villageId}" +
            "</if> " +
            "<if test='zuId != null'>" +
            "zu.Z_Key = #{zuId}" +
            "</if> " +
            "</script>")
    @Results({@Result(column = "region.R_Key", property = "regionId"),
            @Result(column = "R_name", property = "regionName"),
            @Result(column = "Zhen_Id", property = "zhenId"),
            @Result(column = "zhen.Z_Name", property = "zhenName"),
            @Result(column = "Village_Id", property = "villageId"),
            @Result(column = "V_Name", property = "villageName"),
            @Result(column = "Z_Key", property = "zuId"),
            @Result(column = "z", property = "zuName")
    })
    List<HashMap> queryIn(@Param("villageId") Integer villageId, @Param("zuId") Integer zuId);

    //2019-11-27
    @Select("select * from zhen where Z_Name=#{zhenname}")
    Zhen quaryByname(String zhenname);

}