/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.config;

import java.util.ArrayList;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiV1(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("appat-api-1.0")
                .apiInfo(getApiV1Info())
                .produces(Set.of(MediaType.APPLICATION_JSON_VALUE))
                //.consumes(Set.of(MediaType.APPLICATION_JSON_VALUE))
                .select()
                .apis(RequestHandlerSelectors.basePackage("pe.i2digital.app"))
                //.paths(PathSelectors.any())
                .paths(PathSelectors.ant("/api/v1/**"))
                .build();
    }
    public ApiInfo getApiV1Info(){
        return new ApiInfo(
                "Documentacion del REST Api - APP-AT",
                "Documentacion del API Rest de los recursos del backend de la plataforma APP-AT",
                "1.0",
                "urn:tos",
                new Contact("Omar Saavedra Salazar", "i2digital.pe", "osaavedras@unprg.edu.pe"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
