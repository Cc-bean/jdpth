package com.hj.jdpth;

import com.github.pagehelper.Page;
import com.hj.jdpth.domain.Resources;
import com.hj.jdpth.domain.Village;
import com.hj.jdpth.repository.AssetsdetailsMapper;
import com.hj.jdpth.repository.ResourcesMapper;
import com.hj.jdpth.repository.SubsidynameMapper;
import com.hj.jdpth.repository.ZuzhihuodongSelectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Zuzhitest {
    @Autowired
    ZuzhihuodongSelectMapper zuzhihuodongSelectMapper;
    @Autowired
    SubsidynameMapper subsidynameMapper;
    @Autowired
    AssetsdetailsMapper assetsdetailsMapper;
    @Autowired
    ResourcesMapper resourcesMapper;

    @Test
    public void f() {
        List list = zuzhihuodongSelectMapper.GetInfoSelectByTime(1349, 6, 2, "2015-01-01", "2019-12-01");
        Page<Village> villages = zuzhihuodongSelectMapper.GetVillage(2);
        System.out.println(list.size());
        System.out.println(villages.size());

    }

    @Test
    public void h() {

        System.out.println(resourcesMapper.quaryResourcesStyle(45, 20, "土地资源"));
    }
}
