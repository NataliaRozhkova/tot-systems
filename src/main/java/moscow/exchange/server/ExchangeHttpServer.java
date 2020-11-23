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

    public ExchangeHttpServer(String host, int port, Repository repository) throws IOException {
        this.host = host;
        this.port = port;
        server = HttpServer.create(new InetSocketAddress(host, port), 0);
        server.createContext("/", new StartServiceHandler());
        server.createContext("/security", new SecurityHandler());
        server.createContext("/security/read", new GetSecurityHandler(repository));
        server.createContext("/security/history", new GetSecurityHistoryHandler(repository));
        server.createContext("/security/add", new CreateSecurityHandler(repository));
        server.createContext("/security/add/all", new CreateAllSecurityHandler(repository));
        server.createContext("/security/update", new UpdateSecurityHandler(repository));
        server.createContext("/security/delete", new DeleteSecurityHandler(repository));
        server.createContext("/security/list", new GetListSecurityHandler(repository));
        server.createContext("/transaction", new TransactionHandler());
        server.createContext("/transaction/add", new CreateTransactionHandler(repository));
        server.createContext("/transaction/add/all", new CreateAllTransactionsHandler(repository));
        server.createContext("/transaction/delete", new DeleteTransactionHandler(repository));
        server.createContext("/transaction/update", new UpdateTransactionHandler(repository));
        server.createContext("/transaction/list", new GetListTransactionHandler(repository));
        server.createContext("/pivot_table", new PivotTableHandler());
        server.createContext("/pivot_table/list", new GetPivotTableHandler(repository));
        server.setExecutor(Executors.newFixedThreadPool(10));
    }

    public void start() {
        server.start();
    }

    public  void  stop(int stop) {
        server.stop(stop);
    }
}
