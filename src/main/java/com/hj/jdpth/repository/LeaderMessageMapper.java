package com.hj.jdpth.repository;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 调取各项信息最新资源
 */
@Component(value = "leaderMessageMapper")
public interface LeaderMessageMapper {

    /*调取有关资金最新条目
     * */
    @Select("SELECT zhen.Z_region, zhen.Zhen_Id,zhen.Z_Name,village.Village_Id,village.V_Name,capital.Capital_Id,capital.C_Type,capitaldetails.CapitalDetails_Id,capitaldetails.CD_XiangQing,capitaldetails.CD_ZiJinId,MAX(CD_Time),capitaldetails.CD_Income,capitaldetails.CD_Expenditure \n" +
            "FROM capitaldetails JOIN capital on CD_ZiJinId=Capital_Id \n" +
            "RIGHT JOIN village on capital.C_Villageid=village.Village_Id \n" +
            "JOIN zhen on V_Zhen_xiang=Zhen_Id GROUP BY Village_Id")
    List<HashMap> moneyMessages();

    /*调取有关组织发展最新条目
     * */
    @Select("select * from(\n" +
            "(select * from (SELECT j.JJFZ_Name as name,j.JJFZ_Sex as sex,j.JJFZ_Time as time,j.JJFZ_Phone as phone,j.JJFZ_WenHua as wenhua,j.JJFZ_shenfenzheng as shenfenzheng,j.JJFZ_ZhiWu as zhiwu,j.JJFZ_Entity as entity,j.JJFZ_Village as village,v.V_Name,z.Z_Name FROM jijifenzi j left outer join village v on j.JJFZ_Village=v.Village_Id left outer join zhen z on v.V_Zhen_xiang=z.Zhen_Id order by time desc)as base GROUP BY village)union\n" +
            "(select * from(SELECT f.FZDX_Name as name, f.FZDX_Sex as sex,f.FZDX_Time as time,f.FZDX_Phone as phone,f.FZDX_WenHua as wenhua,f.FZDX_shenfenzheng as shenfenzheng,f.FZDX_ZhiWu as zhiwu,f.FZDX_Entity as entity,F.FZDX_Village as village,v.V_Name,z.Z_Name FROM fazhanduixiang f left outer join village v on F.FZDX_Village=v.Village_Id left outer join zhen z on v.V_Zhen_xiang=z.Zhen_Id  order by time desc)as base GROUP BY village)union\n" +
            "(select * from(SELECT y.YBDY_Name as name,y.YBDY_Sex as sex,y.YBDY_Time as time,y.YBDY_Phone as phone,y.YBDY_WenHua as wenhua,y.YBDY_shengfenzheng as shenfenzheng,y.YBDY_ZhiWu as zhiwu,y.YBDY_Entity as entity,y.YBDY_Village as village,v.V_Name,z.Z_Name FROM yvbeidangyuan y left outer join village v on y.YBDY_Village=v.Village_Id left outer join zhen z on v.V_Zhen_xiang=z.Zhen_Id order by time desc)as base  GROUP BY village )union\n" +
            "(select * from(SELECT d.DYZZ_Name as name,d.DYZZ_Sex as sex,d.DYZZ_Time as time,d.DYZZ_Phone as phone,d.DYZZ_WenHua as wenhua,d.DYZZ_ShengFengZheng as shenfenzheng,d.DYZZ_ZhiWu as zhiwu,d.DYZZ_Entity as entity,d.DYZZ_Village as village,v.V_Name,z.Z_Name FROM dangyuanzhuanzheng d left outer join village v on d.DYZZ_Village=v.Village_Id left outer join zhen z on v.V_Zhen_xiang=z.Zhen_Id order by time desc)as base GROUP BY village)order by time desc)as base  GROUP BY village")
    List<HashMap> zzfzMessages();

    /*调取有关资金最新条目
     * */
    /*调取有关资金最新条目
     * */


}
