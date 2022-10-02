package dao;

import entity.Ticket;

import java.util.List;

public interface TicketDao {
    List<Ticket> getAll();

    Ticket getOne(int id);

    void add(Ticket ticket);
}
