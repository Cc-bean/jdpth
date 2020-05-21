package com.hj.jdpth.service;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.domain.Partymemberinformation;
import com.hj.jdpth.domain.PartymemberinformationAndYonghu;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface PartymemberinformationService {

    //党员添加和修改时要显示的分级页面的显示
    Map<String, Object> FENJI(Manager manager);

    Page<PartymemberinformationAndYonghu> queryAll_yyq(Integer qu_id, Integer zhen_id, Integer cun_id, String name);

    PartymemberinformationAndYonghu queryById_yyq(Integer id);

    String deleteById_yyq(Integer id);

    String deleteManyById_yyq(Integer[] id);

    String insert_yyq(Partymemberinformation p, String name, String sex/*, MultipartFile dangyuan_pic*/);

    String update_yyq(Partymemberinformation p, Integer id/*,MultipartFile dangyuan_pic*/);


}
