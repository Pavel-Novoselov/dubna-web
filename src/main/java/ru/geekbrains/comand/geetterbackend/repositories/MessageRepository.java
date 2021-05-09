package ru.geekbrains.comand.geetterbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.geekbrains.comand.geetterbackend.entities.Message;

import java.util.Collection;

public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {

    Collection<Message> findByReceiverIdOrderByCreatedDesc(Long senderId);
    Collection<Message> findBySenderIdOrderByCreatedDesc(Long receiverId);
}
