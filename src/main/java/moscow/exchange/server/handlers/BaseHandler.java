package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.io.OutputStream;

public abstract class BaseHandler<T, K> implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        T requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handlePostRequest(httpExchange);
        }
        handleResponse(httpExchange, presentResponse(requestRepository(requestParamValue)));
    }

    abstract T handleGetRequest(HttpExchange httpExchangeParameters) throws IOException;

    abstract T handlePostRequest(HttpExchange httpExchangeParameters) throws IOException;

    abstract Response<K> requestRepository(T requestParameter);

    abstract String presentResponse(Response<K> response);


    private void handleResponse(HttpExchange httpExchange, String response) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
