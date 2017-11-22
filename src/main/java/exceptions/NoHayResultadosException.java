package exceptions;

import exceptions.JRuterosException;

public class NoHayResultadosException extends JRuterosException {

	private static final long serialVersionUID = 1L;
	
	public NoHayResultadosException() {
		super("No hay resultados en la búsqueda.");
	}

	public NoHayResultadosException(String msg) {
		super(msg);
	}
	
	
}