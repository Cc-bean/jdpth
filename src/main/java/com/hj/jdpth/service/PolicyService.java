package com.hj.jdpth.service;

import com.hj.jdpth.domain.Policy;
import com.hj.jdpth.domain.Policytype;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PolicyService {

    public Map<String, Object> GetInfoFirst(Integer shengId, Integer shiId, Integer quId, Integer zId, Integer vId, Integer managerId, Integer startPage, Integer pageSize);

    //    public Map<String,Object> findPolicyByQuId(Integer quId,Integer startPage,Integer pageSize);
//    public Map<String,Object> findPolicyByZhenId(Integer zhenId,Integer quId,Integer startPage,Integer pageSize);
//    public Map<String,Object> findPolicyByCunId(Integer zhenId,Integer cunId,Integer quId,Integer startPage,Integer pageSize);
    public Map<String, Object> deletePolicy(Integer policyId, Integer managerId);

    public Map<String, Object> deleteManyPolicy(Integer[] policyId, Integer managerId);

    public Map<String, Object> findPolicy(Integer policyId);

    public Map<String, Object> zhen_List(Integer quId);

    public Map<String, Object> cun_list(Integer zhenId);

    String fileUpload(Policytype policytype, Policy policy, MultipartFile policy_wenjian);

    public Map<String, Object> shi_List(Integer sId);

    public Map<String, Object> qu_List(Integer shiId);

    //public Map<String,Object> fileUpload(MultipartFile ExcelFile,Integer pType,String pName ,HttpServletRequest request);
    //Policy insertPolicy(Policy policy);


}
