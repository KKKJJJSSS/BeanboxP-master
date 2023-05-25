package Beanbox.Beanbox.controller;

import Beanbox.Beanbox.model.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MenuController {
    private final MenuMapper menuMapper;

    @Autowired
    public MenuController(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @GetMapping("/test")
    public String getMenuList(Model model) {
        List<String> menuList = menuMapper.getMenuNames();

        model.addAttribute("menuList", menuList);

        return "test";
    }


}
