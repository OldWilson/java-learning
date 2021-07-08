package com.example.demo.service;

import com.example.demo.data.EmployeeInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeSevice {

    List<EmployeeInfo> findAll();

    /*
        @Transactional 注解应该只被应用到 public 方法上
        将标签放置在需要进行事务管理的方法上，而不是放在所有接口实现类上:只读的接口就不需要事务管理
     */
    @Transactional
    EmployeeInfo create(EmployeeInfo dto);

    EmployeeInfo fineById(Long id);

    @Transactional
    EmployeeInfo update(Long id, EmployeeInfo dto);

    @Transactional
    void deleteEmployee(Long id);
}
