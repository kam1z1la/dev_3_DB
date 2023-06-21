package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 200)
    @Length(min = 3, max = 200, message = "Enter the word more for 3 and less for 200")
    private String name;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name=" + name +
                "}";
    }
}