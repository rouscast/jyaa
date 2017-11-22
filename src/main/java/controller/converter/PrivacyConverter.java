package controller.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import application.manager.DaoManager;
import model.Privacy;


@FacesConverter("privacyConverter")
public class PrivacyConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
		String value) {
		Privacy privacy=null;
		if (value == "" || value == null){
			return null;
		}
		try {
			privacy= (Privacy) DaoManager.getPrivacyDao().getElemento(Integer.valueOf((Integer.parseInt(value))));
		}catch(Exception e){
			FacesMessage msg =new FacesMessage("Error en la conversion.",	"Conversion error.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}
		return privacy;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value == null) {
			return null;
		}
		
		if (value instanceof String){
			return (String) value;
		}
		
		if (value instanceof Privacy) {
	        return String.valueOf(((Privacy) value).getId());
	    } 

		try {
			return value.toString();

		} catch (NullPointerException e) {
			throw new ConverterException(e);
		} catch (ClassCastException e) {
			throw new ConverterException(e);
		}

	}
}