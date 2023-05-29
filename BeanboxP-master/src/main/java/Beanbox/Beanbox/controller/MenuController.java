package Beanbox.Beanbox.controller;

import Beanbox.Beanbox.dto.MenuDto;
import Beanbox.Beanbox.model.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class MenuController {
    private final MenuMapper menuMapper;

    public MenuController(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @GetMapping("/checktest")
    public String getMenuList(Model model) {
        List<MenuDto> menuList = menuMapper.getMenuList();
        model.addAttribute("menuList", menuList);
        return "checktest";
    }
}