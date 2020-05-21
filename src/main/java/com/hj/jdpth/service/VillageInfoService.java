package com.hj.jdpth.service;

import com.hj.jdpth.domain.Village;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface VillageInfoService {

    public Map<String, Object> VillageInfoSearch(Integer mType, Integer qid, Integer zid, Integer vid, Integer startPage, Integer pageSize);

    public Map<String, Object> VillageXQ(Integer id);

    public Map<String, Object> InsertV(Village village, MultipartFile[] files);

    public Map<String, Object> UpdateV(Village village, MultipartFile[] files);

    public Map<String, Object> uploadFile(MultipartFile file);

    public Map<String, Object> InsertV2(Village village);

    public Map<String, Object> UpdateV2(Village village);
}
