package my.examples.shop.service;

import lombok.RequiredArgsConstructor;
import my.examples.shop.domain.Role;
import my.examples.shop.domain.User;
import my.examples.shop.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor // 필드중에 final 한 변수만 받아들이는 생성자를 자동으로 만들어준다.
public class ShopUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userByEmail = userRepository.getUserByEmail(email);

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        Set<Role> roles = userByEmail.getRoles();
        for(Role role : roles){
           list.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        // Spring Security가 제공하는 UserDetails 를 구현하는 객체에
        // 추가적인 정보를 가지도록 클래스를 만들었다.
        SecurityUser user =
                new SecurityUser(email,
                        userByEmail.getPasswd(),
                        list);
        user.setId(userByEmail.getId());
        user.setName(userByEmail.getName());
        return user;
    }
}
