package Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class File {
    public String readFile(String filePath){
        StringJoiner joiner = new StringJoiner("\n");
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while(br.ready()){
                joiner.add(br.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(joiner);
    }
}
