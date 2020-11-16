package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;

public class DeleteSecurityHandler extends BaseHandler<String, String> implements HttpHandler {

    public DeleteSecurityHandler(Repository repository) {
        this.repository = repository;
    }

    private final Repository repository;


    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
        String[] httpExchange = httpExchangeParameters.getRequestURI().getQuery().split("=");
        return httpExchange[1];
    }

    @Override
    String handlePostRequest(HttpExchange httpExchangeParameters) {
        return null;
    }

    @Override
    Response<String> requestRepository(String requestParameter) {
        return repository.deleteSecurity(requestParameter);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
