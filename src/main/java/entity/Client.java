package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"ticket"})
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 200)
    @Length(min = 3, max = 200, message = "Enter the word more for 3 and less for 200")
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Ticket> ticket = new LinkedList<>();
}
