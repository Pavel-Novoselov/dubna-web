package ru.geekbrains.comand.geetterbackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.comand.geetterbackend.services.UserAuthService;
import ru.geekbrains.comand.geetterbackend.repositories.TweetRepository;

@Controller
@AllArgsConstructor
public class HomeController {

    private final TweetRepository tweetRepository;
    private final UserAuthService userAuthService;

    @RequestMapping(value = "/api/swagger", method = RequestMethod.GET)
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }

}
