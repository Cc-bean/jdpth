package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Villagesurvey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component(value = "villagesurveyMapper")
public interface VillagesurveyMapper {

    @Select("select * from villagesurvey where VillageSurvey_Id=#{id}")
    Villagesurvey FindById_lfm(Integer id);

    @Insert("insert into villagesurvey (VS_Villageid,VS_Survey,VS_VillageHead,VS_population,VS_place,VS_shuji) values (#{vsVillageid},#{vsSurvey},#{vsVillagehead},#{vsPopulation},#{vsPlace},#{vsShuji})")
    boolean VInsert(Villagesurvey villagesurvey);

    @Update("update villagesurvey set VS_Villageid=#{vsVillageid},VS_Survey=#{vsSurvey},VS_VillageHead=#{vsVillagehead},VS_population=#{vsPopulation},VS_place=#{vsPlace},VS_shuji=#{vsShuji} where VillageSurvey_Id=#{villagesurveyId}")
    boolean VUpdate(Villagesurvey villagesurvey);
}
