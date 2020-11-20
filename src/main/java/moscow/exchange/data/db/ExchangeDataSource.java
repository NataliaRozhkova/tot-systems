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

    private final String url;

    private final SessionFactory sessionFactory;

    public ExchangeDataSource(String url) {

        this.sessionFactory = buildSessionFactory();
        this.url = url;
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

    public Response<String> createSecurity(final Security security) {
        return new SecurityDAO(sessionFactory.openSession()).create(security);
    }

    public Response<String> createAllSecurities(final List<Security> securities) {
        StringBuilder response = new StringBuilder();
        int count = 0;
        for (Security s : securities) {
            if (createSecurity(s).state == Response.State.SUCCESS) count++;
        }
        return new Response<>("Добавлено записей " + count, Response.State.SUCCESS);
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

    public Response<String> createTransaction(final Transaction transaction) throws IOException {
        Security security = readSecurity(transaction.getSecId()).body;
        if (security == null) {
            URL url = new URL(this.url + transaction.getSecId());
            InputStream stream = url.openStream();
            ArrayList<Security> securities = new SecurityParser().parse(stream);
            stream.close();
            createAllSecurities(securities);
            security = readSecurity(transaction.getSecId()).body;
        }
        if (security == null && transaction.getSecId() == null) {
            return new Response<>("secid cannot be null", Response.State.ERROR);
        }
        if (security == null && transaction.getSecId() != null) {
            return new Response<>("security not found", Response.State.ERROR);
        }
        transaction.setSecurity(security);
        return new TransactionDAO(sessionFactory.openSession()).create(transaction);
    }

    public Response<String> createAllTransaction(List<Transaction> transactions) throws IOException {
        StringBuilder response = new StringBuilder();
        int count = 0;
        for (Transaction t : transactions) {
            if (createTransaction(t).state == Response.State.SUCCESS) {
                count++;
            }
        }
        return new Response<>("Добавлено записей " + count, Response.State.SUCCESS);
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

    public Response<List<Transaction>> readTransactionWithSortParameter(String sortParameter, int limit, int offset) {
        return new TransactionDAO(sessionFactory.openSession()).readWithSortParameters(sortParameter, limit, offset);
    }

    public Response<List<Transaction>> readTransactionWithFilterParameter(String sortParameter, String filterParameter, String value, int limit, int offset) {
        return new TransactionDAO(sessionFactory.openSession()).readWithFilterAndSortParameter(sortParameter, filterParameter, value, limit, offset);
    }

    public Response<List<Security>> readSecurityWithSortParameter(String sortParameter, int limit, int offset) {
        return new SecurityDAO(sessionFactory.openSession()).readWithSortParameters(sortParameter, limit, offset);
    }

    public Response<List<Security>> readSecurityWithFilterParameter(String value) {
        return new SecurityDAO(sessionFactory.openSession()).readWithFilterParameter(value);
    }
}
