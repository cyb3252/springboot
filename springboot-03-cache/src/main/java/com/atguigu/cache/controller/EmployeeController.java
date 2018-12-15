package com.atguigu.cache.controller;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyb
 * @date 2018/11/24 - 15:12
 */
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable Integer id){
        Employee employee = employeeService.getEmployee(id);
        return employee;
    }

    /**
     * 这里为了方便，使用的是参数拼接的方式，来完成调用的
     * @param employee
     * @return
     */
    @GetMapping("/emp")
    public Employee updateEmp(Employee employee){
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastName/{lastName}")
    public Employee getEmpByLastName(@PathVariable String lastName){
        Employee emp = employeeService.getEmpByLastName(lastName);
        return emp;
    }
}
