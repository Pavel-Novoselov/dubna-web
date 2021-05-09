package ru.geekbrains.comand.geetterbackend.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class LogInError {

    private int status;
    private String msg;
    private Date date;

    public LogInError(int status, String msg) {
        this.status = status;
        this.msg = msg;
        this.date = new Date();
    }

    // TODO: при необходимости поправить под дто ошибок
}
