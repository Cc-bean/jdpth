package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.Job;
import com.hj.jdpth.domain.Nation;
import com.hj.jdpth.repository.JobMapper;
import com.hj.jdpth.repository.NationMapper;
import com.hj.jdpth.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class JobController {

    @Autowired
    JobService jobService;
    @Autowired
    JobMapper jobMapper;
    @Autowired
    NationMapper nationMapper;

    @PostMapping(value = "/Village/JobSearch")
    public Map<String, Object> JobSearch(@RequestParam(required = false) Integer mType,   //管理员类型
                                         @RequestParam(required = false) Integer rKey,
                                         @RequestParam(required = false) Integer startPage,
                                         @RequestParam(required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (mType != null && startPage != null && pageSize != null) {
            map = jobService.SearchJob(rKey, startPage, pageSize);
        } else {
            map.put("data", "数据不完整!");
            map.put("state", "false");
        }
        return map;
    }

    @PostMapping(value = "/JobAdd")
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.JOB)
    public Map<String, Object> JobAdd(Job job,
                                      @RequestParam Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        job.setjVillage(2);
        boolean b = jobMapper.Insert(job);
        if (b == true) {
            map.put("state", "success");
        } else {
            map.put("state", "error");
        }
        return map;
    }

    @GetMapping(value = "/v/NationList")
    public Map<String, Object> NationList() {
        Map<String, Object> map = new HashMap<>();
        List<Nation> nationList = nationMapper.queryAll_yyq();
        map.put("nations", nationList);
        return map;
    }
}
