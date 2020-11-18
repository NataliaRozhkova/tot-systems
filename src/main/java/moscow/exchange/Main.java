package moscow.exchange;

import com.google.gson.Gson;
import moscow.exchange.data.db.DBConfig;
import moscow.exchange.data.db.ExchangeDataSource;
import moscow.exchange.data.repository.Repository;
import moscow.exchange.server.ExchangeHttpServer;
import moscow.exchange.server.ServerConfig;

import java.io.*;

public class Main {

    private static final String DB_CONFIG_FILE_PATH = "src/main/resources/db_config.txt";
    private static final String SERVER_CONFIG_FILE_PATH = "src/main/resources/server_config.txt";

    public static void main(String[] args) throws IOException {

        ServerConfig config = new Gson().fromJson(FileReader.getFile(SERVER_CONFIG_FILE_PATH),ServerConfig.class);
        DBConfig dbConfig = new Gson().fromJson(FileReader.getFile(DB_CONFIG_FILE_PATH),DBConfig.class);
        dbConfig.setHibernateConfigFile();
        ExchangeHttpServer server = new ExchangeHttpServer(config.host, config.port, new Repository(new ExchangeDataSource()));
        server.start();

    }
}
