package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.SubsidyCustom;
import com.hj.jdpth.domain.Subsidyinformation;
import com.hj.jdpth.domain.Subsidyname;
import com.hj.jdpth.domain.SubsidynameCustom;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("subsidyinformationMapper")
public interface SubsidyinformationMapper {


    //根据cun/leixing/查找
    @Select("select * from subsidyInformation where SI_NameOfSubsidy=#{id}")
    Subsidyinformation selectImportExcel(Integer id);

    //根据ID 查找详情
    //siNameofsubsidy
    @Select("select a.SubsidyInformation_Id,a.SI_SubsidyAmount,a.SI_Receivetime,a.SI_Prize,a.SI_Thing,a.SI_house,a.SI_Object,b.S_name,b.S_Up,b.S_Down,c.ST_Name,c.SI_Villageid,c.SI_beiyong2 from subsidyInformation a,subsidyName b,subsidyType c where a.SI_Nameofsubsidy= b.S_Key and b.S_type = c.SubsidyType_Id and SubsidyInformation_Id=#{id}")
    HashMap selectInforMationBYId(int id);

    //根据ID查看所有补助对象
    @Select("select SI_Object from subsidyInformation where SubsidyInformation_Id=#{id}")
    String selectObjectMationBYId(int id);

    //删除根据id 12.1
    @Delete("delete from subsidyname where S_Key=#{id}")
    void deleteInforMationBYId(int id);

    //动态查找2019.9.24
    @SelectProvider(type = SubsinformationSqlProvider.class, method = "selectSubsidyInforMationDynamic")
    Page<HashMap> selectSubsidyInforMationDynamic(Map params);

    //动态插入补助项（补助项级次表联添加）
    @InsertProvider(type = SubsinformationSqlProvider.class, method = "intserSubsidyInforMationDynamic")
    public void intserSubsidyInforMationDynamic(SubsidynameCustom subsidynameCustom);

    //动态更新补助项信息
    @InsertProvider(type = SubsinformationSqlProvider.class, method = "updateInformationDynamic")
    public void updateInformationDynamic(Subsidyinformation subsidyinformation);

    //动态更新补助项名称
    @Update("update subsidyName set S_name = #{name} where S_Key = #{id}")
    public void updateInformatioName(@Param("name") String name, @Param("id") int id);
//yuyyuyuyu

    class SubsinformationSqlProvider {
        //动态查找
        public String selectSubsidyInforMationDynamic(Map params) {
            return new SQL() {
                {
                    SELECT("a.SubsidyInformation_Id,a.SI_SubsidyAmount,a.SI_Receivetime,b.S_name,b.S_Key,c.ST_Name,c.SI_Villageid,c.SI_beiyong2");
                    FROM("subsidyInformation a", "subsidyName b", "subsidyType c");
                    WHERE("a.SI_Nameofsubsidy= b.S_Key ");
                    WHERE(" b.S_type = c.SubsidyType_Id");
                    if (params.get("type") != null) {
                        WHERE("b.S_type=#{type}");
                    }
                    List list = (List) params.get("list");
                    StringBuilder sql = new StringBuilder();
                    sql.append("c.SI_Villageid in ( ");
                    for (int i = 0; i < list.size(); i++) {
                        if (i != list.size() - 1) {
                            sql.append(list.get(i) + ",");
                        } else {
                            sql.append(list.get(i) + ")");
                        }
                    }
                    WHERE(sql.toString());
                    System.out.println(this);
                }

            }.toString();
        }

        //动态插入
        public String intserSubsidyInforMationDynamic(SubsidynameCustom subsidynameCustom) {
            return new SQL() {
                {
                    INSERT_INTO("subsidyInformation");
                    if (subsidynameCustom.getSiThing() != null && !subsidynameCustom.getSiThing().equals("")) {//物品
                        VALUES("SI_Thing", "#{siThing}");
                    }
                    if (subsidynameCustom.getSiNameofsubsidy() != null && !subsidynameCustom.getSiNameofsubsidy().equals("")) {//id
                        VALUES("SI_NameOfSubsidy", "#{siNameofsubsidy}");
                    }
                    if (subsidynameCustom.getSiSubsidyamount() != null && !subsidynameCustom.getSiSubsidyamount().equals("")) {//金额
                        VALUES("SI_SubsidyAmount", "#{siSubsidyamount}");
                    }
                    if (subsidynameCustom.getSiPrize() != null && !subsidynameCustom.getSiPrize().equals("")) {//奖励品
                        VALUES("SI_Prize", "#{siPrize}");
                    }
                    if (subsidynameCustom.getSiReceivetime() != null && !subsidynameCustom.getSiReceivetime().equals("")) {//申领时间
                        VALUES("SI_Receivetime", "#{siReceivetime}");
                    }
                    if (subsidynameCustom.getSiStandard() != null && !subsidynameCustom.getSiStandard().equals("")) {//标准
                        VALUES("SI_Standard", "#{siStandard}");
                    }
                }
            }.toString();
        }

        //动态更新
        public String updateInformationDynamic(Subsidyinformation subsidyinformation) {
            return new SQL() {
                {
                    UPDATE("subsidyInformation");
                    if (subsidyinformation.getSiStandard() != null) {
                        SET("SI_Standard =#{siStandard}");
                    }
                    if (subsidyinformation.getSiReceivetime() != null) {
                        SET("SI_Receivetime =#{siReceivetime}");
                    }
                    if (subsidyinformation.getSiPrize() != null) {
                        SET("SI_Prize =#{siPrize}");
                    }
                    if (subsidyinformation.getSiThing() != null) {
                        SET("SI_Thing =#{siThing}");
                    }
                    if (subsidyinformation.getSiNameofsubsidy() != null) {
                        SET("SI_NameOfSubsidy =#{siNameofsubsidy}");
                    }
                    if (subsidyinformation.getSiObject() != null) {
                        SET("SI_Object = #{siObject}");
                    }
                    if (subsidyinformation.getSiHouse() != null) {
                        SET("SI_house = #{siHouse}");
                    }
                    if (subsidyinformation.getSiSubsidyamount() != null) {
                        SET("SI_Subsidyamount = #{siSubsidyamount}");
                    }
                    WHERE("SubsidyInformation_Id = #{subsidyinformationId}");
                }
            }.toString();
        }
    }

}
