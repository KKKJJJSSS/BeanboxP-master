package Beanbox.Beanbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/gps")
    public String gps() {
        return "gps";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }

    @GetMapping("/order")
    public String order() {
        return "order";
    }

    @GetMapping("/sidebutton")
    public String sidebutton() {
        return "sidebutton";
    }


    @GetMapping("/coffeebean")
    public String coffee_info() {
        return "coffeebean_info";
    }
}