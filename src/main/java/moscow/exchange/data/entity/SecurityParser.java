package moscow.exchange.data.entity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SecurityParser {

    public static ArrayList<Security> securityArrayList = new ArrayList<>();
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

    public ArrayList<Security> parse(final File file) {
        try {
            parser.parse(file, new XMLHandler());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return securityArrayList;
    }

    private static class XMLHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("row")) {
                int id = 0;
                int isTraded = 0;
                int emitentId = 0;
                try {
                    id = Integer.parseInt(attributes.getValue("id"));
                } catch (NumberFormatException e) {
                    id = 0;
                }
                try {
                    isTraded = Integer.parseInt(attributes.getValue("is_traded"));
                } catch (NumberFormatException e) {
                    isTraded = 0;
                }
                try {
                    emitentId = Integer.parseInt(attributes.getValue("emitent_id"));
                } catch (NumberFormatException e) {
                    emitentId = 0;
                }
                String secId = attributes.getValue("secid");
                String shortname = attributes.getValue("shortname");
                String regNumber = attributes.getValue("regnumber");
                String name = attributes.getValue("name");
                String isin = attributes.getValue("isin");
                String emitentTitle = attributes.getValue("emitent_title");
                String emitentInn = attributes.getValue("emitent_inn");
                String emitentOkpo = attributes.getValue("emitent_okpo");
                String gosreg = attributes.getValue("gosreg");
                String type = attributes.getValue("type");
                String group = attributes.getValue("group");
                String primaryBoardId = attributes.getValue("primary_boardid");
                String marketPriceBoardId = attributes.getValue("marketprice_boardid");


                securityArrayList.add(new Security(id,
                        secId,
                        shortname,
                        regNumber,
                        name,
                        isin,
                        isTraded,
                        emitentId,
                        emitentTitle,
                        emitentInn,
                        emitentOkpo,
                        gosreg,
                        type,
                        group,
                        primaryBoardId,
                        marketPriceBoardId));
            }
        }


    }


}
