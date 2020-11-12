package moscow.exchange;

import moscow.exchange.data.db.ExchangeDataSource;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.entity.parser.SecurityParser;
import moscow.exchange.data.entity.parser.TransactionParser;
import moscow.exchange.data.repository.Repository;
import moscow.exchange.server.ExchangeHttpServer;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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


//        ExchangeDataSource dataSource = new ExchangeDataSource();
//        ArrayList<Transaction> securities = (ArrayList<Transaction>) dataSource.readAllTransactions().body;
//        StringBuilder builder = new StringBuilder();
//        for (Transaction s : securities) {
//            builder.append(s.toString() + "\n");
//        }
//        System.out.println(builder.toString());


    }


}
