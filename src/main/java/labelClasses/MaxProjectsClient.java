package labelClasses;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MaxProjectsClient {
    private String name;
    private int max_project;

    @Override
    public String toString() {
        return "{" +
                "name='" + name  +
                ", max_project=" + max_project +
                "}";
    }
}
