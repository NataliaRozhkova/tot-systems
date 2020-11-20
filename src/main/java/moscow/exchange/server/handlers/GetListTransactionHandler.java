package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;

import java.util.HashMap;
import java.util.List;

public class GetListTransactionHandler extends BaseHandler<HashMap<String, String>, List<Transaction>> implements HttpHandler {

    private final Repository repository;

    public GetListTransactionHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    HashMap<String, String> handleGetRequest(HttpExchange httpExchangeParameters) {
        return exchangeParametersFromRequest(httpExchangeParameters);
    }

    @Override
    HashMap<String, String> handlePostRequest(HttpExchange httpExchangeParameters) {
        return null;
    }

    @Override
    Response<List<Transaction>> requestRepository(HashMap<String, String> requestParameter) {
        return repository.readTransactionWithSortParameter(requestParameter.get("sort_by"),
                Integer.parseInt(requestParameter.get("limit")),
                Integer.parseInt(requestParameter.get("offset")));
    }

    @Override
    String presentResponse(Response<List<Transaction>> response) {
        if (response.state == Response.State.ERROR) {
            return "Security not found";
        }
        StringBuilder listTransaction = new StringBuilder();
        listTransaction.append(TableDate.TRANSACTION_TABLE_HEAD);

        for (Transaction s : response.body) {
            listTransaction.append(s.toStringXml());
        }
        return listTransaction.append(TableDate.FINISH_TABLE).toString();
    }
}
