package dao;

import entity.Client;
import lombok.Data;
import query.Database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Data
public class ClientService implements Service<Client> {
    private Client client;

    public ClientService() {
        client = new Client();
    }

    private long create(String name) {
        try (PreparedStatement create = Database.INSTANCE.getConnection()
                .prepareStatement("INSERT INTO client(name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            create.setString(1, name);
            create.executeUpdate();
            try (ResultSet resultSet = create.getGeneratedKeys()) {
                if (resultSet.next()) {
                    client.setId(resultSet.getLong("id"));
                }
            }
            return client.getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setName(long id, String name) {
        try (PreparedStatement update = Database.INSTANCE.getConnection()
                .prepareStatement("UPDATE client SET name=? WHERE id=?")) {
            update.setString(1, name);
            update.setLong(2, id);
            update.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getById(long id) {
        try (PreparedStatement read = Database.INSTANCE.getConnection().prepareStatement("SELECT * FROM client");
             ResultSet resultSet = read.executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getLong("id") == id) {
                    client.setName(resultSet.getString("name"));
                }
            }
            return Optional.ofNullable(client.getName())
                    .orElseGet(() -> "Id not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (PreparedStatement delete = Database.INSTANCE.getConnection().prepareStatement("DELETE FROM client WHERE id=?")) {
            delete.setLong(1, id);
            delete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> listAll() {
        try (PreparedStatement read = Database.INSTANCE.getConnection().prepareStatement("SELECT * FROM client");
             ResultSet resultSet = read.executeQuery()) {
            List<Client> clientList = new LinkedList<>();
            while (resultSet.next()) {
                clientList.add(new Client(resultSet.getLong("id"), resultSet.getString("name")));
            }
            return clientList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        System.out.println("ID new create user: " + clientService.create("Anton Kravchenko"));
        clientService.setName(1, "Mikola");
        clientService.deleteById(1);
        System.out.println("Get By Id: " + clientService.getById(1));
        System.out.println("All data table: " + clientService.listAll());
        Database.closedConnection(Database.INSTANCE.getConnection());
    }
}
