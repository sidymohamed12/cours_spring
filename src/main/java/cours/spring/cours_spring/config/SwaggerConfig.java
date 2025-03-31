package cours.spring.cours_spring.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Value("${spring.application.version}")
    private String version;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cours Spring API")
                        .version("1.0.0")
                        .description("API pour gérer une boutique en ligne"));
    }

    // @Bean
    // public GroupedOpenApi groupedOpenApi() {
    // return GroupedOpenApi.builder().group("gestion boutique cours spring")
    // .build();
    // }
}
