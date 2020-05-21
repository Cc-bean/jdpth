package com.hj.jdpth.repository;


import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Dangyuanzhuanzheng;
import com.hj.jdpth.domain.Zuzifazhan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "dangyuanzhuanzhengMapper")
public interface DangyuanzhuanzhengMapper {

    //查询全部信息
    @Select("select * from dangyuanzhuanzheng where DYZZ_Village in(select Village_Id from village where V_Region=#{quId})")
    @Results(value = {
            @Result(column = "DYZZ_Id", property = "id"),
            @Result(column = "DYZZ_Name", property = "name"),
            @Result(column = "DYZZ_Native", property = "minzu"),
            @Result(column = "DYZZ_Time", property = "time"),
            @Result(column = "DYZZ_WenHua", property = "wenhua"),
            @Result(column = "DYZZ_Village", property = "villageId"),
            @Result(column = "DYZZ_Zu", property = "zuId"),
            @Result(column = "DYZZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "DYZZ_DanWei", property = "danwei"),
            @Result(column = "DYZZ_ZhiWu", property = "zhiwu"),
            @Result(column = "DYZZ_Sex", property = "sex"),
            @Result(column = "DYZZ_Phone", property = "phone"),
            @Result(column = "DYZZ_Year", property = "year"),
            @Result(column = "DYZZ_Entity", property = "entity"),
            @Result(column = "DYZZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GentInfo(@Param("quId") Integer quId);

    //查询一个镇的信息
    @Select("select * from dangyuanzhuanzheng where DYZZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId})")
    @Results(value = {
            @Result(column = "DYZZ_Id", property = "id"),
            @Result(column = "DYZZ_Name", property = "name"),
            @Result(column = "DYZZ_Native", property = "minzu"),
            @Result(column = "DYZZ_Time", property = "time"),
            @Result(column = "DYZZ_WenHua", property = "wenhua"),
            @Result(column = "DYZZ_Village", property = "villageId"),
            @Result(column = "DYZZ_Zu", property = "zuId"),
            @Result(column = "DYZZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "DYZZ_DanWei", property = "danwei"),
            @Result(column = "DYZZ_ZhiWu", property = "zhiwu"),
            @Result(column = "DYZZ_Sex", property = "sex"),
            @Result(column = "DYZZ_Phone", property = "phone"),
            @Result(column = "DYZZ_Year", property = "year"),
            @Result(column = "DYZZ_Entity", property = "entity"),
            @Result(column = "DYZZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByZhen(@Param("zhenId") Integer zhenId);

    //查询一个村的信息
    @Select("select * from dangyuanzhuanzheng where DYZZ_Village=#{cunId} ")
    @Results(value = {
            @Result(column = "DYZZ_Id", property = "id"),
            @Result(column = "DYZZ_Name", property = "name"),
            @Result(column = "DYZZ_Native", property = "minzu"),
            @Result(column = "DYZZ_Time", property = "time"),
            @Result(column = "DYZZ_WenHua", property = "wenhua"),
            @Result(column = "DYZZ_Village", property = "villageId"),
            @Result(column = "DYZZ_Zu", property = "zuId"),
            @Result(column = "DYZZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "DYZZ_DanWei", property = "danwei"),
            @Result(column = "DYZZ_ZhiWu", property = "zhiwu"),
            @Result(column = "DYZZ_Sex", property = "sex"),
            @Result(column = "DYZZ_Phone", property = "phone"),
            @Result(column = "DYZZ_Year", property = "year"),
            @Result(column = "DYZZ_Entity", property = "entity"),
            @Result(column = "DYZZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByCun(@Param("cunId") Integer cunId);

    @Select("select * from dangyuanzhuanzheng where DYZZ_Time=#{time}")
    @Results(value = {
            @Result(column = "DYZZ_Id", property = "id"),
            @Result(column = "DYZZ_Name", property = "name"),
            @Result(column = "DYZZ_Native", property = "minzu"),
            @Result(column = "DYZZ_Time", property = "time"),
            @Result(column = "DYZZ_WenHua", property = "wenhua"),
            @Result(column = "DYZZ_Village", property = "villageId"),
            @Result(column = "DYZZ_Zu", property = "zuId"),
            @Result(column = "DYZZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "DYZZ_DanWei", property = "danwei"),
            @Result(column = "DYZZ_ZhiWu", property = "zhiwu"),
            @Result(column = "DYZZ_Sex", property = "sex"),
            @Result(column = "DYZZ_Phone", property = "phone"),
            @Result(column = "DYZZ_Year", property = "year"),
            @Result(column = "DYZZ_Entity", property = "entity"),
            @Result(column = "DYZZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByTime(@Param("time") Date time);

    @Select("select * from dangyuanzhuanzheng where DYZZ_Entity=#{entity}")
    Page<Zuzifazhan> GetInfoByEntity(@Param("entity") Integer entity);

    @Select("select * from dangyuanzhuanzheng where DYZZ_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})")
    Page<Dangyuanzhuanzheng> pageFindDangyuanzhuanzhengByZId(Integer zId);

    @Select("select * from dangyuanzhuanzheng where DYZZ_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})and  DYZZ_Village =#{vId}")
    Page<Dangyuanzhuanzheng> pageFindDangyuanzhuanzhengByVIdandZId(@Param("zId") Integer zId, @Param("vId") Integer vId);

    @Select("select * from dangyuanzhuanzheng where DYZZ_Village  in(select Village_Id from village where V_Zhen_Xiang=#{zId})and DYZZ_Village =#{vId}and DYZZ_Time=#{dyzzTime}")
    Page<Dangyuanzhuanzheng> pageFindDangyuanzhuanzhengByVIdandZIdandDyzzTime(@Param("zId") Integer zId, @Param("vId") Integer vId, @Param("dyzzTime") String dyzzTime);

    //查询所有党员
    @Select("select * from dangyuanzhuanzheng ")
    Page<Dangyuanzhuanzheng> selectAlldangyuanzhuanzheng();

    //根据id删除一条数
    @Delete("delete from dangyuanzhuanzheng where  DYZZ_Id=#{dyzzId}")
    int DeleteDangyuanzhuanzheng(Integer dyzzId);

    //同时删除多条数据
    @Delete("delete from dangyuanzhuanzheng where   DYZZ_Id=#{dyzzId} ")
    int DeleteDuoTiaoDangyuanzhuanzheng(Integer[] dyzzId);

    //插入
    @Insert("insert into dangyuanzhuanzheng ( DYZZ_Name, DYZZ_Native, DYZZ_Time, DYZZ_Wenhua, DYZZ_Village, DYZZ_Zu,DYZZ_Entity, DYZZ_ShengFengZheng, DYZZ_Danwei, DYZZ_Zhiwu, DYZZ_Sex, DYZZ_Phone, DYZZ_Year,DYZZ_Xuhao)values(#{dyzzName},#{dyzzNative},#{dyzzTime},#{dyzzWenhua},#{dyzzVillage},#{dyzzZu},#{dyzzEntity},#{dyzzShengfengzheng},#{dyzzDanwei},#{dyzzZhiwu},#{dyzzSex},#{dyzzPhone},#{dyzzYear},#{dyzzXuhao}) ")
    int Insertdangyuanzhuanzheng(Dangyuanzhuanzheng dangyuanzhuanzheng);

    //根据id查询
    @Select("select * from dangyuanzhuanzheng where  DYZZ_Id=#{dyzzId} ")
    Dangyuanzhuanzheng queryAll(Integer dyzzId);

    //根据id修改
    @Update("update dangyuanzhuanzheng set DYZZ_Name=#{dyzzName}, DYZZ_Native=#{dyzzNative}, DYZZ_Time=#{dyzzTime}, DYZZ_Wenhua=#{dyzzWenhua}, DYZZ_Village=#{dyzzVillage}, DYZZ_Zu=#{dyzzZu}, DYZZ_ShengFengZheng=#{dyzzShengfengzheng}, DYZZ_Danwei=#{dyzzDanwei}, DYZZ_Zhiwu=#{dyzzZhiwu}, DYZZ_Sex=#{dyzzSex}, DYZZ_Phone=#{dyzzPhone}, DYZZ_Year=#{dyzzYear},DYZZ_Entity=#{dyzzEntity} where  DYZZ_Id=#{dyzzId}")
    int UpdateDangyuanzhuanzheng(Dangyuanzhuanzheng dangyuanzhuanzheng);

    //根据序号查询党员
    @Select("Select * from dangyuanzhuanzheng where DYZZ_xuhao=#{xuhao}")
    Dangyuanzhuanzheng findDangyuanzhuanzhengByxuhao(String xuhao);

    @Select("Select count(*) from dangyuanzhuanzheng where   DYZZ_Village in(select Village_Id from village where V_Region=#{quId})and  DYZZ_Time between #{time1} and #{time2}")
    Integer countByQu(@Param("quId") Integer quId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select count(*) from dangyuanzhuanzheng where   DYZZ_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})and DYZZ_Time between #{time1} and #{time2}")
    Integer countByZhen(@Param("zId") Integer zId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select * from dangyuanzhuanzheng where   DYZZ_Village=#{villageId} and  DYZZ_Time between #{time1} and #{time2}")
    @Results(value = {
            @Result(column = "DYZZ_Id", property = "id"),
            @Result(column = "DYZZ_Name", property = "name"),
            @Result(column = "DYZZ_Native", property = "minzu"),
            @Result(column = "DYZZ_Time", property = "time"),
            @Result(column = "DYZZ_WenHua", property = "wenhua"),
            @Result(column = "DYZZ_Village", property = "villageId"),
            @Result(column = "DYZZ_Zu", property = "zuId"),
            @Result(column = "DYZZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "DYZZ_DanWei", property = "danwei"),
            @Result(column = "DYZZ_ZhiWu", property = "zhiwu"),
            @Result(column = "DYZZ_Sex", property = "sex"),
            @Result(column = "DYZZ_Phone", property = "phone"),
            @Result(column = "DYZZ_Year", property = "year"),
            @Result(column = "DYZZ_Entity", property = "entity"),
            @Result(column = "DYZZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> findByCun(@Param("villageId") Integer villageId, @Param("time1") String time1, @Param("time2") String time2);
}