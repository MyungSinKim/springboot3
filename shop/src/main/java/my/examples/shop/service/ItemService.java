package my.examples.shop.service;

import my.examples.shop.domain.Item;
import org.springframework.data.domain.Page;

public interface ItemService {
    public Page<Item> getItems(int page);

    public Page<Item> getItems(String searchStr, int page);

    public void addItem(Item item);
}
