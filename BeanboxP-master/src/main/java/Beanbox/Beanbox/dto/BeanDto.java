package Beanbox.Beanbox.dto;

import lombok.Data;

@Data
public class BeanDto {
    private String bean_name;
    private String bean_description;
    private int aroma;
    private int sweetness;
    private int acidity;
    private int bitter;
}
