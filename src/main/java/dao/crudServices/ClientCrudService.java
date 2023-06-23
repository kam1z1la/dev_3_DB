package dao.crudServices;

import dao.Service;
import entity.Client;
import entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import query.Database;

import java.util.List;
import java.util.Optional;

public class ClientCrudService implements Service<Client> {
    private Transaction transaction = null;

    @Override
    public void create(Client entityClass) {
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
    public List<Client> showAll() {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            return session.createQuery("from Client", Client.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    @Override
    public boolean update(Client entityClass) {
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
    public boolean delete(Client entityClass) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            Client client = session.find(Client.class, entityClass.getId());
            if (Optional.ofNullable(client).isPresent()) {
                transaction = session.beginTransaction();
                session.remove(client);
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
        ClientCrudService clientService = new ClientCrudService();
        Client client = new Client();
        client.setName("Anton Mychacho");
        System.out.println("[INFO] Show all data: \n" + clientService.showAll());
        clientService.create(client);
        System.out.println("[INFO] Show all data: \n" + clientService.showAll());
        client.setName("Stepan Mychacho");
        clientService.update(client);
        System.out.println("[INFO] Show all data: \n" + clientService.showAll());
        clientService.delete(client);
        System.out.println("[INFO] Show all data: \n" + clientService.showAll());
    }
}
