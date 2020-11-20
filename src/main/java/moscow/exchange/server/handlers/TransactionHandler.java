package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.FileReader;

import java.io.IOException;
import java.io.OutputStream;

public class TransactionHandler implements HttpHandler {

    private static final String TRANSACTION_HTML_PAGE_PATH = "src/main/resources/transaction.html";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String htmlPage = FileReader.getFile(TRANSACTION_HTML_PAGE_PATH);
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        httpExchange.sendResponseHeaders(200, htmlPage.getBytes().length);
        outputStream.write(htmlPage.getBytes());
        outputStream.flush();
        outputStream.close();
    }


}
