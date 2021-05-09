package ru.geekbrains.comand.geetterbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ru.geekbrains.comand.geetterbackend.entities.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findOneByUsername(@NotNull String username);
    Optional<User> findOneByFirstName(@NotNull String firstName);
    Optional<User> findOneByLastName(@NotNull String lastName);
    Optional<User> findOneByEmail(@NotNull String email);
    Optional<User> findOneByPhone(@NotNull String phone);

    boolean existsByUsername(@NotNull String username);
    
    @Query(value = "SELECT u FROM User u JOIN FETCH u.subscriptions WHERE u.id=?1")
    Optional<User> findOneByIdWithSubscriptions(Long id);

    @Query(value = "SELECT u FROM User u JOIN FETCH u.subscriptions WHERE u.id=?1")
    Optional<User> findAllWithSubscriptions();

   // @Query(value="SELECT u FROM User u JOIN FETCH u.roles WHERE u.id=?1")
    Optional<User> findUserByUsername(String username);
}
