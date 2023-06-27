package dao.crudServices;

import dao.Service;
import entity.Client;
import entity.Planet;
import entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import query.Database;

import java.util.List;
import java.util.Optional;

public class TicketCrudService implements Service<Ticket> {
    private Transaction transaction = null;


    @Override
    public void create(Ticket entityClass) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entityClass);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> showAll() {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            return session.createQuery("from Ticket", Ticket.class)
                    .list();
        } catch (Exception e) {

            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    @Override
    public boolean update(Ticket entityClass) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entityClass);
            session.flush();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Ticket entityClass) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            Ticket ticket = session.find(Ticket.class, entityClass.getId());
            if (Optional.ofNullable(ticket).isPresent()) {
                transaction = session.beginTransaction();
                session.remove(ticket);
                session.flush();
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }


    public static void main(String[] args) {
        TicketCrudService ticketService = new TicketCrudService();

        Ticket ticket = new Ticket();

        Client client = new Client();
        client.setId(25);

        Planet from_p = new Planet();
        from_p.setId("MERCURY");
        from_p.setName("mercutiy");

        Planet to_p = new Planet();
        to_p.setId("JUPITER");
        to_p.setName("jupiter");

        ticket.populateTicket(client, from_p, to_p);
        System.out.println("[INFO] Show all data: \n" + ticketService.showAll());
        ticketService.create(ticket);

        System.out.println("[INFO] Show all data: \n" + ticketService.showAll());
        client.setId(25);

        from_p.setId("JUPITER");
        to_p.setId("MERCURY");
        ticket.populateTicket(client, from_p, to_p);

        ticketService.update(ticket);
        System.out.println("[INFO] Show all data: \n" + ticketService.showAll());

        ticketService.delete(ticket);
        System.out.println("[INFO] Show all data: \n" + ticketService.showAll());
    }
}
