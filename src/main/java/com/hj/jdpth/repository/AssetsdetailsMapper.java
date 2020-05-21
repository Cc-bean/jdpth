package com.hj.jdpth.repository;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "assetsdetailsMapper")
public interface AssetsdetailsMapper {
    //查询资产类型
    @Select("select * from assets where Assets_Id = #{assetsId}")
    @Results(value = {
            @Result(id = true, column = "Assets_Id", property = "assetsId"),
            @Result(column = "A_Type", property = "aType")
    })
    Page<Assets> queryAssets(int assetsId);


    @SelectProvider(type = AssertsProvider.class, method = "getAsserts")
    @Results(value = {
            @Result(id = true, column = "ZCMZ_Id", property = "zcmzId"),
            @Result(column = "ZCMZ_LeiXing", property = "assets", one = @One(select = "com.hj.jdpth.repository.AssetsdetailsMapper.queryAssets"))
    })
    Page<AssetsAndAssetsdetails> quaryQZC(@Param("qu_id") Integer qu_id, @Param("zhen_id") Integer zhen_id, @Param("cun_id") Integer cun_id, @Param("value") String value);

    class AssertsProvider {
        public String getAsserts(@Param("qu_id") Integer qu_id, @Param("zhen_id") Integer zhen_id, @Param("cun_id") Integer cun_id, @Param("value") String value) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("zichanmingzi ");
            if (qu_id != 0 && zhen_id == 0 && cun_id == 0) {
                sql.AND();
                sql.WHERE(" ZCMZ_LeiXing in " +
                        "(select Assets_Id from assets  where A_Villageid in " +
                        "(select Village_Id from village where V_Region = #{qu_id}))");
            }
            if (qu_id != 0 && zhen_id != 0 && cun_id == 0) {
                sql.AND();
                sql.WHERE(" ZCMZ_LeiXing in " +
                        "(select Assets_Id from assets  where A_Villageid in " +
                        "(select Village_Id from village where V_Zhen_xiang = #{zhen_id} ))");
            }
            if (qu_id != 0 && zhen_id != 0 && cun_id != 0) {
                sql.AND();
                sql.WHERE(" ZCMZ_LeiXing in (select Assets_Id from assets where A_Villageid =#{cun_id} )");
            }
            if (value != null) {
                sql.AND();
                sql.WHERE("ZCMZ_LeiXing in (select Assets_Id from assets where A_Type like '${value}%' )");
            }
            return sql.toString();
        }
    }

    //查询全部的资产类型
    @Select("select * from assets")
    List<Assets> quaryAllType();

    //查询全部的资产名字表
    @Select("select * from zichanmingzi ")
    List<Zichanmingzi> quaryZiChanMingZi();


    //查询资产类型
    @Select("select zichanmingzi.ZCMZ_Name,zichanmingzi.ZCMZ_Id ,assets.A_Type   from zichanmingzi,assets where zichanmingzi.ZCMZ_LeiXing=assets.Assets_Id and  zichanmingzi.ZCMZ_LeiXing in (select Assets_Id from assets where A_Type =#{A_Type})")
    Page<Map<Assets, Zichanmingzi>> quaryByType(String A_Type);

    //单删
    @Delete("delete from zichanmingzi where ZCMZ_Id =#{id}")
    boolean deleteZichanmingzi(Integer id);

    //根据主键查资产类型表
    @Select("select * from zichanmingzi where ZCMZ_Id = #{id}")
    Zichanmingzi quaryById(Integer id);

    //根据组id和村id查询资源类型
    @Select("select * from assets  where A_Villageid =#{A_Villageid} and A_Zu =#{A_Zu}")
    List<Assets> quaryA_Type(@Param("A_Villageid") Integer A_Villageid, @Param("A_Zu") Integer A_Zu);

    @Select("select * from assets where A_Villageid =#{A_Villageid} ")
    List<Assets> quaryA_Type_gsh(@Param("A_Villageid") Integer A_Villageid);

    //根据资产经营/台账表的类型查询资产名字表类型
    @Select("select ZCMZ_Name from zichanmingzi where ZCMZ_Id = #{id}")
    String quaryZCMZ_Name(Integer id);

    //根据资产经营/台账表的类型查询资产名字表类型
    @Select("select * from zichanmingzi where ZCMZ_Id = #{id}")
    Zichanmingzi quaryZCMZ(Integer id);

    //根据资产类型和资产名字查询资产名字表（查重复）
    @Select("select * from zichanmingzi where ZCMZ_Name = #{value} and ZCMZ_LeiXing = #{ZCMZ_LeiXing}")
    Zichanmingzi quaryZCMZ_gsh(@Param("value") String value, @Param("ZCMZ_LeiXing") Integer ZCMZ_LeiXing);

    //根据村id查询区
    @Select("select R_name from region where R_Key =(select V_Region from village where Village_Id =#{id})")
    String quaryQv(Integer id);

    //根据村id查询镇
    @Select("select Z_Name from zhen where Zhen_Id =(select V_Zhen_xiang from village where Village_Id =#{id})")
    String quaryZhen(Integer id);

    //组id查组名
    @Select("select Z_Name from zu where Z_Key =#{id}")
    String quaryZu(Integer id);

    //插入资产名字表
    @Insert("insert into zichanmingzi  (ZCMZ_Name,ZCMZ_LeiXing) values ( #{zcmzName},#{zcmzLeixing})")
    @Options(useGeneratedKeys = true, keyProperty = "zcmzId")
    Integer addAssetsdetails(Zichanmingzi zichanmingzi);

    //查询资产台账表
    @Select("select * from zichantaizhang where ZJTZ_Name = #{ZCMZ_Id}")
    Zichantaizhang quaryZichantaizhang(Integer ZCMZ_Id);


    //查询资产台账表(主键)
    @Select("select * from zichantaizhang where ZJTZ_Id  = #{Id}")
    Zichantaizhang quaryZCTZ(Integer Id);

    //查询资产经营表
    @Select("select * from zichanjingying where ZCJY_Name = #{ZCMZ_Id}")
    Zichanjingying quaryZiChanJingYing(Integer ZCMZ_Id);

    //查询资产经营表（主键）
    @Select("select * from zichanjingying where ZCJY_Id = #{Id}")
    Zichanjingying quaryZCJY(Integer Id);

    //更新资产台账表
    @UpdateProvider(type = UpdateZichantaizhang.class, method = "getSQL")
    Integer updateZichantaizhang(Zichantaizhang zichantaizhang);

    class UpdateZichantaizhang {
        public String getSQL(Zichantaizhang zichantaizhang) {
            SQL sql = new SQL();
            sql.UPDATE("zichantaizhang");
            if (zichantaizhang.getZjtzJilangdanwei() != null) {
                sql.SET("ZJTZ_JiLangDanWei = #{zjtzJilangdanwei}");
            }
            if (zichantaizhang.getZjtzShuliang() != null) {
                sql.SET("ZJTZ_ShuLiang = #{zjtzShuliang}");
            }
            if (zichantaizhang.getZjtzDanjian() != null) {
                sql.SET("ZJTZ_DanJian = #{zjtzDanjian}");
            }
            if (zichantaizhang.getZjtzBiandongqingkuang() != null) {
                sql.SET("ZJTZ_BianDongQingKuang = #{zjtzBiandongqingkuang}");
            }
            if (zichantaizhang.getZjtzXianzhi() != null) {
                sql.SET("ZJTZ_XianZhi = #{zjtzXianzhi}");
            }
            if (zichantaizhang.getZjtzPinpai() != null) {
                sql.SET("ZJTZ_PinPai = #{zjtzPinpai}");
            }
            if (zichantaizhang.getZjtzXinghao() != null) {
                sql.SET("ZJTZ_XingHao = #{zjtzXinghao}");
            }
            if (zichantaizhang.getZjtzGoujianshijian() != null) {
                sql.SET("ZJTZ_GouJianShiJian =#{zjtzGoujianshijian}");
            }
            if (zichantaizhang.getZjtzLaiyuan() != null) {
                sql.SET("ZJTZ_LaiYuan = #{zjtzLaiyuan}");
            }
            if (zichantaizhang.getZjtzYvjinianxian() != null) {
                sql.SET("ZJTZ_YvJiNianXian = #{zjtzYvjinianxian}");
            }
            if (zichantaizhang.getZjtzDepartment() != null) {
                sql.SET("ZJTZ_Department =#{zjtzDepartment}");
            }
            if (zichantaizhang.getZjtzPeople() != null) {
                sql.SET("ZJTZ_People =#{zjtzPeople}");
            }
            if (zichantaizhang.getZjtzZhuangtai() != null) {
                sql.VALUES("ZJTZ_ZhuangTai", "#{zjtzZhuangtai}");
            }
            if (zichantaizhang.getZjtzBeizhu() != null) {
                sql.VALUES("ZJTZ_BeiZhu", "#{zjtzBeizhu}");
            }
            sql.WHERE("ZJTZ_Id = #{zjtzId}");
            return sql.toString();
        }
    }

    //更新资产经营表
    @UpdateProvider(type = UpdateZiChanJingYing.class, method = "getSQL")
    Integer updateZiChanJingYing(Zichanjingying zichanjingying);

    class UpdateZiChanJingYing {
        public String getSQL(Zichanjingying zichanjingying) {
            SQL sql = new SQL();
            sql.UPDATE("zichanjingying");
            if (zichanjingying.getZcjyPhoto() != null) {
                sql.SET("ZCJY_Photo = #{zcjyPhoto}");
            }
            if (zichanjingying.getZcjyZhenshilujing() != null) {
                sql.SET("ZCJY_ZhenShiLuJing = #{zcjyZhenshilujing}");
            }
            if (zichanjingying.getZcjyZhuangtai() != null) {
                sql.SET("ZCJY_ZhuangTai = #{zcjyZhuangtai}");
            }
            if (zichanjingying.getZcjyChengzhuren() != null) {
                sql.SET("ZCJY_ChengZhuRen = #{zcjyChengzhuren}");
            }
            if (zichanjingying.getZcjyHetong() != null) {
                sql.SET("ZCJY_HeTong = #{zcjyHetong}");
            }
            if (zichanjingying.getZcjyHetongqixian() != null) {
                sql.SET("ZCJY_HeTongQiXian = #{zcjyHetongqixian}");
            }
            if (zichanjingying.getZcjyHetongjine() != null) {
                sql.SET("ZCJY_HeTongJine = #{zcjyHetongjine}");
            }
            if (zichanjingying.getZcjyZhifuqinkuang() != null) {
                sql.SET("ZCJY_ZhiFuQinKuang = #{zcjyZhifuqinkuang}");
            }
            if (zichanjingying.getZcjyBeizhu() != null) {
                sql.SET("ZCJY_BeiZhu =#{zcjyBeizhu}");
            }
            if (zichanjingying.getZcjyXvhao() != null) {
                sql.SET("ZCJY_xvhao = #{zcjyXvhao}");
            }
            sql.WHERE("ZCJY_Id = #{zcjyId}");
            return sql.toString();
        }
    }

    //查询资产名字表的主键通过资产名称与资产类型
    @Select("select * from zichanmingzi where ZCMZ_Name = #{ZCMZ_Name} and ZCMZ_LeiXing = #{ZCMZ_LeiXing}")
    List<Zichanmingzi> quaryZMCZ_ID(@Param("ZCMZ_Name") String ZCMZ_Name, @Param("ZCMZ_LeiXing") Integer ZCMZ_LeiXing);

    //插入资产台账表
    @InsertProvider(type = InsertZichantaizhang.class, method = "getSQL")
    Integer insertZCTZ(Zichantaizhang zichantaizhang);

    class InsertZichantaizhang {
        public String getSQL(Zichantaizhang zichantaizhang) {
            SQL sql = new SQL();
            sql.INSERT_INTO("zichantaizhang");
            sql.VALUES("ZJTZ_Name", "#{zjtzName}");
            if (zichantaizhang.getZjtzJilangdanwei() != null) {
                sql.VALUES("ZJTZ_JiLangDanWei", "#{zjtzJilangdanwei}");
            }
            if (zichantaizhang.getZjtzShuliang() != null) {
                sql.VALUES("ZJTZ_ShuLiang", "#{zjtzShuliang}");
            }
            if (zichantaizhang.getZjtzDanjian() != null) {
                sql.VALUES("ZJTZ_DanJian", "#{zjtzDanjian}");
            }
            if (zichantaizhang.getZjtzYuanzhi() != null) {
                sql.VALUES("ZJTZ_YuanZhi", "#{zjtzYuanzhi}");
            }
            if (zichantaizhang.getZjtzBiandongqingkuang() != null) {
                sql.VALUES("ZJTZ_BianDongQingKuang", "#{zjtzBiandongqingkuang}");
            }
            if (zichantaizhang.getZjtzXianzhi() != null) {
                sql.VALUES("ZJTZ_XianZhi", "#{zjtzXianzhi}");
            }
            if (zichantaizhang.getZjtzPinpai() != null) {
                sql.VALUES("ZJTZ_PinPai", "#{zjtzPinpai}");
            }
            if (zichantaizhang.getZjtzXinghao() != null) {
                sql.VALUES("ZJTZ_XingHao", "#{zjtzXinghao}");
            }
            if (zichantaizhang.getZjtzGoujianshijian() != null) {
                sql.VALUES("ZJTZ_GouJianShiJian", "#{zjtzGoujianshijian}");
            }
            if (zichantaizhang.getZjtzLaiyuan() != null) {
                sql.VALUES("ZJTZ_LaiYuan", "#{zjtzLaiyuan}");
            }
            if (zichantaizhang.getZjtzYvjinianxian() != null) {
                sql.VALUES("ZJTZ_YvJiNianXian", "#{zjtzYvjinianxian}");
            }
            if (zichantaizhang.getZjtzDepartment() != null) {
                sql.VALUES("ZJTZ_Department", "#{zjtzDepartment}");
            }
            if (zichantaizhang.getZjtzPeople() != null) {
                sql.VALUES("ZJTZ_People", "#{zjtzPeople}");
            }
            if (zichantaizhang.getZjtzZhuangtai() != null) {
                sql.VALUES("ZJTZ_ZhuangTai", "#{zjtzZhuangtai}");
            }
            if (zichantaizhang.getZjtzBeizhu() != null) {
                sql.VALUES("ZJTZ_BeiZhu", "#{zjtzBeizhu}");
            }
            return sql.toString();
        }
    }

    //插入资产经营表
    @InsertProvider(type = InsertZiChanJingYing.class, method = "getSQL")
    Integer insertZCJY(Zichanjingying zichanjingying);

    class InsertZiChanJingYing {
        public String getSQL(Zichanjingying zichanjingying) {
            SQL sql = new SQL();
            sql.INSERT_INTO("zichanjingying");
            sql.VALUES("ZCJY_Name", "#{zcjyName}");
            if (zichanjingying.getZcjyPhoto() != null) {
                sql.VALUES("ZCJY_Photo", "#{zcjyPhoto}");
            }
            if (zichanjingying.getZcjyZhenshilujing() != null) {
                sql.VALUES("ZCJY_ZhenShiLuJing", "#{zcjyZhenshilujing}");
            }
            if (zichanjingying.getZcjyZhuangtai() != null) {
                sql.VALUES("ZCJY_ZhuangTai", "#{zcjyZhuangtai}");
            }
            if (zichanjingying.getZcjyChengzhuren() != null) {
                sql.VALUES("ZCJY_ChengZhuRen", "#{zcjyChengzhuren}");
            }
            if (zichanjingying.getZcjyHetong() != null) {
                sql.VALUES("ZCJY_HeTong", "#{zcjyHetong}");
            }
            if (zichanjingying.getZcjyHetongqixian() != null) {
                sql.VALUES("ZCJY_HeTongQiXian", "#{zcjyHetongqixian}");
            }
            if (zichanjingying.getZcjyHetongjine() != null) {
                sql.VALUES("ZCJY_HeTongJinE", "#{zcjyHetongjine}");
            }
            if (zichanjingying.getZcjyZhifuqinkuang() != null) {
                sql.VALUES("ZCJY_ZhiFuQinKuang", "#{zcjyZhifuqinkuang}");
            }
            if (zichanjingying.getZcjyBeizhu() != null) {
                sql.VALUES("ZCJY_BeiZhu", "#{zcjyBeizhu}");
            }
            if (zichanjingying.getZcjyXvhao() != null) {
                sql.VALUES("ZCJY_xvhao", "#{zcjyXvhao}");
            }
            return sql.toString();
        }
    }

    //根据序号查重
    @Select("select * from  zichanjingying where ZCJY_xvhao like '${value}%'")
    Zichanjingying quaryZCYJ_gsh(String value);

    //根据资产类型和村id查找资产类型id
    @Select("select Assets_Id from assets  where A_Villageid =#{A_Villageid} and A_Type =#{A_Type} and A_Zu=#{zuId}")
    Integer quaryZCLXId(@Param("A_Villageid") Integer villageId, @Param("A_Type") String type, @Param("zuId") Integer zuId);
}
