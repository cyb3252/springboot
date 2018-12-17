package com.cyb.cache.service;

import com.cyb.cache.bean.Department;
import com.cyb.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author cyb
 * @date 2018/12/14 - 11:25
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Cacheable(cacheNames = "dept")
    public Department getDeptById (Integer id) {
        Department department = departmentMapper.getDeptById(id);
        return department;
    }
}
