package exceptions;


public class JRuterosException extends Exception {	
	
	
	private static final long serialVersionUID = 1L;
	
	private String message="Error interno en la Aplicación Jruteros";
	
	public JRuterosException(){
		super();
	}
	
	public JRuterosException(String msg){
		super(msg);
		this.message=msg;
		
	}
	
	public JRuterosException(String msg, Exception e){
		super(msg,e);
		this.message=msg;
		
	}
}
