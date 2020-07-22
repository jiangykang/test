package com.ls.system.config;

import com.ls.system.enums.RequestCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Min.Hu
 * Time: 2020/4/27 14:03
 * <p> 返回包装类
 * 返回的结果, 可以用此类包装
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public class ResponseResult {

    public int code;

    public String msg;

    public Object data;


    /**
     * 是否为第一页
     */
    private Boolean first;
    /**
     * 是否为尾页
     */
    private Boolean last;
    /**
     * 当前页（从0开始）
     */
    private Integer number;
    /**
     * 本页有多少条数据
     */
    private Integer numberOfElements;
    /**
     * 每页显示条数
     */
    private Integer size;


    private long totalElements;
    /**
     * 总页数
     */

    private Integer totalPages;


    public ResponseResult(int code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public ResponseResult() {
        super();
    }


    /**
     * 操作成功的返回
     *
     * @param data
     * @param requestcodeenum
     * @param msg
     * @return
     */
    public static ResponseResult success(Object data, RequestCodeEnum requestcodeenum, String msg) {

        ResponseResult ResponseResult = null;

        requestcodeenum = requestcodeenum == null ?
                RequestCodeEnum.CODE200 : requestcodeenum;

        Page page = new PageImpl(new ArrayList());

        //为null
        if (data == null) {
            ResponseResult = new ResponseResult(requestcodeenum.getCode(), msg, null);
        } else if (com.baomidou.mybatisplus.extension.plugins.pagination.Page.class.isAssignableFrom(data.getClass())) {
            com.baomidou.mybatisplus.extension.plugins.pagination.Page mybatisPage = (com.baomidou.mybatisplus.extension.plugins.pagination.Page) data;

            List records = mybatisPage.getRecords();

            long page2 = mybatisPage.getCurrent();

            long size2 = mybatisPage.getSize();

            long total = mybatisPage.getTotal();

            PageRequest pageable = PageRequest.of((int) page2 - 1, (int) size2);

            //PageRequest.of(page2, size2);

            page = new PageImpl(records, pageable, total);

            ResponseResult = new ResponseResult(requestcodeenum.getCode(), msg, page.getContent());
        } else if (Page.class.isAssignableFrom(data.getClass())) {
            page = (Page) data;
            ResponseResult = new ResponseResult(requestcodeenum.getCode(), msg, page.getContent());
        } else if (!Page.class.isAssignableFrom(data.getClass())) {
            ResponseResult = new ResponseResult(requestcodeenum.getCode(), msg, data);
        }

        BeanUtils.copyProperties(page, ResponseResult);

        return ResponseResult;
    }


    public static ResponseResult success(Object data, String msg) {
        return success(data, RequestCodeEnum.CODE200, msg);
    }


    public static ResponseResult success(Object data, RequestCodeEnum requestcodeenum) {
        return success(data, requestcodeenum, requestcodeenum.getReasonPhrase());
    }


    public static ResponseResult success(RequestCodeEnum requestcodeenum, String msg) {
        return success(null, requestcodeenum, msg);
    }

    public static ResponseResult success(RequestCodeEnum requestcodeenum) {
        return success(null, requestcodeenum, requestcodeenum.getReasonPhrase());
    }

    public static ResponseResult success(Object data) {
        return success(data, RequestCodeEnum.CODE200);
    }

    public static ResponseResult successMsg(String msg) {
        return success(null, RequestCodeEnum.CODE200, msg);
    }

    public static ResponseResult success() {
        return success(null, RequestCodeEnum.CODE200);
    }

    /**
     * 鉴权失败 返回值
     *
     * @param data
     * @param requestcodeenum
     * @param msg
     * @return
     */
    public static ResponseResult auth(Object data, RequestCodeEnum requestcodeenum, String msg) {
        ResponseResult ResponseResult = null;
        requestcodeenum = requestcodeenum == null ?
                RequestCodeEnum.CODE401 : requestcodeenum;
        //为null
        if (data == null) {
            ResponseResult = new ResponseResult(requestcodeenum.getCode(), msg, null);
        }

        return ResponseResult;
    }


    public static ResponseResult auth(Object data, String msg) {
        return auth(data, RequestCodeEnum.CODE401, msg);
    }


    public static ResponseResult auth(Object data, RequestCodeEnum requestcodeenum) {
        return auth(data, requestcodeenum, requestcodeenum.getReasonPhrase());
    }


    public static ResponseResult auth(RequestCodeEnum requestcodeenum, String msg) {
        return auth(null, requestcodeenum, msg);
    }

    public static ResponseResult auth(RequestCodeEnum requestcodeenum) {
        return auth(null, requestcodeenum, requestcodeenum.getReasonPhrase());
    }

    public static ResponseResult auth(Object data) {
        return success(data, RequestCodeEnum.CODE401);
    }

    public static ResponseResult auth(String msg) {
        return success(null, RequestCodeEnum.CODE401, msg);
    }

    public static ResponseResult auth() {
        return success(null, RequestCodeEnum.CODE401);
    }

    /**
     * 无权限 返回值
     *
     * @param data
     * @param requestcodeenum
     * @param msg
     * @return
     */
    public static ResponseResult permission(Object data, RequestCodeEnum requestcodeenum, String msg) {
        ResponseResult ResponseResult = null;
        requestcodeenum = requestcodeenum == null ?
                RequestCodeEnum.CODE403 : requestcodeenum;
        //为null
        if (data == null) {
            ResponseResult = new ResponseResult(requestcodeenum.getCode(), msg, null);
        }

        return ResponseResult;
    }


    public static ResponseResult permission(Object data, String msg) {
        return permission(data, RequestCodeEnum.CODE403, msg);
    }


    public static ResponseResult permission(Object data, RequestCodeEnum requestcodeenum) {
        return permission(data, requestcodeenum, requestcodeenum.getReasonPhrase());
    }


    public static ResponseResult permission(RequestCodeEnum requestcodeenum, String msg) {
        return permission(null, requestcodeenum, msg);
    }

    public static ResponseResult permission(RequestCodeEnum requestcodeenum) {
        return permission(null, requestcodeenum, requestcodeenum.getReasonPhrase());
    }

    public static ResponseResult permission(Object data) {
        return success(data, RequestCodeEnum.CODE403);
    }

    public static ResponseResult permission(String msg) {
        return success(null, RequestCodeEnum.CODE403, msg);
    }

    public static ResponseResult permission() {
        return success(null, RequestCodeEnum.CODE403);
    }


    /**
     * 操作失败的返回
     *
     * @param data
     * @param requestcodeenum
     * @param msg
     * @return
     */
    public static ResponseResult failure(Object data, RequestCodeEnum requestcodeenum,
                                         String msg) {


        requestcodeenum = requestcodeenum == null ?
                RequestCodeEnum.CODE500 : requestcodeenum;

        ResponseResult ResponseResult = new ResponseResult(requestcodeenum.getCode(), msg, data);

        if (data != null && Page.class.isAssignableFrom(data.getClass())) {
            BeanUtils.copyProperties(data, ResponseResult);
        }
        return ResponseResult;
    }

    public static ResponseResult failure(Object data, String msg) {
        return failure(data, RequestCodeEnum.CODE500, msg);
    }

    public static ResponseResult failure(Object data, RequestCodeEnum requestcodeenum) {
        return failure(data, requestcodeenum, requestcodeenum.getReasonPhrase());
    }

    public static ResponseResult failure(RequestCodeEnum requestcodeenum, String msg) {
        return failure(null, requestcodeenum, msg);
    }

    public static ResponseResult failure(RequestCodeEnum requestcodeenum) {
        return failure(null, requestcodeenum, requestcodeenum.getReasonPhrase());
    }

    public static ResponseResult failure(Object data) {
        return failure(data, RequestCodeEnum.CODE500);
    }

    public static ResponseResult failure(String msg) {
        return failure(null, RequestCodeEnum.CODE500, msg);
    }

    public static ResponseResult failure() {
        return failure(null, RequestCodeEnum.CODE500);
    }

    public Boolean isSuccess() {
        return this.getCode() == 200;
    }

    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public Boolean getFirst() {
        return first;
    }


    public void setFirst(Boolean first) {
        this.first = first;
    }


    public Boolean getLast() {
        return last;
    }


    public void setLast(Boolean last) {
        this.last = last;
    }


    public Integer getNumber() {
        if (number != null) {
            return number + 1;
        }
        return number;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }


    public Integer getNumberOfElements() {
        return numberOfElements;
    }


    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }


    public Integer getSize() {
        return size;
    }


    public void setSize(Integer size) {
        this.size = size;
    }


    public Integer getTotalPages() {
        return totalPages;
    }


    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }


    public long getTotalElements() {
        return totalElements;
    }


    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }


}
