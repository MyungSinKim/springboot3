package my.examples.shop.service;

import lombok.RequiredArgsConstructor;
import my.examples.shop.domain.Item;
import my.examples.shop.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;

    /**
     * page는 1부터 시작한다.
     * @param page
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Item> getItems(int page) {
        Pageable pageable = PageRequest.of(page -1, 5);
        Page<Item> items = itemRepository.getItems(pageable);
        return items;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Item> getItems(String searchStr, int page) {
        Pageable pageable = PageRequest.of(page -1, 5);
        Page<Item> items = itemRepository.getItems(searchStr, pageable);
        return items;
    }

    @Override
    @Transactional
    public void addItem(Item item) {
        itemRepository.save(item);
    }
}
