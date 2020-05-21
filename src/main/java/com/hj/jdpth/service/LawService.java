package com.hj.jdpth.service;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Law;
import com.hj.jdpth.domain.Lawtype;
import com.hj.jdpth.domain.Manager;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LawService {

    List<Lawtype> queryAllType();

    Page<Law> queryLaw(Integer L_name, String name);


    String insertLaw(Manager manager, Integer lawtype, Law law, MultipartFile law_wenjian);

    String deleteLawAndLawType(Integer law_id);

    String deleteLawAndLawTypeMany(Integer[] law_ids);

    String updateLaw(Integer managerId, Lawtype lawtype, Law law, MultipartFile law_wenjian, Integer law_id);

    List<Law> queryLeaderLaw(Integer L_name, Integer zhen_id);

}
