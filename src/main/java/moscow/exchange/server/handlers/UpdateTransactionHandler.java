package moscow.exchange.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;
import java.io.InputStream;

public class UpdateTransactionHandler extends BaseHandler<String, String> implements HttpHandler {

    private final Repository repository;

    public UpdateTransactionHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
        return null;
    }

    @Override
    String handlePostRequest(HttpExchange httpExchangeParameters) throws IOException {
        InputStream stream = httpExchangeParameters.getRequestBody();
        String json = new String(stream.readAllBytes());
        stream.close();
        return json;
    }

    @Override
    Response<String> requestRepository(String requestParameter) {
        Transaction transaction = new Gson().fromJson(requestParameter, Transaction.class);
        if (transaction.getSecId() == null) {
            return new Response<>("secid cannot be null", Response.State.ERROR);
        }
        transaction.setSecurity(repository.readSecurity(transaction.getSecId()).body);
        return repository.updateTransaction(transaction);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
