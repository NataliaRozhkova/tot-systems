package moscow.exchange;

import moscow.exchange.data.db.DBConfig;
import moscow.exchange.data.db.ExchangeDataSource;
import moscow.exchange.data.repository.Repository;
import moscow.exchange.server.ExchangeHttpServer;
import moscow.exchange.server.ServerConfig;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        ServerConfig config = ServerConfig.getInstance();
        DBConfig dbConfig = DBConfig.getInstance();
        dbConfig.setHibernateConfigFile();
        ExchangeHttpServer server = new ExchangeHttpServer(config.host, config.port, new Repository(new ExchangeDataSource()));
        server.start();

    }
}
