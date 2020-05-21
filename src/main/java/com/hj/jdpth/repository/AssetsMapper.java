package com.hj.jdpth.repository;


import com.hj.jdpth.domain.Assets;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "assetsMapper")
public interface AssetsMapper {
    @Select("select A_Type from assets ")
    List<Assets> quaryAllType();

    @Select("select * from assets where A_Villageid=#{villageId} and A_Zu=#{zuId}")
    List<Assets> quaryAllByVidZid(@Param("villageId") Integer villageId, @Param("zuId") Integer zuId);

    //插入资产表
    @Insert("insert into assets (A_Type,A_Villageid,A_Zu) values ( #{aType}, #{aVillageid}, #{aZu})")
    @Options(useGeneratedKeys = true, keyProperty = "assetsId")
    Integer addAssets(Assets assets);

    //查找
    @Select("select Assets_Id from assets where A_Zu =#{A_Zu} and A_Villageid =#{A_Villageid} and A_Type = #{A_Type}")
    Assets quaryAssets_Id(@Param("A_Zu") Integer A_Zu, @Param("A_Villageid") Integer A_Villageid, @Param("A_Type") String A_Type);

}
