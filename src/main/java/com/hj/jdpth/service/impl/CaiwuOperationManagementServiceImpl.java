package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Operationmanagement;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.domain.Zhen;
import com.hj.jdpth.domain.Zu;
import com.hj.jdpth.repository.CaiwuCapitalMapper;
import com.hj.jdpth.repository.CaiwuOperationManagementMapper;
import com.hj.jdpth.service.CaiwuCapitalService;
import com.hj.jdpth.service.CaiwuOperationManagementService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CaiwuOperationManagementServiceImpl implements CaiwuOperationManagementService {
    @Autowired
    CaiwuOperationManagementMapper caiwuOperationManagementMapper;
    @Autowired
    CaiwuCapitalMapper caiwuCapitalMapper;
    @Autowired
    CaiwuCapitalService caiwuCapitalService;

    @Override
    public Map<String, Object> findOperationManagement(int regionId, int zhenId, int villageId, int style, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();

        PageHelper.startPage(startPage, pageSize);
        Page<Map<Village, Operationmanagement>> operationmanagement = null;
        if (style == 2 || style == 3) {
            operationmanagement = caiwuOperationManagementMapper.findOperationManagement(regionId, 0, 0);
        } else if (style == 4) {
            operationmanagement = caiwuOperationManagementMapper.findOperationManagement(regionId, zhenId, 0);
        } else if (style == 5) {
            operationmanagement = caiwuOperationManagementMapper.findOperationManagement(regionId, zhenId, villageId);
        }
        if (style != 0) {
            List<Zhen> zhen = caiwuCapitalMapper.findZhenByRegionId(regionId);
            List list = new ArrayList();
            for (Zhen zhen1 : zhen) {
                Map<String, Object> map = new HashMap<>();
                List<Village> villages = caiwuCapitalMapper.findVillageByZhenId(zhen1.getZhenId());
                map.put("village", villages);
                map.put("zhen", zhen1);
                list.add(map);
            }
            result.put("region", list);
        } else {
            operationmanagement = caiwuOperationManagementMapper.findOperationManagement(regionId, zhenId, villageId);
        }
        result.put("data", operationmanagement);
        //总页数
        result.put("total", operationmanagement.getPages());
        //记录总数
        result.put("count", operationmanagement.getTotal());
        //当前第几页
        result.put("nowPage", operationmanagement.getPageNum());
        return result;
    }

    void deleteFile(String lujings) {
        String[] lujing = lujings.split(",");
        for (String lujing1 : lujing) {
            File file = new File("E:\\HJResourse\\OperationManagement\\" + lujing1);
            file.delete();
        }
    }

    public Map<String, Object> deleteOperationManagement(int operationmanagementId) {
        Map<String, Object> result = new HashMap<>();
        Operationmanagement op = caiwuOperationManagementMapper.findOperationManagementById(operationmanagementId);
        try {
            if (op.getOmTmBeiyong2() != null) {
                deleteFile(op.getOmTmBeiyong2());
            }
            if (caiwuOperationManagementMapper.deleteOperationManagement(operationmanagementId) > 0) {
                result.put("status", "success");
                result.put("data", "删除成功");
            } else {
                result.put("status", "null");
                result.put("data", "没有数据");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "删除出错");
        }
        return result;
    }

    public Map<String, Object> deleteManyOperationManagement(String[] operationmanagementId) {
        Map<String, Object> result = new HashMap<>();
        int b = 0;
        int c;
        try {
            Operationmanagement op;
            for (String a : operationmanagementId) {
                c = Integer.parseInt(a);
                op = caiwuOperationManagementMapper.findOperationManagementById(c);
                if (op.getOmTmBeiyong2() != null) {
                    deleteFile(op.getOmTmBeiyong2());
                }
                if (caiwuOperationManagementMapper.deleteOperationManagement(c) > 0) {
                    b++;
                }
            }
            if (b == 0) {
                result.put("status", "null");
                result.put("data", "没有数据");
            } else {
                result.put("status", "success");
                result.put("data", "删除成功");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "删除出错");
        }
        return result;
    }

    @Override
    public Map<String, Object> insertOperationManagement(Operationmanagement operationmanagement) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (caiwuOperationManagementMapper.insertOperationmanagement(operationmanagement) > 0) {
                result.put("status", "success");
                result.put("data", "新增成功");
            } else {
                result.put("status", "error");
                result.put("data", "新增失败");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "新增出错");
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> uploadFile(MultipartFile file) {
        String path;
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件的名称" + originalFileName);
        //获取文件的类型，以“.”作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        path = "E:\\HJResourse\\OperationManagement\\";
        File filePath = new File(path);
        System.out.println("文件保存的路径" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            //System.out.println("图片目录不存在，创建图片路径"+filePath);
            filePath.mkdirs();
        }
        //设置文件的新名字
        String fileName = System.currentTimeMillis() + "." + type;
        System.out.println("文件新名称" + fileName);
        //在指定的路径下面创建一个新的文件
        File targerFile = new File(path, fileName);
        //将文件保存到服务器的指定位置
        try {
            file.transferTo(targerFile);
            map.put("path", fileName);
            map.put("name", originalFileName);
            return map;
        } catch (Exception e) {
            System.out.println("文件保存错误");
            map.put("path", "");
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> updateOperationManagement(Operationmanagement operationmanagement) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (caiwuOperationManagementMapper.updateOperationmanagement(operationmanagement) > 0) {
                result.put("status", "success");
                result.put("data", "修改成功");
            } else {
                result.put("status", "error");
                result.put("data", "修改失败");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "修改出错");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional
    public Map<String, Object> importExcel(String path) {
        Workbook wookbook = null;
        try {
            //定义工作区间
            FileInputStream fis = new FileInputStream(path);
            wookbook = new XSSFWorkbook(fis);
            fis.close();
        } catch (Exception e) {
            try {
                FileInputStream fis = new FileInputStream(path);
                wookbook = new HSSFWorkbook(fis);
                fis.close();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<>();
        try {
            Sheet sheet = wookbook.getSheetAt(0);
            for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                int villageId;
                int zuId;
                String onName;
                Integer bianhao = 0;
                String lixiang = "";
                String zhaobiao = "";
                String shishi = "";
                String shishifang = "";
                String jianlifang = "";
                String yanshoufang = "";
                String xiangqing = "";
                String beizhu = "";
                Operationmanagement operationmanagement = new Operationmanagement();
                Cell cell = row.getCell(0);
                if (cell != null) {
                    villageId = (int) cell.getNumericCellValue();
                } else {
                    continue;
                }
                cell = row.getCell(1);
                if (!caiwuCapitalService.getCellValue(cell).equals("")) {
                    zuId = (int) cell.getNumericCellValue();
                } else {
                    List<Zu> zuList = caiwuCapitalMapper.findAllZuByID(villageId);
                    if (zuList.size() > 1) {
                        break;
                    } else {
                        zuId = zuList.get(0).getzKey();
                    }
                }
                cell = row.getCell(2);
                if (cell != null) {
                    onName = caiwuCapitalService.getCellValue(cell);
                } else {
                    continue;
                }
                cell = row.getCell(3);
                if (cell != null) {
                    bianhao = (int) cell.getNumericCellValue();
                }
                cell = row.getCell(4);
                if (cell != null) {
                    lixiang = caiwuCapitalService.getCellValue(cell);
                }
                cell = row.getCell(5);
                if (cell != null) {
                    zhaobiao = caiwuCapitalService.getCellValue(cell);
                }
                cell = row.getCell(6);
                if (cell != null) {
                    shishi = caiwuCapitalService.getCellValue(cell);
                }
                cell = row.getCell(7);
                if (cell != null) {
                    shishifang = caiwuCapitalService.getCellValue(cell);
                }
                cell = row.getCell(8);
                if (cell != null) {
                    jianlifang = caiwuCapitalService.getCellValue(cell);
                }
                cell = row.getCell(9);
                if (cell != null) {
                    yanshoufang = caiwuCapitalService.getCellValue(cell);
                }
                cell = row.getCell(10);
                if (cell != null) {
                    xiangqing = caiwuCapitalService.getCellValue(cell);
                }
                cell = row.getCell(11);
                if (cell != null) {
                    beizhu = caiwuCapitalService.getCellValue(cell);
                }
                operationmanagement.setOmVillageid(villageId);
                operationmanagement.setOmName(onName);
                operationmanagement.setOmTenderingmaterial(String.valueOf(bianhao));
                operationmanagement.setOmSourceoffunds(lixiang);
                operationmanagement.setOmImplementtheplan(zhaobiao);
                operationmanagement.setOmImplementation(shishi.equals("是"));
                operationmanagement.setOmImplementingparty(shishifang);
                operationmanagement.setOmFollowupmanagement(jianlifang);
                operationmanagement.setOmAcceptanceparty(yanshoufang);
                operationmanagement.setOmDivisionofresponsibility(xiangqing);
                operationmanagement.setOmType(beizhu);
                operationmanagement.setOmTmBeiyong1(String.valueOf(zuId));
                caiwuOperationManagementMapper.insertOperationmanagement(operationmanagement);
            }
            map.put("data", "成功");
        } catch (Exception e) {
            map.put("data", "出现错误");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            return map;
        }
    }
}
