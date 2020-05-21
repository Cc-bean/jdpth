package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Sheng;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface ShengMapper {
    @Select("select * from sheng")
    List<Sheng> queryAllSheng_yyq();

    @Select("select * from sheng where S_Key=#{id}")
    Sheng queryById_yyq(Integer id);

    ///
    //省下拉列表
    @Select("select S_Key,S_name from sheng")
    public List<HashMap> selectShengAdId();

    //根据id 查省名字
    @Select("select S_name from sheng where S_Key=#{id}")
    public String selectNameById(int id);
    ///
}
