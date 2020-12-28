package br.com.compasso.alunoapirest.repository;

import br.com.compasso.alunoapirest.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que possibilita a classe
 * {@link br.com.compasso.alunoapirest.controller.UsuarioController}
 * herdar metodos da JPA como findAll, findById.
 */
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
