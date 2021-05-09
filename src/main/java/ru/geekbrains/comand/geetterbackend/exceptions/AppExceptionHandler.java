package ru.geekbrains.comand.geetterbackend.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.geekbrains.comand.geetterbackend.entities.dto.ExceptionDto;

@RestControllerAdvice
public class AppExceptionHandler {
	
	private static final String DEFAULT_MSG = "Oops! Something went wrong";

	@ExceptionHandler(NotFoundForTheRequestedParamException.class)
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	public ExceptionDto notFound(NotFoundForTheRequestedParamException e) {
		return fromException(e, HttpStatus.I_AM_A_TEAPOT);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ExceptionDto duplication(SQLIntegrityConstraintViolationException e) {
		return fromException(e, HttpStatus.CONFLICT); // FIXME: create a pretty message in DTO
	}
	
	@ExceptionHandler(IncorrectRequestException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ExceptionDto incorrectRequest(IncorrectRequestException e) {
		return fromException(e, HttpStatus.CONFLICT);
	}
	
	private ExceptionDto fromException(Exception e, HttpStatus status) {
		return ExceptionDto
				.builder()
				.status(status.value())
				.error(status.getReasonPhrase())
				.timestamp(LocalDateTime.now())
				.message(e.getMessage() == null ? DEFAULT_MSG : e.getMessage())
				.build();
	}
	
}
