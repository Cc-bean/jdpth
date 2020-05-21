package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Component(value = "caiwuCapitalMapper")
public interface CaiwuCapitalMapper {

    //根据显示所有的资金详情和对应的资金类型（区级）
    @Select("select capital.C_Type,capital.C_Villageid,capital.CD_Beiyong1,capital.CD_Beiyong2,capital.CD_zu,capitaldetails.CD_XiangQing," +
            "capitaldetails.CD_Income,capitaldetails.CD_Expenditure,capitaldetails.CD_Time,capitaldetails.CD_XiangQing,capitaldetails.CD_ZiJinId,capitaldetails.CapitalDetails_Id" +
            " from capital,capitaldetails where capitaldetails.CD_ZiJinId=capital.Capital_Id and C_Villageid in " +
            "(select Village_Id from village where V_Region = #{regionId} )")
    Page<Map<Capital, Capitaldetails>> findCapitaldetails(int regionId);

    //根据镇id查资金详情和对应的资金类型（镇级或职能级）
    @Select("select capital.C_Type,capital.C_Villageid,capital.CD_Beiyong1,capital.CD_Beiyong2,capital.CD_zu,capitaldetails.CD_XiangQing," +
            "capitaldetails.CD_Income,capitaldetails.CD_Expenditure,capitaldetails.CD_Time,capitaldetails.CD_XiangQing,capitaldetails.CD_ZiJinId,capitaldetails.CapitalDetails_Id" +
            " from capital,capitaldetails where capitaldetails.CD_ZiJinId=capital.Capital_Id and C_Villageid in " +
            "(select Village_Id from village where V_Zhen_xiang = #{zhenId} )")
    Page<Map<Capital, Capitaldetails>> findCapitaldetailsByzhenId(int zhenId);

    @Select("select * from zhen where Z_region=#{regionId}")
    List<Zhen> findZhenByRegionId(int regionId);

    @Select("select * from village where V_Zhen_xiang=#{Zhen_Id}")
    List<Village> findVillageByZhenId(int ZhenId);

    @Select("select capital.C_Type,capital.C_Villageid,capital.CD_Beiyong1,capital.CD_Beiyong2,capital.CD_zu,capitaldetails.CD_XiangQing," +
            "capitaldetails.CD_Income,capitaldetails.CD_Expenditure,capitaldetails.CD_Time,capitaldetails.CD_XiangQing,capitaldetails.CD_ZiJinId,capitaldetails.CapitalDetails_Id " +
            "  from capital,capitaldetails where capitaldetails.CD_ZiJinId=capital.Capital_Id and C_Villageid =#{villageId} ")
    Page<Map<Capital, Capitaldetails>> findCapitaldetailsByVillageId(int villageId);

    //通过区id，镇id，村id，（随便组合）和资金类型进行查找资金详情和对应的资金类型
    @SelectProvider(type = findCapitaldetailsByStyleProvider.class, method = "getCapitaldetails")
    Page<Map<Capital, Capitaldetails>> findCapitaldetailsByStyle(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId, @Param("cType") String cType);

    @Delete("delete from capitaldetails where CapitalDetails_Id=#{capitalDetailsId}")
    int deleteCapitaldetails(int capitalDetailsId);

    @Insert("insert into capital(C_Type,C_Villageid,CD_Income,CD_Expenditure,CD_Beiyong1,CD_Beiyong2,CD_zu,CD_xuhao)" +
            " values(#{cType},#{cVillageid},#{cdIncome},#{cdExpenditure},#{cdBeiyong1},#{cdBeiyong2},#{cdZu},#{cdXuhao})")
    @Options(useGeneratedKeys = true, keyProperty = "capitalId")
    int insertCapital(Capital capital);

    @Insert("insert into capitaldetails(CD_Income,CD_Expenditure,CD_Time,CD_XiangQing,CD_Zu,CD_ZiJinId,CD_beiyong1)" +
            " values(#{cdIncome},#{cdExpenditure},#{cdTime},#{cdXiangqing},#{cdZu},#{cdZijinid},#{cdBeiyong1})")
    @Options(useGeneratedKeys = true, keyProperty = "capitaldetailsId")
    int insertCapitaldetails(Capitaldetails capitaldetails);

    //导入资金
    @Insert("insert into capital (C_Villageid,C_Type,CD_Income,CD_Expenditure,CD_Beiyong1,CD_Beiyong2,CD_zu,CD_xuhao)" +
            " values (#{cVillageid},#{cType},#{cdIncome},#{cdExpenditure},#{cdBeiyong1},#{cdBeiyong2},#{cdZu},#{cdXuhao})")
    @Options(useGeneratedKeys = true, keyProperty = "capitalId")
    int importCapital(Capital capital);

    //只用于导入测试
    @Update("update capital set CD_Beiyong1=#{cdBeiyong1},CD_Beiyong2=#{cdBeiyong2} where Capital_Id=#{capitalId}")
    int updateCapital(Capital capital);

    //导入资金详情
    @Insert("insert into capitaldetails(CD_Income,CD_Expenditure,CD_Time,CD_XiangQing,CD_Zu,CD_ZiJinId)" +
            " values (#{cdIncome},#{cdExpenditure},#{cdTime},#{cdXiangqing},#{cdZu},#{cdZijinid})")
    @Options(useGeneratedKeys = true, keyProperty = "capitaldetailsId")
    int importCapitaldetails(Capitaldetails capitaldetails);

    @Select("select * from village where V_Name=#{name}")
    Village findVillageByName(String name);

    @Select("select * from village where Village_Id=#{villageId}")
    Village findVillageById(int villageId);

    @Select("select * from zhen where Zhen_Id=#{zhenId}")
    Zhen findZhenById(int zhenId);

    @Select("select * from capital where C_Villageid=#{villageId} and CD_Income=#{year} and CD_Expenditure=#{month} and C_Type like CONCAT('%',#{style},'%')")
    Capital findCapitalmohu(@Param("villageId") int villageId, @Param("year") int year, @Param("month") int month, @Param("style") String style);

    //只用于导入测试
    @Select("select Capital_Id from capital where C_Villageid=#{villageId} and CD_zu=#{zuId} and CD_Income=#{year} and CD_Expenditure=#{month} and C_Type like CONCAT('%',#{style},'%')")
    Capital findCapitalimport(@Param("villageId") int villageId, @Param("zuId") int zuId, @Param("year") int year, @Param("month") int month, @Param("style") String style);

    //只用于导入测试
    @Select("select CapitalDetails_Id from capitaldetails where CD_ZiJinId=#{capitalId} ")
    List<Capital> findCapitalDetailsById(int capitalId);

    //只用于导入测试
    @Select("select Z_Key from zu where Z_village=#{villageId} and Z_Name=#{zuName}")
    Zu findZuByName(@Param("villageId") int villageId, @Param("zuName") String zuName);

    @Select("select Z_Key from zu where Z_village=#{villageId} and Z_Key=#{zuId}")
    Zu findZuByID(@Param("villageId") int villageId, @Param("zuId") int zuId);

    @Select("select Z_Key from zu where Z_village=#{villageId}")
    List<Zu> findAllZuByID(@Param("villageId") int villageId);

    @Update("update capitaldetails set CD_Income=#{cdIncome},CD_Expenditure=#{cdExpenditure},CD_Time=#{cdTime},CD_XiangQing=#{cdXiangqing}," +
            "CD_Zu=#{cdZu},CD_ZiJinId=#{cdZijinid},CD_beiyong1=#{cdBeiyong1} where CapitalDetails_Id=#{capitaldetailsId}")
    int updateCapitaldetails(Capitaldetails capitaldetails);

    @Select("select * from capitaldetails where CapitalDetails_Id=#{capitaldetailsId}")
    Capitaldetails findCapitaldetailsById(int capitaldetailsId);

    @Select("select capital.C_Type,capital.C_Villageid,capital.CD_zu,capitaldetails.CD_Income,capitaldetails.CD_Expenditure ," +
            "capitaldetails.CD_Time,capitaldetails.CD_XiangQing,capitaldetails.CapitalDetails_Id" +
            " from capital,capitaldetails where capitaldetails.CD_ZiJinId=capital.Capital_Id and CapitalDetails_Id=#{capitaldetailsId}")
    Map<String, Object> findCapitalaDetails(int capitaldetailsId);


    class findCapitaldetailsByStyleProvider {
        public String getCapitaldetails(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId, @Param("cType") String cType) {
            SQL sql = new SQL();
            sql.SELECT("capital.C_Type,capital.C_Villageid,capital.CD_Beiyong1,capital.CD_Beiyong2,capital.CD_zu,capitaldetails.CD_XiangQing,capitaldetails.CD_beiyong1 as files, " +
                    "capitaldetails.CD_Income,capitaldetails.CD_Expenditure,capitaldetails.CD_Time,capitaldetails.CD_XiangQing,capitaldetails.CD_ZiJinId,capitaldetails.CapitalDetails_Id ");
            sql.FROM("capital,capitaldetails");
            if (cType == null) {
                if (regionId != 0 && zhenId == 0 && villageId == 0) {
                    sql.AND();
                    sql.WHERE("capitaldetails.CD_ZiJinId=capital.Capital_Id  and C_Villageid in " +
                            "(select Village_Id from village where V_Region = #{regionId} )");
                } else if (regionId != 0 && zhenId != 0 && villageId == 0) {
                    sql.AND();
                    sql.WHERE(" capitaldetails.CD_ZiJinId=capital.Capital_Id  and C_Villageid in  " +
                            "(select Village_Id from village where V_Zhen_xiang = #{zhenId} )");
                } else if (regionId != 0 && zhenId != 0 && villageId != 0) {
                    sql.AND();
                    sql.WHERE(" capitaldetails.CD_ZiJinId=capital.Capital_Id  and C_Villageid =#{villageId}");
                }
            } else {
                if (regionId != 0 && zhenId == 0 && villageId == 0) {//and capital.C_Type like CONCAT('%',#{cType},'%')
                    sql.AND();
                    sql.WHERE("capitaldetails.CD_ZiJinId=capital.Capital_Id and capital.C_Type like CONCAT('%',#{cType},'%') and C_Villageid in " +
                            "(select Village_Id from village where V_Region = #{regionId} )");
                }
                if (regionId != 0 && zhenId != 0 && villageId == 0) {
                    sql.AND();
                    sql.WHERE(" capitaldetails.CD_ZiJinId=capital.Capital_Id and capital.C_Type like CONCAT('%',#{cType},'%') and C_Villageid in  " +
                            "(select Village_Id from village where V_Zhen_xiang = #{zhenId} )");
                }
                if (regionId != 0 && zhenId != 0 && villageId != 0) {
                    sql.AND();
                    sql.WHERE(" capitaldetails.CD_ZiJinId=capital.Capital_Id and capital.C_Type like CONCAT('%',#{cType},'%') and C_Villageid =#{villageId}");
                }
            }
            return sql.toString();
        }
    }
}
