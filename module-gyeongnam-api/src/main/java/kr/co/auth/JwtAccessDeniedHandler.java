package kr.co.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.results.ApiResponseMessage;
import kr.co.results.CommonErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setStatus(CommonErrorCode.FAIL.getCode());
        apiResponseMessage.setMessage(CommonErrorCode.FAIL.getMessage());
        apiResponseMessage.setErrorCode(String.valueOf(HttpStatus.FORBIDDEN.value()));
        apiResponseMessage.setErrorMessage(HttpStatus.FORBIDDEN.getReasonPhrase());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponseMessage));

    }
}
