package my.examples.shop.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/*
Spring Security가 제공하는 User는 로그인아이디, 암호, 권한정보를 가진다.
추가적인정보 (Long값 id, 사람이름) 등을 가지도록 User를 상속받는 클래스를 작성
 */
@Setter
@Getter
public class SecurityUser extends User {
    private Long id;
    private String name; // 사람 이름

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
    }

    public String getEmail(){
        return getUsername();
    }
}
