package kr.co.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "관광 공모전 API 명세서",
                description = "관광 공모전 API 명세서 입니다@@",
                version = "v1"),
        //servers = {@Server(url = "https://api.s2it.kro.kr")}) //prod
        servers = {@Server(url = "/")}) //local
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    //test
    @Bean
    public GroupedOpenApi userGroupApi() {
        return GroupedOpenApi.builder()
                .group("2")
                .displayName("앱")
                .pathsToMatch("/api/v1/app/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminGroupApi() {
        return GroupedOpenApi.builder()
                .group("1")
                .displayName("웹")
                .pathsToMatch("/api/v1/**")
                .pathsToExclude("/api/v1/app/**")
                .build();
    }

    @Bean
    public OpenAPI initOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("관광 공모전 API")
                        .description("관광 공모전 API 명세서입니다.")
                        .version("v0.0.1"))
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(
                        new Components().addSecuritySchemes(
                                "Authorization",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT").name("Authorization")
                        ));
    }
}
