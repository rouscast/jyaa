package controller.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import application.manager.DaoManager;
import model.Activity;


@FacesConverter("activityConverter")
public class ActivityConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
		String value) {
		Activity activity=null;
		if (value == "" || value == null){
			return null;
		}
		
		try {
			activity= (Activity) DaoManager.getActivityDao().getElemento(Integer.valueOf((Integer.parseInt(value))));
		}catch(Exception e){
			FacesMessage msg =new FacesMessage("Error en la conversion.",	"Conversion error.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}
		return activity;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value instanceof String){
			return (String) value;
		}
		
		if (value instanceof Activity) {
	        return String.valueOf(((Activity) value).getId());
	    } 
		
		if (value == null) {
			return null;
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