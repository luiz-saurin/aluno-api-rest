package br.com.compasso.alunoapirest.controller;

import br.com.compasso.alunoapirest.config.security.TokenService;
import br.com.compasso.alunoapirest.controller.dto.TokenDto;
import br.com.compasso.alunoapirest.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Classe de controle de autenticacao de Usuarios.
 *
 * Para fins de teste, a autenticacao devolve no corpo da requisicao Http o
 * Token de autenticacao e o Tipo do token. Este token sera utilizado para realizar as
 * operacoes de Cadastro de Usuario, Atualizacao e Remocao.
 */
@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    /**
     * Metodo POST para autenticacao de Usuario.
     * A Classe {@link LoginForm} define as informacoes que devem ser fornecidas para
     * efetuar a autenticacao.
     *
     * @return Token de autenticacao e o Tipo do token.
     */
    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
