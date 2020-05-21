package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Subsidizedhouseholds;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component("subsidizedhouseholdsMapper")
public interface SubsidizedhouseholdsMapper {

    //根据享受的补助项ID查看对象
    @Select("select * from subsidizedHouseholds where SH_EnjoyTheSubsidy=#{id}")
    Page<Subsidizedhouseholds> selecthouseholdsbyIds(Integer id);

    //导入EXCEL
    @InsertProvider(type = SubsidizedhouseholdsSqlProvider.class, method = "insertDynamicImportExcel")
    @Options(useGeneratedKeys = true, keyProperty = "subsidizedhouseholdsId", keyColumn = "SubsidizedHouseholds_Id")
    void insertDynamicImportExcel(Subsidizedhouseholds subsidizedhouseholds);

    class SubsidizedhouseholdsSqlProvider {
        public String insertDynamicImportExcel(Subsidizedhouseholds subsidizedhouseholds) {
            return new SQL() {
                {
                    INSERT_INTO("subsidizedHouseholds");
                    if (subsidizedhouseholds.getSubsidizedhouseholdsId() != null && !subsidizedhouseholds.getSubsidizedhouseholdsId().equals(" ")) {
                        VALUES("", "#{subsidizedhouseholdsId}");
                    }
                    if (subsidizedhouseholds.getShName() != null && !subsidizedhouseholds.getShName().equals(" ")) {
                        VALUES("SH_Name", "#{shName}");
                    }
                    if (subsidizedhouseholds.getShAddress() != null && !subsidizedhouseholds.getShAddress().equals(" ")) {
                        VALUES("SH_Address", "#{shAddress}");
                    }
                    if (subsidizedhouseholds.getShEnjoythesubsidy() != null && !subsidizedhouseholds.getShEnjoythesubsidy().equals("")) {
                        VALUES("SH_EnjoyTheSubsidy", "#{shEnjoythesubsidy}");
                    }
                    if (subsidizedhouseholds.getShFamilyinformation() != null && !subsidizedhouseholds.getShFamilyinformation().equals("")) {
                        VALUES("SH_FamilyInformation", "#{shFamilyinformation}");
                    }
                    if (subsidizedhouseholds.getShVillageid() != null && !subsidizedhouseholds.getShVillageid().equals(" ")) {
                        VALUES("SH_Villageid", "#{shVillageid}");
                    }
                    if (subsidizedhouseholds.getShBeiyong1() != null && !subsidizedhouseholds.getShBeiyong1().equals(" ")) {
                        VALUES("SH_beiyong1", "#{shBeiyong1}");//序号
                    }
                    if (subsidizedhouseholds.getShBeiyong2() != null && !subsidizedhouseholds.getShBeiyong2().equals(" ")) {
                        VALUES("SH_beiyong2", "#{shBeiyong2}");//身份证
                    }
                    if (subsidizedhouseholds.getShBeiyong3() != null && !subsidizedhouseholds.getShBeiyong3().equals(" ")) {
                        VALUES("SH_beiyong3", "#{shBeiyong3}");//年份
                    }
                    if (subsidizedhouseholds.getShHu() != null && !subsidizedhouseholds.getShHu().equals(" ")) {
                        VALUES("SH_Hu", "#{shHu}");
                    }
                }
            }.toString();
        }
    }
}
