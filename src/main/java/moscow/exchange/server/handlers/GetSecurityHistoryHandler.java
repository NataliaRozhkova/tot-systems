package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;


public class GetSecurityHistoryHandler extends BaseHandler<String, Security> implements HttpHandler {

    private final Repository repository;

    public GetSecurityHistoryHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
        String[] parameters = httpExchangeParameters.getRequestURI().getQuery().split("=");
        return parameters[1];
    }

    @Override
    String handlePostRequest(HttpExchange httpExchangeParameters) {
        return null;
    }

    @Override
    Response<Security> requestRepository(String requestParameter) {
        return repository.readSecurity(requestParameter);
    }

    @Override
    String presentResponse(Response<Security> response) {
        if (response.state == Response.State.ERROR) {
            return "Security not found";
        }
        StringBuilder history = new StringBuilder();
        Security security = response.body;
        history.append("<h2>").append(security.getSecId()).append(" ").append(security.getName()).append("</h2>");

        history.append(TableDate.TRANSACTION_TABLE_HEAD);

        for (Transaction t : security.getTransactions()) {
            history.append(t.toStringXml());
        }
        history.append(TableDate.FINISH_TABLE);
        return history.toString();

    }
}
