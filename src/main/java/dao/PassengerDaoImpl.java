package dao;

import entity.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PassengerDaoImpl implements PassengerDao {
    private EntityManagerFactory factory = JpaUtilClass.getInstance();

    @Override
    public List<Passenger> getAll() {
        EntityManager entityManager = factory.createEntityManager();
        return entityManager.createQuery(
                "select p from Passenger p", Passenger.class).getResultList();
    }

    @Override
    public Passenger getOne(int id) {
        EntityManager entityManager = factory.createEntityManager();
        TypedQuery<Passenger> q = entityManager.createQuery(
                "select p from Passenger p where p.id = :id", Passenger.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public void add(Passenger passenger) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(passenger);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
