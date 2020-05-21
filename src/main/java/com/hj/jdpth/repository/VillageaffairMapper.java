package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Villageaffair;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(value = "villageaffairMapper")
public interface VillageaffairMapper {
    //搜索行政村会议
    @Select("select * from (villageaffair inner join village on VA_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where Z_region=#{regionId}")
//按区Id查村公告
    Page<Map<String, Object>> findaffairByRegionId(Integer regionId);

    @Select("select * from (villageaffair inner join village on VA_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where Zhen_Id=#{zhenId}")
//按镇Id查村公告
    Page<Map<String, Object>> findaffairByZhenId(Integer zhenId);

    @Select("select * from (villageaffair inner join village on VA_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where Village_Id=#{cunId}")
//按村Id查村公告
    Page<Map<String, Object>> findaffairByCunId(Integer cunId);

    @Select("select * from (villageaffair inner join village on VA_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where VA_Name like '${value}%' ")
//按名字查村公告
    Page<Map<String, Object>> findaffairByaffairName(@Param("value") String value);

    //按会议ID查询会议详情
    @Select("select * from (villageaffair inner join village on VA_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where VillageAffair_Id=#{id}")
    Map<String, Object> findAffairXingQing_yp(Integer id);

    //删除一条记录
    @Delete("delete from villageaffair where VillageAffair_Id=#{id}")
    Boolean deleteAffairById_yp(Integer id);

    //添加一条记录
    @Insert("insert into villageaffair (VA_Villageid,VA_Time,VA_Place,VA_Attendants,VA_Host,VA_Notetaker,VA_Content,VA_TypeOfAffair,VA_Photo,VA_Name,VA_Title,VA_xuhao)values(#{vaVillageid},#{vaTime},#{vaPlace},#{vaAttendants},#{vaHost},#{vaNotetaker},#{vaContent},#{vaTypeofaffair},#{vaPhoto},#{vaName},#{vaTitle},#{vaXuhao}) ")
    int insertAffair_yp(Villageaffair villageaffair);

    //编辑 更新一条记录
    @Update("update villageaffair set VA_Villageid=#{vaVillageid},VA_Time=#{vaTime},VA_Place=#{vaPlace},VA_Attendants=#{vaAttendants},VA_Host=#{vaHost},VA_Notetaker=#{vaNotetaker},VA_Content=#{vaContent},VA_TypeOfAffair=#{vaTypeofaffair},VA_Photo=#{vaPhoto},VA_Name=#{vaName},VA_Title=#{vaTitle},VA_xuhao=#{vaXuhao} where VillageAffair_Id=#{villageaffairId}")
    Boolean updateAffair_yp(Villageaffair villageaffair);

    //根据序号查找一个村会议的信息
    @Select("select * from villageaffair where VA_xuhao=#{vaXuhao}")
    Villageaffair findAffairByxuhao_yp(@Param("vaXuhao") String vaXuhao);

    //2019-11-27
    @Select("select * from villageaffair where VillageAffair_Id=#{id}")
    Villageaffair findbyid_lfm(Integer id);
}
