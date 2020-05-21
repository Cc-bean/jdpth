package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Hu;
import com.hj.jdpth.domain.Villagecadres_Custom;
import com.hj.jdpth.domain.Yonghu;
import com.hj.jdpth.domain.YonghuCustom;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.YonghuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YonghuServiceImpl implements YonghuService {
    //动态查找用户
    @Autowired
    YonghuMapper yonghuMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    PolicyStatuMapper policyStatuMapper;
    @Autowired
    ZuMapper zuMapper;
    @Autowired
    HuMapper huMapper;

    //查看详情
    @Override
    public YonghuCustom selectYonghuDetail(Integer id) {
        return yonghuMapper.selectYonhuByID(id);
    }

    @Override
    public Map seletYonghuDynamic(Map map) {
        List list = new ArrayList();
        if (map.get("cunId") != null && !map.get("cunId").equals("")) {
            list.add(map.get("cunId"));
        } else {
            list = villageMapper.selectIDDynamic(map);
            if (list.size() == 0)
                list.add(null);
        }
        map.put("list", list);

        PageHelper.startPage((int) map.get("startPage"), (int) map.get("pageSize"));
        Page<YonghuCustom> page = yonghuMapper.selectYongDynamic(map);

        Map<String, Object> result = new HashMap<>();
        result.put("yonghu", page);
        //总页数
        result.put("total", page.getPages());
        //记录总数
        result.put("count", page.getTotal());
        //当前页数
        result.put("nowPage", page.getPageNum());
        return result;
    }

    //删除用户
    @Override
    public void deleteYongHu(List<Integer> ids) {
        int size = ids.size();
        for (int i = 0; i < size; i++) {
            yonghuMapper.deleteYonhuBYId(ids.get(i));
        }
    }

    //更新用户
    @Override
    public void updateYongHu(Yonghu yonghu) {
        yonghuMapper.updateYongHuBYId(yonghu);
    }

    //添加用户
    @Override
    public void insertYongHu(Yonghu yonghu) {
        yonghuMapper.insertYongHu(yonghu);
    }

    @Override
    public List<HashMap> selectPolicyStatuNameAdId() {
        return policyStatuMapper.selectNameAdID();
    }

    //某村，组下拉列表
    @Override
    public List<HashMap> selecctZuNameAdId(int cunId) {
        return zuMapper.selectNameAdId(cunId);
    }

    //根据组ID查找户
    @Override
    public List<Hu> selectHuNameAdId(int zuId) {
        return huMapper.selectNameAdID(zuId);
    }
}
