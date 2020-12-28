package br.com.compasso.alunoapirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe principal (inicial) de execucao da API.
 */
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableSwagger2
public class AlunoApiRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlunoApiRestApplication.class, args);
    }

}
