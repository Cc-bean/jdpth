package com.hj.jdpth.controller;

import com.hj.jdpth.domain.Manager;
import com.hj.jdpth.repository.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class LeaderManagerController {
    @Autowired
    ManagerMapper managerMapper;

    @PutMapping(value = "/m/updateManager")
    public Map<String, Object> updateManager(
            @RequestParam Integer managerId,
            @RequestParam(required = false) String mAccountnumber,
            @RequestParam(required = false) String mPassword,
            @RequestParam(required = false) String mName,
            @RequestParam(required = false) String mSex,
            @RequestParam(required = false) Integer mDepartmentid,
            @RequestParam(required = false) String mPhone,
            @RequestParam(required = false) Integer mPost,
            @RequestParam(required = false) Integer mVillageid,
            @RequestParam(required = false) Integer mZhenid,
            @RequestParam(required = false) Integer mShi,
            @RequestParam(required = false) Integer mRegion,
            @RequestParam(required = false) Integer mType) {
        Map<String, Object> map = new HashMap<>();
        try {
            Manager manager = managerMapper.FindManager(managerId);
            if (manager == null) {
                map.put("state", "error");
                return map;
            }
            if (mAccountnumber != null) {
                manager.setmAccountnumber(mAccountnumber);
            }
            if (mPassword != null) {
                manager.setmPassword(mPassword);
            }
            if (mName != null) {
                manager.setmName(mName);
            }
            if (mSex != null) {
                manager.setmSex(mSex);
            }
            if (mDepartmentid != null) {
                manager.setmDepartmentid(mDepartmentid);
            }
            if (mPhone != null) {
                manager.setmPhone(mPhone);
            }
            if (mPost != null) {
                manager.setmPost(mPost);
            }
            if (mVillageid != null) {
                manager.setmVillageid(mVillageid);
            }
            if (mZhenid != null) {
                manager.setmZhenid(mZhenid);
            }
            if (mShi != null) {
                manager.setmShi(mShi);
            }
            if (mRegion != null) {
                manager.setmRegion(mRegion);
            }
            if (mType != null) {
                manager.setmType(mType);
            }

            managerMapper.updateManager(manager);
            map.put("state", "success");

        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", "error");
        }
        return map;
    }
}
