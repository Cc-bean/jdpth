package com.hj.jdpth.controller;

import com.hj.jdpth.annotation.OperationLogDetail;
import com.hj.jdpth.aop.OperationType;
import com.hj.jdpth.aop.OperationUnit;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.ResourcesService;
import com.hj.jdpth.service.ResourcesdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ResourcesdetailsController {
    @Autowired
    ResourcesdetailsMapper resourcesdetailsMapper;
    @Autowired
    ResourcesMapper resourcesMapper;
    @Autowired
    ResourcesService resourcesService;
    @Autowired
    ResourcesdetailsService resourcesdetailsService;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    ManagerMapper managerMapper;

    //四级搜索   用了
    @PostMapping("/resources/resourcesDetails")
    public Map<String, Object> queryCun(
            @RequestParam(required = false) Integer mType,
            @RequestParam Integer mRegion,
            @RequestParam(required = false) Integer mZhenid,
            @RequestParam(required = false) Integer mVillageid,
            @RequestParam(required = false) String rType,
            @RequestParam Integer startPage,
            @RequestParam Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        if (startPage != null && pageSize != null && mRegion != null) {
            if (mType == null) {
                mType = 0;
            }
            if (mZhenid == null) {
                mZhenid = 0;
            }
            if (mVillageid == null) {
                mVillageid = 0;
            }
            result = resourcesdetailsService.queryResourcesdetails(mType, mRegion, mZhenid, mVillageid, rType, startPage, pageSize);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    //单删
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.RESOURCESDETAIL)
    @DeleteMapping("/resources/resourcesDetailsDelete/{resourcesId}/{managerId}")
    public Map<String, Object> deleteOne(
            @PathVariable(value = "resourcesId") Integer resourcesId,
            @PathVariable(required = false) Integer managerId) {
        System.out.println(resourcesId);
        Map<String, Object> result = new HashMap<>();
        if (resourcesId != null) {
            Map<String, Object> map = resourcesdetailsService.deleteOne(resourcesId);
            result.put("data", map);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    //多删 传String数组 没用这个
    //防止深度序列化   traditional: true,
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.RESOURCESDETAIL)
    @DeleteMapping("/resourcesDetailsDeleteMany/{resourcesId}/{managerId}")
    public Map<String, Object> deleteMany(
            @PathVariable(value = "resourcesId") String[] resourcesId,
            @PathVariable(required = false) Integer managerId) {
        Map<String, Object> result = new HashMap<>();
        if (resourcesId.length != 0) {
            Map<String, Object> map = resourcesdetailsService.deleteMany(resourcesId);
            result.put("data", map);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    //多删（传string）
    @OperationLogDetail(operationType = OperationType.DELETE, operationUnit = OperationUnit.RESOURCESDETAIL)
    @DeleteMapping("/resources/resourcesDetailsDeleteMany/{resourcesId}/{managerId}")
    public Map<String, Object> deleteStringMany(
            @PathVariable(value = "resourcesId") String resourcesId,
            @PathVariable(required = false) Integer managerId) {
        Map<String, Object> result = new HashMap<>();
        if (!resourcesId.equals("null")) {
            Map<String, Object> map = resourcesdetailsService.deleteStringMany(resourcesId);
            result.put("data", map);
        } else {
            result.put("data", "数据不完整!");
            result.put("state", "false");
        }
        return result;
    }

    //资源详情添加
    @OperationLogDetail(operationType = OperationType.INSERT, operationUnit = OperationUnit.RESOURCESDETAIL)
    @PostMapping("/resources/addResourcesResourcesDetails")
    public Map<String, Object> addResourcesResourcesDetails(
            @RequestParam(required = false) Integer managerId,
            @RequestParam Integer resourcesId,
            @RequestParam(required = false) String RD_Name,
            @RequestParam(required = false) String RD_Adress,
            @RequestParam(required = false) Float RD_AreaCovered,
            @RequestParam(required = false) String RD_Details) {
        Map<String, Object> result = new HashMap<>();
        if (resourcesId != null) {
            if (RD_AreaCovered == null) {
                RD_AreaCovered = 0f;
            }
            try {
                Map<String, Object> map2 = resourcesdetailsService.addResourcesDetails(RD_Adress, RD_AreaCovered, RD_Details, RD_Name, resourcesId);
                result.put("data2", map2);
                result.put("status", "success");
            } catch (Exception e) {
                result.put("data", "error");
                result.put("status", "error");
            }
        } else {
            result.put("data", "null");
            result.put("status", "输入不完整");
        }
        return result;
    }

    //编辑资源详情
    @OperationLogDetail(operationType = OperationType.UPDATE, operationUnit = OperationUnit.RESOURCESDETAIL)
    @PostMapping("/resources/updateResourcesdetails")
    public Map<String, Object> updateResourcesdetails(
            @RequestParam(required = false) Integer managerId,
            @RequestParam(required = false) Integer Resources_Id,
            @RequestParam(required = false) String RD_Name,
            @RequestParam(required = false) String RD_Adress,
            @RequestParam(required = false) Float RD_AreaCovered,
            @RequestParam(required = false) String RD_Details,
            @RequestParam Integer ResourcesDetails_Id) {
        Map<String, Object> result = new HashMap<>();
        if (ResourcesDetails_Id != null) {
            Resourcesdetails resourcesdetails = resourcesdetailsMapper.quaryResourcesDetails(ResourcesDetails_Id);
            if (resourcesdetails != null) {
                if (RD_AreaCovered == null) {
                    RD_AreaCovered = resourcesdetails.getRdAreacovered();
                }
                resourcesdetails.setRdName(RD_Name);
                resourcesdetails.setRdResourcesid(Resources_Id);
                resourcesdetails.setRdAdress(RD_Adress);
                resourcesdetails.setRdAreacovered(RD_AreaCovered);
                resourcesdetails.setRdDetails(RD_Details);
                Integer flag = resourcesdetailsMapper.updateResourcesdetails(resourcesdetails);
                if (flag > 0) {
                    result.put("status", "修改成功");
                }
            } else {
                result.put("data", "找不到，无法修改");
                result.put("status", "修改失败");
            }
        } else {
            result.put("data", "数据不完整");
            result.put("status", "修改失败");
        }
        return result;
    }

    @PostMapping("/resources/importResourcesExcel")
    public Map<String, Object> importResourcesExcel(
            @RequestParam("file") MultipartFile file
    ) {
        Map<String, Object> result = new HashMap<>();
        result = resourcesdetailsService.uploadFile(file);
        String path = result.get("path").toString();
        result.clear();
        result = resourcesdetailsService.uploadExcel(path);
        File file1 = new File(path);
        file1.delete();
        return result;
    }

    @PostMapping("/resources/quary11StyleByZuid")
    public Map<String, Object> quary11StyleByZuid(
            @RequestParam("zuid") Integer zuid
    ) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> resources = resourcesMapper.quaryStyleByZuid(zuid);
        result.put("data", resources);
        return result;
    }
}
