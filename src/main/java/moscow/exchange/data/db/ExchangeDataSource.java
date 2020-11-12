package moscow.exchange.data.db;

import moscow.exchange.data.Response;
import moscow.exchange.data.db.dao.SecurityDAO;
import moscow.exchange.data.db.dao.TransactionDAO;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.entity.parser.SecurityParser;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExchangeDataSource {

    private final SessionFactory sessionFactory;

    public ExchangeDataSource() {
        this.sessionFactory = buildSessionFactory();
    }

    public Response<String> createSecurity(final Security security) {
        return new SecurityDAO(sessionFactory.openSession()).create(security);
    }

    public Response<String> createAllSecurities(final List<Security> securities) {
        StringBuilder response = new StringBuilder();
        for (Security s : securities) {
            response.append(createSecurity(s).body).append("\n");
        }
        return new Response<>(response.toString(), Response.State.SUCCESS);
    }

    public Response<Security> readSecurity(final String secId) {
        return new SecurityDAO(sessionFactory.openSession()).readBySecId(secId);
    }

    public Response<List<Security>> readAllSecurities() {
        return new SecurityDAO(sessionFactory.openSession()).readAll();
    }

    public Response<String> deleteSecurity(final String secId) {
        return new SecurityDAO(sessionFactory.openSession()).delete(secId);
    }

    public Response<String> updateSecurity(final Security security) {
        return new SecurityDAO(sessionFactory.openSession()).update(security);
    }

    public Response<String> createTransaction(final Transaction transaction) {
        Response<String> response = new TransactionDAO(sessionFactory.openSession()).create(transaction);
        if (response.body.equals("Security not found")) {
            try {
                URL url = new URL("https://iss.moex.com/iss/securities.xml?q=" + transaction.getSecId());
                InputStream stream = url.openStream();
                ArrayList<Security> security = new SecurityParser().parse(stream);
                stream.close();
                createAllSecurities(security);
                new TransactionDAO(sessionFactory.openSession()).create(transaction);
                response = new Response<>("Load new security from https://iss.moex.com", Response.State.SUCCESS);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        return response;
    }

    public Response<String> createAllTransaction(final List<Transaction> transactions) {
        StringBuilder response = new StringBuilder();
        for (Transaction t : transactions) {
            response.append(createTransaction(t).body).append("\n");
        }
        return new Response<>(response.toString(), Response.State.SUCCESS);
    }

    public Response<Transaction> readTransaction(final long id) {
        return new TransactionDAO(sessionFactory.openSession()).readById(id);
    }

    public Response<List<Transaction>> readAllTransactions() {
        return new TransactionDAO(sessionFactory.openSession()).readAll();
    }

    public Response<String> deleteTransaction(final long id) {
        return new TransactionDAO(sessionFactory.openSession()).delete(id);
    }

    public Response<String> updateTransaction(final Transaction transaction) {
        return new TransactionDAO(sessionFactory.openSession()).update(transaction);
    }

    public Response<List<Transaction>> readTransactionWithSortParameter(String sortParameter) {
        return new TransactionDAO(sessionFactory.openSession()).readWithSortParameters(sortParameter);
    }

    public Response<List<Transaction>> readTransactionWithFilterParameter(String value) {
        return new TransactionDAO(sessionFactory.openSession()).readWithFilterParameter(value);
    }

    public Response<List<Security>> readSecurityWithSortParameter(String sortParameter) {
        return new SecurityDAO(sessionFactory.openSession()).readWithSortParameters(sortParameter);
    }

    public Response<List<Security>> readSecurityWithFilterParameter(String value) {
        return new SecurityDAO(sessionFactory.openSession()).readWithFilterParameter(value);
    }


    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
