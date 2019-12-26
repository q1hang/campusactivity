package com.campusactivity.common.exception;

/**
 * @Author q1hang
 * @Description 登录异常类
 * @create: 2019-12-12 21:18
 **/
public class SSOLoginException extends CustomException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 404;

    public SSOLoginException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public SSOLoginException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public SSOLoginException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public SSOLoginException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
