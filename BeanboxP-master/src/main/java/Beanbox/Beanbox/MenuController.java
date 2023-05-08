package Beanbox.Beanbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    @Autowired
    private MenuMapper menuMapper;

    @GetMapping("/menu/{menuId}")
    public Menu getMenuById(@PathVariable Long menuId) {
        return menuMapper.getMenuById(menuId);
    }
}
