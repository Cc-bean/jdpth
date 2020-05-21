package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Journal;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

@Component(value = "journalMapper")
public interface JournalMapper {

    //新增日志
    @Insert({"INSERT INTO journal(J_PeopleId,J_Type,J_Time) VALUES (#{jPeopleid},#{jType},#{jTime})"})
    int insertJournal(Journal journal);

    //倒序日志
    @Select("<script>" +
            "select M_Name,J_Type,J_Time from journal,manager" +
            " where J_Peopleid = Manager_Id" +
            " <if test='jType != null'>" +
            "  and J_Type like concat(#{jType},'%') " +
            " </if> " +
            "order by Journal_Id desc" +
            "</script>")
    @Results(value = {
            @Result(column = "M_Name", property = "mName"),
            @Result(column = "J_Type", property = "jType"),
            @Result(column = "J_Time", property = "jTime")})
    Page<HashMap> queryAllJournal(@Param("jType") String jType);


    @Select("<script>" +
            "select Journal_Id,M_Name,J_Type,J_Time from journal,manager" +
            " where J_Peopleid = Manager_Id" +
            " <if test='jType != null'>" +
            "  and J_Type like concat(#{jType},'%') " +
            " </if> " +
            " <if test='zhenId != null'>" +
            "  and M_ZhenId = #{zhenId} " +
            " </if> " +
            " <if test='cunId != null'>" +
            "  and M_VillageId = #{cunId} " +
            " </if> " +
            " <if test='startTime != null and endTime != null'>" +
            "  and J_Time BETWEEN #{startTime} and #{endTime} " +
            " </if> " +
            "order by Journal_Id desc" +
            "</script>")
    @Results(value = {
            @Result(column = "Journal_Id", property = "id"),
            @Result(column = "M_Name", property = "mName"),
            @Result(column = "J_Type", property = "jType"),
            @Result(column = "J_Time", property = "jTime")})
    Page<HashMap> queryJournalss(@Param("jType") String jType, @Param("zhenId") Integer zhenId,
                                 @Param("cunId") Integer cunId, @Param("startTime") LocalDate startTIme,
                                 @Param("endTime") LocalDate endTime);


    @Delete("DELETE from journal where Journal_Id = #{id}")
    void delete(@Param("id") String id);
}
