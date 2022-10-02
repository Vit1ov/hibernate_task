package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtilClass {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("jpa");

    public static EntityManagerFactory getInstance() {
        return factory;
    }

}
