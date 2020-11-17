package moscow.exchange;

import moscow.exchange.data.db.ExchangeDataSource;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;
import moscow.exchange.server.ExchangeHttpServer;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader;
        String host;
        int port;

        try {
            reader = new BufferedReader(new FileReader(new File("src/main/resources/server_parameters.txt")));
            host = reader.readLine();
            port = Integer.parseInt(reader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            host = "localhost";
            port = 8001;
        }

        ExchangeHttpServer server = new ExchangeHttpServer(host, port, new Repository(new ExchangeDataSource()));
        server.start();



    }


}
