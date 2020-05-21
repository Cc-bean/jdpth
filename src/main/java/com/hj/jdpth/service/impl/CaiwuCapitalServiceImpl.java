package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.*;
import com.hj.jdpth.repository.CaiwuCapitalMapper;
import com.hj.jdpth.service.CaiwuCapitalService;
import com.hj.jdpth.service.CaiwulicaiService;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CaiwuCapitalServiceImpl implements CaiwuCapitalService {
    @Autowired
    CaiwuCapitalMapper caiwuCapitalMapper;

    @Autowired
    CaiwulicaiService caiwulicaiService;

    @Override
    public Map<String, Object> findCapitaldetails(int regionId, int zhenId, int villageId, String cType, int style, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        Page<Map<Capital, Capitaldetails>> capitaldetails = null;
        if (style == 2 || style == 3) {
            capitaldetails = caiwuCapitalMapper.findCapitaldetailsByStyle(regionId, 0, 0, null);
        } else if (style == 4) {
            capitaldetails = caiwuCapitalMapper.findCapitaldetailsByStyle(regionId, zhenId, 0, null);
        } else if (style == 5) {
            capitaldetails = caiwuCapitalMapper.findCapitaldetailsByStyle(regionId, zhenId, villageId, null);
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
            capitaldetails = caiwuCapitalMapper.findCapitaldetailsByStyle(regionId, zhenId, villageId, cType);
        }
        result.put("data", capitaldetails);
        //总页数
        result.put("total", capitaldetails.getPages());
        //记录总数
        result.put("count", capitaldetails.getTotal());
        //当前第几页
        result.put("nowPage", capitaldetails.getPageNum());
        return result;
    }

    @Override
    public Map<String, Object> findzhenByRegionId(int regionId) {
        Map<String, Object> result = new HashMap<>();
        List<Zhen> zhen = caiwuCapitalMapper.findZhenByRegionId(regionId);
        result.put("data", zhen);
        return result;
    }

    @Override
    public Map<String, Object> findVillageByZhenId(int zhenId) {
        Map<String, Object> result = new HashMap<>();
        List<Village> village = caiwuCapitalMapper.findVillageByZhenId(zhenId);
        result.put("data", village);
        return result;
    }

    @Override
    public Map<String, Object> findCapitaldetailsByZhenId(int zhenId, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        try {
            Page<Map<Capital, Capitaldetails>> capitaldetails = caiwuCapitalMapper.findCapitaldetailsByzhenId(zhenId);
            result.put("status", "success");
            result.put("data", capitaldetails);
            //总页数
            result.put("total", capitaldetails.getPages());
            //记录总数
            result.put("count", capitaldetails.getTotal());
            //当前第几页
            result.put("nowPage", capitaldetails.getPageNum());
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "查询出错");
        }
        return result;
    }

    @Override
    public Map<String, Object> findCapitaldetailsByVillageId(int villageId, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        try {
            Page<Map<Capital, Capitaldetails>> capitaldetails = caiwuCapitalMapper.findCapitaldetailsByVillageId(villageId);
            if (capitaldetails.isEmpty()) {
                result.put("status", "null");
                result.put("data", "没有数据");
            } else {
                result.put("status", "success");
                result.put("data", capitaldetails);
                //总页数
                result.put("total", capitaldetails.getPages());
                //记录总数
                result.put("count", capitaldetails.getTotal());
                //当前第几页
                result.put("nowPage", capitaldetails.getPageNum());
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "查询出错");
        }
        return result;
    }

    @Override
    public Map<String, Object> findCapitaldetailsByStyle(int regionId, int zhenId, int villageId, String cType, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(startPage, pageSize);
        try {
            Page<Map<Capital, Capitaldetails>> capitaldetails = caiwuCapitalMapper.findCapitaldetailsByStyle(regionId, zhenId, villageId, cType);
            if (capitaldetails.isEmpty()) {
                result.put("status", "null");
                result.put("data", "没有数据");
            } else {
                result.put("status", "success");
                result.put("data", capitaldetails);
                //总页数
                result.put("total", capitaldetails.getPages());
                //记录总数
                result.put("count", capitaldetails.getTotal());
                //当前第几页
                result.put("nowPage", capitaldetails.getPageNum());
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "null");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteCapitaldetails(int capitaldetailsId) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (caiwuCapitalMapper.deleteCapitaldetails(capitaldetailsId) > 0) {
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

    @Override
    public Map<String, Object> deleteManyCapitaldetails(String[] capitaldetailsId) {
        Map<String, Object> result = new HashMap<>();
        try {
            int b = 0;
            for (String a : capitaldetailsId) {
                Capitaldetails capitaldetails = caiwuCapitalMapper.findCapitaldetailsById(Integer.parseInt(a));
                if (capitaldetails.getCdBeiyong1() != null) {
                    String[] lujing = capitaldetails.getCdBeiyong1().split(",");
                    for (String lujing1 : lujing) {
                        File file = new File("E:\\HJResourse\\Capitaldetails\\" + lujing1);
                        file.delete();
                    }
                }
                if (caiwuCapitalMapper.deleteCapitaldetails(Integer.parseInt(a)) > 0) {
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
    @Transactional
    public Map<String, Object> uploadExcel(String path) {
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
        Map<String, Object> map = null;
        try {
            map = new HashMap<>();
            //获取第一行的excel表数据
            Sheet sheet = wookbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            String SheetOne = getCellValue(cell);//获取资金的种类
            int year;
            int month;
            String year1;
            String month1;
            //判断是否能提取出年，月
            year1 = SheetOne.substring(0, 4);
            year = Integer.parseInt(year1);
            month1 = SheetOne.substring(5, 7);
            month = Integer.parseInt(month1);
            //提取第三行的村id，组id
            row = sheet.getRow(2);
            cell = row.getCell(0);
            int villageId = (int) cell.getNumericCellValue();
            cell = row.getCell(1);
            int zuId;
            String strZuid = getCellValue(cell);
            if (strZuid.equals("")) {
                List<Zu> zuList = caiwuCapitalMapper.findAllZuByID(villageId);
                if (zuList.size() > 1) {
                    map.put("lackZuId", "缺少组id");
                    return map;
                } else {
                    zuId = zuList.get(0).getzKey();
                }
            } else {
                zuId = Integer.parseInt(strZuid.replace(".00", ""));
            }
            Village village = caiwuCapitalMapper.findVillageById(villageId);
            //判断第四行是现金收支，还是银行收支
            String style;
            row = sheet.getRow(3);
            cell = row.getCell(0);
            String SheetThree = getCellValue(cell);
            if (SheetThree.equals("现金收支")) {
                style = "现金收支";
            } else {
                style = "银行收支";
            }
            //判断是否已经导入过资金类型表（关于现金收支的）
            Capital capital0 = new Capital();
            Capital capital = caiwuCapitalMapper.findCapitalimport(villageId, zuId, year, month, style);

            Map<String, Object> result = new HashMap<>();
            result = caiwulicaiService.importcapital(5, sheet, result, capital, capital0, zuId, villageId, village.getvName(), SheetOne, style, year1, month1);
            //循环银行支出
            int rowNum = (int) result.get("rowNum") + 1;
            row = sheet.getRow(rowNum);
            if (row == null) {
                map.put("status", "成功");
                return map;
            }
            cell = row.getCell(0);
            if (cell == null) {
                map.put("status", "成功");
                return map;
            }
            String Sheetpanduan = getCellValue(cell);
            if (Sheetpanduan.equals("现金收支")) {
                style = "现金收支";
            } else {
                style = "银行收支";
            }
            //判断是否已经导入过资金表（关于银行收支的）
            Capital capita2 = caiwuCapitalMapper.findCapitalimport(villageId, zuId, year, month, style);
            Map<String, Object> result1 = new HashMap<>();
            caiwulicaiService.importcapital(rowNum + 2, sheet, result1, capita2, capital0, zuId, villageId, village.getvName(), SheetOne, style, year1, month1);
            map.put("status", "成功");
        } catch (Exception e) {
            map.put("status", "出现错误");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            return map;
        }
    }

    @Transactional
    public Map<String, Object> yearUploadExcel(String path) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> result = new HashMap<>();
        //定义一个excel表格
        Workbook workbook = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            //XSSFWorkbook是操作2003年以后的版本，扩展名是,xlsx
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } catch (Exception e) {
            try {
                FileInputStream fis = new FileInputStream(path);
                //HSSFWorkbook是操作Excel2003以前（包括2003）的版本，扩展名是.xls
                workbook = new HSSFWorkbook(fis);
                fis.close();
            } catch (Exception e1) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        //获取第一个Excel的第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet的第一行row
        Row row = sheet.getRow(0);
        //获取row的第一个cell
        Cell cell = row.getCell(0);
        String sheetHeader = getCellValue(cell);
        //判断第一行的数据的内容
        String capitalStyle = "";
        if (sheetHeader.indexOf("现金") != -1) {
            capitalStyle = "现金收支";
        } else {
            capitalStyle = "银行收支";
        }
        String year1 = sheetHeader.substring(0, 4);//获取导入的资金年份
        Integer year = Integer.parseInt(year1);
        row = sheet.getRow(2);
        cell = row.getCell(0);
        Integer villageId = (int) cell.getNumericCellValue();
        cell = row.getCell(1);
        Integer zuId;
        String strZuid = getCellValue(cell);
        if (strZuid.equals("")) {
            List<Zu> zuList = caiwuCapitalMapper.findAllZuByID(villageId);
            if (zuList.size() > 1) {
                result.put("lackZuId", "缺少组id");
                return result;
            } else {
                zuId = zuList.get(0).getzKey();
            }
        } else {
            zuId = Integer.parseInt(strZuid.replace(".00", ""));
        }

        Village village = caiwuCapitalMapper.findVillageById(villageId);
        Integer month1 = 0;
        try {
            Capital capital = null;
            int capitalId = 0;
            String begMonthCapital = "";
            boolean flag1 = true;
            for (int rowNum = 4; rowNum <= sheet.getLastRowNum(); rowNum++) {
                row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                cell = row.getCell(0);
                Date date;
                if (cell != null && getCellValue(cell) != "") {
                    System.out.println(cell.getDateCellValue());
                    date = cell.getDateCellValue();
                } else {
                    continue;
                }
                String month2 = sdf.format(date).substring(5, 7);
                Integer month = Integer.parseInt(month2);
                String CapitalDetailsincome = "";//资金详情的本次现金收入
                String CapitalDetailsexpenditure = "";//资金详情的本次现金支出
                String content = "";
                String Captialincome;      //资金的本月现金收入
                String Captialexpenditure; //资金的本月现金支出
                if (month1 != month) {
                    capital = caiwuCapitalMapper.findCapitalimport(villageId, zuId, year, month, capitalStyle);
                    month1 = month;
                    if (capital == null) {
                        flag1 = true;
                        capital = new Capital();
                        capital.setcVillageid(villageId);
                        capital.setcType(village.getvName() + year1 + "年" + month + "月" + capitalStyle + "明细");
                        capital.setCdZu(zuId);
                        capital.setCdIncome(year1);
                        capital.setCdExpenditure(String.valueOf(month));
                        caiwuCapitalMapper.importCapital(capital);
                        capitalId = capital.getCapitalId();
                    } else {
                        flag1 = false;
                        capitalId = capital.getCapitalId();
                    }

                    boolean flag;
                    cell = row.getCell(2);
                    if (getCellValue(cell).equals("年初余额")) {
                        cell = row.getCell(6);
                        begMonthCapital = getCellValue(cell);
                        flag = true;
                    } else {
                        flag = false;
                    }
                    //添加月初余额
                    Capitaldetails begCapitaldetails = new Capitaldetails();
                    begCapitaldetails.setCdZijinid(capitalId);
                    begCapitaldetails.setCdZu(zuId);
                    begCapitaldetails.setCdXiangqing("月初余额");
                    String date0 = year1 + "-" + month1 + "-" + "01";
                    begCapitaldetails.setCdTime(sdf.parse(date0));
                    if (begMonthCapital != "") {
                        begCapitaldetails.setCdIncome(new BigDecimal(begMonthCapital));
                    }
                    if (flag1 == true) {
                        caiwuCapitalMapper.importCapitaldetails(begCapitaldetails);
                    }

                    if (flag == true) {
                        continue;
                    }
                }
                if (flag1 == false) {
                    continue;
                }
                cell = row.getCell(2);
                if (!getCellValue(cell).equals("本月合计")) {
                    content = getCellValue(cell);
                    begMonthCapital = "";
                } else {
                    cell = row.getCell(3);
                    Captialincome = getCellValue(cell);
                    cell = row.getCell(4);
                    Captialexpenditure = getCellValue(cell);
                    capital.setCdBeiyong1(Captialincome);
                    capital.setCdBeiyong2(Captialexpenditure);
                    caiwuCapitalMapper.updateCapital(capital);
                    cell = row.getCell(6);
                    begMonthCapital = getCellValue(cell);
                    continue;
                }
                cell = row.getCell(3);
                if (cell != null) {
                    CapitalDetailsincome = getCellValue(cell);
                }
                cell = row.getCell(4);
                if (cell != null) {
                    CapitalDetailsexpenditure = getCellValue(cell);
                }
                Capitaldetails capitaldetails = new Capitaldetails();
                capitaldetails.setCdZijinid(capitalId);
                capitaldetails.setCdZu(zuId);
                capitaldetails.setCdXiangqing(content);
                capitaldetails.setCdTime(date);
                if (CapitalDetailsincome != "") {
                    capitaldetails.setCdIncome(new BigDecimal(CapitalDetailsincome));
                }
                if (CapitalDetailsexpenditure != "") {
                    capitaldetails.setCdExpenditure(new BigDecimal(CapitalDetailsexpenditure));
                }
                caiwuCapitalMapper.importCapitaldetails(capitaldetails);
            }
            result.put("status", "成功");
        } catch (Exception e) {
            result.put("status", "出现错误");
            e.printStackTrace();
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public Map<String, Object> insertCapital(Capital capital) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (caiwuCapitalMapper.insertCapital(capital) > 0) {
                result.put("status", "success");
                result.put("data", "新增成功");
            } else {
                result.put("status", "null");
                result.put("data", "新增失败");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "新增出错");
        }
        return result;
    }

    @Override
    public Map<String, Object> insertCapitaldetails(Capitaldetails capitaldetails) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (caiwuCapitalMapper.insertCapitaldetails(capitaldetails) > 0) {
                result.put("status", "success");
                result.put("data", "新增成功");
            } else {
                result.put("status", "null");
                result.put("data", "新增失败");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("data", "新增出错");
        }
        return result;
    }

    public Map<String, Object> updateCapitaldetails(Capitaldetails capitaldetails) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (caiwuCapitalMapper.updateCapitaldetails(capitaldetails) > 0) {
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

    @Override
    public Map<String, Object> uploadFile(MultipartFile file, String path) {
        Map<String, Object> map = new HashMap<>();
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件的名称" + originalFileName);
        //获取文件的类型，以“.”作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
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
            map.put("type", type);
            return map;
        } catch (Exception e) {
            map.put("path", "");
            map.put("name", "此文件上传失败");
            e.printStackTrace();
        }
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
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).replaceAll(" ", "");
                break;
            case Cell.CELL_TYPE_FORMULA:
//                cellValue = cell.getCellFormula();
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
