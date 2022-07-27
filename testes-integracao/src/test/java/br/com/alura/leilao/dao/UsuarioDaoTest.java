package br.com.alura.leilao.dao;

import br.com.alura.leilao.configuracao.JPAConfig;
import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    void setup() {
        this.em = JPAConfig.getEntityManager();
        this.dao = new UsuarioDao(em);
    }

    @Test
    void testeDeveriaEncontrarUsuarioCadastroPeloUserName() {
        Usuario usuarioTeste = new Usuario("fulano", "fulano@gmail.com", "123456");
        em.getTransaction().begin();
        em.persist(usuarioTeste);
        em.getTransaction().commit();

        Usuario usuarioEncontrado = dao.buscarPorUsername("fulano");

        assertNotNull(usuarioEncontrado);
    }

    @Test
    void testeBuscaDeUsuarioPeloUserName() {
        Usuario usuarioTeste = new Usuario("fulano", "fulano@gmail.com", "123456");
        em.getTransaction().begin();
        em.persist(usuarioTeste);
        em.getTransaction().commit();

        assertThrows(NoResultException.class, () -> dao.buscarPorUsername("beltrano"));
    }

}
