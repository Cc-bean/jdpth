package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Job;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;


@Component(value = "jobmapper")
public interface JobMapper {
    ////
    ////
    //导入
    @Select("select J_name from job where J_Key=#{jkey} and J_village=#{Id}")
    String selectNameBYIdAdVillage(@Param("jkey") Integer jkey, @Param("Id") Integer Id);

    //根据Id查职务名称
    @Select("Select J_name from job where J_Key=#{id}")
    public String selectNameById(int id);

    //下拉Id？name列表
    @Select("Select J_name,J_Key from job")
    public List<HashMap> selectNameAdID();

    @Select("Select J_name,J_Key from job where J_village=#{id}")
    public List<HashMap> selectNameAdIDX(int id);

    /////
    @Select("select * from  job where J_village in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qid}))")
    Page<Job> SearchJob(Integer qid);

    @Select("select * from job where J_Key = #{id}")
    Job queryById_yyq(Integer id);

    @Select("select * from job")
    List<Job> queryAll_yyq();

    @Insert("insert into job (J_name,J_village) values (#{jName},#{jVillage})")
    boolean Insert(Job job);
}
