package Beanbox.Beanbox.dto;

import lombok.Data;

@Data
public class RecipeDto {
    private String coffee_name;
    private int recipe_price;
    private String recipe_description;
    private String recipe_bean;
    private int recipe_rating;
    private int sweetness;
    private int acidity;
    private int bitter;
    private int greasy;
}