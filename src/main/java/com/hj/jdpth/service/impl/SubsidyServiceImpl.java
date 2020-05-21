package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.SubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("subsidyServiceImpl")
public class SubsidyServiceImpl implements SubsidyService {
    @Autowired
    SubsidyinformationMapper subsidyinformationMapper;
    @Autowired
    SubsidyobjectMapper subsidyobjectMapper;
    @Autowired
    SubsidytypeMapper subsidytypeMapper;
    @Autowired
    SubsidynameMapper subsidynameMapper;
    @Autowired
    PolicyMapper policyMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    SubsidizedhouseholdsMapper subsidizedhouseholdsMapper;

    //根据id查看详情
    @Override
    public HashMap selectDetailByID(int id) {

        return subsidyinformationMapper.selectInforMationBYId(id);
    }

    //根据ID 查看所有补助对象
    @Override
    public Map selectOBjectsByID(Map params) {
        Map<String, Object> result = new HashMap(16);
        PageHelper.startPage((int) params.get("startPage"), (int) params.get("pageSize"));
        Page<Subsidyobject> res = subsidyobjectMapper.selectObjectbyIds((int) params.get("id"));
        //总页数
        result.put("total", res.getPages());
        //记录总数
        result.put("count", res.getTotal());
        //当前页数
        result.put("nowPage", res.getPageNum());
        result.put("objects", res);
        return result;
    }

    //根据ID 查看所有补助户
    @Override
    public Map selectHouserholdByID(Map params) {
        Map<String, Object> result = new HashMap(16);
        PageHelper.startPage((int) params.get("startPage"), (int) params.get("pageSize"));
        Page<Subsidizedhouseholds> res = subsidizedhouseholdsMapper.selecthouseholdsbyIds((int) params.get("id"));
        //总页数
        result.put("total", res.getPages());
        //记录总数
        result.put("count", res.getTotal());
        //当前页数
        result.put("nowPage", res.getPageNum());
        result.put("objects", res);
        return result;
    }

    //动态查找
    @Override
    public Map<String, Object> selectSubsidyInformationDynamic(Map params) {//待验证

        List list = new ArrayList();
        Map<String, Object> result = new HashMap<>();
        if (params.get("cunId") != null && !params.get("cunId").equals("")) {
            list.add(params.get("cunId"));
        } else {
            list = villageMapper.selectIDDynamic(params);
            if (list.size() == 0)
                list.add(null);
        }

        params.put("list", list);

        if (list.size() != 0) {
            PageHelper.startPage((int) params.get("startPage"), (int) params.get("pageSize"));
            Page<HashMap> page = subsidyinformationMapper.selectSubsidyInforMationDynamic(params);

            result.put("subsidyinformation", page);
            //总页数
            result.put("total", page.getPages());
            //记录总数
            result.put("count", page.getTotal());
            //当前页数
            result.put("nowPage", page.getPageNum());
        }
        return result;
    }

    //添加补助项类型
    @Override
    public void insertSubsidyType(Subsidytype subsidytype) {
        subsidytypeMapper.insertTypeDynamic(subsidytype);
    }

    //插入补助项
    @Override
    public void insertSubsidyName(SubsidynameCustom subsidynameCustom) {
        subsidynameMapper.insertSubsidynameDynamic(subsidynameCustom);
        //woca
        subsidynameCustom.setSiNameofsubsidy(subsidynameCustom.getsKey());
        subsidyinformationMapper.intserSubsidyInforMationDynamic(subsidynameCustom);
    }

    //补助项检测重复
    @Override
    public String distinctName(String name, Integer type) {

        return subsidynameMapper.distinctname(name, type);
    }

    //补助项修改
    @Override
    public void updateSubsidyName(String name, Subsidyinformation subsidyinformation) {
        subsidyinformationMapper.updateInformatioName(name, subsidyinformation.getSiNameofsubsidy());
        subsidyinformationMapper.updateInformationDynamic(subsidyinformation);
    }

    //根据村选择补助类型
    @Override
    public List<HashMap> selectPolicyTypeBIId(int id) {
        return subsidytypeMapper.selectSubsidytypesx(id);
    }

    //根据村选择补助项名称
    @Override
    public List<HashMap> selectPolicyNameBYId(int type) {
        return subsidynameMapper.selectPolicyNameBYId(type);
    }

    //国家政策
    @Override
    public List<HashMap> selectPolicyNationNameAdID() {
        return policyMapper.selectPolicyAdNameNation();
    }

    //国家政策BY村Id
    @Override
    public List<HashMap> selectPolicyNationNameAdID(Integer id) {
        return policyMapper.selectPolicyAdNameNationBYID(id);
    }

    //实施政策
    @Override
    public List<HashMap> selectPolicyShisHiNameAdID() {
        return policyMapper.selectPoclicyAdNameShisHi();
    }

    //实施政策BY村Id
    @Override
    public List<HashMap> selectPolicyShisHiNameAdID(int id) {
        return policyMapper.selectPoclicyAdNameShisHiBYID(id);
    }


    //删除
    @Override
    public void deleteInformation(List<Integer> ids) {
        for (int i = 0; i < ids.size(); i++) {
            subsidyinformationMapper.deleteInforMationBYId(ids.get(i));
        }
    }
}
