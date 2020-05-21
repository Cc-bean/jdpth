package com.hj.jdpth.repository;

import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Villagecadres;
import com.hj.jdpth.domain.Villagecadres_Custom;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "villagecadresMapper")
public interface VillagecadresMapper {
    //多条件（村id 入职时间）查找所有村干部信息
    @SelectProvider(type = VillagecadresSqlProvider.class, method = "selectDynamic")
    @Results({
            @Result(property = "vcPost", column = "VC_Post"),
            @Result(property = "jName", column = "VC_Post", one = @One(select = "com.hj.jdpth.repository.JobMapper.selectNameById", fetchType = FetchType.EAGER)),
    })
    Page<Villagecadres_Custom> selectDynamic(Map<String, Object> map);

    //测试，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，
    //根据Id查干部所有信息
    @Select("select * from villagecadres where VillageCadres_Id=#{id}")
    @Results({
            @Result(property = "vcVillageid", column = "VC_VillageId"),
            @Result(property = "jName", column = "VC_Post", one = @One(select = "com.hj.jdpth.repository.JobMapper.selectNameById", fetchType = FetchType.EAGER)),
            @Result(property = "zhenId", column = "VC_VillageId", one = @One(select = "com.hj.jdpth.repository.VillageMapper.selectZhenID", fetchType = FetchType.EAGER))
    })
    Villagecadres_Custom selectById(int id);

    //。。，，，，，，，，，，，，，，，，，，，，，，，，，，，

    //根据Id删除
    @Delete("delete from villagecadres where VillageCadres_Id=#{id}")
    void deleteById(int id);

    //动态插入
    @InsertProvider(type = VillagecadresSqlProvider.class, method = "insertDynamic")
    @Options(useGeneratedKeys = true, keyProperty = "villagecadresId", keyColumn = "VillageCadres_Id")
    void insertDynamic(Villagecadres obj);

    //动态更新
    @UpdateProvider(type = VillagecadresSqlProvider.class, method = "updateDynamic")
    void updateDynamic(Villagecadres obj);

    //////////////////////////////////动态Sql
    class VillagecadresSqlProvider {
        public String selectDynamic(Map<String, Object> map) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("villagecadres");
                    if (map.get("year") != null && !map.get("year").equals("")) {
                        WHERE(" VC_beiyong1 LIKE CONCAT ('%',#{year},'%')");
                    }
                    List list = (List) map.get("list");
                    if (list != null && !list.equals("")) {
                        StringBuilder sql = new StringBuilder();
                        sql.append("VC_VillageId in ( ");
                        for (int i = 0; i < list.size(); i++) {
                            if (i != list.size() - 1) {
                                sql.append(list.get(i) + ",");
                            } else {
                                sql.append(list.get(i) + ")");
                            }
                        }
                        WHERE(sql.toString());
                    }

                }
            }.toString();
        }

        public String insertDynamic(Villagecadres obj) {
            return new SQL() {
                {
                    INSERT_INTO("villagecadres");
                    if (obj.getVcVillageid() != null && !obj.getVcVillageid().equals("")) {
                        VALUES("VC_VillageId", "#{vcVillageid}");
                    }
                    if (obj.getVcZhize() != null && !obj.getVcZhize().equals("")) {
                        VALUES("VC_ZhiZe", "#{vcZhize}");
                    }
                    if (obj.getVcSex() != null && !obj.getVcSex().equals("")) {
                        VALUES("VC_Sex", "#{vcSex}");
                    }
                    if (obj.getVcPost() != null && !obj.getVcPost().equals("")) {
                        VALUES("VC_Post", "#{vcPost}");//基本废弃
                    }
                    if (obj.getVcPosts() != null && !obj.getVcPosts().equals("")) {//11.27
                        VALUES("VC_Posts", "#{vcPosts}");//新增字段
                    }
                    if (obj.getVcName() != null && !obj.getVcName().equals("")) {
                        VALUES("VC_Name", "#{vcName}");
                    }
                    if (obj.getVcPhone() != null && !obj.getVcPhone().equals("")) {
                        VALUES("VC_Phone", "#{vcPhone}");
                    }
                    if (obj.getVcEntityid() != null && !obj.getVcEntityid().equals("")) {
                        VALUES("VC_entityId", "#{vcEntityid}");
                    }
                    if (obj.getVcBeiyong1() != null && !obj.getVcBeiyong1().equals("")) {
                        VALUES("VC_beiyong1", "#{vcBeiyong1}");
                    }
                    if (obj.getVcBeiyong2() != null && !obj.getVcBeiyong2().equals("")) {
                        VALUES("VC_beiyong2", "#{vcBeiyong2}");
                    }
                    if (obj.getVcGongzi() != null && !obj.getVcGongzi().equals("")) {
                        VALUES("VC_GongZi", "#{vcGongzi}");
                    }
                    if (obj.getVcNewimagename() != null && !obj.getVcNewimagename().equals("")) {
                        VALUES("VC_OldimageName", "#{vcOldimagename}");
                    }
                    if (obj.getVcOldimagename() != null && !obj.getVcOldimagename().equals("")) {
                        VALUES("VC_NewimageName", "#{vcNewimagename}");
                    }
                }
            }.toString();
        }

        public String updateDynamic(Villagecadres obj) {
            return new SQL() {
                {
                    UPDATE("villagecadres");
                    if (obj.getVcVillageid() != null) {
                        SET("VC_VillageId = #{vcVillageid}");
                    }
                    if (obj.getVcZhize() != null) {
                        SET("VC_ZhiZe = #{vcZhize}");
                    }
                    if (obj.getVcSex() != null) {
                        SET("VC_Sex = #{vcSex}");
                    }
                    if (obj.getVcPost() != null) {
                        SET("VC_Post = #{vcPost}");//基本废弃
                    }
                    if (obj.getVcPosts() != null) {
                        SET("VC_Posts = #{vcPosts}");//新增字段
                    }
                    if (obj.getVcName() != null) {
                        SET("VC_Name = #{vcName}");
                    }
                    if (obj.getVcPhone() != null) {
                        SET("VC_Phone = #{vcPhone}");
                    }
                    if (obj.getVcEntityid() != null) {
                        SET("VC_entityId = #{vcEntityid}");
                    }
                    if (obj.getVcBeiyong1() != null) {
                        SET("VC_beiyong1 = #{vcBeiyong1}");
                    }
                    if (obj.getVcBeiyong2() != null) {
                        SET("VC_beiyong2 = #{vcBeiyong2}");
                    }
                    if (obj.getVcGongzi() != null) {
                        SET("VC_GongZi = #{vcGongzi}");
                    }
                    if (obj.getVcNewimagename() != null && !obj.getVcNewimagename().equals("")) {
                        SET("VC_OldimageName = #{vcOldimagename}");
                    }
                    if (obj.getVcOldimagename() != null && !obj.getVcOldimagename().equals("")) {
                        SET("VC_NewimageName = #{vcNewimagename}");
                    }

                    WHERE("VillageCadres_Id = #{villagecadresId}");
                }
            }.toString();
        }
    }

}

