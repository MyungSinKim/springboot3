package my.examples.shop.repository;

import my.examples.shop.domain.Item;
import my.examples.shop.repository.custom.ItemRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
    // Pageable를 파라미터로 받아들이면 페이징 처리가 된다.
    // 페이징 처리를 하면 내부적으로 count(*)를 구하는 sql도 함께 실행된다.
    // 문제는 카운트를 못구할 때가 있다. 예를 들면 fetch join을 할 때 못구함.
    @Query(value = "select i from Item i order by i.id DESC",
           countQuery = "select count(i) from Item i")
    public Page<Item> getItems(Pageable pageable);

    @Query(value = "select i from Item i where i.name like concat('%', :searchStr, '%') order by i.id DESC",
            countQuery = "select count(i) from Item i where i.name like concat('%', :searchStr, '%') ")
    public Page<Item> getItems(@Param("searchStr") String searchStr, Pageable pageable);
}
