package br.com.compasso.alunoapirest.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * Classe de configuracao da documentacao do Swagger.
 *
 * Para acessar a pagina HTML da documentacao, acesse:
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigurations {

    @Bean
    public Docket alunoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.compasso.alunoapirest"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(getApiInfo())
                /*.ignoredParameterTypes(Usuario.class)*/
                .globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                        .name("Authorization")
                                        .description("Header para Token JWT")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(false)
                                        .build()));
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Alunos API REST")
                .description("Esta API faz o cadastro, consulta, alteração e exclusão de Alunos")
                .contact(new Contact("Nome do Contato", "www.contato.com", "contato@mail.com"))
                .version("1.0.0")
                .build();
    }
}
