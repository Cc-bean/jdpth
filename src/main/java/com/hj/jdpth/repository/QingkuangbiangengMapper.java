package com.hj.jdpth.repository;


import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Qingkuangbiangeng;
import com.hj.jdpth.domain.Zuzifazhan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "qingkuangbiangengMapper")
public interface QingkuangbiangengMapper {

    //查询全部信息
    @Select("select * from qingkuangbiangeng where QKBG_Zu in(select Z_Key from zu where Z_village in(select Village_Id from village where V_Region=#{quId}))")
    Page<Qingkuangbiangeng> GentInfo(@Param("quId") Integer quId);

    //查询一个镇的信息
    @Select("select * from qingkuangbiangeng where QKBG_Zu in(select Z_Key from zu where Z_village in(select Village_Id from village where V_Zhen_Xiang=#{zId}))")
    Page<Qingkuangbiangeng> GetInfoByZhen(@Param("zId") Integer zId);

    //查询一个村的信息
    @Select("select * from qingkuangbiangeng where QKBG_Zu in(select Z_Key from zu where Z_village=#{cunId})")
    Page<Qingkuangbiangeng> GetInfoByCun(@Param("cunId") Integer cunId);

    @Select("select * from qingkuangbiangeng where QKBG_Time=#{time}")
    Page<Qingkuangbiangeng> GetInfoByTime(@Param("time") Date time);

    @Select("select * from qingkuangbiangeng where QKBG_Time=#{time}")
    Page<Zuzifazhan> GetInfoByEntity(@Param("entity") Integer entity);

    @Select("select * from qingkuangbiangeng where QKBG_Zu in(select Z_Key from zu where Z_village in(select Village_Id from village where V_Zhen_Xiang=#{zId}))")
    Page<Qingkuangbiangeng> pageFindQingkuangbiangengByZId(@Param("zId") Integer zId);

    @Select("select * from qingkuangbiangeng where QKBG_Zu in(select Z_Key from zu where Z_village in(select Village_Id from village where V_Zhen_Xiang=#{zId}))and QKBG_Zu in(select Z_Key from zu where Z_village=#{vId}) ")
    Page<Qingkuangbiangeng> pageFindQingkuangbiangengByZIdandVId(@Param("zId") Integer zId, @Param("vId") Integer vId);

    @Select("select * from qingkuangbiangeng where QKBG_Zu in(select Z_Key from zu where Z_village in(select Village_Id from village where V_Zhen_Xiang=#{zId}))and QKBG_Zu in(select Z_Key from zu where Z_village=#{vId})and where QKBG_Time=#{qkbgTime}")
    Page<Qingkuangbiangeng> pageFindQingkuangbiangengByZIdandVIdandTime(@Param("zId") Integer zId, @Param("vId") Integer vId, @Param("qkbgTime") String qkbgTime);

    @Select("select * from qingkuangbiangeng")
    Page<Qingkuangbiangeng> selectAllqingKuangBianGeng();

    @Select("select * from qingkuangbiangeng where QKBG_Id=#{qkbgId}")
    Qingkuangbiangeng queryAll(Integer qkbgId);

    @Delete("delete from qingkuangbiangeng where QKBG_Id=#{qkbgId}")
    int DeleteqingKuangBianGeng(Integer qkbgId);

    @Delete("delete from qingkuangbiangeng where QKBG_Id in(#{qkbgId})")
    int DeleteDuoTiaoqingKuangBianGeng(Integer[] qkbgId);

    @Insert("insert into qingkuangbiangeng(QKBG_Zu,QKBG_Leixing,QKBG_Phone,QKBG_Shengfenzheng,QKBG_Name,QKBG_Minzu,QKBG_Time,QKBG_Yuanyin,QKBG_Dizhi,QKBG_Year,QKBG_Xuhao) values(#{qkbgZu},#{qkbgLeixing},#{qkbgPhone},#{qkbgShengfenzheng},#{qkbgName},#{qkbgMinzu},#{qkbgTime},#{qkbgYuanyin},#{qkbgDizhi},#{qkbgYear},#{qkbgXuhao})")
    int InsertQingKuangBianGeng(Qingkuangbiangeng qingkuangbiangeng);

    @Update("update qingkuangbiangeng set QKBG_Zu=#{qkbgZu}, QKBG_Leixing=#{qkbgLeixing}, QKBG_Phone=#{qkbgPhone}, QKBG_Shengfenzheng=#{qkbgShengfenzheng}, QKBG_Name=#{qkbgName}, QKBG_Minzu=#{qkbgMinzu}, QKBG_Time=#{qkbgTime}, QKBG_Yuanyin=#{qkbgYuanyin}, QKBG_Dizhi=#{qkbgDizhi}, QKBG_Year=#{qkbgYear},QKBG_Xuhao=#{qkbgXuhao} where QKBG_Id=#{qkbgId}")
    int UpdateQingKuangBianGeng(Qingkuangbiangeng qingkuangbiangeng);

    //根据序号查询情况变更
    @Select("Select * from qingkuangbiangeng where  QKBG_xuhao=#{xuhao}")
    Qingkuangbiangeng findQingKuangBianGengByxuhao(String xuhao);


}