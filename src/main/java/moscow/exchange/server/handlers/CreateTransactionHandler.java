package moscow.exchange.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.entity.parser.SecurityParser;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CreateTransactionHandler extends BaseHandler<String, String> implements HttpHandler {

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
    Response<String> requestRepository(String requestParameter) throws IOException {
        Transaction transaction = new Gson().fromJson(requestParameter, Transaction.class);
        Security security = repository.readSecurity(transaction.getSecId()).body;
        if (security == null) {
            URL url = new URL("https://iss.moex.com/iss/securities.xml?q=" + transaction.getSecId());
            InputStream stream = url.openStream();
            ArrayList<Security> securities = new SecurityParser().parse(stream);
            stream.close();
            repository.createAllSecurities(securities);
            security = repository.readSecurity(transaction.getSecId()).body;
        }
        if (security == null && transaction.getSecId() == null) {
        return  new Response<>("secid cannot be null", Response.State.ERROR);
        }
        if (security == null && transaction.getSecId() != null) {
            return  new Response<>("security not found", Response.State.ERROR);
        }
        transaction.setSecurity(security);
        return repository.createTransaction(transaction);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
