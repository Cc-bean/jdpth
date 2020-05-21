package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.CaiwuCapitalMapper;
import com.hj.jdpth.repository.CaiwulicaiMapper;
import com.hj.jdpth.service.CaiwulicaiService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CaiwulicaiServiceImpl implements CaiwulicaiService {
    @Autowired
    CaiwulicaiMapper caiwulicaiMapper;
    @Autowired
    CaiwuCapitalMapper caiwuCapitalMapper;

    @Override
    public Map<String, Object> findlicai(int regionId, int zhenId, int villageId, int zuId, int style, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Map<String, Object>> licaiqingkuang = null;
        if (style == 2 || style == 3) {
            licaiqingkuang = caiwulicaiMapper.findlicai(regionId, 0, 0, 0);
        } else if (style == 4) {
            licaiqingkuang = caiwulicaiMapper.findlicai(regionId, zhenId, 0, 0);
        } else if (style == 5) {
            licaiqingkuang = caiwulicaiMapper.findlicai(regionId, zhenId, villageId, 0);
        }
        if (style != 0) {
            result.put("region", findallVillageId(regionId));
        } else {
            licaiqingkuang = caiwulicaiMapper.findlicai(regionId, zhenId, villageId, zuId);
        }
        result.put("data", licaiqingkuang);
        //总页数
        result.put("total", licaiqingkuang.getPages());
        //记录总数
        result.put("count", licaiqingkuang.getTotal());
        //当前第几页
        result.put("nowPage", licaiqingkuang.getPageNum());
        return result;
    }

    List findallVillageId(int regionId) {
        List<Zhen> zhen = caiwuCapitalMapper.findZhenByRegionId(regionId);
        List list = new ArrayList();
        for (Zhen zhen1 : zhen) {
            Map<String, Object> map = new HashMap<>();
            List<Village> villages = caiwuCapitalMapper.findVillageByZhenId(zhen1.getZhenId());
            map.put("village", villages);
            map.put("zhen", zhen1);
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, Object> findZu(int villageId) {
        Map<String, Object> result = new HashMap<>();
        List<Zu> zuList = caiwulicaiMapper.findZu(villageId);
        result.put("data", zuList);
        return result;
    }

    public Map<String, Object> deleteLicai(int licaiId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Licaiqingkuang licaiqingkuang = caiwulicaiMapper.findLicaiById(licaiId);
            if (licaiqingkuang.getLcLujing() != null) {
                String[] lujing = licaiqingkuang.getLcLujing().split(",");
                for (String lujing1 : lujing) {
                    if (lujing1 != "" || lujing1 != null) {
                        File file = new File(lujing1);
                        file.delete();
                    }
                }
            }
            if (caiwulicaiMapper.deleteLicai(licaiId) > 0) {
                result.put("status", "success");
                result.put("data", "删除成功");
            } else {
                result.put("status", "null");
                result.put("data", "没有数据");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "删除出错");
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> deleteManyLicai(String[] licaiId) {
        Map<String, Object> result = new HashMap<>();
        try {
            int b = 0;
            int c;
            Licaiqingkuang licaiqingkuang;
            for (String a : licaiId) {
                c = Integer.parseInt(a);
                licaiqingkuang = caiwulicaiMapper.findLicaiById(c);
                System.out.println(licaiqingkuang);
                if (licaiqingkuang.getLcLujing() != null) {
                    String[] lujing = licaiqingkuang.getLcLujing().split(",");
                    for (String lujing1 : lujing) {
                        if (lujing1 != "" || lujing1 != null) {
                            File file = new File(lujing1);
                            file.delete();
                        }
                    }
                }
                if (caiwulicaiMapper.deleteLicai(c) > 0) {
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
    public Map<String, Object> insertLicai(Licaiqingkuang licaiqingkuang) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (caiwulicaiMapper.insertLicai(licaiqingkuang) > 0) {
                result.put("status", "success");
                result.put("data", "新增成功");
            } else {
                result.put("status", "null");
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
        String path = "";
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件的名称" + originalFileName);
        //获取文件的类型，以“.”作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        path = "E:\\HJResourse\\Licaiqingkuang\\";
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
            byte[] bs = file.getBytes();
            InputStream in = new ByteArrayInputStream(bs);
            FileOutputStream fos = new FileOutputStream(targerFile);
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = in.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();
            fos.close();
            in.close();
            //file.transferTo(targerFile);
            map.put("path", fileName);
            map.put("name", originalFileName);
            map.put("type", type);
            map.put("state", "success");
            return map;
        } catch (Exception e) {
            System.out.println("文件保存错误");
            map.put("path", "");
            map.put("name", "此文件上传失败");
            map.put("state", "error");
            map.put("errorStack", e.toString());
            return map;
        }

    }

    @Override
    public Map<String, Object> updataLicai(Licaiqingkuang licaiqingkuang) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (caiwulicaiMapper.updataLicai(licaiqingkuang) > 0) {
                result.put("status", "success");
                result.put("data", "修改成功");
            } else {
                result.put("status", "null");
                result.put("data", "无修改");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "修改出错");
        }
        return result;
    }

    @Transactional
    public Map<String, Object> importcapital(int rowNum, Sheet sheet, Map<String, Object> map, Capital capital, Capital capital0, int zuId, Integer villageId, String villageName,
                                             String SheetOne, String style, String year1, String month1) {
        int capitalId;
        boolean flag;
        if (capital == null) {
            flag = true;
            capital0.setcVillageid(villageId);
            capital0.setcType(villageName + SheetOne.substring(0, 8) + style + "明细");
            capital0.setCdZu(zuId);
            capital0.setCdIncome(year1);
            capital0.setCdExpenditure(month1);
            caiwuCapitalMapper.importCapital(capital0);
            capitalId = capital0.getCapitalId();
        } else {
            flag = false;
            capital0 = capital;
            capitalId = capital.getCapitalId();
        }

        String CapitalDetailsincome = "";//资金详情的本次现金收入
        String CapitalDetailsexpenditure = "";//资金详情的本次现金支出
        Date time = null;
        String content;
        String Captialincome;      //资金的本月现金收入
        String Captialexpenditure; //资金的本月现金支出

        for (; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }

            Cell cell = row.getCell(0);
            //判断第一个单元格是否为空
            if (cell != null && !getCellValue(cell).equals("")) {
                time = cell.getDateCellValue();
            } else {
                continue;
            }
            cell = row.getCell(2);
            //当为本月合计的时候跳出循环
            if (!getCellValue(cell).equals("本月合计")) {
                content = getCellValue(cell);
            } else {
                cell = row.getCell(3);
                Captialincome = getCellValue(cell);
                cell = row.getCell(4);
                Captialexpenditure = getCellValue(cell);
                if (!Captialincome.equals("")) {
                    capital0.setCdBeiyong1(Captialincome);
                } else {
                    capital0.setCdBeiyong1("");
                }
                if (!Captialexpenditure.equals("")) {
                    capital0.setCdBeiyong2(Captialexpenditure);
                } else {
                    capital0.setCdBeiyong2("");
                }
                if (flag == true) {
                    caiwuCapitalMapper.updateCapital(capital0);
                }
                break;
            }
            if (flag == false) {
                continue;
            }
            //当为期初余额的时候
            if (!getCellValue(cell).equals("月初余额")) {
                cell = row.getCell(3);
                if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    CapitalDetailsincome = getCellValue(cell);
                }
            } else {
                cell = row.getCell(6);
                if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    CapitalDetailsincome = getCellValue(cell);
                }
            }
            cell = row.getCell(4);
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                CapitalDetailsexpenditure = getCellValue(cell);
            }
            Capitaldetails capitaldetails = new Capitaldetails();
            capitaldetails.setCdZijinid(capitalId);
            capitaldetails.setCdZu(zuId);
            capitaldetails.setCdXiangqing(content);
            capitaldetails.setCdTime(time);
            if (CapitalDetailsincome != "") {
                capitaldetails.setCdIncome(new BigDecimal(CapitalDetailsincome));
                CapitalDetailsincome = "";
            }
            if (CapitalDetailsexpenditure != "") {
                capitaldetails.setCdExpenditure(new BigDecimal(CapitalDetailsexpenditure));
                CapitalDetailsexpenditure = "";
            }
            caiwuCapitalMapper.importCapitaldetails(capitaldetails);

        }
        map.put("rowNum", rowNum);
        return map;
    }

    java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");

    public String getCellValue(Cell cell) {
        String cellValue = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue().replaceAll(" ", "");
                break;
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = df.format(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).replaceAll(" ", "");
                break;
            case Cell.CELL_TYPE_FORMULA:
                try {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    cellValue = String.valueOf(cell.getRichStringCellValue());
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                break;

            default:
                cellValue = "";
        }
        return cellValue;
    }
}
