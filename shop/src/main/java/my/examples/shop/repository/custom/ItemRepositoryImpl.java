package my.examples.shop.repository.custom;

import com.querydsl.jpa.JPQLQuery;
import my.examples.shop.domain.Item;
import my.examples.shop.domain.QItem;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ItemRepositoryImpl extends QuerydslRepositorySupport implements ItemRepositoryCustom {

    public ItemRepositoryImpl(){
        super(Item.class); // 부모클래스에게 사용할 Entity클래스 정보를 전달한다.
    }

    @Override
    public List<Item> getItems(int start, int limit, String searchType, String searchStr) {
        // searchType LIKE_NAME : select i from Item i where i.name like concat('%' + :searchStr + '%') order by i.id asc
        // searchType EQ_NAME :select i from Item i where i.name = :searchStr order by i.id asc

        QItem qItem = QItem.item;  // 엔티티명을 대신하는 q클래스인스턴스 생성
        JPQLQuery<Item> query = from(qItem);  // select i from Item i

        if("LIKE_NAME".equals(searchType)){
            query.where(qItem.name.like("%" + searchStr + "%")); // select i from Item i where i.name like concat('%' + :searchStr + '%')
        }else{
            query.where(qItem.name.eq(searchStr)); //select i from Item i where i.name = :searchStr
        }
        query.orderBy(qItem.id.asc()); // order by i.id asc
        query.offset(start).limit(limit); // limit :start, :limit
        return query.fetch();
    }
}
