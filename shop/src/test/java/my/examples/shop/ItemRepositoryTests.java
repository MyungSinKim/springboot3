package my.examples.shop;

import my.examples.shop.domain.Item;
import my.examples.shop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTests {
	@Autowired
	ItemRepository itemRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getItems() throws Exception{
		List<Item> items = itemRepository.getItems(0, 3, "LIKE_NAME", "item");

		for(Item item : items){
			System.out.println(item.getName() + ", " + item.getPrice());
		}
	}

}

