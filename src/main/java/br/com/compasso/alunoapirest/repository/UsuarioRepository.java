package br.com.compasso.alunoapirest.repository;

import br.com.compasso.alunoapirest.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface que possibilita a classe
 * {@link br.com.compasso.alunoapirest.controller.UsuarioController}
 * herdar metodos da JPA como findAll, findById.
 *
 * Também possibilita definir interfaces especificas como findByNome,
 * findByUsuario, findByEmail, findByPerfilId. Usando a nomenclatura "findBy" seguido
 * de algum dos atributos da Classe {@link Usuario}, a JPA consegue indentificar o atributo
 * e criar uma Query automaticamente para fazer a consulta no banco de dados.
 *
 * Caso seja um atributo herdado de outra classe, utilize da seguinte maneira (exemplo):
 * findByNomeDaClasse_AtributoDaClasse
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);

    Page<Usuario> findByUsuario(String usuario, Pageable paginacao);

    Page<Usuario> findByNome(String nome, Pageable paginacao);

    Page<Usuario> findByPerfilId(Long id, Pageable paginacao);
}
