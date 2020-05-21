package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.*;
import com.hj.jdpth.service.ResourcesdetailsService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourcesdetailsServiceImpl implements ResourcesdetailsService {
    @Autowired
    ResourcesdetailsMapper resourcesdetailsMapper;
    @Autowired
    ResourcesMapper resourcesMapper;
    @Autowired
    VillageMapper villageMapper;
    @Autowired
    ZuMapper zuMapper;
    @Autowired
    ZhenMapper zhenMapper;
    @Autowired
    CaiwuCapitalMapper caiwuCapitalMapper;

    @Override
    public Map<String, Object> queryResourcesdetails(Integer mtyle, Integer rkey, Integer zhenId, Integer villageId, String resourceStyle, Integer startPage, Integer pageSize) {
        PageHelper.startPage(startPage, pageSize);
        Map<String, Object> result = new HashMap<>();
        Page<Map<String, Object>> assetsPage = null;
        if (mtyle == 2 || mtyle == 3) {
            assetsPage = resourcesdetailsMapper.queryNameAddress_gsh(rkey, 0, 0, null);
        } else if (mtyle == 4) {
            assetsPage = resourcesdetailsMapper.queryNameAddress_gsh(rkey, zhenId, 0, null);
        } else if (mtyle == 5) {
            assetsPage = resourcesdetailsMapper.queryNameAddress_gsh(rkey, zhenId, villageId, null);
        }
        if (mtyle != 0) {
            List<Zhen> zhenList = zhenMapper.quaryByRegion_gsh(rkey);
            List list = new ArrayList();
            for (Zhen zhen : zhenList) {
                List<Village> villagelist = villageMapper.queryByZhen_gsh(zhen.getZhenId());
                for (Village village : villagelist) {
                    List<Map<String, Object>> mapList = resourcesMapper.quaryDisStyle(village.getVillageId());
                    Map<String, Object> map = new HashMap<>();
                    map.put("resourceStyle", mapList);
                    map.put("village", village);
                    list.add(map);
                }
            }
            result.put("region", list);
        } else {
            assetsPage = resourcesdetailsMapper.queryNameAddress_gsh(rkey, zhenId, villageId, resourceStyle);
        }
        if (assetsPage.isEmpty()) {
            result.put("status", "null");
        } else {
            result.put("status", "success");
            result.put("data", assetsPage);
            //总页数
            result.put("total", assetsPage.getPages());
            //记录总数
            result.put("count", assetsPage.getTotal());
            //当前第几页
            result.put("nowPage", assetsPage.getPageNum());
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteOne(Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Resourcesdetails resourcesdetails = resourcesdetailsMapper.quaryById(id);
            if (resourcesdetails != null) {
                Boolean flag = resourcesdetailsMapper.deleteResourcesDetails(id);
                if (flag == true) {
                    result.put("status", "success");
                    result.put("data", "删除成功");
                } else {
                    result.put("status", "false");
                    result.put("data", "删除失败");
                }
            } else {
                result.put("status", "null");
                result.put("data", "没有数据，无法删除");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "error");
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteMany(String[] arrayId) {
        Map<String, Object> result = new HashMap<>();
        for (String Rid : arrayId) {
            int flagb = 0;
            Integer rid = Integer.valueOf(Rid);
            Resourcesdetails resourcesdetails = resourcesdetailsMapper.quaryById(rid);
            if (resourcesdetails != null) {
                Boolean flag = resourcesdetailsMapper.deleteResourcesDetails(rid);
                if (flag == true) {
                    flagb++;
                }
                if (flagb != 0) {
                    result.put("status", "success");
                    result.put("data", "删除成功");
                } else {
                    result.put("status", "flase");
                    result.put("data", "删除失败");
                }
            } else {
                result.put("status", "null");
                result.put("data", "没有数据，无法删除");
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteStringMany(String resourcesId) {
        Map<String, Object> result = new HashMap<>();
        String[] arrayId = resourcesId.split(",");
        for (String Rid : arrayId) {
            Integer rid = Integer.valueOf(Rid);
            Resourcesdetails resourcesdetails = resourcesdetailsMapper.quaryById(rid);
            if (resourcesdetails != null) {
                Boolean flag = resourcesdetailsMapper.deleteResourcesDetails(rid);
                if (flag == true) {
                    result.put("status", "success");
                    result.put("data", "删除成功");
                } else {
                    result.put("status", "false");
                    result.put("data", "删除失败");
                }
            } else {
                result.put("status", "null");
                result.put("data", "没有数据，无法删除");
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> addResourcesDetails(String RD_Adress, Float RD_AreaCovered, String RD_Details, String RD_Name, Integer RD_ResourcesId) {
        Map<String, Object> result = new HashMap<>();
        Resourcesdetails resourcesdetails = new Resourcesdetails();
        try {
            resourcesdetails.setRdAdress(RD_Adress);
            resourcesdetails.setRdAreacovered(RD_AreaCovered);
            resourcesdetails.setRdDetails(RD_Details);
            resourcesdetails.setRdName(RD_Name);
            resourcesdetails.setRdResourcesid(RD_ResourcesId);
            Integer flag = resourcesdetailsMapper.addResourcesDetails(resourcesdetails);
            result.put("data", flag);
            result.put("status", "添加成功");
        } catch (Exception e) {
            result.put("data", "error");
            result.put("status", "error");
        }
        return result;
    }

    @Override
    @Transactional
    public Map<String, Object> uploadExcel(String path) {
        Map<String, Object> map = new HashMap<>();
        Workbook workbook = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            workbook = new HSSFWorkbook(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            try {
                FileInputStream fileInputStream = new FileInputStream(path);
                workbook = new XSSFWorkbook(fileInputStream);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //获取第一个表
        Sheet sheet = workbook.getSheetAt(0);
        try {
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                Cell cell = null;
                //从第三行
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                //获取第三行的第一个表格
                cell = row.getCell(0);
                //判断是否为空
                if (cell == null || cell.equals("") || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    continue;
                }
                int vid = (int) cell.getNumericCellValue();
                //获取第三行的第二个表格
                cell = row.getCell(1);
                int zuid;
                if (getCellValue(cell).equals("")) {
                    List<Zu> zuList = caiwuCapitalMapper.findAllZuByID(vid);
                    if (zuList.size() > 1) {
                        map.put("status", "第" + (i + 1) + "行缺少组id");
                        return map;
                    } else {
                        zuid = caiwuCapitalMapper.findAllZuByID(vid).get(0).getzKey();
                    }
                } else {
                    zuid = (int) cell.getNumericCellValue();
                }
                int zuId = (int) cell.getNumericCellValue();
                //获取第三行的第三个表格
                cell = row.getCell(2);
                if (cell == null || cell.equals("") || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                    map.put("status", "第" + (i + 1) + "行未填写资源类型");
                    return map;
                }
                String resourceStyle = getCellValue(cell);
                Resources resources11 = resourcesMapper.quaryResourcesStyle(vid, zuid, resourceStyle);
                if (resources11 == null) {
                    map.put("status", "请新增第" + (i + 1) + "行的--" + resourceStyle + "--后重新导入");
                    return map;
                }
                Integer resourceStyleId = resources11.getResourcesId();
                //获取第三行的第四个表格
                cell = row.getCell(3);
                String rd_Name = getCellValue(cell);
                //获取第三行的第五个表格
                cell = row.getCell(4);
                String resourcesDetails = getCellValue(cell);
                //获取第三行的第六个表格
                cell = row.getCell(5);
                String address = getCellValue(cell);
                //获取第三行的第七个表格
                cell = row.getCell(6);
                float cover = (float) cell.getNumericCellValue();
                Resourcesdetails resourcesdetails = new Resourcesdetails();
                //插入资源详情表
                resourcesdetails.setRdResourcesid(resourceStyleId);
                resourcesdetails.setRdAreacovered(cover);
                resourcesdetails.setRdAdress(address);
                resourcesdetails.setRdName(rd_Name);
                resourcesdetails.setRdDetails(resourcesDetails);
                resourcesdetailsMapper.AdddResourcesdetails(resourcesdetails);
            }
            map.put("status", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "出现错误");
            throw new RuntimeException();
        } finally {
            return map;
        }
    }

    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        String path = "";
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        path = "E:\\HJResourse\\Resources\\";
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
        }
        String fileName = System.currentTimeMillis() + "." + type;

        File targerFile = new File(path, fileName);
        try {
            file.transferTo(targerFile);
            map.put("path", path + fileName);
            map.put("name", originalFileName);
            map.put("type", type);
            map.put("state", "success");
        } catch (IOException e) {
            map.put("path", "");
            map.put("name", "此文件上传失败");
            map.put("state", "error");
        }
        return map;
    }

    private String getCellValue(Cell cell) {
        String cellValue = "";
        java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().replaceAll(" ", "");
                break;
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).replaceAll(" ", "");
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_BLANK:
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }

}
