package com.hj.jdpth.service;

import com.hj.jdpth.domain.Capital;
import com.hj.jdpth.domain.Licaiqingkuang;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CaiwulicaiService {
    Map<String, Object> findlicai(int regionId, int zhenId, int villageId, int zuId, int style, Integer startPage, Integer pageSize);

    Map<String, Object> findZu(int villageId);

    Map<String, Object> deleteLicai(int licaiId);

    Map<String, Object> deleteManyLicai(String[] licaiId);

    Map<String, Object> insertLicai(Licaiqingkuang licaiqingkuang);

    Map<String, Object> uploadFile(MultipartFile file);

    Map<String, Object> updataLicai(Licaiqingkuang licaiqingkuang);

    //导入资金的方法
    Map<String, Object> importcapital(int rowNum, Sheet sheet, Map<String, Object> map, Capital capital, Capital capital0, int zuId, Integer villageId, String villageName,
                                      String SheetOne, String style, String year1, String month1);
}
