package com.hj.jdpth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public interface JournalService {

    //多条件查询日志
    public ResponseEntity<Map<String, Object>> queryJournals(Integer pageSize, Integer startPage, String jType,
                                                             Integer zhenId, Integer cId, LocalDate startTime, LocalDate endTime);

    //删除日志
    public ResponseEntity<String> delete(String[] ids);
}
