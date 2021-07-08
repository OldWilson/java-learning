package com.example.demo.repository;

import com.example.demo.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EmployeeRepository {

    private static final ConcurrentHashMap<Long, Employee> employeeStorage = new ConcurrentHashMap<>();

    public Employee save(Employee employee) {
        employeeStorage.put(employee.getId(), employee);
        return employee;
    }

    public void delete(Long id) {
        Employee item = employeeStorage.get(id);
        if (item != null) {
            employeeStorage.remove(item.getId());
        }

    }

    public Employee getOne(Long id) {
        return employeeStorage.get(id);
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employeeStorage.values());
    }
}
