package ru.geekbrains.comand.geetterbackend.entities.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDto {

	private int status;
	private String error;
	private LocalDateTime timestamp;
	private String message;
}
