package com.example.demo.controller;

import com.example.demo.advice.GlobalResponseAdvice;
import com.example.demo.data.EmployeeInfo;
import com.example.demo.service.EmployeeSevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "员工管理")
@GlobalResponseAdvice
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeSevice employeeSevice;

    @ApiOperation(value = "Get all employees", notes = "获取员工列表")
    @GetMapping("/employees")
    public List<EmployeeInfo> findALl() {
        return employeeSevice.findAll();
    }

    @PostMapping("/employees")
    public EmployeeInfo create(@RequestBody EmployeeInfo employeeInfo) {
        return employeeSevice.create(employeeInfo);
    }

    @GetMapping("/employees/{id}")
    public EmployeeInfo findById(@PathVariable Long id) {
        return employeeSevice.fineById(id);
    }

    @PutMapping("/employees/{id}")
    public EmployeeInfo update(@PathVariable Long id, @RequestBody EmployeeInfo employeeInfo) {
        return employeeSevice.update(id, employeeInfo);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeSevice.deleteEmployee(id);
    }

    @GetMapping("/error1")
    public List<EmployeeInfo> throwException() throws Exception {
        throw new Exception("手动抛出异常");
    }

    @GetMapping("/error2")
    public List<EmployeeInfo> throwRuntimeException() {
        throw new RuntimeException("手动抛出运行时异常");
    }


//    @Autowired
//    private EmployeeRepository repository;
//
//    @GetMapping("/employees")
//    List<Employee> finaALl() {
//        return repository.findAll();
//    }
//
//    @PostMapping("/employees")
//    Employee create(@RequestBody Employee employee) {
//        return repository.save(employee);
//    }
//
//    @GetMapping("/employees/{id}")
//    Employee findById(@PathVariable  Long id) {
//        return repository.getOne(id);
//    }
//
//    Employee update(@PathVariable Long id, @RequestBody Employee newEmployee) {
//        Employee employee = repository.getOne(id);
//        if (employee != null) {
//            employee.setName(newEmployee.getName());
//            employee.setRole(newEmployee.getRole());
//            return repository.save(employee);
//        }
//        return repository.save(newEmployee);
//    }
//
//    @DeleteMapping("/employees/{id}")
//    void deleteEmployee(@PathVariable Long id) {
//        repository.delete(id);
//    }
}
