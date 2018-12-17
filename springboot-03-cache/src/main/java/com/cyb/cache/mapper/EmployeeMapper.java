package com.cyb.cache.mapper;

import com.cyb.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @author cyb
 * @date 2018/11/24 - 14:59
 */
@Mapper
public interface EmployeeMapper {
    @Select("select * from employee where id =#{id}")
    public Employee getEmpById (Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    public void updateEmp (Employee employee);

    @Delete("delete from employee where id =#{id}")
    public void deleteEmpById (Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmp (Employee employee);

    @Select("select * from employee where lastName =#{lastName}")
    Employee getEmpByLastName (String lastName);
}
