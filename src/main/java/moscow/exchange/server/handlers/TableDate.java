package moscow.exchange.server.handlers;

public class TableDate {
    public final static String TRANSACTION_TABLE_HEAD = "<table  border=\"1\">" +
            "<tr>\n" +
            "<th>ID </th>\n" +
            "<th>BOARDID </th>\n" +
            "<th>TRADEDATE </th>\n" +
            "<th>SHORTNAME </th>\n" +
            "<th>SECID </th>\n" +
            "<th>NUMTRADES </th>\n" +
            "<th>VALUE </th>\n" +
            "<th>OPEN </th>\n" +
            "<th>LOW </th>\n" +
            "<th>HIGH </th>\n" +
            "<th>LEGALCLOSEPRICE</th>\n" +
            "<th>WAPRICE</th>\n" +
            "<th>CLOSE</th>\n" +
            "<th>VOLUME</th>\n" +
            "<th>MARKETPRICE2</th>\n" +
            "<th>MARKETPRICE3</th>\n" +
            "<th>ADMITTEDQUOTE</th>\n" +
            "<th>MP2VALTRD</th>\n" +
            "<th>MARKETPRICE3TRADESVALUE</th>\n" +
            "<th>ADMITTEDVALUE</th>\n" +
            "<th>WAVAL</th>\n" +
            "</tr>";
    public final static String SECURITY_TABLE_HEAD = "<table  border=\"1\"> " +
            "<tr>\n" +
            "<th>secid</th>\n" +
            "<th>shortname </th>\n" +
            "<th>regnumber </th>\n" +
            "<th>name</th>\n" +
            "<th>isin</th>\n" +
            "<th>is_traded</th>\n" +
            "<th>emitent_id</th>\n" +
            "<th>emitent_title</th>\n" +
            "<th>emitent_inn </th>\n" +
            "<th>emitent_okpo </th>\n" +
            "<th>gosreg </th>\n" +
            "<th>type </th>\n" +
            "<th>group </th>\n" +
            "<th>primary_boardid </th>\n" +
            "<th>marketprice_boardid</th>\n" +
            "</tr>";
    public final static String PIVOT_TABLE_HEAD = "<table  border=\"1\"> " +
            "<tr>\n" +
            "<th>secid </th>\n" +
            "<th>regnumber </th>\n" +
            "<th>name </th>\n" +
            "<th>emitent_title </th>\n" +
            "<th>tradedate </th>\n" +
            "<th>numtrades </th>\n" +
            "<th>open </th>\n" +
            "<th>close </th>\n"+
            "</tr>";


    public final static String FINISH_TABLE = "</table>";
}
