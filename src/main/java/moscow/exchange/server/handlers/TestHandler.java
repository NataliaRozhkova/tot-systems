package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;

import java.io.*;

public class TestHandler extends BaseHandler<String, String> implements HttpHandler {
    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) throws IOException {
        return getFile();
    }

    @Override
    String handlePostRequest(HttpExchange httpExchangeParameters) throws IOException {
        InputStream stream = httpExchangeParameters.getRequestBody();
        String testFile = new String(stream.readAllBytes());
        stream.close();
        System.out.println(testFile);
        return testFile;
    }

    @Override
    Response<String> requestRepository(String requestParameter) throws IOException {
        return new Response<>(requestParameter, Response.State.SUCCESS);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }

    private static String getFile() {
        BufferedReader reader;
        StringBuilder html = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(new File("src/main/resources/.html")));
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
