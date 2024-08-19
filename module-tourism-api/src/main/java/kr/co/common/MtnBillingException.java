package kr.co.common;

import lombok.Getter;

@Getter
public class MtnBillingException extends RuntimeException {
    private final String code;

    public MtnBillingException(String code, String message) {
        super(message);
        this.code = code;
    }

    public MtnBillingException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
