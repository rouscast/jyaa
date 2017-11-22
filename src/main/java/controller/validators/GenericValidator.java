package controller.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericValidator {

	/**
	 * String no vacio
	 * @param aString
	 * @return
	 */
	public static Boolean isNotEmpty(String aString){
		return !aString.trim().isEmpty();
	}

	/**
	 * String sin espacios en blancos
	 * @param aString
	 * @return
	 */
	public static Boolean noWhite(String aString){
		return !aString.contains(" ");
	}

	public static Boolean isNotNull(Object object){
		return object!=null;
	}
	/**
	 * Determina si tiene el formato de una direccion de mail
	 * @param aEmail
	 * @return
	 */
	public static boolean isEmail(String aEmail) {
		String patternEmail= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(patternEmail);
		Matcher matcher = pattern.matcher(aEmail);
		return matcher.matches();
	}
}
