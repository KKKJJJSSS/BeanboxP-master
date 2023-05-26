package Beanbox.Beanbox.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenuMapper {
    @Select("SELECT * FROM menu")
    List<String> getMenuNames();
}



