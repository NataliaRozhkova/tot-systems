package moscow.exchange.data.entity.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import moscow.exchange.data.entity.Transaction;
import java.io.IOException;
import java.util.List;

public class TransactionJSONConverter {
    public static String convertToJSON(final Transaction transaction) {
        try {
            return new ObjectMapper().writeValueAsString(transaction);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Transaction deserialize(final String json) {
        try {
            return new ObjectMapper().readValue(json, Transaction.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertToJSON(final List<Transaction> transactions) {
        try {
            return new ObjectMapper().writeValueAsString(transactions);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
