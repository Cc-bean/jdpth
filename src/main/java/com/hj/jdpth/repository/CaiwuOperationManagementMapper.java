package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Operationmanagement;
import com.hj.jdpth.domain.Village;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(value = "operationManagementMapper")
public interface CaiwuOperationManagementMapper {
    @SelectProvider(type = findOperationManagement.class, method = "getOperationManagement")
    Page<Map<Village, Operationmanagement>> findOperationManagement(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId);

    @Delete("delete from operationManagement where OperationManagement_Id=#{operationmanagementId}")
    int deleteOperationManagement(int operationmanagementId);

    @Select("select * from operationManagement where OperationManagement_Id=#{operationmanagementId}")
    Operationmanagement findOperationManagementById(int operationmanagementId);

    @Insert("insert into operationmanagement (OM_Name,OM_Implementation,OM_TenderingMaterial,OM_ImplementingParty,OM_SourceOfFunds," +
            "OM_ImplementThePlan,OM_Acceptanceparty,OM_FollowUpManagement," +
            "OM_DivisionOfResponsibility,OM_Villageid,OM_Type,OM_TM_ZhaoBiaoWenDang,OM_TM_ShiShiWenDang,OM_TM_SourceOfFundsFile," +
            "OM_TM_AcceptancePartyFile,OM_TM_FollowUpManagementFile,OM_TM_DivisionOfResponsibilityFile,OM_TM_beiyong1,OM_TM_beiyong2) " +
            "values (#{omName},#{omImplementation},#{omTenderingmaterial},#{omImplementingparty},#{omSourceoffunds},#{omImplementtheplan},#{omAcceptanceparty}," +
            "#{omFollowupmanagement},#{omDivisionofresponsibility},#{omVillageid},#{omType},#{omTmZhaobiaowendang},#{omTmShishiwendang},#{omTmSourceoffundsfile}," +
            "#{omTmAcceptancepartyfile},#{omTmFollowupmanagementfile},#{omTmDivisionofresponsibilityfile},#{omTmBeiyong1},#{omTmBeiyong2})")
    @Options(useGeneratedKeys = true, keyProperty = "operationmanagementId")
    int insertOperationmanagement(Operationmanagement operationmanagement);

    @Update("update operationmanagement set OM_Name=#{omName},OM_Implementation=#{omImplementation},OM_TenderingMaterial=#{omTenderingmaterial}," +
            "OM_ImplementingParty=#{omImplementingparty},OM_SourceOfFunds=#{omSourceoffunds},OM_ImplementThePlan=#{omImplementtheplan}," +
            "OM_Acceptanceparty=#{omAcceptanceparty},OM_FollowUpManagement=#{omFollowupmanagement},OM_DivisionOfResponsibility=#{omDivisionofresponsibility}," +
            "OM_Villageid=#{omVillageid},OM_Type=#{omType},OM_TM_ZhaoBiaoWenDang=#{omTmZhaobiaowendang},OM_TM_ShiShiWenDang=#{omTmShishiwendang}," +
            "OM_TM_SourceOfFundsFile=#{omTmSourceoffundsfile},OM_TM_AcceptancePartyFile=#{omTmAcceptancepartyfile},OM_TM_FollowUpManagementFile=" +
            "#{omTmFollowupmanagementfile},OM_TM_DivisionOfResponsibilityFile=#{omTmDivisionofresponsibilityfile},OM_TM_beiyong1=#{omTmBeiyong1},OM_TM_beiyong2=#{omTmBeiyong2} where " +
            " OperationManagement_Id=#{operationmanagementId}")
    int updateOperationmanagement(Operationmanagement operationmanagement);


    class findOperationManagement {
        public String getOperationManagement(@Param("regionId") int regionId, @Param("zhenId") int zhenId, @Param("villageId") int villageId) {
            SQL sql = new SQL();
            sql.SELECT(" village.Village_Id,village.V_Zhen_xiang,village.V_Name,OM_Name,OM_Implementation,OM_TenderingMaterial,OM_ImplementingParty,OM_SourceOfFunds," +
                    "OM_ImplementThePlan,OM_Acceptanceparty,OM_FollowUpManagement,OperationManagement_Id," +
                    "OM_DivisionOfResponsibility,OM_Villageid,OM_Type,OM_TM_ZhaoBiaoWenDang,OM_TM_ShiShiWenDang,OM_TM_SourceOfFundsFile," +
                    "OM_TM_AcceptancePartyFile,OM_TM_FollowUpManagementFile,OM_TM_DivisionOfResponsibilityFile,OM_TM_beiyong1,OM_TM_beiyong2 ");
            sql.FROM(" village,operationManagement ");
            if (regionId != 0 && zhenId == 0 && villageId == 0) {
                sql.AND();
                sql.WHERE("village.Village_Id=operationManagement.OM_Villageid and village.V_Region = #{regionId}  ");
            }
            if (regionId != 0 && zhenId != 0 && villageId == 0) {
                sql.AND();
                sql.WHERE(" village.Village_Id=operationManagement.OM_Villageid and village.V_Zhen_xiang = #{zhenId} ");
            }
            if (regionId != 0 && zhenId != 0 && villageId != 0) {
                sql.AND();
                sql.WHERE(" village.Village_Id=operationManagement.OM_Villageid and village.Village_Id =#{villageId}");
            }
            return sql.toString();
        }
    }
}
