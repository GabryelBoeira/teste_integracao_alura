package br.com.alura.leilao.configuracao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConfig {

    private static EntityManagerFactory FACTORY = Persistence.
            createEntityManagerFactory("tests");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
