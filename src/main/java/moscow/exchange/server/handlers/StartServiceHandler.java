package moscow.exchange.server.handlers;

import moscow.exchange.FileReader;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class StartServiceHandler implements HttpHandler {

    private final String START_SERVICE_HTML_PAGE = "src/main/resources/start_service.html";

    String htmlPage = FileReader.getFile(START_SERVICE_HTML_PAGE);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        httpExchange.sendResponseHeaders(200, htmlPage.getBytes().length);
        outputStream.write(htmlPage.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
