package com.example.demo.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = -689385441590607092L;

    private Long id;

    private String name;

    private String role;
}
