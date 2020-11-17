package moscow.exchange.server.handlers;

import com.google.gson.Gson;
import moscow.exchange.server.FileReader;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.repository.Repository;

import java.io.*;

public class UpdateSecurityHandler extends BaseHandler<String, Security> implements HttpHandler {

    private final Repository repository;

    public UpdateSecurityHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
            handleResponse(httpExchange, presentResponse(requestRepository(requestParamValue)));
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handlePostRequest(httpExchange);
            handleResponse(httpExchange, (requestRepositoryToUpdateSecurity(requestParamValue)));

        }
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
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
    Response<Security> requestRepository(String requestParameter) {
        return repository.readSecurity(requestParameter);
    }

    String requestRepositoryToUpdateSecurity(String requestParameter) {
        return repository.updateSecurity(new Gson().fromJson(requestParameter, Security.class)).body;
    }

    @Override
    String presentResponse(Response<Security> response) {
        if (response.state == Response.State.ERROR) {
            return "Security not found";
        }
        return TableDate.SECURITY_TABLE_HEAD +
                response.body.toStringXml() + TableDate.FINISH_TABLE + FileReader.getFile("src/main/resources/security_update.html");
    }


}
