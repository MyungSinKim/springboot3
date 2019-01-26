package my.examples.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/main")
    public String main() {
        return "index";  // resources/templates/index.html 을 사용.
    }
}
