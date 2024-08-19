package kr.co.common;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));

        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setErrorCode(CommonErrorCode.COMMON_FAIL.getCode());
        apiResponseMessage.setErrorMessage(new Gson().toJson(errors));
        return ResponseEntity.badRequest().body(apiResponseMessage);
    }

    @ExceptionHandler(MtnJwtException.class)
    public ResponseEntity<?> handleValidationExceptions(MtnJwtException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setErrorCode(ex.getCode());
        apiResponseMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseMessage);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleValidationExceptions(UsernameNotFoundException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setErrorCode(ex.getCode());
        apiResponseMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseMessage);
    }

    /**
     * RuntimeException 공통 Advice
     * @param ex
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<?> handleValidationExceptions(CommonException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setErrorCode(ex.getCode());
        apiResponseMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseMessage);
    }

    @ExceptionHandler(MtnLoginException.class)
    public ResponseEntity<?> handleValidationExceptions(MtnLoginException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setErrorCode(ex.getCode());
        apiResponseMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseMessage);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleValidationExceptions(AccessDeniedException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponseMessage);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<?> handleValidationExceptions(DisabledException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseMessage);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleValidationExceptions(BadCredentialsException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.PASSWORD_NOT_MATCH);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseMessage);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<?> handleValidationExceptions(LockedException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.LOCKED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseMessage);
    }

    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<?> handleValidationExceptions(AccountExpiredException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.EXPIRED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseMessage);
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<?> handleValidationExceptions(CredentialsExpiredException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.EXPIRED_PASSWORD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseMessage);
    }


    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<?> handleValidationExceptions(AuthenticationCredentialsNotFoundException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.NOT_FOUND_TOKEN);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponseMessage);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleValidationExceptions(HttpRequestMethodNotSupportedException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.METHOD_NOT_ALLOWED);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiResponseMessage);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleValidationExceptions(IOException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.IO_ERROR);

        log.warn("IOException 발생:", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseMessage);
    }

    @ExceptionHandler(MissingRequestCookieException.class)
    public ResponseEntity<?> handleValidationExceptions(MissingRequestCookieException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setError(CommonErrorCode.MISSING_COOKIE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseMessage);
    }

    @ExceptionHandler(MtnBillingException.class)
    public ResponseEntity<?> handleValidationExceptions(MtnBillingException ex){
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setErrorCode(ex.getCode());
        apiResponseMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseMessage);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> handleValidationExceptions(DateTimeParseException ex) {
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus("FAIL");
        apiResponseMessage.setMessage("실패");
        apiResponseMessage.setErrorCode(CommonErrorCode.INVALID_DATE_FORMAT.getCode());
        apiResponseMessage.setErrorMessage(CommonErrorCode.INVALID_DATE_FORMAT.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseMessage);
    }
}
