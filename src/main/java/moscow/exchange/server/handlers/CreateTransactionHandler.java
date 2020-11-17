package moscow.exchange.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;
import java.io.InputStream;

public class CreateTransactionHandler  extends BaseHandler<String, String> implements HttpHandler {

    public CreateTransactionHandler(Repository repository) {
        this.repository = repository;
    }

    private final Repository repository;

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
        Transaction transaction = new Gson().fromJson(requestParameter, Transaction.class);
        transaction.setSecurity(repository.readSecurity(transaction.getSecId()).body);
        return repository.createTransaction(transaction);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
