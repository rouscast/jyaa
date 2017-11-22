package model.kml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Document")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentKML {
	
	@XmlElement(name = "name",namespace="http://www.google.com/kml/ext/2.2")
    String name;
	
    @XmlElement(name = "description",namespace="http://www.google.com/kml/ext/2.2")
    String descripcion;
    
    @XmlElement(name = "Placemark",namespace="http://www.google.com/kml/ext/2.2") 
	private PlacemarkKMl lugar;
    
   
	public DocumentKML() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the lugar
	 */
	public PlacemarkKMl getLugar() {
		return lugar;
	}

	/**
	 * @param lugar the lugar to set
	 */
	public void setLugar(PlacemarkKMl lugar) {
		this.lugar = lugar;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [name=" + name + ", descripcion=" + descripcion
				+ ", lugar=" + lugar + "]";
	}
	
	
}
