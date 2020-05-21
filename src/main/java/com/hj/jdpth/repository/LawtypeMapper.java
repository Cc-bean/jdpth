package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Law;
import com.hj.jdpth.domain.Lawtype;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "lawtypeMapper")
public interface LawtypeMapper {
    @Select("select * from lawtype")
    List<Lawtype> queryAllType_yyq();

    @Insert("insert into lawtype (L_name,L_sheng,L_shi,L_region,L_zhen) values (#{lName},#{lSheng},#{lShi},#{lRegion},#{lZhen})")
    @Options(useGeneratedKeys = false, keyProperty = "lKey", keyColumn = "L_Key")
    int insertLawtype_yyq(Integer lawtype);

    @Delete("delete from lawtype where L_Key=#{id}")
    int deletelawType_yyq(Integer id);

    @Update("update lawtype set L_name=#{lName},L_sheng=#{lSheng},L_shi=#{lShi},L_region=#{lRegion},L_zhen=#{lZhen} where L_Key=#{lKey}")
    int updateLawType_yyq(Lawtype lawtype);

    @Select("select * from lawtype where L_Key=#{id}")
    Lawtype queryLawTypeById(Integer id);
}
