package kr.co.config;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration implements AuditorAware<String> {
    /**
     * 현재 사용자를 가져온다. 익명 사용자인 경우 SYSTEM 을 리턴한다.
     * @return 현재 로그인된 사용자
     */
    @Override
    public @NotNull Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getName)
                .filter(name -> !name.equals("anonymousUser"))
                .orElse("SYSTEM").describeConstable();
    }
}
