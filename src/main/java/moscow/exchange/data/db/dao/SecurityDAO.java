package moscow.exchange.data.db.dao;

import moscow.exchange.data.Response;
import moscow.exchange.data.entity.Security;
import org.apache.commons.dbcp2.BasicDataSource;
import org.jetbrains.annotations.NotNull;

import javax.print.attribute.standard.MediaSize;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecurityDAO {

    private final HashMap<String, ColumnDB> tableColumn = setTableColumn();

    private HashMap<String, ColumnDB> setTableColumn() {
        HashMap<String, ColumnDB> columns = new HashMap<>();
        columns.put("id", new ColumnDB(1, "id", Integer.class));
        columns.put("secid", new ColumnDB(2, "secid", String.class));
        columns.put("shortname", new ColumnDB(3, "shortname", String.class));
        columns.put("reg_number", new ColumnDB(4, "reg_number", String.class));
        columns.put("name", new ColumnDB(5, "name", String.class));
        columns.put("isin", new ColumnDB(6, "isin", String.class));
        columns.put("is_traded", new ColumnDB(7, "is_traded", Integer.class));
        columns.put("emitent_id", new ColumnDB(8, "emitent_id", Integer.class));
        columns.put("emitent_title", new ColumnDB(9, "emitent_title", String.class));
        columns.put("emitent_inn", new ColumnDB(10, "emitent_inn", String.class));
        columns.put("emitent_okpo", new ColumnDB(11, "emitent_okpo", String.class));
        columns.put("gosreg", new ColumnDB(12, "gosreg", String.class));
        columns.put("type", new ColumnDB(13, "type", String.class));
        columns.put("security_group", new ColumnDB(14, "security_group", String.class));
        columns.put("primary_board_id", new ColumnDB(15, "primary_board_id", String.class));
        columns.put("marketprice_boardid", new ColumnDB(16, "marketprice_boardid", String.class));
        return columns;
    }

    private static final int ID = 1;
    private static final int SUCCESS = 1;
    private static final int ERROR = 0;

    @NotNull
    private final BasicDataSource dbSource;

    public SecurityDAO(@NotNull BasicDataSource dataSource) {
        this.dbSource = dataSource;
    }

    public Response<String> create(@NotNull final Security security) {
        Connection connection = null;
        Response<String> response = null;
        try {
            connection = dbSource.getConnection();
            PreparedStatement statement = setStatementPut(connection, security);
            if (statement.executeUpdate() == SUCCESS) {
                response = new Response<>("Success", Response.State.SUCCESS);
            }
        } catch (SQLException exception) {
            if (exception.getErrorCode() == 0) {
                response = new Response<>("Object already exists", Response.State.ERROR);
            } else {
                response = new Response<>("Error", Response.State.ERROR);
            }
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return response;
    }

    public Response<List<Security>> readAll() {
        Response<List<Security>> response;
        Connection connection = null;
        try {
            connection = dbSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLCars.GET_ALL.QUERY);
            ArrayList<Security> cars = mappingToSecurity(statement.executeQuery());
            response = new Response<>(cars, Response.State.SUCCESS);
        } catch (SQLException exception) {
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return response;
    }

    public Response<List<Security>> readAll(final HashMap<String, String> parameters) {
        ArrayList<Security> cars;
        Connection connection = null;
        Response<List<Security>> response;
        try {
            connection = dbSource.getConnection();
            PreparedStatement statement = setStatementGet(connection, parameters);
            cars = mappingToSecurity(statement.executeQuery());
            response = new Response<>(cars, Response.State.SUCCESS);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
            response = new Response<>(null, Response.State.ERROR);
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return response;
    }

    public Response<String> delete(@NotNull int id) {
        Connection connection = null;
        Response<String> response;
        try {
            connection = dbSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLCars.DELETE.QUERY);
            statement.setInt(ID, id);
            int result = statement.executeUpdate();
            if (result == SUCCESS) {
                response = new Response<>("Success", Response.State.SUCCESS);
            } else if (result == ERROR) {
                response = new Response<>("Object not found", Response.State.ERROR);
            } else {
                response = new Response<>("Error", Response.State.ERROR);
            }

        } catch (SQLException exception) {
            response = new Response<>("Error", Response.State.ERROR);
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return response;
    }

    private PreparedStatement setStatementPut(final Connection connection, final Security security) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLCars.INSERT.QUERY);
        statement.setInt(tableColumn.get("id").columnNumber, security.id);
        statement.setString(tableColumn.get("secid").columnNumber, security.secId);
        statement.setString(tableColumn.get("shortname").columnNumber, security.shortname);
        statement.setString(tableColumn.get("reg_number").columnNumber, security.regNumber);
        statement.setString(tableColumn.get("name").columnNumber, security.name);
        statement.setString(tableColumn.get("isin").columnNumber, security.isin);
        statement.setInt(tableColumn.get("is_traded").columnNumber, security.isTraded);
        statement.setInt(tableColumn.get("emitent_id").columnNumber, security.emitentId);
        statement.setString(tableColumn.get("emitent_title").columnNumber, security.emitentTitle);
        statement.setString(tableColumn.get("emitent_inn").columnNumber, security.emitentInn);
        statement.setString(tableColumn.get("emitent_okpo").columnNumber, security.emitentOkpo);
        statement.setString(tableColumn.get("gosreg").columnNumber, security.gosreg);
        statement.setString(tableColumn.get("type").columnNumber, security.type);
        statement.setString(tableColumn.get("security_group").columnNumber, security.group);
        statement.setString(tableColumn.get("primary_board_id").columnNumber, security.primaryBoardId);
        statement.setString(tableColumn.get("marketprice_boardid").columnNumber, security.marketPriceBoardId);
        return statement;
    }

    private PreparedStatement setStatementGet(final Connection connection, final HashMap<String, String> parameters) throws SQLException {
        return connection.prepareStatement(generateSelectQuery(parameters));
    }

    private String generateSelectQuery(final HashMap<String, String> parameters) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM security WHERE ");
        for (String parameter : parameters.keySet()) {
            query.append(parameter)
                    .append(" = '")
                    .append(parameters.get(parameter))
            .append("' and ");
        }
        query.delete(query.length() - 5, query.length() -1);
        return  query.append(";").toString();
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private ArrayList<Security> mappingToSecurity(ResultSet set) throws SQLException {
        ArrayList<Security> securities = new ArrayList<>();
        while (set.next()) {
            securities.add(new Security(set.getInt(tableColumn.get("id").columnNumber),
                    set.getString(tableColumn.get("secid").columnNumber),
                    set.getString(tableColumn.get("shortname").columnNumber),
                    set.getString(tableColumn.get("reg_number").columnNumber),
                    set.getString(tableColumn.get("name").columnNumber),
                    set.getString(tableColumn.get("isin").columnNumber),
                    set.getInt(tableColumn.get("is_traded").columnNumber),
                    set.getInt(tableColumn.get("emitent_id").columnNumber),
                    set.getString(tableColumn.get("emitent_title").columnNumber),
                    set.getString(tableColumn.get("emitent_inn").columnNumber),
                    set.getString(tableColumn.get("emitent_okpo").columnNumber),
                    set.getString(tableColumn.get("gosreg").columnNumber),
                    set.getString(tableColumn.get("type").columnNumber),
                    set.getString(tableColumn.get("security_group").columnNumber),
                    set.getString(tableColumn.get("primary_board_id").columnNumber),
                    set.getString(tableColumn.get("marketprice_boardid").columnNumber)
            ));
        }
        return securities;
    }

    public enum SQLCars {
        GET_ALL("SELECT * FROM security"),
        INSERT("INSERT INTO security (id," +
                "secid," +
                "shortname," +
                "reg_number," +
                "name," +
                "isin," +
                "is_traded," +
                "emitent_id," +
                "emitent_title," +
                "emitent_inn," +
                "emitent_okpo," +
                "gosreg," +
                "type," +
                "security_group," +
                "primary_board_id," +
                "marketprice_boardid) VALUES ( (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?))"),
        DELETE("DELETE FROM security WHERE id = (?)");

        String QUERY;

        SQLCars(String QUERY) {
            this.QUERY = QUERY;
        }
    }


}
