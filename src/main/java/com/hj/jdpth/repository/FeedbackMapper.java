package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Backtype;
import com.hj.jdpth.domain.Feedback;
import com.hj.jdpth.domain.Feedbackreply;
import com.hj.jdpth.domain.Zhen;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component(value = "feedbackMapper")
public interface FeedbackMapper {

    @SelectProvider(type = SearchYichuli.class, method = "findyichuli")
    Page<Feedback> SearchYichuli(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid, @Param("value") String value);

    class SearchYichuli {
        public String findyichuli(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid, @Param("value") String value) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("feedback");
            if (qid != 0 && zhenid == 0 && vid == 0) {
                sql.AND();
                sql.WHERE("F_beiyong2 in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qid}))");
            }
            if (qid != 0 && zhenid != 0 && vid == 0) {
                sql.AND();
                sql.WHERE("F_beiyong2 in (select Village_Id from village where V_Zhen_xiang=#{zhenid})");
            }
            if (qid != 0 && zhenid != 0 && vid != 0) {
                sql.AND();
                sql.WHERE("F_beiyong2=#{vid}");
            }
            if (!value.equals("0")) {
                sql.AND();
                sql.WHERE("F_Title like '${value}%'");
            }
            sql.AND();
            sql.WHERE("F_Handle=1");

            return sql.toString();
        }
    }

    @SelectProvider(type = SearchWeichuli.class, method = "findweichuli")
    Page<Feedback> SearchWeichuli(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid, @Param("value") String value);

    class SearchWeichuli {
        public String findweichuli(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid, @Param("value") String value) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("feedback");
            if (qid != 0 && zhenid == 0 && vid == 0) {
                sql.AND();
                sql.WHERE("F_beiyong2 in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qid}))");
            }
            if (qid != 0 && zhenid != 0 && vid == 0) {
                sql.AND();
                sql.WHERE("F_beiyong2 in (select Village_Id from village where V_Zhen_xiang= #{zhenid})");
            }
            if (qid != 0 && zhenid != 0 && vid != 0) {
                sql.AND();
                sql.WHERE("F_beiyong2=#{vid}");
            }
            if (!value.equals("0")) {
                sql.AND();
                sql.WHERE("F_Title like '${value}%'");
            }
            sql.AND();
            sql.WHERE("F_Handle=0");

            return sql.toString();
        }
    }

    @Insert("insert into feedbackreply (FR_FeedbackId,FR_people,FR_Time,FR_Content,FR_beiyong1,FR_beiyong2,FR_beiyong3) values (#{frFeedbackid},#{frPeople},#{frTime},#{frContent},#{frBeiyong1},#{frBeiyong2},#{frBeiyong3})")
    boolean Reply(Feedbackreply feedbackreply);

    @Select("select * from feedback where Feedback_Id=#{feedbackId}")
    Feedback findFeedback(Integer feedbackId);

    @Update("update feedback set F_Handle=#{fHandle} where Feedback_Id=#{feedbackId}")
    boolean clFeedback(Feedback feedback);

    @Select("select * from backtype where B_Key=#{id}")
    Backtype FindBacktype(Integer id);

    @Select("select * from feedbackreply where FR_FeedbackId=#{id}")
    Feedbackreply FindFeedbackreply(Integer id);

    @Update("update feedback set F_Judge=#{fJudge} where Feedback_Id=#{feedbackId}")
    boolean Upfankui(Feedback feedback);

    @SelectProvider(type = Xiangshangfankui.class, method = "up")
    Page<Feedback> Xiangshangfankui(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid, @Param("value") String value);

    class Xiangshangfankui {
        public String up(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid, @Param("value") String value) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("feedback");
            if (qid != 0 && zhenid == 0 && vid == 0) {
                sql.AND();
                sql.WHERE("F_beiyong2 in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qid}))");
            }
            if (qid != 0 && zhenid != 0 && vid == 0) {
                sql.AND();
                sql.WHERE("F_beiyong2 in (select Village_Id from village where V_Zhen_xiang=#{zhenid})");
            }
            if (qid != 0 && zhenid != 0 && vid != 0) {
                sql.AND();
                sql.WHERE("F_beiyong2=#{vid}");
            }
            if (!value.equals("0")) {
                sql.AND();
                sql.WHERE("F_Title like '${value}%'");
            }
            sql.AND();
            sql.WHERE("F_Judge=1");
            return sql.toString();
        }
    }


    @Select("select * from feedback where F_beiyong2 in (select Village_Id from village where V_Zhen_xiang=#{zid}) and F_Time between #{time1} and #{time2}")
    List<Feedback> Chulilv(@Param("zid") Integer zid, @Param("time1") String time1, @Param("time2") String time2);

    @Select("select * from feedback where F_beiyong2 in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qid})) and F_Time between #{time1} and #{time2}")
    List<Feedback> bingtu(@Param("qid") Integer qid, @Param("time1") String time1, @Param("time2") String time2);


    @Select("select * from zhen,feedback where feedback.F_beiyong2 in(select Village_Id from village where V_Zhen_xiang=zhen.Zhen_Id) and F_Time between #{time1} and #{time2}")
    List<Map<Zhen, Feedback>> aa(@Param("time1") String time1, @Param("time2") String time2);


    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,feedback.F_Handle,feedback.F_Time,Z_name from " +
            "village,feedback,zhen " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> queryLeader(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,feedback.F_Handle,feedback.F_Time,Z_name from " +
            "village,feedback,zhen " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> queryLeader2(@Param("zhenId") Integer zhenId);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,feedback.F_Handle,feedback.F_Time,Z_name from " +
            "village,feedback,zhen " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and F_Handle=1" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> ycl(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,feedback.F_Handle,feedback.F_Time,Z_name from " +
            "village,feedback,zhen " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and F_Handle=0" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> wcl(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name from " +
            "village,feedback,zhen,backtype " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> lx(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("<script>" +
            "SELECT village.Village_Id,village.V_Name,feedback.Feedback_Id,feedback.F_Title,feedback.F_Content,feedback.F_Time,backtype.B_Type,feedback.F_Handle,Z_name from " +
            "village,feedback,zhen,backtype " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key " +
            "and F_Handle=1" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> ycllb(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("<script>" +
            "SELECT village.Village_Id,village.V_Name,feedback.Feedback_Id,feedback.F_Title,feedback.F_Content,feedback.F_Time,backtype.B_Type,feedback.F_Handle,Z_name from " +
            "village,feedback,zhen,backtype " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key " +
            "and F_Handle=0" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> wcllb(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("select * from feedback where F_Judge=1 and F_beiyong2 in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qid})) and  F_Time between #{time1} and #{time2}")
    List<Feedback> upregion(@Param("qid") Integer qid, @Param("time1") String time1, @Param("time2") String time2);

    @Select("select * from backtype")
    List<Backtype> typelist();

    @Select("select * from feedback where F_beiyong2 in (select Village_Id from village where V_Zhen_xiang=#{zhenId}) and F_Time like '${value}%'")
    List<Feedback> findbydate(@Param("zhenId") Integer zhenId, @Param("value") String value);

    @Select("select * from feedback where  F_beiyong2 in (select Village_Id from village where V_Zhen_xiang=#{zhenId}) and F_Type=#{lx}")
    List<Feedback> findbylx(@Param("zhenId") Integer zhenId, @Param("lx") Integer lx);

    @Select("select * from feedback where F_beiyong2 in (select Village_Id from village where V_Zhen_xiang=#{zhenId}) and F_Time like '${value}%' and F_Handle=1")
    List<Feedback> findbydateycl(@Param("zhenId") Integer zhenId, @Param("value") String value);

    @Select("select * from feedback where F_beiyong2 in (select Village_Id from village where V_Zhen_xiang=#{zhenId}) and F_Time like '${value}%' and F_Handle=0")
    List<Feedback> findbydatewcl(@Param("zhenId") Integer zhenId, @Param("value") String value);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,feedback.F_Handle,feedback.F_Time,Z_name from " +
            "village,feedback,zhen " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Handle=1" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> ycl1(@Param("zhenId") Integer zhenId);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,feedback.F_Handle,feedback.F_Time,Z_name from " +
            "village,feedback,zhen " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Handle=0" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> wcl1(@Param("zhenId") Integer zhenId);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,feedback.F_Handle,feedback.F_Time,Z_name from " +
            "village,feedback,zhen " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Handle=1" +
            "<if test = ' villageId != 0'>" +
            "and Village_Id = #{villageId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> vycl(@Param("villageId") Integer villageId);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,feedback.F_Handle,feedback.F_Time,Z_name from " +
            "village,feedback,zhen " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Handle=0" +
            "<if test = ' villageId != 0'>" +
            "and Village_Id = #{villageId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> vwcl(@Param("villageId") Integer villageId);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,feedback.F_Handle,feedback.F_Time,Z_name from " +
            "village,feedback,zhen " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "<if test = ' villageId != 0'>" +
            "and Village_Id = #{villageId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName")})
    List<HashMap> vtotal(@Param("villageId") Integer villageId);

    @Select("select * from feedback where F_beiyong2 in (select Village_Id from village where Village_Id=#{villageId}) and F_Time like '${value}%' and F_Handle=1")
    List<Feedback> findbydateyclV(@Param("villageId") Integer villageId, @Param("value") String value);

    @Select("select * from feedback where F_beiyong2 in (select Village_Id from village where Village_Id=#{villageId}) and F_Time like '${value}%' and F_Handle=0")
    List<Feedback> findbydatewclV(@Param("villageId") Integer villageId, @Param("value") String value);

    @Select("select * from feedback where F_beiyong2 in (select Village_Id from village where Village_Id=#{villageId}) and F_Time like '${value}%' ")
    List<Feedback> findbydatetotalV(@Param("villageId") Integer villageId, @Param("value") String value);

    //2019-11-20
    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name,feedback.F_Title,feedback.F_Content,feedback.F_ImagePath ,feedbackreply.FR_Content from " +
            "village,zhen,backtype,feedbackreply RIGHT JOIN feedback ON feedback.Feedback_Id=feedbackreply.FR_FeedbackId " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName"),
            @Result(column = "feedback.F_Title", property = "fTitle"),
            @Result(column = "feedback.F_Content", property = "fContent"),
            @Result(column = "feedback.F_ImagePath", property = "fImagepath")})
    Page<HashMap> leaderxqall(@Param("time1") String time1, @Param("time2") String time2, @Param("zhenId") Integer zhenId);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name,feedback.F_Title,feedback.F_Content,feedback.F_ImagePath ,feedbackreply.FR_Content from  " +
            "village,zhen,backtype,feedbackreply RIGHT JOIN feedback ON feedback.Feedback_Id=feedbackreply.FR_FeedbackId " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key" +
            " and F_Handle=1" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName"),
            @Result(column = "feedback.F_Title", property = "fTitle"),
            @Result(column = "feedback.F_Content", property = "fContent"),
            @Result(column = "feedback.F_ImagePath", property = "fImagepath")})
    Page<HashMap> leaderxqycl(@Param("time1") String time1, @Param("time2") String time2, @Param("zhenId") Integer zhenId);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name,feedback.F_Title,feedback.F_Content,feedback.F_ImagePath ,feedbackreply.FR_Content from  " +
            "village,zhen,backtype,feedbackreply RIGHT JOIN feedback ON feedback.Feedback_Id=feedbackreply.FR_FeedbackId " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key" +
            " and F_Handle=0" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName"),
            @Result(column = "feedback.F_Title", property = "fTitle"),
            @Result(column = "feedback.F_Content", property = "fContent"),
            @Result(column = "feedback.F_ImagePath", property = "fImagepath")})
    Page<HashMap> leaderxqwcl(@Param("time1") String time1, @Param("time2") String time2, @Param("zhenId") Integer zhenId);

    @Select("select * from feedback where F_Type=#{lx} and F_Time between #{time1} and #{time2}")
    List<Feedback> qulxbintufindbylx(@Param("lx") Integer lx, @Param("time1") String time1, @Param("time2") String time2);

    //2019-11-27
    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name,feedback.F_Title,feedback.F_Content,feedback.F_ImagePath ,feedbackreply.FR_Content from  " +
            "village,zhen,backtype,feedbackreply RIGHT JOIN feedback ON feedback.Feedback_Id=feedbackreply.FR_FeedbackId " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "<if test = ' villageId != 0'>" +
            "and Village_Id = #{villageId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName"),
            @Result(column = "feedback.F_Title", property = "fTitle"),
            @Result(column = "feedback.F_Content", property = "fContent"),
            @Result(column = "feedback.F_ImagePath", property = "fImagepath")})
    Page<HashMap> zhenleaderxqall(@Param("time1") String time1, @Param("time2") String time2, @Param("zhenId") Integer zhenId, @Param("villageId") Integer villageId);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name,feedback.F_Title,feedback.F_Content,feedback.F_ImagePath ,feedbackreply.FR_Content from  " +
            "village,zhen,backtype,feedbackreply RIGHT JOIN feedback ON feedback.Feedback_Id=feedbackreply.FR_FeedbackId " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key" +
            " and F_Handle=1" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "<if test = ' villageId != 0'>" +
            "and Village_Id = #{villageId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName"),
            @Result(column = "feedback.F_Title", property = "fTitle"),
            @Result(column = "feedback.F_Content", property = "fContent"),
            @Result(column = "feedback.F_ImagePath", property = "fImagepath")})
    Page<HashMap> zhenleaderxqycl(@Param("time1") String time1, @Param("time2") String time2, @Param("zhenId") Integer zhenId, @Param("villageId") Integer villageId);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name,feedback.F_Title,feedback.F_Content,feedback.F_ImagePath ,feedbackreply.FR_Content from  " +
            "village,zhen,backtype,feedbackreply RIGHT JOIN feedback ON feedback.Feedback_Id=feedbackreply.FR_FeedbackId " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key" +
            " and F_Handle=0" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "<if test = ' villageId != 0'>" +
            "and Village_Id = #{villageId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName"),
            @Result(column = "feedback.F_Title", property = "fTitle"),
            @Result(column = "feedback.F_Content", property = "fContent"),
            @Result(column = "feedback.F_ImagePath", property = "fImagepath")})
    Page<HashMap> zhenleaderxqwcl(@Param("time1") String time1, @Param("time2") String time2, @Param("zhenId") Integer zhenId, @Param("villageId") Integer villageId);

    //2019-12-13

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name,feedback.F_Title,feedback.F_Content,feedback.F_ImagePath ,feedbackreply.FR_Content from " +
            "village,zhen,backtype,feedbackreply RIGHT JOIN feedback ON feedback.Feedback_Id=feedbackreply.FR_FeedbackId " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key " +
            "<if test = ' bkey != 0'>" +
            "and backtype.B_Key=#{bkey} " +
            "</if>" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName"),
            @Result(column = "feedback.F_Title", property = "fTitle"),
            @Result(column = "feedback.F_Content", property = "fContent"),
            @Result(column = "feedback.F_ImagePath", property = "fImagepath")})
    Page<HashMap> findbylxall(@Param("time1") String time1, @Param("time2") String time2, @Param("zhenId") Integer zhenId, @Param("bkey") Integer bkey);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name,feedback.F_Title,feedback.F_Content,feedback.F_ImagePath ,feedbackreply.FR_Content from  " +
            "village,zhen,backtype,feedbackreply RIGHT JOIN feedback ON feedback.Feedback_Id=feedbackreply.FR_FeedbackId " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key " +
            "<if test = ' bkey != 0'>" +
            "and backtype.B_Key=#{bkey} " +
            "</if>" +
            " and F_Handle=1" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName"),
            @Result(column = "feedback.F_Title", property = "fTitle"),
            @Result(column = "feedback.F_Content", property = "fContent"),
            @Result(column = "feedback.F_ImagePath", property = "fImagepath")})
    Page<HashMap> findbylxycl(@Param("time1") String time1, @Param("time2") String time2, @Param("zhenId") Integer zhenId, @Param("bkey") Integer bkey);

    @Select("<script>" +
            "SELECT village.Village_Id,feedback.Feedback_Id,backtype.B_Type,F_Handle,Z_name,feedback.F_Title,feedback.F_Content,feedback.F_ImagePath ,feedbackreply.FR_Content from  " +
            "village,zhen,backtype,feedbackreply RIGHT JOIN feedback ON feedback.Feedback_Id=feedbackreply.FR_FeedbackId " +
            "where Village_Id=F_beiyong2 and V_Zhen_xiang = Zhen_Id " +
            "and F_Time between #{time1} and #{time2}" +
            "and feedback.F_Type=backtype.B_Key " +
            "<if test = ' bkey != 0'>" +
            "and backtype.B_Key=#{bkey} " +
            "</if>" +
            " and F_Handle=0" +
            "<if test = ' zhenId != 0'>" +
            "and Zhen_Id = #{zhenId}" +
            "</if>" +
            "</script>")
    @Results({@Result(column = "village.Village_Id", property = "villageId"),
            @Result(column = "feedback.Feedback_Id", property = "feedbackId"),
            @Result(column = "backtype.B_Type", property = "bType"),
            @Result(column = "feedback.F_Handle", property = "fHandle"),
            @Result(column = "Z_name", property = "zName"),
            @Result(column = "feedback.F_Title", property = "fTitle"),
            @Result(column = "feedback.F_Content", property = "fContent"),
            @Result(column = "feedback.F_ImagePath", property = "fImagepath")})
    Page<HashMap> findbylxwcl(@Param("time1") String time1, @Param("time2") String time2, @Param("zhenId") Integer zhenId, @Param("bkey") Integer bkey);
}
