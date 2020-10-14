package moscow.exchange.data.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "row")
public class Security {

    private int id;
    private String secId;
    private String shortname;
    private String regNumber;
    private String name;
    private String isin;
    private int isTraded;
    private int emitentId;
    private String emitentTitle;
    private String emitentInn;
    private String emitentOkpo;
    private String gosreg;
    private String type;
    private String group;
    private String primaryBoardId;
    private String marketPriceBoardId;

    public Security() {
    }

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

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute(name = "secid")
    public void setSecId(String secId) {
        this.secId = secId;
    }

    @XmlAttribute(name = "shortname")
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @XmlAttribute(name = "regnumber")
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "isin")
    public void setIsin(String isin) {
        this.isin = isin;
    }

    @XmlAttribute(name = "is_traded")
    public void setIsTraded(int isTraded) {
        this.isTraded = isTraded;
    }

    @XmlAttribute(name = "emitent_id")
    public void setEmitentId(int emitentId) {
        this.emitentId = emitentId;
    }

    @XmlAttribute(name = "emitent_title")
    public void setEmitentTitle(String emitentTitle) {
        this.emitentTitle = emitentTitle;
    }

    @XmlAttribute(name = "emitent_inn")
    public void setEmitentInn(String emitentInn) {
        this.emitentInn = emitentInn;
    }

    @XmlAttribute(name = "emitent_okpo")
    public void setEmitentOkpo(String emitentOkpo) {
        this.emitentOkpo = emitentOkpo;
    }

    @XmlAttribute(name = "gosreg")
    public void setGosreg(String gosreg) {
        this.gosreg = gosreg;
    }

    @XmlAttribute(name = "type")
    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "group")
    public void setGroup(String group) {
        this.group = group;
    }

    @XmlAttribute(name = "primary_boardid")
    public void setPrimaryBoardId(String primaryBoardId) {
        this.primaryBoardId = primaryBoardId;
    }

    @XmlAttribute(name = "marketprice_boardid")
    public void setMarketPriceBoardId(String marketPriceBoardId) {
        this.marketPriceBoardId = marketPriceBoardId;
    }

    public int getId() {
        return id;
    }

    public String getSecId() {
        return secId;
    }

    public String getShortName() {
        return shortname;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getName() {
        return name;
    }

    public String getIsin() {
        return isin;
    }

    public int getIsTraded() {
        return isTraded;
    }

    public int getEmitentId() {
        return emitentId;
    }

    public String getEmitentTitle() {
        return emitentTitle;
    }

    public String getEmitentInn() {
        return emitentInn;
    }

    public String getEmitentOkpo() {
        return emitentOkpo;
    }

    public String getGosreg() {
        return gosreg;
    }

    public String getType() {
        return type;
    }

    public String getGroup() {
        return group;
    }

    public String getPrimaryBoardId() {
        return primaryBoardId;
    }

    public String getMarketPriceBoardId() {
        return marketPriceBoardId;
    }
}

