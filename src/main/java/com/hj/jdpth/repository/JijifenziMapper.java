package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component(value = "jijifenziMapper")
public interface JijifenziMapper {


    //查询全部信息
    @Select("select * from jijifenzi where JJFZ_Village in(select Village_Id from village where V_Region=#{quId})")
    @Results(value = {
            @Result(column = "JJFZ_Id", property = "id"),
            @Result(column = "JJFZ_Name", property = "name"),
            @Result(column = "JJFZ_Native", property = "minzu"),
            @Result(column = "JJFZ_Time", property = "time"),
            @Result(column = "JJFZ_WenHua", property = "wenhua"),
            @Result(column = "JJFZ_Village", property = "villageId"),
            @Result(column = "JJFZ_Zu", property = "zuId"),
            @Result(column = "JJFZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "JJFZ_DanWei", property = "danwei"),
            @Result(column = "JJFZ_ZhiWu", property = "zhiwu"),
            @Result(column = "JJFZ_Sex", property = "sex"),
            @Result(column = "JJFZ_Phone", property = "phone"),
            @Result(column = "JJFZ_Year", property = "year"),
            @Result(column = "JJFZ_Entity", property = "entity"),
            @Result(column = "JJFZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GentInfo(@Param("quId") Integer quId);

    //查询一个镇的信息
    @Select("select * from jijifenzi where JJFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId})")
    @Results(value = {
            @Result(column = "JJFZ_Id", property = "id"),
            @Result(column = "JJFZ_Name", property = "name"),
            @Result(column = "JJFZ_Native", property = "minzu"),
            @Result(column = "JJFZ_Time", property = "time"),
            @Result(column = "JJFZ_WenHua", property = "wenhua"),
            @Result(column = "JJFZ_Village", property = "villageId"),
            @Result(column = "JJFZ_Zu", property = "zuId"),
            @Result(column = "JJFZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "JJFZ_DanWei", property = "danwei"),
            @Result(column = "JJFZ_ZhiWu", property = "zhiwu"),
            @Result(column = "JJFZ_Sex", property = "sex"),
            @Result(column = "JJFZ_Phone", property = "phone"),
            @Result(column = "JJFZ_Year", property = "year"),
            @Result(column = "JJFZ_Entity", property = "entity"),
            @Result(column = "JJFZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByZhen(@Param("zhenId") Integer zhenId);

    //查询一个村的信息
    @Select("select * from jijifenzi where JJFZ_Village=#{cunId} ")
    @Results(value = {
            @Result(column = "JJFZ_Id", property = "id"),
            @Result(column = "JJFZ_Name", property = "name"),
            @Result(column = "JJFZ_Native", property = "minzu"),
            @Result(column = "JJFZ_Time", property = "time"),
            @Result(column = "JJFZ_WenHua", property = "wenhua"),
            @Result(column = "JJFZ_Village", property = "villageId"),
            @Result(column = "JJFZ_Zu", property = "zuId"),
            @Result(column = "JJFZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "JJFZ_DanWei", property = "danwei"),
            @Result(column = "JJFZ_ZhiWu", property = "zhiwu"),
            @Result(column = "JJFZ_Sex", property = "sex"),
            @Result(column = "JJFZ_Phone", property = "phone"),
            @Result(column = "JJFZ_Year", property = "year"),
            @Result(column = "JJFZ_Entity", property = "entity"),
            @Result(column = "JJFZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByCun(@Param("cunId") Integer cunId);

    @Select("select * from jijifenzi where JJFZ_Time=#{time}")
    @Results(value = {
            @Result(column = "JJFZ_Id", property = "id"),
            @Result(column = "JJFZ_Name", property = "name"),
            @Result(column = "JJFZ_Native", property = "minzu"),
            @Result(column = "JJFZ_Time", property = "time"),
            @Result(column = "JJFZ_WenHua", property = "wenhua"),
            @Result(column = "JJFZ_Village", property = "villageId"),
            @Result(column = "JJFZ_Zu", property = "zuId"),
            @Result(column = "JJFZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "JJFZ_DanWei", property = "danwei"),
            @Result(column = "JJFZ_ZhiWu", property = "zhiwu"),
            @Result(column = "JJFZ_Sex", property = "sex"),
            @Result(column = "JJFZ_Phone", property = "phone"),
            @Result(column = "JJFZ_Year", property = "year"),
            @Result(column = "JJFZ_Entity", property = "entity"),
            @Result(column = "JJFZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> GetInfoByTime(@Param("time") Date time);

    @Select("select * from jijifenzi where JJFZ_Entity=#{entity}")
    Page<Zuzifazhan> GetInfoByEntity(@Param("entity") Integer entity);

    @Select("select * from jijifenzi  where JJFZ_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})")
    Page<Jijifenzi> pageFindJijifenziByZId(Integer zId);

    @Select("select * from jijifenzi where JJFZ_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})and JJFZ_Village =#{vId}")
    Page<Jijifenzi> pageFindJijifenziByZIdandVId(@Param("zId") Integer zId, @Param("vId") Integer vId);

    @Select("select * from jijifenzi where JJFZ_Village in(select Village_Id from village where V_Zhen_Xiang=#{zId})and JJFZ_Village =#{vId} and JJFZ_Time=#{jjfzTime}")
    Page<Jijifenzi> pageFindJijifenziByZIdandVIdandJjfzTime(@Param("zId") Integer zId, @Param("vId") Integer vId, @Param("jjfzTime") String jjfzTime);

    //查询所有积极分子
    @Select("select * from jijifenzi ")
    Page<Jijifenzi> selectAllJijifenzi();

    //根据id删除一条数
    @Delete("delete from jijifenzi where JJFZ_Id=#{jjfzId}")
    int DeleteJijifenzi(Integer jjfzId);

    //同时删除多条数据
    @Delete("delete from jijifenzi where  JJFZ_Id in(#{jjfzId}) ")
    int DeleteDuoTiaoJijifenzi(Integer[] jjfzId);

    //插入
    @Insert("insert into jijifenzi (Jjfz_Name,Jjfz_Native,Jjfz_Time,Jjfz_Wenhua,Jjfz_Village,Jjfz_Zu,Jjfz_Shenfenzheng,Jjfz_Danwei,Jjfz_Zhiwu,Jjfz_Sex,Jjfz_Phone,Jjfz_Year,JJFZ_Xuhao,JJFZ_Entity)values(#{jjfzName},#{jjfzNative},#{jjfzTime},#{jjfzWenhua},#{jjfzVillage},#{jjfzZu},#{jjfzShenfenzheng},#{jjfzDanwei},#{jjfzZhiwu},#{jjfzSex},#{jjfzPhone},#{jjfzYear},#{jjfzXuhao},#{jjfzEntity}) ")
    int InsertJijifenzi(Jijifenzi jijifenzi);

    //根据id查询
    @Select("select * from jijifenzi where  JJFZ_Id=#{jjfzId} ")
    Jijifenzi queryAll(Integer jjfzId);

    //根据id修改
    @Update("update jijifenzi set Jjfz_Name=#{jjfzName},Jjfz_Native=#{jjfzNative},Jjfz_Time=#{jjfzTime},Jjfz_Wenhua=#{jjfzWenhua},Jjfz_Shenfenzheng=#{jjfzShenfenzheng},Jjfz_Danwei=#{jjfzDanwei},Jjfz_Zhiwu=#{jjfzZhiwu},Jjfz_Sex=#{jjfzSex},Jjfz_Phone=#{jjfzPhone},Jjfz_Year=#{jjfzYear},JJFZ_Entity=#{jjfzEntity} where JJFZ_Id=#{jjfzId}")
    int UpdateJijifenzi(Jijifenzi jijifenzi);

    @Select("select * from zhen where Zhen_Id=#{zId}")
    Zhen GetZhen(@Param("zId") Integer zId);

    //根据村id查村对象
    @Select("select * from village where Village_Id=#{vId}")
    Village GetVillage(@Param("vId") Integer vId);

    //根据镇找村
    @Select("Select * from village where V_Zhen_xiang=#{zhenId}")
    List<Village> cun_List(Integer zhenId);

    //根据序号查询积极分子
    @Select("Select * from jijifenzi where JJFZ_xuhao=#{xuhao}")
    Jijifenzi findJijifenziByxuhao(String xuhao);

    //根据区id获取区
    @Select("Select * from region where R_Key=#{quId}")
    Region getRegion(Integer quId);

    @Select("Select V_Name from village where Village_Id=#{villageId}")
    String FindVillageById(Integer villageId);

    @Select("Select Z_Name from zu where Z_Key=#{zuId}")
    String FindZuById(Integer zuId);

    @Select("Select QKBGLX_Name from QingKuangBianGengLeiXing where QKBGLX_Id=#{qId}")
    String FindLeixinZuById(Integer qId);

    @Select("Select count(*) from jijifenzi where  JJFZ_Village in(select Village_Id from village where V_Region=#{quId})and JJFZ_Time between #{time1} and #{time2}")
    Integer countByQu(@Param("quId") Integer quId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select count(*) from jijifenzi where JJFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zId})")
    Integer countByZhen(@Param("zId") Integer zId);

    @Select("Select count(*) from jijifenzi where JJFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zId})and JJFZ_Time between #{time1} and #{time2}")
    Integer FindByZhen(@Param("zId") Integer zId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select * from jijifenzi where JJFZ_Village=#{vId}")
    @Results(value = {
            @Result(column = "JJFZ_Id", property = "id"),
            @Result(column = "JJFZ_Name", property = "name"),
            @Result(column = "JJFZ_Native", property = "minzu"),
            @Result(column = "JJFZ_Time", property = "time"),
            @Result(column = "JJFZ_WenHua", property = "wenhua"),
            @Result(column = "JJFZ_Village", property = "villageId"),
            @Result(column = "JJFZ_Zu", property = "zuId"),
            @Result(column = "JJFZ_shenfenzheng", property = "shenfenzheng"),
            @Result(column = "JJFZ_DanWei", property = "danwei"),
            @Result(column = "JJFZ_ZhiWu", property = "zhiwu"),
            @Result(column = "JJFZ_Sex", property = "sex"),
            @Result(column = "JJFZ_Phone", property = "phone"),
            @Result(column = "JJFZ_Year", property = "year"),
            @Result(column = "JJFZ_Entity", property = "entity"),
            @Result(column = "JJFZ_XuHao", property = "xuhao")
    })
    Page<Zuzifazhan> findByCun(@Param("vId") Integer vId);

    @Select("Select count(*) from partymemberinformation where PM_Village in(select Village_Id from village where V_Zhen_xiang=#{zId})and PMI_JoinTime between #{time1} and #{time2}")
    Integer findDangyaunByQu(@Param("zId") Integer zId, @Param("time1") String time1, @Param("time2") String time2);


    @Select("Select * from partymemberinformation where PM_Village=#{vId}")
    Page<Partymember> findDangyaunByCun(@Param("vId") Integer vId);

    @Select("Select count(*) from partymemberinformation where PM_Village in(select Village_Id from village where V_Zhen_xiang=#{zId})")
    Integer countdangyuanByZhen(@Param("zId") Integer zId);

    @Select("Select count(*) from partymemberinformation where PM_Village in(select Village_Id from village where V_Zhen_xiang=#{zId})and PMI_JoinTime between #{time1} and #{time2}")
    Integer FinddangyuanByZhen(@Param("zId") Integer zId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select Z_Name from zhen where Zhen_Id=#{zId}")
    List<String> findzName(@Param("zId") Integer zId);

    @Select("Select User_Name from yonghu where User_Id in(select PM_yonghu from partymemberinformation where PartyMemberInformation_Id=#{pId})")
    String findpName(@Param("pId") Integer pId);

    @Select("Select User_Sex from yonghu where User_Id in(select PM_yonghu from partymemberinformation where PartyMemberInformation_Id=#{pId})")
    String findpSex(@Param("pId") Integer pId);

    @Select("Select User_Nation from yonghu where User_Id in(select PM_yonghu from partymemberinformation where PartyMemberInformation_Id=#{pId})")
    Integer findpNation(@Param("pId") Integer pId);

    @Select("Select J_name from job where J_Key=#{jKey}")
    String zhiwu(Integer jKey);

    @Select("Select N_name from nation where N_Key=#{nKey}")
    String nation(Integer nKey);


}


