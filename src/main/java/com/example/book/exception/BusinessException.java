package com.example.book.exception;

/**
 * @author songsiyuan
 * @date 2023/8/21 09 33
 * discription 定义业务异常类
 */
public class BusinessException extends Exception {
    //定义业务异常代码
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
