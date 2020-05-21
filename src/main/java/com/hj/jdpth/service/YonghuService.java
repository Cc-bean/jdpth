package com.hj.jdpth.service;


import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Hu;
import com.hj.jdpth.domain.Yonghu;
import com.hj.jdpth.domain.YonghuCustom;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface YonghuService {
    //查看详情
    public YonghuCustom selectYonghuDetail(Integer id);

    //动态查询用户
    public Map seletYonghuDynamic(Map map);

    //删除用户
    public void deleteYongHu(List<Integer> ids);

    //更新用户
    public void updateYongHu(Yonghu yonghu);

    //添加用户
    public void insertYongHu(Yonghu yonghu);

    //政治面貌列表
    public List<HashMap> selectPolicyStatuNameAdId();

    //某村，组下拉列表
    public List<HashMap> selecctZuNameAdId(int cunId);

    //根据组ID查找户
    public List<Hu> selectHuNameAdId(int zuId);
}
