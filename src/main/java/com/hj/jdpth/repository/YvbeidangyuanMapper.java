package com.hj.jdpth.repository;


import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Yvbeidangyuan;
import com.hj.jdpth.domain.Zuzifazhan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "yvbeidangyuanMapper")
public interface YvbeidangyuanMapper {

    //查询全部信息
    @Select("select * from yvbeidangyuan where YBDY_Village in(select Village_Id from village where V_Region=#{quId})")
    @Results(value = {
            @Result(column = "YBDY_Id", property = "id"),
            @Result(column = "YBDY_Name", property = "name"),
            @Result(column = "YBDY_Native", property = "minzu"),
            @Result(column = "YBDY_Time", property = "time"),
            @Result(column = "YBDY_WenHua", property = "wenhua"),
            @Result(column = "YBDY_Village", property = "villageId"),
            @Result(column = "YBDY_Zu", property = "zuId"),
            @Result(column = "YBDY_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "YBDY_DanWei", property = "danwei"),
            @Result(column = "YBDY_ZhiWu", property = "zhiwu"),
            @Result(column = "YBDY_Sex", property = "sex"),
            @Result(column = "YBDY_Phone", property = "phone"),
            @Result(column = "YBDY_Year", property = "year"),
            @Result(column = "YBDY_Entity", property = "entity"),
            @Result(column = "YBDY_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GentInfo(@Param("quId") Integer quId);

    //查询一个镇的信息
    @Select("select * from yvbeidangyuan where YBDY_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId})")
    @Results(value = {
            @Result(column = "YBDY_Id", property = "id"),
            @Result(column = "YBDY_Name", property = "name"),
            @Result(column = "YBDY_Native", property = "minzu"),
            @Result(column = "YBDY_Time", property = "time"),
            @Result(column = "YBDY_WenHua", property = "wenhua"),
            @Result(column = "YBDY_Village", property = "villageId"),
            @Result(column = "YBDY_Zu", property = "zuId"),
            @Result(column = "YBDY_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "YBDY_DanWei", property = "danwei"),
            @Result(column = "YBDY_ZhiWu", property = "zhiwu"),
            @Result(column = "YBDY_Sex", property = "sex"),
            @Result(column = "YBDY_Phone", property = "phone"),
            @Result(column = "YBDY_Year", property = "year"),
            @Result(column = "YBDY_Entity", property = "entity"),
            @Result(column = "YBDY_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByZhen(@Param("zhenId") Integer zhenId);

    //查询一个村的信息
    @Select("select * from yvbeidangyuan where YBDY_Village=#{cunId} ")
    @Results(value = {
            @Result(column = "YBDY_Id", property = "id"),
            @Result(column = "YBDY_Name", property = "name"),
            @Result(column = "YBDY_Native", property = "minzu"),
            @Result(column = "YBDY_Time", property = "time"),
            @Result(column = "YBDY_WenHua", property = "wenhua"),
            @Result(column = "YBDY_Village", property = "villageId"),
            @Result(column = "YBDY_Zu", property = "zuId"),
            @Result(column = "YBDY_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "YBDY_DanWei", property = "danwei"),
            @Result(column = "YBDY_ZhiWu", property = "zhiwu"),
            @Result(column = "YBDY_Sex", property = "sex"),
            @Result(column = "YBDY_Phone", property = "phone"),
            @Result(column = "YBDY_Year", property = "year"),
            @Result(column = "YBDY_Entity", property = "entity"),
            @Result(column = "YBDY_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByCun(@Param("cunId") Integer cunId);

    @Select("select * from yvbeidangyuan where YBDY_Time=#{time}")
    @Results(value = {
            @Result(column = "YBDY_Id", property = "id"),
            @Result(column = "YBDY_Name", property = "name"),
            @Result(column = "YBDY_Native", property = "minzu"),
            @Result(column = "YBDY_Time", property = "time"),
            @Result(column = "YBDY_WenHua", property = "wenhua"),
            @Result(column = "YBDY_Village", property = "villageId"),
            @Result(column = "YBDY_Zu", property = "zuId"),
            @Result(column = "YBDY_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "YBDY_DanWei", property = "danwei"),
            @Result(column = "YBDY_ZhiWu", property = "zhiwu"),
            @Result(column = "YBDY_Sex", property = "sex"),
            @Result(column = "YBDY_Phone", property = "phone"),
            @Result(column = "YBDY_Year", property = "year"),
            @Result(column = "YBDY_Entity", property = "entity"),
            @Result(column = "YBDY_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByTime(@Param("time") Date time);

    @Select("select * from yvbeidangyuan where YBDY_Entity=#{entity}")
    Page<Zuzifazhan> GetInfoByEntity(@Param("entity") Integer entity);

    @Select("select * from yvbeidangyuan where YBDY_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})")
    Page<Yvbeidangyuan> pageFindYvbeidangyuanByZId(Integer zId);

    @Select("select * from yvbeidangyuan where YBDY_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})and YBDY_Village =#{vId}")
    Page<Yvbeidangyuan> pageYvbeidangyuanByVIdandZId(@Param("zId") Integer zId, @Param("vId") Integer vId);

    @Select("select * from yvbeidangyuan where YBDY_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})and YBDY_Village =#{vId} and YBDY_Time=#{ybdyTime}")
    Page<Yvbeidangyuan> pageYvbeidangyuanByVIdandZIdandYbdyTime(@Param("zId") Integer zId, @Param("vId") Integer vId, @Param("ybdyTime") String ybdyTime);

    //查询所有预备党员
    @Select("select * from yvbeidangyuan ")
    Page<Yvbeidangyuan> selectAllYvbeidangyuan();

    //根据id删除一条数
    @Delete("delete from yvbeidangyuan where  YBDY_Id=#{ybdyId}")
    int DeleteYvbeidangyuan(Integer ybdyId);

    //同时删除多条数据
    @Delete("delete from yvbeidangyuan where   YBDY_Id=#{ybdyId} ")
    int DeleteDuoTiaoYvbeidangyuan(Integer[] ybdyId);

    //插入
    @Insert("insert into yvbeidangyuan (YBDY_Name,YBDY_Native,YBDY_Time,YBDY_Wenhua,YBDY_Village,YBDY_Zu,YBDY_ShengFenZheng,YBDY_Danwei,YBDY_Zhiwu,YBDY_Sex,YBDY_Phone,YBDY_Year,YBDY_Xuhao,YBDY_Entity)values(#{ybdyName},#{ybdyNative},#{ybdyTime},#{ybdyWenhua},#{ybdyVillage},#{ybdyZu},#{ybdyShengfenzheng},#{ybdyDanwei},#{ybdyZhiwu},#{ybdySex},#{ybdyPhone},#{ybdyYear},#{ybdyXuhao},#{ybdyEntity}) ")
    int InsertYvbeidangyuan(Yvbeidangyuan yvbeidangyuan);

    //根据id查询
    @Select("select * from yvbeidangyuan where  YBDY_Id=#{ybdyId} ")
    Yvbeidangyuan queryAll(Integer ybdyId);

    //根据id修改
    @Update("update yvbeidangyuan set YBDY_Name=#{ybdyName},YBDY_Native=#{ybdyNative},YBDY_Time=#{ybdyTime},YBDY_Wenhua=#{ybdyWenhua},YBDY_Village=#{ybdyVillage},YBDY_Zu=#{ybdyZu},YBDY_ShengFenZheng=#{ybdyShengfenzheng},YBDY_Danwei=#{ybdyDanwei},YBDY_Zhiwu=#{ybdyZhiwu},YBDY_Sex=#{ybdySex},YBDY_Phone=#{ybdyPhone},YBDY_Year=#{ybdyYear},YBDY_Entity=#{ybdyEntity} where YBDY_Id=#{ybdyId}")
    int UpdateYvbeidangyuan(Yvbeidangyuan yvbeidangyuan);

    //根据序号查询预备党员
    @Select("Select * from yvbeidangyuan where YBDY_xuhao=#{xuhao}")
    Yvbeidangyuan findYvbeidangyuanByxuhao(String xuhao);

    @Select("Select count(*) from yvbeidangyuan where  YBDY_Village in(select Village_Id from village where V_Region=#{quId})and YBDY_Time between #{time1} and #{time2}")
    Integer countByQu(@Param("quId") Integer quId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select count(*) from yvbeidangyuan where YBDY_Village in(select Village_Id from village where V_Zhen_xiang=#{zId})")
    Integer countByZhen(@Param("zId") Integer zId);

    @Select("Select count(*) from yvbeidangyuan where YBDY_Village in(select Village_Id from village where V_Zhen_xiang=#{zId})and YBDY_Time between #{time1} and #{time2}")
    Integer FindByZhen(@Param("zId") Integer zId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select * from yvbeidangyuan where YBDY_Village=#{vId}")
    @Results(value = {
            @Result(column = "YBDY_Id", property = "id"),
            @Result(column = "YBDY_Name", property = "name"),
            @Result(column = "YBDY_Native", property = "minzu"),
            @Result(column = "YBDY_Time", property = "time"),
            @Result(column = "YBDY_WenHua", property = "wenhua"),
            @Result(column = "YBDY_Village", property = "villageId"),
            @Result(column = "YBDY_Zu", property = "zuId"),
            @Result(column = "YBDY_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "YBDY_DanWei", property = "danwei"),
            @Result(column = "YBDY_ZhiWu", property = "zhiwu"),
            @Result(column = "YBDY_Sex", property = "sex"),
            @Result(column = "YBDY_Phone", property = "phone"),
            @Result(column = "YBDY_Year", property = "year"),
            @Result(column = "YBDY_Entity", property = "entity"),
            @Result(column = "YBDY_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> findByCun(@Param("vId") Integer vId);

}