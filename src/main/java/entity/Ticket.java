package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.TimeZone;

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
    private TimeZone created_at;

    @ManyToOne(optional = false)
    private Client client;

    @ManyToOne(optional = false)
    private Planet from_planet;

    @ManyToOne(optional = false)
    private Planet to_planet;

    public void populateTicket(Client client, Planet from_planet, Planet to_planet) {
        created_at = TimeZone.getTimeZone("UTC");
        this.client = client;
        this.from_planet = from_planet;
        this.to_planet = to_planet;
    }
}
