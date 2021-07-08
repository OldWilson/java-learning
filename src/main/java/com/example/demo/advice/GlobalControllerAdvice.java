package com.example.demo.advice;

import com.alibaba.fastjson.JSON;
import com.example.demo.data.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.WebUtils;

import java.lang.annotation.Annotation;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice implements ResponseBodyAdvice<Object> {
    private static final Class<? extends Annotation> ANNOTATION_TYPE = GlobalResponseAdvice.class;

    /*
        拦截之前的业务处理，请求先到supports再到beforeBodyWrite
        自定义拦截，若类/方法名称（或其他维度的信息）在指定的常量范围之内，则不拦截
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        // 检查方法上或者类上的注解是否存在
        if (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE)
                && returnType.hasMethodAnnotation(ANNOTATION_TYPE)) {
            // 类上注解
            GlobalResponseAdvice classAdvice = returnType.getDeclaringClass().getAnnotation(GlobalResponseAdvice.class);
            // 方法上注解
            GlobalResponseAdvice methodAdvice = returnType.getMethod().getAnnotation(GlobalResponseAdvice.class);
            return classAdvice.value() && methodAdvice.value();
        } else if (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE)) {
            // 只有类上存在注解
            GlobalResponseAdvice responseAdvice = returnType.getDeclaringClass().getAnnotation(GlobalResponseAdvice.class);
            return responseAdvice.value();
        } else if (returnType.hasMethodAnnotation(ANNOTATION_TYPE)) {
            // 只有方法上存在注解
            GlobalResponseAdvice responseAdvice = returnType.getMethod().getAnnotation(GlobalResponseAdvice.class);
            return responseAdvice.value();
        } else {
            // 类和方法上都不存在注解
            return false;
        }
    }

    /*
        向客户端返回响应信息之前的业务逻辑处理
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 不包装文件类型
        if (body instanceof Resource) {
            return body;
        }

        // 防止重复包装
        if (body instanceof Result) {
            return body;
        }

        // 处理string类型的返回值
        if (body instanceof String) {
            return JSON.toJSONString(Result.success(body));
        }
        // 该方法返回的媒体类型是否是application/json。若不是，直接返回响应结果
        if (!selectedContentType.includes(MediaType.APPLICATION_JSON)) {
            return body;
        }
        return Result.success(body);
    }

    public final ResponseEntity<Result<?>> handleException(Exception e, WebRequest request) {
        log.error("handleException: ", e);
        HttpHeaders httpHeaders = new HttpHeaders();
        return this.handleException(e, httpHeaders, request);
    }

    public ResponseEntity<Result<?>> handleException(Exception e, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.failure();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(e, body, headers, status, request);
    }

    public ResponseEntity<Result<?>> handleExceptionInternal(Exception e, Result<?> body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
        }
        body.setDescription(e.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }
}
