package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioDaoTest {

    private UsuarioDao dao;

    @Test
    void testeBuscaDeUsuarioPeloUserName() {
        this.dao = new UsuarioDao();
        Usuario usuario = this.dao.buscarPorUsername("fulano");

        Assertions.assertNotNull(usuario);
    }

}
