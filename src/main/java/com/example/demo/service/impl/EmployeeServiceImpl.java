package com.example.demo.service.impl;

import com.example.demo.data.EmployeeInfo;
import com.example.demo.domain.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeSevice {

    @Autowired
    private EmployeeRepository repository;

    private final String EMPOYEE_CACHE = "EMPLOYEE";

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeInfo> findAll() {
        List<Employee> entityList = repository.findAll();
        List<EmployeeInfo> dtoList = employeeMapper.toDTOList(entityList);
//        Map<Long, EmployeeInfo> map = dtoList.stream().collect(Collectors.toMap(EmployeeInfo::getId, item -> item));
        return dtoList;
    }

    @Override
    public EmployeeInfo create(EmployeeInfo dto) {
        Employee entity = repository.save(employeeMapper.toEntity(dto));
        return employeeMapper.toDTO(entity);
    }

    @Override
    public EmployeeInfo fineById(Long id) {
        EmployeeInfo dto = employeeMapper.toDTO(repository.getOne(id));
        return dto;
    }

    @Override
    public EmployeeInfo update(Long id, EmployeeInfo dto) {
        Employee entity = repository.getOne(id);
        Employee updEntity = null;
        if (entity != null) {
            entity.setName(dto.getName());
            entity.setRole(dto.getRole());
            updEntity = repository.save(entity);
        } else {
            updEntity = repository.save(employeeMapper.toEntity(dto));
        }
        return employeeMapper.toDTO(updEntity);
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.delete(id);
    }
}
