package controller.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("birthdateValidator")
public class BirthdateValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Date today= new Date();
		if(today.compareTo((Date)value)<0){
			throw new ValidatorException(new FacesMessage("Ingrese un dato real"));
		}		
	}
}
