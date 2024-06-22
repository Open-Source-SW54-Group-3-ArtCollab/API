package upc.edu.artcollab.api.shared.infrastructure.documentation.openapi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration class.
 * This class is responsible for configuring the OpenAPI documentation for the application.
 */

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI artcollabPlatformOpenApi() {
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("ArtCollab Platform API - UPC")
                        .description("ArtCollab Platform application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("ArtCollab Platform wiki Documentation")
                        .url("https://artcollab-platform.wiki.github.io/docs"));
        return openApi;
    }
}
