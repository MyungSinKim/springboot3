package my.examples.shop.controller;

import lombok.RequiredArgsConstructor;
import my.examples.shop.domain.User;
import my.examples.shop.dto.UserJoinDto;
import my.examples.shop.service.UserService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(
            @RequestParam(name = "fail",
                    required = false,
                    defaultValue = "false") String errorFlag){

        return "users/login"; // view name 을 리턴한다.
    }

    @GetMapping("/join")
    public String joinform(){
        return "users/joinform";
    }

    // form 으로부터 전달된 값이 객체에 저장된다.
    @PostMapping("/join")
    public String join(@ModelAttribute UserJoinDto userJoinDto){
//        Assert.hasText(userJoinDto.getName(), "이름을 입력하세요.");
        if(userJoinDto.getName() != null &&
            userJoinDto.getName().length() < 2){
            throw new IllegalArgumentException("이름을 입력하세요.");
        }
        if(!userJoinDto.getPassword1().equals(userJoinDto.getPassword2())){
            throw new IllegalArgumentException("암호가 일치하지 않는다.");
        }

        User user = new User();
        user.setName(userJoinDto.getName());
        user.setEmail(userJoinDto.getEmail());

        PasswordEncoder passwordEncoder
                = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPasswd(passwordEncoder.encode(userJoinDto.getPassword1()));

        // UserService를 이용하여 저장한다.
        userService.addUser(user);
        return "redirect:/users/welcome";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "users/welcome";
    }
}
