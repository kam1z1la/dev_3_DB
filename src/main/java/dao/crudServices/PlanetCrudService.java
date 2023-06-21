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
    public void create(Object ... newData) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            Planet planet = new Planet(newData[0].toString(), newData[1].toString());
            session.persist(planet);
            System.out.printf("[INFO] Create new object: %s\r\n", planet);
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
            return  session.createQuery("from Planet", Planet.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    @Override
    public void update(Object id, Object ... newData) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            planet.setName(newData[0].toString());
            session.merge(planet);
            System.out.printf("[INFO] Update object: %s\r\n", planet);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            Objects.requireNonNull(transaction).rollback();
        }
    }

    @Override
    public void deleteById(Object id) {
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if(Optional.ofNullable(planet).isPresent()) {
                session.remove(planet);
                System.out.printf("[INFO] Remove object: %s\r\n", planet);
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
        planetService.create("EARTH", "Земля");
        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
        planetService.update("EARTH",  "земля");
        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
        planetService.deleteById("EARTH");
        System.out.println("[INFO] Show all data: \n" + planetService.showAll());
    }
}
