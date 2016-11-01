package com.rollingstone.api.rest.docs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
*/


@Configuration
//@EnableSwagger
@ComponentScan("com.rollingstone.api.rest")
public class SwaggerConfig {

  /*  public static final String DEFAULT_INCLUDE_PATTERNS = "/userservice/.*";

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean 
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(DEFAULT_INCLUDE_PATTERNS);
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot userservice REST APIs",
                "The APIs here demonstrate a user REST microservice using Spring Boot 'production-ready' service built with Spring Boot and other helpful libraries",
                "http://opensource.org/licenses/MIT",
                "binitdatta@gmail.com",
                "MIT",
                "http://opensource.org/licenses/MIT"
        );
        return apiInfo;
    }*/
}
