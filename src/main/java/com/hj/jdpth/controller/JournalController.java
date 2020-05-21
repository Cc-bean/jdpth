package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Journal;
import com.hj.jdpth.repository.JournalMapper;
import com.hj.jdpth.service.AssetsdetailsService;
import com.hj.jdpth.service.JobService;
import com.hj.jdpth.service.JournalService;
import com.hj.jdpth.service.impl.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * 日志
 */

@CrossOrigin
@Controller //处理管理员日志
public class JournalController {

    @Autowired
    JournalService journalService;



   /* @PostMapping("/h/journals")     //显示日志
    public ResponseEntity<Map<String, Object>> queryListJournal(@RequestParam("startPage") Integer startPage,
                                                                @RequestParam("pageSize") Integer pageSize,
                                                                @RequestParam(value = "jType", required = false) String jType) {
        Map<String, Object> map = new HashMap<>();
        try {
            PageHelper.startPage(startPage, pageSize);
            Page<HashMap> hashMaps = journalMapper.queryAllJournal(jType);
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
    }*/

    @PostMapping("/h/journals")  //多条件查询日志
    public ResponseEntity<Map<String, Object>> queryLJournals(@RequestParam("startPage") Integer startPage,
                                                              @RequestParam("pageSize") Integer pageSize,
                                                              @RequestParam(value = "jType", required = false) String jType,
                                                              @RequestParam(value = "zhenId", required = false) Integer zhenId,
                                                              @RequestParam(value = "cId", required = false) Integer cId,
                                                              @RequestParam(value = "stime", required = false, defaultValue = "2000-01-01") String startTime,
                                                              @RequestParam(value = "etime", required = false, defaultValue = "2099-12-30") String endTime) {
        return journalService.queryJournals(pageSize, startPage, jType, zhenId, cId, LocalDate.parse(startTime), LocalDate.parse(endTime));
    }

    @DeleteMapping("/h/journals") //删除日志
    public ResponseEntity<String> delete(@RequestParam String ids) {
        if (ids.contains(",")) {
            return journalService.delete(ids.split(","));
        } else {
            return journalService.delete(new String[]{ids});
        }
    }
}

