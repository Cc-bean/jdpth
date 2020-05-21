package com.hj.jdpth.repository;

import com.hj.jdpth.domain.Department;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "departmentMapper")
public interface DepartmentMapper {

    //查询职能部门
    @Select("select * from department")
    List<Department> queryAllDp();
}
