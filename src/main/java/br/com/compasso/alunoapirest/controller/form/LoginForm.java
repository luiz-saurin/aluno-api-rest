package br.com.compasso.alunoapirest.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;

/**
 * Classe que define quais informacoes devem ser fornecidas para realizar a autenticacao
 * do Usuario.
 *
 * Tambem pode realizar validacao dos dados recebidos utilizando as notacoes do
 * Spring Boot Validation. Exemplos: @Not Null, @NotEmpty, @Lengh, @Enumerated, @Email.
 *
 * Observacao: As Classes do pacote {@link br.com.compasso.alunoapirest.controller.form}
 * tem o objetivo de definir/limitar/validar os dados que sao recebidos do Usuario.
 */
@ApiModel(description = "Formulario de Login (Autenticacao)")
public class LoginForm {

    @ApiModelProperty(example = "joao.silva", required = true)
    @NotEmpty
    private String usuario;
    @ApiModelProperty(example = "joao123", required = true)
    @NotEmpty
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(usuario, senha);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
