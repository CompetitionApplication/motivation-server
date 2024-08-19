package kr.co.common;

import lombok.Getter;

@Getter
public class UsernameNotFoundException extends RuntimeException {
    private final String code;

    public UsernameNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public UsernameNotFoundException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
