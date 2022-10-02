package dao;

import entity.Flight;
import entity.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


public class FlightDaoImpl implements FlightDao {

    private EntityManagerFactory factory = JpaUtilClass.getInstance();

    @Override
    public List<Flight> getAll() {
        EntityManager entityManager = factory.createEntityManager();
        return entityManager.createQuery(
                "select f from Flight f", Flight.class).getResultList();
    }

    @Override
    public Flight getOne(int id) {
        EntityManager entityManager = factory.createEntityManager();
        TypedQuery<Flight> q = entityManager.createQuery(
                "select f from Flight f where f.id = :id", Flight.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public void add(Flight flight) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(flight);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Flight> getFlightByTicketPriceAngLocationWithJPQL(int price, String location) {
        EntityManager entityManager = factory.createEntityManager();
        Query jpqlQuery = entityManager.createQuery("SELECT f FROM Ticket t " +
                "JOIN t.flight f  " +
                "JOIN t.passenger p   " +
                "WHERE t.price > :price " +
                "GROUP BY p.location " +
                "HAVING p.location = :location " +
                "ORDER BY f.airplane", Flight.class);
        jpqlQuery.setParameter("price", price);
        jpqlQuery.setParameter("location", location);
        return jpqlQuery.getResultList();
    }

    public List<Flight> getFlightByTicketPriceAngLocationWithNamedQuery(int price, String location) {
        EntityManager entityManager = factory.createEntityManager();
        Query namedQuery = entityManager.createNamedQuery("Flight.findByPriceAndLocation", Flight.class);
        namedQuery.setParameter("price", price);
        namedQuery.setParameter("location", location);
        return (List<Flight>) namedQuery.getResultList();
    }

    public List<Flight> getFlightByTicketPriceAngLocationWithNativeQuery(int price, String location) {
        EntityManager entityManager = factory.createEntityManager();
        Query nativeQuery
                = entityManager.createNativeQuery
                ("SELECT f.id, f.start, f.destination, f.airplane FROM tickets t " +
                "JOIN flights f ON t.flight_id = f.id  " +
                "JOIN passengers p ON t.passenger_id = p.id  " +
                "WHERE t.price > :price " +
                "GROUP BY p.location " +
                "HAVING p.location = :location " +
                "ORDER BY f.airplane ", Flight.class);
        nativeQuery.setParameter("price", price);
        nativeQuery.setParameter("location", location);
        return (List<Flight>) nativeQuery.getResultList();
    }

    public List<Flight> getFlightByTicketPriceAngLocationWithCriteriaQuery(int price, String location) {
        EntityManager entityManager = factory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> criteriaQuery = criteriaBuilder.createQuery(Flight.class);
        Root<Flight> flightRoot = criteriaQuery.from(Flight.class);
        Join<Flight, Ticket> flightTicketJoin = flightRoot.join("tickets", JoinType.INNER);

        criteriaQuery.select(flightRoot);
        criteriaQuery.where(criteriaBuilder.greaterThan(flightTicketJoin.get("price"), price))
                .groupBy(flightRoot.get("destination"))
                .having(criteriaBuilder.equal(flightRoot.get("destination"), "USA"))
                .orderBy(criteriaBuilder.asc(flightRoot.get("airplane")));
        List<Flight> queryResult = entityManager.createQuery(criteriaQuery)
                .getResultList();
        return queryResult;
    }
}
