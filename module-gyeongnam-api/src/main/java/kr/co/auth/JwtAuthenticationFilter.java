package kr.co.auth;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.results.CommonErrorCode;
import kr.co.results.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String userId = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            try {
                userId = jwtUtil.extractUsername(jwt);
            }catch(IllegalArgumentException e){
                logger.error("error occured during getting username from token!", e);
                throw new CommonException(CommonErrorCode.INVALID_TOKEN.getCode(), CommonErrorCode.INVALID_TOKEN.getMessage());
            }catch(ExpiredJwtException e){
                logger.warn("the token is expired and not valid anymore!", e);
                throw new CommonException(CommonErrorCode.EXPIRED_TOKEN.getCode(), CommonErrorCode.EXPIRED_TOKEN.getMessage(), e);
            }catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
                throw new CommonException(CommonErrorCode.AUTHENTICATION_FAILED.getCode(), CommonErrorCode.AUTHENTICATION_FAILED.getMessage(), e);
            }catch(MalformedJwtException e){
                logger.error("the token is not valid!", e);
                throw new CommonException(CommonErrorCode.WRONG_TOKEN.getCode(), CommonErrorCode.WRONG_TOKEN.getMessage(), e);
            }
        }else{
            logger.warn("couldn't find bearer string, will ignore the header");
        }


        if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailService.loadUserByUsername(userId);
            if (jwtUtil.validateToken(jwt,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null,null);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request,response);
    }
}
