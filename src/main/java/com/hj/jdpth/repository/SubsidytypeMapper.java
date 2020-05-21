package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Subsidytype;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

@Component("subsidytypeMapper")
public interface SubsidytypeMapper {

    //根据ID查找补助项类型名称,
    @Select("select ST_Name from subsidyType where SubsidyType_Id=#{id}")
    String selectNameBYId(int id);

    //导入
    @Select("select SubsidyType_Id from subsidyType where ST_Name=#{subType} and SI_VillageId=#{ID}")
    Integer selectIDByNameAdVillage(@Param("subType") String subType, @Param("ID") Integer id);
//    //补助类型列表根据村ID
//    @Select("select SubsidyType_Id,ST_Name from subsidyType where ")
//    List<Subsidytype> selectSubsidytypes();

    //某村下的所有有补助类型列表
    @Select("select SubsidyType_Id,ST_Name from subsidyType where SI_Villageid=#{id}")
    List<HashMap> selectSubsidytypesx(int id);

    //动态插入补助项类型
    //@Insert("insert into subsidyType (ST_Name,SI_Villageid) values (#{stName},#{siVillageid})")
    @InsertProvider(type = SubsidytypeSqlProvider.class, method = "insertTypeDynamic")
    void insertTypeDynamic(Subsidytype obj);

    //补助类型详细信息
    @Select("select * from subsidyType where SubsidyType_Id=#{id}")
    Subsidytype selectALLXInXI(int id);

    class SubsidytypeSqlProvider {
        //动态插入补助项类型
        public String insertTypeDynamic(Subsidytype obj) {
            return new SQL() {
                {
                    INSERT_INTO("subsidyType");
                    if (obj.getStName() != null && !obj.getStName().equals("")) {
                        VALUES("ST_Name", "#{stName}");
                    }
                    if (obj.getSiVillageid() != null && !obj.getSiVillageid().equals("")) {
                        VALUES("SI_Villageid", "#{siVillageid}");//村Id
                    }
                    if (obj.getSiBeiyong1() != null && !obj.getSiBeiyong1().equals("")) {
                        VALUES("SI_beiyong1", "#{siBeiyong1}");//备用
                    }
                    if (obj.getSiBeiyong2() != null && !obj.getSiBeiyong2().equals("")) {
                        VALUES("SI_beiyong2", "#{siBeiyong2}");//镇ID
                    }
                    if (obj.getSiBeiyong3() != null && !obj.getSiBeiyong3().equals("")) {
                        VALUES("SI_beiyong3", "#{siBeiyong3}");//备用
                    }
                }
            }.toString();
        }
    }

}
