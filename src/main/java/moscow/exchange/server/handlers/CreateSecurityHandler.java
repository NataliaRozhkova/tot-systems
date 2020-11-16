package moscow.exchange.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
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
        InputStream stream = httpExchangeParameters.getRequestBody();
        String json = new String(stream.readAllBytes());
        stream.close();
        return json;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
        return null;
    }

    @Override
    Response<String> requestRepository(String requestParameter) {
        Security security = new Gson().fromJson(requestParameter, Security.class);


        return security.getName().matches("^[а-яА-ЯёЁ0-9 ]+$") ?
                repository.createSecurity(security) :
                new Response<>(" Error \n В поле name могут быть только кириллица, цифры и пробел", Response.State.ERROR);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
