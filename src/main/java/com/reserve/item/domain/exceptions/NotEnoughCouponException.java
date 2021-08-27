package com.reserve.item.domain.exceptions;

public class NotEnoughCouponException extends RuntimeException{
    public NotEnoughCouponException() {
        super();
    }

    public NotEnoughCouponException(String message) {
        super(message);
    }

    public NotEnoughCouponException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughCouponException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughCouponException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
