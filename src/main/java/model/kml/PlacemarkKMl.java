package model.kml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * PointKML que representa el elemento Point de un archivo KML 
 * @author wilk
 *
 */
@XmlRootElement(name="Placemark")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlacemarkKMl{
	
	@XmlElement(name="name",namespace="http://www.google.com/kml/ext/2.2")
	private String nombre;
	
	@XmlElement(name="description",namespace="http://www.google.com/kml/ext/2.2")
	private String descripcion;
	
	@XmlElement(name="Point",namespace="http://www.google.com/kml/ext/2.2")
	private PointKML punto;
 
 
	public PointKML getPoint() {
		return punto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlacemarkKMl [nombre=" + nombre + ", punto=" + punto + "]";
	}
	
	
}