import dao.*;

public class JpaTest {
    public static void main(String[] args) {

// проверка crud операций

//        Ticket ticket = new Ticket();
//        ticket.setPrice(1400);
//
//        Passenger passenger = new Passenger();
//        passenger.setAge(24);
//        passenger.setLocation("Russia");
//        passenger.setName("Andrew");
//        passenger.setSurname("Alexeev");
//
//        Flight flight = new Flight();
//        flight.setStart("Australia");
//        flight.setDestination("Canada");
//
//        ticket.setPassenger(passenger);
//        flight.addTicketToFlight(ticket);
//
//        flightDao.add(flight);
//
//        TicketDao ticketDao = new TicketDaoImpl();
//
//        System.out.println(ticketDao.getAll());
//
//        PassengerDao passengerDao = new PassengerDaoImpl();
//        System.out.println(passengerDao.getOne(6));
//        System.out.println(passengerDao.getAll());

        //проверка сложного запроса разными способами

        FlightDaoImpl flightDaoImpl = new FlightDaoImpl();
//        System.out.println(flightDaoImpl.getFlightByTicketPriceAngLocationWithJPQL(500, "Russia"));
//        System.out.println(flightDaoImpl.getFlightByTicketPriceAngLocationWithNamedQuery(500, "Russia"));
//        System.out.println(flightDaoImpl.getFlightByTicketPriceAngLocationWithNativeQuery(500, "Russia"));
        System.out.println(flightDaoImpl.getFlightByTicketPriceAngLocationWithCriteriaQuery(500, "Russia"));
    }
}
