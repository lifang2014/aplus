package com.aplus.exception;


/**
 * Created by lifang on 2015/1/22.
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    /**
     * 格式划异常信息,支持占位符字符串
     * @param message
     * @param params
     */
    public ServiceException(String message,Object... params) {
        super(String.format(message, params));
    }
}
