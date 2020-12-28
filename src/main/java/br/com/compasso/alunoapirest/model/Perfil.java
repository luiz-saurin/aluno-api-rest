package br.com.compasso.alunoapirest.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Modelo que define os tipos de perfis para objetos da classe {@link Usuario}.
 *
 * Inicialmente, para fins de teste, existem apenas dois tipos (1 - aluno, 2 - admin).
 *
 * Tambem implementa a interface {@link GrantedAuthority}, que possibilita implementar
 * niveis de acesso para cada tipo de Perfil.
 */
@Entity
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    @OneToMany
    private List<Usuario> usuario = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getAuthority() {
        return this.tipo;
    }
}
