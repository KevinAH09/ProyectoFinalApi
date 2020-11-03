/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto;

import static io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation.HEADER;
import java.util.Collections;
import static java.util.Collections.singletonList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author colo7
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                )
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("org.una.aeropuerto.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Seguridad", "Metodos de Seguridad") {
                },
                        new Tag("Roles", "Metodos de Roles") {
                },
                        new Tag("Aerolineas", "Metodos de aerolineas") {
                },
                        new Tag("Parametros del sistema", "Metodos de parametros del sistema") {
                },
                        new Tag("Aviones", "Metodos de aviones") {
                },
                        new Tag("Zonas", "Metodos de zonas") {
                },
                        new Tag("Zonas y Aviones", "Metodos de zonas y aviones") {
                },
                        new Tag("Vuelos", "Metodos de vuelos") {
                },
                        new Tag("Bitacora de vuelos", "Metodos de bitacoras de vuelos") {
                },
                        new Tag("Area de trabajo", "Metodos de area de trabajo") {
                },
                        new Tag("Registro de acciones", "Metodos de registro de acciones") {
                },
                        new Tag("Control de gastos de mantenimiento", "Metodos sobre control de gastos de mantenimiento") {
                },
                        new Tag("Detalle de control de gastos de mantenimiento", "Metodos sobre detalle de control de gastos de mantenimiento") {
                },  
                        new Tag("Reportes", "Metodos sobre reportes") {
                },  
                        new Tag("Usuarios", "Entidad de Usuarios")
                );

    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Administracion del AeropuertoUNA",
                "Rest API sobre la administracion del AeropuertoUNA.",
                "Proyecto progamacion III grupo6. Versión:2.0.0",
                "https://google.com",
                new Contact("UNA Sede Región Brunca", "https://srb.una.ac.cr/index.php/es/", "decanatosrb@una.cr"),
                "Apache-2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}
