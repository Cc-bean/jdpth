package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Policytype;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("policyTypeMapper")
public interface PolicyTypeMapper {
    @Select("select * from policyType")
    public List<Policytype> selectAllXinxi();

    @Insert("insert into policyType (PT_Name) values(#{ptName})")
    int insertPolicytype(Policytype policytype);


}
