package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Shi;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface ShiMapper {
    @Select("select * from shi where S_sheng=#{id}")
    List<Shi> queryShiByShengId_yyq(Integer id);

    @Select("select * from shi where S_Key=#{id}")
    Shi queryShiById_yyq(Integer id);

    ///
    //根据省Id查找市
    @Select("select S_Key,S_name from shi where S_sheng=#{id}")
    public List<HashMap> selectShiNameAdId(int id);

    //根据ID查市名字
    @Select("select S_name from shi where S_Key=#{id}")
    public String selectNameByID(int id);
    ///
}
