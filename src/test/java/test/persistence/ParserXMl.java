package test.persistence;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.kml.RouteKML;

public class ParserXMl {

	public static void main(String[] args) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RouteKML.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			RouteKML routeKml = (RouteKML) unmarshaller.unmarshal(new File("F:\\prueba.kml"));
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
