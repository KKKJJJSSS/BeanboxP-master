package Beanbox.Beanbox.controller;

import Beanbox.Beanbox.dto.CartDto;
import Beanbox.Beanbox.dto.RecipeDto;
import Beanbox.Beanbox.dto.UserDto;
import Beanbox.Beanbox.model.CartMapper;
import Beanbox.Beanbox.model.RecipeMapper;
import Beanbox.Beanbox.model.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final UserMapper userMapper;

    private final HttpSession httpSession;

    private final CartMapper cartMapper;

    private final RecipeMapper recipeMapper;

    public UserController(UserMapper userMapper, HttpSession httpSession, CartMapper cartMapper, RecipeMapper recipeMapper) {
        this.userMapper = userMapper;
        this.httpSession = httpSession;
        this.cartMapper = cartMapper;
        this.recipeMapper = recipeMapper;
    }

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

            httpSession.setAttribute("username", foundUser.getUsername());
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
        }

        List<RecipeDto> recipeList = recipeMapper.getRecipeList();

        model.addAttribute("recipeList", recipeList);
        model.addAttribute("username", username);

        return "checktest";
    }


    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(HttpSession session, @RequestBody String coffeeName) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }

        List<String> allCoffeeNames = cartMapper.getAllCoffeeNamesForUser(username);

        if (!allCoffeeNames.contains(coffeeName)) {
            cartMapper.saveToCart(coffeeName, username);
            return new ResponseEntity<>("상품이 성공적으로 장바구니에 추가되었습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("이미 추가된 상품입니다.", HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/auth/delete_coffee_bean", method = RequestMethod.POST)
    public ResponseEntity deleteCoffeeBean(@RequestParam("cart_number") int cartNumber, HttpSession session) {

        // 삭제 작업 수행
        int deletedRowCount = cartMapper.deleteCoffeeBeanByCartNumber(cartNumber);

        if (deletedRowCount > 0) {
            return ResponseEntity.ok("삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제할 항목을 찾을 수 없습니다.");
        }
    }

    @GetMapping("/cartrecipe")
    public String getRecipeList(@RequestParam("img_number") String img_number, Model model) {
        List<RecipeDto> recipeList = recipeMapper.getRecipeList();

        model.addAttribute("recipeList", recipeList);
        model.addAttribute("img_number", img_number);
        return "cartrecipe";
    }
}
