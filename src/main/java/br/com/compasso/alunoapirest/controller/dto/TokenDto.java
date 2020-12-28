package br.com.compasso.alunoapirest.controller.dto;

import io.swagger.annotations.ApiModel;

/**
 * Classe que envia ao Usuario seu Token de autenticacao.
 *
 * Essa classe serve apenas para fins de teste, pois nao é seguro enviar o token ao usuario
 * dessa maneira!
 */
@ApiModel(description = "Formulario de resposta, devolvendo o token de autenticacao ao Usuario logado")
public class TokenDto {

    private final String token;
    private final String tipo;

    public TokenDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
