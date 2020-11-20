package moscow.exchange.data.entity;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "row")

public class Transaction {

    @SerializedName("id")
    private long id;
    @SerializedName("board_id")
    private String boardId;
    @SerializedName("trade_date")
    private Date tradeDate;
    @SerializedName("shortname")
    private String shortName;
    private transient Security security;
    @SerializedName("secid")
    private String secId;
    @SerializedName("num_trades")
    private double numTrades;
    @SerializedName("value")
    private double value;
    @SerializedName("open")
    private double open;
    @SerializedName("low")
    private double low;
    @SerializedName("high")
    private double high;
    @SerializedName("legal_close_price")
    private double legalClosePrice;
    @SerializedName("wa_price")
    private double waPrice;
    @SerializedName("close")
    private double close;
    @SerializedName("volume")
    private double volume;
    @SerializedName("market_price_2")
    private double marketPrice2;
    @SerializedName("market_price_3")
    private double marketPrice3;
    @SerializedName("admitted_quote")
    private double admittedQuote;
    @SerializedName("mp_2_val_trd")
    private double mp2valtrd;
    @SerializedName("market_price_3_trades_value")
    private double marketPrice3TradesValue;
    @SerializedName("admitted_value")
    private double admittedValue;
    @SerializedName("wa_val")
    private double waval;

    public Transaction() {
    }

    public Transaction(long id,
                       String boardId,
                       Date tradeDate,
                       String shortName,
                       String secId,
                       double numTrades,
                       double value,
                       double open,
                       double low,
                       double high,
                       double legalClosePrice,
                       double waPrice,
                       double close,
                       double volume,
                       double marketPrice2,
                       double marketPrice3,
                       double admittedQuote,
                       double mp2valtrd,
                       double marketPrice3TradesValue,
                       double admittedValue,
                       double waval) {
        this.id = id;
        this.boardId = boardId;
        this.tradeDate = tradeDate;
        this.shortName = shortName;
        this.secId = secId;
        this.numTrades = numTrades;
        this.value = value;
        this.open = open;
        this.low = low;
        this.high = high;
        this.legalClosePrice = legalClosePrice;
        this.waPrice = waPrice;
        this.close = close;
        this.volume = volume;
        this.marketPrice2 = marketPrice2;
        this.marketPrice3 = marketPrice3;
        this.admittedQuote = admittedQuote;
        this.mp2valtrd = mp2valtrd;
        this.marketPrice3TradesValue = marketPrice3TradesValue;
        this.admittedValue = admittedValue;
        this.waval = waval;
        this.security = new Security(secId);

    }

    public Security getSecurity() {
        return security;
    }

    @XmlAttribute(name = "SECID")
    public void setSecurity(Security security) {
        this.security = security;
        if (security != null) {
            this.secId = security.getSecId();
        }
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBoardId() {
        return boardId;
    }

    @XmlAttribute(name = "BOARDID")
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    @XmlAttribute(name = "TRADEDATE")
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getShortName() {
        return shortName;
    }

    @XmlAttribute(name = "SHORTNAME")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public double getNumTrades() {
        return numTrades;
    }

    @XmlAttribute(name = "NUMTRADES")
    public void setNumTrades(double numTrades) {
        this.numTrades = numTrades;
    }

    public double getValue() {
        return value;
    }

    @XmlAttribute(name = "VALUE")
    public void setValue(double value) {
        this.value = value;
    }

    public double getOpen() {
        return open;
    }

    @XmlAttribute(name = "OPEN")
    public void setOpen(double open) {
        this.open = open;
    }

    public double getLow() {
        return low;
    }

    @XmlAttribute(name = "LOW")
    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    @XmlAttribute(name = "HIGH")
    public void setHigh(double high) {
        this.high = high;
    }

    public double getLegalClosePrice() {
        return legalClosePrice;
    }

    @XmlAttribute(name = "LEGALCLOSEPRICE")
    public void setLegalClosePrice(double legalClosePrice) {
        this.legalClosePrice = legalClosePrice;
    }

    public double getWaPrice() {
        return waPrice;
    }

    @XmlAttribute(name = "WAPRICE")
    public void setWaPrice(double waPrice) {
        this.waPrice = waPrice;
    }

    public double getClose() {
        return close;
    }

    @XmlAttribute(name = "CLOSE")
    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    @XmlAttribute(name = "VOLUME")
    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getMarketPrice2() {
        return marketPrice2;
    }

    @XmlAttribute(name = "MARKETPRICE2")
    public void setMarketPrice2(double marketPrice2) {
        this.marketPrice2 = marketPrice2;
    }

    public double getMarketPrice3() {
        return marketPrice3;
    }

    @XmlAttribute(name = "MARKETPRICE3")
    public void setMarketPrice3(double marketPrice3) {
        this.marketPrice3 = marketPrice3;
    }

    public double getAdmittedQuote() {
        return admittedQuote;
    }

    @XmlAttribute(name = "ADMITTEDQUOTE")
    public void setAdmittedQuote(double admittedQuote) {
        this.admittedQuote = admittedQuote;
    }

    public double getMp2valtrd() {
        return mp2valtrd;
    }

    @XmlAttribute(name = "MP2VALTRD")
    public void setMp2valtrd(double mp2valtrd) {
        this.mp2valtrd = mp2valtrd;
    }

    public double getMarketPrice3TradesValue() {
        return marketPrice3TradesValue;
    }

    @XmlAttribute(name = "MARKETPRICE3TRADESVALUE")
    public void setMarketPrice3TradesValue(double marketPrice3TradesValue) {
        this.marketPrice3TradesValue = marketPrice3TradesValue;
    }

    public double getAdmittedValue() {
        return admittedValue;
    }

    @XmlAttribute(name = "ADMITTEDVALUE")
    public void setAdmittedValue(double admittedValue) {
        this.admittedValue = admittedValue;
    }

    public double getWaval() {
        return waval;
    }

    @XmlAttribute(name = "WAVAL")
    public void setWaval(double waval) {
        this.waval = waval;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Transaction)) {
            return false;
        }
        return this.id == ((Transaction) obj).id &&
                this.boardId.equals(((Transaction) obj).boardId) &&
                this.tradeDate.equals(((Transaction) obj).tradeDate) &&
                this.shortName.equals(((Transaction) obj).shortName) &&
                this.security.equals(((Transaction) obj).secId) &&
                this.numTrades == ((Transaction) obj).numTrades &&
                this.value == ((Transaction) obj).value &&
                this.open == ((Transaction) obj).open &&
                this.low == ((Transaction) obj).low &&
                this.high == ((Transaction) obj).high &&
                this.legalClosePrice == ((Transaction) obj).legalClosePrice &&
                this.waPrice == ((Transaction) obj).waPrice &&
                this.close == ((Transaction) obj).close &&
                this.volume == ((Transaction) obj).volume &&
                this.marketPrice2 == ((Transaction) obj).marketPrice2 &&
                this.marketPrice3 == ((Transaction) obj).marketPrice3 &&
                this.admittedQuote == ((Transaction) obj).admittedQuote &&
                this.mp2valtrd == ((Transaction) obj).mp2valtrd &&
                this.marketPrice3TradesValue == ((Transaction) obj).marketPrice3TradesValue &&
                this.admittedValue == ((Transaction) obj).admittedValue &&
                this.waval == ((Transaction) obj).waval &&
                this.secId.equals(((Transaction) obj).secId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return (int) id * prime + boardId.hashCode() + tradeDate.hashCode() + shortName.hashCode() + security.hashCode();
    }

    public String toString() {
        StringBuilder transactionString = new StringBuilder();
        transactionString.append(id).append("\t")
                .append(boardId).append("\t")
                .append(tradeDate).append("\t")
                .append(shortName).append("\t")
                .append(security).append("\t")
                .append(numTrades).append("\t")
                .append(value).append("\t")
                .append(open).append("\t")
                .append(low).append("\t")
                .append(high).append("\t")
                .append(legalClosePrice).append("\t")
                .append(waPrice).append("\t")
                .append(close).append("\t")
                .append(volume).append("\t")
                .append(marketPrice2).append("\t")
                .append(marketPrice3).append("\t")
                .append(admittedQuote).append("\t")
                .append(mp2valtrd).append("\t")
                .append(marketPrice3TradesValue).append("\t")
                .append(admittedValue).append("\t")
                .append(waval).append("\t")
                .append(secId).append("\t");
        return transactionString.toString();
    }

    public String toStringXml() {
        StringBuilder transactionString = new StringBuilder();
        transactionString.append("<tr>\n")
                .append("<td>").append(id).append("</td>\n")
                .append("<td>").append(boardId).append("</td>\n")
                .append("<td>").append(tradeDate).append("</td>\n")
                .append("<td>").append(shortName).append("</td>\n")
                .append("<td>").append(secId).append("</td>\n")
                .append("<td>").append(numTrades).append("</td>\n")
                .append("<td>").append(value).append("</td>\n")
                .append("<td>").append(open).append("</td>\n")
                .append("<td>").append(low).append("</td>\n")
                .append("<td>").append(high).append("</td>\n")
                .append("<td>").append(legalClosePrice).append("</td>\n")
                .append("<td>").append(waPrice).append("</td>\n")
                .append("<td>").append(close).append("</td>\n")
                .append("<td>").append(volume).append("</td>\n")
                .append("<td>").append(marketPrice2).append("</td>\n")
                .append("<td>").append(marketPrice3).append("</td>\n")
                .append("<td>").append(admittedQuote).append("</td>\n")
                .append("<td>").append(mp2valtrd).append("</td>\n")
                .append("<td>").append(marketPrice3TradesValue).append("</td>\n")
                .append("<td>").append(admittedValue).append("</td>\n")
                .append("<td>").append(waval).append("</td>\n")
                .append("</tr>\n");
        return transactionString.toString();
    }

    public String pivotTableXml() {
        StringBuilder pivotTable = new StringBuilder();
        pivotTable.append("<tr>")
                .append("<td>").append(secId).append("</td>")
                .append("<td>").append(security.getRegNumber()).append("</td>")
                .append("<td>").append(security.getName()).append("</td>")
                .append("<td>").append(security.getEmitentTitle()).append("</td>")
                .append("<td>").append(tradeDate).append("</td>")
                .append("<td>").append(numTrades).append("</td>")
                .append("<td>").append(open).append("</td>")
                .append("<td>").append(close).append("</td>")
                .append("</tr>");
        return pivotTable.toString();
    }

}
