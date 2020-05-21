package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.repository.JournalMapper;
import com.hj.jdpth.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    JournalMapper journalMapper;

    @Override
    public ResponseEntity<Map<String, Object>> queryJournals(Integer pageSize, Integer startPage, String jType,
                                                             Integer zhenId, Integer cId, LocalDate startTime, LocalDate endTime) {
        PageHelper.startPage(startPage, pageSize);
        Page<HashMap> hashMaps = journalMapper.queryJournalss(jType, zhenId, cId, startTime, endTime);
        Map<String, Object> map = new HashMap<>();
        try {
            if (hashMaps.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                map.put("data", hashMaps);
                map.put("count", hashMaps.getTotal());
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        } catch (Exception e) {
            map.put("data", "error");
            return new ResponseEntity<>(map, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @Override
    public ResponseEntity<String> delete(String[] ids) {
        try {
            for (int i = 0; i < ids.length; i++) {
                journalMapper.delete(ids[i]);
            }
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
