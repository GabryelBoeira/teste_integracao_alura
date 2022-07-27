package br.com.alura.leilao.dao;

import br.com.alura.leilao.configuracao.JPAConfig;
import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    void setup() {
        this.em = JPAConfig.getEntityManager();
        this.dao = new UsuarioDao(em);
    }

    @Test
    void testeBuscaDeUsuarioPeloUserName() {
        Usuario usuarioTeste = new Usuario("fulano", "fulano@gmail.com", "123456");

        em.getTransaction().begin();
        em.persist(usuarioTeste);
        em.getTransaction().commit();

        Usuario usuarioEncontrado = dao.buscarPorUsername("fulano");
        Assertions.assertNotNull(usuarioEncontrado);
    }

}
