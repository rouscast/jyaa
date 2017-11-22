package model.kml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * PointKML que representa el elemento Point de un archivo KML 
 * @author wilk
 *
 */
@XmlRootElement(name="Point")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PointKML {


	private String coordenadas;
	
	private double latitud;
	private double longitud;
	private double altura;
	
	
	public PointKML() {
		super();
	}



	@XmlElement(name="coordinates",namespace="http://www.google.com/kml/ext/2.2")
	public void setCoordinates(String coordenadas) {
		this.coordenadas = coordenadas;
		this.convertirCoordenada(coordenadas);
	}

	
	
	 public void convertirCoordenada(final String coordinates) {
        String[] coords = coordinates.replaceAll(",\\s+", ",").trim().split(",");
        if ((coords.length< 1)&&(coords.length > 3)) {
            throw new IllegalArgumentException();
        }
        this.longitud = Double.parseDouble((coords[0]));
        this.latitud = Double.parseDouble((coords[1]));
        if (coords.length == 3) {
            this.altura = Double.parseDouble((coords[2]));
        }
	 }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PointKML [latitud=" + latitud + ", longitud=" + longitud
				+ ", altura=" + altura +" coordenadas "+ coordenadas+" ]";
	}
 
	
	 
}