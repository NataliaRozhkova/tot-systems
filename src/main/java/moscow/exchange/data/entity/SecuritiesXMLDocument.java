package moscow.exchange.data.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "document")
public class SecuritiesXMLDocument {

    @XmlElement(name = "data")
    public SecurityList securitiesList;

}
