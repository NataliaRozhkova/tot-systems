package moscow.exchange.data.entity.parser;

import moscow.exchange.data.entity.Transaction;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransactionParser {
    private static ArrayList<Transaction> transactionArrayList = new ArrayList<>();
    private final SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser parser;

    public TransactionParser() {
        try {
            this.parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public static void removeArray() {
        transactionArrayList.clear();
    }

    public ArrayList<Transaction> parse(final File file) {
        try {
            parser.parse(file, new TransactionParser.XMLHandler());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionArrayList;
    }

    public ArrayList<Transaction> parse(final InputStream stream) {
        try {
            parser.parse(stream, new TransactionParser.XMLHandler());
            stream.close();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionArrayList;
    }

    private static class XMLHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("row")) {
                String boardId = attributes.getValue("BOARDID");
                String tradeDateStr = attributes.getValue("TRADEDATE");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                try {
                    if (tradeDateStr != null) {
                        date = format.parse(tradeDateStr);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String shortName = attributes.getValue("SHORTNAME");
                String secId = attributes.getValue("SECID");
                String numTradesStr = attributes.getValue("NUMTRADES");
                double numTrades = numTradesStr != null && numTradesStr.length() != 0 ? Double.parseDouble(numTradesStr) : 0;
                String valueStr = attributes.getValue("VALUE");
                double value = valueStr != null && valueStr.length() != 0 ? Double.parseDouble(valueStr) : 0;
                String openStr = attributes.getValue("OPEN");
                double open = openStr != null && openStr.length() != 0 ? Double.parseDouble(openStr) : 0;
                String lowStr = attributes.getValue("LOW");
                double low = lowStr != null && lowStr.length() != 0 ? Double.parseDouble(lowStr) : 0;
                String highStr = attributes.getValue("HIGH");
                double high = highStr != null && highStr.length() != 0 ? Double.parseDouble(highStr) : 0;
                String legalClosePriceStr = attributes.getValue("LEGALCLOSEPRICE");
                double legalClosePrice = legalClosePriceStr != null && legalClosePriceStr.length() != 0 ? Double.parseDouble(legalClosePriceStr) : 0;
                String waPriceStr = attributes.getValue("WAPRICE");
                double waPrice = waPriceStr != null && waPriceStr.length() != 0 ? Double.parseDouble(waPriceStr) : 0;
                String closeStr = attributes.getValue("CLOSE");
                double close = closeStr != null && closeStr.length() != 0 ? Double.parseDouble(closeStr) : 0;
                String volumeStr = attributes.getValue("VOLUME");
                double volume = volumeStr != null && volumeStr.length() != 0 ? Double.parseDouble(volumeStr) : 0;
                String marketPrice2Str = attributes.getValue("MARKETPRICE2");
                double marketPrice2 = marketPrice2Str != null && marketPrice2Str.length() != 0 ? Double.parseDouble(marketPrice2Str) : 0;
                String marketPrice3Str = attributes.getValue("MARKETPRICE3");
                double marketPrice3 = marketPrice3Str != null && marketPrice3Str.length() != 0 ? Double.parseDouble(marketPrice3Str) : 0;
                String admittedQuoteStr = attributes.getValue("ADMITTEDQUOTE");
                double admittedQuote = admittedQuoteStr != null && admittedQuoteStr.length() != 0 ? Double.parseDouble(admittedQuoteStr) : 0;
                String mp2valtrdStr = attributes.getValue("MP2VALTRD");
                double mp2valtrd = mp2valtrdStr != null && mp2valtrdStr.length() != 0 ? Double.parseDouble(mp2valtrdStr) : 0;
                String marketPrice3TradesValueStr = attributes.getValue("MARKETPRICE3TRADESVALUE");
                double marketPrice3TradesValue = marketPrice3TradesValueStr != null && marketPrice3TradesValueStr.length() != 0 ? Double.parseDouble(marketPrice3TradesValueStr) : 0;
                String admittedValueStr = attributes.getValue("ADMITTEDVALUE");
                double admittedValue = admittedValueStr != null && admittedValueStr.length() != 0 ? Double.parseDouble(admittedValueStr) : 0;
                String wavalStr = attributes.getValue("WAVAL");
                double waval = wavalStr != null && wavalStr.length() != 0 ? Double.parseDouble(wavalStr) : 0;
                if (boardId != null) {
                    transactionArrayList.add(new Transaction(0,
                            boardId,
                            date,
                            shortName,
                            secId,
                            numTrades,
                            value,
                            open,
                            low,
                            high,
                            legalClosePrice,
                            waPrice,
                            close,
                            volume,
                            marketPrice2,
                            marketPrice3,
                            admittedQuote,
                            mp2valtrd,
                            marketPrice3TradesValue,
                            admittedValue,
                            waval));
                }
            }

        }


    }


}