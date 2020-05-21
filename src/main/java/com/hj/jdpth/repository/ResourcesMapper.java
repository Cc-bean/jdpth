package com.hj.jdpth.repository;


import com.github.pagehelper.Page;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Component(value = "resourcesMapper")
public interface ResourcesMapper {

    //资源子类型添加  用了
    @Insert("insert into resources (R_Type ,R_beiyong1,R_Villageid,R_Zu) values (#{rType},#{rBeiyong1},#{rVillageid},#{rZu})")
    @Options(useGeneratedKeys = true, keyProperty = "resourcesId")
    Integer AddResources(Resources resources);

    //EXCEL
    @Insert("insert into resources (R_Type,R_Villageid,R_Zu,R_xvhao) values (#{rType},#{rVillageid},#{rZu},#{rXvhao})")
    @Options(useGeneratedKeys = true, keyProperty = "resourcesId")
    Integer AdddRecources(Resources resources);

    //在资源类型表中根据类型查询主键
    @Select("select R_Key from resourceType where R_type = #{Type}")
    Integer quaryRtype(String Type);

    //在资源类型表中根据类型查询资源子类型
    @Select("select  R_beiyong1,Resources_Id from  resources  where R_Type = #{R_Key}")
    List<Resources> quaryResourcesR_beiyong1(Integer R_Key);

    //用了
    @UpdateProvider(type = updateResourcesdetails.class, method = "getSQL")
    Integer updateResources(Resources resources);

    class updateResourcesdetails {
        public String getSQL(Resources resources) {
            SQL sql = new SQL();
            sql.UPDATE("resources");
            if (resources.getrType() != null) {
                sql.SET("R_Type =#{rType}");
            }
            sql.WHERE("Resources_Id=#{resourcesId}");
            return sql.toString();
        }
    }

    //用了
    @Select("select * from resources where  Resources_Id = #{Resources_Id}")
    Resources quaryResources(@Param("Resources_Id") Integer Resources_Id);

    //用了
    @Select("select * from resources  " +
            "where R_villageid=#{villageId} and R_zu=#{zuId} and R_Type=#{style}")
    Resources quaryResourcesStyle(@Param("villageId") Integer villageId, @Param("zuId") Integer zuId, @Param("style") String style);

    @Select("select distinct R_Type  from Resources   where R_Villageid=#{villageId}")
    List<Map<String, Object>> quaryDisStyle(int villageId);

    @Select("select  *  from  Resources  where R_zu=#{zuid}")
    List<Map<String, Object>> quaryStyleByZuid(int zuid);
}