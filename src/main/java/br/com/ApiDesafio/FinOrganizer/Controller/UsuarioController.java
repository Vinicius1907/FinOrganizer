package br.com.ApiDesafio.FinOrganizer.Controller;

import br.com.ApiDesafio.FinOrganizer.Entity.Usuario;
import br.com.ApiDesafio.FinOrganizer.Service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/v1")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

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
        Usuario novoLoginUsuario = usuarioService.login(loginUsuario);
        return new ResponseEntity<>(novoLoginUsuario, HttpStatus.OK);
    }
}
