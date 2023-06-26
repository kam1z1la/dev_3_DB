package dao.crudServices;

import dao.Service;
import entity.Client;
import entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import query.Database;

import java.util.ArrayList;
import java.util.List;

public class TicketCrudService implements Service<Ticket> {
    private Transaction transaction = null;


    @Override
    public void create(Ticket entityClass) {

    }

    @Override
    public List<Ticket> showAll() {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
//            return session.createQuery("select t from Ticket t " +
//                            "join fetch t.client c" +
//                            "join fetch t.from_planet from_p" +
//                            "join fetch t.to_planet to_p", Ticket.class)
//                    .list();
            List<Client> clients = session.createQuery("FROM Client c JOIN FETCH c.ticket", Client.class).list();
            List<Ticket> tickets = new ArrayList<>();
            for (Client client : clients) {
                tickets.addAll(client.getTicket());
            }
            return tickets;
        } catch (Exception e) {

            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    @Override
    public boolean update(Ticket entityClass) {
        return false;
    }

    @Override
    public boolean delete(Ticket entityClass) {
        return false;
    }

    public static void main(String[] args) {
        TicketCrudService ticketService = new TicketCrudService();
        System.out.println("[INFO] Show all data: \n" + ticketService.showAll());
    }
}
