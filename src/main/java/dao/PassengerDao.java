package dao;

import entity.Passenger;

import java.util.List;

public interface PassengerDao {
    List<Passenger> getAll();

    Passenger getOne(int id);

    void add(Passenger passenger);
}
