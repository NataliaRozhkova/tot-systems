package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.repository.Repository;

import java.util.HashMap;
import java.util.List;

public class GetListSecurityHandler extends BaseHandler<HashMap<String, String>, List<Security>> implements HttpHandler {

    private final Repository repository;

    public GetListSecurityHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    HashMap<String, String> handleGetRequest(HttpExchange httpExchangeParameters)  {
        return exchangeParametersFromRequest(httpExchangeParameters);
    }

    @Override
    HashMap<String, String> handlePostRequest(HttpExchange httpExchangeParameters)  {
        return null;
    }

    @Override
    Response<List<Security>> requestRepository(HashMap<String, String> requestParameter) {
        return repository.readSecurityWithSortParameter(requestParameter.get("sort_parameter"),
                Integer.parseInt(requestParameter.get("limit")),
                Integer.parseInt(requestParameter.get("offset")));
    }

    @Override
    String presentResponse(Response<List<Security>> response) {
        if (response.state == Response.State.ERROR) {
            return "Security not found";
        }
        StringBuilder listSecurity = new StringBuilder();
        listSecurity.append(TableDate.SECURITY_TABLE_HEAD);

        for (Security s : response.body){
            listSecurity.append(s.toStringXml());
        }
        return listSecurity.append(TableDate.FINISH_TABLE).toString();
    }
}
