package moscow.exchange.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.FileReader;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.repository.Repository;

import java.io.IOException;
import java.io.InputStream;

public class CreateSecurityHandler extends BaseHandler<String, String> implements HttpHandler {

    private final String CREATE_SECURITY_HTML_PAGE_PATH = "src/main/resources/security_add.html";

    private final Repository repository;

    public CreateSecurityHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
            handleResponse(httpExchange, requestParamValue);
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handlePostRequest(httpExchange);
            handleResponse(httpExchange, presentResponse(requestRepository(requestParamValue)));
        }
    }

    @Override
    String handlePostRequest(HttpExchange httpExchangeParameters) throws IOException {
        InputStream stream = httpExchangeParameters.getRequestBody();
        String json = new String(stream.readAllBytes());
        stream.close();
        return json;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
        return FileReader.getFile(CREATE_SECURITY_HTML_PAGE_PATH);
    }

    @Override
    Response<String> requestRepository(String requestParameter) {
        Security security = new Gson().fromJson(requestParameter, Security.class);
        if (security.getName() != null && security.getSecId() != null) {
            return security.getName().matches("^[а-яА-ЯёЁ0-9 ]+$") ?
                    repository.createSecurity(security) :
                    new Response<>(" Error \n В поле name могут быть только кириллица, цифры и пробел", Response.State.ERROR);
        } else

            return new Response<>(" Error \n Поля name и secid не могут быть пустыми", Response.State.ERROR);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
