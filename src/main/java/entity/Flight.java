package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Flight.findByPriceAndLocation", query = "SELECT f FROM Ticket t " +
        "JOIN t.flight f  " +
        "JOIN t.passenger p   " +
        "WHERE t.price > :price " +
        "GROUP BY p.location " +
        "HAVING p.location = :location " +
        "ORDER BY f.airplane")
@Data
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start")
    private String start;

    @Column(name = "destination")
    private String destination;

    @Column(name = "airplane")
    private int airplane;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Ticket> tickets;

    public void addTicketToFlight(Ticket ticket) {
        if (tickets == null) {
            tickets = new ArrayList<>();
        }
        tickets.add(ticket);
        ticket.setFlight(this);
    }

    public Flight() {
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", destination='" + destination + '\'' +
                ", airplane=" + airplane +
                '}';
    }
}
