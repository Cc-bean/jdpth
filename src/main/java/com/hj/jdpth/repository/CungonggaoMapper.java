package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Villagebulletin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(value = "cungonggaoMapper")
public interface CungonggaoMapper {
    //搜索村公告
    @Select("select * from (villagebulletin inner join village on VB_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where Z_region=#{regionId}")
//按区Id查村公告
    Page<Map<String, Object>> findgongGaoByRegionId(Integer regionId);

    @Select("select * from (villagebulletin inner join village on VB_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where Zhen_Id=#{zhenId}")
//按镇Id查村公告
    Page<Map<String, Object>> findgongGaoByZhenId(Integer zhenId);

    @Select("select * from (villagebulletin inner join village on VB_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where Village_Id=#{cunId}")
//按村Id查村公告
    Page<Map<String, Object>> findgongGaoByCunId(Integer cunId);

    @Select("select * from (villagebulletin inner join village on VB_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where VB_Title like '${value}%' ")
//按名字查村公告
    Page<Map<String, Object>> findgongGaoBygongGaoName(@Param("value") String value);

    //按公告ID查询公告详情
    @Select("select * from (villagebulletin inner join village on VB_Villageid=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id where VillageBulletin_Id=#{id}")
    Map<String, Object> findgongGaoXingQing_yp(Integer id);

    //删除一条记录
    @Delete("delete from villagebulletin where VillageBulletin_Id=#{id}")
    Boolean deleteGonggaoById_yp(Integer id);

    //添加一条记录
    @Insert("insert into villagebulletin (VB_LanchPersonId,VB_Villageid,VB_Title,VB_Content,VB_LanchTime,VB_xuhao)values(#{vbLanchpersonid},#{vbVillageid},#{vbTitle},#{vbContent},#{vbLanchtime},#{vbXuhao}) ")
    int insertGonggao_yp(Villagebulletin villagebulletin);

    //编辑 更新一条记录
    @Update("update villagebulletin set VB_LanchPersonId=#{vbLanchpersonid},VB_Villageid=#{vbVillageid},VB_Title=#{vbTitle},VB_Content=#{vbContent},VB_LanchTime=#{vbLanchtime} where VillageBulletin_Id=#{villagebulletinId}")
    Boolean updateGonggao_yp(Villagebulletin villagebulletin);

    //根据序号查找一个村公告的信息
    @Select("select * from villagebulletin where VB_xuhao=#{vbXuhao}")
    Villagebulletin findgonggaoByxuhao_yp(@Param("vbXuhao") String vbXuhao);

}
