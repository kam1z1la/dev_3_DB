package Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class File {
    public String readFile(String filePath) {
        StringJoiner joiner = new StringJoiner("\n");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.ready()) {
                joiner.add(br.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(joiner);
    }

    public static String getQueryFromFile(String file, String command) {
        StringBuilder builder = new StringBuilder();
        String[] lines = file.split(";");
        for (String line : lines) {
            if (line.contains(command)) {
                builder.append(line.replace(command,"").replace("values",""));
            }
        }
        return builder.toString().replaceAll("[()'']", "\n").trim();
    }

    public static Object[] createArray(String data) {
        return Stream.of(data.replaceAll("[\\s+]"," ").split(",")).toArray();
    }
}
