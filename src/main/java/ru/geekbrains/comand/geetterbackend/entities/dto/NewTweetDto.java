package ru.geekbrains.comand.geetterbackend.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class NewTweetDto {
    private Long userId;
    private String text;
    private boolean isPublished;
}
