package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Jiaoyv;
import com.hj.jdpth.domain.Manager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;


@Component(value = "JisoyvzixunSelectMapper")
public interface JisoyvzixunSelectMapper {
    @SelectProvider(type = JisoyvzixunSelect.class, method = "jiaoyvzixunSelect")
    Page<Jiaoyv> GetInfoSelect(Integer quId, Integer zhenId, Integer cunId);

    class JisoyvzixunSelect {
        public String jiaoyvzixunSelect(Integer quId, Integer zhenId, Integer cunId) {
            return new SQL() {{
                SELECT("*");
                FROM("jiaoyv");
                if (cunId != null) {
                    WHERE("JY_Village=" + cunId);
                } else if (zhenId != null) {
                    WHERE("JY_Zhen=" + zhenId);
                } else if (quId != null) {
                    WHERE("JY_Qu=" + quId);
                } else {
                    WHERE("JY_Shi=" + 151);
                }
            }}.toString();
        }
    }

    //添加一个教育资讯文件
    @Insert("insert into jiaoyv (JY_LuJing,JY_ZhenShiLuJing,JY_NmaeOfFile,JY_Sheng,JY_Shi,JY_Qu,JY_Zhen,JY_Village,JY_Time,JY_People)values(#{jyLujing},#{jyZhenshilujing},#{jyNmaeoffile},#{jySheng},#{jyShi},#{jyQu},#{jyZhen},#{jyVillage},#{jyTime},#{jyPeople})")
    Integer AddInfo(Jiaoyv jiaoyv);


}
