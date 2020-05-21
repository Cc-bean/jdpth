package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Law;
import com.hj.jdpth.domain.Zhen;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "lawMapper")
public interface LawMapper {

    @SelectProvider(type = LawProvider.class, method = "lawProvider")
    Page<Law> queryAllLaw_yyq(@Param("L_name") String L_name, @Param("value") String value);

    class LawProvider {
        public String lawProvider(@Param("L_name") String L_name, @Param("value") String value) {
            SQL sql = new SQL();
            sql.SELECT("law.*");
            sql.FROM("law inner join lawtype on law.L_Type=lawtype.L_Key");
            if (!L_name.equals("")) {
                sql.AND();
                sql.WHERE("lawtype.L_name=#{L_name}");
            }
            if (!value.equals("")) {
                sql.AND();
                sql.WHERE("L_Title like '${value}%'");
            }
            return sql.toString();
        }
    }

    @Select("select * from law where Law_Id=#{id}")
    Law queryByLawId_yyq(Integer id);


    @Insert("insert into law (L_People,L_Title,L_Content,L_pubdate,L_Type) values (#{lPeople},#{lTitle},#{lContent},#{lPubdate},#{lType})")
    int insertLaw_yyq(Law law);

    @Delete("delete from law where Law_Id=#{id}")
    int deleteLaw_yyq(Integer id);


    @Update("update law set L_People=#{lPeople},L_Title=#{lTitle},L_Content=#{lContent},L_pubdate=#{lPubdate},L_Type=#{lType} where Law_Id=#{lawId}")
    int updateLaw_yyq(Law law);


    @Select("select count(*) from law")
    int lawNumber();

    @SelectProvider(type = queryLeaderLaw_yyq.class, method = "queryLeaderLaw_yyq")
    List<Law> queryLeaderLaw_yyq(@Param("L_name") String L_name, @Param("zhen_id") Integer zhen_id);

    class queryLeaderLaw_yyq {
        public String queryLeaderLaw_yyq(@Param("L_name") String L_name, @Param("zhen_id") Integer zhen_id) {
            SQL sql = new SQL();
            sql.SELECT("law.*");
            sql.FROM("law inner join lawtype on law.L_Type=lawtype.L_Key");
            if (!L_name.equals("")) {
                sql.AND();
                sql.WHERE("lawtype.L_name=#{L_name}");
            }
            if (zhen_id != 0) {
                sql.AND();
                sql.WHERE("lawtype.L_zhen=#{zhen_id}");
            }
            return sql.toString();
        }
    }

    @Select("select Zhen_Id from zhen where Z_Name like '${value}%'")
    Integer Zhen_Id(String value);
}
