package moscow.exchange;

import moscow.exchange.data.db.SecurityDataSource;
import moscow.exchange.data.entity.SecurityParser;
import moscow.exchange.data.entity.TransactionParser;
import java.util.HashMap;

public class Main {

    public static void main(String[] args)  {

        TransactionParser parser = new TransactionParser();
        SecurityParser securityParser = new SecurityParser();
        SecurityDataSource dataSource = new SecurityDataSource();
        HashMap<String, String> par = new HashMap<>();
        par.put("shortname", "Apple");
        par.put("name", "Apple Inc.");
        par.put("is_traded", "212121");
        dataSource.update(154676, par);
    }
}
