package LabelClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class YoungestEldestWorkers {
    private String name;
    private LocalDate birthday;
    private String type;

    @Override
    public String toString() {
        return "{" +
                "name='" + name +
                ", birthday=" + birthday +
                ", type='" + type  +
                "}";
    }
}
