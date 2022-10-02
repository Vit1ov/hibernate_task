package dao;

import entity.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class TicketDaoImpl implements TicketDao{
    private EntityManagerFactory factory = JpaUtilClass.getInstance();

    @Override
    public List<Ticket> getAll() {
        EntityManager entityManager = factory.createEntityManager();
        return entityManager.createQuery(
                "select t from Ticket t", Ticket.class).getResultList();
    }

    @Override
    public Ticket getOne(int id) {
        EntityManager entityManager = factory.createEntityManager();
        TypedQuery<Ticket> q = entityManager.createQuery(
                "select t from Ticket t where t.id = :id", Ticket.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public void add(Ticket ticket) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
