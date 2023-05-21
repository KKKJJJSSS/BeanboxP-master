package Beanbox.Beanbox.model;

import Beanbox.Beanbox.dto.BeanDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BeanMapper {
    @Select("SELECT * FROM coffeebean")
    List<BeanDto> getBeanList();
}


