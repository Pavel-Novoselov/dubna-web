package ru.geekbrains.comand.geetterbackend.controllers;

import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.comand.geetterbackend.services.UserAuthService;
import ru.geekbrains.comand.geetterbackend.entities.dto.NewTweetDto;
import ru.geekbrains.comand.geetterbackend.entities.dto.UserDto;
import ru.geekbrains.comand.geetterbackend.helpers.JspHelper;
import ru.geekbrains.comand.geetterbackend.helpers.JspTwits;
import ru.geekbrains.comand.geetterbackend.helpers.JspUsers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
//@Api(value = "Jsp")
@AllArgsConstructor
public class JspControllerForUI {

    private final UserAuthService userAuthService;

    @RequestMapping("/")
    public String home() {
        return "/user_tweets";
    } // return to home if needed

    @RequestMapping("/users")
    public String users() {
        return "/users";
    }

    @RequestMapping("/new_user")
    public String new_user() {
        return "/new_user";
    }

    @RequestMapping("/add_user_form")
    public String add_user_form() {
        return "/add_user_form";
    }

    @RequestMapping("/user")
    public String user() {
        return "/user";
    }

    @RequestMapping("/profile")
    public String profile() {
        return "/profile";
    }

    @RequestMapping("/delete_user")
    public String delete_user() {
        return "/delete_user";
    }

    @RequestMapping("/update_user")
    public String update_user() {
        return "/update_user";
    }

    @RequestMapping("/tweets")
    public String tweets() {
        return "tweets";
    }

    @RequestMapping("/user_tweets")
    public String user_tweets() {
        return "user_tweets";
    }

    @RequestMapping("/tweet")
    public String tweet() {
        return "tweet";
    }

    @RequestMapping("/create_tweet")
    public String create_tweet() {
        return "create_tweet";
    }

    @RequestMapping("/create_user_tweet")
    public String create_user_tweet(final HttpServletRequest request, @Valid final NewTweetDto newTweetDto) throws IOException {
        JspTwits.createTweet(newTweetDto, JspHelper.generateAbsURL(request));
        return "user_tweets";
    }

    @RequestMapping("/delete_tweet")
    public String deleteTwits() {
        return "delete_tweet";
    }

    @RequestMapping("/create_comment_by_tweet")
    public String createCommentByTweet() {
        return "create_comment_by_tweet";
    }

    @RequestMapping("/do_like")
    public String doLike(final HttpServletRequest request, @RequestParam(name = "tweetId") final String tweetId) throws IOException, ParseException {
        JspTwits.doLike(tweetId, JspHelper.generateAbsURL(request));
        return "user_tweets";
    }

    @RequestMapping("/login")
    public String login(final Model model, final String error, final String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "/register";
    }

    @RequestMapping("/register_user")
    public String register_user(final HttpServletRequest request, @Valid final UserDto userDto) throws IOException {
        JspUsers.createUser(userDto, JspHelper.generateAbsURL(request));
//            if (JspUsers.getUserByUsername(String.valueOf(userDto.getUsername()), JspHelper.generateAbsURL(request)) == null) {
//                Thread.sleep(1000);
//            } else {
//                userAuthService.authWithHttpServletRequest(request, userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()));
//                return "/user_tweets";
//            }
        return "login";
    }

    @RequestMapping("/update_profile")
    public String update_profile(final HttpServletRequest request, @Valid final UserDto userDto) throws IOException {
        JspUsers.updateUser(userDto, JspHelper.generateAbsURL(request));
        return "/profile";
    }

    @RequestMapping("/user_subscriptions")
    public String userSubscriptions() {
        return "/user_subscriptions";
    }

    @RequestMapping("/subscribe")
    public String subscribeTo(final HttpServletRequest request) throws IOException {
        final String url = "/api/users/" + request.getParameter("id") +
                "/subscribe?target=" + request.getParameter("target");
        final String json = new JSONObject(request.getParameterMap()).toJSONString();
        JspUsers.putJsonToUrl(json, JspHelper.generateAbsURL(request)+url);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @RequestMapping("/unsubscribe")
    public String unsubscribeFrom(final HttpServletRequest request) throws IOException {
        final String url = "/api/users/" + request.getParameter("id") +
                "/unsubscribe?target=" + request.getParameter("target");
        final String json = new JSONObject(request.getParameterMap()).toJSONString();
        JspUsers.putJsonToUrl(json, JspHelper.generateAbsURL(request)+url);
        return "/user_subscriptions";
    }

    @RequestMapping("/forgot_password")
    public String forgot_password() {
        return "/forgot_password";
    }
}
