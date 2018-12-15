package com.atguigu.cache.mapper;

import com.atguigu.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author cyb
 * @date 2018/11/24 - 14:58
 */
@Mapper
public interface DepartmentMapper {
    @Select("SELECT * FROM department where id = #{id}")
    Department getDeptById (Integer id);
}
