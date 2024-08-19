package kr.co.common;

import lombok.Getter;

@Getter
public class MtnLoginException extends RuntimeException {

    private final String code;

    public MtnLoginException(String code, String message) {
        super(message);
        this.code = code;
    }

    public MtnLoginException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
