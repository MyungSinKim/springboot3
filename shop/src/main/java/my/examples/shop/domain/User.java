package my.examples.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account") // user 라는 테이블명은 DBMS에서 생성안될 수 있다.
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment를 사용한다.
    private Long id;
    @Column(length = 100)
    private String name;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String passwd;
    private LocalDateTime createDate;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") ,
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
    private Set<Role> roles;

    // 엔티티의 초기값을 설정하기 위한 생성자를 만든다.
    public User(){
        createDate = LocalDateTime.now();
        roles = new HashSet<>();
    }

    public void addRole(Role role){
        if(roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
