package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Jiaoyv;
import com.hj.jdpth.domain.Manager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

@Component(value = "JiaoyvzixunListMapper")
public interface JiaoyvzixunListMapper {
    //首次进入
    @SelectProvider(type = GetInfoFirst.class, method = "getInfoFirst")
    Page<Jiaoyv> GetInfoAllFirst(@Param("quId") Integer quId, @Param("zhenId") Integer zhenId, @Param("cunId") Integer cunId, @Param("adminTypeId") Integer adminTypeId);

    class GetInfoFirst {
        public String getInfoFirst(@Param("quId") Integer quId, @Param("zhenId") Integer zhenId, @Param("cunId") Integer cunId, @Param("adminTypeId") Integer adminTypeId) {
            return new SQL() {{
                SELECT("*");
                FROM("jiaoyv");
                if (adminTypeId == 1 || adminTypeId == 2 || adminTypeId == 3) {
                    WHERE("JY_Qu=" + quId);
                } else if (adminTypeId == 4) {
                    WHERE("JY_Zhen=" + zhenId);
                } else if (adminTypeId == 5) {
                    WHERE("JY_Village=" + cunId);
                } else {
                    WHERE("JY_Shi=" + 151);
                }
            }}.toString();
        }
    }

    @Select("select * from jiaoyv where JY_Id=#{JY_Id}")
    Jiaoyv GetInfoOne(@Param("JY_Id") Integer JY_Id);

    @Delete("delete from jiaoyv where JY_Id=#{JY_Id}")
    boolean DeleteInfoOne(@Param("JY_Id") Integer JY_Id);

    //根据管理员id查找管理员
    @Select("select * from manager where Manager_Id=#{managerId}")
    Manager GetInfoManager(@Param("managerId") Integer managerId);
}
