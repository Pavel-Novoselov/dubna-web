package ru.geekbrains.comand.geetterbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
//@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private String text;

    public Comment(final User user, final Tweet tweet, final LocalDateTime created, final String text) {
        this.user = user;
        this.tweet = tweet;
        this.created = created;
        this.text = text;
    }
}
