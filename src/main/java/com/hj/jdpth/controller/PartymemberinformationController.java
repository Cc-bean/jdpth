package com.hj.jdpth.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.PartymemberinformationService;
import com.hj.jdpth.utils.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class PartymemberinformationController {
    @Autowired
    PartymemberinformationService partymemberinformationService;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    JobMapper jobMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    NationMapper nationMapper;

    @PostMapping("/queryPartyMembers")
    public Map<String, Object> partyMembersPage(@RequestParam Integer startPage, @RequestParam Integer pageSize,
                                                @RequestParam(required = false) Integer rKey, @RequestParam(required = false) Integer zhenId,
                                                @RequestParam(required = false) Integer villageId, @RequestParam(required = false) String userName,
                                                Manager manager
                                                /*@RequestParam Integer mRegion,@RequestParam Integer mZhenid,@RequestParam Integer mVillageid,
                                                @RequestParam Integer mType*/) {
        Page<PartymemberinformationAndYonghu> partyMembersPage = null;
        Map<String, Object> result = new HashMap<>();

        if (manager.getmType() == 2 || manager.getmType() == 3) {
            Region region = regionMapper.queryById_yyq(manager.getmRegion());
            result.put("quName", region.getrName());
            List<Zhen> zhenList = zhenMapper.queryZhenByQuId_yyq(manager.getmRegion());
            result.put("zhenName", zhenList);
            PageHelper.startPage(startPage, pageSize);
            partyMembersPage = partymemberinformationService.queryAll_yyq(manager.getmRegion(), zhenId, villageId, userName);
        }
        if (manager.getmType() == 4) {
            Region region = regionMapper.queryById_yyq(manager.getmRegion());
            result.put("quName", region.getrName());
            Zhen zhen = zhenMapper.queryByZhenId_yyq(manager.getmZhenid());
            result.put("zhenName", zhen);
            List<Village> cunList = villageMapper.queryVillageByZhenId_yyq(manager.getmZhenid());
            result.put("cunName", cunList);
            PageHelper.startPage(startPage, pageSize);
            partyMembersPage = partymemberinformationService.queryAll_yyq(manager.getmRegion(), manager.getmZhenid(), villageId, userName);
        }
        if (manager.getmType() == 5) {
            Region region = regionMapper.queryById_yyq(manager.getmRegion());
            result.put("quName", region.getrName());
            Zhen zhen = zhenMapper.queryByZhenId_yyq(manager.getmZhenid());
            result.put("zhenName", zhen);
            Village cunName = villageMapper.queryByCunId_yyq(manager.getmVillageid());
            result.put("cunName", cunName);
            PageHelper.startPage(startPage, pageSize);
            partyMembersPage = partymemberinformationService.queryAll_yyq(manager.getmRegion(), manager.getmZhenid(), manager.getmVillageid(), userName);
        }
        result.put("partyMembers", partyMembersPage);
        //总页数
        result.put("total", partyMembersPage.getPages());
        //记录总数
        result.put("count", partyMembersPage.getTotal());
        //当前第几页
        result.put("nowPage", partyMembersPage.getPageNum());
        return result;
    }


    @GetMapping("/findPartyMemberById/{id}")/////详情
    public Map<String, Object> findPartyMemberById(@PathVariable(value = "id") Integer id) {
        PartymemberinformationAndYonghu partyMember = partymemberinformationService.queryById_yyq(id);
        Map<String, Object> map = new HashMap<>();
        map.put("partyMember", partyMember);
        return map;
    }


    @GetMapping("/findCun/{zhen_id}")/////镇找村
    public Map<String, Object> findCun(@PathVariable(value = "zhen_id") Integer zhen_id) {
        List<Village> cunList = villageMapper.queryVillageByZhenId_yyq(zhen_id);
        Map<String, Object> map = new HashMap<>();
        map.put("cunName", cunList);
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.PARTYMEMBERINFORMATION)
    @DeleteMapping("/deletePartyMemberById/{id}/{managerId}")/////删除
    public Map<String, Object> deleteById(@PathVariable("id") Integer id, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        String result = partymemberinformationService.deleteById_yyq(id);
        map.put("result", result);
        return map;
    }

    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.PARTYMEMBERINFORMATION)
    @DeleteMapping("/deleteManyPartyMemberById/{ids}/{managerId}")/////批量删除
    public Map<String, Object> deleteMany(@PathVariable("ids") Integer[] ids, @PathVariable(value = "managerId") Integer managerId) {
        Map<String, Object> map = new HashMap<>();
        String result = partymemberinformationService.deleteManyById_yyq(ids);
        map.put("result", result);
        return map;
    }

    @PostMapping("/insertPartyMemberShow")////////添加时要显示的一些数据
    public Map<String, Object> insertShow(Manager manager) {
        Map<String, Object> map = partymemberinformationService.FENJI(manager);
        return map;
    }

    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.PARTYMEMBERINFORMATION)
    @PostMapping("/insertPartyMemberSubmit")////添加党员
    public Map<String, Object> insertSubmit(@RequestParam Integer managerId, @RequestParam(required = false) String fazhanTime, @RequestParam(required = false) String joinTime, Partymemberinformation partymemberinformation, @RequestParam String name, @RequestParam String sex/*, @RequestParam(required = false) MultipartFile dangyuan_pic*/) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            partymemberinformation.setPmiJointime(sdf.parse(joinTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            partymemberinformation.setPmJoined(sdf.parse(fazhanTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result = partymemberinformationService.insert_yyq(partymemberinformation, name, sex/*,dangyuan_pic*/);
        map.put("result", result);
        return map;
    }


    @PostMapping("/updatePartyMemberShow")/////修改党员要显示的信息
    public Map<String, Object> updatePartyMemberShow(@RequestParam Integer id, Manager manager) {
        Map<String, Object> map = partymemberinformationService.FENJI(manager);
        PartymemberinformationAndYonghu partyMember = partymemberinformationService.queryById_yyq(id);
        map.put("partyMember", partyMember);
        map.put("fazhanTime", partyMember.getPmJoined());
        map.put("joinTime", partyMember.getPmiJointime());
        return map;
    }

    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.PARTYMEMBERINFORMATION)
    @PutMapping("/updatePartyMemberSubmit")////////修改党员
    public Map<String, Object> updatePartyMemberSubmit(@RequestParam Integer managerId, @RequestParam(required = false) String fazhanTime, @RequestParam(required = false) String joinTime, Partymemberinformation partymemberinformation, @RequestParam Integer id/*,@RequestParam(required = false)MultipartFile dangyuan_pic*/) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            partymemberinformation.setPmiJointime(sdf.parse(joinTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            partymemberinformation.setPmJoined(sdf.parse(fazhanTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result = partymemberinformationService.update_yyq(partymemberinformation, id/*,dangyuan_pic*/);
        map.put("result", result);
        return map;
    }

    @PostMapping("/importPartyMember")
    public Map<String, Object> importPartyMember(HttpServletRequest request, MultipartFile importfile) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        //MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream in = null;
        List<List<Object>> listob = null;
        //MultipartFile file = multipartRequest.getFile("upfile");
        if (importfile.getOriginalFilename().equals("")) {
            map.put("result0", "无excel表");
        } else {
            in = importfile.getInputStream();
            listob = new ImportExcelUtil().getBankListByExcel(in, importfile.getOriginalFilename());
            in.close();
            //该处可调用service相应方法进行数据保存到数据库中
            for (int i = 1; i < listob.size(); i++) {
                List<Object> lo = listob.get(i);
                Partymemberinformation p = new Partymemberinformation();
                if (!String.valueOf(lo.get(0)).equals("")) {
                    p.setPmVillage(Integer.valueOf(String.valueOf(lo.get(0))));
                }
                p.setPmBeiyong1(String.valueOf(lo.get(5)));
                p.setPmJiguan(String.valueOf(lo.get(6)));
                p.setPmiDegreeofeducation(String.valueOf(lo.get(7)));
                if (!String.valueOf(lo.get(8)).equals("")) {
                    p.setPmJoined(sdf.parse(String.valueOf(lo.get(8))));//发展时间
                }
                if (!String.valueOf(lo.get(9)).equals("")) {
                    p.setPmiJointime(sdf.parse(String.valueOf(lo.get(9))));//入党时间
                }
                p.setPmiInauguralunit(String.valueOf(lo.get(10)));//就职单位
                if (!String.valueOf(lo.get(11)).equals("")) {
                    p.setPmiPost(Integer.valueOf(String.valueOf(lo.get(11))));
                }

                p.setPmiDetails(String.valueOf(lo.get(12)));

                String result = partymemberinformationService.insert_yyq(p, "", "");

                if (result.equals("无该用户信息，请先添加该用户信息")) {
                    sb.append("第" + (i + 2) + "行" + result + ",");
                    //map.put("result" + i, "第" + (i + 2) + "行" + result);
                }
               /* if (result.equals("名字与已有用户重名，但无该用户信息，请先添加该用户信息")) {
                    sb.append("第" + (i + 2) + "行" + result+",");
                    //map.put("result" + i, "第" + (i + 2) + "行" + result);
                }*/
            }
            map.put("error", sb);
            map.put("result", "导入完成");
        }
        return map;
    }

}
