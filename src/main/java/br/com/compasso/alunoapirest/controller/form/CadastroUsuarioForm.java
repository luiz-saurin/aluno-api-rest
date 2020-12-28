package br.com.compasso.alunoapirest.controller.form;

import br.com.compasso.alunoapirest.model.Perfil;
import br.com.compasso.alunoapirest.model.Sexo;
import br.com.compasso.alunoapirest.model.Usuario;
import br.com.compasso.alunoapirest.repository.PerfilRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Classe que define quais informacoes devem ser fornecidas para realizar a cadastro
 * de um novo Usuario.
 *
 * Tambem pode realizar validacao dos dados recebidos utilizando as notacoes do
 * Spring Boot Validation. Exemplos: @Not Null, @NotEmpty, @Lengh, @Enumerated, @Email.
 *
 * Observacao: As Classes do pacote {@link br.com.compasso.alunoapirest.controller.form}
 * tem o objetivo de definir/limitar/validar os dados que sao recebidos do Usuario.
 */
@ApiModel(description = "Formulario de cadastro de Usuario")
public class CadastroUsuarioForm {

    @ApiModelProperty(example = "Joao da Silva", required = true)
    @NotEmpty
    @Length(min = 5, max = 30)
    private String nome;
    @ApiModelProperty(example = "joao.silva", required = true)
    @NotEmpty
    @Length(min = 5, max = 30)
    private String usuario;
    @ApiModelProperty(example = "joao.silva@mail.com", required = true)
    @NotEmpty
    @Email
    private String email;
    @ApiModelProperty(example = "joao123", required = true)
    @NotEmpty
    private String senha;
    @ApiModelProperty(notes = "Formato: DDNNNNNNNNN", example = "48910102020", required = true)
    @NotEmpty
    private String telefone;
    @ApiModelProperty(example = "MASCULINO", required = true)
    @Enumerated
    private Sexo sexo;
    @ApiModelProperty(notes = "Formato: DD-MM-YYYY", example = "01-01-1001", required = true)
    @NotEmpty
    private String dataNascimento;
    @ApiModelProperty(example = "30", required = true)
    @NotNull
    private Integer idade;
    @ApiModelProperty(notes = "Opcoes: 1 - aluno, 2 - admin", example = "1", required = true)
    @NotNull
    private Long perfilId;

    public Usuario converter(PerfilRepository perfilRepository) {
        Perfil perfil = perfilRepository.getOne(perfilId);
        return new Usuario(nome, usuario, email, senha, telefone, sexo, dataNascimento, idade, perfil);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha.isEmpty()) {
            this.senha = senha;
        } else {
            this.senha = new BCryptPasswordEncoder().encode(senha);
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }
}
