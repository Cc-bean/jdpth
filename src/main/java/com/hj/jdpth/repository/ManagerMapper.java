package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Manager;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component(value = "managerMapper")
public interface ManagerMapper {

    //处理后台页面登录使用
    @Select("<script>" +
            "select * from manager" +
            " <where> " +
            " <if test = 'mAccountnumber != null'>" +
            "M_AccountNumber = #{mAccountnumber} " +
            " </if> " +
            "<if test = 'mPassword != null'>" +
            "and M_Password = #{mPassword}" +
            "</if>" +
            "<if test = 'mType != null'>" +
            "and M_type = #{mType}" +
            "</if>" +
            " </where> " +
            "</script>")
    Manager checkLogin(@Param("mAccountnumber") String mAccountnumber, @Param("mPassword") String mPassword, @Param("mType") Integer mType);

    @Select("<script>" +
            "SELECT M_AccountNumber,M_ZhenId,Z_Name,M_type FROM `manager` join zhen on M_ZhenId=Zhen_Id" +
            " where " +
            " M_AccountNumber = #{mAccountnumber} " +
            " and M_Password = #{mPassword} " +
            "</script>")
    @Results(value = {
            @Result(column = "M_AccountNumber", property = "accountnumber"),
            @Result(column = "M_ZhenId", property = "zhenId"),
            @Result(column = "Z_Name", property = "zhenName"),
            @Result(column = "M_type", property = "mType")
    })
    HashMap checkLLogin(@Param("mAccountnumber") String mAccountnumber, @Param("mPassword") String mPassword);

    //查看所有管理员
    @Select("<script>" +
            "SELECT " +
            "       Manager_Id\n" +
            "      ,M_AccountNumber\n" +
            "      ,M_Password\n" +
            "      ,M_Name\n" +
            "      ,M_Sex\n" +
            "      ,M_DepartmentId\n" +
            "      ,M_Phone\n" +
            "      ,job.J_name\n" +
            "      ,village.V_Name\n" +
            "      ,zhen.Z_Name\n" +
            "      ,shi.S_name\n" +
            "      ,region.R_name \n" +
            "\t    ,managertype.M_Type\n" +
            "  FROM manager left join department on M_DepartmentId = Department_Id\n" +
            "\tleft join village on M_VillageId = Village_Id \n" +
            "\tleft join zhen on  M_ZhenId = Zhen_Id,shi,region,managertype,job\n" +
            "\twhere\n" +
            "\tmanager.M_type = M_Key and M_Region = R_Key and M_Shi = shi.S_Key and M_Post=J_Key " +
            " <if test='zhenId == null and villageId == null'> " +
            "  and M_Region =1349 " +
            " </if> " +
            " <if test='zhenId != null'>" +
            "  and M_ZhenId =#{zhenId} " +
            " </if> " +
            " <if test='villageId != null'> " +
            "  and M_VillageId = #{villageId} " +
            " </if> " +
            " <if test='mAccountnumber != null'>" +
            "  and M_AccountNumber =#{mAccountnumber} " +
            " </if> " +
            "</script>")
    @Results({@Result(column = "Manager_Id", property = "managerId"),
            @Result(column = "M_AccountNumber", property = "mAccountnumber"),
            @Result(column = "M_Password", property = "mPassword"),
            @Result(column = "M_Name", property = "mName"),
            @Result(column = "M_Sex", property = "mSex"),
            @Result(column = "D_Name", property = "dName"),
            @Result(column = "M_Phone", property = "mPhone"),
            @Result(column = "job.J_name", property = "jName"),
            @Result(column = "village.V_Name", property = "vName"),
            @Result(column = "zhen.Z_Name", property = "mZhenName"),
            @Result(column = "shi.S_name", property = "mShi"),
            @Result(column = "region.R_name", property = "mRegion"),
            @Result(column = "managertype.M_Type", property = "mType")
    })
    Page<HashMap> queryAllManager(@Param("zhenId") Integer zhenid, @Param("villageId") Integer villageId,
                                  @Param("mAccountnumber") String mAccountnumber);

    @Select("select * from manager where Manager_Id=#{managerId}")
    Manager FindManager(Integer managerId);

    @Insert("INSERT INTO manager\n" +
            "(`M_AccountNumber`,\n" +
            "`M_Password`,\n" +
            "`M_Name`,\n" +
            "`M_Sex`,\n" +
            "`M_DepartmentId`,\n" +
            "`M_Phone`,\n" +
            "`M_Post`,\n" +
            "`M_VillageId`,\n" +
            "`M_ZhenId`,\n" +
            "`M_Shi`,\n" +
            "`M_Region`,\n" +
            "`M_type`)\n" +
            "VALUES\n" +
            "(#{mAccountnumber},\n" +
            "#{mPassword},\n" +
            "#{mName},\n" +
            "#{mSex},\n" +
            "#{mDepartmentid},\n" +
            "#{mPhone},\n" +
            "#{mPost},\n" +
            "#{mVillageid},\n" +
            "#{mZhenid},\n" +
            "#{mShi},\n" +
            "#{mRegion},\n" +
            "#{mType})\n")
    int insertManager(Manager manager);     //用于新增管理员

    @Update("update manager set  M_AccountNumber=#{mAccountnumber},M_Password=#{mPassword},M_Name=#{mName}," +
            "M_Sex=#{mSex},M_DepartmentId=#{mDepartmentid},M_Phone=#{mPhone},M_Post=#{mPost}," +
            "M_VillageId=#{mVillageid},M_ZhenId=#{mZhenid},M_Shi=#{mShi},M_Region=#{mRegion},M_type=#{mType} " +
            "where Manager_Id=#{managerId}")
    int updateManager(Manager manager);


}
