package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class UsuarioDaoTest {

    private UsuarioDao dao;

    private EntityManager em;

    @Test
    void testeBuscaDeUsuarioPeloUserName() {
        this.dao = new UsuarioDao(this.em);
        Usuario usuario = this.dao.buscarPorUsername("fulano");

        Assertions.assertNotNull(usuario);
    }

}
