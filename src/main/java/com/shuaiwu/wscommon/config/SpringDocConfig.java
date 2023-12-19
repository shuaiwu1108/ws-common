package com.shuaiwu.wscommon.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    private String title = "后台管理系统";
    private String description = "后台管理系统API接口";
    private String version = "v1.0.0";
    private String websiteName = "后台管理系统";

    @Value("${doc.url:http://127.0.0.1:9090}")
    private String websiteUrl;

    @Bean
    public OpenAPI heroOpenAPI() {
        return new OpenAPI()
            .info(new Info().title(title)
                .description(description)
                .version(version))
            .externalDocs(new ExternalDocumentation().description(websiteName)
                .url(websiteUrl));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi(){
        return GroupedOpenApi.builder()
            .group(title)
            .pathsToMatch("/**")
            .build();
    }
}
