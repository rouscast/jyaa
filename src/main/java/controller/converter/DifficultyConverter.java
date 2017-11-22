package controller.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import application.manager.DaoManager;
import model.Difficulty;


@FacesConverter("difficultyConverter")
public class DifficultyConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
		String value) {
		Difficulty difficulty=null;
		if (value == "" || value == null){
			return null;
		}
		try {
			difficulty= (Difficulty) DaoManager.getDifficultyDao().getElemento(Integer.valueOf((Integer.parseInt(value))));
		}catch(Exception e){
			FacesMessage msg =new FacesMessage("Error en la conversion.",	"Conversion error.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}
		return difficulty;
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
		
		if (value instanceof Difficulty) {
	        return String.valueOf(((Difficulty) value).getId());
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