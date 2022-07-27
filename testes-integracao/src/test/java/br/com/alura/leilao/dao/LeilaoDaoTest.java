package br.com.alura.leilao.dao;

import br.com.alura.leilao.builders.LeilaoBuilder;
import br.com.alura.leilao.builders.UsuarioBuilder;
import br.com.alura.leilao.configuracao.JPAConfig;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LeilaoDaoTest {

    private LeilaoDao dao;
    private EntityManager em;

    @BeforeEach
    void setup() {
        this.em = JPAConfig.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        this.em.getTransaction().getRollbackOnly();
    }

    private Leilao persistirLeilao() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("fulano")
                .comEmail("fulano@gmail.com")
                .comSenha("123456")
                .criar();
        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial(new BigDecimal("70"))
                .comData(LocalDate.now())
                .comUsuario(usuario)
                .criar();

        leilao = dao.salvar(leilao);
        assertNotNull(leilao.getId(), "Nao retorno o id para o leilao salvo");
        return leilao;
    }

    @Test
    void testeDeveriaCadastrarUmLeilao() {
        Leilao leilao = persistirLeilao();
        Leilao leilaoSalvo = dao.buscarPorId(leilao.getId());

        assertNotNull(leilaoSalvo, "Nao retorno o leilao para o id informado salvo");
    }

    @Test
    void testeDeveriaAtualizarUmLeilaoCadastrado() {
        Leilao leilao = persistirLeilao();
        leilao.setNome("Celular");
        leilao.setValorInicial(new BigDecimal("400"));
        Leilao leilaoAtualizado = dao.salvar(leilao);

        assertEquals(leilao.getId(), leilaoAtualizado.getId(), "Nao e o mesmo id para os dois objetos");
        assertEquals("Celular", leilaoAtualizado.getNome(), "Nao e o mesmo nome apos atualizar");
        assertEquals(new BigDecimal("400"), leilaoAtualizado.getValorInicial(), "Nao e o mesmo valor inicial alterado");
    }
}
