package com.example.demo.data;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Data
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "All details about the Employee.")
public class EmployeeInfo extends BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5085736980638539613L;

    private Long id;

    @ApiModelProperty(notes = "Name should have at least 2 characters.")
    private String name;

    private String role;

    private String displayName;

    @JSONField(name = "build-number")
    @JsonProperty("build-number")
    private String buildNumber;
}
