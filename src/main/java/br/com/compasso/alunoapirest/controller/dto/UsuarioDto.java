package br.com.compasso.alunoapirest.controller.dto;

import br.com.compasso.alunoapirest.model.Sexo;
import br.com.compasso.alunoapirest.model.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

/**
 * Classe que define quais informacoes serao mostradas na consulta de um Usuario.
 *
 * Observacao: As Classes do pacote {@link br.com.compasso.alunoapirest.controller.dto}
 * tem o objetivo de definir/limitar os dados que sao enviados ao Usuario.
 * No caso, o Usuario pode visualizar todas os seus dados, com a senha criptografada.
 */
@ApiModel(description = "Formulario de resposta com as informacoes do Usuario consultado")
public class UsuarioDto {

    @ApiModelProperty(notes = "Identificador unico do Usuario", example = "1")
    private final Long id;
    @ApiModelProperty(example = "Joao da Silva")
    private final String nome;
    @ApiModelProperty(example = "joao.silva")
    private String usuario;
    @ApiModelProperty(example = "joao.silva@mail.com")
    private final String email;
    @ApiModelProperty(notes = "Senha criptografada")
    private final String senha;
    @ApiModelProperty(notes = "Formato: DDD-NNNNNNNNN", example = "48910102020")
    private final String telefone;
    @ApiModelProperty(example = "MASCULINO")
    private final Sexo sexo;
    @ApiModelProperty(notes = "Formato: DD/MM/YYYY", example = "01-01-1001")
    private final String dataNascimento;
    @ApiModelProperty(example = "30")
    private final Integer idade;
    @ApiModelProperty(notes = "Opcoes: 1 - aluno, 2 - admin", example = "1")
    private final String perfilTipo;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.usuario = usuario.getUsuario();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.telefone = usuario.getTelefone();
        this.sexo = usuario.getSexo();
        this.dataNascimento = usuario.getDataNascimento();
        this.idade = usuario.getIdade();
        this.perfilTipo = usuario.getPerfil().getTipo();
    }

    public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getPerfilTipo() {
        return perfilTipo;
    }
}
