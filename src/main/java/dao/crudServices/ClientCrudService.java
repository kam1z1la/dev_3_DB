package dao.crudServices;

import dao.Service;
import entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import query.Database;

import java.util.List;
import java.util.Optional;

public class ClientCrudService implements Service<Client> {
    private Transaction transaction = null;

    @Override
    public void create(Client entityClass, Object... newData) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            entityClass = new Client();
            entityClass.setName(newData[0].toString());
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
    public <K> void update(Client entityClass, K id, Object... newData) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            entityClass = session.get(Client.class, id);
            if (Optional.ofNullable(id).isPresent()) {
                entityClass.setName(newData[0].toString());
                transaction = session.beginTransaction();
                session.merge(entityClass);
                session.flush();
                transaction.commit();
            } else {
                System.out.println("Don't find id:");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public <K> void deleteById(K id) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            Client client = session.get(Client.class, id);
            transaction = session.beginTransaction();
            if (Optional.ofNullable(session.get(Client.class, id)).isPresent()) {
                session.remove(client);
                session.flush();
                transaction.commit();
            } else {
                System.out.println("[INFO] Don't find id:");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    protected Integer getIdByName(String name) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            Client client = (Client) session.createQuery("from Client ñ " +
                             "where ñ.name = :name").setParameter("name", name)
                    .uniqueResult();
            return client.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Don't find id:");
        return -1;
    }

    public static void main(String[] args) {
        ClientCrudService clientService = new ClientCrudService();
        System.out.println("[INFO] Show all data: \n" + clientService.showAll());
        clientService.create(new Client(), "Anton Mychacho");
        System.out.println("[INFO] Show all data: \n" + clientService.showAll());
        clientService.update(new Client(), clientService.getIdByName("Anton Mychacho") ,"Stepan Mychacho");
        System.out.println("[INFO] Show all data: \n" + clientService.showAll());
        clientService.deleteById(clientService.getIdByName("Stepan Mychacho"));
        System.out.println("[INFO] Show all data: \n" + clientService.showAll());
    }
}
