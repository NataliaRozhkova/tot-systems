package moscow.exchange.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader {

    public static String getFile(String path) {
        BufferedReader reader;
        StringBuilder html = new StringBuilder();
        try {
            reader = new BufferedReader(new java.io.FileReader(new File(path)));
            String line = reader.readLine();
            while (line != null) {
                html.append(line).append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html.toString();
    }


}
