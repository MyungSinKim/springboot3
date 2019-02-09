package my.examples.shop.controller.admin;

import lombok.RequiredArgsConstructor;
import my.examples.shop.domain.Item;
import my.examples.shop.domain.ItemImage;
import my.examples.shop.service.ItemService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/admin/items")
@RequiredArgsConstructor
public class AdminItemController {
    private final ItemService itemService;

    @GetMapping("/writeform")
    public String writeform(){
        return "admin/items/writeform";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") int price,
            @RequestParam(name = "image1") MultipartFile image1,
            @RequestParam(name = "image2") MultipartFile image2
            ){

        String file1 = createUUIDFileName();
        String file2 = createUUIDFileName();

        // image1의 내용을 file1에 저장하는 코드
        // image2의 내용을 file2에 저장하는 코드

        Item item = new Item();
        item.setName(name);
        item.setPrice(price);

        ItemImage itemImage1 = new ItemImage();
        itemImage1.setContentType(image1.getContentType());
        itemImage1.setFileLength(image1.getSize());
        itemImage1.setFileName(image1.getOriginalFilename());
        itemImage1.setSaveFileName(file1);
        item.addImage(itemImage1);

        ItemImage itemImage2 = new ItemImage();
        itemImage2.setContentType(image2.getContentType());
        itemImage2.setFileLength(image2.getSize());
        itemImage2.setFileName(image2.getOriginalFilename());
        itemImage2.setSaveFileName(file2);
        item.addImage(itemImage2);

        itemService.addItem(item);

        return "redirect:/admin/items/writeform";
    }

    private String createUUIDFileName(){

        String dir = "/tmp/2019/02/09/"; // 날짜를 이용해 폴더를 생성
        File fileDir = new File(dir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        String uuid = UUID.randomUUID().toString(); // uuid값을 구한다.
        return dir + uuid;
    }
}
