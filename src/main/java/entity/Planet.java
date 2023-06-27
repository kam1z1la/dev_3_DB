package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "planet")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"ticketFromPlanet", "ticketToPlanet"})
public class Planet {
    @Id
    @Column(name = "id")
    @Pattern(regexp = "^[À-ß]+$", message = "All booths must be large")
    private String id;

    @Column(name = "name", length = 500)
    @Length(min = 1, max = 500, message = "Enter the word more for 1 and less for 500")
    private String name;

    @OneToMany(mappedBy = "from_planet", cascade = CascadeType.REMOVE)
    private List<Ticket> ticketFromPlanet = new LinkedList<>();

    @OneToMany(mappedBy = "to_planet", cascade = CascadeType.REMOVE)
    private List<Ticket> ticketToPlanet = new LinkedList<>();
}