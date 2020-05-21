package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;


@Component(value = "policyMapper")
public interface PolicyMapper {


    //查询所有政策
    @Select("select * from policy")
    Page<Policy> queryA();

    //查询所有政策
    @Select("select * from policy where P_Type=#{pType}")
    Page<Policy> queryAll(@Param("pType") Integer pType);

    @Select("select count(*) from policy")
    int policyNumber();

    @Select("select count(*) from policy where P_zhenid=#{zId}")
    int policyNumberZhen(@Param("zId") Integer zId);

    @Select("select * from policy where P_Type=#{pType} and P_zhenid=#{zId}")
    Page<Policy> query(@Param("pType") Integer pType, @Param("zId") Integer zId);

    //根据省查
    @Select("select * from policy where  P_Sheng=#{shengId}")
    Page<Policy> findPolicyByShengId(@Param("shengId") Integer shengId);

    //根据市查
    @Select("select * from policy where  P_Shi=#{shiId}")
    Page<Policy> findPolicyByShiId(@Param("shiId") Integer shiId);

    //根据区查
    @Select("select * from policy where  P_Region=#{quId}")
    Page<Policy> findPolicyByQuId(@Param("quId") Integer quId);

    //根据乡镇
    @Select("select * from policy where  P_zhenid=#{zhenId}")
    Page<Policy> findPolicyByZhenId(@Param("zhenId") Integer zhenId);

    //根据村
    @Select("select * from policy where P_Villageid=#{cunId}")
    Page<Policy> findPolicyByCunId(@Param("cunId") Integer cunId);

    //单删政策
    @Delete("delete from policy where Policy_Id=#{policyId}")
    int deletePolicy(Integer policyId);

    //多删政策
    @Delete("delete from policy where Policy_Id in(#{policyId})")
    int deleteManyPolicy(Integer[] policyId);

    //根据政策id找政策
    @Select("Select * from policy where Policy_Id=#{policyId}")
    Policy findPolicy(Integer policyId);

    //根据省找市
    @Select("Select * from shi where S_sheng=#{sId}")
    List<Shi> shi_List(Integer sId);

    //根据市找区
    @Select("Select * from region where R_shi=#{shiId}")
    List<Region> qu_List(Integer shiId);

    //根据区找镇
    @Select("Select * from zhen where Z_region=#{quId}")
    List<Zhen> zhen_List(Integer quId);

    //根据镇找村
    @Select("Select * from sheng where S_Key=#{zhenId}")
    List<Village> cun_list(Integer zhenId);

    //找区
    @Select("Select * from region where R_Key=#{quId}")
    Region selectRegion(Integer quId);

    @Select("Select PT_Name from PolicyType where PolicyType_Id=#{pType}")
    String selectLeixinName(Integer pType);


    ///////////////////////////////////////////////
    @Select("select * from policy")
    public List<Policy> selectAllXinxi();

    //国家政策s
    @Select("select Policy_Id,P_Name from policy where P_Type = 1")
    List<HashMap> selectPolicyAdNameNation();

    //查找国家政策根据村ID
    @Select("select Policy_Id,P_Name from policy where P_Type = 1 and P_Villageid=#{id}")
    List<HashMap> selectPolicyAdNameNationBYID(int id);

    //实施政策
    @Select("select Policy_Id,P_Name from policy where P_Type <> 1")
    List<HashMap> selectPoclicyAdNameShisHi();

    //实施政策根据村ID
    @Select("select Policy_Id,P_Name from policy where P_Type <> 1 and P_Villageid=#{id}")
    List<HashMap> selectPoclicyAdNameShisHiBYID(int id);

    //0根据ID找政策
    @Select("select P_Name from policy where Policy_Id=#{id}")
    String selectNAmeByID(int id);

    //插入政策
    @Insert("insert into policy(P_Name,P_DocumentPath,P_Type,P_DateTime,P_Villageid,P_zhenid,P_Region,P_Shi,P_Sheng)values(#{pName},#{pDocumentpath},#{pType},#{pDatetime},#{pVillageid},#{pZhenid},#{pRegion},#{pShi},#{pSheng})")
    int insertPolicy(Policy policy);

}
//Policy_I
//P_Name
//P_DocumentPath
//P_Type
//P_DateTime
//P_Villageid
//P_zhenid
//P_Region
// P_Shi
// P_Sheng

