package Beanbox.Beanbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private MenuDao menuDao;

    @Autowired
    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public List<String> getAllMenuNames() {
        List<Menu> menus = menuDao.getAllMenus();
        return menus.stream().map(Menu::getMenuName).collect(Collectors.toList());
    }

    public String getMenuNameById(int id) {
        return menuDao.getMenuById(id).getMenuName();
    }

}