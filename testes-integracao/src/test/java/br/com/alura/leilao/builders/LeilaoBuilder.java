package br.com.alura.leilao.builders;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoBuilder {

    private String nome;
    private BigDecimal valorInicial;
    private LocalDate data = null;
    private Usuario usuario;

    public LeilaoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LeilaoBuilder comValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
        return this;
    }

    public LeilaoBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public LeilaoBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Leilao criar() {
        if (this.data == null) return new Leilao(nome, valorInicial, usuario);

        return new Leilao(nome, valorInicial, data, usuario);
    }
}
