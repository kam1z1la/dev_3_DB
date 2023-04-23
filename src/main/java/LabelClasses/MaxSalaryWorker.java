package LabelClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MaxSalaryWorker {
    private String name;
    private int max_salary;

    @Override
    public String toString() {
        return   "{" +
                "name='" + name +
                ", max_salary=" + max_salary +
                "}";
    }
}
