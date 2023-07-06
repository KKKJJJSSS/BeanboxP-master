package Beanbox.Beanbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/gps")
    public String gps() {
        return "gps";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}