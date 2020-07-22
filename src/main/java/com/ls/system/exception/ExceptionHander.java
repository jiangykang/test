package com.ls.system.exception;

import com.ls.system.config.ResponseResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Min.Hu
 * Time: 2019/12/16 16:26
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionHander {

    @ExceptionHandler(RuntimeException.class)
    public ResponseResult RuntimeException(Exception ex) {
        /**
         * 描述
         */
        String message = ex.getMessage();
        /**
         * 返回值
         */
        log.debug(message);
        return ResponseResult.failure(message);
    }

    /**
     * @功能描述: 传值验证
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseResult MethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        /**
         * 描述
         */
        String message = ex.getMessage();
        /**
         * 返回值
         */
        log.debug(message);
        List<ErrorValid> valids = new ArrayList<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError obj : allErrors
        ) {
            ErrorValid errorValid = new ErrorValid();
            errorValid.setObjectName(obj.getObjectName());
            errorValid.setDefaultMessage(obj.getDefaultMessage());
            valids.add(errorValid);
        }

        return ResponseResult.failure(valids, "参数格式不正确或校验不通过");
    }

    /**
     * @功能描述: body值为空
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseResult HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        /**
         * 返回值
         */
        log.debug("参数错误");
        return ResponseResult.failure("参数错误");
    }

    /**
     * @功能描述: 密码错误
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {IncorrectCredentialsException.class})
    @ResponseBody
    public ResponseResult IncorrectCredentialsException(IncorrectCredentialsException ex) {
        ex.printStackTrace();
        log.debug("密码错误");
        return ResponseResult.failure("密码错误");
    }

    /**
     * @功能描述: 登录失败，该用户已被冻结
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {LockedAccountException.class})
    @ResponseBody
    public ResponseResult LockedAccountException(LockedAccountException ex) {
        ex.printStackTrace();
        log.debug("登录失败，该用户已被冻结");
        return ResponseResult.failure("登录失败，该用户已被冻结");
    }


    /**
     * @功能描述: 该用户不存在
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseBody
    public ResponseResult AuthenticationException(AuthenticationException ex) {
        ex.printStackTrace();
        log.debug("该用户不存在");
        return ResponseResult.failure("该用户不存在");
    }

    /**
     * @功能描述: token错误
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {UnauthenticatedException.class})
    @ResponseBody
    public ResponseResult UnauthenticatedException(UnauthenticatedException ex) {
        ex.printStackTrace();
        log.debug("token错误");
        return ResponseResult.auth("认证信息有误或失效");
    }


    /**
     * @功能描述: token错误
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseBody
    public ResponseResult UnauthorizedException(UnauthorizedException ex) {
        ex.printStackTrace();
        log.debug("用户无权限");
        return ResponseResult.permission("用户无权限");
    }


    /**
     * @功能描述: 空指针异常
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseBody
    public ResponseResult NullPointerException(NullPointerException ex) {
        ex.printStackTrace();
        /**
         * 返回值
         */
        log.debug("空指针异常！");
        return ResponseResult.failure(ex.getMessage(),"系统异常！");
    }

    /**
     * @功能描述: mybatis 异常
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {MyBatisSystemException.class})
    @ResponseBody
    public ResponseResult MyBatisSystemException(MyBatisSystemException ex) {
        ex.printStackTrace();
        /**
         * 返回值
         */
        log.debug("mybatis系统异常！");
        return ResponseResult.failure(ex.getMessage(),"系统异常！");
    }

    /**
     * @功能描述: sql 异常
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {SQLException.class})
    @ResponseBody
    public ResponseResult SQLException(SQLException ex) {
        ex.printStackTrace();
        /**
         * 返回值
         */
        log.debug("sql系统异常！");
        return ResponseResult.failure(ex.getMessage(),"系统异常！");
    }

    /**
     * @功能描述: sql 异常
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    @ResponseBody
    public ResponseResult MissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ex.printStackTrace();
        /**
         * 返回值
         */
        log.debug("sql系统异常！");
        return ResponseResult.failure(ex.getParameterName() + "不能为空");
    }

    /**
     * @功能描述: 所有异常
     * @param: 异常
     * @return:
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseResult Exception(Exception ex) {
        ex.printStackTrace();
        /**
         * 返回值
         */
        log.debug("系统异常！");
        return ResponseResult.failure(ex.getMessage(),"系统异常！");
    }

    @Data
    public class ErrorValid {
        String objectName;
        String defaultMessage;
    }

}
