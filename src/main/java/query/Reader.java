package query;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;
import java.util.StringJoiner;
import java.io.FileReader;

public class Reader {
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

    public Properties readPropertiesFile(String filePath) {
        Properties properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.ready()) {
                properties.load(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
