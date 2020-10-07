package moscow.exchange.data.db;

import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;

import java.util.HashMap;
import java.util.List;

public class SecurityDataSource extends  MoscowExchangeDataSource<Security>{
    @Override
    public Response<String> create(Security security) {
        return null;
    }

    @Override
    public Response<String> delete(long id) {
        return null;
    }

    @Override
    public Response<List<Security>> readAll() {
        return null;
    }

    @Override
    public Response<List<Security>> readAll(HashMap<String, String> parameters) {
        return null;
    }

    @Override
    public Response<String> update(long id, Security newEntity) {
        return null;
    }
}
