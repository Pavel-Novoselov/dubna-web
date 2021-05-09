package ru.geekbrains.comand.geetterbackend.entities.dto;

import lombok.*;
import ru.geekbrains.comand.geetterbackend.entities.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class UserDto {

    private Long id;
    @NotEmpty
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime dateOfBirth;
    private boolean enabled;
    //@JsonProperty("roles")
    private Set<Role> roles;
    //@JsonProperty("subscriptions")
    private List<UserDto> subscriptions;
}
