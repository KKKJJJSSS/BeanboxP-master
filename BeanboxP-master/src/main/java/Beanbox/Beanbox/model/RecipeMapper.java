package Beanbox.Beanbox.model;

import Beanbox.Beanbox.dto.RecipeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecipeMapper {
    @Select("SELECT * FROM recipe")
    List<RecipeDto> getRecipeList();
}
