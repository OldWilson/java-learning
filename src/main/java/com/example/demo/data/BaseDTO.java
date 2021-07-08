package com.example.demo.data;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class BaseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4749715180766325606L;
}
