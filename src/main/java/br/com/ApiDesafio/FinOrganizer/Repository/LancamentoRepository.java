package br.com.ApiDesafio.FinOrganizer.Repository;

import br.com.ApiDesafio.FinOrganizer.Entity.Lancamento;
import br.com.ApiDesafio.FinOrganizer.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    Optional<List<Lancamento>> findByUsuario_idAndCategoria(Optional<UUID> id, String categoria);
}
