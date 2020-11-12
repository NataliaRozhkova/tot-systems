package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.converter.SecurityJSONConverter;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;
import java.io.InputStream;

public class CreateSecurityHandler extends BaseHandler<String, String> implements HttpHandler {

    private final Repository repository;

    public CreateSecurityHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    String handlePostRequest(HttpExchange httpExchangeParameters) throws IOException {
        System.out.println("!!!!!!!!!!!!!!!!!!");

        InputStream stream = httpExchangeParameters.getRequestBody();
        StringBuilder json = new StringBuilder();
        int jsonByte;
        while ((jsonByte = stream.read()) != -1) {
            json.append((char) jsonByte);
        }
        stream.close();
        System.out.println(json.toString());

        return json.toString();
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters)  {
        return null;
    }

    @Override
    Response<String> requestRepository(String requestParameter) {
        return repository.createSecurity(SecurityJSONConverter.deserialize(requestParameter));
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
