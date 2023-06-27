package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.Transaction;
import query.Database;

import java.time.LocalTime;
import java.time.ZoneId;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Ticket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at", nullable = false)
    private LocalTime created_at;

    @ManyToOne(optional = false)
    private Client client;

    @ManyToOne(optional = false)
    private Planet from_planet;

    @ManyToOne(optional = false)
    private Planet to_planet;

    public void populateTicket(Client client, Planet from_planet, Planet to_planet) {
        created_at = LocalTime.now(ZoneId.of("UTC"));
        this.client = client;
        this.from_planet = from_planet;
        this.to_planet = to_planet;
    }

    public void populateEntityList(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = Database.INSTANCE.getConnection().openSession()) {
            transaction = session.beginTransaction();
            client.getTicket().add(ticket);
            from_planet.getTicketFromPlanet().add(ticket);
            to_planet.getTicketToPlanet().add(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
