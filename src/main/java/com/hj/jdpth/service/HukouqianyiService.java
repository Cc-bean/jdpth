package com.hj.jdpth.service;

import com.hj.jdpth.domain.Hukouqianyi;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface HukouqianyiService {

    public Map<String, Object> QianYiSearch(Integer mType, Integer qid, Integer zid, Integer vid, Integer qyType, String time, Integer startPage, Integer pageSize);

    public Map<String, Object> QianYiXQ(Integer id);

    public Map<String, Object> DeleteOne(Integer id);

    public Map<String, Object> DeleteMany(String hkqyId);

    public Map<String, Object> InsertHkqy(Hukouqianyi hukouqianyi, MultipartFile[] files, String hkqyTime);

    public Map<String, Object> UpdateHkqy(Hukouqianyi hukouqianyi, MultipartFile[] files, String hkqyTime);

    public boolean Xvhao(String xvhao);

    public Map<String, Object> Add(Hukouqianyi hukouqianyi);

    public Map<String, Object> uploadFile(MultipartFile file);

    public Map<String, Object> InsertHkqy2(Hukouqianyi hukouqianyi);
}
