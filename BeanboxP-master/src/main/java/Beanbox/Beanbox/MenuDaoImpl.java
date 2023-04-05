package Beanbox.Beanbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuDaoImpl implements MenuDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MenuDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Menu> getAllMenus() {
        String sql = "SELECT menu_name FROM menu";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Menu(rs.getString("menu_name")));
    }

    @Override
    public Menu getMenuById(int id) {
        return null;
    }
}
