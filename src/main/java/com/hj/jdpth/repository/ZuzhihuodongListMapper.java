package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


import java.util.List;

@Component(value = "zuzhihuodongListMapper")
public interface ZuzhihuodongListMapper {
    //查询全部信息
    @Select("select * from dangyuanzuzhihuodong where DYZZHD_Villageid in(select Village_Id from village where V_Zhen_xiang in (select Zhen_Id from zhen where Z_region=#{quId}))")
    Page<Dangyuanzuzhihuodong> GentInfo(@Param("quId") Integer quId);

    //查询一个镇的信息
    @Select("select * from dangyuanzuzhihuodong where DYZZHD_Villageid in(select Village_Id from village where V_Zhen_xiang=#{zhenId} and V_Xian_qu=#{quId})")
    Page<Dangyuanzuzhihuodong> GetInfoByZhen(@Param("zhenId") Integer zhenId, @Param("quId") Integer quId);

    //查询一个村的信息
    @Select("select * from dangyuanzuzhihuodong where DYZZHD_Villageid=#{cunId} ")
    Page<Dangyuanzuzhihuodong> GetInfoByCun(@Param("cunId") Integer cunId);

    //查询单个活动类型
    @Select("select * from dangyuanzuzhihuodong where DYZZHD_Villageid=#{cunId} and DYZZHD_LeiXing=#{typeId} ")
    Page<Dangyuanzuzhihuodong> GetInfoByType(@Param("cunId") Integer cunId, @Param("typeId") Integer typeId);

    //根据区id查区对象
    @Select("select * from region where R_Key=#{rId}")
    Region GetRegion(@Param("rId") Integer rKey);

    //根据镇id和区id查镇对象
    @Select("select * from zhen where Zhen_Id=#{zId}")
    Zhen GetZhen(@Param("zId") Integer zKey);

    //根据村id查村对象
    @Select("select * from village where Village_Id=#{vId}")
    Village GetVillage(@Param("vId") Integer vKey);

    //根据id查党员组织活动的详情
    @Select("select * from dangyuanzuzhihuodong where DYZZHD_Id=#{DYZZHD_Id}")
    Dangyuanzuzhihuodong GetInfoById(@Param("DYZZHD_Id") Integer DYZZHD_Id);

    //根据id删除一条党员组织活动
    @Delete("delete from dangyuanzuzhihuodong where DYZZHD_Id=#{DYZZHD_Id}")
    boolean DeleteInfoOne(@Param("DYZZHD_Id") Integer DYZZHD_Id);

    //根据id字符数组删除多条党员组织活动
    @Delete("delete from dangyuanzuzhihuodong where DYZZHD_Id in (#{DYZZHD_Ids})")
    boolean DeleteInfoMany(@Param("DYZZHD_Ids") String DYZZHD_Ids);

    //根据区搜索镇
    @Select("select * from zhen where Z_region=#{quID}")
    List<Zhen> list_zhen(@Param("quID") Integer quID);

    //根据镇搜索村
    @Select("select * from village where V_Zhen_xiang=#{zhenId}")
    List<Village> list_village(@Param("zhenId") Integer zhenId);

    //添加一个党员组织活动类型
    @Insert("insert into huodongleixing (HDLX_Name,HDLX_Sheng,HDLX_Shi,HDLX_Qu,HDLX_Zhen,HDLX_Village) values(#{hdlxName},#{hdlxSheng},#{hdlxShi},#{hdlxQu},#{hdlxZhen},#{hdlxVillage})")
    Integer AddInfoType(Huodongleixing huodongleixing);

    //根据区id查询党员组织活动类型
    @Select("select * from huodongleixing where HDLX_Qu=#{HDLX_Qu}")
    List<Huodongleixing> GetInfoTypeQu(@Param("HDLX_Qu") Integer HDLX_Qu);

    //根据镇id查询党员组织活动类型
    @Select("select * from huodongleixing where HDLX_Zhen=#{HDLX_Zhen}")
    List<Huodongleixing> GetInfoTypeZhen(@Param("HDLX_Zhen") Integer HDLX_Zhen);

    //根据村id查询党员组织活动类型
    @Select("select * from huodongleixing where HDLX_Village=#{HDLX_Village}")
    List<Huodongleixing> GetInfoTypeVillage(@Param("HDLX_Village") Integer HDLX_Village);

    //添加一个党员组织活动
    @Insert("insert into dangyuanzuzhihuodong (DYZZHD_Name,DYZZHD_Content,DYZZHD_Photo,DYZZHD_ZhenShiLuJing,DYZZHD_Place,DYZZHD_Time,DYZZHD_LeiXing,DYZZHD_Villageid) values(#{dyzzhdName},#{dyzzhdContent},#{dyzzhdPhoto},#{dyzzhdZhenshilujing},#{dyzzhdPlace},#{dyzzhdTime},#{dyzzhdLeixing},#{dyzzhdVillageid})")
    Integer AddInfo(Dangyuanzuzhihuodong dangyuanzuzhihuodong);

    //查询单个组织活动类型
    @Select("select HDLX_Id from huodongleixing where HDLX_Village=#{HDLX_Village} and HDLX_Name=#{HDLX_Name}")
    Integer GetTypeId(@Param("HDLX_Village") Integer HDLX_Village, @Param("HDLX_Name") String HDLX_Name);

    /*//修改一个党员组织活动信息
    @Update("update dangyuanzuzhihuodong set DYZZHD_Name=#{dyzzhdName},DYZZHD_Content=#{dyzzhdContent},DYZZHD_Photo=#{dyzzhdPhoto},DYZZHD_ZhenShiLuJing=#{dyzzhdZhenshilujing},DYZZHD_Place=#{dyzzhdPlace},DYZZHD_Time=#{dyzzhdTime},DYZZHD_LeiXing=#{dyzzhdLeixing}")
    Integer UpdateInfo(Dangyuanzuzhihuodong dangyuanzuzhihuodong);*/

    //修改一个党员组织活动信息
    @Update("update dangyuanzuzhihuodong set DYZZHD_Name=#{dyzzhdName},DYZZHD_Content=#{dyzzhdContent},DYZZHD_Photo=#{dyzzhdPhoto},DYZZHD_ZhenShiLuJing=#{dyzzhdZhenshilujing},DYZZHD_Place=#{dyzzhdPlace},DYZZHD_Time=#{dyzzhdTime},DYZZHD_LeiXing=#{dyzzhdLeixing} where DYZZHD_Id = #{dyzzhdId}")
    Integer UpdateInfo(Dangyuanzuzhihuodong dangyuanzuzhihuodong);


    //根据序号查找一个党员组织活动的信息
    @Select("select * from dangyuanzuzhihuodong where DYZZHD_xvhao=#{dyzzhdXvhao}")
    Dangyuanzuzhihuodong GetInfoByXvhao(@Param("dyzzhdXvhao") String dyzzhdXvhao);

    @Select("select HDLX_Name from huodongleixing where HDLX_Village=#{HDLX_Village} and HDLX_ID=#{HDLX_ID}")
    String GetTypeName(@Param("HDLX_Village") Integer HDLX_Village, @Param("HDLX_ID") Integer HDLX_ID);
}
