package br.com.alura.leilao.dao;

import br.com.alura.leilao.configuracao.JPAConfig;
import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    private

    @BeforeEach
    void setup() {
        this.em = JPAConfig.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        this.em.getTransaction().getRollbackOnly();
    }

    private Usuario persistirUsuario() {
        Usuario usuario = new Usuario("fulano", "fulano@gmail.com", "123456");
        em.persist(usuario);
        return usuario;
    }

    @Test
    void testeDeveriaEncontrarUsuarioCadastroPeloUserName() {
        Usuario usuario = persistirUsuario();

        Usuario usuarioEncontrado = dao.buscarPorUsername("fulano");

        assertNotNull(usuarioEncontrado);
        assertEquals(usuario.getNome(), usuarioEncontrado.getNome());
    }

    @Test
    void testeComFalhaAoProcurarUsuarioQueNaoExisteCadastradoPeloUserName() {
        persistirUsuario();

        assertThrows(NoResultException.class, () -> dao.buscarPorUsername("beltrano"));
    }

}
