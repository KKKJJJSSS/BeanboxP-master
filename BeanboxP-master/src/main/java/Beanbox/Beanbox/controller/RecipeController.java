package Beanbox.Beanbox.controller;

import Beanbox.Beanbox.dto.RecipeDto;
import Beanbox.Beanbox.model.RecipeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller // 해당 클래스가 Spring의 Controller임을 선언
public class RecipeController {
    private final RecipeMapper recipeMapper;
    @Autowired
    public RecipeController(RecipeMapper recipeMapper) {
        this.recipeMapper = recipeMapper;
    }

    @GetMapping("/test") // HTTP GET 요청을 처리하는 핸들러 매핑
    public String getRecipeList(Model model) {
        List<RecipeDto> recipeList = recipeMapper.getRecipeList();
        model.addAttribute("recipeList", recipeList);
        return "test";
    }
}