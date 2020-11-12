package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;

public class GetSecurityHandler extends BaseHandler<String,Security> implements HttpHandler {

    private final Repository repository;

    public GetSecurityHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchange) throws IOException {
        String[] httpExchangeParameters = httpExchange.getRequestURI().getQuery().split("=");
        String secidValue = httpExchangeParameters[1];
        return secidValue;
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
        return new StringBuilder()
                .append(TableDate.SECURITY_TABLE_HEAD)
                .append(response.body.toStringXml()).append(TableDate.FINISH_TABLE).toString();
    }
}
