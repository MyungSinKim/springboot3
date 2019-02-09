package my.examples.shop.service;

import lombok.RequiredArgsConstructor;
import my.examples.shop.domain.Role;
import my.examples.shop.domain.User;
import my.examples.shop.repository.RoleRepository;
import my.examples.shop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public User addUser(User user) {
        Role role = roleRepository.getRole("USER");
        user.addRole(role);
        return userRepository.save(user);
    }
}
