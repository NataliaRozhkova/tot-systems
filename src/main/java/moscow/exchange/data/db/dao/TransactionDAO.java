package moscow.exchange.data.db.dao;

import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import moscow.exchange.data.entity.Transaction;
import moscow.exchange.data.entity.parser.SecurityParser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.TransientPropertyValueException;
import org.jetbrains.annotations.NotNull;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    private final Session session;

    public TransactionDAO(Session session) {
        this.session = session;
    }

    public Response<String> create(final Transaction transaction) {
        session.beginTransaction();
        Response<String> response;
        try {
            session.save(transaction);
            session.getTransaction().commit();
            response = new Response<>("Success", Response.State.SUCCESS);
        } catch (TransientPropertyValueException e) {

                response = new Response<>("Security not found", Response.State.ERROR);

        } catch (PersistenceException e) {
            response = new Response<>(e.getMessage(), Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }


    public Response<List<Transaction>> readAll() {
        session.beginTransaction();
        Response<List<Transaction>> response;
        List<Transaction> transactions;
        try {
            transactions = session.createQuery("FROM Transaction").list();
            response = new Response<>(transactions, Response.State.SUCCESS);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }

    public Response<List<Transaction>> readWithSortParameters(String sortParameter) {
        session.beginTransaction();
        StringBuilder query = new StringBuilder("FROM Transaction ");
        query.append("ORDER BY ")
                .append(sortParameter);
        Response<List<Transaction>> response;
        try {
            List<Transaction> transactions = session.createQuery(query.toString()).list();
            response = new Response<>(transactions, Response.State.SUCCESS);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }

    public Response<List<Transaction>> readWithFilterParameter(String value) {
        session.beginTransaction();
        Query query = session.createQuery(
                "FROM Transaction  WHERE tradedate = :tradedate "
        )
                .setParameter("tradedate", value);
        ArrayList<Transaction> transactions = (ArrayList<Transaction>) query.getResultList();
        session.close();
        return new Response<>(transactions, Response.State.SUCCESS);

    }

    public Response<Transaction> readById(long id) {
        session.beginTransaction();
        Response<Transaction> response;
        try {
            Transaction transaction = session.get(Transaction.class, id);
            session.getTransaction().commit();
            response = new Response<>(transaction, Response.State.SUCCESS);
        } catch (PersistenceException e) {
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }

    public Response<String> delete(@NotNull long id) {
        session.beginTransaction();
        Transaction deleted = session.get(Transaction.class, id);
        session.getTransaction().commit();
        Response<String> response;
        if (deleted != null) {
            try {
                session.beginTransaction();
                session.delete(deleted);
                session.getTransaction().commit();
                response = new Response<>("Success", Response.State.SUCCESS);
            } catch (Exception e) {
                response = new Response<>(e.getCause().getMessage(), Response.State.ERROR);
            } finally {
                session.close();
            }
        } else {
            response = new Response<>("Element not found", Response.State.ERROR);
        }
        return response;
    }


    public Response<String> update(Transaction transaction) {
        session.beginTransaction();
        Response<String> response;
        try {
            session.update(transaction);
            session.getTransaction().commit();
            response = new Response<>("Success", Response.State.SUCCESS);
        } catch (PersistenceException e) {
            response = new Response<>(e.getCause().getMessage(), Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;

    }


}
