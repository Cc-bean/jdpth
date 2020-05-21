package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Resources;
import com.hj.jdpth.domain.Resourcesdetails;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component(value = "resourcesdetailsMapper")
public interface ResourcesdetailsMapper {
    //区镇村 查询    用了
    @SelectProvider(type = ResourcesProvider.class, method = "getResources")
    Page<Map<String, Object>> queryNameAddress_gsh(@Param("qu_id") Integer qu_id, @Param("zhen_id") Integer zhen_id, @Param("cun_id") Integer cun_id, @Param("resourceStyle") String resourceStyle);

    class ResourcesProvider {
        public String getResources(@Param("qu_id") Integer qu_id, @Param("zhen_id") Integer zhen_id, @Param("cun_id") Integer cun_id, @Param("resourceStyle") String resourceStyle) {
            SQL sql = new SQL();
            sql.SELECT("*,village.V_Zhen_xiang as mZhenid");
            sql.FROM("resourcesDetails");
            if (qu_id != 0 && zhen_id == 0 && cun_id == 0) {
                sql.AND();
                sql.JOIN("resources on resourcesDetails.RD_ResourcesId = resources.Resources_Id", "village on village.Village_Id = resources.R_Villageid");
                sql.WHERE(" village.V_Region = #{qu_id} ");
            }
            if (qu_id != 0 && zhen_id != 0 && cun_id == 0) {
                sql.AND();
                sql.JOIN("resources on resourcesDetails.RD_ResourcesId=resources.Resources_Id", "village on village.Village_Id = resources.R_Villageid");
                sql.WHERE(" village.V_Zhen_xiang = #{zhen_id} ");
            }
            if (qu_id != 0 && zhen_id != 0 && cun_id != 0) {
                sql.AND();
                sql.JOIN("resources on resourcesDetails.RD_ResourcesId=resources.Resources_Id", "village on village.Village_Id = resources.R_Villageid");
                sql.WHERE(" R_Villageid =#{cun_id} ");
            }
            if (resourceStyle != null) {
                sql.AND();
                sql.WHERE("resources.R_Type =concat('%',#{resourceStyle},'%') ");
            }
            return sql.toString();
        }
    }


    //单删
    @Delete("delete from resourcesDetails where  ResourcesDetails_Id = #{id}")
    boolean deleteResourcesDetails(Integer id);

    //跟据资源详情表主键查询
    @Select("select * from resourcesDetails where ResourcesDetails_Id =#{id}")
    Resourcesdetails quaryById(Integer id);


    //添加资源详情
    @Insert("insert into  resourcesDetails (RD_Adress,RD_AreaCovered,RD_Details,RD_Name,RD_ResourcesId) values (#{rdAdress},#{rdAreacovered},#{rdDetails},#{rdName},#{rdResourcesid})")
    Integer addResourcesDetails(Resourcesdetails resourcesdetails);

    /*
    //插入资源表
    @Insert("insert into resources (R_Type,R_Villageid,R_beiyong1,R_zu) values (#{rType},#{rVillageid},#{rBeiyong1},#{rZu})")
    Integer AddResources(Resources resources);
    */

    //在资源表中查询主键
    @Select("select Resources_Id from resources ")
    List<Resources> quaryResourcesKey();

    //@Update("update resourcesDetails set RD_Name =#{rdName},RD_Adress =#{rdAdress},RD_AreaCovered =#{rdAreacovered},RD_Details=#{rdDetails} where  ResourcesDetails_Id=#{resourcesdetailsId}")
    @UpdateProvider(type = updateResourcesdetails.class, method = "getSQL")
    Integer updateResourcesdetails(Resourcesdetails resourcesdetails);

    class updateResourcesdetails {
        public String getSQL(Resourcesdetails resourcesdetails) {
            SQL sql = new SQL();
            sql.UPDATE("resourcesDetails");
            if (resourcesdetails.getRdName() != null) {
                sql.SET("RD_Name =#{rdName}");
            }
            if (resourcesdetails.getRdAdress() != null) {
                sql.SET("RD_Adress =#{rdAdress}");
            }
            if (resourcesdetails.getRdAreacovered() != null) {
                sql.SET("RD_AreaCovered =#{rdAreacovered}");
            }
            if (resourcesdetails.getRdDetails() != null) {
                sql.SET("RD_Details=#{rdDetails}");
            }
            sql.WHERE("ResourcesDetails_Id=#{resourcesdetailsId}");
            return sql.toString();
        }
    }

    @Select("select * from resourcesDetails where ResourcesDetails_Id =#{ResourcesDetails_Id}")
    Resourcesdetails quaryResourcesDetails(Integer ResourcesDetails_Id);

    @Insert("insert into  resourcesDetails (RD_Adress,RD_AreaCovered,RD_Details,RD_Name,RD_ResourcesId) values (#{rdAdress},#{rdAreacovered},#{rdDetails},#{rdName},#{rdResourcesid})")
    @Options(useGeneratedKeys = true, keyProperty = "resourcesdetailsId")
    Integer AdddResourcesdetails(Resourcesdetails resourcesdetails);

}
