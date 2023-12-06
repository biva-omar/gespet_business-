package fr.formationspring.gespet.business.exception;

public class GespetBusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6302411059239993656L;

	public GespetBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public GespetBusinessException(String message) {
		super(message);
	}
	
}
