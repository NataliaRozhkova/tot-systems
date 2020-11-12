package moscow.exchange.server;

import com.sun.net.httpserver.HttpServer;
import moscow.exchange.data.repository.Repository;
import moscow.exchange.server.handlers.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class ExchangeHttpServer {

    final String host;
    final int port;
    final HttpServer server;
    private Repository repository ;

    public ExchangeHttpServer(String host, int port, Repository repository) throws IOException {
        this.host = host;
        this.port = port;
        this.repository = repository;
        server = HttpServer.create(new InetSocketAddress(host, port), 0);
        server.createContext("/", new StartServiceHandler());
        server.createContext("/security", new SecurityHandler());
        server.createContext("/security/read", new GetSecurityHandler(repository));
        server.createContext("/security/history", new GetSecurityHistoryHandler(repository));
        server.createContext("/security/add", new SecurityCreateServiceHandler());
        server.createContext("/new", new CreateSecurityHandler(repository));
        server.setExecutor(Executors.newFixedThreadPool(10));
    }

    public void start() {
        server.start();
    }
}
