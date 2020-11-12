package moscow.exchange.data.entity.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import moscow.exchange.data.entity.PivotTableEntry;

import java.io.IOException;
import java.util.List;

public class PivotTableJSONConverter {
    public static String convertToJSON(final PivotTableEntry entry) {
        try {
            return new ObjectMapper().writeValueAsString(entry);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PivotTableEntry deserialize(final String json) {
        try {
            return new ObjectMapper().readValue(json, PivotTableEntry.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertToJSON(final List<PivotTableEntry> tables) {
        try {
            return new ObjectMapper().writeValueAsString(tables);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
