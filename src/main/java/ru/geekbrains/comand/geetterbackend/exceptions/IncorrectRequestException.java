package ru.geekbrains.comand.geetterbackend.exceptions;

public class IncorrectRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IncorrectRequestException() {
		super();
	}
	
	public IncorrectRequestException(String msg) {
		super(msg);
	}

}
