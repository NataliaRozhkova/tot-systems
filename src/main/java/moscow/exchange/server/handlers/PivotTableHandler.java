package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.FileReader;

import java.io.IOException;
import java.io.OutputStream;

public class PivotTableHandler implements HttpHandler {

    private static final String PIVOTE_TABLE_HTML_PAGE = "src/main/resources/pivot_table.html";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String htmlPage = FileReader.getFile(PIVOTE_TABLE_HTML_PAGE);
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        httpExchange.sendResponseHeaders(200, htmlPage.getBytes().length);
        outputStream.write(htmlPage.getBytes());
        outputStream.flush();
        outputStream.close();
    }


}
