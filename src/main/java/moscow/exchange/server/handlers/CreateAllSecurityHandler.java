package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.entity.parser.SecurityParser;
import moscow.exchange.data.repository.Repository;
import moscow.exchange.FileReader;

import java.io.*;
import java.util.List;

public class CreateAllSecurityHandler extends BaseHandler<String, String> implements HttpHandler {

    private final String OUTPUT = "src/main/resources/output.xml";
    private final String CREATE_ALL_SECURITY_HTML_PAGE = "src/main/resources/security_add_all.html";

    private final Repository repository;

    public CreateAllSecurityHandler(Repository repository) {
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
        String xml = new String(stream.readAllBytes());
        stream.close();
        FileWriter writer = new FileWriter(OUTPUT);
        String document = xml.split("<document>")[1].split("</document>")[0];
        writer.write(document);
        writer.flush();
        writer.close();
        return OUTPUT;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
        return FileReader.getFile(CREATE_ALL_SECURITY_HTML_PAGE);
    }

    @Override
    Response<String> requestRepository(String requestParameter) {
        List<Security> securities = new SecurityParser().parse(new File(requestParameter));
        return repository.createAllSecurities(securities);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}
