package Beanbox.Beanbox;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("SELECT menu_name, menu_price FROM menu")
    List<Menu> findAllMenus();
}

