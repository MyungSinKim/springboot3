package my.examples.shop.repository.custom;

import my.examples.shop.domain.Item;

import java.util.List;

// 다이나믹 SQL이 필요한 메소드를 가지는 인터페이스
public interface ItemRepositoryCustom {
    public List<Item> getItems(int start, int limit, String searchType, String searchStr);
}
