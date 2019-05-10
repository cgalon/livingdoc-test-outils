package fr.pe.incub.mescomics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigurationDeSwagger  extends WebMvcConfigurationSupport {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.pe.incub.mescomics"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(ajouteLesMetadataDeLApplication());
    }

    private ApiInfo ajouteLesMetadataDeLApplication() {
        return new ApiInfoBuilder()
                .title("Gestion d'une collection de comics")
                .description("Application d'exemple de génération de documentation")
                .version("1.0.0")
                .contact(new Contact("Christophe Galon", "http://incubateur.git-scm.pole-emploi.intra", "christophe.galon@pole-emploi.fr"))
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
