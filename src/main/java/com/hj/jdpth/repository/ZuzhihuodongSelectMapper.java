package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Dangyuanzuzhihuodong;
import com.hj.jdpth.domain.Village;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component(value = "ZuzhihuodongSelectMapper")
public interface ZuzhihuodongSelectMapper {
    @SelectProvider(type = ZuzhihuodongSelect.class, method = "ZuzhihuodongSelectAll")
    Page<Dangyuanzuzhihuodong> GetInfoSelectAll(@Param("quId") Integer quId, @Param("zhenId") Integer zhenId, @Param("cunId") Integer cunId, @Param("typeId") Integer typeId);

    @SelectProvider(type = ZuzhihuodongSelect.class, method = "queryZuzhihuodongSelectByTime")
    Page<HashMap> GetInfoSelectByTime(@Param("quId") Integer quId, @Param("zhenId") Integer zhenId, @Param("cunId") Integer cunId, @Param("startTime") String startTime, @Param("stopTime") String stopTime);

    @SelectProvider(type = ZuzhihuodongSelect.class, method = "GetVillage")
    Page<Village> GetVillage(@Param("cunId") Integer cunId);

    @SelectProvider(type = ZuzhihuodongSelect.class, method = "FindByZhenAndTime")
    Page<HashMap> FindByZhenAndTime(@Param("zhenId") Integer zhenId, @Param("year") String year);

    class ZuzhihuodongSelect {

        public String FindByZhenAndTime(@Param("zhenId") Integer zhenId, @Param("year") String year) {
            return new SQL() {{
                SELECT("DYZZHD_Id,DYZZHD_Name,DYZZHD_Content,DYZZHD_Photo,DYZZHD_ZhenshiluJing,DYZZHD_Place,DYZZHD_Time,HDLX_Name,V_Name,Z_Name");
                FROM("dangyuanzuzhihuodong,huodongleixing,zhen,village");
                WHERE("year(DYZZHD_Time) = '" + year + "' AND HDLX_Id = DYZZHD_LeiXing AND HDLX_Village = Village_Id AND Zhen_id = V_Zhen_xiang");
                if (zhenId != 0) {
                    AND().WHERE("DYZZHD_Villageid in(select Village_Id from village where V_Zhen_xiang =" + zhenId + ")");
                }
                ;
            }}.toString();

        }


        public String ZuzhihuodongSelectAll(@Param("quId") Integer quId, @Param("zhenId") Integer zhenId, @Param("cunId") Integer cunId, @Param("typeId") Integer typeId) {
            return new SQL() {{
                SELECT("*");
                FROM("dangyuanzuzhihuodong");
                if (typeId != null) {
                    WHERE("DYZZHD_Villageid=" + cunId);
                    AND().WHERE("DYZZHD_LeiXing=" + typeId);
                } else if (cunId != null) {
                    WHERE("DYZZHD_Villageid=" + cunId);
                } else if (zhenId != null) {
                    WHERE("DYZZHD_Villageid in(select Village_Id from village where V_Zhen_xiang =" + zhenId + ")");
                }
                ;
            }}.toString();

        }

        public String queryZuzhihuodongSelectByTime(@Param("quId") Integer quId
                , @Param("zhenId") Integer zhenId
                , @Param("cunId") Integer cunId
                , @Param("startTime") String startTime
                , @Param("stopTime") String stopTime) {
            return new SQL() {{
                SELECT("DYZZHD_Id,DYZZHD_Name,DYZZHD_Content,DYZZHD_Photo,DYZZHD_ZhenshiluJing,DYZZHD_Place,DYZZHD_Time,HDLX_Name");
                FROM("dangyuanzuzhihuodong,huodongleixing");
                if (startTime != null && stopTime != null) {
                    if (cunId != null) {
                        //WHERE("DYZZHD_Villageid="+cunId);
                        WHERE("DYZZHD_Villageid = " + cunId + " AND DYZZHD_Time > '" + startTime + "' AND DYZZHD_Time < '" + stopTime + "'" + " AND HDLX_Id = DYZZHD_LeiXing");
                    } else if (zhenId != null) {
                        WHERE("DYZZHD_Villageid in(select Village_Id from village where V_Zhen_xiang = " + zhenId + ") and DYZZHD_Time > '" + startTime + "' and DYZZHD_Time < '" + stopTime + "'" + " AND HDLX_Id = DYZZHD_LeiXing");
                    } else if (quId != null) {
                        WHERE("DYZZHD_Villageid in(select Village_Id from village where V_Zhen_xiang in(select Zhen_Id from zhen where Z_region = " + quId + "))+DYZZHD_Time > '" + startTime + "' and DYZZHD_Time < '" + stopTime + "'" + " AND HDLX_Id = DYZZHD_LeiXing");
                    }
                } else if (startTime == null && stopTime == null) {
                    if (cunId != null) {
                        WHERE("DYZZHD_Villageid = " + cunId + " AND HDLX_Id = DYZZHD_LeiXing");
                    } else if (zhenId != null) {
                        WHERE("DYZZHD_Villageid in(select Village_Id from village where V_Zhen_xiang = " + zhenId + ")" + " AND HDLX_Id = DYZZHD_LeiXing");
                    } else if (quId != null) {
                        WHERE("DYZZHD_Villageid in(select Village_Id from village where V_Zhen_xiang in(select Zhen_Id from zhen where Z_region = " + quId + "))" + " AND HDLX_Id = DYZZHD_LeiXing");
                    }
                }
                ;
            }}.toString();
        }

        public String GetVillage(@Param("cunId") Integer cunId) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("village");
                    WHERE("Village_Id=" + cunId);
                }
            }.toString();
        }
    }
}
