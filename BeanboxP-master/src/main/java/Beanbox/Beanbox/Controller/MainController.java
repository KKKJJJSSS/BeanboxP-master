package Beanbox.Beanbox.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/recipe")
    public String recipe() {
        return "recipe";
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
    @GetMapping("/menu")
    public String menu() {
        return "menu";
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
    @GetMapping("/checktest")
    public String checktest(){ return "checktest"; }
    @GetMapping("/notice")
    public String notice(){ return "notice"; }
}