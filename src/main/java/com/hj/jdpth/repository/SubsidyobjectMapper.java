package com.hj.jdpth.repository;

import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Subsidyobject;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component("dubsidyobjectMapper")
public interface SubsidyobjectMapper {

    //根据享受的补助项ID查看对象
    @Select("select * from subsidyObject where SO_EnjoyTheSubsidy=#{id}")
    Page<Subsidyobject> selectObjectbyIds(Integer id);

    @InsertProvider(type = SubsidyobjecSqlProvider.class, method = "insertDynamic")
    @Options(useGeneratedKeys = true, keyProperty = "subsidyobjectId", keyColumn = "SubsidyObject_Id")
    void insertDynamic(Subsidyobject subsidyobject);

    class SubsidyobjecSqlProvider {
        public String insertDynamic(Subsidyobject subsidyobject) {
            return new SQL() {
                {
                    INSERT_INTO("subsidyObject");
                    if (subsidyobject.getSoShenfenzheng() != null && !subsidyobject.getSoShenfenzheng().equals(" ")) {
                        VALUES("SO_ShenFenZheng", "#{soShenfenzheng}");
                    }
                    if (subsidyobject.getSoVillageid() != null && !subsidyobject.getSoVillageid().equals(" ")) {
                        VALUES("SO_villageid", "#{soVillageid}");
                    }
                    if (subsidyobject.getSoYonghu() != null && !subsidyobject.getSoYonghu().equals(" ")) {
                        VALUES("SO_Yonghu", "#{soYonghu}");
                    }
                    if (subsidyobject.getSoBeiyong5() != null && !subsidyobject.getSoBeiyong5().equals("")) {
                        VALUES("SO_beiyong5", "#{soBeiyong5}");//年份
                    }
                    if (subsidyobject.getSoBeiyong4() != null && !subsidyobject.getSoBeiyong4().equals(" ")) {
                        VALUES("SO_beiyong4", "#{soBeiyong4}");//补助原因
                    }
                    if (subsidyobject.getSoBeiyong3() != null && !subsidyobject.getSoBeiyong3().equals(" ")) {
                        VALUES("SO_beiyong3", "#{soBeiyong3}");//序号
                    }
                    if (subsidyobject.getSoBeiyong2() != null && !subsidyobject.getSoBeiyong2().equals(" ")) {
                        VALUES("SO_beiyong2", "#{soBeiyong2}");//户口类别
                    }
                    if (subsidyobject.getSoBeiyong1() != null && !subsidyobject.getSoBeiyong1().equals(" ")) {
                        VALUES("SO_beiyong1", "#{soBeiyong1}");//性别
                    }
                    if (subsidyobject.getSoEnjoythesubsidy() != null && !subsidyobject.getSoEnjoythesubsidy().equals(" ")) {
                        VALUES("SO_EnjoyTheSubsidy", "#{soEnjoythesubsidy}");//享受的补助
                    }
                    if (subsidyobject.getSoName() != null && !subsidyobject.getSoName().equals(" ")) {
                        VALUES("SO_Name", "#{soName}");
                    }
                    if (subsidyobject.getSoSubsidyid() != null && !subsidyobject.getSoSubsidyid().equals(" ")) {
                        VALUES("SO_SubsidyId", "#{soSubsidyid}");//补助户Id外间参考补助户表
                    }
                }
            }.toString();
        }
    }
}
