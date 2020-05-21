package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Nation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "nationMapper")
public interface NationMapper {
    //查民族名字ByID
    @Select("select N_name from nation where N_Key=#{id}")
    String queryById_yyq(Integer id);

    @Select("select * from nation")
    List<Nation> queryAll_yyq();

    //    //导入
    @Select("select N_Key from nation where N_name=#{name}")
    Integer selectIDBYName(String name);

}
