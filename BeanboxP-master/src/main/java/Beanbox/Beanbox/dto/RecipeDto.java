package Beanbox.Beanbox.dto;

import lombok.Data;

@Data
public class RecipeDto {
    public String coffee_name;
    public int recipe_price;
    public String recipe_description;
    public String recipe_bean;
    public int sweetness;
    public int acidity;
    public int bitter;
    public int greasy;
    public String img_number;
}