package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;

import java.util.HashMap;
import java.util.List;

public class GetPivotTableHandler extends BaseHandler<HashMap<String, String>, List<Transaction>> implements HttpHandler {

    private final Repository repository;

    public GetPivotTableHandler(Repository repository) {
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
        return repository.readTransactionWithFilterParameter(
                requestParameter.get("sort_by"),
                requestParameter.get("filter_by"),
                requestParameter.get("filter_value"),
                Integer.parseInt(requestParameter.get("limit")),
                Integer.parseInt(requestParameter.get("offset"))

        );
    }

    @Override
    String presentResponse(Response<List<Transaction>> response) {
        if (response.state == Response.State.ERROR) {
            return "Transaction not found";
        }
        StringBuilder listTransaction = new StringBuilder();
        listTransaction.append(TableDate.PIVOT_TABLE_HEAD);

        for (Transaction t : response.body) {
            listTransaction.append(t.pivotTableXml());
        }
        return listTransaction.append(TableDate.FINISH_TABLE).toString();
    }

}
