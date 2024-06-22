package upc.edu.artcollab.api.shared.infrastructure.documentation.configuration.openapi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI agenciesOpenApi(){
        //General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("ArtCollab API")
                        .description("ArtCollab REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("ArtCollab Platform wiki Documentation")
                        .url("https://artocollab.wiki.github.io/docs"));

        return openApi;

    }

}