package kr.co.config;

import kr.co.auth.JwtAccessDeniedHandler;
import kr.co.auth.JwtAuthenticationEntryPoint;
import kr.co.auth.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] permitUrl = {"/api/v1/common/file/**","/api/v1/admin/login/**","/api/v1/external/**", "/api/v1/app/user/login/**", "/api/v1/app/user/join/**","/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**"};
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(permitUrl)
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        //configuration.addAllowedOrigin("http://admin.s2it.kro.kr"); // https://s2it.kro.kr 에서 시작하는 요청은 여기서 설정한 CORS 규칙을 따릅니다.
        //configuration.addAllowedOrigin("*"); // https://s2it.kro.kr 에서 시작하는 요청은 여기서 설정한 CORS 규칙을 따릅니다.
        configuration.addAllowedOrigin("http://localhost:3000"); // http://localhost:3001 에서 시작하는 요청은 여기서 설정한 CORS 규칙을 따릅니다.
        configuration.addAllowedOrigin("http://badgechallenge.kro.kr/"); // http://localhost:3001 에서 시작하는 요청은 여기서 설정한 CORS 규칙을 따릅니다.
        //configuration.addAllowedOrigin("https://api.s2it.kro.kr"); // http://localhost:3001 에서 시작하는 요청은 여기서 설정한 CORS 규칙을 따릅니다.
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
