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
    public void create(Planet entityClass) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
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
    public boolean update(Planet entityClass) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entityClass);
            session.flush();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Planet entityClass) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            Planet planet = session.find(Planet.class, entityClass.getId());
            if (Optional.ofNullable(planet).isPresent()) {
                transaction = session.beginTransaction();
                session.remove(planet);
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
        PlanetCrudService planetService = new PlanetCrudService();
//        Planet planet = new Planet();
//        planet.setId("EARTH");
//        planet.setName("Земля");
//        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
//        planetService.create(planet);
//        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
//        planet.setName("Earth");
//        System.out.println("planetService.update(planet) = " + planetService.update(planet));
//        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
//        System.out.println("Delete: " + planetService.delete(planet));
        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
    }
}
