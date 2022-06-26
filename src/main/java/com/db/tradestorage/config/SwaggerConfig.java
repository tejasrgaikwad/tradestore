package com.db.tradestorage.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Message Board API")
                .description("Swagger API for Rule Engine")
                .termsOfServiceUrl("http://example.com")
                .contact("tejasrgaikwad@gmail.com").license("Test License")
                .licenseUrl("tejasrgaikwad@gmail.com").version("1.0").build();
    }


        @Bean
        public LinkDiscoverers discoverers() {
            List<LinkDiscoverer> plugins = new ArrayList<>();
            plugins.add(new CollectionJsonLinkDiscoverer());
            return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

        }

}