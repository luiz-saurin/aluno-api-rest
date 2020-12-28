package br.com.compasso.alunoapirest.controller;

import br.com.compasso.alunoapirest.controller.dto.UsuarioDto;
import br.com.compasso.alunoapirest.controller.form.AtualizacaoUsuarioForm;
import br.com.compasso.alunoapirest.controller.form.CadastroUsuarioForm;
import br.com.compasso.alunoapirest.model.Perfil;
import br.com.compasso.alunoapirest.model.Usuario;
import br.com.compasso.alunoapirest.repository.PerfilRepository;
import br.com.compasso.alunoapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * Classe de Controle de {@link Usuario}.
 * <p>
 * Nessa classe foi definido e configurado os metodos HTTP de acesso à API, sendo eles os metodos
 * GET, POST, PUT e DELETE.
 * <p>
 * Para fins de teste, os metodos POST, PUT e DELETE desta classe necessitam de autenticacao,
 * implementado na Classe {@link AutenticacaoController}.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    /**
     * Metodo GET que retorna todos os usuarios cadastrados.
     *
     * O metodo permite a entrada dos seguintes parametros:
     *
     * @param nome      Nome completo do usuario
     * @param usuario   Nome de usuario/login
     * @param paginacao Parametros de paginacao como:
     *                  -> Ordenar Por
     *                  -> Ordem Crescente/Decrescente
     *                  -> Numero da pagina
     *                  -> Quantidade de resultados por pagina
     *
     *                  Caso nao seja passado nenhum parametro de paginacao, o padrao sera:
     *                  Pagina = 0, Quantidade de Resultados = 10,
     *                  Ordenar por ID, Ordenacao Crescente.
     *                  Exemplo de URL:
     *                  http://localhost:8080/usuarios?page=0&size=10&sort=id,desc&sort=nome,desc
     * @return Lista de todos os usuarios cadastrados.
     */
    @GetMapping
    //@Cacheable(value = "listar")
    public Page<UsuarioDto> listarTodos(@RequestParam(required = false) String nome,
                                        @RequestParam(required = false) String usuario,
                                        @PageableDefault(sort = "id",
                                                direction = Sort.Direction.ASC,
                                                page = 0,
                                                size = 10)
                                                Pageable paginacao) {
        if (nome == null && usuario == null) {
            Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
            return UsuarioDto.converter(usuarios);
        } else {
            if (nome == null && usuario.length() > 0) {
                Page<Usuario> usuarios = usuarioRepository.findByUsuario(usuario, paginacao);
                return UsuarioDto.converter(usuarios);
            } else {
                Page<Usuario> usuarios = usuarioRepository.findByNome(nome, paginacao);
                return UsuarioDto.converter(usuarios);
            }

        }
    }

    /**
     * Metodo GET que retorna todos os usuarios considerados Alunos.
     */
    @GetMapping("/alunos")
    //@Cacheable(value = "listar")
    public Page<UsuarioDto> listarAlunos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Usuario> usuarios = usuarioRepository.findByPerfilId(1L, paginacao);
        return UsuarioDto.converter(usuarios);
    }

    /**
     * Metodo GET que retorna todos os usuarios considerados Administradores.
     */
    @GetMapping("/admins")
    //@Cacheable(value = "listar")
    public Page<UsuarioDto> listarAdmins(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Usuario> usuarios = usuarioRepository.findByPerfilId(2L, paginacao);
        return UsuarioDto.converter(usuarios);
    }

    /**
     * Metodo GET que retorna qualquer usuarios com o ID fornecido.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> listaUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(new UsuarioDto(usuario.get()));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Metodo POST para cadastro de usuarios.
     * A Classe {@link CadastroUsuarioForm} define as informacoes que devem ser
     * fornecidas para o cadastro.
     *
     * Para executar este metodo, precisa realizar autenticacao.
     */
    @PostMapping
    @Transactional
    //@CacheEvict(value = "listar", allEntries = true)
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroUsuarioForm usuarioForm, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Perfil> optional = perfilRepository.findById(usuarioForm.getPerfilId());
        if (optional.isPresent()) {
            Usuario usuario = usuarioForm.converter(perfilRepository);
            usuarioRepository.save(usuario);

            URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            //uriComponentsBuilder.path("/usuarios/{id}") = devolve no cabeçalho location a url do novo recurso criado
            //buildAndExpand(aluno.getId()).toUri() = devolve no corpo da resposta uma representação desse novo recurso criado
            return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    /**
     * Metodo PUT para atualizar os dados de um Usuario ja cadastrado.
     * A Classe {@link AtualizacaoUsuarioForm} define as informacoes que devem ser
     * fornecidas para o cadastro.
     *
     * Para executar este metodo, precisa realizar autenticacao.
     */
    @PutMapping("/{id}")
    @Transactional
    //@CacheEvict(value = "listar", allEntries = true)
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioForm usuarioForm) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            Usuario usuario = usuarioForm.atualizar(id, usuarioRepository);
            return ResponseEntity.ok(new UsuarioDto(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Metodo DELETE para remocao de um Usuario.
     *
     * Para executar este metodo, precisa realizar autenticacao.
     */
    @DeleteMapping("/{id}")
    @Transactional
    //@CacheEvict(value = "listar", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
