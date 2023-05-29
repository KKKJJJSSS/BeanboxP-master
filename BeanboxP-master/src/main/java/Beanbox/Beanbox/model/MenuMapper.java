package Beanbox.Beanbox.model;

import Beanbox.Beanbox.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenuMapper {
    @Select("SELECT menu_name,menu_price FROM menu")
    List<MenuDto> getMenuList();
}



