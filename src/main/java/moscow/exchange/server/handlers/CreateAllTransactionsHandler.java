package moscow.exchange.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.entity.parser.TransactionParser;
import moscow.exchange.data.repository.Repository;
import moscow.exchange.FileReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CreateAllTransactionsHandler extends BaseHandler<String, String> implements HttpHandler {
    
    private static final String DOWNLOAD_XML_FILE = "src/main/resources/output.xml";
    private final String CREATE_ALL_TRANSACTIONS_HTML_PAGE = "src/main/resources/transaction_add_all.html";

    private final Repository repository;

    public CreateAllTransactionsHandler(Repository repository) {
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
        FileWriter writer = new FileWriter(DOWNLOAD_XML_FILE);
        writer.write("<document>");
        writer.write(xml.split("<document>")[1].split("</document>")[0]);
        writer.write("</document>");
        writer.flush();
        writer.close();
        return DOWNLOAD_XML_FILE;
    }

    @Override
    String handleGetRequest(HttpExchange httpExchangeParameters) {
        return FileReader.getFile(CREATE_ALL_TRANSACTIONS_HTML_PAGE);
    }

    @Override
    Response<String> requestRepository(String requestParameter) throws IOException {
        TransactionParser.removeArray();
        ArrayList<Transaction> transactions = new TransactionParser().parse(new File(requestParameter));
        return repository.createAllTransaction(transactions);
    }

    @Override
    String presentResponse(Response<String> response) {
        return response.body;
    }
}

