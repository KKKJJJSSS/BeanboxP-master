package Beanbox.Beanbox.controller;

import Beanbox.Beanbox.dto.CartDto;
import Beanbox.Beanbox.dto.RecipeDto;
import Beanbox.Beanbox.dto.UserDto;
import Beanbox.Beanbox.model.CartMapper;
import Beanbox.Beanbox.model.RecipeMapper;
import Beanbox.Beanbox.model.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private RecipeMapper recipeMapper;

    @PostMapping("/register")
    @ResponseBody
    public Map<String, String> signup(@RequestBody UserDto user) {
        Map<String, String> response = new HashMap<>();

        if (userMapper.countByUsername(user.getUsername()) == 0 &&
                userMapper.countByEmail(user.getEmail()) == 0 &&
                userMapper.countByNumber(user.getNumber()) == 0) {
            userMapper.insert(user);
            response.put("result", "success");
        } else {
            response.put("result", "fail");
        }

        return response;
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> signin(@RequestBody UserDto user) {
        Map<String, Object> response = new HashMap<>();

        UserDto foundUser = userMapper.findByUsername(user.getUsername());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            response.put("result", "success");
            response.put("username", foundUser.getUsername());


        } else {
            response.put("result", "fail");
        }

        return response;
    }

    @GetMapping("/mypage")
    public String mypage(Model model, HttpSession session)  {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "login";
        } else {
            List<UserDto> userList = userMapper.getUserList();

            model.addAttribute("userList", userList);
            model.addAttribute("username", username);
        }
        return "mypage";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "login";
        } else {
            List<RecipeDto> recipeList = recipeMapper.getRecipeList();
            List<CartDto> cartList = cartMapper.getCartList();

            model.addAttribute("cartList", cartList);
            model.addAttribute("recipeList", recipeList);
            model.addAttribute("username", username);
        }
        return "cart";
    }

    @GetMapping("/checktest")
    public String checktest(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "login";
        } else {
            List<RecipeDto> recipeList = recipeMapper.getRecipeList();

            model.addAttribute("recipeList", recipeList);
            model.addAttribute("username", username);
        }
        return "checktest";
    }

}