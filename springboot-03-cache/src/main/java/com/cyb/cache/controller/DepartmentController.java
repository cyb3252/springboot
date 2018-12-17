package com.cyb.cache.controller;

import com.cyb.cache.bean.Department;
import com.cyb.cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyb
 * @date 2018/12/14 - 11:29
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/get/{id}")
    public Department getDept (@PathVariable Integer id) {
        Department deptById = departmentService.getDeptById(id);
        return deptById;
    }
}
