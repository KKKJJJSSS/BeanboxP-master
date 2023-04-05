package Beanbox.Beanbox;

import java.util.List;

public interface MenuDao {
    List<Menu> getAllMenus();

    Menu getMenuById(int id);
}

