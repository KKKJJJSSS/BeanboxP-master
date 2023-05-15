package Beanbox.Beanbox.controller;

import Beanbox.Beanbox.model.MenuMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller // 해당 클래스가 Spring의 Controller임을 선언
public class MenuController {
    @Autowired // MenuMapper를 자동으로 주입
    private MenuMapper menuMapper; // MenuMapper 객체 선언

    @GetMapping("/test") // HTTP GET 요청을 처리하는 핸들러 매핑
    public String getMenuList(Model model) { // Model 객체를 매개변수로 받는 getMenuList 메소드
        List<String> menuList = menuMapper.getMenuList(); // MenuMapper 객체의 getMenuList 메소드를 호출하여 메뉴 목록을 가져옴
        model.addAttribute("menuList", menuList); // Model 객체에 "menuList"라는 이름으로 메뉴 목록을 추가함
        return "test"; // "test" 뷰 이름 반환
    }
}