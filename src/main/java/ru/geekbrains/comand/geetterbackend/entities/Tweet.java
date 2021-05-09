package ru.geekbrains.comand.geetterbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tweets")
@Builder
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "tweet")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Comment> comments;

    // TODO: это реализация через хибернейт, будем подтягивать или напишем другую?
    // @Size(max = 140, message = "Введите текст длинной до 140 символов.")
    @Column(nullable = false)
    private String text;

    private Boolean isPublished;

    private Long likes;
}
