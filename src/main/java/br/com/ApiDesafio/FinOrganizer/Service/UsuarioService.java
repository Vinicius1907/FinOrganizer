package br.com.ApiDesafio.FinOrganizer.Service;

import br.com.ApiDesafio.FinOrganizer.Entity.Usuario;
import br.com.ApiDesafio.FinOrganizer.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario cadastro(Usuario usuario){
        usuarioRepository.saveAndFlush(usuario);
        return usuario;
    }

    public Usuario login(Usuario infoLogin){
        Optional<Usuario> loginUsuario = usuarioRepository.findByEmailAndSenha(infoLogin.getEmail(), infoLogin.getSenha());

        if(loginUsuario.isPresent()){
            return loginUsuario.get();
        } else {
            throw new RuntimeException("Email ou senha Incorreta");
        }
    }


    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }
}
