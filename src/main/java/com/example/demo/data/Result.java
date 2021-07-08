package com.example.demo.data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -2460242077362472193L;

    @ApiModelProperty(notes = "状态码")
    private String code;

    @ApiModelProperty(notes = "数据")
    private T data;

    @ApiModelProperty(notes = "描述")
    private String message;

    @ApiModelProperty(notes = "详细描述")
    private String description;

    public static Result<Void> success() {
        return new Result<>(ResultCode.SUCCESS.getCode(), null, null, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), data, null, null);
    }

    public static <T> Result<T> success(ResultCode resultCode, T data) {
        if (resultCode == null) {
            return success(data);
        }
        return new Result<>(resultCode.getCode(), data, null, null);
    }

    public static <T> Result<T> failure() {
        return new Result<>(ResultCode.INTERNAL_SERVER_ERROR.getCode(), null, ResultCode.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    public static <T> Result<T> failure(ResultCode resultCode) {
        if (resultCode == null) {
            return failure();
        }
        return new Result<>(resultCode.getCode(), null, resultCode.getMessage(), null);
    }

    public static <T> Result<T> failure(ResultCode resultCode, T data){
        if (resultCode == null) {
            return new Result<>(ResultCode.INTERNAL_SERVER_ERROR.getCode(), data, ResultCode.INTERNAL_SERVER_ERROR.getMessage(), null);
        }
        return new Result<>(resultCode.getCode(), data, resultCode.getMessage(), null);
    }

}
