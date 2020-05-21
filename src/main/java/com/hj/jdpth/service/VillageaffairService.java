package com.hj.jdpth.service;

import com.hj.jdpth.domain.Villageaffair;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface VillageaffairService {
    public Map<String, Object> findAffair(Integer style, Integer qu_id, Integer zhen_id, Integer cun_id, String affairName, Integer startPage, Integer pageSize);

    public Map<String, Object> findAffairXingQing_yp(Integer id);

    public Map<String, Object> deleteAffairById_yp(Integer id);

    public Map<String, Object> deleteManyAffairById_yp(String Ids);

    public Map<String, Object> insertAffair_yp(Villageaffair villageaffair);

    public Map<String, Object> updateAffair_yp(Villageaffair villageaffair);

    public Boolean findAffairByxuhao_yp(String vaXuhao);

    //2019-11-27
    public Map<String, Object> uploadFile(MultipartFile file);
}
