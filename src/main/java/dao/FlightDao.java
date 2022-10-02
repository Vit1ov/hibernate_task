package dao;

import entity.Flight;

import java.util.List;

public interface FlightDao {
    List<Flight> getAll();

    Flight getOne(int id);

    void add(Flight flight);

}
