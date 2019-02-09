package my.examples.shop.controller;

import lombok.RequiredArgsConstructor;
import my.examples.shop.domain.Item;
import my.examples.shop.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemService itemService;

    // /main 1page , /main?page=1 , /main?page=2
    @GetMapping("/main")
    public String main(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "searchStr", required = false) String searchStr,
            Model model

    ) {


        Page<Item> itemPage = null;
        if(searchStr == null) {
            itemPage = itemService.getItems(page);
        }else{
            itemPage = itemService.getItems(searchStr, page);
        }
        System.out.println(itemPage.getTotalPages()); // 전체 페이지수
        System.out.println(itemPage.getTotalElements()); // 전체 건수
        System.out.println(itemPage.getSize()); // 건수
        model.addAttribute("itemPage", itemPage);

        return "index";  // resources/templates/index.html 을 사용.
    }
}
