package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Hukouqianyi;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;


@Component(value = "hukouqianyiMapper")
public interface HukouqianyiMapper {

    @Select("select * from hukouqianyi")
    Page<Hukouqianyi> FindByQu();

    @Select("select * from hukouqianyi where HKQY_Village=#{vid}")
    Page<Hukouqianyi> FindByVid(Integer vid);

    @SelectProvider(type = HukouqiyiProvider.class, method = "getPartmember")
//value是时间
    Page<Hukouqianyi> Search(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid, @Param("qyType") Integer qyType, @Param("value") String value);

    class HukouqiyiProvider {
        public String getPartmember(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid, @Param("qyType") Integer qyType, @Param("value") String value) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("hukouqianyi");
            if (qid != 0 && zhenid == 0 && vid == 0) {
                sql.AND();
                sql.WHERE("HKQY_Village in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qid}))");
            }
            if (qid != 0 && zhenid != 0 && vid == 0) {
                sql.AND();
                sql.WHERE("HKQY_Village in (select Village_Id from village where V_Zhen_xiang=#{zhenid})");
            }
            if (qid != 0 && zhenid != 0 && vid != 0) {
                sql.AND();
                sql.WHERE("HKQY_Village=#{vid}");
            }
            if (!value.equals("0")) {
                sql.AND();
                sql.WHERE("HKQY_Time like '${value}%'");
            }
            if (qyType == 1) {
                sql.AND();
                sql.WHERE("HKQY_LeiXing=1");
            }
            if (qyType == 2) {
                sql.AND();
                sql.WHERE("HKQY_LeiXing=2");
            }
            return sql.toString();
        }
    }

    @Select("select * from hukouqianyi where HKQY_Id=#{id}")
    Hukouqianyi FindById(Integer id);

    @Delete("delete from hukouqianyi where HKQY_Id=#{id}")
    boolean DeleteHKQY(Integer id);

    @Insert("insert into hukouqianyi (HKQY_Name,HKQY_Sex,HKQY_Phone,HKQY_Entity,HKQY_Village,HKQY_Zu,HKQY_MinZu,HKQY_Time,HKQY_DiZhi,HKQY_ZhengMing,HKQY_LeiXing,HKQY_WenHuaChengDu,HKQY_Beiyong1) values(#{hkqyName},#{hkqySex},#{hkqyPhone},#{hkqyEntity},#{hkqyVillage},#{hkqyZu},#{hkqyMinzu},#{hkqyTime},#{hkqyDizhi},#{hkqyZhengming},#{hkqyLeixing},#{hkqyWenhuachengdu},#{hkqyBeiyong1})")
    boolean HkqyInsert(Hukouqianyi hukouqianyi);

    @Update("update hukouqianyi set HKQY_Name=#{hkqyName},HKQY_Sex=#{hkqySex},HKQY_Phone=#{hkqyPhone},HKQY_Entity=#{hkqyEntity},HKQY_Village=#{hkqyVillage},HKQY_Zu=#{hkqyZu},HKQY_MinZu=#{hkqyMinzu},HKQY_Time=#{hkqyTime},HKQY_DiZhi=#{hkqyDizhi},HKQY_ZhengMing=#{hkqyZhengming},HKQY_LeiXing=#{hkqyLeixing} where HKQY_Id=#{hkqyId}")
    boolean HkqyUpdate(Hukouqianyi hukouqianyi);

    @Select("select * from hukouqianyi where HKQY_Beiyong1=#{hkqyBeiyong1}")
    Hukouqianyi getxvhao(String hkqyBeiyong1);
}