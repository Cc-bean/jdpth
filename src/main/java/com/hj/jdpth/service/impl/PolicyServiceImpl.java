package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.JijifenziMapper;
import com.hj.jdpth.repository.PolicyMapper;
import com.hj.jdpth.repository.PolicyTypeMapper;
import com.hj.jdpth.service.PolicyService;
import com.hj.jdpth.utils.wordtopdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    PolicyMapper policyMapper;

    @Autowired
    JijifenziMapper jijifenziMapper;
    @Autowired
    PolicyTypeMapper policyTypeMapper;

    private String LeixinName;

    @Override
    public Map<String, Object> GetInfoFirst(Integer shengId, Integer shiId, Integer quId, Integer zId, Integer vId, Integer managerId, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        if (shengId != null) {
            try {
                Page<Policy> policy = policyMapper.findPolicyByShengId(shengId);
                for (int i = 0; i < policy.size(); i++) {
                    LeixinName = policyMapper.selectLeixinName(policy.get(i).getpType());
                    policy.get(i).setLeixinName(LeixinName);
                }
                if (policy == null) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    //总页数
                    result.put("total", policy.getPages());
                    //记录总数
                    result.put("count", policy.getTotal());
                    //数据
                    result.put("data", policy);
                    //当前第几页
                    result.put("nowPage", policy.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (shiId != null) {
            try {
                Page<Policy> policy = policyMapper.findPolicyByShiId(shiId);
                for (int i = 0; i < policy.size(); i++) {
                    LeixinName = policyMapper.selectLeixinName(policy.get(i).getpType());
                    policy.get(i).setLeixinName(LeixinName);
                }
                if (policy == null) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    //总页数
                    result.put("total", policy.getPages());
                    //记录总数
                    result.put("count", policy.getTotal());
                    //数据
                    result.put("data", policy);
                    //当前第几页
                    result.put("nowPage", policy.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else {
            try {
                Page<Policy> policy = policyMapper.queryA();
                for (int i = 0; i < policy.size(); i++) {
                    LeixinName = policyMapper.selectLeixinName(policy.get(i).getpType());
                    policy.get(i).setLeixinName(LeixinName);
                }
                if (policy == null) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    //总页数
                    result.put("total", policy.getPages());
                    //记录总数
                    result.put("count", policy.getTotal());
                    //数据
                    result.put("data", policy);
                    //当前第几页
                    result.put("nowPage", policy.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        }
//        else if (managerId==2||managerId==3){
//            System.out.println("进入区级");
//            try {
//                PageHelper.startPage(startPage, pageSize);
//                Page<Policy> policy;
//                if (vId!=0) {
//                    policy = policyMapper.findPolicyByCunId(vId);
//                }else if (zId!=0){
//                    policy = policyMapper.findPolicyByZhenId(zId);
//                }else {
//                    policy = policyMapper.findPolicyByQuId(quId);
//                }
//                for (int i=0;i<policy.size();i++){
//                    LeixinName=policyMapper.selectLeixinName(policy.get(i).getpType());
//                    policy.get(i).setLeixinName(LeixinName);
//                }
//                if (policy.isEmpty()) {
//                    result.put("status", "null");
//                    result.put("data", "没有数据");
//                } else {
//                    result.put("status", "success");
//                    result.put("data", policy);
//                    //总页数
//                    result.put("total", policy.getPages());
//                    //记录总数
//                    result.put("count", policy.getTotal());
//                    //当前第几页
//                    result.put("nowPage", policy.getPageNum());
//                }
//            }catch (Exception e){
//                result.put("status", "error");
//                result.put("data", "查询出错");
//            }
//        }else if (managerId==4){
//            System.out.println("进入镇级");
//            try {
//                PageHelper.startPage(startPage, pageSize);
//                Page<Policy> policy;
//                if (vId!=0){
//                    policy = policyMapper.findPolicyByCunId(vId);
//                }
//                else {
//                    policy = policyMapper.findPolicyByZhenId(zId);
//                }
//                for (int i=0;i<policy.size();i++){
//                    LeixinName=policyMapper.selectLeixinName(policy.get(i).getpType());
//                    policy.get(i).setLeixinName(LeixinName);
//                }
//                if (policy.isEmpty()) {
//                    result.put("status", "null");
//                    result.put("data", "没有数据");
//                } else {
//                    result.put("status", "success");
//                    result.put("data", policy);
//                    //总页数
//                    result.put("total", policy.getPages());
//                    //记录总数
//                    result.put("count", policy.getTotal());
//                    //当前第几页
//                    result.put("nowPage", policy.getPageNum());
//                }
//            }catch (Exception e){
//                result.put("status", "error");
//                result.put("data", "查询出错");
//            }
//        }else if (managerId==5){
//            System.out.println("进入村级");
//            try {
//                PageHelper.startPage(startPage, pageSize);
//                Page<Policy> policy=policyMapper.findPolicyByCunId(vId);
//                for (int i=0;i<policy.size();i++){
//                    LeixinName=policyMapper.selectLeixinName(policy.get(i).getpType());
//                    policy.get(i).setLeixinName(LeixinName);
//                }
//                if (policy.isEmpty()) {
//                    result.put("status", "null");
//                    result.put("data", "没有数据");
//                } else {
//                    result.put("status", "success");
//                    result.put("data", policy);
//                    //总页数
//                    result.put("total", policy.getPages());
//                    //记录总数
//                    result.put("count", policy.getTotal());
//                    //当前第几页
//                    result.put("nowPage", policy.getPageNum());
//                }
//            }catch (Exception e){
//                result.put("status", "error");
//                result.put("data", "查询出错");
//            }
//        }
        return result;
    }

    //    @Override
//    public Map<String, Object> findPolicyByQuId( Integer quId, Integer startPage, Integer pageSize) {
//        Map<String, Object> map=new HashMap<>();
//        try {
//            Page<Policy>  policy = policyMapper.findPolicyByQuId(quId);
//            map.put("data", policy);
//            PageHelper.startPage(startPage, pageSize);
//            Page<Policy> PolicyPage = policyMapper.findPolicyByQuId(quId);
//            Map<String, Object> result = new HashMap<>();
//            result.put("record", PolicyPage);
//            //总页数
//            result.put("total", PolicyPage.getPages());
//            //记录总数
//            result.put("count", PolicyPage.getTotal());
//            //当前第几页
//            result.put("nowPage", PolicyPage.getPageNum());
//            return result;
//        }catch (Exception e){
//            map.put("data","发生异常");
//        }
//        return map;
//    }
//
//    @Override
//    public Map<String, Object> findPolicyByZhenId( Integer zhenId, Integer quId, Integer startPage, Integer pageSize) {
//        Map<String, Object> map=new HashMap<>();
//        try {
//            Page<Policy>  policy = policyMapper.findPolicyByZhenId(quId,zhenId);
//            map.put("data", policy);
//            PageHelper.startPage(startPage, pageSize);
//            Page<Policy> PolicyPage = policyMapper.findPolicyByZhenId(quId,zhenId);
//            Map<String, Object> result = new HashMap<>();
//            result.put("record", PolicyPage);
//            //总页数
//            result.put("total", PolicyPage.getPages());
//            //记录总数
//            result.put("count", PolicyPage.getTotal());
//            //当前第几页
//            result.put("nowPage", PolicyPage.getPageNum());
//            return result;
//        }catch (Exception e){
//            map.put("data","发生异常");
//        }
//        return map;
//    }
//
//    @Override
//    public Map<String, Object> findPolicyByCunId( Integer zhenId, Integer cunId, Integer quId, Integer startPage, Integer pageSize) {
//        Map<String, Object> map=new HashMap<>();
//        try {
//            Page<Policy>  policy = policyMapper.findPolicyByCunId(quId,zhenId,cunId);
//            map.put("data", policy);
//            PageHelper.startPage(startPage, pageSize);
//            Page<Policy> PolicyPage = policyMapper.findPolicyByCunId(quId,zhenId,cunId);
//            Map<String, Object> result = new HashMap<>();
//            result.put("record", PolicyPage);
//            //总页数
//            result.put("total", PolicyPage.getPages());
//            //记录总数
//            result.put("count", PolicyPage.getTotal());
//            //当前第几页
//            result.put("nowPage", PolicyPage.getPageNum());
//            return result;
//        }catch (Exception e){
//            map.put("data","发生异常");
//        }
//        return map;
//    }
    @Override
    public Map<String, Object> deletePolicy(Integer policyId, Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        Policy policy = policyMapper.findPolicy(policyId);
        if (policy != null) {
            int b = policyMapper.deletePolicy(policyId);
            if (b == 1) {
                map.put("data", "success");
            } else {
                map.put("data", "error");
            }
        } else {
            map.put("data", "无此政策");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteManyPolicy(Integer[] policyId, Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        int result = 0;
        for (int a = 0; a < policyId.length; a++) {
            result = policyMapper.deletePolicy(policyId[a]);
        }
        map.put("result", result);
        return map;
    }


    @Override
    public Map<String, Object> findPolicy(Integer policyId) {
        Map<String, Object> map = new HashMap<>();
        Policy policy = policyMapper.findPolicy(policyId);
        if (policy != null) {
            map.put("data", policy);
        } else {
            map.put("data", null);
        }
        return map;

    }

    @Override
    public Map<String, Object> zhen_List(Integer quId) {
        Map<String, Object> map = new HashMap<>();
        List<Zhen> zhens = policyMapper.zhen_List(quId);
        map.put("data", zhens);
        return map;
    }


    @Override
    public Map<String, Object> cun_list(Integer zhenId) {
        Map<String, Object> map = new HashMap<>();
        List<Village> zhens = policyMapper.cun_list(zhenId);
        map.put("data", zhens);
        return map;
    }

    //String path0="C:\\HJResourse\\Policy\\";
    String path0 = "E:\\HJResourse\\Policy\\";

    @Override
    public String fileUpload(Policytype policytype, Policy policy, MultipartFile policy_wenjian) {
        long s = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = sdf.format(new Date());
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int j = policyTypeMapper.insertPolicytype(policytype);
        policy.setpType(policytype.getPolicytypeId());
        policy.setpDatetime(date);
        if (!policy_wenjian.getOriginalFilename().equals("")) {
            String path = path0;
            File savedir = new File(path0);
            if (!savedir.exists()) savedir.mkdirs();
            String realName = policy_wenjian.getOriginalFilename();
            String newName = realName;
            File newFile = new File(path + newName);
            //将内存中的数据写入磁盘
            try {
                policy_wenjian.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //WordToHtml wordToHtml=new WordToHtml();
            wordtopdf wtp = new wordtopdf();
            String pdfName = realName.substring(0, realName.lastIndexOf(".")) + ".pdf";
            wtp.doc2pdf(path + realName, path + pdfName);
            /*if((realName.substring(realName.lastIndexOf(".") + 1)).equals("docx")){
                try {
                    wordToHtml.Word2007ToHtml(path,newName,htmlName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
            /*if((realName.substring(realName.lastIndexOf(".") + 1)).equals("doc")){
                try {
                    wordToHtml.Word2003ToHtml(path,path,newName,htmlName);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }*/
            policy.setpName(pdfName);
            File file = new File(path + newName);
            file.delete();
            /*String path=path0;
            File savedir=new File(path0);
            if(!savedir.exists()) savedir.mkdirs();
            String realName= policy_wenjian.getOriginalFilename();
            String newName=realName;
            File newFile=new File(path+newName);
            //将内存中的数据写入磁盘
            try {
                policy_wenjian.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            wordtopdf wtp=new wordtopdf();
            String pdfName=realName.substring(0,realName.lastIndexOf("."))+".pdf";
            wtp.doc2pdf(path+realName,path+pdfName);
            policy.setpName(pdfName);
            File file=new File(path+newName);
            file.delete();*/
        }
        int i = 0;
        if (j > 0) {
            i = policyMapper.insertPolicy(policy);
        }
        if (i > 0 && j > 0) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }


    @Override
    public Map<String, Object> shi_List(Integer sId) {
        Map<String, Object> map = new HashMap<>();
        List<Shi> shis = policyMapper.shi_List(sId);
        map.put("data", shis);
        return map;
    }

    @Override
    public Map<String, Object> qu_List(Integer shiId) {
        Map<String, Object> map = new HashMap<>();
        List<Region> qus = policyMapper.qu_List(shiId);
        map.put("data", qus);
        return map;
    }


}
