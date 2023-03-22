package br.com.ApiDesafio.FinOrganizer.Service;

import br.com.ApiDesafio.FinOrganizer.Entity.Usuario;
import br.com.ApiDesafio.FinOrganizer.Repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Optional<Usuario> gerarDadosUsuario(){
        Usuario usuario = new Usuario();

        usuario.setEmail("vini.lima1907@gmail.com");
        usuario.setSenha("Vini-Lima1907");

        return Optional.of(usuario);
    }

    @Test
    void cadastro() {
        Optional<Usuario> usuario = gerarDadosUsuario();

        Usuario novoUsuario = usuarioService.cadastro(usuario.get());

        Assert.assertNotNull(novoUsuario);
        Assert.assertEquals(usuario.get().getEmail(), novoUsuario.getEmail());
    }

    @Test
    void login(){
        Optional<Usuario> usuario = gerarDadosUsuario();
        when(usuarioRepository.findByEmailAndSenha(usuario.get().getEmail(), usuario.get().getSenha())).thenReturn(usuario);

        Usuario usuarioLogin = usuarioService.login(usuario.get());

        assertNotNull(usuarioLogin);
        assertEquals(usuarioLogin, usuario.get());
    }
}