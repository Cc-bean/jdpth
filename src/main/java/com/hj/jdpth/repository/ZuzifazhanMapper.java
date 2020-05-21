package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "zuzifazhanMapper")
public interface ZuzifazhanMapper {

    //查询所有
    @Select("select * from zuzifazhan ")
    Page<Zuzifazhan> GentInfo();

    //根据区查询
    @Select("select * from zuzifazhan where ZZFZ_Village in(select Village_Id from village where V_Region=#{quId})")
    Page<Zuzifazhan> GentInfoByQu(@Param("quId") Integer quId);

    //根据镇查询
    @Select("select * from zuzifazhan where ZZFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId})")
    Page<Zuzifazhan> GetInfoByZhen(@Param("zhenId") Integer zhenId);

    //根据村查询
    @Select("select * from zuzifazhan where ZZFZ_Village=#{cunId} ")
    Page<Zuzifazhan> GetInfoByCun(@Param("cunId") Integer cunId);

    //根据entity查询
    @Select("select * from zuzifazhan where ZZFZ_Entity=#{entity}")
    Page<Zuzifazhan> GetInfoByEntity(@Param("entity") String entity);

    //根据村id查村名
    @Select("Select V_Name from village where Village_Id=#{villageId}")
    String GetVillageById(Integer villageId);

    //根据组id查组名
    @Select("Select Z_Name from zu where Z_Key=#{zuId}")
    String GetZuById(Integer zuId);

    //根据名字查询
    @Select("Select * from zuzifazhan where ZZFZ_Name like '%${value}%'")
    Page<Zuzifazhan> GetByName(String value);

    //新增
    @Insert("insert into zuzifazhan (ZZFZ_Name,ZZFZ_Native,JJFZ_Time,FZDX_Time,YBDY_Time,ZSDY_Time,ZZFZ_Wenhua,ZZFZ_Village,ZZFZ_Zu,ZZFZ_Shenfenzheng,ZZFZ_DanWei,ZZFZ_ZhiWu,ZZFZ_Sex,ZZFZ_Phone,ZZFZ_XuHao,ZZFZ_Entity,ZZFZ_BeiYong1,ZZFZ_BeiYong2,ZZFZ_BeiYong3,ZZFZ_Address)values" +
            "(#{zzfzName},#{zzfzNative},#{jjfzTime},#{fzdxTime},#{ybdyTime},#{zsdyTime},#{zzfzWenHua},#{zzfzVillage},#{zzfzZu},#{zzfzShenfenzheng},#{zzfzDanWei},#{zzfzZhiWu},#{zzfzSex},#{zzfzPhone},#{zzfzXuHao},#{zzfzEntity},#{BeiYong1},#{BeiYong2},#{BeiYong3},#{zzfzAddress}) ")
    int InsertZuzifazhan(Zuzifazhan zuzifazhan);

    //根据id修改
    @Update("update zuzifazhan set ZZFZ_Name=#{zzfzName},ZZFZ_Native=#{zzfzNative},JJFZ_Time=#{jjfzTime},FZDX_Time=#{fzdxTime},YBDY_Time=#{ybdyTime},zsdy_Time=#{zsdyTime},ZZFZ_Wenhua=#{zzfzWenHua},ZZFZ_Village=#{zzfzVillage}," +
            "ZZFZ_Shenfenzheng=#{zzfzShenfenzheng},ZZFZ_DanWei=#{zzfzDanWei},ZZFZ_ZhiWu=#{zzfzZhiWu},ZZFZ_Sex=#{zzfzSex},ZZFZ_Phone=#{zzfzPhone},ZZFZ_Entity=#{zzfzEntity},ZZFZ_XuHao=#{zzfzXuHao},ZZFZ_BeiYong1=#{BeiYong1},ZZFZ_BeiYong2=#{BeiYong2},ZZFZ_BeiYong3=#{BeiYong3},ZZFZ_Address=#{zzfzAddress} where ZZFZ_Id=#{zzfzId}")
    int UpdateZuzifazhan(Zuzifazhan Zuzifazhan);

    //根据id查询信息
    @Select("Select * from zuzifazhan where ZZFZ_Id=#{zzfzId}")
    Zuzifazhan GetById(Integer zzfzId);

    //根据id删除
    @Delete("Delete from zuzifazhan where ZZFZ_Id=#{zzfzId}")
    int Delete(Integer zzfzId);

    //根据序号查询
    @Select("Select * from zuzifazhan where ZZFZ_XuHao=#{xuhao}")
    Zuzifazhan GetByXuHao(String xuhao);

    //根据名字和身份查询
    @Select("Select * from zuzifazhan where ZZFZ_Name like '%${value}%'and ZZFZ_Entity=#{entity}")
    Page<Zuzifazhan> GetByNameAndEntity(@Param("value") String value, @Param("entity") String entity);

    //根据名字和村查询
    @Select("Select * from zuzifazhan where ZZFZ_Name like '%${value}%'and ZZFZ_Village=#{vId}")
    Page<Zuzifazhan> GetByNameAndvId(@Param("value") String value, @Param("vId") Integer vId);

    //根据身份和村查询
    @Select("Select * from zuzifazhan where ZZFZ_Village=#{vId} and ZZFZ_Entity=#{entity}")
    Page<Zuzifazhan> GetByEntityAndvId(@Param("vId") Integer vId, @Param("entity") String entity);

    //根据身份和村查询和名字查询
    @Select("Select * from zuzifazhan where ZZFZ_Name like '%${value}%'and ZZFZ_Village=#{vId} and ZZFZ_Entity=#{entity}")
    Page<Zuzifazhan> GetByEntityAndvIdandName(@Param("value") String value, @Param("vId") Integer vId, @Param("entity") String entity);

    //根据名字和镇查询
    @Select("Select * from zuzifazhan where ZZFZ_Name like '%${value}%'and ZZFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId})")
    Page<Zuzifazhan> GetByNameAndzId(@Param("value") String value, @Param("zhenId") Integer zhenId);

    //根据身份和镇查询
    @Select("Select * from zuzifazhan where ZZFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId}) and ZZFZ_Entity=#{entity}")
    Page<Zuzifazhan> GetByEntityAndzId(@Param("zhenId") Integer zhenId, @Param("entity") String entity);

    //根据身份和镇查询和名字查询
    @Select("Select * from zuzifazhan where ZZFZ_Name like '%${value}%'and ZZFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId}) and ZZFZ_Entity=#{entity}")
    Page<Zuzifazhan> GetByEntityAndzIdandName(@Param("value") String value, @Param("zhenId") Integer zhenId, @Param("entity") String entity);

    //根据镇id查镇名
    @Select("Select Z_Name from zhen where Zhen_Id=#{zId}")
    List<String> GetzName(@Param("zId") Integer zId);

    @Select("Select count(*) from zuzifazhan where ZZFZ_Entity=#{entity} and ZZFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId})")
    Integer CountByEntityAndzId(@Param("entity") String entity, @Param("zhenId") Integer zhenId);

    @Select("Select count(*) from zuzifazhan where ZZFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId}) and JJFZ_Time between #{time1} and #{time2}")
    Integer FindByZhen1(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select count(*) from zuzifazhan where ZZFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId}) and FZDX_Time between #{time1} and #{time2}")
    Integer FindByZhen2(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select count(*) from zuzifazhan where ZZFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId}) and YBDY_Time between #{time1} and #{time2}")
    Integer FindByZhen3(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select count(*) from zuzifazhan where ZZFZ_Village in(select Village_Id from village where V_Zhen_xiang=#{zhenId}) and ZSDY_Time between #{time1} and #{time2}")
    Integer FindByZhen4(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("Select * from zuzifazhan where ZZFZ_Village=#{vId} and ZZFZ_Entity=#{entity}")
    Page<Zuzifazhan> CountByCun(@Param("vId") Integer vId, @Param("entity") String entity);

    @Select("Select * from zuzifazhan where ZZFZ_Id=#{Id} and ZZFZ_Entity=#{entity}")
    Zuzifazhan CountByIdAndEntity(@Param("Id") Integer Id, @Param("entity") String entity);

    //根据镇找村
    @Select("Select * from village where V_Zhen_xiang=#{zhenId}")
    List<Village> cun_List(Integer zhenId);

    @Select("Select Z_Name from zhen where Zhen_Id=#{zId}")
    List<String> findzName(@Param("zId") Integer zId);


    @Select("Select N_Key from nation where N_name =#{value}")
    Integer nation(String value);

    @SelectProvider(type = ZuzifazhanProvider.class, method = "get")
//value是用户名字
    Page<Zuzifazhan> queryAll_yyq(@Param("quId") Integer quId, @Param("zId") Integer zId, @Param("vId") Integer vId, @Param("value") String value, @Param("entity") String entity);

    class ZuzifazhanProvider {
        public String get(@Param("quId") Integer quId, @Param("zId") Integer zId, @Param("vId") Integer vId, @Param("value") String value, @Param("entity") String entity) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("zuzifazhan");
            if (quId != 0 && zId == 0 && vId == 0) {
                sql.AND();
                sql.INNER_JOIN("village on zuzifazhan.ZZFZ_Village=village.Village_Id");
                sql.INNER_JOIN("zhen on village.V_Zhen_xiang=zhen.Zhen_Id");
                sql.WHERE("zhen.Z_region=#{quId}");
                //sql.WHERE("PM_Village in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qu_id}))");
            }
            if (quId != 0 && zId != 0 && vId == 0) {
                sql.AND();
                sql.INNER_JOIN("village on zuzifazhan.ZZFZ_Village=village.Village_Id");
                sql.WHERE("village.V_Zhen_xiang=#{zId}");
                //sql.WHERE("PM_Village in (select Village_Id from village where V_Zhen_xiang=#{zhen_id})");
            }
            if (quId != 0 && zId != 0 && vId != 0) {
                sql.AND();
                sql.WHERE("ZZFZ_Village=#{vId}");
            }
            if (value != null) {
                sql.AND();
                sql.WHERE("ZZFZ_Name like '%${value}%'");
            }
            if (entity != null) {
                sql.AND();
                sql.WHERE("ZZFZ_Entity=#{entity}");
            }
            return sql.toString();
        }
    }
}

