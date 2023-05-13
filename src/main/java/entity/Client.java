package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "{id=" + id +
                ", name='" + name
                + "'}";
    }
}
