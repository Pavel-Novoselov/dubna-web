package ru.geekbrains.comand.geetterbackend.exceptions;

public class NotFoundForTheRequestedParamException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG = "Not found for the requested param: ";

	public NotFoundForTheRequestedParamException() {
		super();
	}
	
	public NotFoundForTheRequestedParamException(Long param) {
		super(MSG + param);
	}
	
	public NotFoundForTheRequestedParamException(String param) {
		super(MSG + param);
	}
	
}
