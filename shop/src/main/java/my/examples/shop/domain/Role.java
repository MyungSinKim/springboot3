package my.examples.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles") // role 라는 테이블명은 DBMS에서 생성안될 수 있다.
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment를 사용한다.
    private Long id;
    @Column(length = 20)
    private String name;
}
