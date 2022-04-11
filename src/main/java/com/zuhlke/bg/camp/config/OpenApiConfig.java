package com.zuhlke.bg.camp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Zoo Reception API")
                        .description("Zoo Reception application for Camp Purposes")
                        .version("v1.0.0")
                ).tags(List.of(
                        OpenApiTags.GUIDE_OPERATIONS.getTag(),
                        OpenApiTags.CHARITY_OPERATIONS.getTag()
                ));
    }
}
