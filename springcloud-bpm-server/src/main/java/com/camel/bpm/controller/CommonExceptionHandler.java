package com.camel.bpm.controller;

import com.camel.core.utils.ResultUtil;
import com.camel.core.entity.Result;
import com.camel.bpm.exceptions.ProcessNotFoundException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author baily
 */
@ControllerAdvice
@RestController
public class CommonExceptionHandler {

    @ExceptionHandler(ActivitiObjectNotFoundException.class)
    public Result numberFormatException(HttpServletResponse response){
        return ResultUtil.processNotFound("未找到相关流程，或该流程已经流转完成");
    }

    @ExceptionHandler(ProcessNotFoundException.class)
    public Result processNotFoundException(){
        return ResultUtil.processNotFound("未找到相关流程，或该流程已经流转完成");
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeException(RuntimeException e){
        return ResultUtil.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
