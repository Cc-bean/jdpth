package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Cunhuodongleixing;
import com.hj.jdpth.domain.Cunzuzhihuodong;
import com.hj.jdpth.domain.CunzuzhihuodongInfo;
import com.hj.jdpth.domain.Village;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "cunzuzhihuodongMapper")
public interface CunzuzhihuodongMapper {
    @SelectProvider(type = CunzuzhihuodongProvider.class, method = "getCunzuzhihuodong")//搜索村级活动
    @Results({
            @Result(property = "cunhuodongleixing", column = "CDYZZHD_LeiXing", javaType = Cunhuodongleixing.class, one = @One(select = "com.hj.jdpth.repository.CunzuzhihuodongMapper.FindLeixinById"))
    })
    Page<CunzuzhihuodongInfo> queryHuodong_yp(@Param("qu_id") Integer qu_id, @Param("zhen_id") Integer zhen_id, @Param("cun_id") Integer cun_id, @Param("chdlx_id") Integer chdlx_id);

    //搜索村级活动使用
    @Select("select * from cunhuodongleixing where CHDLX_Id=#{id}")
//根据村组织活动详情表  的活动类型属性  查找村活动类型表
    Cunhuodongleixing FindLeixinById(Integer id);

    //按活动ID查询活动详情
    @Select("select * from (cunzuzhihuodong inner join cunhuodongleixing on CDYZZHD_LeiXing=CHDLX_Id )where CDYZZHD_Id =#{id}")
    Map<String, Object> findXingqing_yp(Integer id);

    //删除一条记录
    @Delete("delete from cunzuzhihuodong where CDYZZHD_Id=#{id}")
    Boolean deleteOneById_yp(Integer id);

    //添加一条记录
    @Insert("insert into cunzuzhihuodong (CDYZZHD_Name,CDYZZHD_Content,CDYZZHD_Photo,CDYZZHD_Zhenshilujing,CDYZZHD_Place,CDYZZHD_Time,CDYZZHD_LeiXing,CDYZZHD_xuhao) values (#{cdyzzhdName},#{cdyzzhdContent},#{cdyzzhdPhoto},#{cdyzzhdZhenshilujing},#{cdyzzhdPlace},#{cdyzzhdTime},#{cdyzzhdLeixing},#{cdyzzhdXuhao}) ")
    int insertOne_yp(Cunzuzhihuodong cunzuzhihuodong);

    //编辑 更新一条记录
    @Update("update cunzuzhihuodong set CDYZZHD_Name=#{cdyzzhdName},CDYZZHD_Content=#{cdyzzhdContent},CDYZZHD_Photo=#{cdyzzhdPhoto},CDYZZHD_Zhenshilujing=#{cdyzzhdZhenshilujing},CDYZZHD_Place=#{cdyzzhdPlace},CDYZZHD_Time=#{cdyzzhdTime},CDYZZHD_LeiXing=#{cdyzzhdLeixing} where CDYZZHD_Id=#{cdyzzhdId}")
    Boolean updateOne_yp(Cunzuzhihuodong cunzuzhihuodong);

    //根据序号查找一个村活动的信息
    @Select("select * from cunzuzhihuodong where CDYZZHD_xuhao=#{cdyzzhdXuhao}")
    Cunzuzhihuodong findHuodongByxuhao_yp(@Param("cdyzzhdXuhao") String cdyzzhdXuhao);

    //根据村ID搜索村活动类型
    @Select("select * from  cunhuodongleixing where CHDLX_Village=#{cun_id}")
    Page<Cunhuodongleixing> findChdlxById_yp(Integer cun_id);

    //添加一条村活动类型
    @Insert("insert into cunhuodongleixing (CHDLX_Name,CHDLX_Sheng,CHDLX_Shi,CHDLX_Qu,CHDLX_Zhen,CHDLX_Village) values (#{chdlxName},#{chdlxSheng},#{chdlxShi},#{chdlxQu},#{chdlxZhen},#{chdlxVillage}) ")
    int insertLeixing_yp(Cunhuodongleixing cunhuodongleixing);

    //镇id查村
    @Select("select * from village where V_Zhen_Xiang=#{zhenId}")
    List<Village> findbyId_yp(Integer zhenId);


    class CunzuzhihuodongProvider {//搜索村级活动使用

        public String getCunzuzhihuodong(@Param("qu_id") Integer qu_id, @Param("zhen_id") Integer zhen_id, @Param("cun_id") Integer cun_id, @Param("chdlx_id") Integer chdlx_id) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("cunzuzhihuodong");
            if (qu_id != null && zhen_id == null && cun_id == null && chdlx_id == null) {
                sql.AND();
                sql.WHERE("CDYZZHD_Leixing in (select CHDLX_Id from cunhuodongleixing where CHDLX_Village in (select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{qu_id})))");
            }
            if (qu_id != null && zhen_id != null && cun_id == null && chdlx_id == null) {
                sql.AND();
                sql.WHERE("CDYZZHD_Leixing in (select CHDLX_Id from cunhuodongleixing where CHDLX_Village in (select Village_Id from village where V_Zhen_xiang=#{zhen_id}))");
            }
            if (qu_id != null && zhen_id != null && cun_id != null && chdlx_id == null) {
                sql.AND();
                sql.WHERE("CDYZZHD_Leixing in (select CHDLX_Id from cunhuodongleixing where CHDLX_Village=#{cun_id})");
            }
            if (qu_id != null && zhen_id != null && cun_id != null && chdlx_id != null) {
                sql.AND();
                sql.WHERE("CDYZZHD_Leixing=#{chdlx_id}");
            }
            return sql.toString();
        }
    }

    //领导镇级页面查询
    @Select("select CDYZZHD_Name,CDYZZHD_Time,CDYZZHD_Id,CDYZZHD_Place,CDYZZHD_Content from (cunzuzhihuodong inner join cunhuodongleixing on CDYZZHD_LeiXing=CHDLX_Id)inner join village on CHDLX_Village=Village_Id where Village_Id=#{cunId} and CDYZZHD_Time between #{time1} and #{time2}")
    List<HashMap> chdzhen(@Param("cunId") Integer cunId, @Param("time1") String time1, @Param("time2") String time2);

    //领导全区页面查询
    @Select("select * from ((cunzuzhihuodong inner join cunhuodongleixing on CDYZZHD_LeiXing=CHDLX_Id)inner join village on CHDLX_Village=Village_Id )inner join zhen on V_Zhen_xiang=Zhen_Id where Zhen_Id=#{zhenId} and CDYZZHD_Time  between #{time1} and #{time2}")
    List<HashMap> chdqu(@Param("zhenId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);

    @Select("select * from ((cunzuzhihuodong inner join cunhuodongleixing on CDYZZHD_LeiXing=CHDLX_Id)inner join village on CHDLX_Village=Village_Id )inner join zhen on V_Zhen_xiang=Zhen_Id where Zhen_Id=#{zhenId} ")
    List<HashMap> chdqu1(@Param("zhenId") Integer zhenId);

    @Select("select * from cunzuzhihuodong inner join cunhuodongleixing on CDYZZHD_LeiXing=CHDLX_Id where CHDLX_Village in (select Village_Id from village where V_Zhen_xiang=#{zhenId}) and CDYZZHD_Time like '${value}%'")
    List<Cunzuzhihuodong> findBydate(@Param("zhenId") Integer zhenId, @Param("value") String value);


    //全区折线图
    @Select("select Z_Name,CDYZZHD_Time from (((cunzuzhihuodong inner join cunhuodongleixing on CDYZZHD_LeiXing=CHDLX_Id)inner join village on CHDLX_Village=Village_Id )inner join zhen on V_Zhen_xiang=Zhen_Id)inner join region on Z_region=R_Key where R_key=1349 and CDYZZHD_Time between #{time1} and #{time2}")
    List<HashMap> qzxian(@Param("time1") String time1, @Param("time2") String time2);

    //镇级折线图
    @Select("select * from ((cunzuzhihuodong inner join cunhuodongleixing on CDYZZHD_LeiXing=CHDLX_Id)inner join village on CHDLX_Village=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where Zhen_Id=#{zhenId} and CDYZZHD_Time between #{time1} and #{time2}")
    List<HashMap> zzxian(@Param("cunId") Integer zhenId, @Param("time1") String time1, @Param("time2") String time2);


    /**
     * 用于查出所有村级活动类型
     *
     * @return list
     * @Param zhenId 镇
     * villageId 村
     */
    @Select("<script>" +
            "SELECT * FROM `cunhuodongleixing` " +
            "<where>" +
            "<if test = 'zhenId == null and villageId == null'> " +
            "CHDLX_Qu = 1349 " +
            "</if>" +
            "<if test = 'zhenId != null'>" +
            "and CHDLX_Zhen = #{zhenId} " +
            "</if>" +
            "<if test = 'villageId != null'>" +
            "and CHDLX_Village = #{villageId} " +
            "</if>" +
            "</where>" +
            "</script>")
    List<Cunhuodongleixing> queryAllLeixing(@Param("zhenId") Integer zhenId,
                                            @Param("villageId") Integer villageId);

    //根据村id和活动类型名查活动类型id
    @Select("select CHDLX_Id from cunhuodongleixing where CHDLX_Village=#{village} and CHDLX_Name=#{name}")
    Integer id(@Param("village") Integer village, @Param("name") String name);

    //（新版）镇级折线图
    @Select("select * from cunzuzhihuodong where CDYZZHD_LeiXing in (select CHDLX_Id from cunhuodongleixing where CHDLX_Village=#{villageId}) and CDYZZHD_Time like '${value}%' ")
    List<Cunzuzhihuodong> Vzhexian(@Param("villageId") Integer villageId, @Param("value") String value);

    @Select("select CHDLX_Id from cunhuodongleixing where CHDLX_Name=#{name} and CHDLX_Village=#{id}")
    Integer selectIDBYName(@Param("name") String name, @Param("id") Integer id);

    //2019-11-27
    @Select("select * from cunzuzhihuodong where CDYZZHD_Id=#{id}")
    Cunzuzhihuodong findbyid_lfm(Integer id);
}
