package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Hu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "huMapper")
public interface HuMapper {
    @Delete("delete from hu where H_Key=#{id}")
    boolean HuDelete(Integer id);


    @SelectProvider(type = findhu.class, method = "gethu")
    Page<Map<String, Object>> HuSearch(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId, @Param("zuId") int zuId);

    class findhu {
        public String gethu(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId, @Param("zuId") int zuId) {
            SQL sql = new SQL();
            sql.SELECT("village.Village_Id,village.V_Name,hu.H_Key,hu.H_name");
            sql.FROM("hu");
            if (regionId != 0 && zhenId == 0 && villageId == 0 && zuId == 0) {
                sql.AND();
                sql.JOIN("zu on hu.H_zu=zu.Z_Key ", "village on zu.Z_village=village.Village_Id");
                sql.WHERE(" village.V_Region=#{regionId}");
            }
            if (regionId != 0 && zhenId != 0 && villageId == 0 && zuId == 0) {
                sql.AND();
                sql.JOIN("zu on hu.H_zu=zu.Z_Key ", "village on zu.Z_village=village.Village_Id");
                sql.WHERE(" village.V_Zhen_xiang = #{zhenId} ");
            }
            if (regionId != 0 && zhenId != 0 && villageId != 0 && zuId == 0) {
                sql.AND();
                sql.JOIN("zu on hu.H_zu=zu.Z_Key ", "village on zu.Z_village=village.Village_Id");
                sql.WHERE(" village.Village_Id =#{villageId}");
            }
            if (regionId != 0 && zhenId != 0 && villageId != 0 && zuId != 0) {
                sql.AND();
                sql.JOIN("zu on hu.H_zu=zu.Z_Key ", "village on zu.Z_village=village.Village_Id");
                sql.WHERE(" zu.Z_Key=#{zuId}");
            }
            return sql.toString();
        }
    }

    @Insert("insert into hu (H_name,H_zu,H_beiyong1) values (#{hName},#{hZu},#{hBeiyong1})")
    boolean HuInsert(Hu hu);

    @Update("update hu set H_name =#{hName} where H_Key=#{hKey}")
    boolean HuUpdate(Hu hu);

    @Select("select * from hu where H_Key=#{id}")
    Hu FindHuById(Integer id);

    ////
    //根据组ID查找户
    @Select("select * from Hu where H_zu=#{zuId}")
    public List<Hu> selectNameAdID(int zuId);

    @Select("select * from hu where H_beiyong1=#{hBeiyong1}")
    Hu Check(String H_beiyong1);

    //根据ID查找户名
    @Select("select H_name where H_Key=#{Id}")
    public String selectNameById(int Id);

    //根据身份证查找户号
    @Select("select User_Hu from yonghu where User_EntityId=#{card}")
    String selectHuhaoByCard(String card);
}
