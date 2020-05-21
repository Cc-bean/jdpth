package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.FeedbackMapper;
import com.hj.jdpth.repository.VillageMapper;
import com.hj.jdpth.repository.ZhenMapper;
import com.hj.jdpth.service.FeedService;
import com.hj.jdpth.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;
    @Autowired
    FeedbackMapper feedbackMapper;
    @Autowired
    FeedService feedService;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    ZhenMapper zhenMapper;

    @GetMapping(value = "/XQ/{feedbackId}")
    public Map<String, Object> XQ(@PathVariable(value = "feedbackId") Integer feedbackId) {
        Map<String, Object> map = new HashMap<>();
        Feedback feedback = feedbackMapper.findFeedback(feedbackId);
        Backtype backtype = feedbackMapper.FindBacktype(feedback.getfType());
        Feedbackreply feedbackreply = feedbackMapper.FindFeedbackreply(feedback.getFeedbackId());
        feedback.setBacktype(backtype);
        feedback.setFeedbackreply(feedbackreply);
        map.put("data", feedback);
        return map;
    }


    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.FEEDBACKREPLY)
    @PostMapping(value = "/FeedbackReply")
    public Map<String, Object> FeedbackReply(Feedbackreply feedbackreply, @RequestParam Integer managerId, @RequestParam Integer frFeedbackid) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = feedbackService.FeedbakcReply(feedbackreply, managerId, frFeedbackid);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/YichuliSearch")
    public Map<String, Object> YichuliSearch(@RequestParam(required = false) Integer mType,   //管理员类型
                                             @RequestParam(required = false) Integer rKey,     //区id
                                             @RequestParam(required = false) Integer zhenId,     //镇id
                                             @RequestParam(required = false) Integer villageId,
                                             @RequestParam(required = false) String title,
                                             @RequestParam(required = false) Integer startPage,
                                             @RequestParam(required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (mType != null && startPage != null && pageSize != null) {
            map = feedbackService.YichuliSearch(rKey, zhenId, villageId, title, mType, startPage, pageSize);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @PostMapping(value = "/WeichuliSearch")
    public Map<String, Object> WeichuliSearch(@RequestParam(required = false) Integer mType,   //管理员类型
                                              @RequestParam(required = false) Integer rKey,     //区id
                                              @RequestParam(required = false) Integer zhenId,     //镇id
                                              @RequestParam(required = false) Integer villageId,
                                              @RequestParam(required = false) String title,
                                              @RequestParam(required = false) Integer startPage,
                                              @RequestParam(required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (mType != null && startPage != null && pageSize != null) {
            map = feedbackService.WeichuliSearch(rKey, zhenId, villageId, title, mType, startPage, pageSize);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @PostMapping(value = "/Xiangshangfankui")
    public Map<String, Object> Xiangshangfankui(Feedback feedback, @RequestParam Integer feedbackId) {
        Map<String, Object> map = new HashMap<>();
        feedback = feedbackMapper.findFeedback(feedbackId);
        feedback.setfJudge(true);
        boolean b = feedbackMapper.Upfankui(feedback);
        if (b == true) {
            map.put("statue", "success");
        } else {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/UpSearch")
    public Map<String, Object> UpSearch(@RequestParam(required = false) Integer mType,   //管理员类型
                                        @RequestParam(required = false) Integer rKey,     //区id
                                        @RequestParam(required = false) Integer zhenId,     //镇id
                                        @RequestParam(required = false) Integer villageId,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) Integer startPage,
                                        @RequestParam(required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (mType != null && startPage != null && pageSize != null) {
            map = feedbackService.Xiangshang(rKey, zhenId, villageId, title, mType, startPage, pageSize);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @PostMapping(value = "/zhenbintu")
    public Map<String, Object> zhenbintu(@RequestParam(required = false) Integer zhenId,
                                         @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = feedbackService.chulilv(zhenId, time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/Fankuileader")
    public Map<String, Object> Fankuileader(@RequestParam(required = false) Integer zhenId,
                                            @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        List<Zhen> list = zhenMapper.queryZhenByQuId_yyq(1349);
        List a = new ArrayList();
        try {
            for (Zhen z :
                    list) {
                a = new ArrayList();
                for (Zhen zhens :
                        list) {
                    map2 = new HashMap<>();
                    map2 = feedService.leaderUse(zhens.getZhenId(), time);
                    a.add(map2);
                }
            }
        } catch (Exception e) {
            map.put("statue", "error");
        }
        map.put("data", a);
        return map;
    }

    @PostMapping(value = "/qubingtu")
    public Map<String, Object> qubingtu(@RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = feedbackService.bingtu(time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/qulx")
    public Map<String, Object> qulx(@RequestParam(required = false) Integer zhenId, @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = feedbackService.qulx(zhenId, time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/ycllb")
    public Map<String, Object> ycllb(@RequestParam(required = false) Integer zhenId, @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = feedbackService.ycllb(zhenId, time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/wcllb")
    public Map<String, Object> wcllb(@RequestParam(required = false) Integer zhenId, @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = feedbackService.wcllb(zhenId, time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/upregion")
    public Map<String, Object> upregion(@RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = feedbackService.upregion(time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/typelist")
    public Map<String, Object> typelist() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Backtype> list = feedbackMapper.typelist();
            map.put("data", list);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/zhenlx")
    public Map<String, Object> zhenlx(@RequestParam(required = false) Integer zhenId, @RequestParam(required = false) String time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = feedbackService.zhenlx(zhenId, time);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

/*    @PostMapping(value = "/quzhexian")
    public Map<String,Object> quzhexian(@RequestParam(required = false) Integer zhenId){
        Map<String,Object> map=new HashMap<>();
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            map=feedbackService.quzhexian(zhenId,sdf.format(date));
        }catch (Exception e){
            map.put("statue","error");
        }
        return map;
    }*/

    @PostMapping(value = "/zhenzhexian")
    public Map<String, Object> zhenzhexian(@RequestParam(required = false) Integer zhenId) {
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            map = feedbackService.zhenzhexian(zhenId, sdf.format(date));
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

/*    @PostMapping(value = "/zhenzhexian2")
    public Map<String,Object> zhenzhexian2(@RequestParam Integer zhenId,@RequestParam(required = false) String time){
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> map2=new HashMap<>();
        Map<String,Object> map3=new HashMap<>();
        List<Village> list= villageMapper.queryVillageByZhenId_yyq(zhenId);
        List a=new ArrayList();
        try {
            for (Village v :
                    list) {
                a=new ArrayList();
                for (Village villages :
                        list) {
                    map2=new HashMap<>();
                    map2=feedService.zhenzhexian(villages.getVillageId(),time);
                    a.add(map2);
                }
            }
        }catch (Exception e){
            map.put("statue","error");
        }
        map.put("data",a);
        return map;
    }*/

    @PostMapping(value = "/quzhexian2")
    public Map<String, Object> quzhexian2() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        List a = new ArrayList();
        List b = new ArrayList();
        List<Zhen> list = zhenMapper.queryZhenByQuId_yyq(1349);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Integer t = Integer.parseInt(sdf.format(new Date()));
        try {
            b = new ArrayList();
            for (Zhen zhens :
                    list) {
                a = new ArrayList();
                map3 = new HashMap<>();
                for (int i = 2019; i <= t + 1; i++) {
                    map2 = new HashMap<>();
                    map2 = feedService.quzhexian(zhens.getZhenId(), String.valueOf(i));
                    map2.put("year", i);
                    a.add(map2);
                }
                map3.put("zhen", a);
                map3.put("name", zhens.getzName());
                b.add(map3);
            }
            map.put("data", b);
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }

    @PostMapping(value = "/zhenzhexian2")
    public Map<String, Object> zhenzhexian2(@RequestParam Integer zhenId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();
        List<Village> list = villageMapper.queryVillageByZhenId_yyq(zhenId);
        List a = new ArrayList();
        List b = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Integer t = Integer.parseInt(sdf.format(new Date()));
        try {
            if (list.size() != 0) {
                b = new ArrayList();
                for (Village villages :
                        list) {
                    a = new ArrayList();
                    map3 = new HashMap<>();
                    for (int i = 2019; i <= t + 1; i++) {
                        map2 = new HashMap<>();
                        map2 = feedService.zhenzhexian(villages.getVillageId(), String.valueOf(i));
                        map2.put("year", i);
                        a.add(map2);
                    }
                    map3.put("village", a);
                    map3.put("name", villages.getvName());
                    b.add(map3);
                }
                map.put("data", b);
            } else {
                a = new ArrayList();
                for (int i = 2019; i <= t + 1; i++) {
                    for (int j = 1; j <= 12; j++) {
                        map2 = new HashMap<>();
                        map2.put("time", j + "月");
                        map2.put("total", 0);
                        a.add(map2);
                        map3.put("year", i);
                    }
                    map3.put("name", null);
                    map3.put("zongfankui", a);
                    b.add(map3);
                    map4.put("village", b);
                }
                map.put("data", map4);
            }
        } catch (Exception e) {
            map.put("statue", "error");
        }
        return map;
    }
}
