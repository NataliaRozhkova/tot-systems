package moscow.exchange.data.db.dao;

import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Transaction;
import org.hibernate.Session;
import org.hibernate.TransientPropertyValueException;

import javax.persistence.PersistenceException;
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
        try {
            List<Transaction> transactions = session
                    .createQuery("SELECT i FROM Transaction i JOIN FETCH i.security", Transaction.class)
                    .list();
            response = new Response<>(transactions, Response.State.SUCCESS);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }


    public Response<List<Transaction>> readWithSortParameters(String sortParameter, int limit, int offset) {
        session.beginTransaction();
        StringBuilder query = new StringBuilder();
        query.append("SELECT i FROM Transaction i JOIN FETCH i.security")
                .append(setSortParameter(sortParameter));

        Response<List<Transaction>> response;
        try {
            List<Transaction> transactions = session.createQuery(query.toString(), Transaction.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
            response = new Response<>(transactions, Response.State.SUCCESS);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }

    public Response<List<Transaction>> readWithFilterAndSortParameter(String sortParameter, String filterParameter, String value, int limit, int offset) {
        session.beginTransaction();
        StringBuilder query = new StringBuilder();
        query.append("SELECT i FROM Transaction i JOIN FETCH i.security");
        query.append(setFilterParameter(filterParameter, value));
        query.append(setSortParameter(sortParameter));
        List<Transaction> transactions = session.createQuery(query.toString(), Transaction.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        session.close();
        return new Response<>(transactions, Response.State.SUCCESS);

    }

    private String setSortParameter(String sortParameter) {
        return sortParameter != null ?
                " ORDER BY " + sortParameterToColumn(sortParameter) :
                "";
    }

    private String sortParameterToColumn(String sortParameter) {
        if (sortParameter == null) {
            return "";
        }
        switch (sortParameter) {
            case "id":
                return "i.id";
            case "secid":
                return "i.security.secId";
            case "tradedate":
                return "i.tradeDate";
            case "open":
                return "i.open";
            case "close":
                return "i.close";
            case "numtrades":
                return "i.numTrades";
            case "regnumber":
                return "i.security.regNumber";
            case "name":
                return "i.security.name";
            case "emitent_title":
                return "i.security.emitentTitle";
            default:
                return "";
        }


    }

    private String setFilterParameter(String filterParameter, String value) {
        if (filterParameter == null || value == null) {
            return "";
        }
        if (filterParameter.equals("emitent_title")) {
            return " WHERE lower(i.security.emitentTitle)  LIKE  lower(\'%" + value + "%\')";
        } else {

            return " WHERE i.tradeDate = \'" + value + "\'";
        }
    }

    public Response<Transaction> readById(long id) {
        session.beginTransaction();
        Response<Transaction> response;
        StringBuilder query = new StringBuilder();
        query.append("SELECT i FROM Transaction i JOIN FETCH i.security")
                .append(" WHERE ")
                .append("i.id = " + id);
        try {
            List<Transaction> transactions = session.createQuery(query.toString(), Transaction.class).getResultList();
            Transaction transaction = transactions.isEmpty() ? null : transactions.get(0);
            session.getTransaction().commit();
            response = new Response<>(transaction, Response.State.SUCCESS);
        } catch (PersistenceException e) {
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }

    public Response<String> delete(long id) {
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
