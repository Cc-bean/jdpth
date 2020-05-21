package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Licaiqingkuang;
import com.hj.jdpth.domain.Zu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "caiwulicaiMapper")
public interface CaiwulicaiMapper {
    @SelectProvider(type = findlicai.class, method = "getlicai")
    Page<Map<String, Object>> findlicai(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId, @Param("zuId") int zuId);

    @Select("select * from zu where Z_village=#{villageId}")
    List<Zu> findZu(int villageId);

    @Delete("delete from licaiqingkuang where LC_Id=#{licaiId}")
    int deleteLicai(int licaiId);

    @Select("select * from licaiqingkuang where LC_Id=#{licaiId}")
    Licaiqingkuang findLicaiById(int licaiId);

    @Insert("insert into licaiqingkuang (LC_name,LC_year,LC_month,LC_zu,LC_lujing ) values (#{lcName},#{lcYear},#{lcMonth},#{lcZu},#{lcLujing})")
    @Options(useGeneratedKeys = true, keyProperty = "lcId")
    int insertLicai(Licaiqingkuang licaiqingkuang);

    @Update("update licaiqingkuang set LC_name=#{lcName},LC_year=#{lcYear},LC_month=#{lcMonth},LC_lujing=#{lcLujing}," +
            "LC_zu=#{lcZu} where LC_Id=#{lcId} ")
    int updataLicai(Licaiqingkuang licaiqingkuang);


    class findlicai {
        public String getlicai(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId, @Param("zuId") int zuId) {
            SQL sql = new SQL();
            sql.SELECT("village.Village_Id,village.V_Zhen_xiang,village.V_Name,licaiqingkuang.LC_Id,licaiqingkuang.LC_name,licaiqingkuang.LC_year,licaiqingkuang.LC_month,licaiqingkuang.LC_zu,licaiqingkuang.LC_lujing");
            sql.FROM("licaiqingkuang");
            if (regionId != 0 && zhenId == 0 && villageId == 0 && zuId == 0) {
                sql.AND();
                sql.JOIN("zu on licaiqingkuang.LC_zu=zu.Z_Key ", "village on zu.Z_village=village.Village_Id");
                sql.WHERE(" village.V_Region=#{regionId}");
            }

            if (regionId != 0 && zhenId != 0 && villageId == 0 && zuId == 0) {
                sql.AND();
                sql.JOIN("zu on licaiqingkuang.LC_zu=zu.Z_Key ", "village on zu.Z_village=village.Village_Id");
                sql.WHERE(" village.V_Zhen_xiang = #{zhenId} ");
            }
            if (regionId != 0 && zhenId != 0 && villageId != 0 && zuId == 0) {
                sql.AND();
                sql.JOIN("zu on licaiqingkuang.LC_zu=zu.Z_Key ", "village on zu.Z_village=village.Village_Id");
                sql.WHERE(" village.Village_Id =#{villageId}");
            }
            if (regionId != 0 && zhenId != 0 && villageId != 0 && zuId != 0) {
                sql.AND();
                sql.JOIN("zu on licaiqingkuang.LC_zu=zu.Z_Key ", "village on zu.Z_village=village.Village_Id");
                sql.WHERE(" zu.Z_Key=#{zuId}");
            }
            return sql.toString();
        }
    }
}
