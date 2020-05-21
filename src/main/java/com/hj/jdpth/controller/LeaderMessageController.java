package com.hj.jdpth.controller;

import com.hj.jdpth.repository.LeaderMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang
 * 获取更新状况
 */
@CrossOrigin
@RestController
public class LeaderMessageController {

    @Autowired
    LeaderMessageMapper leaderMessageMapper;

    @GetMapping("/leader/messages")
    public Map<String, Object> getMessages() {
        Map<String, Object> map = new HashMap<>();
        List<HashMap> list1 = leaderMessageMapper.moneyMessages();  //关于资金
        List<HashMap> list2 = leaderMessageMapper.zzfzMessages();   //关于组织发展
        if (list1.size() == 0) {
            map.put("money", "无最新情况");
        } else {
            map.put("money", list1);
        }
        if (list2.size() == 0) {
            map.put("zzfz", "无最新情况");
        } else {
            map.put("zzfz", list2);
        }
        return map;
    }
}
