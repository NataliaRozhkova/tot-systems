package moscow.exchange.data.db;

import moscow.exchange.data.Response;
import moscow.exchange.data.db.dao.SecurityDAO;
import moscow.exchange.data.entity.Security;

import java.util.HashMap;
import java.util.List;

public class SecurityDataSource extends MoscowExchangeDataSource<Security> {

    public Response<String> create(Security security) {
        return new SecurityDAO(getDataSource()).create(security);
    }

    public Response<String> delete(int id) {
        return new SecurityDAO(getDataSource()).delete(id);
    }

    public Response<List<Security>> readAll() {
        return new SecurityDAO(this.getDataSource()).readAll();
    }

    public Response<List<Security>> readAll(HashMap<String, String> parameters) {
        return new SecurityDAO(getDataSource()).readAll(parameters);
    }

    public Response<String> update(int id, HashMap<String, String> newParameters) {
        return new SecurityDAO(getDataSource()).update(id, newParameters);
    }
}
