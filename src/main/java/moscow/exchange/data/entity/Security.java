package moscow.exchange.data.entity;

public class Security {

    public final int id;
    public final String secId;
    public final String shortname;
    public final String regNumber;
    public final String name;
    public final String isin;
    public final int isTraded;
    public final int emitentId;
    public final String emitentTitle;
    public final String emitentInn;
    public final String emitentOkpo;
    public final String gosreg;
    public final String type;
    public final String group;
    public final String primaryBoardId;
    public final String marketPriceBoardId;


    public Security(int id,
                    String secId,
                    String shortname,
                    String regNumber,
                    String name,
                    String isin,
                    int isTraded,
                    int emitentId,
                    String emitentTitle,
                    String emitentInn,
                    String emitentOkpo,
                    String gosreg,
                    String type,
                    String group,
                    String primaryBoardId,
                    String marketPriceBoardId) {
        this.id = id;
        this.secId = secId;
        this.shortname = shortname;
        this.regNumber = regNumber;
        this.name = name;
        this.isin = isin;
        this.isTraded = isTraded;
        this.emitentId = emitentId;
        this.emitentTitle = emitentTitle;
        this.emitentInn = emitentInn;
        this.emitentOkpo = emitentOkpo;
        this.gosreg = gosreg;
        this.type = type;
        this.group = group;
        this.primaryBoardId = primaryBoardId;
        this.marketPriceBoardId = marketPriceBoardId;
    }




}
