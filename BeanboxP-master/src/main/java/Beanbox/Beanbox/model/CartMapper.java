package Beanbox.Beanbox.model;

import Beanbox.Beanbox.dto.CartDto;
import Beanbox.Beanbox.dto.RecipeDto;
import org.apache.ibatis.annotations.Insert;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CartMapper {

    @Insert("INSERT INTO shopping_cart (cart_number, user_id, product_number, item_quantity) " +
            "VALUES (#{cart_number}, #{user_id}, #{product_number}, #{item_quantity})")
    void insertCart(CartDto cartDto);


}
