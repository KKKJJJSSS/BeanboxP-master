package Beanbox.Beanbox.dto;

// Menu 데이터를 전달하기 위한 DTO(Data Transfer Object) 클래스
public class MenuDto {
    private String menuName; // 메뉴 이름을 저장하는 멤버 변수

    // 메뉴 이름을 반환하는 메소드
    public String getMenuName() {
        return menuName;
    }

    // 메뉴 이름을 설정하는 메소드
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}