package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Job;
import com.hj.jdpth.domain.Partymemberinformation;
import com.hj.jdpth.domain.PartymemberinformationAndYonghu;
import com.hj.jdpth.domain.Yonghu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;


@Component(value = "partymemberinformationMapper")
public interface PartymemberinformationMapper {

    @SelectProvider(type = PartymemberProvider.class, method = "getPartmember")//value是用户名字
    @Results({
            @Result(property = "yonghu", column = "PM_YongHu", javaType = Yonghu.class, one = @One(select = "com.hj.jdpth.repository.YonghuMapper.queryById_yyq")),
            @Result(property = "job", column = "PMI_Post", javaType = Job.class, one = @One(select = "com.hj.jdpth.repository.JobMapper.queryById_yyq"))
    })
    Page<PartymemberinformationAndYonghu> queryAll_yyq(@Param("qu_id") Integer qu_id, @Param("zhen_id") Integer zhen_id, @Param("cun_id") Integer cun_id, @Param("value") String value);

    class PartymemberProvider {
        public String getPartmember(@Param("qu_id") Integer qu_id, @Param("zhen_id") Integer zhen_id, @Param("cun_id") Integer cun_id, @Param("value") String value) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("partymemberinformation");
            if (qu_id != 0 && zhen_id == 0 && cun_id == 0) {
                sql.AND();
                sql.INNER_JOIN("village on partymemberinformation.PM_Village=village.Village_Id");
                sql.INNER_JOIN("zhen on village.V_Zhen_xiang=zhen.Zhen_Id");
                sql.WHERE("zhen.Z_region=#{qu_id}");
                //sql.WHERE("PM_Village in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qu_id}))");
            }
            if (qu_id != 0 && zhen_id != 0 && cun_id == 0) {
                sql.AND();
                sql.INNER_JOIN("village on partymemberinformation.PM_Village=village.Village_Id");
                sql.WHERE("village.V_Zhen_xiang=#{zhen_id}");
                //sql.WHERE("PM_Village in (select Village_Id from village where V_Zhen_xiang=#{zhen_id})");
            }
            if (qu_id != 0 && zhen_id != 0 && cun_id != 0) {
                sql.AND();
                sql.WHERE("PM_Village=#{cun_id}");
            }
            if (!value.equals("")) {
                sql.AND();
                sql.WHERE("PM_yonghu in(select User_Id from yonghu where User_Name like '${value}%')");
            }
            return sql.toString();
        }
    }

    /* @Select("select * from partymemberinformation where PM_yonghu in(select User_Id from yonghu where User_Name like '${value}%')")
     @Results({
             @Result(property = "yonghu",column = "PM_YongHu",javaType = Yonghu.class,one = @One(select = "com.hj.jdpt.repository.YonghuMapper.queryById_yyq")),
             @Result(property = "job",column = "PMI_Post",javaType = Job.class,one = @One(select = "com.hj.jdpth.repository.JobMapper.queryById_yyq"))
     })
     List<PartymemberinformationAndYonghu> queryByName(@Param("value") String User_Name);*/
    @Select("select * from partymemberinformation where PartyMemberInformation_Id=#{id}")
    @Results({
            @Result(property = "yonghu", column = "PM_YongHu", javaType = Yonghu.class, one = @One(select = "com.hj.jdpth.repository.YonghuMapper.queryById_yyq")),
            @Result(property = "job", column = "PMI_Post", javaType = Job.class, one = @One(select = "com.hj.jdpth.repository.JobMapper.queryById_yyq"))
    })
    PartymemberinformationAndYonghu queryById_yyq(Integer id);


    @Delete("delete from partymemberinformation where PartyMemberInformation_Id=#{id}")
    int deleteById_yyq(Integer id);


    @Insert("insert into partymemberinformation (PMI_Post,PMI_Details,PMI_Photo,PMI_DegreeOfEducation,PMI_InauguralUnit,PMI_JoinTime,PM_JiGuan,PM_beiyong1,PM_Basic,PM_Joined,PM_YongHu,PM_Village)" +
            " values (#{pmiPost},#{pmiDetails},#{pmiPhoto},#{pmiDegreeofeducation},#{pmiInauguralunit},#{pmiJointime},#{pmJiguan},#{pmBeiyong1},#{pmBasic},#{pmJoined},#{pmYonghu},#{pmVillage})")
    int insert_yyq(Partymemberinformation p);


    @Update("update partymemberinformation set PMI_Post=#{pmiPost},PMI_Details=#{pmiDetails},PMI_Photo=#{pmiPhoto},PMI_DegreeOfEducation=#{pmiDegreeofeducation},PMI_InauguralUnit=#{pmiInauguralunit}" +
            ",PMI_JoinTime=#{pmiJointime},PM_JiGuan=#{pmJiguan},PM_beiyong1=#{pmBeiyong1},PM_Basic=#{pmBasic},PM_Joined=#{pmJoined},PM_YongHu=#{pmYonghu},PM_Village=#{pmVillage} where PartyMemberInformation_Id=#{partymemberinformationId}")
    int update_yyq(Partymemberinformation p);

}
