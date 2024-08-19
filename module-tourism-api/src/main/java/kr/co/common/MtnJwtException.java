package kr.co.common;

import lombok.Getter;

@Getter
public class MtnJwtException extends RuntimeException {

    private String code;

    public MtnJwtException(String code, String message) {
        super(message);
        this.code = code;
    }

    public MtnJwtException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
