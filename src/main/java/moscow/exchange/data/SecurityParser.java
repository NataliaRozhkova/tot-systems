package moscow.exchange.data;

import moscow.exchange.data.entity.Security;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

public class SecurityParser {

    private static ArrayList<Security> securityArrayList = new ArrayList<>();
    private final SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser parser;

    public SecurityParser() {
        try {
            this.parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void parse() {

    }

    private static class XMLHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("row")) {
                securityArrayList.add(new Security(Integer.parseInt(attributes.getValue("id")),
                        attributes.getValue("secid"),
                        attributes.getValue("shortname"),
                        attributes.getValue("regnumber"),
                        attributes.getValue("name"),
                        attributes.getValue("isin"),
                        Integer.parseInt(attributes.getValue("is_traded")),
                        Integer.parseInt(attributes.getValue("emitent_id")),
                        attributes.getValue("emitent_title"),
                        attributes.getValue("emitent_inn"),
                        attributes.getValue("emitent_okpo"),
                        attributes.getValue("gosreg"),
                        attributes.getValue("type"),
                        attributes.getValue("group"),
                        attributes.getValue("primary_boardid"),
                        attributes.getValue("marketprice_boardid")
                        ));
            }
        }


    }


}
