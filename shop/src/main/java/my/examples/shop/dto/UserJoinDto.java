package my.examples.shop.dto;

import lombok.Data;

@Data // Dto에서는 사용. Entity에서는 되도록 사용하지 않는다.
public class UserJoinDto {
    private String name;
    private String email;
    private String password1;
    private String password2;
}
