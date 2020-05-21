package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.FeedbackMapper;
import com.hj.jdpth.repository.YonghuMapper;
import com.hj.jdpth.repository.ZhenMapper;
import com.hj.jdpth.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;
    @Autowired
    YonghuMapper yonghuMapper;
    @Autowired
    ZhenMapper zhenMapper;

    @Override
    public Map<String, Object> FeedbakcReply(Feedbackreply feedbackreply, Integer managerId, Integer frFeedbackid) {
        Map<String, Object> map = new HashMap<>();
        Feedback feedback = feedbackMapper.findFeedback(frFeedbackid);
        feedbackreply.setFrPeople(managerId);
        feedbackreply.setFrFeedbackid(frFeedbackid);
        feedbackreply.setFrTime(new Date());
        boolean b = feedbackMapper.Reply(feedbackreply);
        if (b == true) {
            feedback.setfHandle(true);
            feedbackMapper.clFeedback(feedback);
            map.put("statue", "success");
        } else {
            map.put("statue", "false");
        }
        return map;
    }

    @Override
    public Map<String, Object> YichuliSearch(int regionId, int zhenId, int villageId, String title, int mType, Integer startPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Feedback> feedbackPage = feedbackMapper.SearchYichuli(regionId, zhenId, villageId, title);
        Backtype backtype = null;
        Feedbackreply feedbackreply = null;
        Yonghu yonghu = null;
        for (int i = 0; i < feedbackPage.size(); i++) {
            backtype = feedbackMapper.FindBacktype(feedbackPage.get(i).getfType());
            feedbackreply = feedbackMapper.FindFeedbackreply(feedbackPage.get(i).getFeedbackId());
            yonghu = yonghuMapper.queryById_yyq(feedbackPage.get(i).getfPeopleid());
            feedbackPage.get(i).setBacktype(backtype);
            feedbackPage.get(i).setFeedbackreply(feedbackreply);
            feedbackPage.get(i).setYonghu(yonghu);
        }
        map.put("record", feedbackPage);
        //总页数
        map.put("total", feedbackPage.getPages());
        //记录总数
        map.put("count", feedbackPage.getTotal());
        //当前第几页
        map.put("nowPage", feedbackPage.getPageNum());
        return map;
    }

    @Override
    public Map<String, Object> WeichuliSearch(int regionId, int zhenId, int villageId, String title, int mType, Integer startPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Feedback> feedbackPage = feedbackMapper.SearchWeichuli(regionId, zhenId, villageId, title);
        Backtype backtype = null;
        Feedbackreply feedbackreply = null;
        Yonghu yonghu = null;
        for (int i = 0; i < feedbackPage.size(); i++) {
            backtype = feedbackMapper.FindBacktype(feedbackPage.get(i).getfType());
            feedbackreply = feedbackMapper.FindFeedbackreply(feedbackPage.get(i).getFeedbackId());
            yonghu = yonghuMapper.queryById_yyq(feedbackPage.get(i).getfPeopleid());
            feedbackPage.get(i).setBacktype(backtype);
            feedbackPage.get(i).setFeedbackreply(feedbackreply);
            feedbackPage.get(i).setYonghu(yonghu);
        }
        map.put("record", feedbackPage);
        //总页数
        map.put("total", feedbackPage.getPages());
        //记录总数
        map.put("count", feedbackPage.getTotal());
        //当前第几页
        map.put("nowPage", feedbackPage.getPageNum());
        return map;
    }

    @Override
    public Map<String, Object> Xiangshang(int regionId, int zhenId, int villageId, String title, int mType, Integer startPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Feedback> feedbackPage = feedbackMapper.Xiangshangfankui(regionId, zhenId, villageId, title);
        Backtype backtype = null;
        Feedbackreply feedbackreply = null;
        Yonghu yonghu = null;
        for (int i = 0; i < feedbackPage.size(); i++) {
            backtype = feedbackMapper.FindBacktype(feedbackPage.get(i).getfType());
            feedbackreply = feedbackMapper.FindFeedbackreply(feedbackPage.get(i).getFeedbackId());
            yonghu = yonghuMapper.queryById_yyq(feedbackPage.get(i).getfPeopleid());
            feedbackPage.get(i).setBacktype(backtype);
            feedbackPage.get(i).setFeedbackreply(feedbackreply);
            feedbackPage.get(i).setYonghu(yonghu);
        }
        map.put("record", feedbackPage);
        //总页数
        map.put("total", feedbackPage.getPages());
        //记录总数
        map.put("count", feedbackPage.getTotal());
        //当前第几页
        map.put("nowPage", feedbackPage.getPageNum());
        return map;
    }


    @Override
    public Map<String, Object> chulilv(int zhenId, String time) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        int yichuli = 0;
        int weichuli = 0;
        List<Feedback> list = feedbackMapper.Chulilv(zhenId, time1, time2);
        for (Feedback f :
                list) {
            if (f.getfHandle() == true) {
                yichuli++;
            }
            if (f.getfHandle() == false) {
                weichuli++;
            }
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        map.put("yichuli", yichuli);
        map.put("weichuli", weichuli);
        map.put("zongfankui", list.size());
        if (list.size() == 0) {
            map.put("yichulilv", 0);
            map.put("weichulilv", 0);
        } else {
            map.put("yichulilv", numberFormat.format((float) yichuli / (float) list.size() * 100) + "%");
            map.put("weichulilv", numberFormat.format((float) weichuli / (float) list.size() * 100) + "%");
        }
        result.put("data", map);
        return result;
    }

    @Override
    public Map<String, Object> bingtu(String time) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        int yichuli = 0;
        int weichuli = 0;
        List<Feedback> list = feedbackMapper.bingtu(1349, time1, time2);
        for (Feedback f :
                list) {
            if (f.getfHandle() == true) {
                yichuli++;
            }
            if (f.getfHandle() == false) {
                weichuli++;
            }
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        map.put("yichuli", yichuli);
        map.put("weichuli", weichuli);
        map.put("zongfankui", list.size());
        if (list.size() == 0) {
            map.put("yichulilv", 0);
            map.put("weichulilv", 0);
        } else {
            map.put("yichulilv", numberFormat.format((float) yichuli / (float) list.size() * 100) + "%");
            map.put("weichulilv", numberFormat.format((float) weichuli / (float) list.size() * 100) + "%");
        }
        result.put("data", map);
        return result;
    }

    @Override
    public Map<String, Object> leaderUse(Integer zhenId, String time) {
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        List c = new ArrayList();
        List<Zhen> zhens = zhenMapper.queryZhenByQuId_yyq(1349);
        List<HashMap> list = feedbackMapper.queryLeader(1, time1, time2);
        List<HashMap> list2 = feedbackMapper.queryLeader(2, time1, time2);
        List<HashMap> list3 = feedbackMapper.queryLeader(3, time1, time2);
        List<HashMap> list4 = feedbackMapper.queryLeader(4, time1, time2);
        List<HashMap> list5 = feedbackMapper.queryLeader(5, time1, time2);
        List<HashMap> list6 = feedbackMapper.queryLeader(6, time1, time2);
        List<HashMap> list7 = feedbackMapper.queryLeader(7, time1, time2);
        List<HashMap> list8 = feedbackMapper.queryLeader(8, time1, time2);
        List<HashMap> list21 = feedbackMapper.ycl(1, time1, time2);
        List<HashMap> list22 = feedbackMapper.ycl(2, time1, time2);
        List<HashMap> list23 = feedbackMapper.ycl(3, time1, time2);
        List<HashMap> list24 = feedbackMapper.ycl(4, time1, time2);
        List<HashMap> list25 = feedbackMapper.ycl(5, time1, time2);
        List<HashMap> list26 = feedbackMapper.ycl(6, time1, time2);
        List<HashMap> list27 = feedbackMapper.ycl(7, time1, time2);
        List<HashMap> list28 = feedbackMapper.ycl(8, time1, time2);
        List<HashMap> list31 = feedbackMapper.wcl(1, time1, time2);
        List<HashMap> list32 = feedbackMapper.wcl(2, time1, time2);
        List<HashMap> list33 = feedbackMapper.wcl(3, time1, time2);
        List<HashMap> list34 = feedbackMapper.wcl(4, time1, time2);
        List<HashMap> list35 = feedbackMapper.wcl(5, time1, time2);
        List<HashMap> list36 = feedbackMapper.wcl(6, time1, time2);
        List<HashMap> list37 = feedbackMapper.wcl(7, time1, time2);
        List<HashMap> list38 = feedbackMapper.wcl(8, time1, time2);
        Map<String, Object> a1 = new HashMap<>();
        Map<String, Object> a2 = new HashMap<>();
        Map<String, Object> a3 = new HashMap<>();
        Map<String, Object> a4 = new HashMap<>();
        Map<String, Object> a5 = new HashMap<>();
        Map<String, Object> a6 = new HashMap<>();
        Map<String, Object> a7 = new HashMap<>();
        Map<String, Object> a8 = new HashMap<>();
        Map<String, Object> a = new HashMap<>();
        if (list.size() != 0) {
            a1.put("total", list.size());
        } else {
            a1.put("total", 0);
        }
        if (list21.size() != 0) {
            a1.put("ycl", list21.size());
        } else {
            a1.put("ycl", 0);
        }
        if (list31.size() != 0) {
            a1.put("wcl", list31.size());
        } else {
            a1.put("wcl", 0);
        }
        if (list2.size() != 0) {
            a2.put("total", list2.size());
        } else {
            a2.put("total", 0);
        }
        if (list22.size() != 0) {
            a1.put("ycl", list22.size());
        } else {
            a2.put("ycl", 0);
        }
        if (list32.size() != 0) {
            a1.put("wcl", list32.size());
        } else {
            a2.put("wcl", 0);
        }
        if (list3.size() != 0) {
            a3.put("total", list3.size());
        } else {
            a3.put("total", 0);
        }
        if (list23.size() != 0) {
            a3.put("ycl", list23.size());
        } else {
            a3.put("ycl", 0);
        }
        if (list33.size() != 0) {
            a3.put("wcl", list33.size());
        } else {
            a3.put("wcl", 0);
        }
        if (list4.size() != 0) {
            a4.put("total", list4.size());
        } else {
            a4.put("total", 0);
        }
        if (list24.size() != 0) {
            a4.put("ycl", list24.size());
        } else {
            a4.put("ycl", 0);
        }
        if (list34.size() != 0) {
            a4.put("wcl", list34.size());
        } else {
            a4.put("wcl", 0);
        }
        if (list5.size() != 0) {
            a5.put("total", list5.size());
        } else {
            a5.put("total", 0);
        }
        if (list25.size() != 0) {
            a5.put("ycl", list25.size());
        } else {
            a5.put("ycl", 0);
        }
        if (list35.size() != 0) {
            a5.put("wcl", list35.size());
        } else {
            a5.put("wcl", 0);
        }

        if (list5.size() != 0) {
            a5.put("total", list5.size());
        } else {
            a5.put("total", 0);
        }
        if (list25.size() != 0) {
            a5.put("ycl", list25.size());
        } else {
            a5.put("ycl", 0);
        }
        if (list35.size() != 0) {
            a5.put("wcl", list35.size());
        } else {
            a5.put("wcl", 0);
        }
        if (list6.size() != 0) {
            a6.put("total", list6.size());
        } else {
            a6.put("total", 0);
        }
        if (list26.size() != 0) {
            a6.put("ycl", list26.size());
        } else {
            a6.put("ycl", 0);
        }
        if (list36.size() != 0) {
            a6.put("wcl", list36.size());
        } else {
            a6.put("wcl", 0);
        }
        if (list7.size() != 0) {
            a7.put("total", list7.size());
        } else {
            a7.put("total", 0);
        }
        if (list27.size() != 0) {
            a7.put("ycl", list27.size());
        } else {
            a7.put("ycl", 0);
        }
        if (list37.size() != 0) {
            a7.put("wcl", list37.size());
        } else {
            a7.put("wcl", 0);
        }
        if (list8.size() != 0) {
            a8.put("total", list8.size());
        } else {
            a8.put("total", 0);
        }
        if (list28.size() != 0) {
            a8.put("ycl", list28.size());
        } else {
            a8.put("ycl", 0);
        }
        if (list38.size() != 0) {
            a8.put("wcl", list38.size());
        } else {
            a8.put("wcl", 0);
        }
        a1.put("name", zhens.get(0).getzName());
        a2.put("name", zhens.get(1).getzName());
        a3.put("name", zhens.get(2).getzName());
        a4.put("name", zhens.get(3).getzName());
        a5.put("name", zhens.get(4).getzName());
        a6.put("name", zhens.get(5).getzName());
        a7.put("name", zhens.get(6).getzName());
        a8.put("name", zhens.get(7).getzName());
        /*for (HashMap object: list
        ) {
            if(map.containsKey(object.get("zName"))){
                map.put(String.valueOf(object.get("zName")), map.get(object.get("zName")).intValue() + 1);
            }else{
                map.put(String.valueOf(object.get("zName")), new Integer(1));
            }
        }
        for (HashMap object2: list2
        ) {
            if(map2.containsKey(object2.get("zName"))){
                map2.put(String.valueOf(object2.get("zName"))+"已处理", map2.get(object2.get("zName")).intValue() + 1);
            }else{
                map2.put(String.valueOf(object2.get("zName"))+"已处理", new Integer(1));
            }
        }
        for (HashMap object3: list3
        ) {
            if(map3.containsKey(object3.get("zName"))){
                map3.put(String.valueOf(object3.get("zName"))+"未处理", map2.get(object3.get("zName")).intValue() + 1);
            }else{
                map3.put(String.valueOf(object3.get("zName"))+"未处理", new Integer(1));
            }
        }
        result.put("total",map);
        result.put("yichuli",map2);
        result.put("weichuli",map3);*/

//        a.put(zhens.get(0).getzName(),a1);
//        a.put(zhens.get(1).getzName(),a2);
//        a.put(zhens.get(2).getzName(),a3);
//        a.put(zhens.get(3).getzName(),a4);
//        a.put(zhens.get(4).getzName(),a5);
//        a.put(zhens.get(5).getzName(),a6);
//        a.put(zhens.get(6).getzName(),a7);
//        a.put(zhens.get(7).getzName(),a8);
        c.add(a1);
        c.add(a2);
        c.add(a3);
        c.add(a4);
        c.add(a5);
        c.add(a6);
        c.add(a7);
        c.add(a8);
        a.put("data", c);
        return a;
    }

    @Override
    public Map<String, Object> qulx(Integer zhenId, String time) {
        List c = new ArrayList();
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        Map<String, Object> a = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> result2 = new HashMap<>();
        Map<String, Object> result3 = new HashMap<>();
        Map<String, Object> result4 = new HashMap<>();
        Map<String, Object> result5 = new HashMap<>();
        Map<String, Object> result6 = new HashMap<>();
        Map<String, Object> result7 = new HashMap<>();
        Map<String, Object> result8 = new HashMap<>();
        List<Zhen> zhens = zhenMapper.queryZhenByQuId_yyq(1349);
        List<Backtype> backtypes = feedbackMapper.typelist();
        List<HashMap> list1 = feedbackMapper.lx(1, time1, time2);
        List<HashMap> list2 = feedbackMapper.lx(2, time1, time2);
        List<HashMap> list3 = feedbackMapper.lx(3, time1, time2);
        List<HashMap> list4 = feedbackMapper.lx(4, time1, time2);
        List<HashMap> list5 = feedbackMapper.lx(5, time1, time2);
        List<HashMap> list6 = feedbackMapper.lx(6, time1, time2);
        List<HashMap> list7 = feedbackMapper.lx(7, time1, time2);
        List<HashMap> list8 = feedbackMapper.lx(8, time1, time2);
        if (list1.size() != 0) {
            for (Backtype b :
                    backtypes) {
                List<Feedback> flist = feedbackMapper.findbylx(zhens.get(0).getZhenId(), b.getbKey());
                if (flist.size() != 0) {
                    result.put(b.getbType(), flist.size());
                } else {
                    result.put(b.getbType(), 0);
                }
            }
        } else {
            for (Backtype b :
                    backtypes) {
                result.put(b.getbType(), 0);
            }
        }
        if (list2.size() != 0) {
            for (Backtype b :
                    backtypes) {
                List<Feedback> flist = feedbackMapper.findbylx(zhens.get(1).getZhenId(), b.getbKey());
                if (flist.size() != 0) {
                    result2.put(b.getbType(), flist.size());
                } else {
                    result2.put(b.getbType(), 0);
                }
            }
        } else {
            for (Backtype b :
                    backtypes) {
                result2.put(b.getbType(), 0);
            }
        }
        if (list3.size() != 0) {
            for (Backtype b :
                    backtypes) {
                List<Feedback> flist = feedbackMapper.findbylx(zhens.get(2).getZhenId(), b.getbKey());
                if (flist.size() != 0) {
                    result3.put(b.getbType(), flist.size());
                } else {
                    result3.put(b.getbType(), 0);
                }
            }
        } else {
            for (Backtype b :
                    backtypes) {
                result3.put(b.getbType(), 0);
            }
        }
        if (list4.size() != 0) {
            for (Backtype b :
                    backtypes) {
                List<Feedback> flist = feedbackMapper.findbylx(zhens.get(3).getZhenId(), b.getbKey());
                if (flist.size() != 0) {
                    result4.put(b.getbType(), flist.size());
                } else {
                    result4.put(b.getbType(), 0);
                }
            }
        } else {
            for (Backtype b :
                    backtypes) {
                result4.put(b.getbType(), 0);
            }
        }
        if (list5.size() != 0) {
            for (Backtype b :
                    backtypes) {
                List<Feedback> flist = feedbackMapper.findbylx(zhens.get(4).getZhenId(), b.getbKey());
                if (flist.size() != 0) {
                    result5.put(b.getbType(), flist.size());
                } else {
                    result5.put(b.getbType(), 0);
                }
            }
        } else {
            for (Backtype b :
                    backtypes) {
                result5.put(b.getbType(), 0);
            }
        }
        if (list6.size() != 0) {
            for (Backtype b :
                    backtypes) {
                List<Feedback> flist = feedbackMapper.findbylx(zhens.get(5).getZhenId(), b.getbKey());
                if (flist.size() != 0) {
                    result6.put(b.getbType(), flist.size());
                } else {
                    result6.put(b.getbType(), 0);
                }
            }
        } else {
            for (Backtype b :
                    backtypes) {
                result6.put(b.getbType(), 0);
            }
        }
        if (list7.size() != 0) {
            for (Backtype b :
                    backtypes) {
                List<Feedback> flist = feedbackMapper.findbylx(zhens.get(6).getZhenId(), b.getbKey());
                if (flist.size() != 0) {
                    result7.put(b.getbType(), flist.size());
                } else {
                    result7.put(b.getbType(), 0);
                }
            }
        } else {
            for (Backtype b :
                    backtypes) {
                result7.put(b.getbType(), 0);
            }
        }
        if (list8.size() != 0) {
            for (Backtype b :
                    backtypes) {
                List<Feedback> flist = feedbackMapper.findbylx(zhens.get(7).getZhenId(), b.getbKey());
                if (flist.size() != 0) {
                    result8.put(b.getbType(), flist.size());
                } else {
                    result8.put(b.getbType(), 0);
                }
            }
        } else {
            for (Backtype b :
                    backtypes) {
                result8.put(b.getbType(), 0);
            }
        }
        result.put("name", zhens.get(0).getzName());
        result2.put("name", zhens.get(1).getzName());
        result3.put("name", zhens.get(2).getzName());
        result4.put("name", zhens.get(3).getzName());
        result5.put("name", zhens.get(4).getzName());
        result6.put("name", zhens.get(5).getzName());
        result7.put("name", zhens.get(6).getzName());
        result8.put("name", zhens.get(7).getzName());
/*        a.put(zhens.get(0).getzName(),result);
        a.put(zhens.get(1).getzName(),result2);
        a.put(zhens.get(2).getzName(),result3);
        a.put(zhens.get(3).getzName(),result4);
        a.put(zhens.get(4).getzName(),result5);
        a.put(zhens.get(5).getzName(),result6);
        a.put(zhens.get(6).getzName(),result7);
        a.put(zhens.get(7).getzName(),result8);*/
        c.add(result);
        c.add(result2);
        c.add(result3);
        c.add(result4);
        c.add(result5);
        c.add(result6);
        c.add(result7);
        c.add(result8);
        a.put("data", c);
        return a;
    }

    @Override
    public Map<String, Object> ycllb(Integer zhenId, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        List list2 = new ArrayList();
        List<HashMap> list = feedbackMapper.ycllb(zhenId, time1, time2);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        for (HashMap object : list) {
            result.put("Village_Id", object.get("Village_Id"));
            result.put("Feedback_Id", object.get("Feedback_Id"));
            result.put("V_Name", object.get("V_Name"));
            result.put("F_Content", object.get("F_Content"));
            result.put("F_Title", object.get("F_Title"));
            result.put("B_Type", object.get("B_Type"));
            result.put("zName", object.get("zName"));
            if (object.get("F_Time") != null) {
                result.put("F_Time", sdf.format(object.get("F_Time")));
            }
            list2.add(result);
        }
        map.put("data", list);
        return map;
    }

    @Override
    public Map<String, Object> wcllb(Integer zhenId, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        List<HashMap> list = feedbackMapper.wcllb(zhenId, time1, time2);
        List list2 = new ArrayList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        for (HashMap object : list) {
            result.put("Village_Id", object.get("Village_Id"));
            result.put("Feedback_Id", object.get("Feedback_Id"));
            result.put("V_Name", object.get("V_Name"));
            result.put("F_Content", object.get("F_Content"));
            result.put("F_Title", object.get("F_Title"));
            result.put("B_Type", object.get("B_Type"));
            result.put("zName", object.get("zName"));
            if (object.get("F_Time") != null) {
                result.put("F_Time", sdf.format(object.get("F_Time")));
            }
            list2.add(result);
        }
        map.put("data", list);
        return map;
    }

    @Override
    public Map<String, Object> upregion(String time) {
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        int yichuli = 0;
        int weichuli = 0;
        List<Feedback> list = feedbackMapper.upregion(1349, time1, time2);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        for (Feedback f :
                list) {
            if (f.getfHandle() == true) {
                yichuli++;
            }
            if (f.getfHandle() == false) {
                weichuli++;
            }
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        map.put("yichuli", yichuli);
        map.put("weichuli", weichuli);
        map.put("zongfankui", list.size());
        if (list.size() == 0) {
            map.put("yichulilv", 0);
            map.put("weichulilv", 0);
        } else {
            map.put("yichulilv", numberFormat.format((float) yichuli / (float) list.size() * 100) + "%");
            map.put("weichulilv", numberFormat.format((float) weichuli / (float) list.size() * 100) + "%");
        }
        result.put("data", map);
        return map;
    }

    @Override
    public Map<String, Object> zhenlx(Integer zhenId, String time) {
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        List list1 = new ArrayList();
        List<Backtype> backtypes = feedbackMapper.typelist();
        List<HashMap> list = feedbackMapper.lx(zhenId, time1, time2);
        map.put("total", list.size());
        /*if (list.size()!=0){
            for (Backtype b :
                    backtypes) {
                List<Feedback> flist = feedbackMapper.findbylx(zhenId, b.getbKey());
                if (flist.size()!=0){
                    map.put(b.getbType(), flist.size());
                }else {
                    map.put(b.getbType(), 0);
                }
            }
        }else {
            for (Backtype b :
                    backtypes) {
                map.put(b.getbType(), 0);
            }
        }*/
        for (Backtype b :
                backtypes) {
            list1 = new ArrayList();
            for (Backtype b1 :
                    backtypes) {
                Map<String, Object> aa = new HashMap<>();
                List<Feedback> flist = feedbackMapper.findbylx(zhenId, b1.getbKey());
                if (flist.size() != 0) {
                    aa.put("shuliang", flist.size());
                } else {
                    aa.put("shuliang", 0);
                }
                aa.put("type", b1.getbType());
                list1.add(aa);
            }
        }
//        result.put("data",map);
//        result.put("type",backtypes);
//        result.put("list",list1);
        result.put("total", map);
        result.put("data", list1);
        return result;
    }

    @Override
    public Map<String, Object> quzhexian(Integer zhenId, String time) {
        /*String[] strs=time.split("~");
        String time1=strs[0];
        String time2=strs[1];*/
        List a = new ArrayList();
        List b = new ArrayList();
        List<Zhen> zhens = zhenMapper.queryZhenByQuId_yyq(1349);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();
        Map<String, Object> map5 = new HashMap<>();
        Map<String, Object> map6 = new HashMap<>();
        Map<String, Object> map7 = new HashMap<>();
        Map<String, Object> map8 = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        List<HashMap> list = feedbackMapper.queryLeader(1, time1, time2);
//        List<HashMap> list2 = feedbackMapper.queryLeader(2, time1, time2);
//        List<HashMap> list3 = feedbackMapper.queryLeader(3, time1, time2);
//        List<HashMap> list4 = feedbackMapper.queryLeader(4, time1, time2);
//        List<HashMap> list5 = feedbackMapper.queryLeader(5, time1, time2);
//        List<HashMap> list6 = feedbackMapper.queryLeader(6, time1, time2);
//        List<HashMap> list7 = feedbackMapper.queryLeader(7, time1, time2);
//        List<HashMap> list8 = feedbackMapper.queryLeader(8, time1, time2);
        List<HashMap> list = feedbackMapper.queryLeader2(1);
        List<HashMap> list2 = feedbackMapper.queryLeader2(2);
        List<HashMap> list3 = feedbackMapper.queryLeader2(3);
        List<HashMap> list4 = feedbackMapper.queryLeader2(4);
        List<HashMap> list5 = feedbackMapper.queryLeader2(5);
        List<HashMap> list6 = feedbackMapper.queryLeader2(6);
        List<HashMap> list7 = feedbackMapper.queryLeader2(7);
        List<HashMap> list8 = feedbackMapper.queryLeader2(8);
        Integer t = Integer.parseInt(time);
        if (list.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    result = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydate(1, String.valueOf(j));
                    result.put("time", j);
                    result.put("total", fTime.size());
                    b.add(result);
                    map.put("time", b);
                    map.put("name", zhens.get(0).getzName());
                }
            }
        } else {
            map.put("name", zhens.get(0).getzName());
        }
        if (list2.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    result = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydate(2, String.valueOf(j));
                    result.put("time", j);
                    result.put("total", fTime.size());
                    b.add(result);
                    map2.put("time", b);
                    map2.put("name", zhens.get(1).getzName());
                }
            }
        } else {
            map2.put("name", zhens.get(1).getzName());
        }
        if (list3.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    result = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydate(3, String.valueOf(j));
                    result.put("time", j);
                    result.put("total", fTime.size());
                    b.add(result);
                    map3.put("time", b);
                    map3.put("name", zhens.get(2).getzName());
                }
            }
        } else {
            map3.put("name", zhens.get(2).getzName());
        }
        if (list4.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    result = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydate(4, String.valueOf(j));
                    result.put("time", j);
                    result.put("total", fTime.size());
                    b.add(result);
                    map4.put("time", b);
                    map4.put("name", zhens.get(3).getzName());
                }
            }
        } else {
            map4.put("name", zhens.get(3).getzName());
        }
        if (list5.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    result = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydate(5, String.valueOf(j));
                    result.put("time", j);
                    result.put("total", fTime.size());
                    b.add(result);
                    map5.put("time", b);
                    map5.put("name", zhens.get(4).getzName());
                }
            }
        } else {
            map5.put("name", zhens.get(4).getzName());
        }
        if (list6.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    result = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydate(6, String.valueOf(j));
                    result.put("time", j);
                    result.put("total", fTime.size());
                    b.add(result);
                    map6.put("time", b);
                    map6.put("name", zhens.get(5).getzName());
                }
            }
        } else {
            map6.put("name", zhens.get(5).getzName());
        }
        if (list7.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    result = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydate(7, String.valueOf(j));
                    result.put("time", j);
                    result.put("total", fTime.size());
                    b.add(result);
                    map7.put("time", b);
                    map7.put("name", zhens.get(6).getzName());
                }
            }
        } else {
            map7.put("name", zhens.get(6).getzName());
        }
        if (list8.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    result = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydate(8, String.valueOf(j));
                    result.put("time", j);
                    result.put("total", fTime.size());
                    b.add(result);
                    map8.put("time", b);
                    map8.put("name", zhens.get(7).getzName());
                }
            }
        } else {
            map8.put("name", zhens.get(7).getzName());
        }
        /*if (list.size()!=0) {
            for (HashMap object1:list){
                b=new ArrayList();
            for (HashMap object :
                    list) {
                result =new HashMap<>();
                List<Feedback> fTime = feedbackMapper.findbydate(1, sdf.format(object.get("F_Time")));
                result.put("time",sdf.format(object.get("F_Time")));
                result.put("total",fTime.size());
                b.add(result);
                map.put("time",b);
                map.put("name",zhens.get(0).getzName());
            }
            }
        }else {
            map.put("name",zhens.get(0).getzName());
        }
        if (list2.size()!=0) {
            for (HashMap object1 :
                    list2) {
                b=new ArrayList();
            for (HashMap object :
                    list2) {
                result =new HashMap<>();
                List<Feedback> fTime = feedbackMapper.findbydate(2, sdf.format(object.get("F_Time")));
                result.put("time",sdf.format(object.get("F_Time")));
                result.put("total",fTime.size());
                b.add(result);
                map2.put("time",b);
                map2.put("name",zhens.get(1).getzName());
            }
            }
        }else {
            map2.put("name",zhens.get(1).getzName());
        }
        if (list3.size()!=0) {
            for (HashMap object1 :
                    list3) {
                b=new ArrayList();
            for (HashMap object :
                    list3) {
                result =new HashMap<>();
                List<Feedback> fTime = feedbackMapper.findbydate(3, sdf.format(object.get("F_Time")));
                result.put("time",sdf.format(object.get("F_Time")));
                result.put("total",fTime.size());
                b.add(result);
                map3.put("time",b);
                map3.put("name",zhens.get(2).getzName());
            }
            }
        }else {
            map3.put("name",zhens.get(2).getzName());
        }
        if (list4.size()!=0) {
            for (HashMap object1 :
                    list4) {
                b=new ArrayList();
            for (HashMap object :
                    list4) {
                result =new HashMap<>();
                List<Feedback> fTime = feedbackMapper.findbydate(4, sdf.format(object.get("F_Time")));
                result.put("time",sdf.format(object.get("F_Time")));
                result.put("total",fTime.size());
                b.add(result);
                map4.put("time",b);
                map4.put("name",zhens.get(3).getzName());
            }
            }
        }else {
            map4.put("name",zhens.get(3).getzName());
        }
        if (list5.size()!=0) {
            for (HashMap object1 :
                    list5) {
                b=new ArrayList();
            for (HashMap object :
                    list5) {
                result =new HashMap<>();
                List<Feedback> fTime = feedbackMapper.findbydate(5, sdf.format(object.get("F_Time")));
                result.put("time",sdf.format(object.get("F_Time")));
                result.put("total",fTime.size());
                b.add(result);
                map5.put("time",b);
                map5.put("name",zhens.get(4).getzName());
            }
            }
        }else {
            map5.put("name",zhens.get(4).getzName());
        }
        if (list6.size()!=0) {
            for (HashMap object1:
                 list6) {
                b=new ArrayList();
            for (HashMap object :
                    list6) {
                result =new HashMap<>();
                List<Feedback> fTime = feedbackMapper.findbydate(6, sdf.format(object.get("F_Time")));
                result.put("time",sdf.format(object.get("F_Time")));
                result.put("total",fTime.size());
                b.add(result);
                map6.put("time",b);
                map6.put("name",zhens.get(5).getzName());
            }
            }
        }else {
            map6.put("name",zhens.get(5).getzName());
        }
        if (list7.size()!=0) {
            for (HashMap object1:
                 list7) {
                b=new ArrayList();
            for (HashMap object :
                    list7) {
                result =new HashMap<>();
                List<Feedback> fTime = feedbackMapper.findbydate(7, sdf.format(object.get("F_Time")));
                result.put("time",sdf.format(object.get("F_Time")));
                result.put("total",fTime.size());
                b.add(result);
                map7.put("time",b);
                map7.put("name",zhens.get(6).getzName());
            }
            }
        }else {
            map7.put("name",zhens.get(6).getzName());
        }
        if (list8.size()!=0) {
            for (HashMap object1 :
                    list8) {
                b=new ArrayList();
            for (HashMap object :
                    list8) {
                result =new HashMap<>();
                List<Feedback> fTime = feedbackMapper.findbydate(8, sdf.format(object.get("F_Time")));
                result.put("time",sdf.format(object.get("F_Time")));
                result.put("total",fTime.size());
                b.add(result);
                map8.put("time",b);
                map8.put("name",zhens.get(7).getzName());
            }
            }
        }else {
            map8.put("name",zhens.get(7).getzName());
        }*/

//        a.add(result);
        a.add(map);
        a.add(map2);
        a.add(map3);
        a.add(map4);
        a.add(map5);
        a.add(map6);
        a.add(map7);
        a.add(map8);
        Map<String, Object> q = new HashMap<>();
        q.put("data", a);
        return q;
    }

    @Override
    public Map<String, Object> zhenzhexian(Integer zhenId, String time) {
        /*String[] strs=time.split("~");
        String time1=strs[0];
        String time2=strs[1];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");*/
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> a = new HashMap<>();
        List b = new ArrayList();
        List<HashMap> list = feedbackMapper.ycl1(zhenId);
        List<HashMap> list2 = feedbackMapper.wcl1(zhenId);
        Integer t = Integer.parseInt(time);
        if (list.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    a = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydateycl(zhenId, String.valueOf(j));
                    a.put("time", j);
                    a.put("total", fTime.size());
                    b.add(a);
                }
                result.put("ycl", b);
            }
        } else {
            result.put("ycl", 0);
        }
        if (list2.size() != 0) {
            for (int i = 2012; i <= t; i++) {
                b = new ArrayList();
                for (int j = 2012; j <= t; j++) {
                    a = new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydatewcl(zhenId, String.valueOf(j));
                    a.put("time", j);
                    a.put("total", fTime.size());
                    b.add(a);
                }
                result.put("wcl", b);
            }
        } else {
            result.put("wcl", 0);
        }
/*        if (list.size()!=0) {
            for (HashMap object1 :
                    list) {
                b=new ArrayList();
                for (HashMap object :
                        list) {
                    a=new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydateycl(zhenId, sdf.format(object.get("F_Time")));
                    if (fTime.size() != 0) {
//                        map.put(sdf.format(object.get("F_Time")), fTime.size());
                        a.put("time",sdf.format(object.get("F_Time")));
                        a.put("total",fTime.size());
                        b.add(a);
                        map.put("",b);
                    }
                    result.put("ycl", map);
                }
            }
        }else {
            map.put("",0);
            result.put("ycl", map);
        }
        if (list2.size()!=0) {
            for (HashMap object1 :
                    list2) {
                b=new ArrayList();
                for (HashMap object :
                        list2) {
                    a=new HashMap<>();
                    List<Feedback> fTime = feedbackMapper.findbydatewcl(zhenId, sdf.format(object.get("F_Time")));
                    if (fTime.size() != 0) {
//                        map2.put(sdf.format(object.get("F_Time")), fTime.size());
                        a.put("time",sdf.format(object.get("F_Time")));
                        a.put("total",fTime.size());
                        b.add(a);
                        map2.put("",b);
                    }
                    result.put("wcl", map2);
                }
            }
        }else {
            map2.put("", 0);
            result.put("wcl", map2);
        }*/
        return result;
    }

    @Override
    public Map<String, Object> qulxbintu(String time) {
        String[] strs = time.split("~");
        String time1 = strs[0];
        String time2 = strs[1];
        Map<String, Object> result = new HashMap<>();
        List list1 = new ArrayList();
        List<Backtype> backtypes = feedbackMapper.typelist();
//        for (Backtype b :
//                backtypes) {
        list1 = new ArrayList();
        for (Backtype b1 :
                backtypes) {
            Map<String, Object> aa = new HashMap<>();
            List<Feedback> flist = feedbackMapper.qulxbintufindbylx(b1.getbKey(), time1, time2);
            if (flist.size() != 0) {
                aa.put("shuliang", flist.size());
            } else {
                aa.put("shuliang", 0);
            }
            aa.put("type", b1.getbType());
            list1.add(aa);
        }
//        }
        result.put("data", list1);
        return result;
    }
}
