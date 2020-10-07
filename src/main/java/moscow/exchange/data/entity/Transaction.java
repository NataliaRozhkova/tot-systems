package moscow.exchange.data.entity;

public class Transaction {

    public final String boardId;
    public final String tradeDate;
    public final String shortName;
    public final String secId;
    public final double numTrades;
    public final double value;
    public final double open;
    public final double low;
    public final double high;
    public final double legalClosePrice;
    public final double waPrice;
    public final double close;
    public final double volume;
    public final double marketPrice2;
    public final double marketPrice3;
    public final double admittedQuote;
    public final double mp2valtrd;
    public final double marketPrice3TradesValue;
    public final double admittedValue;
    public final double waval;


    public Transaction(String boardId,
                       String tradeDate,
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
    }
}
