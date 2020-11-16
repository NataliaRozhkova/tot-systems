package moscow.exchange.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;
import java.io.InputStream;

public class UpdateSecurityHandler extends BaseHandler<String, String> implements HttpHandler {

    private final Repository repository;

    public UpdateSecurityHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    String handlePostRequest(HttpExchange httpExchangeParameters) throws IOException {
        InputStream stream = httpExchangeParameters.getRequestBody();
        String json = new String(stream.readAllBytes());
        stream.close();
        System.out.println(json);
        return json;
    }

    @Override
    Response<String> requestRepository(String requestParameter) {
        return repository.updateSecurity(new Gson().fromJson(requestParameter, Security.class));
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
        return null;
    }
}
