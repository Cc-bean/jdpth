package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Cunfazhan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(value = "cunfazhanMapper")
public interface CunfazhanMapper {
    //搜索村发展规划
    @Select("select * from ((cunfazhan inner join village on CFZ_village=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id)inner join region on Z_region=R_key where R_Key=#{regionId}")
//按区Id查村发展规划
    Page<Map<String, Object>> findfaZhanByRegionId(Integer regionId);

    @Select("select * from ((cunfazhan inner join village on CFZ_village=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id)inner join region on Z_region=R_key where Zhen_Id=#{zhenId}")
//按镇Id查村发展规划
    Page<Map<String, Object>> findfaZhanByZhenId(Integer zhenId);

    @Select("select * from ((cunfazhan inner join village on CFZ_village=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id)inner join region on Z_region=R_key where Village_Id=#{cunId}")
//按村Id查村发展规划
    Page<Map<String, Object>> findfaZhanByCunId(Integer cunId);

    //按发展规划ID查询公告详情
    @Select("select * from ((cunfazhan inner join village on CFZ_village=Village_Id)inner join zhen on V_Zhen_xiang=Zhen_Id)inner join region on Z_region=R_key where CFZ_Id=#{id}")
    Map<String, Object> findFazhanXingQing_yp(Integer id);

    //删除一条记录
    @Delete("delete from cunfazhan where CFZ_Id=#{id}")
    Boolean deleteFazhanById_yp(Integer id);

    //添加一条记录
    @Insert("insert into cunfazhan (CFZ_Content,CFZ_village,CFZ_xuhao)values(#{cfzContent},#{cfzVillage},#{cfzXuhao}) ")
    int insertFazhan_yp(Cunfazhan cunfazhan);

    //编辑 更新一条记录
    @Update("update cunfazhan set CFZ_Content=#{cfzContent},CFZ_village=#{cfzVillage},CFZ_xuhao=#{cfzXuhao} where CFZ_Id=#{cfzId}")
    Boolean updateFazhan_yp(Cunfazhan cunfazhan);

    //根据序号查找一个发展规划的信息
    @Select("select * from cunfazhan where CFZ_xuhao=#{cfzXuhao}")
    Cunfazhan findFazhanByxuhao_yp(@Param("cfzXuhao") String cfzXuhao);


}
