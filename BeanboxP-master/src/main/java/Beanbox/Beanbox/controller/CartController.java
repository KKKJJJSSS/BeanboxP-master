package Beanbox.Beanbox.controller;

import Beanbox.Beanbox.dto.CartDto;
import Beanbox.Beanbox.model.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    private CartMapper cartMapper; // iBatis의 cartMapper를 주입받습니다.

    public CartController(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @RequestMapping("/add-to-cart")
    public String addToCart(@RequestParam("cart_number") int cartNumber,
                            @RequestParam("user_id") String userId,
                            @RequestParam("product_number") int productNumber,
                            @RequestParam("item_quantity") int itemQuantity) {

        // CartDto 객체 생성 및 데이터 설정
        CartDto cartDto = new CartDto();
        cartDto.setCart_number(cartNumber);
        cartDto.setUser_id(userId);
        cartDto.setProduct_number(productNumber);
        cartDto.setItem_quantity(itemQuantity);
        return "addtocart";
    }



}
