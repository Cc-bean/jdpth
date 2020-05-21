package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Villagesurvey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component(value = "BasicVillageMapper")
public interface BasicVillageMapper {
    @Select("select * from village where Village_Id=#{id}")
    Village FindById(Integer id);

    @Delete("delete from village where Village_Id=#{id}")
    boolean DeleteBasic(Integer id);

    @Delete("delete from villagesurvey where VS_Villageid=#{id}")
    boolean DeleteVS(Integer id);

    @Select("select * from villagesurvey where VS_Villageid=#{id}")
    Villagesurvey FindByVid(Integer id);
}
