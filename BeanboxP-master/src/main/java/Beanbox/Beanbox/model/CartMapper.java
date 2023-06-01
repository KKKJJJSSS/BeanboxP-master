package Beanbox.Beanbox.model;

import Beanbox.Beanbox.dto.CartDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartMapper {
    @Select("SELECT * FROM shopping_cart")
    List<CartDto> getCartList();

}
