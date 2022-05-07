package com.reine.store.service.ex;

/**
 * 用户名重复异常
 * @author reine
 * @since 2022/5/6 16:27
 */
public class UsernameDuplicateException extends ServiceException{
    public UsernameDuplicateException() {
        super();
    }

    public UsernameDuplicateException(String message) {
        super(message);
    }

    public UsernameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
