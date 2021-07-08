package com.example.demo.mapper;

import com.example.demo.data.EmployeeInfo;
import com.example.demo.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    EmployeeInfo toDTO(Employee entity);

    Employee toEntity(EmployeeInfo dto);

    List<EmployeeInfo> toDTOList(List<Employee> list);

    List<Employee> toEntityList(List<EmployeeInfo> list);
}
