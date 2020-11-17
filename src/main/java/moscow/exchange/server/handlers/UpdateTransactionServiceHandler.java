package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UpdateTransactionServiceHandler extends BaseHandler<String, Transaction> implements HttpHandler {

    private final Repository repository;


    public UpdateTransactionServiceHandler(Repository repository) {
        this.repository = repository;
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
    String handlePostRequest(HttpExchange httpExchangeParameters)  {
        return null;
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
                response.body.toStringXml() + TableDate.FINISH_TABLE + getFile();
    }

    private static String getFile() {
        BufferedReader reader;
        StringBuilder html = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(new File("src/main/resources/transaction_update.html")));
            String line = reader.readLine();
            while (line != null) {
                html.append(line).append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html.toString();
    }
}