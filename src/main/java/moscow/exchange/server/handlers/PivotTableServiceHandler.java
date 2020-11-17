package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class PivotTableServiceHandler implements HttpHandler {
    String htmlPage = getFile();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        handleResponse(exchange);
    }

    private void handleResponse(HttpExchange httpExchange) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        httpExchange.sendResponseHeaders(200, htmlPage.getBytes().length);
        outputStream.write(htmlPage.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private static String getFile() {
        BufferedReader reader;
        StringBuilder html = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(new File("src/main/resources/pivot_table.html")));
            String line = reader.readLine();
            while (line != null) {
                html.append(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html.toString();
    }
}
