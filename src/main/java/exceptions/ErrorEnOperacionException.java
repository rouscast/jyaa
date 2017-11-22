package exceptions;

import exceptions.JRuterosException;

public class ErrorEnOperacionException extends JRuterosException {

	private static final long serialVersionUID = 1L;
	
	public ErrorEnOperacionException() {
		super("Hubo un error en la operacion.");
	}

	public ErrorEnOperacionException(String msg) {
		super(msg);
	}

	public ErrorEnOperacionException(String msg, Exception e) {
		super(msg , e);
	}
	
	
}