package ru.geekbrains.comand.geetterbackend.entities;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private LocalDateTime dateOfBirth;
    private boolean enabled;

    @ManyToMany
    @JoinTable(
    			name = "subscriptions",
    			joinColumns = @JoinColumn(name = "user_id", referencedColumnName="id"),
    			inverseJoinColumns = @JoinColumn(name = "subscibed_to_id", referencedColumnName="id")
    		)
    private List<User> subscriptions;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
