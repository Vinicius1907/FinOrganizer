package br.com.ApiDesafio.FinOrganizer.Controller;

import br.com.ApiDesafio.FinOrganizer.Entity.Usuario;
import br.com.ApiDesafio.FinOrganizer.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/usuarios/v1")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity findAll(){
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity cadastro(@RequestBody Usuario novoUsuario){
        Usuario usuario = usuarioService.cadastro(novoUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody Usuario loginUsuario){
        Usuario usuario = usuarioService.login(loginUsuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
