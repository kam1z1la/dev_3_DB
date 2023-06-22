package dao.crudServices;

import dao.Service;
import entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import query.Database;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlanetCrudService implements Service<Planet> {
    private Transaction transaction = null;

    @Override
    public void create(Planet entityClass, Object... newData) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            entityClass = new Planet(newData[0].toString(), newData[1].toString());
            session.persist(entityClass);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            Objects.requireNonNull(transaction).rollback();
        }
    }

    @Override
    public List<Planet> showAll() {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            return session.createQuery("from Planet", Planet.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    @Override
    public <K> void update(Planet entityClass, K id, Object... newData) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            entityClass = session.get(Planet.class, id);
            entityClass.setName(newData[0].toString());
            session.merge(entityClass);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            Objects.requireNonNull(transaction).rollback();
        }
    }

    @Override
    public <K> void deleteById(K id) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (Optional.ofNullable(planet).isPresent()) {
                session.remove(planet);
                session.flush();
                transaction.commit();
            } else {
                System.out.println("Don't find id:");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Objects.requireNonNull(transaction).rollback();
        }
    }


    public static void main(String[] args) {
        PlanetCrudService planetService = new PlanetCrudService();
        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
        planetService.create(new Planet(),"EARTH", "Земля");
        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
        planetService.update(new Planet(),"EARTH", "земля");
        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
        planetService.deleteById("EARTH");
        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
    }
}
