package LabelClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProjectPrices {
    private int id;
    private int price;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", price=" + price +
                "}";
    }
}
