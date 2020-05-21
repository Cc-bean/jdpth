package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Subsidyname;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component("subsidynameMapper")
public interface SubsidynameMapper {

    //导入
    @Select("select S_Key  from subsidyName where S_name=#{sname} and S_beiyong2=#{village}")
    Integer selectIdByNameAdVillage(@Param("sname") String id, @Param("village") Integer Id);

    //补助项项所有信息
    @Select("select * from subsidyName where S_Key=#{id}")
    public Subsidyname selectAllXinxi(int id);

    //根据村选择补助项名称
    @Select("select S_name,S_Key from subsidyName where S_type=#{type}")
    List<HashMap> selectPolicyNameBYId(int type);

    //根据ID选名字
    @Select("select S_name from subsidyName where S_Key=#{id}")
    public String selectNameBYId(int id);

    //添加补助项
    @InsertProvider(type = SubsidynameSqlProvider.class, method = "insertSubsidynameDynamic")
    @Options(useGeneratedKeys = true, keyProperty = "sKey", keyColumn = "S_Key")
    public int insertSubsidynameDynamic(Subsidyname subsidyname);

    //检测重复
    @Select("select S_name from subsidyName where S_name=#{name} and S_type=#{type}")
    public String distinctname(@Param("name") String name, @Param("type") Integer type);

    class SubsidynameSqlProvider {
        public String insertSubsidynameDynamic(Subsidyname subsidyname) {
            return new SQL() {
                {
                    INSERT_INTO("subsidyName");
                    if (subsidyname.getsName() != null && !subsidyname.getsName().equals("")) {
                        VALUES("S_name", "#{sName}");
                    }
                    if (subsidyname.getsType() != null && !subsidyname.getsType().equals("")) {
                        VALUES("S_type", "#{sType}");//类型
                    }
                    if (subsidyname.getsBeiyong1() != null && !subsidyname.getsBeiyong1().equals("")) {//年份？
                        VALUES("S_beiyong1", "#{sBeiyong1}");
                    }
                    if (subsidyname.getsBeiyong2() != null && !subsidyname.getsBeiyong2().equals("")) {
                        VALUES("S_beiyong2", "#{sBeiyong2}");//村ID
                    }
                    if (subsidyname.getsBeiyong3() != null && !subsidyname.getsBeiyong3().equals("")) {
                        VALUES("S_beiyong3", "#{sBeiyong3}");
                    }
                    if (subsidyname.getsDown() != null && !subsidyname.getsDown().equals("")) {
                        VALUES("S_down", "sDown");
                    }
                    if (subsidyname.getsUp() != null && !subsidyname.getsUp().equals("")) {
                        VALUES("S_up", "#{sUp}");
                    }
                }
            }.toString();
        }
    }

}
