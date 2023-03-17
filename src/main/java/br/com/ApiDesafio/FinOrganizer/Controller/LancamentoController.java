package br.com.ApiDesafio.FinOrganizer.Controller;

import br.com.ApiDesafio.FinOrganizer.Entity.Lancamento;
import br.com.ApiDesafio.FinOrganizer.Entity.Usuario;
import br.com.ApiDesafio.FinOrganizer.Service.LancamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos/v1")
@RequiredArgsConstructor
public class LancamentoController {

    private final LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity<Lancamento> postarLancamento(@RequestBody Lancamento lancamento){
        Lancamento salvarLancamento = lancamentoService.salvarLancamento(lancamento);
        return new ResponseEntity<>(salvarLancamento, HttpStatus.CREATED);
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<Optional<List<Lancamento>>> minhasCategorias(@RequestBody Usuario usuario, @PathVariable String categoria){
        Optional<List<Lancamento>> receitas = lancamentoService.listarReceitas(usuario, categoria);
        return new ResponseEntity<>(receitas, HttpStatus.OK);
    }

    @GetMapping("/resumo")
    public ResponseEntity<ArrayList<Optional<List<Lancamento>>>> resumo(@RequestBody Usuario usuario){
        ArrayList <Optional<List<Lancamento>>> resumo = lancamentoService.resumo(usuario);
        return new ResponseEntity<>(resumo, HttpStatus.OK);
    }
}
