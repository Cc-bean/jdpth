package com.hj.jdpth.service;

import com.hj.jdpth.domain.Capital;
import com.hj.jdpth.domain.Capitaldetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CaiwuCapitalService {
    Map<String, Object> findCapitaldetails(int regionId, int zhenId, int villageId, String cType, int style, Integer startPage, Integer pageSize);

    Map<String, Object> findzhenByRegionId(int regionId);

    Map<String, Object> findVillageByZhenId(int zhenId);

    Map<String, Object> findCapitaldetailsByZhenId(int zhenId, Integer startPage, Integer pageSize);

    Map<String, Object> findCapitaldetailsByVillageId(int villageId, Integer startPage, Integer pageSize);

    Map<String, Object> findCapitaldetailsByStyle(int regionId, int zhenId, int villageId, String cType, Integer startPage, Integer pageSize);

    Map<String, Object> deleteCapitaldetails(int capitaldetailsId);

    Map<String, Object> deleteManyCapitaldetails(String[] capitaldetailsId);

    Map<String, Object> uploadExcel(String path);

    Map<String, Object> yearUploadExcel(String path) throws Exception;

    Map<String, Object> insertCapital(Capital capital);

    Map<String, Object> insertCapitaldetails(Capitaldetails capitaldetails);

    Map<String, Object> updateCapitaldetails(Capitaldetails capitaldetails);

    Map<String, Object> uploadFile(MultipartFile file, String path);


    String getCellValue(Cell cell);
}
