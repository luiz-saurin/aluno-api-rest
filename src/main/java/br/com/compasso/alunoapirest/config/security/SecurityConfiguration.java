package br.com.compasso.alunoapirest.config.security;

import br.com.compasso.alunoapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe que implementa as configuracoes de seguranca do Spring Boot Web Secutiry.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * Configuracoes de autenticacao
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Configuracoes de autorizacao.
     *
     * A configuracao inicial, esta da seguinte forma:
     * -> Requisicoes HTTP GET para qualquer URL /usuarios esta liberada
     * -> A requisicao HTTP POST para a URL /autenticacao esta liberada para
     * permitir o Usuario realizar a autenticacao
     *
     * Para fins de testes e facilitar o desenvolvimento da API, foi configurado:
     * -> Acesso ao monitoramento pelo /actuator foi liberado
     * - Comando: .antMatchers(HttpMethod.GET, "/actuator").permitAll()
     * -> Acesso ao banco de dados em memoria /h2-console foi liberado
     * - Comando: .antMatchers("/h2-console/**").permitAll().and().headers().frameOptions().sameOrigin()
     *
     * Qualquer outra requisicao alem das listadas acima precisa de autenticacao.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
                .antMatchers(HttpMethod.POST, "/autenticacao").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().frameOptions().sameOrigin()
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class)
        ;
    }

    /**
     * Configuracoes de recursos estaticos (.js, .css, imagens, .html)
     *
     * A configuracao feita abaixo sever para visualizacao da pagina html
     * da documentacao Swagger.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/**.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/configuration/**",
                        "/swagger-resources/**")
        ;
    }
}
