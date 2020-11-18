package moscow.exchange.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Security {

    @SerializedName("secid")
    private String secId;
    @SerializedName("shortname")
    private String shortName;
    @SerializedName("regnumber")
    private String regNumber;
    @SerializedName("name")
    private String name;
    @SerializedName("isin")
    private String isin;
    @SerializedName("is_traded")
    private int isTraded;
    @SerializedName("emitent_id")
    private int emitentId;
    @SerializedName("emitent_title")
    private String emitentTitle;
    @SerializedName("emitent_inn")
    private String emitentInn;
    @SerializedName("emitent_okpo")
    private String emitentOkpo;
    @SerializedName("gosreg")
    private String gosreg;
    @SerializedName("type")
    private String type;
    @SerializedName("group")
    private String group;
    @SerializedName("primary_boardid")
    private String primaryBoardId;
    @SerializedName("marketprice_boardid")
    private String marketPriceBoardId;
    @SerializedName("history")
    private List<Transaction> transactions;

    public Security(String secId,
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
        this.secId = secId;
        this.shortName = shortname;
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

    @JsonCreator
    public Security(String secId,
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
                    String marketPriceBoardId,
                    List<Transaction> transactions) {
        this.secId = secId;
        this.shortName = shortname;
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
        this.transactions = transactions;

    }

    public Security(String secId) {
        this.secId = secId;
        this.shortName = null;
        this.regNumber = null;
        this.name = null;
        this.isin = null;
        this.isTraded = 0;
        this.emitentId = 0;
        this.emitentTitle = null;
        this.emitentInn = null;
        this.emitentOkpo = null;
        this.gosreg = null;
        this.type = null;
        this.group = null;
        this.primaryBoardId = null;
        this.marketPriceBoardId = null;
    }

    public Security() {
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }



    public void setSecId(String secId) {
        this.secId = secId;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public void setIsTraded(int isTraded) {
        this.isTraded = isTraded;
    }

    public void setEmitentId(int emitentId) {
        this.emitentId = emitentId;
    }

    public void setEmitentTitle(String emitentTitle) {
        this.emitentTitle = emitentTitle;
    }

    public void setEmitentInn(String emitentInn) {
        this.emitentInn = emitentInn;
    }

    public void setEmitentOkpo(String emitentOkpo) {
        this.emitentOkpo = emitentOkpo;
    }

    public void setGosreg(String gosreg) {
        this.gosreg = gosreg;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setPrimaryBoardId(String primaryBoardId) {
        this.primaryBoardId = primaryBoardId;
    }

    public void setMarketPriceBoardId(String marketPriceBoardId) {
        this.marketPriceBoardId = marketPriceBoardId;
    }


    public String getSecId() {
        return secId;
    }

    public String getShortName() {
        return shortName;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Security)) {
            return false;
        }
        return this.secId.equals(((Security) obj).secId)
                && this.shortName.equals(((Security) obj).shortName)
                && this.regNumber.equals(((Security) obj).regNumber)
                && this.name.equals(((Security) obj).name)
                && this.isin.equals(((Security) obj).isin)
                && this.isTraded == ((Security) obj).isTraded
                && this.emitentId == ((Security) obj).emitentId
                && this.emitentTitle.equals(((Security) obj).emitentTitle)
                && this.emitentInn.equals(((Security) obj).emitentInn)
                && this.emitentOkpo.equals(((Security) obj).emitentOkpo)
                && this.gosreg.equals(((Security) obj).gosreg)
                && this.type.equals(((Security) obj).type)
                && this.group.equals(((Security) obj).group)
                && this.primaryBoardId.equals(((Security) obj).primaryBoardId)
                && this.marketPriceBoardId.equals(((Security) obj).marketPriceBoardId)
                && this.transactions.equals(((Security) obj).transactions);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return secId.hashCode() + shortName.hashCode() +
                regNumber.hashCode() +
                name.hashCode() +
                isin.hashCode() +
                prime * isTraded +
                prime * emitentId +
                emitentTitle.hashCode() +
                emitentInn.hashCode() +
                emitentOkpo.hashCode() +
                gosreg.hashCode() +
                type.hashCode() +
                group.hashCode() +
                primaryBoardId.hashCode() +
                marketPriceBoardId.hashCode() +
                transactions.hashCode();
    }


    public String toString() {
        StringBuilder securityString = new StringBuilder();
        securityString.append(secId).append("\t")
                .append(shortName).append("\t")
                .append(regNumber).append("\t")
                .append(name).append("\t")
                .append(isin).append("\t")
                .append(isTraded).append("\t")
                .append(emitentId).append("\t")
                .append(emitentTitle).append("\t")
                .append(emitentInn).append("\t")
                .append(emitentOkpo).append("\t")
                .append(gosreg).append("\t")
                .append(type).append("\t")
                .append(group).append("\t")
                .append(primaryBoardId).append("\t")
                .append(marketPriceBoardId).append("\t");
        return securityString.toString();
    }

    public String toStringXml() {
        StringBuilder securityString = new StringBuilder();
        securityString.append("<tr>\n")
                .append("<td>").append(secId).append("</td>\n")
                .append("<td>").append(shortName).append("</td>\n")
                .append("<td>").append(regNumber).append("</td>\n")
                .append("<td>").append(name).append("</td>\n")
                .append("<td>").append(isin).append("</td>\n")
                .append("<td>").append(isTraded).append("</td>\n")
                .append("<td>").append(emitentId).append("</td>\n")
                .append("<td>").append(emitentTitle).append("</td>\n")
                .append("<td>").append(emitentInn).append("</td>\n")
                .append("<td>").append(emitentOkpo).append("</td>\n")
                .append("<td>").append(gosreg).append("</td>\n")
                .append("<td>").append(type).append("</td>\n")
                .append("<td>").append(group).append("</td>\n")
                .append("<td>").append(primaryBoardId).append("</td>\n")
                .append("<td>").append(marketPriceBoardId).append("</td>\n")
                .append("</tr>\n");
        return securityString.toString();
    }

}

