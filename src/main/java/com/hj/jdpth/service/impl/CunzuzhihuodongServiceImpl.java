package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.CunzuzhihuodongMapper;
import com.hj.jdpth.repository.VillageMapper;
import com.hj.jdpth.repository.ZhenMapper;
import com.hj.jdpth.service.CunzuzhihuodongService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CunzuzhihuodongServiceImpl implements CunzuzhihuodongService {
    @Autowired
    CunzuzhihuodongMapper cunzuzhihuodongMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    VillageMapper villageMapper;

    @Override//查询村级活动
    public Map<String, Object> queryHuodong_yp(Integer style, Integer qu_id, Integer zhen_id, Integer cun_id, Integer chdlx_id, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        if (style == 2 || style == 3) {
            System.out.println("进入区级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<CunzuzhihuodongInfo> cunzuzhihuodongInfo = cunzuzhihuodongMapper.queryHuodong_yp(qu_id, zhen_id, cun_id, chdlx_id);
                if (cunzuzhihuodongInfo.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cunzuzhihuodongInfo);
                    //总页数
                    result.put("total", cunzuzhihuodongInfo.getPages());
                    //记录总数
                    result.put("count", cunzuzhihuodongInfo.getTotal());
                    //当前第几页
                    result.put("nowPage", cunzuzhihuodongInfo.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 4) {
            System.out.println("进入镇级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<CunzuzhihuodongInfo> cunzuzhihuodongInfo = cunzuzhihuodongMapper.queryHuodong_yp(qu_id, zhen_id, cun_id, chdlx_id);
                if (cunzuzhihuodongInfo.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cunzuzhihuodongInfo);
                    //总页数
                    result.put("total", cunzuzhihuodongInfo.getPages());
                    //记录总数
                    result.put("count", cunzuzhihuodongInfo.getTotal());
                    //当前第几页
                    result.put("nowPage", cunzuzhihuodongInfo.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 5) {
            System.out.println("进入村级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<CunzuzhihuodongInfo> cunzuzhihuodongInfo = cunzuzhihuodongMapper.queryHuodong_yp(qu_id, zhen_id, cun_id, chdlx_id);
                if (cunzuzhihuodongInfo.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cunzuzhihuodongInfo);
                    //总页数
                    result.put("total", cunzuzhihuodongInfo.getPages());
                    //记录总数
                    result.put("count", cunzuzhihuodongInfo.getTotal());
                    //当前第几页
                    result.put("nowPage", cunzuzhihuodongInfo.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> findXingqing_yp(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> cunzuzhihuodong = cunzuzhihuodongMapper.findXingqing_yp(id);
        result.put("cunzuzhihuodong", cunzuzhihuodong);
        return result;
    }

    @Override
    public Map<String, Object> deleteOneById_yp(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Boolean flag = cunzuzhihuodongMapper.deleteOneById_yp(id);
        result.put("flag", flag);
        return result;
    }

    @Override
    public Map<String, Object> deleteManyById_yp(String Ids) {
        String[] ids = Ids.split(",");
        Map<String, Object> result = new HashMap<>();
        for (String id : ids) {
            Boolean flag = cunzuzhihuodongMapper.deleteOneById_yp(Integer.parseInt(id));
            if (flag == true) {
                if (result.get("success") != null) {
                    result.put("success", id + "," + result.get("success"));
                } else {
                    result.put("success", id);
                }
            } else {
                if (result.get("error") != null) {
                    result.put("error", id + "," + result.get("error"));
                } else {
                    result.put("error", id);
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> insertOne_yp(Cunzuzhihuodong cunzuzhihuodong, String czzhdTime) {
        Map<String, Object> result = new HashMap<>();

        cunzuzhihuodong.setCdyzzhdTime(Date.valueOf(czzhdTime));
        int i = cunzuzhihuodongMapper.insertOne_yp(cunzuzhihuodong);
        if (i > 0) {
            result.put("result", i);
        } else {
            result.put("result", i);
        }
        return result;
    }

    @Override
    public Map<String, Object> daoruOne_yp(Cunzuzhihuodong cunzuzhihuodong) {
        Map<String, Object> result = new HashMap<>();
        int i = cunzuzhihuodongMapper.insertOne_yp(cunzuzhihuodong);
        if (i > 0) {
            result.put("result", i);
        } else {
            result.put("result", i);
        }
        return result;
    }

    @Override
    public Map<String, Object> updateOne_yp(Cunzuzhihuodong cunzuzhihuodong) {
        Map<String, Object> result = new HashMap<>();
        Boolean flag = cunzuzhihuodongMapper.updateOne_yp(cunzuzhihuodong);
        if (flag == true) {
            result.put("result", "更新成功");
        } else {
            result.put("result", "更新失败");
        }
        return result;
    }

    @Override
    public Boolean findHuodongByxuhao_yp(String cdyzzhdXuhao) {
        if (cunzuzhihuodongMapper.findHuodongByxuhao_yp(cdyzzhdXuhao) != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Map<String, Object> findChdlxById_yp(Integer cun_id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Page<Cunhuodongleixing> cunhuodongleixing = cunzuzhihuodongMapper.findChdlxById_yp(cun_id);
            if (cunhuodongleixing.isEmpty()) {
                result.put("status", "null");
                result.put("data", "没有数据");
            } else {
                result.put("status", "success");
                result.put("data", cunhuodongleixing);
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "查询出错");
        }
        return result;
    }

    @Override
    public Map<String, Object> insertLeixing_yp(Cunhuodongleixing cunhuodongleixing) {
        Map<String, Object> result = new HashMap<>();
        int i = cunzuzhihuodongMapper.insertLeixing_yp(cunhuodongleixing);
        if (i > 0) {
            result.put("result", i);
        } else {
            result.put("result", i);
        }
        return result;
    }

    @Override
    public Map<String, Object> chdzhen(Integer cunId, String time) {
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
//        List<Village> villages=cunzuzhihuodongMapper.findbyId_yp(cunId);
        Map<String, Object> result = new HashMap<>();
        List<HashMap> chdzhen = cunzuzhihuodongMapper.chdzhen(cunId, time1, time2);
        result.put("data", chdzhen);
        return result;
    }

    @Override
    public Map<String, Object> chdqu(Integer zhenId, String time) {
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        List c = new ArrayList();
        List<Zhen> zhens = zhenMapper.queryZhenByQuId_yyq(1349);
        Map<String, Object> result = new HashMap<>();

        List<HashMap> list = cunzuzhihuodongMapper.chdqu(1, time1, time2);
        List<HashMap> list2 = cunzuzhihuodongMapper.chdqu(2, time1, time2);
        List<HashMap> list3 = cunzuzhihuodongMapper.chdqu(3, time1, time2);
        List<HashMap> list4 = cunzuzhihuodongMapper.chdqu(4, time1, time2);
        List<HashMap> list5 = cunzuzhihuodongMapper.chdqu(5, time1, time2);
        List<HashMap> list6 = cunzuzhihuodongMapper.chdqu(6, time1, time2);
        List<HashMap> list7 = cunzuzhihuodongMapper.chdqu(7, time1, time2);
        List<HashMap> list8 = cunzuzhihuodongMapper.chdqu(8, time1, time2);

        Map<String, Object> a1 = new HashMap<>();
        Map<String, Object> a2 = new HashMap<>();
        Map<String, Object> a3 = new HashMap<>();
        Map<String, Object> a4 = new HashMap<>();
        Map<String, Object> a5 = new HashMap<>();
        Map<String, Object> a6 = new HashMap<>();
        Map<String, Object> a7 = new HashMap<>();
        Map<String, Object> a8 = new HashMap<>();

        if (list.size() != 0) {
            a1.put("total", list.size());
        } else {
            a1.put("total", 0);
        }
        if (list2.size() != 0) {
            a2.put("total", list2.size());
        } else {
            a2.put("total", 0);
        }
        if (list3.size() != 0) {
            a3.put("total", list3.size());
        } else {
            a3.put("total", 0);
        }
        if (list4.size() != 0) {
            a4.put("total", list4.size());
        } else {
            a4.put("total", 0);
        }
        if (list5.size() != 0) {
            a5.put("total", list5.size());
        } else {
            a5.put("total", 0);
        }
        if (list6.size() != 0) {
            a6.put("total", list6.size());
        } else {
            a6.put("total", 0);
        }
        if (list7.size() != 0) {
            a7.put("total", list7.size());
        } else {
            a7.put("total", 0);
        }
        if (list8.size() != 0) {
            a8.put("total", list8.size());
        } else {
            a8.put("total", 0);
        }

        a1.put("name", zhens.get(0).getzName());
        a2.put("name", zhens.get(1).getzName());
        a3.put("name", zhens.get(2).getzName());
        a4.put("name", zhens.get(3).getzName());
        a5.put("name", zhens.get(4).getzName());
        a6.put("name", zhens.get(5).getzName());
        a7.put("name", zhens.get(6).getzName());
        a8.put("name", zhens.get(7).getzName());

        c.add(a1);
        c.add(a2);
        c.add(a3);
        c.add(a4);
        c.add(a5);
        c.add(a6);
        c.add(a7);
        c.add(a8);
        result.put("data", c);
        return result;
    }

    @Override
    public Map<String, Object> qzxian(String time) {
  /*      String[] strs=time.split("~");
        String time1=strs[0];
        String time2=strs[1];*/
        List a = new ArrayList();
        List<Zhen> zhens = zhenMapper.queryZhenByQuId_yyq(1349);
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();
        Map<String, Object> map5 = new HashMap<>();
        Map<String, Object> map6 = new HashMap<>();
        Map<String, Object> map7 = new HashMap<>();
        Map<String, Object> map8 = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<HashMap> list = cunzuzhihuodongMapper.chdqu1(1);
        List<HashMap> list2 = cunzuzhihuodongMapper.chdqu1(2);
        List<HashMap> list3 = cunzuzhihuodongMapper.chdqu1(3);
        List<HashMap> list4 = cunzuzhihuodongMapper.chdqu1(4);
        List<HashMap> list5 = cunzuzhihuodongMapper.chdqu1(5);
        List<HashMap> list6 = cunzuzhihuodongMapper.chdqu1(6);
        List<HashMap> list7 = cunzuzhihuodongMapper.chdqu1(7);
        List<HashMap> list8 = cunzuzhihuodongMapper.chdqu1(8);
        Integer t = Integer.parseInt(time);
        List b = new ArrayList();
        Map<String, Object> c = new HashMap<>();
        if (list.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    c = new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(1, String.valueOf(j));
                    c.put("total", fTime.size());
                    c.put("time", j);
                    map.put("name", zhens.get(0).getzName());
                    b.add(c);
                    map.put("time", b);
                }
                result.put("0", map);
            }
        } else {
            map.put("name", zhens.get(0).getzName());
            result.put("0", map);
        }
        if (list2.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    c = new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(2, String.valueOf(j));
                    c.put("total", fTime.size());
                    c.put("time", j);
                    map2.put("name", zhens.get(1).getzName());
                    b.add(c);
                    map2.put("time", b);
                }
                result.put("1", map2);
            }
        } else {
            map2.put("name", zhens.get(1).getzName());
            result.put("1", map2);
        }
        if (list3.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    c = new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(3, String.valueOf(j));
                    c.put("total", fTime.size());
                    c.put("time", j);
                    map3.put("name", zhens.get(2).getzName());
                    b.add(c);
                    map3.put("time", b);
                }
                result.put("2", map3);
            }
        } else {
            map3.put("name", zhens.get(2).getzName());
            result.put("2", map3);
        }
        if (list4.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    c = new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(4, String.valueOf(j));
                    c.put("total", fTime.size());
                    c.put("time", j);
                    map4.put("name", zhens.get(3).getzName());
                    b.add(c);
                    map4.put("time", b);
                }
                result.put("3", map4);
            }
        } else {
            map4.put("name", zhens.get(3).getzName());
            result.put("3", map4);
        }
        if (list5.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    c = new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(5, String.valueOf(j));
                    c.put("total", fTime.size());
                    c.put("time", j);
                    map5.put("name", zhens.get(4).getzName());
                    b.add(c);
                    map5.put("time", b);
                }
                result.put("4", map5);
            }
        } else {
            map5.put("name", zhens.get(4).getzName());
            result.put("4", map5);
        }
        if (list6.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    c = new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(6, String.valueOf(j));
                    c.put("total", fTime.size());
                    c.put("time", j);
                    map6.put("name", zhens.get(5).getzName());
                    b.add(c);
                    map6.put("time", b);
                }
                result.put("5", map6);
            }
        } else {
            map6.put("name", zhens.get(5).getzName());
            result.put("5", map6);
        }
        if (list7.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    c = new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(7, String.valueOf(j));
                    c.put("total", fTime.size());
                    c.put("time", j);
                    map7.put("name", zhens.get(6).getzName());
                    b.add(c);
                    map7.put("time", b);
                }
                result.put("6", map7);
            }
        } else {
            map7.put("name", zhens.get(6).getzName());
            result.put("6", map7);
        }
        if (list8.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    c = new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(8, String.valueOf(j));
                    c.put("total", fTime.size());
                    c.put("time", j);
                    map8.put("name", zhens.get(7).getzName());
                    b.add(c);
                    map8.put("time", b);
                }
                result.put("7", map8);
            }
        } else {
            map8.put("name", zhens.get(7).getzName());
            result.put("7", map8);
        }
 /*       if (list.size()!=0) {
            for (HashMap object1 :
                    list) {
                b=new ArrayList();
                for (HashMap object :
                        list) {
                    c=new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(1, sdf.format(object.get("CDYZZHD_Time")));
    //                map.put(sdf.format(object.get("CDYZZHD_Time")), fTime.size());
    //                map.put("name", zhens.get(0).getzName());
                    c.put("total",fTime.size());
                    c.put("time",sdf.format(object.get("CDYZZHD_Time")));
                    map.put("name",zhens.get(0).getzName());
                    b.add(c);
                    map.put("time",b);
                    result.put("0", map);
                }
            }
        }else {
            map.put("name",zhens.get(0).getzName());
            result.put("0",map);
        }
        if (list2.size()!=0) {
            for (HashMap object1 :
                    list2) {
                b=new ArrayList();
                for (HashMap object :
                        list2) {
                    c=new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(2, sdf.format(object.get("CDYZZHD_Time")));
                    c.put("total",fTime.size());
                    c.put("time",sdf.format(object.get("CDYZZHD_Time")));
                    map2.put("name",zhens.get(1).getzName());
                    b.add(c);
                    map2.put("time",b);
                    result.put("1", map2);
                }
            }
        }else {
            map2.put("name",zhens.get(1).getzName());
            result.put("1",map2);
        }
        if (list3.size()!=0) {
            for (HashMap object1 :
                    list3) {
                b=new ArrayList();
                for (HashMap object :
                        list3) {
                    c=new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(3, sdf.format(object.get("CDYZZHD_Time")));
                    c.put("total",fTime.size());
                    c.put("time",sdf.format(object.get("CDYZZHD_Time")));
                    map3.put("name",zhens.get(2).getzName());
                    b.add(c);
                    map3.put("time",b);
                    result.put("2", map3);
                }
            }
        }else {
            map3.put("name",zhens.get(2).getzName());
            result.put("2",map3);
        }
        if (list4.size()!=0) {
            for (HashMap object1 :
                    list4) {
                b=new ArrayList();
                for (HashMap object :
                        list4) {
                    c=new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(4, sdf.format(object.get("CDYZZHD_Time")));
                    c.put("total",fTime.size());
                    c.put("time",sdf.format(object.get("CDYZZHD_Time")));
                    map4.put("name",zhens.get(3).getzName());
                    b.add(c);
                    map4.put("time",b);
                    result.put("3", map4);
                }
            }
        }else {
            map4.put("name",zhens.get(3).getzName());
            result.put("3",map4);
        }
        if (list5.size()!=0) {
            for (HashMap object1 :
                    list5) {
                b=new ArrayList();
                for (HashMap object :
                        list5) {
                    c=new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(5, sdf.format(object.get("CDYZZHD_Time")));
                    c.put("total",fTime.size());
                    c.put("time",sdf.format(object.get("CDYZZHD_Time")));
                    map5.put("name",zhens.get(4).getzName());
                    b.add(c);
                    map5.put("time",b);
                    result.put("4", map5);
                }
            }
        }else {
            map5.put("name",zhens.get(4).getzName());
            result.put("4",map5);
        }
        if (list6.size()!=0) {
            for (HashMap object1 :
                    list6) {
                b=new ArrayList();
                for (HashMap object :
                        list6) {
                    c=new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(6, sdf.format(object.get("CDYZZHD_Time")));
                    c.put("total",fTime.size());
                    c.put("time",sdf.format(object.get("CDYZZHD_Time")));
                    map6.put("name",zhens.get(5).getzName());
                    b.add(c);
                    map6.put("time",b);
                    result.put("5", map6);
                }
            }
        }else {
            map6.put("name",zhens.get(5).getzName());
            result.put("5",map6);
        }
        if (list7.size()!=0) {
            for (HashMap object1 :
                    list7) {
                b=new ArrayList();
                for (HashMap object :
                        list7) {
                    c=new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(7, sdf.format(object.get("CDYZZHD_Time")));
                    c.put("total",fTime.size());
                    c.put("time",sdf.format(object.get("CDYZZHD_Time")));
                    map7.put("name",zhens.get(6).getzName());
                    b.add(c);
                    map7.put("time",b);
                    result.put("6", map7);
                }
            }
        }else {
            map7.put("name",zhens.get(6).getzName());
            result.put("6",map7);
        }
        if (list8.size()!=0) {
            for (HashMap object1 :
                    list8) {
                b=new ArrayList();
                for (HashMap object :
                        list8) {
                    c=new HashMap<>();
                    List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(8, sdf.format(object.get("CDYZZHD_Time")));
                    c.put("total",fTime.size());
                    c.put("time",sdf.format(object.get("CDYZZHD_Time")));
                    map8.put("name",zhens.get(7).getzName());
                    b.add(c);
                    map8.put("time",b);
                    result.put("7", map8);
                }
            }
        }else {
            map8.put("name",zhens.get(7).getzName());
            result.put("7",map8);
        }*/
        a.add(result);
        Map<String, Object> q = new HashMap<>();
        q.put("data", a);
        return q;
    }


    @Override
    public Map<String, Object> zzxian(Integer zhenId, String time) {
        Map<String, Object> result = new HashMap<>();
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        List a = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List b = new ArrayList();
        Map<String, Object> c = new HashMap<>();
        if (zhenId == 1) {
            List<Village> villages = cunzuzhihuodongMapper.findbyId_yp(1);
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map2 = new HashMap<>();
            Map<String, Object> map3 = new HashMap<>();
            List<HashMap> list = cunzuzhihuodongMapper.chdzhen(1, time1, time2);
            List<HashMap> list2 = cunzuzhihuodongMapper.chdzhen(21, time1, time2);
            List<HashMap> list3 = cunzuzhihuodongMapper.chdzhen(22, time1, time2);

            if (list.size() != 0) {
                for (HashMap object1 :
                        list) {
                    b = new ArrayList();
                    for (HashMap object :
                            list) {
                        c = new HashMap<>();
                        List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(1, sdf.format(object.get("CDYZZHD_Time")));
                        c.put("total", fTime.size());
                        c.put("time", sdf.format(object.get("CDYZZHD_Time")));
                        map.put("name", villages.get(0).getvName());
                        b.add(c);
                        map.put("time", b);
                        result.put("0", map);
                    }
                }
            } else {
                map.put("name", villages.get(0).getvName());
                result.put("0", map);
            }
            if (list2.size() != 0) {
                for (HashMap object1 :
                        list2) {
                    b = new ArrayList();
                    for (HashMap object :
                            list2) {
                        c = new HashMap<>();
                        List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(1, sdf.format(object.get("CDYZZHD_Time")));
                        c.put("total", fTime.size());
                        c.put("time", sdf.format(object.get("CDYZZHD_Time")));
                        map2.put("name", villages.get(1).getvName());
                        b.add(c);
                        map2.put("time", b);
                        result.put("0", map2);
                    }
                }
            } else {
                map2.put("name", villages.get(1).getvName());
                result.put("1", map2);
            }
            if (list3.size() != 0) {
                for (HashMap object1 :
                        list3) {
                    b = new ArrayList();
                    for (HashMap object :
                            list3) {
                        c = new HashMap<>();
                        List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(1, sdf.format(object.get("CDYZZHD_Time")));
                        c.put("total", fTime.size());
                        c.put("time", sdf.format(object.get("CDYZZHD_Time")));
                        map3.put("name", villages.get(2).getvName());
                        b.add(c);
                        map3.put("time", b);
                        result.put("0", map3);
                    }
                }
            } else {
                map3.put("name", villages.get(2).getvName());
                result.put("2", map3);
            }
        }
        if (zhenId == 3) {
            List<Village> villages = cunzuzhihuodongMapper.findbyId_yp(3);
            Map<String, Object> map = new HashMap<>();

            List<HashMap> list = cunzuzhihuodongMapper.chdzhen(11, time1, time2);
            if (list.size() != 0) {
                for (HashMap object1 :
                        list) {
                    b = new ArrayList();
                    for (HashMap object :
                            list) {
                        c = new HashMap<>();
                        if (sdf.format(object.get("CDYZZHD_Time")) != null) {
                            List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(3, sdf.format(object.get("CDYZZHD_Time")));
                            c.put("total", fTime.size());
                            c.put("time", sdf.format(object.get("CDYZZHD_Time")));
                        }
                        map.put("name", villages.get(0).getvName());
                        b.add(c);
                        map.put("time", b);
                        result.put("0", map);
                    }
                }
            } else {
                map.put("name", villages.get(0).getvName());
                result.put("0", map);
            }
        }
        if (zhenId == 6) {
            List<Village> villages = cunzuzhihuodongMapper.findbyId_yp(6);
            Map<String, Object> map = new HashMap<>();
            List<HashMap> list = cunzuzhihuodongMapper.chdzhen(2, time1, time2);
            if (list.size() != 0) {
                for (HashMap object1 :
                        list) {
                    b = new ArrayList();
                    for (HashMap object :
                            list) {
                        c = new HashMap<>();
                        if (sdf.format(object.get("CDYZZHD_Time")) != null) {
                            List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.findBydate(6, sdf.format(object.get("CDYZZHD_Time")));
                            c.put("total", fTime.size());
                            c.put("time", sdf.format(object.get("CDYZZHD_Time")));
                        }
                        map.put("name", villages.get(0).getvName());
                        b.add(c);
                        map.put("time", b);
                        result.put("0", map);
                    }
                }
            } else {
                map.put("name", villages.get(0).getvName());
                result.put("0", map);
            }

        }

        a.add(result);
        Map<String, Object> q = new HashMap<>();
        q.put("data", a);
        return q;
    }

    @Override
    public Map<String, Object> Vzhexian(Integer villageId, String time) {
        Village village = villageMapper.FindById_lfm(villageId);
        //Map<String,Object> map =new HashMap<>();
        //Map<String,Object> map2 =new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> aa = new HashMap<>();
        List zz = new ArrayList();
        Integer t = Integer.parseInt(time);

        for (int i = 2012; i <= t; i++) {
            zz = new ArrayList();
            for (int j = 2012; j <= t; j++) {
                aa = new HashMap<>();
                List<Cunzuzhihuodong> fTime = cunzuzhihuodongMapper.Vzhexian(villageId, String.valueOf(j));
                aa.put("time", j);
                aa.put("total", fTime.size());
                zz.add(aa);
            }
            result.put("Vchdzx", zz);
        }

        result.put("name", village.getvName());
        return result;
    }

    //2019-11-27


    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        String path;
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件的名称" + originalFileName);
        //获取文件的类型，以“.”作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        path = "E:\\HJResourse\\Cunhuodong\\";
        File filePath = new File(path);
        System.out.println("文件保存的路径" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            //System.out.println("图片目录不存在，创建图片路径"+filePath);
            filePath.mkdirs();
        }
        //设置文件的新名字
        String fileName = System.currentTimeMillis() + "." + type;
        System.out.println("文件新名称" + fileName);
        //在指定的路径下面创建一个新的文件
        File targerFile = new File(path, fileName);
        //将文件保存到服务器的指定位置
        try {
            file.transferTo(targerFile);
            map.put("path", fileName);
            map.put("name", originalFileName);
            return map;
        } catch (Exception e) {
            System.out.println("文件保存错误");
            map.put("path", "");
            e.printStackTrace();
        }
        return map;
    }
}
