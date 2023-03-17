package br.com.ApiDesafio.FinOrganizer.Service;

import br.com.ApiDesafio.FinOrganizer.Entity.Lancamento;
import br.com.ApiDesafio.FinOrganizer.Entity.Usuario;
import br.com.ApiDesafio.FinOrganizer.Repository.LancamentoRepository;
import br.com.ApiDesafio.FinOrganizer.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final UsuarioRepository usuarioRepository;

    public Lancamento salvarLancamento(Lancamento lancamento) {

        Optional<Usuario> retornarUsuario = usuarioRepository.findById(lancamento.getUsuario().getId());

        if(lancamento.getCategoria() == null || lancamento.getCategoria().isBlank()){
            lancamento.setCategoria("Não categorizado");
        } else if(lancamento.getCategoria().equalsIgnoreCase("despesas") || lancamento.getCategoria().equalsIgnoreCase("despesa")){
            lancamento.setCategoria("despesas");
            retornarUsuario.get().setSaldoAtual(retornarUsuario.get().getSaldoAtual().subtract(lancamento.getValorLancamento()));
        } else if (lancamento.getCategoria().equalsIgnoreCase("receitas") || lancamento.getCategoria().equalsIgnoreCase("receita")) {
            lancamento.setCategoria("receitas");
            retornarUsuario.get().setSaldoAtual(retornarUsuario.get().getSaldoAtual().add(lancamento.getValorLancamento()));
        } else {
            throw new RuntimeException("Categoria invalida");
        }

        if(lancamento.getTipoTransacao().equalsIgnoreCase("debito")){
            lancamento.setTipoTransacao("debito");
        } else if(lancamento.getTipoTransacao().equalsIgnoreCase("credito")){
            lancamento.setTipoTransacao("crdito");
        } else {
            throw new RuntimeException("Escolha um tipo valido");
        }

        lancamento.setUsuario(retornarUsuario.get());

        lancamentoRepository.saveAndFlush(lancamento);
        return lancamento;
    }

    public Optional<List<Lancamento>> listarReceitas(Usuario usuario, String categoria){
        Optional<Usuario> loginUsuario = usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
        Optional<List<Lancamento>> buscarReceitas = null;

        Optional<UUID> uuid = Optional.ofNullable(usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).get().getId());

        if(loginUsuario.isPresent()){
            buscarReceitas = lancamentoRepository.findByUsuario_idAndCategoria(uuid, categoria);
        } else {
            throw new RuntimeException("Não há usuário logado!");
        }

        return buscarReceitas;
    }

    public ArrayList<Optional<List<Lancamento>>> resumo(Usuario usuario){
        Optional<Usuario> loginUsuario = usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
        Optional<List<Lancamento>> buscarReceitas = null;
        Optional<List<Lancamento>> buscarDespesas = null;
        Optional<List<Lancamento>> buscarSemCategoria = null;

        Optional<UUID> uuid = Optional.ofNullable(usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).get().getId());

        if(loginUsuario.isPresent()){
            buscarReceitas = lancamentoRepository.findByUsuario_idAndCategoria(uuid, "receitas");
            buscarDespesas = lancamentoRepository.findByUsuario_idAndCategoria(uuid, "despesas");
            buscarSemCategoria = lancamentoRepository.findByUsuario_idAndCategoria(uuid, "Não categorizado");
        } else {
            throw new RuntimeException("Não há usuário logado!");
        }

        ArrayList<Optional<List<Lancamento>>> meusLancamentos = new ArrayList<>();
        meusLancamentos.add(buscarReceitas);
        meusLancamentos.add(buscarDespesas);
        meusLancamentos.add(buscarSemCategoria);

        return meusLancamentos;
    }
}
