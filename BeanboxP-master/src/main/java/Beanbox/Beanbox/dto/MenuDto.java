package Beanbox.Beanbox.dto;


import lombok.Data;

@Data
public class MenuDto {
    private int menu_number;
    private String menu_name;
    private double menu_price;
    private String menu_description;
}
