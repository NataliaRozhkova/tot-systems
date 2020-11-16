package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.repository.Repository;

import java.io.*;

public class UpdateSecurityServiceHandler extends BaseHandler<String, Security> implements HttpHandler {

    private final Repository repository;

    public UpdateSecurityServiceHandler(Repository repository) {
        this.repository = repository;
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
        return TableDate.SECURITY_TABLE_HEAD +
                response.body.toStringXml() + TableDate.FINISH_TABLE + getFile();
    }

    private static String getFile() {
        BufferedReader reader;
        StringBuilder html = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(new File("src/main/resources/security_update.html")));
            String line = reader.readLine();
            while (line != null) {
                html.append(line).append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html.toString();
    }

}
