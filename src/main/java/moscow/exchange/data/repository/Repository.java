package moscow.exchange.data.repository;

import moscow.exchange.data.Response;
import moscow.exchange.data.db.ExchangeDataSource;
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

public class Repository {

    public final ExchangeDataSource dataSource;

    public Repository(ExchangeDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Response<String> createSecurity(final Security security) {
        return dataSource.createSecurity(security);
    }

    public Response<String> createAllSecurities(final List<Security> securities) {
        return dataSource.createAllSecurities(securities);
    }

    public Response<Security> readSecurity(final String secid) {
        return dataSource.readSecurity(secid);
    }

    public Response<List<Security>> readAllSecurities() {
        return dataSource.readAllSecurities();
    }

    public Response<String> deleteSecurity(final String secid) {
        return deleteSecurity(secid);
    }

    public Response<String> updateSecurity(final Security security) {
        return dataSource.updateSecurity(security);
    }

    public Response<String> createTransaction(final Transaction transaction) {
        return dataSource.createTransaction(transaction);
    }

    public Response<String> createAllTransaction(final List<Transaction> transactions) {
        return dataSource.createAllTransaction(transactions);
    }

    public Response<Transaction> readTransaction(final long id) {
        return dataSource.readTransaction(id);
    }

    public Response<List<Transaction>> readAllTransactions() {
        return dataSource.readAllTransactions();
    }

    public Response<String> deleteTransaction(final long id) {
        return dataSource.deleteTransaction(id);
    }

    public Response<String> updateTransaction(final Transaction transaction) {
        return dataSource.updateTransaction(transaction);
    }

    public Response<List<Transaction>> readTransactionWithSortParameter(String sortParameter) {
        return dataSource.readTransactionWithSortParameter(sortParameter);
    }

    public Response<List<Transaction>> readTransactionWithFilterParameter(String value) {
        return dataSource.readTransactionWithFilterParameter(value);
    }

    public Response<List<Security>> readSecurityWithSortParameter(String sortParameter) {
        return dataSource.readSecurityWithSortParameter(sortParameter);
    }

    public Response<List<Security>> readSecurityWithFilterParameter(String value) {
        return dataSource.readSecurityWithFilterParameter(value);
    }

}
