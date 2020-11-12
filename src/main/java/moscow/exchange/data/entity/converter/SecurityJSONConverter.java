package moscow.exchange.data.entity.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import moscow.exchange.data.entity.Security;

import java.io.IOException;
import java.util.List;

public class SecurityJSONConverter {

    public static String convertToJSON(final Security security) {
        try {
            return new ObjectMapper().writeValueAsString(security);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Security deserialize(final String json) {
        try {
            return new ObjectMapper().readValue(json, Security.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertToJSON(final List<Security> securities) {
        try {
            return new ObjectMapper().writeValueAsString(securities);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
