package Beanbox.Beanbox.controller;

import Beanbox.Beanbox.dto.BeanDto;
import Beanbox.Beanbox.model.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller // 해당 클래스가 Spring의 Controller임을 선언
public class BeanController {
    private final BeanMapper beanMapper;
    @Autowired
    public BeanController(BeanMapper beanMapper) {
        this.beanMapper = beanMapper;
    }

    @GetMapping("/bean") // HTTP GET 요청을 처리하는 핸들러 매핑
    public String getBeanList(Model model) {
        List<BeanDto> beanList = beanMapper.getBeanList();
        model.addAttribute("beanList", beanList);
        return "result";
    }
}