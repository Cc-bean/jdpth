package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Yonghu;
import com.hj.jdpth.domain.YonghuCustom;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;

@Component(value = "yonghuMapper")
public interface YonghuMapper {

    @Select("select * from yonghu where User_Id=#{id}")
    Yonghu queryById_yyq(Integer id);

    @Select("select User_Id,User_EntityId from yonghu")
    List<Yonghu> queryAll_yyq();

    //根据 ID查找
    @Select("select * from yonghu where User_Id=#{id}")
    @Results(id = "YonghuCustom", value = {
            @Result(property = "userZu", column = "User_Zu"),
            @Result(property = "userVillageid", column = "User_VillageId"),
            @Result(property = "userTownship", column = "User_Township"),
            @Result(property = "userNation", column = "User_Nation"),
            @Result(property = "userShi", column = "User_Shi"),
            @Result(property = "userProvince", column = "User_Province"),
            @Result(property = "userRegion", column = "User_Region"),
            @Result(property = "userPoliticalstatus", column = "User_Politicalstatus"),
            @Result(property = "zuName", column = "User_Zu", one = @One(select = "com.hj.jdpth.repository.ZuMapper.selectNameByID", fetchType = FetchType.EAGER)),
            @Result(property = "villageName", column = "User_VillageId", one = @One(select = "com.hj.jdpth.repository.VillageMapper.selectNameByID", fetchType = FetchType.EAGER)),
            @Result(property = "zhenName", column = "User_Township", one = @One(select = "com.hj.jdpth.repository.ZhenMapper.selectNameByID", fetchType = FetchType.EAGER)),
            @Result(property = "nationName", column = "User_Nation", one = @One(select = "com.hj.jdpth.repository.NationMapper.queryById_yyq", fetchType = FetchType.EAGER)),
            @Result(property = "shiName", column = "User_Shi", one = @One(select = "com.hj.jdpth.repository.ShiMapper.selectNameByID", fetchType = FetchType.EAGER)),
            @Result(property = "shengName", column = "User_Province", one = @One(select = "com.hj.jdpth.repository.ShengMapper.selectNameById", fetchType = FetchType.EAGER)),
            @Result(property = "regionName", column = "User_Region", one = @One(select = "com.hj.jdpth.repository.RegionMapper.selectNameByID", fetchType = FetchType.EAGER)),
            @Result(property = "politicalstatusName", column = "User_Politicalstatus", one = @One(select = "com.hj.jdpth.repository.PolicyStatuMapper.selectNameByID", fetchType = FetchType.EAGER))

    })
    YonghuCustom selectYonhuByID(int id);

    //根据身份证获取ID
    @Select("select User_Id from yonghu where User_EntityId=#{card}")
    Integer selectIdBYCard(String card);

    //动态查询用户信息
    @SelectProvider(type = YonghuSqlProvider.class, method = "selectYongDynamic")
    @ResultMap(value = "YonghuCustom")
    Page<YonghuCustom> selectYongDynamic(Map map);

    //动态更新用户
    @UpdateProvider(type = YonghuSqlProvider.class, method = "updateYongHuBYId")
    void updateYongHuBYId(Yonghu yonghu);

    //动态插入用户
    @InsertProvider(type = YonghuSqlProvider.class, method = "insertDynamic")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "User_Id")
    void insertYongHu(Yonghu youghu);

    //删除用户BYID
    @Delete("delete from yonghu where User_Id=#{id}")
    void deleteYonhuBYId(int id);


    class YonghuSqlProvider {
        //动态查询
        public String selectYongDynamic(Map map) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("yonghu");
                    if (map.get("userName") != null && !map.get("userName").equals("")) {
                        WHERE(" User_Name LIKE CONCAT ('%',#{userName},'%')");
                    }
                    if (map.get("list") != null) {
                        List list = (List) map.get("list");
                        StringBuilder sql = new StringBuilder();
                        sql.append("User_VillageId in ( ");
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

        //动态更新
        public String updateYongHuBYId(Yonghu yonghu) {
            return new SQL() {
                {
                    UPDATE("yonghu");
                    if (yonghu.getUserVillageid() != null) {
                        SET("User_VillageId = #{userVillageid}");
                    }
                    if (yonghu.getUserZu() != null) {
                        SET("User_Zu = #{userZu}");//
                    }
                    if (yonghu.getUserHu() != null) {
                        SET("User_Hu = #{userHu}");//////////////
                    }
                    if (yonghu.getUserName() != null) {
                        SET("User_Name = #{userName}");
                    }
                    if (yonghu.getUserSex() != null) {
                        SET("User_Sex = #{userSex}");
                    }
                    if (yonghu.getUserNation() != null) {
                        SET("User_Nation = #{userNation}");
                    }
                    if (yonghu.getUserEntityid() != null) {
                        SET("User_EntityId = #{userEntityid}");
                    }
                    if (yonghu.getUserPhone() != null) {
                        SET("User_Phone = #{userPhone}");
                    }
                    if (yonghu.getUserEdu() != null) {
                        SET("User_Edu = #{userEdu}");
                    }
                    if (yonghu.getUserPoliticalstatus() != null) {
                        SET("User_Politicalstatus = #{userPoliticalstatus}");
                    }
                    if (yonghu.getUserBeiyong1() != null) {
                        SET("User_Beiyong1 = #{userBeiyong1}");
                    }
                    if (yonghu.getUserBeiyong2() != null) {
                        SET("User_Beiyong2 = #{userBeiyong2}");
                    }
                    if (yonghu.getUserAddress() != null) {
                        SET("User_Address = #{userAddress}");
                    }
                    if (yonghu.getUserAccountid() != null) {
                        SET("User_Accountid = #{userAccountid}");
                    }
                    if (yonghu.getUserPassword() != null) {
                        SET("User_Password = #{userPassword}");
                    }
                    if (yonghu.getUserField() != null) {
                        SET("User_Field = #{userField}");
                    }
                    if (yonghu.getUserProvince() != null) {
                        SET("User_Province = #{userProvince}");//
                    }
                    if (yonghu.getUserShi() != null) {
                        SET("User_Shi = #{userShi}");//
                    }
                    if (yonghu.getUserRegion() != null) {
                        SET("User_Region = #{userRegion}");//
                    }
                    if (yonghu.getUserTownship() != null) {
                        SET("User_Township = #{userTownship}");
                    }
                    if (yonghu.getUserFamily() != null) {
                        SET("User_Family = #{userFamily}");
                    }
                    if (yonghu.getUserBeiyong3() != null) {
                        SET("User_beiyong3 = #{userBeiyong3}");
                    }
                    if (yonghu.getUserHunumber() != null) {
                        SET("User_Hunumber = #{userHunumber}");
                    }
                    if (yonghu.getUserChange() != null) {
                        SET("User_Change = #{userChange}");
                    }
                    WHERE("User_Id = #{userId}");
                }
            }.toString();
        }

        //动态插入
        public String insertDynamic(Yonghu yonghu) {
            return new SQL() {
                {
                    INSERT_INTO("yonghu");
                    if (yonghu.getUserVillageid() != null && !yonghu.getUserVillageid().equals("")) {
                        VALUES("User_VillageId", "#{userVillageid}");
                    }
                    if (yonghu.getUserZu() != null && !yonghu.getUserZu().equals("")) {
                        VALUES("User_Zu", "#{userZu}");//
                    }
                    if (yonghu.getUserHu() != null && !yonghu.getUserHu().equals("")) {
                        VALUES("User_Hu", " #{userHu}");//
                    }
                    if (yonghu.getUserName() != null && !yonghu.getUserName().equals("")) {
                        VALUES("User_Name", "#{userName}");
                    }

                    if (yonghu.getUserSex() != null && !yonghu.getUserSex().equals("")) {
                        VALUES("User_Sex", "#{userSex}");
                    }
                    if (yonghu.getUserNation() != null && !yonghu.getUserNation().equals((""))) {
                        VALUES("User_Nation", "#{userNation}");
                    }
                    if (yonghu.getUserEntityid() != null && !yonghu.getUserEntityid().equals("")) {
                        VALUES("User_EntityId", "#{userEntityid}");
                    }
                    if (yonghu.getUserPhone() != null && !yonghu.getUserPhone().equals("")) {
                        VALUES("User_Phone", " #{userPhone}");
                    }
                    if (yonghu.getUserEdu() != null && !yonghu.getUserEdu().equals("")) {
                        VALUES("User_Edu", " #{userEdu}");
                    }
                    if (yonghu.getUserPoliticalstatus() != null && !yonghu.getUserPoliticalstatus().equals("")) {
                        VALUES("User_Politicalstatus", "#{userPoliticalstatus}");
                    }
                    if (yonghu.getUserBeiyong1() != null && !yonghu.getUserBeiyong1().equals("")) {
                        VALUES("User_Beiyong1", "#{userBeiyong1}");
                    }
                    if (yonghu.getUserBeiyong2() != null && !yonghu.getUserBeiyong1().equals("")) {
                        VALUES("User_Beiyong2", "#{userBeiyong2}");
                    }
                    if (yonghu.getUserAddress() != null && !yonghu.getUserAddress().equals("")) {
                        VALUES("User_Address", "#{userAddress}");
                    }
                    if (yonghu.getUserAccountid() != null && !yonghu.getUserAccountid().equals("")) {
                        VALUES("User_Accountid", "#{userAccountid}");
                    }
                    if (yonghu.getUserPassword() != null && !yonghu.getUserPassword().equals("")) {
                        VALUES("User_Password", "#{userPassword}");
                    }
                    if (yonghu.getUserField() != null && !yonghu.getUserField().equals("")) {
                        VALUES("User_Field", "#{userField}");
                    }
                    if (yonghu.getUserProvince() != null && !yonghu.getUserProvince().equals("")) {
                        VALUES("User_Province", "#{userProvince}");//
                    }
                    if (yonghu.getUserShi() != null && !yonghu.getUserShi().equals("")) {
                        VALUES("User_Shi", "#{userShi}");//
                    }
                    if (yonghu.getUserRegion() != null && !yonghu.getUserRegion().equals("")) {
                        VALUES("User_Region", "#{userRegion}");//
                    }
                    if (yonghu.getUserTownship() != null && !yonghu.getUserTownship().equals("")) {
                        VALUES("User_Township", "#{userTownship}");
                    }
                    if (yonghu.getUserFamily() != null && !yonghu.getUserFamily().equals("")) {
                        VALUES("User_Family", "#{userFamily}");
                    }
                    if (yonghu.getUserBeiyong3() != null && !yonghu.getUserBeiyong3().equals("")) {
                        VALUES("User_beiyong3", "#{userBeiyong3}");
                    }
                    if (yonghu.getUserHunumber() != null && !yonghu.getUserHunumber().equals("")) {
                        VALUES("User_Hunumber", "#{userHunumber}");
                    }
                    if (yonghu.getUserChange() != null && !yonghu.getUserChange().equals("")) {
                        VALUES("User_Change", "#{userChange}");
                    }
                }
            }.toString();
        }
    }

}
