package LabelClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LongestProject {
    private int id;
    private int month_count;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", month_count=" + month_count +
                "}";
    }
}
