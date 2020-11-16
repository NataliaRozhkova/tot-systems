package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.repository.Repository;


public class GetSecurityHandler extends BaseHandler<String, Security> implements HttpHandler {

    private final Repository repository;

    public GetSecurityHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
        String[] httpExchange = httpExchangeParameters.getRequestURI().getQuery().split("=");
        return httpExchange[1];
    }

    @Override
    String handlePostRequest(HttpExchange httpExchange) {
        return null;
    }

    @Override
    Response<Security> requestRepository(String request) {
        return repository.readSecurity(request);
    }

    @Override
    String presentResponse(Response<Security> response) {
        if (response.state == Response.State.ERROR) {
            return "Security not found";
        }
        return TableDate.SECURITY_TABLE_HEAD +
                response.body.toStringXml() + TableDate.FINISH_TABLE;
    }
}
