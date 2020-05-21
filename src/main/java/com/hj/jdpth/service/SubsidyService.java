package com.hj.jdpth.service;

import com.hj.jdpth.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SubsidyService {
    //根据id查看详情
    public HashMap selectDetailByID(int id);

    //根据ID 查看所有补助对象
    public Map selectOBjectsByID(Map params);

    //根据ID 查看所有补助对象
    public Map selectHouserholdByID(Map params);

    //动态查找
    public Map<String, Object> selectSubsidyInformationDynamic(Map params);

    //添加补助项类型
    public void insertSubsidyType(Subsidytype subsidytype);

    //插入补助项
    public void insertSubsidyName(SubsidynameCustom subsidynameCustom);

    //补助项检测重复
    public String distinctName(String name, Integer tpye);

    //补助项修改
    public void updateSubsidyName(String name, Subsidyinformation subsidyinformation);

    //根据村选择补助类型
    public List<HashMap> selectPolicyTypeBIId(int id);

    //根据村选择补助项名称
    public List<HashMap> selectPolicyNameBYId(int id);

    //国家政策
    public List<HashMap> selectPolicyNationNameAdID();

    //国家政策BY村Id
    public List<HashMap> selectPolicyNationNameAdID(Integer id);

    //实施政策
    public List<HashMap> selectPolicyShisHiNameAdID();

    //实施政策BY村Id
    public List<HashMap> selectPolicyShisHiNameAdID(int id);

    //删除
    public void deleteInformation(List<Integer> ids);
}
