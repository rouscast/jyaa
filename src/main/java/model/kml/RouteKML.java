package model.kml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="kml",  namespace = "http://www.google.com/kml/ext/2.2")
@XmlAccessorType(XmlAccessType.FIELD)
public class RouteKML {
	
	@XmlElement(name = "Document",  namespace = "http://www.google.com/kml/ext/2.2")
	DocumentKML documentKML;

    public DocumentKML getDocument() {
        return documentKML;
    }

    public void setDocumentKML(DocumentKML documentKML) {
        this.documentKML = documentKML;
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RouteKML [documentKML=" + documentKML + "]";
	}
}
