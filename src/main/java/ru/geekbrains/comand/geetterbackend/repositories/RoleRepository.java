package ru.geekbrains.comand.geetterbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.geekbrains.comand.geetterbackend.entities.Role;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    Optional<Role> findOneByName(@NotNull String name);
}
