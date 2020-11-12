package moscow.exchange.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"secid", "regnumber", "name", "emitent_title", "trade_date", "numtrades", "open", "close"})
public class PivotTableEntry {

    @JsonProperty("secid")
    private String secId;
    @JsonProperty("regnumber")
    private String regNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("emitent_title")
    private String emitentTitle;
    @JsonProperty("trade_date")
    private String tradeDate;
    @JsonProperty("numtrades")
    private double numTrades;
    @JsonProperty("open")
    private double open;
    @JsonProperty("close")
    private double close;

    @JsonCreator
    public PivotTableEntry(final Transaction transaction) {
        this.secId = transaction.getSecId();
        this.regNumber = transaction.getSecurity().getRegNumber();
        this.name = transaction.getSecurity().getName();
        this.emitentTitle = transaction.getSecurity().getEmitentTitle();
        this.tradeDate = transaction.getTradeDate();
        this.numTrades = transaction.getNumTrades();
        this.open = transaction.getOpen();
        this.close = transaction.getClose();
    }

    public String getSecId() {
        return secId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmitentTitle() {
        return emitentTitle;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public double getNumTrades() {
        return numTrades;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmitentTitle(String emitentTitle) {
        this.emitentTitle = emitentTitle;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public void setNumTrades(double numTrades) {
        this.numTrades = numTrades;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setClose(double close) {
        this.close = close;
    }
}
