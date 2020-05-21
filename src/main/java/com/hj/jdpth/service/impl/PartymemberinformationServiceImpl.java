package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.PartymemberinformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartymemberinformationServiceImpl implements PartymemberinformationService {

    @Autowired
    PartymemberinformationMapper partymemberinformationMapper;
    @Autowired
    NationMapper nationMapper;
    @Autowired
    YonghuMapper yonghuMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    JobMapper jobMapper;
    @Autowired
    RegionMapper regionMapper;

    String path0 = "E:\\HJResourse\\Partymemberinformation\\";

    @Override
    public Map<String, Object> FENJI(Manager manager) {
        Map<String, Object> map = new HashMap<>();
        List<Job> jobList = jobMapper.queryAll_yyq();
        List<Nation> nationList = nationMapper.queryAll_yyq();
        map.put("nationList", nationList);
        map.put("jobList", jobList);
        if (manager.getmType() == 2) {
            Region region = regionMapper.queryById_yyq(manager.getmRegion());
            map.put("quName", region.getrName());
            List<Zhen> zhenList = zhenMapper.queryZhenByQuId_yyq(manager.getmRegion());
            map.put("zhenName", zhenList);
        }
        if (manager.getmType() == 4) {
            Region region = regionMapper.queryById_yyq(manager.getmRegion());
            map.put("quName", region.getrName());
            Zhen zhen = zhenMapper.queryByZhenId_yyq(manager.getmZhenid());
            map.put("zhenName", zhen);
            List<Village> cunList = villageMapper.queryVillageByZhenId_yyq(manager.getmZhenid());
            map.put("cunName", cunList);
        }
        if (manager.getmType() == 5) {
            Region region = regionMapper.queryById_yyq(manager.getmRegion());
            map.put("quName", region.getrName());
            Zhen zhen = zhenMapper.queryByZhenId_yyq(manager.getmZhenid());
            map.put("zhenName", zhen);
            Village cunName = villageMapper.queryByCunId_yyq(manager.getmVillageid());
            map.put("cunName", cunName);
        }
        return map;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////查询
    @Override
    public Page<PartymemberinformationAndYonghu> queryAll_yyq(Integer qu_id, Integer zhen_id, Integer cun_id, String name) {
        if (name.equals("") || name == null) {
            name = "";
        }
        Page<PartymemberinformationAndYonghu> Partymemberinformation = partymemberinformationMapper.queryAll_yyq(qu_id, zhen_id, cun_id, name);
        String nation = null;
        for (int i = 0; i < Partymemberinformation.size(); i++) {
            //System.out.println(Partymemberinformation.get(i).getYonghu().getUserNation());
            if (Partymemberinformation.get(i).getYonghu().getUserNation() != null) {
                nation = nationMapper.queryById_yyq(Partymemberinformation.get(i).getYonghu().getUserNation());
            }
            if (nation.equals("") || nation == null) {
                Partymemberinformation.get(i).setNation("无数据");
            } else {
                Partymemberinformation.get(i).setNation(nation);
            }
        }
        return Partymemberinformation;
    }

    @Override
    public PartymemberinformationAndYonghu queryById_yyq(Integer id) {
        PartymemberinformationAndYonghu partymemberinformationAndYonghu = partymemberinformationMapper.queryById_yyq(id);
        String nation = null;
        if (partymemberinformationAndYonghu.getYonghu().getUserNation() != null) {
            nation = nationMapper.queryById_yyq(partymemberinformationAndYonghu.getYonghu().getUserNation());
        }
        nationMapper.queryById_yyq(partymemberinformationAndYonghu.getYonghu().getUserNation());
        if (!nation.equals("") || nation != null) {
            partymemberinformationAndYonghu.setNation(nation);
        } else {
            partymemberinformationAndYonghu.setNation("无数据");
        }
        return partymemberinformationAndYonghu;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////删除
    @Override
    public String deleteById_yyq(Integer id) {
        String Oldpic = path0 + partymemberinformationMapper.queryById_yyq(id).getPmiPhoto();
        File oidFile = new File(Oldpic);
        if (oidFile.exists()) oidFile.delete();
        int a = partymemberinformationMapper.deleteById_yyq(id);
        if (a > 0)
            return "删除成功";
        else return "删除失败";
    }

    @Override
    public String deleteManyById_yyq(Integer[] id) {
        int k = 0;
        for (int i = 0; i < id.length; i++) {
            String Oldpic = path0 + partymemberinformationMapper.queryById_yyq(id[i]).getPmiPhoto();
            File oidFile = new File(Oldpic);
            if (oidFile.exists()) oidFile.delete();
            int a = partymemberinformationMapper.deleteById_yyq(id[i]);
            if (a > 0) k++;
        }
        if (k < id.length && k >= 0) return "删除失败";
        else return "删除成功";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////添加
    @Override
    public String insert_yyq(Partymemberinformation p, String name, String sex/*, MultipartFile dangyuan_pic*/) {
        /*if(!dangyuan_pic.getOriginalFilename().equals("")) {
            long s =System.currentTimeMillis();
            String path=path0;
            String realName= dangyuan_pic.getOriginalFilename();
            String newName=s+realName;
            //新图片
            File newFile=new File(path+newName);
            //将内存中的数据写入磁盘
            try {
                dangyuan_pic.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.setPmiPhoto(newName);
        }*/
        List<Yonghu> yonghus = yonghuMapper.queryAll_yyq();
        int k = 0;
        int j = 0;
        int a = 0;
        for (int i = 0; i < yonghus.size(); i++) {
            /*if (yonghus.get(i).getUserName().equals(name)&&yonghus.get(i).getUserSex().equals(sex)){
                j=i;k=k+1;break;
            }*/
            if (yonghus.get(i).getUserEntityid() != null && yonghus.get(i).getUserEntityid().equals(p.getPmBeiyong1())) {
                /*j = i;
                k = k + 1;*/
                p.setPmYonghu(yonghus.get(i).getUserId());
                a = partymemberinformationMapper.insert_yyq(p);
                break;
            }
        }
        /*if(k>0) {
            p.setPmYonghu(yonghus.get(j).getUserId());
            int a = partymemberinformationMapper.insert_yyq(p);*/
        if (a > 0) {
            return "添加成功";
        } else return "无该用户信息，请先添加该用户信息";
        /*}else {
            return "无该用户信息，请先添加该用户信息";
        }*/

        /*if(k==0){
            return "无该用户信息，请先添加该用户信息";
        }
        else {
            if(yonghus.get(j).getUserEntityid().equals(p.getPmBeiyong1())){
                p.setPmYonghu(yonghus.get(j).getUserId());
                int a= partymemberinformationMapper.insert_yyq(p);
                if(a>0){
                    return "添加成功";
                }else return "添加失败";
            }else {
                return "名字与已有用户重名，但无该用户信息，请先添加该用户信息";
            }

        }*/


        /*if(p.getPmiPhoto()==""||p.getPmiPhoto()==null){
            p.setPmiPhoto("");
        }
        int a= partymemberinformationMapper.insert_yyq(p);
        if(a>0){
            return "添加成功";
        }else return "添加失败";*/
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////更改
    @Override
    public String update_yyq(Partymemberinformation p, Integer id/*,MultipartFile dangyuan_pic*/) {
        /*if(!dangyuan_pic.getOriginalFilename().equals("")){
            long s =System.currentTimeMillis();
            String path=path0;
            String oldPic=path+partymemberinformationMapper.queryById_yyq(id).getPmiPhoto();
            File oldFile=new File(oldPic);
            if(oldFile.exists())oldFile.delete();
            String realName= dangyuan_pic.getOriginalFilename();
            String newName=s+realName;
            File newFile=new File(path+newName);
            try {
                dangyuan_pic.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.setPmiPhoto(newName);
        }else{
            p.setPmiPhoto(partymemberinformationMapper.queryById_yyq(id).getPmiPhoto());
        }*/
        p.setPmYonghu(partymemberinformationMapper.queryById_yyq(id).getYonghu().getUserId());
        p.setPartymemberinformationId(id);
        int a = partymemberinformationMapper.update_yyq(p);
        if (a > 0) return "更新成功";
        else return "更新失败";
    }
}
