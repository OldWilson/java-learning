package com.example.demo.config;

import com.example.demo.data.EmployeeInfo;
import com.example.demo.service.EmployeeSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadInitData {

    @Bean
    CommandLineRunner initDatabase(EmployeeSevice employeeSevice) {
        return args -> {
            EmployeeInfo employeeInfo = new EmployeeInfo();
            employeeInfo.setName("zefeng zhang");
            employeeInfo.setRole("admin");
            employeeInfo.setDisplayName("Display zefeng zhang");
            employeeInfo.setId(100001L);
            log.info("Preloading: {}.", employeeSevice.create(employeeInfo));

            employeeInfo = new EmployeeInfo();
            employeeInfo.setName("Jack Jhons");
            employeeInfo.setRole("member");
            employeeInfo.setDisplayName("Display Jack Jhons");
            employeeInfo.setId(100002L);
            log.info("Proloading: {}.", employeeSevice.create(employeeInfo));
        };
    }
}
