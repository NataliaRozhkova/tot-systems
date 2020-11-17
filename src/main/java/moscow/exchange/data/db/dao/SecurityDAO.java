package moscow.exchange.data.db.dao;

import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class SecurityDAO {

    private final Session session;

    public SecurityDAO(Session session) {
        this.session = session;
    }

    public Response<String> create(final Security security) {
        session.beginTransaction();
        Response<String> response;
        try {
            session.save(security);
            session.getTransaction().commit();
            response = new Response<>("Success", Response.State.SUCCESS);
        } catch (IllegalArgumentException ex) {
            response = new Response<>(ex.getMessage(), Response.State.ERROR);
        } catch (PersistenceException e) {
            response = new Response<>(e.getCause().getCause().getMessage(), Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }


    public Response<List<Security>> readAll() {
        session.beginTransaction();
        List<Security> securities = session.createQuery("FROM Security").list();
        session.getTransaction().commit();
        session.close();
        return new Response<>(securities, Response.State.SUCCESS);
    }

    public Response<List<Security>> readWithSortParameters(String sortParameter, int limit, int offset) {
        session.beginTransaction();
        StringBuilder query = new StringBuilder("FROM Security ");
        if ( sortParameter != null ){
            query.append("ORDER BY ")
                    .append(sortParameter);
        }
        Response<List<Security>> response;
        try {
            List<Security> securities = session.createQuery(query.toString())
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
            response = new Response<>(securities, Response.State.SUCCESS);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }

    public Response<List<Security>> readWithFilterParameter(String value) {
        session.beginTransaction();
        Query query = session.createQuery(
                "FROM Security WHERE emitent_title = :emitent_title "
        )
                .setParameter("emitent_title", value);
        ArrayList<Security> securities = (ArrayList<Security>) query.getResultList();
        session.close();
        return new Response<>(securities, Response.State.SUCCESS);

    }


    public Response<Security> readBySecId(String secId) {
        if (secId == null) {
            return new Response<>(null, Response.State.ERROR);
        }
        session.beginTransaction();
        Response<Security> response;
        try {
            Security security = session.get(Security.class, secId);
            session.getTransaction().commit();
            if (security == null) {
                response = new Response<>(null, Response.State.ERROR);
            } else {
                response = new Response<>(security, Response.State.SUCCESS);
            }
        } catch (PersistenceException e) {
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            session.close();
        }
        return response;
    }

    public Response<String> delete(@NotNull String secId) {
        session.beginTransaction();
        Security deletedSecurity = session.get(Security.class, secId);
        session.getTransaction().commit();
        Response<String> response;
        if (deletedSecurity != null) {
            try {
                session.beginTransaction();
                session.delete(deletedSecurity);
                session.getTransaction().commit();
                response = new Response<>("Success", Response.State.SUCCESS);
            } catch (Exception e) {
                response = new Response<>(e.getMessage(), Response.State.ERROR);
            } finally {
                session.close();
            }
        } else {
            response = new Response<>("Element not found", Response.State.ERROR);
        }
        return response;
    }


    public Response<String> update(Security security) {
        session.beginTransaction();
        Response<String> response;
        try {
            session.update(security);
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
