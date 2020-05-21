//package com.hj.jdpth.service.impl;
//
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.hj.jdpth.domain.*;
//import com.hj.jdpth.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.hj.jdpth.service.JijifenziService;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class JijifenziServiceImpl implements JijifenziService {
//    @Autowired
//    NationMapper nationMapper;
//    @Autowired
//    JijifenziMapper jijifenziMapper;
//    @Autowired
//    FazhanduixiangMapper fazhanduixiangMapper;
//    @Autowired
//    YvbeidangyuanMapper yvbeidangyuanMapper;
//    @Autowired
//    DangyuanzhuanzhengMapper dangyuanzhuanzhengMapper;
//    @Autowired
//    QingkuangbiangengMapper qingkuangbiangengMapper;
//    @Autowired
//    JobMapper jobMapper;
//
//    private String nation=null;
//    private String villageName=null;
//    private String zuName=null;
//    private String leixinName=null;
//
//
//
//    @Override
//    public Map<String, Object> GetInfoAllFirst(Integer quId, Integer zId, Integer vId, Date time, Integer entity, Integer startPage, Integer pageSize, Integer adminId) {
//        Map<String, Object> result = new HashMap<>();
//        Map<String, Object> map;
//        List a=new ArrayList();
//        Page<Zuzifazhan> jijifenzi ;
//        Page<Zuzifazhan> fazhanduixiang;
//        Page<Zuzifazhan>yvbeidangyuan;
//        Page<Zuzifazhan> dangyuanzhuanzheng;
//        Page<Qingkuangbiangeng> qingkuangbiangen;
//        PageHelper.startPage(startPage, pageSize);
//     if (entity != null) {
//         if (entity == 1) {
//             try {
//                 jijifenzi = jijifenziMapper.GetInfoByEntity(entity);
//                 for (int i = 0; i < jijifenzi.size(); i++) {
//                     nation = nationMapper.queryById_yyq(jijifenzi.get(i).getMinzu());
//                     jijifenzi.get(i).setNation(nation);
//                     villageName = jijifenziMapper.FindVillageById(jijifenzi.get(i).getVillageId());
//                     jijifenzi.get(i).setVillageName(villageName);
//                     zuName = jijifenziMapper.FindZuById(jijifenzi.get(i).getZuId());
//                     jijifenzi.get(i).setZuName(zuName);
//
//                 }
//                 if (jijifenzi == null) {
//                     result.put("status", "null");
//                     result.put("data", "没有数据");
//                 } else {
//                     result.put("status", "success");
//                     //总页数
//                     result.put("total", jijifenzi.getPages());
//                     //记录总数
//                     result.put("count", jijifenzi.getTotal());
//                     //页面大小
//                     result.put("pageSize", jijifenzi.getPageSize());
//                     //数据
//                     result.put("data", jijifenzi);
//                     //当前第几页
//                     result.put("nowPage", jijifenzi.getPageNum());
//                 }
//             } catch (Exception e) {
//                 result.put("status", "error");
//                 result.put("data", "查询出错");
//             }
//         } else if (entity == 2) {
//             try {
//                 fazhanduixiang = fazhanduixiangMapper.GetInfoByEntity(entity);
//                 for (int i = 0; i < fazhanduixiang.size(); i++) {
//                     nation = nationMapper.queryById_yyq(fazhanduixiang.get(i).getMinzu());
//                     fazhanduixiang.get(i).setNation(nation);
//                     villageName = jijifenziMapper.FindVillageById(fazhanduixiang.get(i).getVillageId());
//                     fazhanduixiang.get(i).setVillageName(villageName);
//                     zuName = jijifenziMapper.FindZuById(fazhanduixiang.get(i).getZuId());
//                     fazhanduixiang.get(i).setZuName(zuName);
//                 }
//                 if (fazhanduixiang == null) {
//                     result.put("status", "null");
//                     result.put("data", "没有数据");
//                 } else {
//                     result.put("status", "success");
//                     //总页数
//                     result.put("total", fazhanduixiang.getPages());
//                     //记录总数
//                     result.put("count", fazhanduixiang.getTotal());
//                     //页面大小
//                     result.put("pageSize", fazhanduixiang.getPageSize());
//                     //数据
//                     result.put("data", fazhanduixiang);
//                     //当前第几页
//                     result.put("nowPage", fazhanduixiang.getPageNum());
//                 }
//             } catch (Exception e) {
//                 result.put("status", "error");
//                 result.put("data", "查询出错");
//             }
//         } else if (entity == 3) {
//             try {
//                 yvbeidangyuan = yvbeidangyuanMapper.GetInfoByEntity(entity);
//                 for (int i = 0; i < yvbeidangyuan.size(); i++) {
//                     nation = nationMapper.queryById_yyq(yvbeidangyuan.get(i).getMinzu());
//                     yvbeidangyuan.get(i).setNation(nation);
//                     villageName = jijifenziMapper.FindVillageById(yvbeidangyuan.get(i).getVillageId());
//                     yvbeidangyuan.get(i).setVillageName(villageName);
//                     zuName = jijifenziMapper.FindZuById(yvbeidangyuan.get(i).getZuId());
//                     yvbeidangyuan.get(i).setZuName(zuName);
//
//                 }
//                 if (yvbeidangyuan == null) {
//                     result.put("status", "null");
//                     result.put("data", "没有数据");
//                 } else {
//                     result.put("status", "success");
//                     //总页数
//                     result.put("total", yvbeidangyuan.getPages());
//                     //记录总数
//                     result.put("count", yvbeidangyuan.getTotal());
//                     //页面大小
//                     result.put("pageSize", yvbeidangyuan.getPageSize());
//                     //数据
//                     result.put("data", a);
//                     //当前第几页
//                     result.put("nowPage", yvbeidangyuan.getPageNum());
//                 }
//             } catch (Exception e) {
//                 result.put("status", "error");
//                 result.put("data", "查询出错");
//             }
//         } else if (entity == 4) {
//             try {
//                 dangyuanzhuanzheng = dangyuanzhuanzhengMapper.GetInfoByEntity(entity);
//                 for (int i = 0; i < dangyuanzhuanzheng.size(); i++) {
//                     nation = nationMapper.queryById_yyq(dangyuanzhuanzheng.get(i).getMinzu());
//                     dangyuanzhuanzheng.get(i).setNation(nation);
//                     villageName = jijifenziMapper.FindVillageById(dangyuanzhuanzheng.get(i).getVillageId());
//                     dangyuanzhuanzheng.get(i).setVillageName(villageName);
//                     zuName = jijifenziMapper.FindZuById(dangyuanzhuanzheng.get(i).getZuId());
//                     dangyuanzhuanzheng.get(i).setZuName(zuName);
//                 }
//                 if (dangyuanzhuanzheng == null) {
//                     result.put("status", "null");
//                     result.put("data", "没有数据");
//                 } else {
//                     result.put("status", "success");
//                     //总页数
//                     result.put("total", dangyuanzhuanzheng.getPages());
//                     //记录总数
//                     result.put("count", dangyuanzhuanzheng.getTotal());
//                     //页面大小
//                     result.put("pageSize", dangyuanzhuanzheng.getPageSize());
//                     //数据
//                     result.put("data", dangyuanzhuanzheng);
//                     //当前第几页
//                     result.put("nowPage", dangyuanzhuanzheng.getPageNum());
//                 }
//             } catch (Exception e) {
//                 result.put("status", "error");
//                 result.put("data", "查询出错");
//             }
//         }
//     }
//        if (time != null) {
//            if (entity == 1) {
//                try {
//                    jijifenzi = jijifenziMapper.GetInfoByTime(time);
//                    for (int i = 0; i < jijifenzi.size(); i++) {
//                        nation = nationMapper.queryById_yyq(jijifenzi.get(i).getMinzu());
//                        jijifenzi.get(i).setNation(nation);
//                        villageName = jijifenziMapper.FindVillageById(jijifenzi.get(i).getVillageId());
//                        jijifenzi.get(i).setVillageName(villageName);
//                        zuName = jijifenziMapper.FindZuById(jijifenzi.get(i).getZuId());
//                        jijifenzi.get(i).setZuName(zuName);
//                    }
//                    if (jijifenzi == null) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", jijifenzi.getPages());
//                        //记录总数
//                        result.put("count", jijifenzi.getTotal());
//                        //页面大小
//                        result.put("pageSize", jijifenzi.getPageSize());
//                        //数据
//                        result.put("data", jijifenzi);
//                        //当前第几页
//                        result.put("nowPage", jijifenzi.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 2) {
//                try {
//                    fazhanduixiang = fazhanduixiangMapper.GetInfoByTime(time);
//                    for (int i = 0; i < fazhanduixiang.size(); i++) {
//                        nation = nationMapper.queryById_yyq(fazhanduixiang.get(i).getMinzu());
//                        fazhanduixiang.get(i).setNation(nation);
//                        villageName = jijifenziMapper.FindVillageById(fazhanduixiang.get(i).getVillageId());
//                        fazhanduixiang.get(i).setVillageName(villageName);
//                        zuName = jijifenziMapper.FindZuById(fazhanduixiang.get(i).getZuId());
//                        fazhanduixiang.get(i).setZuName(zuName);
//                    }
//                    if (fazhanduixiang == null) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", fazhanduixiang.getPages());
//                        //记录总数
//                        result.put("count", fazhanduixiang.getTotal());
//                        //页面大小
//                        result.put("pageSize", fazhanduixiang.getPageSize());
//                        //数据
//                        result.put("data", fazhanduixiang);
//                        //当前第几页
//                        result.put("nowPage", fazhanduixiang.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 3) {
//                try {
//                    yvbeidangyuan = yvbeidangyuanMapper.GetInfoByTime(time);
//                    for (int i = 0; i < yvbeidangyuan.size(); i++) {
//                        nation = nationMapper.queryById_yyq(yvbeidangyuan.get(i).getMinzu());
//                        yvbeidangyuan.get(i).setNation(nation);
//                        villageName = jijifenziMapper.FindVillageById(yvbeidangyuan.get(i).getVillageId());
//                        yvbeidangyuan.get(i).setVillageName(villageName);
//                    }
//                    if (yvbeidangyuan == null) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", yvbeidangyuan.getPages());
//                        //记录总数
//                        result.put("count", yvbeidangyuan.getTotal());
//                        //页面大小
//                        result.put("pageSize", yvbeidangyuan.getPageSize());
//                        //数据
//                        result.put("data", a);
//                        //当前第几页
//                        result.put("nowPage", yvbeidangyuan.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 4) {
//                try {
//                    dangyuanzhuanzheng = dangyuanzhuanzhengMapper.GetInfoByTime(time);
//                    for (int i = 0; i < dangyuanzhuanzheng.size(); i++) {
//                        nation = nationMapper.queryById_yyq(dangyuanzhuanzheng.get(i).getMinzu());
//                        dangyuanzhuanzheng.get(i).setNation(nation);
//                        villageName = jijifenziMapper.FindVillageById(dangyuanzhuanzheng.get(i).getVillageId());
//                        dangyuanzhuanzheng.get(i).setVillageName(villageName);
//                        zuName = jijifenziMapper.FindZuById(dangyuanzhuanzheng.get(i).getZuId());
//                        dangyuanzhuanzheng.get(i).setZuName(zuName);
//                    }
//                    if (dangyuanzhuanzheng == null) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", dangyuanzhuanzheng.getPages());
//                        //记录总数
//                        result.put("count", dangyuanzhuanzheng.getTotal());
//                        //页面大小
//                        result.put("pageSize", dangyuanzhuanzheng.getPageSize());
//                        //数据
//                        result.put("data", dangyuanzhuanzheng);
//                        //当前第几页
//                        result.put("nowPage", dangyuanzhuanzheng.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 5) {
//                try {
//                    qingkuangbiangen = qingkuangbiangengMapper.GetInfoByTime(time);
//                    for (int i = 0; i < qingkuangbiangen.size(); i++) {
//                        nation = nationMapper.queryById_yyq(qingkuangbiangen.get(i).getQkbgMinzu());
//                        qingkuangbiangen.get(i).setNation(nation);
//                        zuName = jijifenziMapper.FindZuById(qingkuangbiangen.get(i).getQkbgZu());
//                        qingkuangbiangen.get(i).setZuName(zuName);
//                        leixinName = jijifenziMapper.FindLeixinZuById(qingkuangbiangen.get(i).getQkbgLeixing());
//                        qingkuangbiangen.get(i).setLeixinName(leixinName);
//                    }
//                    if (qingkuangbiangen == null) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", qingkuangbiangen.getPages());
//                        //记录总数
//                        result.put("count", qingkuangbiangen.getTotal());
//                        //页面大小
//                        result.put("pageSize", qingkuangbiangen.getPageSize());
//                        //数据
//                        result.put("data", qingkuangbiangen);
//                        //当前第几页
//                        result.put("nowPage", qingkuangbiangen.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            }
////            }else if (entity != null) {
////                if (entity == 1) {
////                    try {
////                        jijifenzi = jijifenziMapper.GetInfoByEntity(entity);
////                        for (int i=0;i<jijifenzi.size();i++){
////                            nation=nationMapper.queryById_yyq(jijifenzi.get(i).getMinzu());
////                            jijifenzi.get(i).setNation(nation);
////                            villageName=jijifenziMapper.FindVillageById(jijifenzi.get(i).getVillageId());
////                            jijifenzi.get(i).setVillageName(villageName);
////                            zuName=jijifenziMapper.FindZuById(jijifenzi.get(i).getZuId());
////                            jijifenzi.get(i).setZuName(zuName);
////                        }
////                        if(jijifenzi == null) {
////                            result.put("status", "null");
////                            result.put("data", "没有数据");
////                        } else {
////                            result.put("status", "success");
////                            //总页数
////                            result.put("total", jijifenzi.getPages());
////                            //记录总数
////                            result.put("count", jijifenzi.getTotal());
////                            //页面大小
////                            result.put("pageSize", jijifenzi.getPageSize());
////                            //数据
////                            result.put("data", jijifenzi);
////                            //当前第几页
////                            result.put("nowPage", jijifenzi.getPageNum());
////                        }
////                    } catch (Exception e) {
////                        result.put("status", "error");
////                        result.put("data", "查询出错");
////                    }
////                } else if (entity == 2) {
////                    try {
////                        fazhanduixiang = fazhanduixiangMapper.GetInfoByEntity(entity);
////                        for (int i=0;i<fazhanduixiang.size();i++){
////                            nation=nationMapper.queryById_yyq(fazhanduixiang.get(i).getMinzu());
////                            fazhanduixiang.get(i).setNation(nation);
////                            villageName=jijifenziMapper.FindVillageById(fazhanduixiang.get(i).getVillageId());
////                            fazhanduixiang.get(i).setVillageName(villageName);
////                            zuName=jijifenziMapper.FindZuById(fazhanduixiang.get(i).getZuId());
////                            fazhanduixiang.get(i).setZuName(zuName);
////                        }
////                        if (fazhanduixiang == null) {
////                            result.put("status", "null");
////                            result.put("data", "没有数据");
////                        } else {
////                            result.put("status", "success");
////                            //总页数
////                            result.put("total", fazhanduixiang.getPages());
////                            //记录总数
////                            result.put("count", fazhanduixiang.getTotal());
////                            //页面大小
////                            result.put("pageSize", fazhanduixiang.getPageSize());
////                            //数据
////                            result.put("data", fazhanduixiang);
////                            //当前第几页
////                            result.put("nowPage", fazhanduixiang.getPageNum());
////                        }
////                    } catch (Exception e) {
////                        result.put("status", "error");
////                        result.put("data", "查询出错");
////                    }
////                } else if (entity == 3) {
////                    try {
////                        yvbeidangyuan = yvbeidangyuanMapper.GetInfoByEntity(entity);
////                        for (int i=0;i<yvbeidangyuan.size();i++){
////                            nation=nationMapper.queryById_yyq(yvbeidangyuan.get(i).getMinzu());
////                            yvbeidangyuan.get(i).setNation(nation);
////                            villageName=jijifenziMapper.FindVillageById(yvbeidangyuan.get(i).getVillageId());
////                            yvbeidangyuan.get(i).setVillageName(villageName);
////                            zuName=jijifenziMapper.FindZuById(yvbeidangyuan.get(i).getZuId());
////                            yvbeidangyuan.get(i).setZuName(zuName);
////                        }
////                        if (yvbeidangyuan == null) {
////                            result.put("status", "null");
////                            result.put("data", "没有数据");
////                        } else {
////                            result.put("status", "success");
////                            //总页数
////                            result.put("total", yvbeidangyuan.getPages());
////                            //记录总数
////                            result.put("count", yvbeidangyuan.getTotal());
////                            //页面大小
////                            result.put("pageSize", yvbeidangyuan.getPageSize());
////                            //数据
////                            result.put("data", a);
////                            //当前第几页
////                            result.put("nowPage", yvbeidangyuan.getPageNum());
////                        }
////                    } catch(Exception e){
////                        result.put("status", "error");
////                        result.put("data", "查询出错");
////                    }
////                } else if (entity == 4) {
////                    try {
////                        dangyuanzhuanzheng = dangyuanzhuanzhengMapper.GetInfoByEntity(entity);
////                        for (int i = 0; i < dangyuanzhuanzheng.size(); i++) {
////                            nation = nationMapper.queryById_yyq(dangyuanzhuanzheng.get(i).getMinzu());
////                            dangyuanzhuanzheng.get(i).setNation(nation);
////                            villageName = jijifenziMapper.FindVillageById(dangyuanzhuanzheng.get(i).getVillageId());
////                            dangyuanzhuanzheng.get(i).setVillageName(villageName);
////                            zuName = jijifenziMapper.FindZuById(dangyuanzhuanzheng.get(i).getZuId());
////                            dangyuanzhuanzheng.get(i).setZuName(zuName);
////                        }
////                        if (dangyuanzhuanzheng == null) {
////                            result.put("status", "null");
////                            result.put("data", "没有数据");
////                        } else {
////                            result.put("status", "success");
////                            //总页数
////                            result.put("total", dangyuanzhuanzheng.getPages());
////                            //记录总数
////                            result.put("count", dangyuanzhuanzheng.getTotal());
////                            //页面大小
////                            result.put("pageSize", dangyuanzhuanzheng.getPageSize());
////                            //数据
////                            result.put("data", dangyuanzhuanzheng);
////                            //当前第几页
////                            result.put("nowPage", dangyuanzhuanzheng.getPageNum());
////                        }
////                    } catch (Exception e) {
////                        result.put("status", "error");
////                        result.put("data", "查询出错");
////                    }
////                }
//        } else if (adminId == 2 || adminId == 3) {
//            System.out.println("进入区级");
//            if (entity == 1) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                       jijifenzi = jijifenziMapper.GetInfoByCun(vId);
//                    } else if (zId != 0) {
//                        jijifenzi = jijifenziMapper.GetInfoByZhen(zId);
//                    } else {
//                        jijifenzi = jijifenziMapper.GentInfo(quId);
//                    }
//                    for (int i=0;i<jijifenzi.size();i++){
//                        nation=nationMapper.queryById_yyq(jijifenzi.get(i).getMinzu());
//                        jijifenzi.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(jijifenzi.get(i).getVillageId());
//                        jijifenzi.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(jijifenzi.get(i).getZuId());
//                        jijifenzi.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(jijifenzi.get(i).getZhiwu()));
//                        jijifenzi.get(i).setZhiwu(job.getjName());
//                    }
//                    if (jijifenzi.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", jijifenzi.getPages());
//                        //数据
//                        result.put("data", jijifenzi);
//                        //每页数据量
//                        result.put("PageSize", jijifenzi.getPageSize());
//                        //记录总数
//                        result.put("count", jijifenzi.getTotal());
//                        //当前第几页
//                        result.put("nowPage", jijifenzi.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 2) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                        fazhanduixiang = fazhanduixiangMapper.GetInfoByCun(vId);
//                    } else if (zId != 0) {
//                        fazhanduixiang = fazhanduixiangMapper.GetInfoByZhen(zId);
//                    } else {
//                        fazhanduixiang = fazhanduixiangMapper.GentInfo(quId);
//                        }
//                    for (int i=0;i<fazhanduixiang.size();i++){
//                        nation=nationMapper.queryById_yyq(fazhanduixiang.get(i).getMinzu());
//                        fazhanduixiang.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(fazhanduixiang.get(i).getVillageId());
//                        fazhanduixiang.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(fazhanduixiang.get(i).getZuId());
//                        fazhanduixiang.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(fazhanduixiang.get(i).getZhiwu()));
//                        fazhanduixiang.get(i).setZhiwu(job.getjName());
//                    }
//                    if (fazhanduixiang.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", fazhanduixiang.getPages());
//                        //数据
//                        result.put("data", fazhanduixiang);
//                        //每页数据量
//                        result.put("PageSize", fazhanduixiang.getPageSize());
//                        //记录总数
//                        result.put("count", fazhanduixiang.getTotal());
//                        //当前第几页
//                        result.put("nowPage", fazhanduixiang.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 3) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                        yvbeidangyuan = yvbeidangyuanMapper.GetInfoByCun(vId);
//                    } else if (zId != 0) {
//                        yvbeidangyuan = yvbeidangyuanMapper.GetInfoByZhen(zId);
//                    } else {
//                        yvbeidangyuan = yvbeidangyuanMapper.GentInfo(quId);
//                        }
//                    for (int i=0;i<yvbeidangyuan.size();i++){
//                        nation=nationMapper.queryById_yyq(yvbeidangyuan.get(i).getMinzu());
//                        yvbeidangyuan.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(yvbeidangyuan.get(i).getVillageId());
//                        yvbeidangyuan.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(yvbeidangyuan.get(i).getZuId());
//                        yvbeidangyuan.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(yvbeidangyuan.get(i).getZhiwu()));
//                        yvbeidangyuan.get(i).setZhiwu(job.getjName());
//                    }
//                    if (yvbeidangyuan.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", yvbeidangyuan.getPages());
//                        //数据
//                        result.put("data", yvbeidangyuan);
//                        //每页数据量
//                        result.put("PageSize", yvbeidangyuan.getPageSize());
//                        //记录总数
//                        result.put("count", yvbeidangyuan.getTotal());
//                        //当前第几页
//                        result.put("nowPage", yvbeidangyuan.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 4) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                        dangyuanzhuanzheng = dangyuanzhuanzhengMapper.GetInfoByCun(vId);
//                    } else if (zId != 0) {
//                        dangyuanzhuanzheng = dangyuanzhuanzhengMapper.GetInfoByZhen(zId);
//                    } else {
//                        dangyuanzhuanzheng = dangyuanzhuanzhengMapper.GentInfo(quId);
//                    }
//                    for (int i=0;i<dangyuanzhuanzheng.size();i++){
//                        nation=nationMapper.queryById_yyq(dangyuanzhuanzheng.get(i).getMinzu());
//                        dangyuanzhuanzheng.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(dangyuanzhuanzheng.get(i).getVillageId());
//                        dangyuanzhuanzheng.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(dangyuanzhuanzheng.get(i).getZuId());
//                        dangyuanzhuanzheng.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(dangyuanzhuanzheng.get(i).getZhiwu()));
//                        dangyuanzhuanzheng.get(i).setZhiwu(job.getjName());
//                    }
//                    if (dangyuanzhuanzheng.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", dangyuanzhuanzheng.getPages());
//                        //数据
//                        result.put("data", dangyuanzhuanzheng);
//                        //每页数据量
//                        result.put("PageSize", dangyuanzhuanzheng.getPageSize());
//                        //记录总数
//                        result.put("count", dangyuanzhuanzheng.getTotal());
//                        //当前第几页
//                        result.put("nowPage", dangyuanzhuanzheng.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if(entity==5){
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                        qingkuangbiangen = qingkuangbiangengMapper.GetInfoByCun(vId);
//                    } else if (zId != 0) {
//                        qingkuangbiangen = qingkuangbiangengMapper.GetInfoByZhen(zId);
//                    } else {
//                        qingkuangbiangen = qingkuangbiangengMapper.GentInfo(quId);
//                    }
//                    for (int i=0;i<qingkuangbiangen.size();i++){
//                        nation=nationMapper.queryById_yyq(qingkuangbiangen.get(i).getQkbgMinzu());
//                        qingkuangbiangen.get(i).setNation(nation);
//                        zuName=jijifenziMapper.FindZuById(qingkuangbiangen.get(i).getQkbgZu());
//                        qingkuangbiangen.get(i).setZuName(zuName);
//                        leixinName=jijifenziMapper.FindLeixinZuById(qingkuangbiangen.get(i).getQkbgLeixing());
//                        qingkuangbiangen.get(i).setLeixinName(leixinName);
//                    }
//                    if (qingkuangbiangen.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", qingkuangbiangen.getPages());
//                        //数据
//                        result.put("data", qingkuangbiangen);
//                        //每页数据量
//                        result.put("PageSize", qingkuangbiangen.getPageSize());
//                        //记录总数
//                        result.put("count", qingkuangbiangen.getTotal());
//                        //当前第几页
//                        result.put("nowPage", qingkuangbiangen.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            }
//        } else if (adminId == 4) {
//            System.out.println("进入镇级");
//            if (entity == 1) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                        jijifenzi = jijifenziMapper.GetInfoByCun(vId);
//                    } else {
//                        jijifenzi = jijifenziMapper.GetInfoByZhen(zId);
//                    }
//                    for (int i=0;i<jijifenzi.size();i++){
//                        nation=nationMapper.queryById_yyq(jijifenzi.get(i).getMinzu());
//                        jijifenzi.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(jijifenzi.get(i).getVillageId());
//                        jijifenzi.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(jijifenzi.get(i).getZuId());
//                        jijifenzi.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(jijifenzi.get(i).getZhiwu()));
//                        jijifenzi.get(i).setZhiwu(job.getjName());
//                    }
//                    if (jijifenzi.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", jijifenzi.getPages());
//                        //记录总数
//                        result.put("count", jijifenzi.getTotal());
//                        //数据
//                        result.put("data", jijifenzi);
//                        //每页数据量
//                        result.put("PageSize", jijifenzi.getPageSize());
//                        //当前第几页
//                        result.put("nowPage", jijifenzi.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 2) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                        fazhanduixiang = fazhanduixiangMapper.GetInfoByCun(vId);
//                    } else {
//                        fazhanduixiang = fazhanduixiangMapper.GetInfoByZhen(zId);
//                    }
//                    for (int i=0;i<fazhanduixiang.size();i++){
//                        nation=nationMapper.queryById_yyq(fazhanduixiang.get(i).getMinzu());
//                        fazhanduixiang.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(fazhanduixiang.get(i).getVillageId());
//                        fazhanduixiang.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(fazhanduixiang.get(i).getZuId());
//                        fazhanduixiang.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(fazhanduixiang.get(i).getZhiwu()));
//                        fazhanduixiang.get(i).setZhiwu(job.getjName());
//
//                    }
//                    if (fazhanduixiang.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", fazhanduixiang.getPages());
//                        //记录总数
//                        result.put("count", fazhanduixiang.getTotal());
//                        result.put("data", fazhanduixiang);
//                        result.put("PageSize", fazhanduixiang.getPageSize());
//                        //当前第几页
//                        result.put("nowPage", fazhanduixiang.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 3) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                        yvbeidangyuan = yvbeidangyuanMapper.GetInfoByCun(vId);
//                    } else {
//                        yvbeidangyuan = yvbeidangyuanMapper.GetInfoByZhen(zId);
//                    }
//                    for (int i=0;i<yvbeidangyuan.size();i++){
//                        nation=nationMapper.queryById_yyq(yvbeidangyuan.get(i).getMinzu());
//                        yvbeidangyuan.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(yvbeidangyuan.get(i).getVillageId());
//                        yvbeidangyuan.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(yvbeidangyuan.get(i).getZuId());
//                        yvbeidangyuan.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(yvbeidangyuan.get(i).getZhiwu()));
//                        yvbeidangyuan.get(i).setZhiwu(job.getjName());
//
//                    }
//                    if (yvbeidangyuan.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", yvbeidangyuan.getPages());
//                        //记录总数
//                        result.put("count", yvbeidangyuan.getTotal());
//                        result.put("data", yvbeidangyuan);
//                        result.put("PageSize", yvbeidangyuan.getPageSize());
//                        //当前第几页
//                        result.put("nowPage", yvbeidangyuan.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 4) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                        dangyuanzhuanzheng = dangyuanzhuanzhengMapper.GetInfoByCun(vId);
//                    } else {
//                        dangyuanzhuanzheng = dangyuanzhuanzhengMapper.GetInfoByZhen(zId);
//                    }
//                    for (int i=0;i<dangyuanzhuanzheng.size();i++){
//                        nation=nationMapper.queryById_yyq(dangyuanzhuanzheng.get(i).getMinzu());
//                        dangyuanzhuanzheng.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(dangyuanzhuanzheng.get(i).getVillageId());
//                        dangyuanzhuanzheng.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(dangyuanzhuanzheng.get(i).getZuId());
//                        dangyuanzhuanzheng.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(dangyuanzhuanzheng.get(i).getZhiwu()));
//                        dangyuanzhuanzheng.get(i).setZhiwu(job.getjName());
//                    }
//                    if (dangyuanzhuanzheng.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", dangyuanzhuanzheng.getPages());
//                        //记录总数
//                        result.put("count", dangyuanzhuanzheng.getTotal());
//                        result.put("data", dangyuanzhuanzheng);
//                        result.put("PageSize", dangyuanzhuanzheng.getPageSize());
//                        //当前第几页
//                        result.put("nowPage", dangyuanzhuanzheng.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if(entity==5){
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    if (vId != 0) {
//                        qingkuangbiangen = qingkuangbiangengMapper.GetInfoByCun(vId);
//                    } else {
//                        qingkuangbiangen = qingkuangbiangengMapper.GetInfoByZhen(zId);
//                    }
//                    for (int i=0;i<qingkuangbiangen.size();i++){
//                        nation=nationMapper.queryById_yyq(qingkuangbiangen.get(i).getQkbgMinzu());
//                        qingkuangbiangen.get(i).setNation(nation);
//                        zuName=jijifenziMapper.FindZuById(qingkuangbiangen.get(i).getQkbgZu());
//                        qingkuangbiangen.get(i).setZuName(zuName);
//                        leixinName=jijifenziMapper.FindLeixinZuById(qingkuangbiangen.get(i).getQkbgLeixing());
//                        qingkuangbiangen.get(i).setLeixinName(leixinName);
//                    }
//                    if (qingkuangbiangen.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", qingkuangbiangen.getPages());
//                        //记录总数
//                        result.put("count", qingkuangbiangen.getTotal());
//                        result.put("data", qingkuangbiangen);
//                        result.put("PageSize", qingkuangbiangen.getPageSize());
//                        //当前第几页
//                        result.put("nowPage", qingkuangbiangen.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            }
//        } else if (adminId == 5) {
//            System.out.println("进入村级");
//            if (entity == 1) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    jijifenzi = jijifenziMapper.GetInfoByCun(vId);
//                    for (int i=0;i<jijifenzi.size();i++){
//                        nation=nationMapper.queryById_yyq(jijifenzi.get(i).getMinzu());
//                        jijifenzi.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(jijifenzi.get(i).getVillageId());
//                        jijifenzi.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(jijifenzi.get(i).getZuId());
//                        jijifenzi.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(jijifenzi.get(i).getZhiwu()));
//                        jijifenzi.get(i).setZhiwu(job.getjName());
//
//                    }
//                    if (jijifenzi.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", jijifenzi.getPages());
//                        //记录总数
//                        result.put("count", jijifenzi.getTotal());
//                        result.put("data", jijifenzi);
//                        result.put("PageSize", jijifenzi.getPageSize());
//
//                        //当前第几页
//                        result.put("nowPage", jijifenzi.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 2) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    fazhanduixiang = fazhanduixiangMapper.GetInfoByCun(vId);
//                    for (int i=0;i<fazhanduixiang.size();i++){
//                        nation=nationMapper.queryById_yyq(fazhanduixiang.get(i).getMinzu());
//                        fazhanduixiang.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(fazhanduixiang.get(i).getVillageId());
//                        fazhanduixiang.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(fazhanduixiang.get(i).getZuId());
//                        fazhanduixiang.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(fazhanduixiang.get(i).getZhiwu()));
//                        fazhanduixiang.get(i).setZhiwu(job.getjName());
//                    }
//                    if (fazhanduixiang.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", fazhanduixiang.getPages());
//                        //记录总数
//                        result.put("count", fazhanduixiang.getTotal());
//                        result.put("data", fazhanduixiang);
//                        result.put("PageSize", fazhanduixiang.getPageSize());
//                        //当前第几页
//                        result.put("nowPage", fazhanduixiang.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 3) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    yvbeidangyuan = yvbeidangyuanMapper.GetInfoByCun(vId);
//                    for (int i=0;i<yvbeidangyuan.size();i++){
//                        nation=nationMapper.queryById_yyq(yvbeidangyuan.get(i).getMinzu());
//                        yvbeidangyuan.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(yvbeidangyuan.get(i).getVillageId());
//                        yvbeidangyuan.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(yvbeidangyuan.get(i).getZuId());
//                        yvbeidangyuan.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(yvbeidangyuan.get(i).getZhiwu()));
//                        yvbeidangyuan.get(i).setZhiwu(job.getjName());
//
//                    }
//                    if (yvbeidangyuan.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", yvbeidangyuan.getPages());
//                        //记录总数
//                        result.put("count", yvbeidangyuan.getTotal());
//                        result.put("data", yvbeidangyuan);
//                        result.put("PageSize", yvbeidangyuan.getPageSize());
//                        //当前第几页
//                        result.put("nowPage", yvbeidangyuan.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if (entity == 4) {
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    dangyuanzhuanzheng = dangyuanzhuanzhengMapper.GetInfoByCun(vId);
//                    for (int i=0;i<dangyuanzhuanzheng.size();i++){
//                        nation=nationMapper.queryById_yyq(dangyuanzhuanzheng.get(i).getMinzu());
//                        dangyuanzhuanzheng.get(i).setNation(nation);
//                        villageName=jijifenziMapper.FindVillageById(dangyuanzhuanzheng.get(i).getVillageId());
//                        dangyuanzhuanzheng.get(i).setVillageName(villageName);
//                        zuName=jijifenziMapper.FindZuById(dangyuanzhuanzheng.get(i).getZuId());
//                        dangyuanzhuanzheng.get(i).setZuName(zuName);
//                        Job job=jobMapper.queryById_yyq(Integer.parseInt(dangyuanzhuanzheng.get(i).getZhiwu()));
//                        dangyuanzhuanzheng.get(i).setZhiwu(job.getjName());
//
//                    }
//                    if (dangyuanzhuanzheng.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", dangyuanzhuanzheng.getPages());
//                        //记录总数
//                        result.put("count", dangyuanzhuanzheng.getTotal());
//                        result.put("data", dangyuanzhuanzheng);
//                        result.put("PageSize", dangyuanzhuanzheng.getPageSize());
//                        //当前第几页
//                        result.put("nowPage", dangyuanzhuanzheng.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            } else if(entity==5){
//                try {
//                    PageHelper.startPage(startPage, pageSize);
//                    qingkuangbiangen = qingkuangbiangengMapper.GetInfoByCun(vId);
//                    for (int i=0;i<qingkuangbiangen.size();i++){
//                        nation=nationMapper.queryById_yyq(qingkuangbiangen.get(i).getQkbgMinzu());
//                        qingkuangbiangen.get(i).setNation(nation);
//                        zuName=jijifenziMapper.FindZuById(qingkuangbiangen.get(i).getQkbgZu());
//                        qingkuangbiangen.get(i).setZuName(zuName);
//                        leixinName=jijifenziMapper.FindLeixinZuById(qingkuangbiangen.get(i).getQkbgLeixing());
//                        qingkuangbiangen.get(i).setLeixinName(leixinName);
//                    }
//                    if (qingkuangbiangen.isEmpty()) {
//                        result.put("status", "null");
//                        result.put("data", "没有数据");
//                    } else {
//                        result.put("status", "success");
//                        //总页数
//                        result.put("total", qingkuangbiangen.getPages());
//                        //记录总数
//                        result.put("count", qingkuangbiangen.getTotal());
//                        result.put("data", qingkuangbiangen);
//                        result.put("PageSize", qingkuangbiangen.getPageSize());
//                        //当前第几页
//                        result.put("nowPage", qingkuangbiangen.getPageNum());
//                    }
//                } catch (Exception e) {
//                    result.put("status", "error");
//                    result.put("data", "查询出错");
//                }
//            }
//        }
//        return result;
//    }
//
//        @Override
//        public Map<String, Object> cun_List (Integer zhenId){
//            Map<String, Object> map = new HashMap<>();
//            List<Village> zhens = jijifenziMapper.cun_List(zhenId);
//            map.put("data", zhens);
//            return map;
//        }
//
//}
//
//
