package moscow.exchange.data.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "data")
public class SecurityList {

    @XmlElementWrapper(name = "rows")
    @XmlElement(name = "row")
    private List<Security> securities;

    private String id;

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public void setSecurities(List<Security> securities) {
        this.securities = securities;
    }

    public List<Security> getSecurity() {
        return securities;
    }

    public String getId() {
        return id;
    }
}
