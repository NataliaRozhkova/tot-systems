package moscow.exchange.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;
import moscow.exchange.FileReader;

import java.io.*;

public class UpdateTransactionHandler extends BaseHandler<String, Transaction> implements HttpHandler {

    private static final String UPDATE_TRANSACTION_HTML_PAGE_PATH = "src/main/resources/transaction_update.html";
    private final Repository repository;


    public UpdateTransactionHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String requestParamValue;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
            handleResponse(httpExchange, presentResponse(requestRepository(requestParamValue)));
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handlePostRequest(httpExchange);
            handleResponse(httpExchange, (requestRepositoryToUpdateTransaction(requestParamValue)));

        }
    }

    private String requestRepositoryToUpdateTransaction(String requestParamValue) {
        Transaction transaction = new Gson().fromJson(requestParamValue, Transaction.class);
        if (transaction.getSecId() == null) {
            return "secid cannot be null";
        }
        transaction.setSecurity(repository.readSecurity(transaction.getSecId()).body);
        return repository.updateTransaction(transaction).body;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) throws IOException {
        String requestParameters = httpExchangeParameters.getRequestURI().getQuery();
        if (requestParameters != null) {
            String[] pair = requestParameters.split("=");
            return pair[1];
        } else return null;
    }

    @Override
    String handlePostRequest(HttpExchange httpExchangeParameters) throws IOException {
        InputStream stream = httpExchangeParameters.getRequestBody();
        String json = new String(stream.readAllBytes());
        stream.close();
        return json;
    }

    @Override
    Response<Transaction> requestRepository(String requestParameter) {
        return repository.readTransaction(Long.parseLong(requestParameter));
    }

    @Override
    String presentResponse(Response<Transaction> response) {
        if (response.state == Response.State.ERROR || response.body == null) {
            return "Transaction not found";
        }
        return TableDate.TRANSACTION_TABLE_HEAD +
                response.body.toStringXml() + TableDate.FINISH_TABLE + FileReader.getFile(UPDATE_TRANSACTION_HTML_PAGE_PATH);
    }


}