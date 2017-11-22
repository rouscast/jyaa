package exceptions;

import exceptions.JRuterosException;

public class ValorRequeridoException extends JRuterosException {

	private static final long serialVersionUID = 1L;
	
	public ValorRequeridoException() {
		super("Excepcion de valor requerido.");
	}

	public ValorRequeridoException(String msg) {
		super(msg);
	}
	
	
}