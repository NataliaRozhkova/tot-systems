package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetListTransactionHandler extends BaseHandler<HashMap<String, String>, List<Transaction>> implements HttpHandler {

    private final Repository repository;

    public GetListTransactionHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    HashMap<String, String> handleGetRequest(HttpExchange httpExchangeParameters) throws IOException {
        String httpParameters = httpExchangeParameters.getRequestURI().getQuery();
        if (httpExchangeParameters != null) {
            HashMap<String, String> parameters = new HashMap<>();
            for (String parameter : httpParameters.split("&")) {
                String[] pair = parameter.split("=");
                if (pair.length > 1) {
                    parameters.put(pair[0], pair[1]);
                }
            }
            return parameters;
        } else return null;
    }

    @Override
    HashMap<String, String> handlePostRequest(HttpExchange httpExchangeParameters) throws IOException {
        return null;
    }

    @Override
    Response<List<Transaction>> requestRepository(HashMap<String, String> requestParameter) throws IOException {
        return repository.readTransactionWithSortParameter(requestParameter.get("sort_parameter"),
                Integer.parseInt(requestParameter.get("limit")),
                Integer.parseInt(requestParameter.get("offset")));    }

    @Override
    String presentResponse(Response<List<Transaction>> response) {
         if (response.state == Response.State.ERROR) {
            return "Security not found";
        }
        StringBuilder listTransaction = new StringBuilder();
        listTransaction.append(TableDate.TRANSACTION_TABLE_HEAD);

        for (Transaction s : response.body){
            listTransaction.append(s.toStringXml());
        }
        return listTransaction.append(TableDate.FINISH_TABLE).toString();
    }
}
