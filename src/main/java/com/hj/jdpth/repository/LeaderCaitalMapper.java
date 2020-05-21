package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Capital;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "leaderCaitalMapper")
public interface LeaderCaitalMapper {
    @SelectProvider(type = findAllCapital.class, method = "findAllCapital")
    @Results({
            @Result(column = "SUM(CD_Beiyong1)", property = "CD_Beiyong1")
    })
    List<Map<String, Object>> findAllCapital(@Param("zhenId") int zhenId, @Param("style") String style);

    @SelectProvider(type = findAllCapitalByZhenId.class, method = "findAllCapitalByZhenId")
    List<Capital> findAllCapitalByZhenId(@Param("villageId") int villageId, @Param("style") String style);

    @SelectProvider(type = findAllCapital.class, method = "findCapitalByVillageId")
    List<Map<String, Object>> findCapitalByVillageId(@Param("villageId") int villageId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //通过时间查找所有的资金
    @Select("select CD_Income as yearCapital,CD_Expenditure as monthCapital,ROUND(SUM(CD_Beiyong1),2) as income,ROUND(SUM(CD_Beiyong2),2) as expenditure ,#{style} as style from capital " +
            "join village on capital.C_Villageid=village.Village_Id " +
            "where C_Type like concat('%',#{style},'%') and CD_Income = #{year} and village.V_Zhen_xiang=#{zhenId} " +
            "group by capital.CD_Income, LPAD(capital.CD_Expenditure,2,0) " +
            "order by capital.CD_Income*100+capital.CD_Expenditure ")
    List<Map<String, Object>> findCapitalYear(@Param("year") String year, @Param("style") String style, @Param("zhenId") Integer zhenId);

    //通过时间和镇id查所有资金
//    List<Map<String,Object>> findCapital

    class findAllCapital {

        public String findAllCapital(@Param("zhenId") int zhenId, @Param("style") String style) {
            SQL sql = new SQL();
            sql.SELECT("CD_Income,CD_Expenditure,SUM(CD_Beiyong1),SUM(CD_Beiyong2),C_Type");
            sql.FROM("capital");
            sql.JOIN("village on capital.C_Villageid=village.Village_Id ");
            sql.GROUP_BY("capital.CD_Income,capital.CD_Expenditure");//,"C_Type like concat('%','银行收支','%')","C_Type like concat('%','现金收支','%')"
            sql.ORDER_BY("capital.CD_Income*10000+capital.CD_Expenditure*100+village.Village_Id");
            sql.WHERE("village.V_Zhen_xiang=#{zhenId}", "C_Type like concat('%',#{style},'%')");
            return sql.toString();
        }

        public String findCapitalByVillageId(@Param("villageId") int villageId, @Param("startDate") String startDate, @Param("endDate") String endDate) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("capital");
            sql.ORDER_BY("capital.CD_Income*100+capital.CD_Expenditure");
            sql.WHERE("C_Villageid=#{villageId}", "capital.CD_Income*100+capital.CD_Expenditure between #{startDate} and #{endDate}");
            return sql.toString();
        }
    }

    class findAllCapitalByZhenId {
        public String findAllCapitalByZhenId(@Param("villageId") int villageId, @Param("style") String style) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("capital");
            sql.ORDER_BY("capital.CD_Income*100+capital.CD_Expenditure");
            sql.WHERE("C_Villageid=#{villageId}", "C_Type like concat('%',#{style},'%')");
            return sql.toString();
        }

    }
}
