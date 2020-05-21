package com.hj.jdpth.repository;


import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Fazhanduixiang;
import com.hj.jdpth.domain.Zuzifazhan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "fazhanduixiangMapper")
public interface FazhanduixiangMapper {


    //查询全部信息
    @Select("select * from fazhanduixiang where FZDX_Village in(select Village_Id from village where V_Region=#{quId})")
    @Results(value = {
            @Result(column = "FZDX_Id", property = "id"),
            @Result(column = "FZDX_Name", property = "name"),
            @Result(column = "FZDX_Native", property = "minzu"),
            @Result(column = "FZDX_Time", property = "time"),
            @Result(column = "FZDX_WenHua", property = "wenhua"),
            @Result(column = "FZDX_Village", property = "villageId"),
            @Result(column = "FZDX_Zu", property = "zuId"),
            @Result(column = "FZDX_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "FZDX_DanWei", property = "danwei"),
            @Result(column = "FZDX_ZhiWu", property = "zhiwu"),
            @Result(column = "FZDX_Sex", property = "sex"),
            @Result(column = "FZDX_Phone", property = "phone"),
            @Result(column = "FZDX_Year", property = "year"),
            @Result(column = "FZDX_Entity", property = "entity"),
            @Result(column = "FZDX_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GentInfo(@Param("quId") Integer quId);

    //查询一个镇的信息
    @Select("select * from fazhanduixiang where FZDX_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId})")
    @Results(value = {
            @Result(column = "FZDX_Id", property = "id"),
            @Result(column = "FZDX_Name", property = "name"),
            @Result(column = "FZDX_Native", property = "minzu"),
            @Result(column = "FZDX_Time", property = "time"),
            @Result(column = "FZDX_WenHua", property = "wenhua"),
            @Result(column = "FZDX_Village", property = "villageId"),
            @Result(column = "FZDX_Zu", property = "zuId"),
            @Result(column = "FZDX_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "FZDX_DanWei", property = "danwei"),
            @Result(column = "FZDX_ZhiWu", property = "zhiwu"),
            @Result(column = "FZDX_Sex", property = "sex"),
            @Result(column = "FZDX_Phone", property = "phone"),
            @Result(column = "FZDX_Year", property = "year"),
            @Result(column = "FZDX_Entity", property = "entity"),
            @Result(column = "FZDX_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByZhen(@Param("zhenId") Integer zhenId);

    //查询一个村的信息
    @Select("select * from fazhanduixiang where FZDX_Village=#{cunId} ")
    @Results(value = {
            @Result(column = "FZDX_Id", property = "id"),
            @Result(column = "FZDX_Name", property = "name"),
            @Result(column = "FZDX_Native", property = "minzu"),
            @Result(column = "FZDX_Time", property = "time"),
            @Result(column = "FZDX_WenHua", property = "wenhua"),
            @Result(column = "FZDX_Village", property = "villageId"),
            @Result(column = "FZDX_Zu", property = "zuId"),
            @Result(column = "FZDX_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "FZDX_DanWei", property = "danwei"),
            @Result(column = "FZDX_ZhiWu", property = "zhiwu"),
            @Result(column = "FZDX_Sex", property = "sex"),
            @Result(column = "FZDX_Phone", property = "phone"),
            @Result(column = "FZDX_Year", property = "year"),
            @Result(column = "FZDX_Entity", property = "entity"),
            @Result(column = "FZDX_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByCun(@Param("cunId") Integer cunId);

    @Select("select * from fazhanduixiang where FZDX_Time=#{time}")
    @Results(value = {
            @Result(column = "FZDX_Id", property = "id"),
            @Result(column = "FZDX_Name", property = "name"),
            @Result(column = "FZDX_Native", property = "minzu"),
            @Result(column = "FZDX_Time", property = "time"),
            @Result(column = "FZDX_WenHua", property = "wenhua"),
            @Result(column = "FZDX_Village", property = "villageId"),
            @Result(column = "FZDX_Zu", property = "zuId"),
            @Result(column = "FZDX_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "FZDX_DanWei", property = "danwei"),
            @Result(column = "FZDX_ZhiWu", property = "zhiwu"),
            @Result(column = "FZDX_Sex", property = "sex"),
            @Result(column = "FZDX_Phone", property = "phone"),
            @Result(column = "FZDX_Year", property = "year"),
            @Result(column = "FZDX_Entity", property = "entity"),
            @Result(column = "FZDX_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByTime(@Param("time") Date time);

    @Select("select * from fazhanduixiang where FZDX_Entity=#{entity}")
    Page<Zuzifazhan> GetInfoByEntity(@Param("entity") Integer entity);

    @Select("select * from fazhanduixiang where FZDX_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})")
    Page<Fazhanduixiang> pageFindFazhanduixiangByZId(Integer zId);

    @Select("select * from fazhanduixiang where FZDX_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})and FZDX_Village =#{vId}")
    Page<Fazhanduixiang> pageFindFazhanduixiangByVIdandZId(@Param("zId") Integer zId, @Param("vId") Integer vId);

    @Select("select * from fazhanduixiang where FZDX_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})and FZDX_Village =#{vId}and FZDX_Time=#{fzdxTime}")
    Page<Fazhanduixiang> pageFindFazhanduixiangByVIdandZIdandFzdxTime(@Param("zId") Integer zId, @Param("vId") Integer vId, @Param("fzdxTime") String fzdxTime);

    //查询所有发展对象
    @Select("select * from fazhanduixiang ")
    Page<Fazhanduixiang> selectAllFazhanduixiang();

    //根据id删除一条数据
    @Delete("delete from fazhanduixiang where FZDX_Id=#{fzdxId}")
    int DeleteFazhanduixiang(Integer fzdxId);

    //同时删除多条数据
    @Delete("delete from fazhanduixiang where FZDX_Id=#{fzdxId}")
    int DeleteDuoTiaoFazhanduixiang(Integer[] fzdxId);

    //插入
    @Insert("insert into fazhanduixiang (FZDX_Name,FZDX_Native,FZDX_Time,FZDX_Wenhua,FZDX_Village,FZDX_Zu,FZDX_Shenfenzheng,FZDX_Danwei,FZDX_Zhiwu,FZDX_Sex,FZDX_Phone,FZDX_Year,FZDX_Xuhao,FZDX_Entity)values(#{fzdxName},#{fzdxNative},#{fzdxTime},#{fzdxWenhua},#{fzdxVillage},#{fzdxZu},#{fzdxShenfenzheng},#{fzdxDanwei},#{fzdxZhiwu},#{fzdxSex},#{fzdxPhone},#{fzdxYear},#{fzdxXuhao},#{fzdxEntity}) ")
    int InsertFazhanduixiang(Fazhanduixiang fazhanduixiang);

    //根据id查询
    @Select("select * from fazhanduixiang where  FZDX_Id =#{fzdxId}")
    Fazhanduixiang queryAll(Integer fzdxId);

    //根据id修改
    @Update("update fazhanduixiang set FZDX_Name=#{fzdxName},FZDX_Native=#{fzdxNative},FZDX_Time=#{fzdxTime},FZDX_Wenhua=#{fzdxWenhua},FZDX_Village=#{fzdxVillage},FZDX_Zu=#{fzdxZu},FZDX_Shenfenzheng=#{fzdxShenfenzheng},FZDX_Danwei=#{fzdxDanwei},FZDX_Zhiwu=#{fzdxZhiwu},FZDX_Sex=#{fzdxSex},FZDX_Phone=#{fzdxPhone},FZDX_Year=#{fzdxYear},FZDX_Entity=#{fzdxEntity} where FZDX_Id=#{fzdxId}")
    int UpdateFazhanduixiang(Fazhanduixiang fazhanduixiang);

    //根据序号查询积极分子
    @Select("Select * from fazhanduixiang where FZDX_xuhao=#{xuhao}")
    Fazhanduixiang findFazhanduixiangByxuhao(String xuhao);

    @Select("Select count(*) from fazhanduixiang where  FZDX_Village in(select Village_Id from village where V_Region=#{quId})and  FZDX_Time between #{time1} and #{time2}")
    Integer countByQu(@Param("quId") Integer quId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select count(*) from fazhanduixiang where FZDX_Village in(select Village_Id from village where V_Zhen_xiang=#{zId})")
    Integer countByZhen(@Param("zId") Integer zId);

    @Select("Select count(*) from fazhanduixiang where FZDX_Village in(select Village_Id from village where V_Zhen_xiang=#{zId})and FZDX_Time between #{time1} and #{time2}")
    Integer FindByZhen(@Param("zId") Integer zId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select * from fazhanduixiang where  FZDX_Village=#{vId}")
    @Results(value = {
            @Result(column = "FZDX_Id", property = "id"),
            @Result(column = "FZDX_Name", property = "name"),
            @Result(column = "FZDX_Native", property = "minzu"),
            @Result(column = "FZDX_Time", property = "time"),
            @Result(column = "FZDX_WenHua", property = "wenhua"),
            @Result(column = "FZDX_Village", property = "villageId"),
            @Result(column = "FZDX_Zu", property = "zuId"),
            @Result(column = "FZDX_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "FZDX_DanWei", property = "danwei"),
            @Result(column = "FZDX_ZhiWu", property = "zhiwu"),
            @Result(column = "FZDX_Sex", property = "sex"),
            @Result(column = "FZDX_Phone", property = "phone"),
            @Result(column = "FZDX_Year", property = "year"),
            @Result(column = "FZDX_Entity", property = "entity"),
            @Result(column = "FZDX_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> findByCun(@Param("vId") Integer vId);
}