package moscow.exchange.data.db;

import moscow.exchange.data.Response;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public abstract class MoscowExchangeDataSource <Entity> {
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USER = "user1";
    private static final String PASSWORD = "123";
    private static final String DB_NAME = "moscow_exchange";
    private static final String DB_TABLE_SECURITY = "security";
    private static final String DB_TABLE_TRANSACTION = "transaction";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private static final String TABLE_SECURITY_DESCRIPTION = " (id integer UNIQUE," +
            "secid varchar(255) UNIQUE," +
            "shortname varchar(255)," +
            "reg_number varchar(255)," +
            "name varchar(255)," +
            "isin varchar(255)," +
            "is_traded integer," +
            "emitent_id integer," +
            "emitent_title varchar(255)," +
            "emitent_inn varchar(255)," +
            "emitent_okpo varchar(255)," +
            "gosreg varchar(255)," +
            "type varchar(255)," +
            "security_group varchar(255)," +
            "primary_board_id varchar(255)," +
            "marketprice_boardid varchar(255));";
    private static final String TABLE_TRANSACTION_DESCRIPTION = "(boardid varchar(255) UNIQUE, " +
            "tradeDate date," +
            "shortname varchar(255)," +
            "secid varchar(255) REFERENCES security (secid)," +
            "numtrades bigint," +
            "value bigint," +
            "open bigint," +
            "low bigint," +
            "high bigint," +
            "legalcloseprice bigint," +
            "waprice bigint," +
            "close bigint," +
            "volume bigint," +
            "marketprice2 bigint," +
            "marketprice3 bigint," +
            "admittedquote bigint," +
            "mp2valtrd bigint," +
            "marketprice3tradesvalue bigint," +
            "admittedvalue bigint," +
            "waval bigint);";

    private static final int DATABASE_NAME = 1;
    private static final int DB_TABLE_NAME = 3;
    private static final int MIN_NUMBER_IDLE_CONNECTION_IN_POOL = 10;
    private static final int MAX_NUMBER_IDLE_CONNECTION_IN_POOL = 20;


    private final BasicDataSource dataSource;

    public MoscowExchangeDataSource() {
        this.dataSource = initDataSource();
    }

    public abstract Response<String> create(final Entity entity);

    public abstract Response<String> delete(final long id);

    public abstract Response<List<Entity>> readAll() ;

    public abstract Response<List<Entity>> readAll(final HashMap<String, String> parameters);

    public abstract Response<String> update(final long id, final Entity newEntity);

    public BasicDataSource getDataSource() {
        return dataSource;
    }

    private BasicDataSource initDataSource() {
        if (this.dataSource != null) {
            return this.dataSource;
        }
        BasicDataSource dataSource = init();
        if (!validationDataBase(dataSource)) {
            return null;
        }
        if (!validationDBTable(dataSource, DB_TABLE_SECURITY)) {
            if (createDBTable(dataSource, DB_TABLE_SECURITY, TABLE_SECURITY_DESCRIPTION)) {
                return dataSource;
            }
        }
        if (!validationDBTable(dataSource, DB_TABLE_TRANSACTION)) {
            if (createDBTable(dataSource, DB_TABLE_TRANSACTION, TABLE_TRANSACTION_DESCRIPTION)) {
                return dataSource;
            }
        }
        return dataSource;
    }

    private BasicDataSource init() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(URL + DB_NAME);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setMinIdle(MIN_NUMBER_IDLE_CONNECTION_IN_POOL);
        dataSource.setMaxIdle(MAX_NUMBER_IDLE_CONNECTION_IN_POOL);
        return dataSource;
    }

    private boolean validationDataBase(BasicDataSource dataSource) {
        try {
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
            ResultSet set = metaData.getCatalogs();
            while (set.next()) {
                if (set.getString(DATABASE_NAME).equals(DB_NAME)) {
                    return true;
                }
            }
            return false;

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private boolean validationDBTable(BasicDataSource dataSource, String tableName) {
        try {
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
            ResultSet set = metaData.getTables(DB_NAME, null, null, new String[]{"TABLE"});
            while (set.next()) {
                if (set.getString(DB_TABLE_NAME).equals(tableName)) {
                    return true;
                }
            }
            return false;

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }


    private boolean createDBTable(BasicDataSource dataSource, String tableName, String tableDescription) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(CREATE_TABLE + tableName + tableDescription);
            return statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }

    }

}
