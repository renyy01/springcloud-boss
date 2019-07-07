package com.camel.oa.config;

import com.camel.core.entity.Result;
import com.camel.core.enums.ResultEnum;
import com.camel.core.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/** @author baily */
@ControllerAdvice
@RestController
public class CommonExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    public Result numberFormatException(HttpServletResponse response){
        response.setStatus(ResultEnum.BAD_REQUEST.getCode());
        return ResultUtil.error(ResultEnum.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeException(RuntimeException e){
        return ResultUtil.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
