package exceptions;

import java.util.ArrayList;

public class FalloCargaXMLException extends JRuterosException {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> errors=new ArrayList<String>();
	
	public FalloCargaXMLException() {
		super();
	}

	public FalloCargaXMLException(String msg) {
		super(msg);
		errors.add(msg);
	}
	
	public ArrayList<String> getErrors(){ 
		return errors;
	}
	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;		
	}
		
	public void addError(String message){
		errors.add(message);			
	}
	
		
	
	@Override
	public String getMessage() {
		String message="";
		for (String error:errors){
			if(message.equals("")){
				message=error;
			}else{
				message=message+" || "+error;
			}
		}
		
		return message;
	}
	
}
