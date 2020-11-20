package moscow.exchange.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;
import moscow.exchange.FileReader;

import java.io.IOException;
import java.io.InputStream;

public class CreateTransactionHandler extends BaseHandler<String, String> implements HttpHandler {

    private final String CREATE_TRANSACTION_HTML_PAGE_PATH ="src/main/resources/transaction_add.html";

    private final Repository repository;

    public CreateTransactionHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String requestParamValue;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
            handleResponse(httpExchange, requestParamValue);
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handlePostRequest(httpExchange);
            handleResponse(httpExchange, presentResponse(requestRepository(requestParamValue)));
        }
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
        return FileReader.getFile(CREATE_TRANSACTION_HTML_PAGE_PATH);
    }

    @Override
    Response<String> requestRepository(String requestParameter) throws IOException {
        Transaction transaction = new Gson().fromJson(requestParameter, Transaction.class);

        return repository.createTransaction(transaction);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
