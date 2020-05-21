package com.hj.jdpth.repository;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Component("policyStatuMapper")
public interface PolicyStatuMapper {
    //根据ID 查找 面貌名
    @Select("select P_name from policyStatu where P_Key=#{id}")
    public String selectNameByID(int id);

    //政治面貌列表
    @Select("select P_name,P_Key from policyStatu ")
    public List<HashMap> selectNameAdID();

    //根据名称查找面貌]
    @Select("select P_Key from policyStatu where P_name=#{name}")
    Integer seletIDBYName(String name);
}
