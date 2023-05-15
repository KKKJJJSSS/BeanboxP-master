package Beanbox.Beanbox.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

// MyBatis의 Mapper 인터페이스를 정의하기 위한 애노테이션
@Repository
@Mapper
public interface MenuMapper {
    // menu 테이블에서 모든 메뉴 이름을 조회하는 SQL을 실행하는 메소드
    @Select("SELECT menu_name FROM menu")
    List<String> getMenuList();
}




