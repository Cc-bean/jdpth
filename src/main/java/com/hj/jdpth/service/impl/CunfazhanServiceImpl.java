package com.hj.jdpth.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hj.jdpth.domain.Cunfazhan;
import com.hj.jdpth.repository.CunfazhanMapper;
import com.hj.jdpth.service.CunfazhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CunfazhanServiceImpl implements CunfazhanService {
    @Autowired
    CunfazhanMapper cunfazhanMapper;

    @Override
    public Map<String, Object> findCunFaZhan(Integer style, Integer qu_id, Integer zhen_id, Integer cun_id, Integer startPage, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        if (style == 2 || style == 3) {
            System.out.println("进入区级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cunfazhan;
                if (cun_id != 0) {
                    cunfazhan = cunfazhanMapper.findfaZhanByCunId(cun_id);
                } else if (zhen_id != 0) {
                    cunfazhan = cunfazhanMapper.findfaZhanByZhenId(zhen_id);
                } else {
                    cunfazhan = cunfazhanMapper.findfaZhanByRegionId(qu_id);
                }
                if (cunfazhan.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cunfazhan);
                    //总页数
                    result.put("total", cunfazhan.getPages());
                    //记录总数
                    result.put("count", cunfazhan.getTotal());
                    //当前第几页
                    result.put("nowPage", cunfazhan.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 4) {
            System.out.println("进入镇级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cunfazhan;
                if (cun_id != 0) {
                    cunfazhan = cunfazhanMapper.findfaZhanByCunId(cun_id);
                } else {
                    cunfazhan = cunfazhanMapper.findfaZhanByZhenId(zhen_id);
                }
                if (cunfazhan.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cunfazhan);
                    //总页数
                    result.put("total", cunfazhan.getPages());
                    //记录总数
                    result.put("count", cunfazhan.getTotal());
                    //当前第几页
                    result.put("nowPage", cunfazhan.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        } else if (style == 5) {
            System.out.println("进入村级");
            try {
                PageHelper.startPage(startPage, pageSize);
                Page<Map<String, Object>> cunfazhan = cunfazhanMapper.findfaZhanByCunId(cun_id);
                if (cunfazhan.isEmpty()) {
                    result.put("status", "null");
                    result.put("data", "没有数据");
                } else {
                    result.put("status", "success");
                    result.put("data", cunfazhan);
                    //总页数
                    result.put("total", cunfazhan.getPages());
                    //记录总数
                    result.put("count", cunfazhan.getTotal());
                    //当前第几页
                    result.put("nowPage", cunfazhan.getPageNum());
                }
            } catch (Exception e) {
                result.put("status", "error");
                result.put("data", "查询出错");
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> findFazhanXingQing_yp(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> fazhanxiangqing = cunfazhanMapper.findFazhanXingQing_yp(id);
        result.put("fazhan", fazhanxiangqing);
        return result;
    }

    @Override
    public Map<String, Object> deleteFazhanById_yp(Integer id) {
        Map<String, Object> result = new HashMap<>();
        Boolean flag = cunfazhanMapper.deleteFazhanById_yp(id);
        result.put("flag", flag);
        return result;
    }

    @Override
    public Map<String, Object> deleteManyFazhanById_yp(String Ids) {
        String[] ids = Ids.split(",");
        Map<String, Object> result = new HashMap<>();
        for (String id : ids) {
            Boolean flag = cunfazhanMapper.deleteFazhanById_yp(Integer.parseInt(id));
            if (flag == true) {
                if (result.get("success") != null) {
                    result.put("success", id + "," + result.get("success"));
                } else {
                    result.put("success", id);
                }
            } else {
                if (result.get("error") != null) {
                    result.put("error", id + "," + result.get("error"));
                } else {
                    result.put("error", id);
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> insertFazhan_yp(Cunfazhan cunfazhan) {
        Map<String, Object> result = new HashMap<>();
        int i = cunfazhanMapper.insertFazhan_yp(cunfazhan);
        if (i > 0) {
            result.put("result", i);
        } else {
            result.put("result", i);
        }
        return result;
    }

    @Override
    public Map<String, Object> updateFazhan_yp(Cunfazhan cunfazhan) {
        Map<String, Object> result = new HashMap<>();
        Boolean flag = cunfazhanMapper.updateFazhan_yp(cunfazhan);
        if (flag == true) {
            result.put("result", "更新成功");
        } else {
            result.put("result", "更新失败");
        }
        return result;
    }

    @Override
    public Boolean findFazhanByxuhao_yp(String cfzXuhao) {
        if (cunfazhanMapper.findFazhanByxuhao_yp(cfzXuhao) != null) {
            return false;
        } else {
            return true;
        }
    }
}
