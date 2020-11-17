package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;

public class DeleteTransactionHandler extends BaseHandler<String, String> implements HttpHandler {

    private final Repository repository;

    public DeleteTransactionHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) throws IOException {
        String[] httpExchange = httpExchangeParameters.getRequestURI().getQuery().split("=");
        return httpExchange[1];
    }

    @Override
    String handlePostRequest(HttpExchange httpExchangeParameters) throws IOException {
        return null;
    }

    @Override
    Response<String> requestRepository(String requestParameter) {
        return repository.deleteTransaction(Long.parseLong(requestParameter));
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
