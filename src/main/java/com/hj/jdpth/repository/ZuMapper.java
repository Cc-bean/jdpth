package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Hu;
import com.hj.jdpth.domain.Zu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "zuMapper")
public interface ZuMapper {

    @Select("select * from zu where Z_Key=#{id}")
    Zu FindById_lfm(Integer id);

    @SelectProvider(type = findzu.class, method = "getzu")
    Page<Map<String, Object>> zuSearch_lfm(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId);

    class findzu {
        public String getzu(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId) {
            SQL sql = new SQL();
            sql.SELECT("village.Village_Id,village.V_Name,zu.Z_key,zu.Z_Name");
            sql.FROM("zu");
            if (regionId != 0 && zhenId != 0 && villageId != 0) {
                sql.AND();
                sql.JOIN("village on zu.Z_village=village.Village_Id", "zhen on village.V_Zhen_xiang=zhen.Zhen_Id");
                sql.WHERE("village.Village_Id=#{villageId}");
            }
            if (regionId != 0 && zhenId != 0 && villageId == 0) {
                sql.AND();
                sql.JOIN("village on zu.Z_village=village.Village_Id", "zhen on village.V_Zhen_xiang=zhen.Zhen_Id");
                sql.WHERE("village.V_Zhen_xiang=#{zhenId}");
            }
            if (regionId != 0 && zhenId == 0 && villageId == 0) {
                sql.AND();
                sql.JOIN("village on zu.Z_village=village.Village_Id", "zhen on village.V_Zhen_xiang=zhen.Zhen_Id");
                sql.WHERE("village.V_Region=#{regionId}");
            }
            return sql.toString();
        }

    }

    @Delete("delete from zu where Z_Key=#{id}")
    boolean ZuDelete(Integer id);

    @Insert("insert into zu (Z_name,Z_village,Z_Beiyong1) values (#{zName},#{zVillage},#{zBeiyong1})")
    boolean ZuInsert(Zu zu);

    @Update("update zu set Z_name =#{zName} where Z_Key=#{zKey}")
    boolean ZuUpdate(Zu zu);

    @Select("select * from zu where Z_village = #{id}")
    List<Zu> quaryByVillage_gsh(Integer id);

    //组名查组id
    @Select("select * from zu where Z_name = #{Name}")
    Zu quertByVillageName_gsh(String Name);

    //根据id查组名
    @Select("select Z_name from zu  where Z_Key=#{id}")
    public String selectNameByID(int id);

    //某村，组下拉列表
    @Select("select Z_name,Z_Key from zu  where Z_village=#{cunId}")
    public List<HashMap> selectNameAdId(int cunId);

    @Select("select * from zu where Z_Beiyong1=#{zBeiyong1}")
    Zu Check(String zBeiyong1);

}
