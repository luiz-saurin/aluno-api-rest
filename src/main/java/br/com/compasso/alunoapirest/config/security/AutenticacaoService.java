package br.com.compasso.alunoapirest.config.security;

import br.com.compasso.alunoapirest.model.Usuario;
import br.com.compasso.alunoapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe que faz verificacao das credenciais do login.
 */
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByUsuario(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados invalidos!");
    }
}
