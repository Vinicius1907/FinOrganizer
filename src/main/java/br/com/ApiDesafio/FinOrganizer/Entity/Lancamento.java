package br.com.ApiDesafio.FinOrganizer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLancamento;
    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "id")
    private Usuario usuario;

    private String nomeLancamento;

    private LocalDate dataLancamento;

    private BigDecimal valorLancamento;

    private String tipoTransacao;

    private String categoria;


}
