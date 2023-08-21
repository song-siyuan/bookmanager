package com.example.book.exception;

/**
 * @author songsiyuan
 * @date 2023/8/21 09 37
 * discription
 */
public class SystemException extends Exception{
    private Integer code;

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code, String message, Throwable cause) {
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
