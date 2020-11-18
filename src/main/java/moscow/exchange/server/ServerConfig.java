package moscow.exchange.server;

import com.google.gson.Gson;
import moscow.exchange.FileReader;

public class ServerConfig {

    private static final String CONFIG_FILE_PATH = "src/main/resources/server_config.txt";
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 8001;

    public static ServerConfig instance;

    public String host;
    public int port;


    private ServerConfig(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static ServerConfig getInstance() {
        if (instance == null) {
            instance = new Gson().fromJson(FileReader.getFile(CONFIG_FILE_PATH), ServerConfig.class);
            if (instance == null) {
                instance = new ServerConfig(DEFAULT_HOST, DEFAULT_PORT);
            }
        }
        return instance;
    }
}
