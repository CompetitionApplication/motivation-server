package kr.co.common;

import lombok.Getter;

@Getter
public class MtnCommonException extends RuntimeException {
    private final String code;

    public MtnCommonException(String code, String message) {
        super(message);
        this.code = code;
    }

    public MtnCommonException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
