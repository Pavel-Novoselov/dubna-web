package ru.geekbrains.comand.geetterbackend.controllers;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import ru.geekbrains.comand.geetterbackend.entities.Message;
import ru.geekbrains.comand.geetterbackend.entities.User;
import ru.geekbrains.comand.geetterbackend.repositories.MessageRepository;
import ru.geekbrains.comand.geetterbackend.repositories.UserRepository;

import java.util.Collection;

@Controller
@RequestMapping(path = "/messages")
@AllArgsConstructor
public class MessageController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    // блок мэсседжей юзера
    @GetMapping(path = "/{id}")
    public String showMessagesByUserId(@PathVariable Long id, Model model,
                                       @SessionAttribute(name="user") User user) {
        model.addAttribute("user", user);
        Collection<Message> receivedMessages = messageRepository.findByReceiverIdOrderByCreatedDesc(id);
        model.addAttribute("receivedMessages", receivedMessages);
        Collection<Message> sendMessages = messageRepository.findBySenderIdOrderByCreatedDesc(id);
        model.addAttribute("sendMessages", sendMessages);
        return "messages/userMessages";
    }

    // блок отправки мэсседжей
    @GetMapping(path = "/send/{id}")
    public String sendMessageToUserId(@PathVariable Long id, Model model) throws NotFoundException {
        model.addAttribute("newMessage", new Message());
        User sendTo = userRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("user with username id: " + id + " not found"));
        model.addAttribute("sendTo", sendTo);
        return "messages/newMessage";
    }

    // опционально - инфа по мэсседжу
    @GetMapping(path = "/messageDetails/{id}")
    public String messageDetails(@PathVariable Long id, Model model) throws NotFoundException {
        Message message = messageRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("message with id: " + id + " not found"));
        //message.setNotReadYet(false);
        messageRepository.saveAndFlush(message);
        model.addAttribute("message", message);
        return "messages/messageDetails";
    }
}
