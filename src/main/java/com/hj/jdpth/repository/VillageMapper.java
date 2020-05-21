package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Villagesurvey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "villageMapper")
public interface VillageMapper {
    /////
    //级联用
    //根据镇ID查村ID
    @Select("select Village_Id,V_Name from village where V_Zhen_Xiang=#{id}")
    List<HashMap> selectIDByZhen_Id(int id);

    //根据村ID选择镇
    @Select("select V_Zhen_Xiang from village where Village_Id=#{id}")
    Integer selectZhenID(int id);

    //级联仅1可选
    @Select("select Village_Id,V_Name from village where Village_Id=#{cunId}")
    List<HashMap> selectIDAdName(int cunId);

    //根据ID查村名daoru
    @Select("select V_Name from village where Village_Id=#{id}")
    String selectNameByID(int id);

    //动态查询村Id
    @SelectProvider(type = villageSqlProvider.class, method = "selectIDDynamic")
    List<Integer> selectIDDynamic(Map<String, Object> map);


    //////////////////////////////////动态sql
    class villageSqlProvider {
        public String selectIDDynamic(Map<String, Object> map) {
            return new SQL() {
                {
                    SELECT("Village_Id");
                    FROM("village");
                    if (map.get("zhenId") != null && !map.get("zhenId").equals("")) {
                        WHERE(" V_Zhen_xiang = #{zhenId}");

                    } else if (map.get("regionId") != null && !map.get("regionId").equals("")) {
                        WHERE("V_Region = #{regionId}");
                    }
                }

            }.toString();
        }
    }
    /////


    @Select("select * from village where Village_Id=(select M_VillageId from manager where Manager_Id=#{id})")
    Village FindBymanager(Integer id);

    @Select("select * from village where Village_Id=#{id}")
    Village FindById_lfm(Integer id);

    @SelectProvider(type = SearchVillage_lfmProvider.class, method = "getPartmember")
//value是时间
    Page<Village> SearchVillage_lfm(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid);

    class SearchVillage_lfmProvider {
        public String getPartmember(@Param("qid") Integer qid, @Param("zhenid") Integer zhenid, @Param("vid") Integer vid) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("village");
            if (qid != 0 && zhenid == 0 && vid == 0) {
                sql.AND();
                sql.WHERE("V_Region=#{qid}");
            }
            if (qid != 0 && zhenid != 0 && vid == 0) {
                sql.AND();
                sql.WHERE("V_Zhen_xiang=#{zhenid}");
            }
            if (qid != 0 && zhenid != 0 && vid != 0) {
                sql.AND();
                sql.WHERE("Village_Id=#{vid}");
            }
            return sql.toString();
        }
    }

    //区查村
    @Select("select Village_Id,V_Name,V_Zhen_xiang,V_Region from village where V_Zhen_xiang  in (select Zhen_Id  from zhen where Z_region = #{Z_region}) ")
    List<Village> queryByQv_gsh(int Z_region);

    //镇查村
    @Select("select Village_Id,V_Name,V_Zhen_xiang,V_Region from village where V_Zhen_xiang = #{V_Zhen_xiang}")
    List<Village> queryByZhen_gsh(int V_Zhen_xiang);

    //村id查村
    @Select("select Village_Id,V_Name,V_Zhen_xiang,V_Region from village where Village_Id = #{id}")
    Village queryByid_gsh(int id);

    //村名查村id
    @Select("select Village_Id,V_Name from village where V_Name =#{Name}")
    Village queryByVillageName_gsh(String Name);

    @Select("select Village_Id,V_Name from village where V_Zhen_xiang=#{zhen_id}")
    List<Village> queryVillageByZhenId_yyq(Integer zhen_id);

    @Select("select Village_Id,V_Name from village where Village_Id=#{cun_id}")
    Village queryByCunId_yyq(Integer cun_id);

    @Options(useGeneratedKeys = true, keyProperty = "villageId")
    @Insert("insert into village (V_Population,V_ImagePath1,V_ImagePath2,V_ImagePath3,V_Landship,V_Area,V_Houses,V_Adress,V_Shi,V_Region,V_Province,V_Name,V_beiyong1,V_beiyong2,V_beiyong3,V_Zhen_xiang) values (#{vPopulation},#{vImagepath1},#{vImagepath2},#{vImagepath3},#{vLandship},#{vArea},#{vHouses},#{vAdress},#{vShi},#{vRegion},#{vProvince},#{vName},#{vBeiyong1},#{vBeiyong2},#{vBeiyong3},#{vZhenXiang})")
    boolean Insert(Village village);

    @Options(useGeneratedKeys = true, keyProperty = "villageId")
    @Update("update village set V_Population=#{vPopulation},V_ImagePath1=#{vImagepath1},V_ImagePath2=#{vImagepath2},V_ImagePath3=#{vImagepath3},V_Landship=#{vLandship},V_Area=#{vArea},V_Houses=#{vHouses},V_Adress=#{vAdress},V_Shi=#{vShi},V_Region=#{vRegion},V_Province=#{vProvince},V_Name=#{vName},V_beiyong1=#{vBeiyong1},V_beiyong2=#{vBeiyong2},V_beiyong3=#{vBeiyong3},V_Zhen_xiang=#{vZhenXiang} where Village_Id=#{villageId}")
    boolean Update(Village village);

    @Select("select * from villagesurvey where VS_Villageid=#{vsVillageid}")
    Villagesurvey FindByVid(Integer vsVillageid);

    /**
     * 查询所有村 用于对应
     *
     * @return list cunid 村name
     */
    @Select("select Village_Id,V_Name from village ")
    @Results({@Result(column = "Village_Id", property = "villageId"),
            @Result(column = "V_Name", property = "vName"),
    })
    List<HashMap> cacheUse();
}
